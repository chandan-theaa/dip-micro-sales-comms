package com.theaa.dip.sales.comms.repository.vo;

import java.time.Instant;

public class QuoteMessageVO {
	
	private Instant lastUpdated;
	
	private String quoteReference;

	private QuoteContentVO quoteContent;

	public String getQuoteReference() {
		return quoteReference;
	}

	public void setQuoteReference(String quoteReference) {
		this.quoteReference = quoteReference;
	}

	public QuoteContentVO getQuoteContent() {
		return quoteContent;
	}

	public void setQuoteContent(QuoteContentVO quoteContent) {
		this.quoteContent = quoteContent;
	}

	public Instant getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Instant lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	
}
