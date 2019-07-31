package com.theaa.dip.sales.comms.dto;

import java.math.BigDecimal;

public class EmailRequestDTO {

	private String quoteReference;
	    
	private String proposerTitle;
	    
	private String proposerSurname;
	    
	private String proposerEmail;
	    
	private String vehicleRegistration;
	    
	private String startDate;
	
	private BigDecimal annualPrice;
	    
	private BigDecimal voluntaryExcess;
	
	
	public EmailRequestDTO(String quoteReference, BigDecimal annualPrice, String proposerTitle, String proposerSurname,
			String proposerEmail, String vehicleRegistration, String startDate, BigDecimal voluntaryExcess) {
		super();
		this.quoteReference = quoteReference;
		this.annualPrice = annualPrice;
		this.proposerTitle = proposerTitle;
		this.proposerSurname = proposerSurname;
		this.proposerEmail = proposerEmail;
		this.vehicleRegistration = vehicleRegistration;
		this.startDate = startDate;
		this.voluntaryExcess = voluntaryExcess;
	}
	

	public String getQuoteReference() {
		return quoteReference;
	}

	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
	}

	public BigDecimal getAnnualPrice() {
		return annualPrice;
	}

	public void setAnnualPrice(BigDecimal annualPrice) {
		this.annualPrice = annualPrice;
	}

	public String getProposerTitle() {
		return proposerTitle;
	}

	public void setProposerTitle(String proposerTitle) {
		this.proposerTitle = proposerTitle;
	}

	public String getProposerSurname() {
		return proposerSurname;
	}

	public void setProposerSurname(String proposerSurname) {
		this.proposerSurname = proposerSurname;
	}

	public String getProposerEmail() {
		return proposerEmail;
	}

	public void setProposerEmail(String proposerEmail) {
		this.proposerEmail = proposerEmail;
	}

	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getVoluntaryExcess() {
		return voluntaryExcess;
	}

	public void setVoluntaryExcess(BigDecimal voluntaryExcess) {
		this.voluntaryExcess = voluntaryExcess;
	}


	@Override
	public String toString() {
		return "EmailRequestDto [quoteReference=" + quoteReference + ", annualPrice=" + annualPrice + ", proposerTitle="
				+ proposerTitle + ", proposerSurname=" + proposerSurname + ", proposerEmail=" + proposerEmail
				+ ", vehicleRegistration=" + vehicleRegistration + ", startDate=" + startDate + ", voluntaryExcess="
				+ voluntaryExcess + "]";
	}
	
}
