package com.theaa.dip.sales.comms.dao.repository;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theaa.dip.lib.instrument.logging.EventLogger;
import com.theaa.dip.lib.instrument.logging.LogEvent;
import com.theaa.dip.sales.comms.exception.SalesCommDatabaseException;
import com.theaa.dip.sales.comms.logging.EventType;
import com.theaa.dip.sales.comms.repository.vo.QuoteMessageVO;
import com.theaa.dip.sales.comms.sqs.dto.QuoteMessageDTO;

import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemResponse;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

@Repository
public class SalesCommsDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesCommsDAO.class);
    
    private final String clazz = this.getClass().getSimpleName();
    
    private final DynamoDbClient dynamoDbClient;
    
   
    
    private final String salesCommsTable;
    
    private final ObjectMapper objectMapper;
    
    @Value("${dip-micro-sales-comms.dynamodb.ttl}")
	private Long daysforttl;
    
    
    public SalesCommsDAO(DynamoDbClient dynamoDbClient, ResourceIdResolver resourceIdResolver, ObjectMapper objectMapper) {
      	this.dynamoDbClient = dynamoDbClient;
        this.salesCommsTable = resourceIdResolver.resolveToPhysicalResourceId("SalesCommsTable");
        this.objectMapper = objectMapper;
        LOGGER.info("Resolved Table Name: {}", this.salesCommsTable);
    }
    
    public Mono<QuoteMessageVO> getQuoteDetailsbyQuoteReference(String quoteRef) {
          HashMap<String, AttributeValue> key_to_get = new HashMap<String, AttributeValue>();
          key_to_get.put("QUOTE_REF", AttributeValue.builder().s(quoteRef).build());
          GetItemRequest itemRequest = GetItemRequest.builder().tableName(salesCommsTable).key(key_to_get)
        		  .projectionExpression("QUOTE_REF,QUOTE_DETAILS").build();
          GetItemResponse itemResponse = dynamoDbClient.getItem(itemRequest);
          Map<String, AttributeValue> returnValue = itemResponse.item();
          try {
			return Mono.just(objectMapper.readValue(returnValue.get("QUOTE_DETAILS").s(), QuoteMessageVO.class));
		} catch (JsonParseException e) {
			  LOGGER.error("Error in getting the quote details by quote reference " + quoteRef + e.getMessage());
	            EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage: in getting the quote details by quote reference  ", e.getMessage()));
	            throw new SalesCommDatabaseException("Error in getting the quote details by quote reference " + quoteRef + e.getMessage());
		} catch (JsonMappingException e) {
			  LOGGER.error("Error in getting the quote details by quote reference " + quoteRef + e.getMessage());
	            EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage: in getting the quote details by quote reference  ", e.getMessage()));
	            throw new SalesCommDatabaseException("Error in getting the quote details by quote reference " + quoteRef + e.getMessage());
		} catch (IOException e) {
			  LOGGER.error("Error in getting the quote details by quote reference " + quoteRef + e.getMessage());
	            EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage: in getting the quote details by quote reference ", e.getMessage()));
	            throw new SalesCommDatabaseException("Error in getting the quote details by quote reference " + quoteRef + e.getMessage());
		}
    }
    
    public void insertQuoteInTheDatabase(QuoteMessageVO quoteDetailsItem) throws SalesCommDatabaseException {
        try {
            if (quoteDetailsItem != null) {
                Map<String, AttributeValue> item_values = new HashMap<>();
                item_values.put("QUOTE_REF", AttributeValue.builder().s(quoteDetailsItem.getQuoteReference()).build());
                String quoteDetails = objectMapper.writeValueAsString(quoteDetailsItem);
                item_values.put("QUOTE_DETAILS", AttributeValue.builder().s(quoteDetails).build());
                long ttlValue = getTTLDetails();
                item_values.put("TTL", AttributeValue.builder().n(String.valueOf(ttlValue)).build());
                PutItemRequest request = PutItemRequest.builder().tableName(salesCommsTable).item(item_values).build();
                dynamoDbClient.putItem(request);
            }
        } catch (JsonProcessingException e) {
            LOGGER.error("Error in inserting record in the database " + quoteDetailsItem + e.getMessage());
            EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage : Error in inserting record in the database ", e.getMessage()));
            throw new SalesCommDatabaseException("Error in inserting record in the database " + quoteDetailsItem + e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("Error in inserting record in the database " + quoteDetailsItem + e.getMessage());
            EventLogger.log(EventType.SALES_COMM_ERROR, clazz, new LogEvent("ErrorMessage : Error in inserting record in the database ", e.getMessage()));
            throw new SalesCommDatabaseException("Error in inserting record in the database " + quoteDetailsItem + e.getMessage());
        }
    }
    
    private Long getTTLDetails() {
    	Instant nowPlusTwoDays = Instant.now().plus(daysforttl, ChronoUnit.DAYS);
    	Long ttl =nowPlusTwoDays.getEpochSecond();
    	return ttl;
  	}

}
