package com.theaa.dip.sales.comms.sqs.dto;

import java.time.Instant;

public class QuoteMessageDTO {
		 private Instant lastUpdated;
		 private String quoteReference;
		 private QuoteContentDTO quoteContent;
		
		public String getQuoteReference() {
			return quoteReference;
		}

		public void setQuoteReference(String quoteReference) {
			this.quoteReference = quoteReference;
		}

		public QuoteContentDTO getQuoteContent() {
			return quoteContent;
		}

		public void setQuoteContent(QuoteContentDTO quoteContent) {
			this.quoteContent = quoteContent;
		}

		public Instant getLastUpdated() {
			return lastUpdated;
		}

		public void setLastUpdated(Instant lastUpdated) {
			this.lastUpdated = lastUpdated;
		}
		
		 
}
