package net.codejava.spring;

import java.util.List;

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.CakeTO;
import net.codejava.spring.model.CartTo;
import net.codejava.spring.model.DoctorWhereAbouts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CakeNBakeHomeController {
	
	@Autowired
	private UserDAO userDao;
	
	
	@RequestMapping(value = "/cakenbake/getHomePageCakes", method = RequestMethod.GET )
	public @ResponseBody List<CakeTO> getHomePageCakes() throws Exception {
		
		return userDao.getHomeCakes();
		
	}
	

	@RequestMapping(value = "/cakenbake/addToCart", method = RequestMethod.POST,consumes="application/json" )
	public @ResponseBody boolean addToCart(@RequestBody CartTo cartTo) throws Exception {
		
		userDao.addToCart(cartTo);
		return false;
		
	}

	
	@RequestMapping(value = "/cakenbake/filterByOccasion", method = RequestMethod.POST,consumes="application/json" )
	public @ResponseBody List<CakeTO> filterByOccasion(@RequestBody String occasion) throws Exception {
		if(occasion != null && occasion.equals("all")){
			return userDao.getHomeCakes();
		}else{
		return userDao.filterByOccasion(occasion);
		}
		
		
	}
	
	@RequestMapping(value = "cakenbake/filterByCakeType", method = RequestMethod.POST,consumes="application/json" )
	public @ResponseBody List<CakeTO> filterByCakeType(@RequestBody String cakeType) throws Exception {
		
		return userDao.filterByCakeType(cakeType);
		
		
	}
	
	@RequestMapping(value = "cakenbake/filterByCakeId", method = RequestMethod.POST,consumes="application/json" )
	public @ResponseBody CakeTO filterByCakeId(@RequestBody String cakeId) throws Exception {
		
		return userDao.filterByCakeId(Integer.parseInt(cakeId));
		
		
	}
	
	
}
