package com.theaa.dip.sales.comms.sqs.dto;

import com.theaa.dip.sales.comms.domain.JourneyType;

public class PaymentMessageDTO {
		    
	private String quoteReference;
	    
	private JourneyType paymentJourney;
    
    public String getQuoteReference() {
		return quoteReference;
	}
	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
	}
	public JourneyType getPaymentJourney() {
		return paymentJourney;
	}
	public void setPaymentJourney(JourneyType paymentJourney) {
		this.paymentJourney = paymentJourney;
	}
	
}
