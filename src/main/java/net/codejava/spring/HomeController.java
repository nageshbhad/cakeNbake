package net.codejava.spring;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.UserDAO;
import net.codejava.spring.model.CakeTO;
import net.codejava.spring.model.OrderDetails;
import net.codejava.spring.service.mail.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "/cakenbake/placeOrder", method = RequestMethod.POST ,consumes="application/json")
	public @ResponseBody String getDummyEmployee(HttpServletRequest request,@RequestBody OrderDetails orderDetails) throws Exception {
		System.out.println("customer EmailId:"+orderDetails.getEmailId());
		String customerEmail = orderDetails.getEmailId();
		String subject = "Order Received successfully!";
		String customerBody = getPatientMsgBody(orderDetails.getName());
		String url = request.getRequestURL().toString();
		String baseUrl = url.substring(0,url.length()- "/cakenbake/placeOrde".length());
		System.out.println(baseUrl+"#/order/");
		String ownerMsgBody = getDoctorMsgBody(orderDetails,baseUrl);
		boolean ownerMessageSent = mailService.sendMail("", "satish.cb@gmail.com,nagesh101093@gmail.com", ownerMsgBody,subject,
				customerEmail);
		boolean customerMessageSent = false;
		if(ownerMessageSent){
			 customerMessageSent = mailService.sendMail("", customerEmail, customerBody,subject,
					"nagesh101093@gmail.com");
		}
		return customerMessageSent+"";
		
	}

	private String getDoctorMsgBody(OrderDetails appointment, String baseUrl) {
		String spaces = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		String body ="";
		for (CakeTO cakeItem : appointment.getCakeTOList()) {
			String cake =System.getProperty("line.separator")+"<h3>"+cakeItem.getCakeName()+spaces+cakeItem.getType()+spaces+ cakeItem.getSize()+spaces+cakeItem.getCakePrice()+spaces+baseUrl+"#/order/"+cakeItem.getCakeId()+"</h3></br>";
			body = body+cake;
		}
		return 	String.join(
	    	    System.getProperty("line.separator"),
	    	    "Dear CakenBake,",
	    	    System.getProperty("line.separator"),
	    	    "<h4>You have received Cake Order request from "+appointment.getName()+"!</h4>",
	    	    System.getProperty("line.separator"),
	    	    "<p>Preferred Details from Customer are: </p> ", 
	    	    
	    	   body,
	    	    "<p>Kindly confirm your availability to customer on his/her number "+appointment.getPhoneNo()+" .</p></br>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h4 style='color:green'>Shipping Details</h4>",
	    	    System.getProperty("line.separator"),
	    	    "<p>Zip code:"+appointment.getZipCode()+"</p>",
	    	    System.getProperty("line.separator"),
	    	    "<p>Address line 1:"+appointment.getAddress1()+"</p>",
	    	    System.getProperty("line.separator"),
	    	    "<p>Address line 2:"+appointment.getAddress2()+"</p>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h5>Message by Customer:"+appointment.getMessage()+"</h5>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h5>Regards </h5>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "My-CakenBake"
	    	);
	}

	private String getPatientMsgBody(String name) {
		return 	String.join(
	    	    System.getProperty("line.separator"),
	    	    "Dear "+name+",",
	    	    "<h2>Thank you for Placing Order with us !</h2></br>",
	    	    "<h2> We are happy to serve you "+name +"</h2>",
	    	    "<p>To Modify you order please call us at : 9158666757 </p> ", 
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "<h5>Regards </h5>",
	    	    System.getProperty("line.separator"),
	    	    System.getProperty("line.separator"),
	    	    "My-CakenBake"
	    	);
		
	}
	
}
