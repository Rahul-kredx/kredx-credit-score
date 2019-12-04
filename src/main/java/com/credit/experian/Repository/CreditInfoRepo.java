package com.credit.experian.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.experian.EnumConstants.ApplicationId;
import com.credit.experian.entities.CreditScoreRequest;
import com.credit.experian.model.CreditScoreInfo;

public interface CreditInfoRepo extends JpaRepository<CreditScoreInfo, Long>{
	
	CreditScoreInfo findByUid1(String uid);

//	CreditScoreInfo findByUid1AndRequest_ApplicationId(String requestUid, ApplicationId applicationId);
	

}
