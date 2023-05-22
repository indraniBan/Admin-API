package com.iemr.admin.controller.fetosensemaster;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.iemr.admin.service.fetosensemaster.FetosenseService;
import com.iemr.admin.utils.response.OutputResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping(value = "fetosense", headers = "Authorization")
public class FetosenseController {
	@Autowired
	private FetosenseService fetosenseService;

	@CrossOrigin
	@ApiOperation(value = "Create Fetosense Tests master by Provider admin", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = { "/createFetosenseTestMaster" }, method = { RequestMethod.POST })
	public String createFetosenseTestMaster(@RequestBody String requestOBJ) {
		OutputResponse response = new OutputResponse();
		try {
			String s = fetosenseService.createFetosenseTestMaster(requestOBJ);	
			if (s != null) {
				response.setResponse(s);
			} else {
			}
		} catch (Exception e) {
			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "Fetch Fetosense Tests master for provider-service-map-id", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = { "/fetchFetosenseTestMaster/{psmID}" }, method = { RequestMethod.GET })
	public String fetchFetosenseTestMaster(@PathVariable("psmID") Integer psmID) {
		OutputResponse response = new OutputResponse();
		try {
			if (psmID != null & psmID > 0) {
				String s = fetosenseService.getFetosenseTestMaster(psmID);
				response.setResponse(s);
			} else {
				response.setError(5002, "Invalid Request ");
			}
		} catch (Exception e) {
			response.setError(e);
		}
		return response.toString();
	}

	@CrossOrigin
	@ApiOperation(value = "update Fetosense Tests master for a particular procedure", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = { "/updateFetosenseTestMaster" }, method = { RequestMethod.POST })
	public String updateProcedureMaster(@ApiParam(value = "{}") @RequestBody String requestOBJ) {
		OutputResponse response = new OutputResponse();
		try {
			String s = fetosenseService.updateFetosenseTestMaster(requestOBJ);
			if (s != null) {
				response.setResponse(s);
			} else {
				response.setError(5002, "Failed to update procedure details");
			}
		} catch (Exception e) {
			response.setError(e);
		}
		return response.toString();
	}
	
	@CrossOrigin
	@ApiOperation(value = "update procedure status for enable or disable", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = { "/updateFetosenseTestMasterStatus" }, method = { RequestMethod.POST })
	public String updateProcedureStatus(@ApiParam(value = "{}") @RequestBody String requestOBJ) {
		OutputResponse response = new OutputResponse();
		try {
			JSONObject jsnOBJ = new JSONObject(requestOBJ);
			if (jsnOBJ != null && jsnOBJ.has("fetosenseTestID") && jsnOBJ.getInt("fetosenseTestID") > 0
					&& jsnOBJ.has("deleted")) {
				String s = fetosenseService.updateFetosenseTestMasterStatus(jsnOBJ.getInt("fetosenseTestID"),
						jsnOBJ.getBoolean("deleted"));
				if (s != null)
					response.setResponse(s);
				else
					response.setError(5002, "Failed to update the status");
			} else {
				response.setError(5002, "invalid request");
			}

		} catch (Exception e) {
			response.setError(e);
		}
		return response.toString();
	}
	
}
