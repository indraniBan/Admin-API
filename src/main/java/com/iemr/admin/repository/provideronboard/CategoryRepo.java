package com.iemr.admin.repository.provideronboard;

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

import com.iemr.admin.data.provideronboard.M_Category;

@Repository
@RestResource(exported = false)
public interface CategoryRepo extends CrudRepository<M_Category, Integer> {
	@Query("SELECT u FROM M_Category u where u.subServiceID=:subServiceID AND u.providerServiceMapID=:providerServiceMapID")
	// ArrayList<M_Category> getAllCategory();

	ArrayList<M_Category> getAllCategory(@Param("subServiceID") Integer subServiceID,
			@Param("providerServiceMapID") Integer providerServiceMapID);

	@Query("SELECT u FROM M_Category u where u.categoryID=:categoryID")
	M_Category getCatData(@Param("categoryID") Integer categoryID);

	/*
	 * @Transactional
	 * 
	 * @Modifying
	 * 
	 * @Query(" UPDATE  M_UserServiceRoleMapping2 u SET u.agentID=:agentID ,u.agentPassword=:agentPassword where u.uSRMappingID = :getuSRMappingID"
	 * ) //void createcitmapping(@Param("providerServiceMapID")Integer
	 * providerServiceMapID, void
	 * createcitmapping(@Param("getuSRMappingID")Integer
	 * getuSRMappingID, @Param("agentID")String agentID,@Param("agentPassword")
	 * String agentPassword);
	 */

	@Transactional
	@Modifying
	@Query(" UPDATE M_Category u SET u.feedbackNatureID=:feedbackid where u.categoryID=:catid")
	int updateCategory(@Param("catid") Integer catid, @Param("feedbackid") Integer feedbackid);

	@Query("SELECT m.categoryID, m.categoryName," + " m.feedbackNatureID," + " f.feedbackNature as feedbackNature, "
			+ " m.providerServiceMapID as providerServiceMapID" + " FROM M_Category m "
			+ " Inner JOIN m.m_Feedbacknature f" + " where m.providerServiceMapID=:providerServiceMapID")
	List<Object[]> getAllCategory(@Param("providerServiceMapID") Integer providerServiceMapID);

	@Query("SELECT m.categoryID, m.categoryName," + " m.feedbackNatureID," + " f.feedbackNature as feedbackNature, "
			+ " m.providerServiceMapID as providerServiceMapID" + " FROM M_Category m "
			+ " Inner JOIN m.m_Feedbacknature f"
			+ " where m.providerServiceMapID=:providerServiceMapID  and m.feedbackNatureID=:feedbackNatureID")
	List<Object[]> getAllCategorywithfeedbackNatureID(
			@Param("providerServiceMapID") Integer providerServiceMapID,
			@Param("feedbackNatureID") Integer feedbackNatureID);

	@Query("SELECT u FROM M_Category u where u.providerServiceMapID=:providerServiceMapID")
	ArrayList<M_Category> getAllCategory1(@Param("providerServiceMapID") Integer providerServiceMapID);

	ArrayList<M_Category> findByProviderServiceMapIDOrderByCategoryNameAsc(Integer providerServiceMapID);

	ArrayList<M_Category> findByProviderServiceMapIDAndFeedbackNatureIDOrderByCategoryNameAsc(
			Integer providerServiceMapID, Integer object);
}
