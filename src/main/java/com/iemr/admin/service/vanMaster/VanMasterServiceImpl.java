package com.iemr.admin.service.vanMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.admin.data.parkingPlace.M_Parkingplace;
import com.iemr.admin.data.vanMaster.M_Van;
import com.iemr.admin.data.vanType.M_VanType;
import com.iemr.admin.repository.parkingPlace.ParkingPlaceRepository;
import com.iemr.admin.repository.vanMaster.VanMasterRepository;
import com.iemr.admin.repository.vanType.VanTypeRepository;

@Service
public class VanMasterServiceImpl implements VanMasterService{
	
	@Autowired
	private  VanMasterRepository vanMasterRepository;
	
	@Autowired
	private  VanTypeRepository vanTypeRepository;
	
	@Autowired
	private ParkingPlaceRepository parkingPlaceRepository;


	@Override
	public ArrayList<M_Van> saveVanDetails(List<M_Van> vanMaster) {
		ArrayList<M_Van>  allData=(ArrayList<M_Van>) vanMasterRepository.save(vanMaster);
		return allData;
	}

//	@Override
//	public ArrayList<M_Van> getAvailableVans(Integer districtID, Integer parkingPlaceID,Integer vanTypeID, Integer providerServiceMapID) {
//		
//		String districtId= "%%";
//		if(null!=districtID){
//			districtId = districtID+"";
//		}
//		
//		String parkingPlaceId= "%%";
//		if(null!=parkingPlaceID){
//			parkingPlaceId = parkingPlaceID+"";
//		}
//		
//		String vanTypeId= "%%";
//		if(null!=vanTypeID){
//			vanTypeId = vanTypeID+"";
//		}
//		
//		ArrayList<M_Van> vansList = new ArrayList<M_Van>();		
//		
//		List<Objects[]>  allData=vanMasterRepository.getAvailableVans(districtId, parkingPlaceId, vanTypeId, providerServiceMapID);
//		
//		for (Object[] objects : allData) {
//
//			vansList.add(new M_Van((Integer)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3], (String)objects[4], (Boolean)objects[5], 
//					(Integer)objects[6], (Integer)objects[7], (String)objects[8],(Integer)objects[9], (String)objects[10],(Integer)objects[11],
//						(String)objects[12],(Integer)objects[13], (String)objects[14],(Integer)objects[15]));
//			
//		}
//		return vansList;
//	}

	@Override
	public ArrayList<M_Van> getAvailableVans( Integer parkingPlaceID,Integer vanTypeID, Integer providerServiceMapID) {
		
				
		String parkingPlaceId= "%%";
		if(null!=parkingPlaceID){
			parkingPlaceId = parkingPlaceID+"";
		}
		
		String vanTypeId= "%%";
		if(null!=vanTypeID){
			vanTypeId = vanTypeID+"";
		}
		
		ArrayList<M_Van> vansList = new ArrayList<M_Van>();		
		
		List<Objects[]>  allData=vanMasterRepository.getAvailableVans( parkingPlaceId, vanTypeId, providerServiceMapID);
		
		for (Object[] objects : allData) {

			vansList.add(new M_Van((Integer)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3], (String)objects[4], (Boolean)objects[5], 
					(Integer)objects[6], (Integer)objects[7], (String)objects[8],(Integer)objects[9], (String)objects[10],(Integer)objects[11], 
					(String)objects[12],(Integer)objects[13],(String)objects[14],(String)objects[15],(String)objects[16],(boolean) objects[17]));
			
		}
		return vansList;
	}
	@Override
	public int updateVanStatus(M_Van vanMaster) {
		int response = vanMasterRepository.updateVanStatus(vanMaster.getVanID(), vanMaster.getDeleted(), vanMaster.getModifiedBy());
		return response;
	}

	@Override
	public ArrayList<M_VanType> getVanTypes() {
		
	
		List<Objects[]>  allData=vanTypeRepository.getVanTypes();
		
		ArrayList<M_VanType> vanTypes = new ArrayList<M_VanType>();		
		for (Object[] objects : allData) {

			vanTypes.add(new M_VanType((Integer)objects[0], (String)objects[1], (String)objects[2],(Boolean)objects[3]));
			
		}
		return vanTypes;
	}

	@Override
	public M_Van getVanByID(Integer vanID) {
		M_Van van = vanMasterRepository.getVanById(vanID);
		return van;
	}

	@Override
	public M_Van updateVanData(M_Van vanData) {
		M_Van van = vanMasterRepository.save(vanData);
		return van;
	}

	@Override
	public ArrayList<M_VanType> saveVanTypeDetails(List<M_VanType> vanTypeMaster) {
		ArrayList<M_VanType>  allData=(ArrayList<M_VanType>) vanTypeRepository.save(vanTypeMaster);
		return allData;
	}

	@Override
	public int updateVanTypeStatus(M_VanType vanType) {
		int response = vanTypeRepository.updateVanTypeStatus(vanType.getVanTypeID(), vanType.getDeleted(), vanType.getModifiedBy());
		return response;
	}

	public List<M_Van> getVanMaster(Integer providerServiceMapID, Integer pp) {
		 
		return vanMasterRepository.findByProviderServiceMapIDAndParkingPlaceID(providerServiceMapID,pp);
	}
	
	
	public List<M_Van> getVanFromFacilityID(Integer facilityID) throws Exception {
		M_Parkingplace pp= parkingPlaceRepository.findFirstByFacilityID(facilityID);
		if(pp==null || pp.getParkingPlaceID()==null){
			throw  new Exception("Main Store doesnt have any Parking place mapped");  
		}
		return vanMasterRepository.findByProviderServiceMapIDAndParkingPlaceID(pp.getProviderServiceMapID(),pp.getParkingPlaceID());
	}
//	@Override
//	public ArrayList<M_Van> getNonMappedAvailablevans( Integer parkingPlaceID,Integer vanTypeID, Integer providerServiceMapID) {
//		
//				
//		String parkingPlaceId= "%%";
//		if(null!=parkingPlaceID){
//			parkingPlaceId = parkingPlaceID+"";
//		}
//		
//		String vanTypeId= "%%";
//		if(null!=vanTypeID){
//			vanTypeId = vanTypeID+"";
//		}
//		
//		ArrayList<M_Van> vansList = new ArrayList<M_Van>();		
//		
//		List<Objects[]>  allData=vanMasterRepository.getNonMappedAvailableVans(parkingPlaceId, vanTypeId, providerServiceMapID);
//		
//		for (Object[] objects : allData) {
//
//			vansList.add(new M_Van((Integer)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3], (String)objects[4], (Boolean)objects[5], 
//					(Integer)objects[6], (Integer)objects[7], (String)objects[8],(Integer)objects[9], (String)objects[10],(Integer)objects[11],
//					(String)objects[12],(Integer)objects[13],(String)objects[14],(String)objects[15],(String)objects[16],(boolean)objects[17]));
//			
//		}
//		return vansList;
//	}
}
