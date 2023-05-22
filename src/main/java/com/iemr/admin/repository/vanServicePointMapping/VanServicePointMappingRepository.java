package com.iemr.admin.repository.vanServicePointMapping;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.iemr.admin.data.provideronboard.M_104drugmaster;
import com.iemr.admin.data.vanServicePointMapping.M_VanServicePointMap;

@Repository
@RestResource(exported = false)
public interface VanServicePointMappingRepository extends CrudRepository<M_VanServicePointMap, Integer> {

	// @Query(value = "SELECT vs.vanServicePointMapID, vs.vanID, vs.vanSession,
	// s.servicePointID, s.servicePointName,"
	// + " vs.providerServiceMapID, vs.deleted "
	// + "(Select * from m_VanServicePointMap vs1 where vs1.vanID IS NULL or
	// vs1.vanID=:vanID) vs"
	//// + " FROM M_VanServicePointMap vs "
	// + " RIGHT JOIN m_servicepoint s on vs.ServicePointID = s.ServicePointID "
	// + " where AND s.districtID =:districtID AND s.parkingPlaceID
	// =:parkingPlaceID AND s.providerServiceMapID =:providerServiceMapID"
	// + " GROUP BY s.servicePointName" ,nativeQuery = true )

	
//	@Query(value = "select vs1.vanServicePointMapID, vs1.vanID,vs1.vanSession, s.servicePointID, s.servicePointName, "
//			 + " vs1.providerServiceMapID, vs1.deleted"
//			 + " from (SELECT * FROM M_VanServicePointMap vs where vs.vanID IS NULL or vs.vanID= :vanID )vs1 "
//			 + " RIGHT JOIN m_servicepoint s on vs1.servicepointid = s.servicepointid"
//			 + " where s.districtID = :districtID AND s.parkingPlaceID = :parkingPlaceID "
//			 + " AND s.providerServiceMapID = :providerServiceMapID"
//			 + " GROUP BY s.servicePointName" ,nativeQuery = true )
//			 List<Objects[]>
//			 getAvailableVanServicePointMappings(@Param("districtID")Integer
//			 districtID,
//			 @Param("parkingPlaceID")Integer parkingPlaceID, @Param("vanID")Integer
//			 vanID, @Param("providerServiceMapID")Integer providerServiceMapID);
			 
	 @Query(value = "select vs1.vanServicePointMapID, vs1.vanID,vs1.vanSession, s.servicePointID, s.servicePointName, "
	 + " vs1.providerServiceMapID, vs1.deleted"
	 + " from (SELECT * FROM M_VanServicePointMap vs where vs.vanID IS NULL or vs.vanID= :vanID )vs1 "
	 + " RIGHT JOIN m_servicepoint s on vs1.servicepointid = s.servicepointid"
	 + " where s.parkingPlaceID = :parkingPlaceID "
	 + " AND s.providerServiceMapID = :providerServiceMapID"
	 + " GROUP BY s.servicePointName" ,nativeQuery = true )
	 List<Objects[]>
	 getAvailableVanServicePointMappings( @Param("parkingPlaceID")Integer parkingPlaceID, @Param("vanID")Integer
	 vanID, @Param("providerServiceMapID")Integer providerServiceMapID);

	 @Query(value = "select vs1.vanServicePointMapID, vs1.vanID,vs1.vanSession, s.servicePointID, s.servicePointName, "
			 + " vs1.providerServiceMapID, vs1.deleted,d.districtID,d.districtName ,db.blockID,db.blockName"
			 + " from (SELECT * FROM M_VanServicePointMap vs where vs.vanID IS NULL or vs.vanID= :vanID )vs1 "
			 + " RIGHT JOIN m_servicepoint s on vs1.servicepointid = s.servicepointid"
			 + " left join m_district d on d.districtID=s.districtID"
			 + " left join m_districtblock db on db.blockID=s.districtBlockID"
			 + " where s.parkingPlaceID = :parkingPlaceID "
			 + " and s.deleted = false "
			 + " AND s.providerServiceMapID = :providerServiceMapID"
			 + " GROUP BY s.servicePointID" ,nativeQuery = true )
			 List<Objects[]>
			 getAvailableVanServicePointMappingsV1( @Param("parkingPlaceID")Integer parkingPlaceID, @Param("vanID")Integer
			 vanID, @Param("providerServiceMapID")Integer providerServiceMapID);
	/*
	 * @Query(value =
	 * "SELECT vmap.vanServicePointMapID, van.vanID, vmap.vanSession, ser.servicePointID , ser.servicePointName, vmap.providerServiceMapID, vmap.deleted"
	 * + " FROM M_VanServicePointMap vmap " +
	 * " RIGHT JOIN m_servicepoint ser on ser.servicePointID = vmap.servicePointID"
	 * + " LEFT join m_van van on vmap.vanID  =van.vanID " +
	 * " AND van.vanID =:vanID" +
	 * " where (ser.stateID IS NULL or cast(ser.stateID as char) like :stateID) "
	 * +
	 * " AND (ser.districtID IS NULL or cast(ser.districtID as char) like :districtID) "
	 * +
	 * " AND (ser.parkingPlaceID IS NULL or cast(ser.parkingPlaceID as char) like :parkingPlaceID)"
	 * , nativeQuery =true) List<Objects[]>
	 * getAvailableVanServicePointMappings(@Param("stateID")String
	 * stateID, @Param("districtID")String districtID,
	 * 
	 * @Param("parkingPlaceID")String parkingPlaceID, @Param("vanID")Integer
	 * vanID);
	 */

	@Transactional
	@Modifying
	@Query("update M_VanServicePointMap v set v.deleted=:deleted, v.modifiedBy=:modifiedBy where v.vanServicePointMapID =:vanServicePointMapID")
	int updateVanServicePointMappingStatus(@Param("vanServicePointMapID") Integer vanServicePointMapID,
			@Param("deleted") Boolean deleted, @Param("modifiedBy") String modifiedBy);

	@Query("SELECT v FROM M_VanServicePointMap v where v.vanServicePointMapID =:vanServicePointMapID")
	M_VanServicePointMap getVanServicePointMapping(@Param("vanServicePointMapID") Integer vanServicePointMapID);

}
