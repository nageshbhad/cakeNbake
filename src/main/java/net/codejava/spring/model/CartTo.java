package net.codejava.spring.model;

public class CartTo {
	
	
	private String cakeName;
	private String cakeUrl;
	private double cakePrice;
	private String userId;
	private int cakeId;
	private String type;
	private String sub_type;
	private String occassion;
	private int cartid;
	
	public CartTo(){
		
	}
	
	
	
	
	public CartTo(String cakeName, String cakeUrl, double cakePrice,
			String userId, int cakeId, String type, String sub_type,
			String occassion, int cartid) {
		super();
		this.cakeName = cakeName;
		this.cakeUrl = cakeUrl;
		this.cakePrice = cakePrice;
		this.userId = userId;
		this.cakeId = cakeId;
		this.type = type;
		this.sub_type = sub_type;
		this.occassion = occassion;
		this.cartid = cartid;
	}




	public String getCakeName() {
		return cakeName;
	}
	public void setCakeName(String cakeName) {
		this.cakeName = cakeName;
	}
	public String getCakeUrl() {
		return cakeUrl;
	}
	public void setCakeUrl(String cakeUrl) {
		this.cakeUrl = cakeUrl;
	}
	public double getCakePrice() {
		return cakePrice;
	}
	public void setCakePrice(double cakePrice) {
		this.cakePrice = cakePrice;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCakeId() {
		return cakeId;
	}
	public void setCakeId(int cakeId) {
		this.cakeId = cakeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSub_type() {
		return sub_type;
	}
	public void setSub_type(String sub_type) {
		this.sub_type = sub_type;
	}
	public String getOccassion() {
		return occassion;
	}
	public void setOccassion(String occassion) {
		this.occassion = occassion;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	
	
	

}
