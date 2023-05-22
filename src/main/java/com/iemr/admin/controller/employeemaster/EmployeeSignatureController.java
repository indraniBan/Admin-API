package com.iemr.admin.controller.employeemaster;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.admin.data.employeemaster.EmployeeSignature;
import com.iemr.admin.service.employeemaster.EmployeeSignatureServiceImpl;
import com.iemr.admin.utils.mapper.InputMapper;
import com.iemr.admin.utils.response.OutputResponse;

@PropertySource("classpath:application.properties")

@RestController
@RequestMapping(value = "/signature1")
public class EmployeeSignatureController {

	@Autowired
	EmployeeSignatureServiceImpl employeeSignatureServiceImpl;

	private InputMapper inputMapper = new InputMapper();

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@CrossOrigin()
	@RequestMapping(value = "/upload", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String uploadFile(@RequestBody EmployeeSignature emp) {
		OutputResponse response = new OutputResponse();
		logger.debug("upload signature for userID started" + emp);

		try {

			emp.setSignature(Base64.getDecoder().decode(emp.getFileContent()));
			Long userSignID = employeeSignatureServiceImpl.uploadSignature(emp);
//
			response.setResponse(userSignID.toString());

		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Unexpected error:" , e);
			logger.error("Signature Upload Failed" + e.getMessage(), e);
			response.setError(e);

		}

		/**
		 * sending the response...
		 */
		logger.debug("response" + response);
		return response.toString();
	}

	@CrossOrigin(origins = "*", exposedHeaders = {HttpHeaders.CONTENT_DISPOSITION,"filename"})
	@RequestMapping(value = "/{userID}", headers = "Authorization", method = { RequestMethod.GET })
	public ResponseEntity<byte[]> fetchFile(@PathVariable("userID") Long userID) throws Exception {
		OutputResponse response = new OutputResponse();
		logger.debug("File download for userID" + userID);

		try {

			EmployeeSignature userSignID = employeeSignatureServiceImpl.fetchSignature(userID);
//			logger.debug("response" + Arrays.toString(userSignID.getSignature()));
//
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
					"inline; filename=\"" + userSignID.getFileName() + "\"");
			responseHeaders.set("filename",userSignID.getFileName());
			
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(userSignID.getFileType()))
					.headers(responseHeaders)
					.body(userSignID.getSignature());

		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Unexpected error:" , e);
			logger.error("File download for userID failed with exception " + e.getMessage(), e);
			throw new Exception("Error while downloading file. Please contact administrator..");

		}

		/**
		 * sending the response...
		 */

	}
	@CrossOrigin()
	@RequestMapping(value = "/signexist/{userID}", headers = "Authorization", method = { RequestMethod.GET })
	public String existFile(@PathVariable("userID") Long userID) throws Exception {
		OutputResponse response = new OutputResponse();
		logger.debug("File download for userID" + userID);

		try {

			Boolean userSignID = employeeSignatureServiceImpl.existSignature(userID);
//			logger.debug("response" + Arrays.toString(userSignID.getSignature()));
//
			response.setResponse(userSignID.toString());

		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Unexpected error:" , e);
			logger.error("File download for userID failed with exception " + e.getMessage(), e);
			response.setError(e);
		}

		/**
		 * sending the response...
		 */
		logger.debug("response" + response);
		return response.toString();
	}
}
