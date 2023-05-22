package com.iemr.admin.data.parkingPlace;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.admin.data.locationmaster.DistrictBlock;
import com.iemr.admin.data.locationmaster.M_District;
import com.iemr.admin.data.provideronboard.M_ProviderServiceMapping;
import com.iemr.admin.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "m_parkingplacesubdistrictmap")
@Data
public class ParkingplaceTalukMapping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	@Column(name = "PPSubDistrictMapID")
	private Integer ppSubDistrictMapID;

	@Expose
	@Column(name = "ParkingPlaceID")
	private Integer parkingPlaceID;

	@Expose
	@Column(name = "DistrictID")
	private Integer districtID;

	@Expose
	@Column(name = "DistrictBlockID")
	private Integer districtBlockID;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "ProviderServiceMapID")
	private M_ProviderServiceMapping m_providerServiceMapping;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "districtID")
	private M_District m_district;

	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "districtBlockID")
	private DistrictBlock districtBlock;
	
	@Expose
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(updatable = false, insertable = false, name = "parkingPlaceID")
	private M_Parkingplace parkingplace;

	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	@Expose
	@Column(name = "Processed", insertable = false, updatable = true)
	private String processed;
	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;
	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	@Expose
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
	

	
}
