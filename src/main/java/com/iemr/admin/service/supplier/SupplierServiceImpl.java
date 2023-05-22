package com.iemr.admin.service.supplier;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.admin.data.supplier.M_Supplier;
import com.iemr.admin.repo.supplier.SupplierRepo;

@Service
public class SupplierServiceImpl implements SupplierInter{
	
//	@Autowired
//	private M_SupplieraddressRepo m_SupplieraddressRepo;
	
	@Autowired
	private SupplierRepo supplierRepo;

	@Override
	public ArrayList<M_Supplier> createSupplier(List<M_Supplier> supplierData) {
		ArrayList<M_Supplier>  savedData=(ArrayList<M_Supplier>) supplierRepo.save(supplierData);
		if(savedData.size()>0)
			return savedData;
		else
		return null;
	}

	@Override
	public ArrayList<M_Supplier> getSupplier(Integer providerServiceMapID) {
		
		ArrayList<M_Supplier> getData=supplierRepo.getSupplierData(providerServiceMapID);
//		if(getData.size()>0)
		return getData;
		
	}

	@Override
	public M_Supplier editSupplier(Integer supplierID) {
		M_Supplier editData=supplierRepo.geteditedData(supplierID);
		return editData;
	}

	@Override
	public M_Supplier saveEditedData(M_Supplier editData) {
		M_Supplier savedEditData=supplierRepo.save(editData);
		return savedEditData;
	}

	@Override
	public Boolean checkSupplierCode(M_Supplier supplier) {
		// TODO Auto-generated method stub
		List<M_Supplier> supplierlist=supplierRepo.findBySupplierCodeAndProviderServiceMapID(supplier.getSupplierCode(),supplier.getProviderServiceMapID());
		if(supplierlist.size()>0)
			return true;
		return false;
	}

//	@Override
//	public ArrayList<M_Supplieraddress> createAddress(List<M_Supplieraddress> resList1) {
//		ArrayList<M_Supplieraddress>  data=(ArrayList<M_Supplieraddress>) m_SupplieraddressRepo.save(resList1);
//		return data;
//	}

}
