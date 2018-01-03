package net.codejava.spring.model;

import java.util.List;

public class OrderDetails {
	private String name;
	private String emailId;
	private String phoneNo;
	private String message;
	private String zipCode;
	private String address1;
	private String address2;
	private List<CakeTO> cakeTOList;
	
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	public List<CakeTO> getCakeTOList() {
		return cakeTOList;
	}

	public void setCakeTOList(List<CakeTO> cakeTOList) {
		this.cakeTOList = cakeTOList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
	
	
	
}
