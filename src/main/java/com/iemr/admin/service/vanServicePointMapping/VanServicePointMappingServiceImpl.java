package com.iemr.admin.service.vanServicePointMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.admin.data.vanMaster.M_Van;
import com.iemr.admin.data.vanServicePointMapping.M_VanServicePointMap;
import com.iemr.admin.repository.vanServicePointMapping.VanServicePointMappingRepository;

@Service
public class VanServicePointMappingServiceImpl implements VanServicePointMappingService {

	@Autowired
	private VanServicePointMappingRepository vanServicePointMappingRepository;

	// @Override
	// public ArrayList<M_VanServicePointMap>
	// getAvailableVanServicePointMappings(Integer districtID,
	// Integer parkingPlaceID, Integer vanID, Integer providerServiceMapID) {
	//
	//
	//
	// ArrayList<M_VanServicePointMap> vansList = new
	// ArrayList<M_VanServicePointMap>();
	//
	// List<Objects[]>
	// allData=vanServicePointMappingRepository.getAvailableVanServicePointMappings(districtID,
	// parkingPlaceID,vanID,providerServiceMapID);
	//
	// for (Object[] objects : allData) {
	//
	// vansList.add(new M_VanServicePointMap((Integer)objects[0],
	// (Integer)objects[1], (Short)objects[2] , (Integer)objects[3],
	// (String)objects[4], (Integer)objects[5], (Boolean)objects[6]));
	//
	// }
	// return vansList;
	// }
	@Override
	public ArrayList<M_VanServicePointMap> getAvailableVanServicePointMappings(Integer parkingPlaceID, Integer vanID,
			Integer providerServiceMapID) {

		ArrayList<M_VanServicePointMap> vansList = new ArrayList<M_VanServicePointMap>();

		List<Objects[]> allData = vanServicePointMappingRepository.getAvailableVanServicePointMappings(parkingPlaceID,
				vanID, providerServiceMapID);

		for (Object[] objects : allData) {

			vansList.add(new M_VanServicePointMap((Integer) objects[0], (Integer) objects[1], (Short) objects[2],
					(Integer) objects[3], (String) objects[4], (Integer) objects[5], (Boolean) objects[6]));

		}
		return vansList;
	}

	@Override
	public ArrayList<M_VanServicePointMap> saveVanServicePointMappings(
			List<M_VanServicePointMap> vanServicePointMappings) {

		ArrayList<M_VanServicePointMap> allData = (ArrayList<M_VanServicePointMap>) vanServicePointMappingRepository
				.save(vanServicePointMappings);

		return allData;
	}

	@Override
	public int updateVanServicePointMappingStatus(M_VanServicePointMap vanMaster) {
		int response = vanServicePointMappingRepository.updateVanServicePointMappingStatus(
				vanMaster.getVanServicePointMapID(), vanMaster.getDeleted(), vanMaster.getModifiedBy());
		return response;
	}

	@Override
	public M_VanServicePointMap getVanServicePointMappingByID(Integer vanServicePointMapID) {
		M_VanServicePointMap allData = vanServicePointMappingRepository.getVanServicePointMapping(vanServicePointMapID);

		return allData;
	}

	public ArrayList<M_VanServicePointMap> getAvailableVanServicePointMappingsV1(Integer parkingPlaceID, Integer vanID,
			Integer providerServiceMapID) {
		// TODO Auto-generated method stub

		ArrayList<M_VanServicePointMap> vansList = new ArrayList<M_VanServicePointMap>();

		List<Objects[]> allData = vanServicePointMappingRepository.getAvailableVanServicePointMappingsV1(parkingPlaceID,
				vanID, providerServiceMapID);

		for (Object[] objects : allData) {

			vansList.add(new M_VanServicePointMap((Integer) objects[0], (Integer) objects[1], (Short) objects[2],
					(Integer) objects[3], (String) objects[4], (Integer) objects[5], (Boolean) objects[6],
					(Integer) objects[7], (String) objects[8], (Integer) objects[9], (String) objects[10]));

		}

		return vansList;
	}

}
