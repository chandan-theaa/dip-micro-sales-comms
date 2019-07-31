package com.theaa.dip.sales.comms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import com.theaa.dip.lib.instrument.logging.EnableLogging;

@EnableLogging
@SpringCloudApplication
@EnableConfigurationProperties({
   CORSConfigurationProperties.class
})
public class SalesCommsApplication {
    
    public static void main(String[] args) {
    	SpringApplication.run(SalesCommsApplication.class);
    }
}
