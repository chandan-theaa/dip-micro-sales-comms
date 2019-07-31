package com.theaa.dip.sales.comms;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("service")
public class CORSConfigurationProperties {
	private List<String> corsOrigins;
	private List<String> corsHeaders;
	private List<String> corsMethods;
	
	public List<String> getCorsOrigins() {
		return corsOrigins;
	}

	public void setCorsOrigins(List<String> corsOrigins) {
		this.corsOrigins = corsOrigins;
	}

	public List<String> getCorsHeaders() {
		return corsHeaders;
	}

	public void setCorsHeaders(List<String> corsHeaders) {
		this.corsHeaders = corsHeaders;
	}

	public List<String> getCorsMethods() {
		return corsMethods;
	}

	public void setCorsMethods(List<String> corsMethods) {
		this.corsMethods = corsMethods;
	}

}
