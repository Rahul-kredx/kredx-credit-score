package com.credit.experian.entities;

import org.springframework.stereotype.Component;

@Component
public class ConsumedData {
	
	private String errorString;
	private String stageOneId_;
	private String stageTwoId_;
	private String showHtmlReportForCreditReport;
	private int  newUserId;
	private String subscriptionMsg;
	public String getErrorString() {
		return errorString;
	}
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}
	public String getStageOneId_() {
		return stageOneId_;
	}
	public void setStageOneId_(String stageOneId_) {
		this.stageOneId_ = stageOneId_;
	}
	public String getStageTwoId_() {
		return stageTwoId_;
	}
	public void setStageTwoId_(String stageTwoId_) {
		this.stageTwoId_ = stageTwoId_;
	}
	public String getShowHtmlReportForCreditReport() {
		return showHtmlReportForCreditReport;
	}
	public void setShowHtmlReportForCreditReport(String showHtmlReportForCreditReport) {
		this.showHtmlReportForCreditReport = showHtmlReportForCreditReport;
	}
	public int getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(int newUserId) {
		this.newUserId = newUserId;
	}
	public String getSubscriptionMsg() {
		return subscriptionMsg;
	}
	public void setSubscriptionMsg(String subscriptionMsg) {
		this.subscriptionMsg = subscriptionMsg;
	}

	
}
