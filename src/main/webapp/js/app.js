

var app = angular.module('CakenBake', ['ngRoute']);
app.factory('httpInterceptor', function ($q, $rootScope, $log) {

    var numLoadings = 0;

    return {
        request: function (config) {

            numLoadings++;

            // Show loader
            $rootScope.$broadcast("loader_show");
            return config || $q.when(config)

        },
        response: function (response) {

            if ((--numLoadings) === 0) {
                // Hide loader
                $rootScope.$broadcast("loader_hide");
            }

            return response || $q.when(response);

        },
        responseError: function (response) {

            if (!(--numLoadings)) {
                // Hide loader
                $rootScope.$broadcast("loader_hide");
            }

            return $q.reject(response);
        }
    };
})
.config(function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
});

app.directive("loader", function ($rootScope) {
    return function ($scope, element, attrs) {
        $scope.$on("loader_show", function () {
            return element.show();
        });
        return $scope.$on("loader_hide", function () {
            return element.hide();
        });
    };
}
)

app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/home", {templateUrl: "home.html", controller: "PageCtrl"})
    .when("/", {templateUrl: "home.html", controller: "PageCtrl"})
    .when("/cartDetails",{templateUrl:"cart.html",controller:"CartController"})
    .when("/checkout",{templateUrl:"checkout.html",controller:"CheckoutController"})
    .when("/orderSuccess",{templateUrl:"success.html",controller:"CheckoutController"})
    .when('/order/:order_Id', {templateUrl:"orderDetails.html",controller:"OwnerController"})
    // Pages 
    .otherwise("/404", {templateUrl: "404.html", controller: "PageCtrl"});
}]);


app.controller('OwnerController', function ( $scope,$rootScope,$window,$routeParams,$location, $http,cart ) {
	$scope.cakeObj={};
	$scope.cakeId = $routeParams.order_Id;
	
	 var req = {
			 method: 'POST',
			 url: 'cakenbake/filterByCakeId',
			
			 data: $scope.cakeId 
			}

			$http(req).then(function(res){
				$scope.cakeObj=res.data;
			}, function(){
				console.log("unsuccessful")
			});
})
	


app.factory('$localstorage', ['$window', function($window) {
  return {
    set: function(key, value) {
      $window.localStorage[key] = value;
    },
    get: function(key, defaultValue) {
      return $window.localStorage[key] || defaultValue || false;
    },
    setObject: function(key, value) {
      $window.localStorage[key] = JSON.stringify(value);
    },
    getObject: function(key, defaultValue) {
      if($window.localStorage[key] != undefined){
          return JSON.parse($window.localStorage[key]);
      }else{
        return defaultValue || false;
      }
    },
    remove: function(key){
      $window.localStorage.removeItem(key);
    },
    clear: function(){
      $window.localStorage.clear();
    }
  }
}]);







app.controller('PageCtrl', function ( $scope,$rootScope,$window,$location, $http,cart ) {
	
	$scope.homePageProducts=[];
	  $http.get('cakenbake/getHomePageCakes').then(function(response) {
	      $scope.homePageProducts = response.data;
	      console.log( $scope.homePageProducts);
	  });
	  

	  $scope.filterOccasion = function(occasion){
		  
		  var req = {
					 method: 'POST',
					 url: 'cakenbake/filterByOccasion',
					
					 data: occasion 
					}

					$http(req).then(function(res){
						$scope.homePageProducts=[];
						$scope.homePageProducts=res.data;
					}, function(){
						console.log("unsuccessful")
					});
		  
	  }
	  
 $scope.filterCakeType = function(cakeType){
		  
		  var req = {
					 method: 'POST',
					 url: 'cakenbake/filterByCakeType',
					
					 data: cakeType 
					}

					$http(req).then(function(res){
						$scope.homePageProducts=[];
						$scope.homePageProducts=res.data;
					}, function(){
						console.log("unsuccessful")
					});
		  
	  }
	/*$scope.homePageProducts=[
{
	"cakeUrl":"images/cakenBake/1.JPG",
	"cakePrice":180,
	"cakeName":"blackForest",
	"cakeId":101
},

{
	"cakeUrl":"images/cakenBake/2.JPG",
	"cakePrice":180,
	"cakeName":"blackForest",
	"cakeId":102
},

{
	"cakeUrl":"images/cakenBake/3.JPG",
	"cakePrice":180,
	"cakeName":"blackForest",
	"cakeId":103
},

{
	"cakeUrl":"images/cakenBake/4.JPG",
	"cakePrice":180,
	"cakeName":"blackForest",
	"cakeId":104
}
	]
*/	$scope.addToCart = function(x){
		cart.addObjectToCart(x);
	/*x.userId = "915666757";
		 var req = {
				 method: 'POST',
				 url: 'cakenbake/addToCart',
				
				 data: x 
				}

				$http(req).then(function(res){
					console.log(res);
					
				}, function(){
					console.log("unsuccessful")
				});*/
		$location.path('cartDetails');
	}
  	
  });

app.controller('CartController', function ( $scope,$rootScope,$window,$location, $http,cart ) {
	$scope.cartAddedProducts=cart.getCartObjects();
	$scope.totalAmt = cart.getCartTotal();
	$scope.deleteFromCart = function(x){
		cart.removeObjectFromCart(x);
		$scope.totalAmt = cart.getCartTotal();
	}
  	
  });

app.controller('CheckoutController', function ( $scope,$rootScope,$window,$location, $http,cart ) {
	$scope.cartAddedProducts=cart.getCartObjects();
	$scope.totalAmt = cart.getCartTotal();
	$scope.deleteFromCart = function(x){
		cart.removeObjectFromCart(x);
		$scope.totalAmt = cart.getCartTotal();
		


	}
	
	$scope.placeOrder = function(orderDetails){
		var cartObject = cart.getCartObjects();
		orderDetails.cakeTOList = cartObject;
		
		 var req = {
				 method: 'POST',
				 url: 'cakenbake/placeOrder',
				
				 data: orderDetails 
				}

				$http(req).then(function(res){
					console.log(res);
					$location.path('orderSuccess');
					
				}, function(){
					
				});
		
	}
  	
  });


app.service('cart', function() {

	this.cartObjects = [];
	this.totalAmt = 0;
    this.addObjectToCart = function (cakeObject) {
        this.cartObjects.push(cakeObject);
        console.log("object added",cakeObject);
    }

    this.removeObjectFromCart = function(cakeObject){
var index = this.cartObjects.indexOf(cakeObject);
  this.cartObjects.splice(index, 1); 
    }

    this.getCartObjects = function() {
    	return this.cartObjects;
    }

    this.getCartTotal = function(){
    	var total = 0;
    	angular.forEach(this.cartObjects, function(value, key) {
    		total = total + value.cakePrice;

});
    	return total;
    }
});


