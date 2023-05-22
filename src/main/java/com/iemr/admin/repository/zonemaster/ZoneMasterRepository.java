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

import com.iemr.admin.data.provideronboard.M_104druggroup;
import com.iemr.admin.data.zonemaster.M_Zone;

@Repository
@RestResource(exported = false)
public interface ZoneMasterRepository extends CrudRepository<M_Zone, Integer>{
	
/*	@Query("SELECT m FROM M_Zone m where deleted=false")
	ArrayList<M_Zone> getAvailableZones();*/
	

	
	@Query("SELECT m.zoneID, m.zoneName, m.zoneDesc, m.zoneHQAddress, m.providerServiceMapID, m.deleted, m.countryID, c.countryName, m.stateID, s.stateName, "
			+ " d.districtID, d.districtName, b.blockID, b.blockName, bm.districtBranchID, bm.villageName,m.m_providerServiceMapping, sm.serviceID, sm.serviceName "
			+ " FROM M_Zone m "
			+ " LEFT JOIN m.m_country c"
			+ " LEFT JOIN m.state s"
			+ " LEFT JOIN m.m_district d"
			+ " LEFT JOIN m.districtBlock b"
			+ " LEFT JOIN m.districtBranchMapping bm"
			+ " LEFT JOIN m.m_providerServiceMapping p"
			+ " LEFT JOIN p.m_serviceMaster sm "
			+ " where p.providerServiceMapID =:providerServiceMapID ORDER By m.zoneName ")
	List<Objects[]> getAvailableZones(@Param("providerServiceMapID")Integer providerServiceMapID);

	@Transactional
	@Modifying
	@Query("update M_Zone m set m.deleted=:deleted, m.modifiedBy=:modifiedBy where m.zoneID =:zoneID")
	int updateZoneStatus(@Param("zoneID")Integer zoneID, @Param("deleted")Boolean deleted, @Param("modifiedBy")String modifiedBy);
	
	@Query("SELECT m FROM M_Zone m where m.zoneID =:zoneID")
	M_Zone getZoneById(@Param("zoneID")Integer zoneID);
	
}
