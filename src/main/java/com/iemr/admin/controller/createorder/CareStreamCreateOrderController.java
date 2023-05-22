package com.iemr.admin.controller.createorder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.admin.data.blocking.M_Serviceprovider_Blocking;
import com.iemr.admin.data.createOrder.CreateOrderData;
import com.iemr.admin.utils.mapper.InputMapper;
import com.iemr.admin.utils.response.OutputResponse;

@RestController
public class CareStreamCreateOrderController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private static final char END_OF_BLOCK='\u001c';
	private static final char START_OF_BLOCK='\u000b';
	private static final char CARRIAGE_RETURN=13;
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/createOrder" , headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String createOrder(@RequestBody String createOrder) throws UnknownHostException, IOException{
		//JSONObject requestOBJ = new JSONObject(providerBlocking);
		OutputResponse response = new OutputResponse();

		try {
			
			CreateOrderData benificiaryDetails = InputMapper.gson().fromJson(createOrder,
					CreateOrderData.class);
	
	//public static void main(String[] args)  {
			
			String firstName=benificiaryDetails.getFirstName();
			String middleName=benificiaryDetails.getMiddleName();
			String lastName=benificiaryDetails.getLastName();
			String patientID=benificiaryDetails.getPatientID();
			String dob=benificiaryDetails.getDob();
			String gender=benificiaryDetails.getGender();
			String acc=benificiaryDetails.getAcc();
		
		Socket socket=new Socket("192.168.1.199",1235);
		System.out.println("Connected to Server");
		
		StringBuffer testHL7MassageToTransmit= new  StringBuffer();
		testHL7MassageToTransmit.append(START_OF_BLOCK)
		.append("MSH|^~"+"\"&|ImageSuite||||20120730000000.996||ORM^O01|MSG02010101_1|P|2.5|||AL||USA|ASCII||||")
		.append(CARRIAGE_RETURN)
		.append("PID|||"+patientID+"||"+firstName+"^"+lastName+"^||"+dob+"|"+gender+"|||||||||||||||||||||||||||Species Code|Breed Code")
		.append(CARRIAGE_RETURN)
		.append("ORC|NW||"+ acc +"||||20110621|||||Referring Physician Felix||||||||||||||||||")
		.append(CARRIAGE_RETURN)
		.append("OBR|0001|||CR_CHEST_PA|||20110622000000.996|||||||||Referring Physician Felix|||||||||||20110624|||||||||20110624||||||||CR_CHEST_PA|||||")
		.append(END_OF_BLOCK)
		.append(CARRIAGE_RETURN);
		
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		//Send the MLLP -wrapped HL7 message to the server
		out.write(testHL7MassageToTransmit.toString().getBytes());
		
		byte[] byteBuffer=new byte[50];
		in.read(byteBuffer);
		//System.out.println("Receiver from server: "+ new String(byteBuffer));
		
		response.setResponse("Receiver from server: "+ new String(byteBuffer));
		//Close the socket and its streams
	
		//socket.close();
		
		//socket.getInputStream();
		
		String ip="localhost";
		int port=8082;
		
		String testMassage="This is the test Massage that the client will transmit";
		byte[] byteBuffer1=testMassage.getBytes();
		Socket socket1=new Socket(ip,port);
		System.out.println("connected to the Server");
		InputStream in1=socket.getInputStream();
		OutputStream out1=socket.getOutputStream();
		
		//Send the Massage to the server
		out1.write(byteBuffer);
		in1.read(byteBuffer);
		//Close the socket and its Streams
		System.out.println("Massage received form Server11"+ new String(byteBuffer));
		
		socket.close();
		
		
		
		
		
	
	

		} catch (Exception e) {
			
			logger.error("Unexpected error:" , e);
			response.setError(e);

		}
		
		/**
		 * sending the response...
		 */
		return response.toString();
		}
	
	
	    
	@CrossOrigin()
	@RequestMapping(value =  "/UpdateOrder" , headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String UpdateOrder(@RequestBody String UpdateOrder) throws UnknownHostException, IOException{
		//JSONObject requestOBJ = new JSONObject(providerBlocking);
		OutputResponse response = new OutputResponse();

		try {
			
			CreateOrderData benificiaryDetails = InputMapper.gson().fromJson(UpdateOrder,
					CreateOrderData.class);
	
	//public static void main(String[] args)  {
			
			String firstName=benificiaryDetails.getFirstName();
			String middleName=benificiaryDetails.getMiddleName();
			String lastName=benificiaryDetails.getLastName();
			String patientID=benificiaryDetails.getPatientID();
			String dob=benificiaryDetails.getDob();
			String gender=benificiaryDetails.getGender();
			String acc=benificiaryDetails.getAcc();
		
		Socket socket=new Socket("192.168.1.101",1235);
		System.out.println("Connected to Server");
		
		StringBuffer testHL7MassageToTransmit= new  StringBuffer();
		testHL7MassageToTransmit.append(START_OF_BLOCK)
		.append("MSH|^~"+"\"&|ImageSuite||||20120730000000.996||ORM^O01|MSG02010101_1|P|2.5|||AL||USA|ASCII||||")
		.append(CARRIAGE_RETURN)
		.append("PID|||"+patientID+"||"+firstName+"^"+lastName+"^||"+dob+"|"+gender+"|||||||||||||||||||||||||||Species Code|Breed Code")
		.append(CARRIAGE_RETURN)
		.append("ORC|XO||"+ acc +"||||20110621|||||Referring Physician Felix||||||||||||||||||")
		.append(CARRIAGE_RETURN)
		.append("OBR|0001|||CR_CHEST_PA|||20110622000000.996|||||||||Referring Physician Felix|||||||||||20110624|||||||||20110624||||||||CR_CHEST_PA|||||")
		.append(END_OF_BLOCK)
		.append(CARRIAGE_RETURN);
		
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		//Send the MLLP -wrapped HL7 message to the server
		out.write(testHL7MassageToTransmit.toString().getBytes());
		
		byte[] byteBuffer=new byte[200];
		in.read(byteBuffer);
		//System.out.println("Receiver from server: "+ new String(byteBuffer));
		
		response.setResponse("Receiver from server: "+ new String(byteBuffer));
		//Close the socket and its streams
	
		socket.close();
		
	
	

		} catch (Exception e) {
			
			logger.error("Unexpected error:" , e);
			response.setError(e);

		}
		
		/**
		 * sending the response...
		 */
		return response.toString();
		}
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/deleteOrder" , headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String deleteOrder(@RequestBody String deleteOrder) throws UnknownHostException, IOException{
		//JSONObject requestOBJ = new JSONObject(providerBlocking);
		OutputResponse response = new OutputResponse();

		try {
			
			CreateOrderData benificiaryDetails = InputMapper.gson().fromJson(deleteOrder,
					CreateOrderData.class);
	
	//public static void main(String[] args)  {
			
			String firstName=benificiaryDetails.getFirstName();
			String middleName=benificiaryDetails.getMiddleName();
			String lastName=benificiaryDetails.getLastName();
			String patientID=benificiaryDetails.getPatientID();
			String dob=benificiaryDetails.getDob();
			String gender=benificiaryDetails.getGender();
			String acc=benificiaryDetails.getAcc();
		
		Socket socket=new Socket("192.168.1.101",1235);
		System.out.println("Connected to Server");
		
		StringBuffer testHL7MassageToTransmit= new  StringBuffer();
		testHL7MassageToTransmit.append(START_OF_BLOCK)
		.append("MSH|^~"+"\"&|ImageSuite||||20120730000000.996||ORM^O01|MSG02010101_1|P|2.5|||AL||USA|ASCII||||")
		.append(CARRIAGE_RETURN)
		.append("PID|||"+patientID+"||"+firstName+"^"+lastName+"^||"+dob+"|"+gender+"|||||||||||||||||||||||||||Species Code|Breed Code")
		.append(CARRIAGE_RETURN)
		.append("ORC|CA||"+ acc +"||||20110621|||||Referring Physician Felix||||||||||||||||||")
		.append(CARRIAGE_RETURN)
		.append("OBR|0001|||CR_CHEST_PA|||20110622000000.996|||||||||Referring Physician Felix|||||||||||20110624|||||||||20110624||||||||CR_CHEST_PA|||||")
		.append(END_OF_BLOCK)
		.append(CARRIAGE_RETURN);
		
		InputStream in=socket.getInputStream();
		OutputStream out=socket.getOutputStream();
		//Send the MLLP -wrapped HL7 message to the server
		out.write(testHL7MassageToTransmit.toString().getBytes());
		
		byte[] byteBuffer=new byte[200];
		in.read(byteBuffer);
		//System.out.println("Receiver from server: "+ new String(byteBuffer));
		
		response.setResponse("Receiver from server: "+ new String(byteBuffer));
		//Close the socket and its streams
	
		socket.close();
		
	
	

		} catch (Exception e) {
			
			logger.error("Unexpected error:" , e);
			response.setError(e);

		}
		
		/**
		 * sending the response...
		 */
		return response.toString();
		}
	
}
	
	    
	