package com.theaa.dip.sales.comms;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("breakdown")
public class CommConfigurationProperties {
    
    private String endpoint, soapAction;
    
    public String getEndpoint() {
        
        return endpoint;
    }
    
    public void setEndpoint(String endpoint) {
        
        this.endpoint = endpoint;
    }
    
    public String getSoapAction() {
        
        return soapAction;
    }
    
    public void setSoapAction(String soapAction) {
        
        this.soapAction = soapAction;
    }
    
}
