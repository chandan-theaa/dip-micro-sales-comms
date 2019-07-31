package com.theaa.dip.sales.comms.sqs;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theaa.dip.lib.instrument.logging.EventLogger;
import com.theaa.dip.lib.instrument.logging.LogEvent;
import com.theaa.dip.lib.instrument.messaging.sns.PlatformMessageClient;
import com.theaa.dip.lib.instrument.messaging.sns.dto.PlatformMessage;
import com.theaa.dip.sales.comms.logging.EventType;
import com.theaa.dip.sales.comms.service.SalesCommsService;
import com.theaa.dip.sales.comms.sqs.dto.PaymentMessageDTO;
import com.theaa.dip.sales.comms.sqs.dto.QuoteMessageDTO;

import reactor.core.publisher.Mono;

@Component 
public class SalesCommsQueueListener {
	
	   private static final Logger LOGGER = LoggerFactory.getLogger(SalesCommsQueueListener.class);
	    
	   private final String clazz = this.getClass().getSimpleName();
	    
	   private static final String QUEUE_NAME = "SalesCommsQueue";
	
	   private final SalesCommsService salesCommsService;
	   
	   private final ObjectMapper objectMapper;
	   
	   private final PlatformMessageClient messageClient;
	    
	   String traceId = Instant.now().toString();
	    
	   @Autowired
	    public SalesCommsQueueListener(SalesCommsService salesCommsService,ObjectMapper objectMapper,PlatformMessageClient messageClient) {
	        this.salesCommsService = salesCommsService;
	        this.objectMapper = objectMapper;
	        this.messageClient = messageClient;
	        LOGGER.info("$$ Listener instantiated with QueueName: {}", QUEUE_NAME);
	    }
	  
	   
	    @SqsListener(value = QUEUE_NAME, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	    private void receiveMessage(String message) {
	        LOGGER.info("SalesCommsQueueListener::Message Received: {}", message);
	        // Read the "Message" from raw sns message	      
	        try {
	        	String cheapTreeRead = objectMapper.readTree(message).at("/MessageAttributes/X-THEAA-EVENT_TYPE/Value").asText();
			  
	        	if(cheapTreeRead!=null && cheapTreeRead.equalsIgnoreCase("DIP-SALES.BREAKDOWN.QUOTE")) {
		        	Optional<PlatformMessage<QuoteMessageDTO>> quoteMessage = messageClient.consume(objectMapper.readTree(message).at("/Message").asText(),
		        			QuoteMessageDTO.class);
		        	 if(quoteMessage.isPresent() && "DIP-SALES.BREAKDOWN.QUOTE".equals(quoteMessage.get().getMetaData().getNamespace())) {
				      // store the quote into the database
		        		 EventLogger.log(EventType.SALES_COMM, clazz, new LogEvent("SALES_BREAKDOWN_QUOTE_RECEIVED", "success"));
		        		 salesCommsService.storeQuotes(quoteMessage.get().getData());
		        	 }	
		        }else if(cheapTreeRead!=null && cheapTreeRead.equalsIgnoreCase("DIP-SALES.PAYMENT.SUCCESS")) {
		        	
		        	Optional<PlatformMessage<PaymentMessageDTO>> paymentMessage = messageClient.consume(objectMapper.
		        			readTree(message).at("/Message").asText(), PaymentMessageDTO.class);
		        	
		        	if(paymentMessage.isPresent() && "DIP-SALES.PAYMENT.SUCCESS".equals(paymentMessage.get().getMetaData().getNamespace())) {
		        		// drop email.
		        	       EventLogger.log(EventType.SALES_COMM, clazz, new LogEvent("PAYMENT_SUCESS_MESSAGE_RECEIVED", "success"));
		        		  salesCommsService.sendPaymentSuccessMessage(paymentMessage.get().getData());
		        	} 
		        }
		    } catch (JsonParseException e) {
	            EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage", e.getMessage()));
	  	   	    LOGGER.error("SalesCommsQueueListener::Error reading quote message or Payment Message", e);
	            return;
	        }catch (IOException e) {
	        	 EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage", e.getMessage()));
	        	LOGGER.error("SalesCommsQueueListener::Error reading quote message or Payment Message", e);
	        	return;
	        }catch(Exception e) {
	        	 EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage", e.getMessage()));
	        	LOGGER.error("SalesCommsQueueListener::Error reading quote message or Payment Message", e);
	        	return;
	        }
	    }
	    
}
