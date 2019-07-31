package com.theaa.dip.sales.comms;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.theaa.dip.lib.instrument.messaging.sns.PlatformMessageClient;

import brave.Tracer;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class SalesCommConfiguration {
	
	    @Bean
	    public ObjectMapper objectMapper() {
	        
	        final ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.findAndRegisterModules();
	        objectMapper.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
	        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	        objectMapper.setSerializationInclusion(Include.NON_NULL);
	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        return objectMapper;
	    }
	    
	    @Bean
	    CorsWebFilter corsWebFilter(CORSConfigurationProperties configuration) {
	        
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.setAllowedOrigins(configuration.getCorsOrigins());
	        corsConfig.setMaxAge(8000L);
	        corsConfig.setAllowedMethods(configuration.getCorsMethods());
	        corsConfig.setAllowedHeaders(configuration.getCorsHeaders());
	        
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);
	        
	        return new CorsWebFilter(source);
	    }
	    
	    @Bean
	    public ErrorAttributes errorAttributes(ObjectMapper objectMapper, Tracer tracer) {
	        
	        return new DefaultErrorAttributes() {
	            
	            @Override
	            public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
	            	return null;
	            }
	  
	            
	        };
	    }
	    
	    @Bean
	    public DynamoDbAsyncClient dynamoDbAsyncClient() {
	        
	        return DynamoDbAsyncClient.create();
	    }
	    
	    @Bean
	    public NotificationMessagingTemplate notificationMessagingTemplate(AmazonSNS amazonSNS) {
	        
	        return new NotificationMessagingTemplate(amazonSNS);
	    }
	    
	    @Bean
	    public DynamoDbClient dynamoDbClient() {
	        
	        return DynamoDbClient.create();
	    }
	    
	    @Bean
	    public ConversionService conversionService() {
	        
	        return new DefaultConversionService();
	    }

	    @Bean
	    public AmazonSNS amazonSNS() {
	        
	        return AmazonSNSClient.builder().build();
	    }
	    
	    @Bean   
	    public PlatformMessageClient messageClient(AmazonSNS client, ObjectMapper objectMapper) {
	        
	        return new PlatformMessageClient(client, objectMapper);
	    }
	    
	    @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }

}