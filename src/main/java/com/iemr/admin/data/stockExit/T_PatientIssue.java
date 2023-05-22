package com.iemr.admin.data.stockExit;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.admin.data.stockentry.ItemStockEntry;
import com.iemr.admin.data.stockentry.PhysicalStockEntry;
import com.iemr.admin.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name="t_patientissue")
@Data
public class T_PatientIssue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	@Column(name="PatientIssueID")
	private Integer patientIssueID;
	
	@Expose
	@Column(name="BenRegID")
	private Integer benRegID;
	
	@Expose
	@Transient
	private Integer facilityID;
	
	@Expose
	@Column(name="VanID")
	private Integer vanID;
	
	@Expose
	@Column(name="ParkingPlaceID")
	private Integer parkingPlaceID;
	
	@Expose
	@Column(name="VisitID")
	private Integer visitID;
	
	@Expose
	@Column(name="PatientName")
	private String patientName;
	
	@Expose
	@Column(name="Age")
	private Integer age;
	
	@Expose
	@Column(name="Gender")
	private String gender;
		
	@Expose
	@Column(name="DoctorName")
	private String doctorName;
	
	@Expose
	@Column(name="PrescriptionID")
	private Integer prescriptionID;
	
	@Expose
	@Column(name="Reference")
	private String reference;
	
	@Expose
	@Column(name="IssueType")
	private String issueType;
	
	@Transient
	@Expose
	private List<ItemStockExit>  itemStockExit;

	
	@Expose
	@Column(name="Deleted", insertable = false, updatable = true)
	private Boolean deleted;
	
	@Expose
	@Column(name="Processed", insertable = false, updatable = true)
	private Character processed;
	
	@Expose
	@Column(name="CreatedBy")
	private String createdBy;
	
	@Expose
	@Column(name="CreatedDate", insertable = false, updatable = false)
	private Date createdDate;
	
	@Expose
	@Column(name="ModifiedBy")
	private String modifiedBy;
	
	@Expose
	@Column(name="LastModDate", insertable = false, updatable = false)
	private Date lastModDate;
	
	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}
}
