package com.iemr.admin.repository.zonemaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.admin.data.zonemaster.M_ZoneDistrictMap;

@Repository
@RestResource(exported = false)
public interface ZoneDistrictMappingRepo extends CrudRepository<M_ZoneDistrictMap, Integer>{

	@Query("SELECT m.zoneDistrictMapID, m.zoneID, z.zoneName, m.districtID, m.providerServiceMapID, m.deleted, p.stateID, s.stateName, d.districtName,"
			+ " sm.serviceID, sm.serviceName, "
			+ " m.deleted as zoneDeleted "
			+ " FROM M_ZoneDistrictMap m "
			+ " LEFT JOIN m.m_zone z "
			+ " LEFT JOIN m.m_providerServiceMapping p "
			+ " LEFT JOIN p.state s "
			+ " LEFT JOIN p.m_serviceMaster sm "
			+ " LEFT JOIN m.m_district d "
			+ " where (m.zoneID IS NULL or cast(m.zoneID as string) like %:zoneID%)")
	List<Objects[]> getAvailableZoneDistrictMappingss(@Param("zoneID")String zoneID);
	
	@Query("SELECT m.zoneDistrictMapID, m.zoneID, z.zoneName, m.districtID, m.providerServiceMapID, m.deleted, p.stateID, s.stateName, d.districtName,"
			+ " sm.serviceID, sm.serviceName,"
			+ " z.deleted as zoneDeleted "
			+ " FROM M_ZoneDistrictMap m "
			+ " LEFT JOIN m.m_zone z "
			+ " LEFT JOIN m.m_providerServiceMapping p "
			+ " LEFT JOIN p.state s "
			+ " LEFT JOIN p.m_serviceMaster sm "
			+ " LEFT JOIN m.m_district d "
			+ " where p.providerServiceMapID =:providerServiceMapID  ORDER By z.zoneName ")
	List<Objects[]> getAvailableZoneDistrictMappingss(@Param("providerServiceMapID")Integer providerServiceMapID);
	
	
	@Transactional
	@Modifying
	@Query("update M_ZoneDistrictMap m set m.deleted=:deleted, m.modifiedBy=:modifiedBy where m.zoneDistrictMapID =:zoneDistrictMapID")
	int updateZoneDistrictMappingStatus(@Param("zoneDistrictMapID")Integer zoneDistrictMapID, @Param("deleted")Boolean deleted, @Param("modifiedBy")String modifiedBy);
	
	
	@Query("SELECT  d.districtName,d.districtID "
			+ " FROM M_ZoneDistrictMap m "
			+ " LEFT JOIN m.m_district d "
			+ " where m.zoneID=:zoneID ORDER By d.districtName")
	List<Objects[]> editZoneDistrictMapping1(@Param("zoneID") Integer zoneID);

	
	@Query("Select u from M_ZoneDistrictMap u where u.zoneID=:zoneID AND u.deleted=0")
	List getRecord(@Param("zoneID") Integer zoneID);
	
}
