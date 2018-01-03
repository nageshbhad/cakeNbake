package net.codejava.spring.model;

public class CakeTO {
	
	public CakeTO() {
		// TODO Auto-generated constructor stub
	}
	
	private String cakeName;
	private String cakeUrl;
	private double cakePrice;
	private int cakeId;
	private String size;
	private String type;
	private String sub_type;
	private String occassion;
	
	
	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public CakeTO(String cakeName, String cakeUrl, double cakePrice, int cakeId) {
		super();
		this.cakeName = cakeName;
		this.cakeUrl = cakeUrl;
		this.cakePrice = cakePrice;
		this.cakeId = cakeId;
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
	public int getCakeId() {
		return cakeId;
	}
	public void setCakeId(int cakeId) {
		this.cakeId = cakeId;
	}
	
	
	
	

}
