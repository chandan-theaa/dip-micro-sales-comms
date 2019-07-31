package com.theaa.dip.sales.comms.sqs.dto;

public class MotorQuoteOptionsDTO {
	 private String coverStartDate;
	 private boolean electronicDocsOnly;
	 private String paymentPlan;
	 private boolean windscreenCover;
	 private String coverType;
	 private boolean protectNCD;
	 private boolean displayInstalments;
	 private boolean membershipBundleOfferAvailable;
	 private boolean buyMembership;
	 private String securityType;
	 
	 
	
		public String getCoverStartDate() {
			return coverStartDate;
		}
		public void setCoverStartDate(String coverStartDate) {
			this.coverStartDate = coverStartDate;
		}
		public boolean isElectronicDocsOnly() {
			return electronicDocsOnly;
		}
		public void setElectronicDocsOnly(boolean electronicDocsOnly) {
			this.electronicDocsOnly = electronicDocsOnly;
		}
		public String getPaymentPlan() {
			return paymentPlan;
		}
		public void setPaymentPlan(String paymentPlan) {
			this.paymentPlan = paymentPlan;
		}
		public boolean isWindscreenCover() {
			return windscreenCover;
		}
		public void setWindscreenCover(boolean windscreenCover) {
			this.windscreenCover = windscreenCover;
		}
		public String getCoverType() {
			return coverType;
		}
		public void setCoverType(String coverType) {
			this.coverType = coverType;
		}
		public boolean isProtectNCD() {
			return protectNCD;
		}
		public void setProtectNCD(boolean protectNCD) {
			this.protectNCD = protectNCD;
		}
		public boolean isDisplayInstalments() {
			return displayInstalments;
		}
		public void setDisplayInstalments(boolean displayInstalments) {
			this.displayInstalments = displayInstalments;
		}
		public boolean isMembershipBundleOfferAvailable() {
			return membershipBundleOfferAvailable;
		}
		public void setMembershipBundleOfferAvailable(boolean membershipBundleOfferAvailable) {
			this.membershipBundleOfferAvailable = membershipBundleOfferAvailable;
		}
		public boolean isBuyMembership() {
			return buyMembership;
		}
		public void setBuyMembership(boolean buyMembership) {
			this.buyMembership = buyMembership;
		}
		public String getSecurityType() {
			return securityType;
		}
		public void setSecurityType(String securityType) {
			this.securityType = securityType;
		}
		
		
	}