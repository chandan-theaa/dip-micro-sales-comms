package com.theaa.dip.sales.comms.repository.vo;


public class DriverVO {
	 private String title;
	 private String firstName;
	 private String lastName;
	 private String dob;
	 private String maritalStatus;
	 private String membershipNumber;
	 private String gender;
	 private boolean member;
	 private boolean proposer;
	 private boolean mainDriver;
	 private boolean businessUse;
	 private boolean useOfOtherVehicles;
	 private ContactInformationVO contactInformation;
	
	public ContactInformationVO getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(ContactInformationVO contactInformation) {
		this.contactInformation = contactInformation;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getMembershipNumber() {
		return membershipNumber;
	}
	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isMember() {
		return member;
	}
	public void setMember(boolean member) {
		this.member = member;
	}
	public boolean isProposer() {
		return proposer;
	}
	public void setProposer(boolean proposer) {
		this.proposer = proposer;
	}
	public boolean isMainDriver() {
		return mainDriver;
	}
	public void setMainDriver(boolean mainDriver) {
		this.mainDriver = mainDriver;
	}
	public boolean isBusinessUse() {
		return businessUse;
	}
	public void setBusinessUse(boolean businessUse) {
		this.businessUse = businessUse;
	}
	public boolean isUseOfOtherVehicles() {
		return useOfOtherVehicles;
	}
	public void setUseOfOtherVehicles(boolean useOfOtherVehicles) {
		this.useOfOtherVehicles = useOfOtherVehicles;
	}

	 
 
}