package com.theaa.dip.sales.comms.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.theaa.dip.sales.comms.dao.repository.SalesCommsDAO;
import com.theaa.dip.sales.comms.dto.EmailRequestDTO;
import com.theaa.dip.sales.comms.repository.vo.QuoteMessageVO;
import com.theaa.dip.sales.comms.sqs.dto.DriverDTO;
import com.theaa.dip.sales.comms.sqs.dto.PaymentMessageDTO;
import com.theaa.dip.sales.comms.sqs.dto.QuoteContentDTO;
import com.theaa.dip.sales.comms.sqs.dto.QuoteMessageDTO;

import reactor.core.publisher.Mono;

@Service
public class SalesCommsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesCommsService.class);
	
	private final SalesCommsDAO salesCommsDao;
	
	ModelMapper modelMapper;
	
	@Value("${dip-micro-sales-comms.email.emv_tag}")
	private String emv_tag;
	    
	@Value("${dip-micro-sales-comms.email.emv_ref}")
	private String emv_ref;
	
	@Value("${dip-micro-sales-comms.email.baseUrl}")
	private String baseUrl;

	@Value("${dip-micro-sales-comms.email.URI}")
	private String URI;
	
	    
	@Autowired
	public SalesCommsService(SalesCommsDAO salesCommsDao,ModelMapper modelMapper) {
		this.salesCommsDao = salesCommsDao;
		this.modelMapper = modelMapper;
	}
	
	 public void storeQuotes(QuoteMessageDTO quote) {
		 if(quote!=null) {
			salesCommsDao.insertQuoteInTheDatabase(adaptQuoteTopicMessageToQuoteDTO(quote));
		 }
	 }
	 
	 
	 private QuoteMessageVO adaptQuoteTopicMessageToQuoteDTO(QuoteMessageDTO sqt) {
		 QuoteMessageVO quoteVO = modelMapper.map(sqt, QuoteMessageVO.class);
		 return quoteVO;
	  }
	 
	 
	 private QuoteMessageDTO adaptQuoteVOToQuoteDTO(QuoteMessageVO sqt) {
		 QuoteMessageDTO quoteDTO = modelMapper.map(sqt, QuoteMessageDTO.class);
		 return quoteDTO;
	  }
	 
	 
	 public Mono<String> sendEmail(EmailRequestDTO emailRequest) {
		 	LOGGER.info("Requesting email my quote {} ", emailRequest.toString());
			Mono<String> clientResponseMono = WebClient.create(baseUrl).post().uri(URI)
					.body(BodyInserters.fromFormData(mapRequestData(emailRequest)))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE).exchange()
					.log(" Sending email  to customer ")
					.flatMap(resp -> resp.toEntity(String.class)).flatMap(entity -> {
						if (entity.getStatusCode().is2xxSuccessful()) {
							return Mono.just("Success");
						} else if (entity.getStatusCode().is3xxRedirection()) {
							if (entity.getHeaders().getLocation().toString().contains("pageok")) {
								return Mono.just("Success");
							}
						}
						return Mono.just("failure");
					});
			return clientResponseMono;
	 }
	 
	    
	 public MultiValueMap<String, String> mapRequestData(final EmailRequestDTO emailRequest) {
	     	MultiValueMap<String, String> values = new LinkedMultiValueMap<>();
	        values.add("emv_tag", emv_tag);
	        values.add("emv_ref", emv_ref);
	        System.out.println("emailRequest.getQuoteReference()==="+emailRequest.getQuoteReference());
	        System.out.println("emailRequest.getStartDate())==="+emailRequest.getStartDate());
	        System.out.println("emailRequest.getProposerEmail()==="+emailRequest.getProposerEmail()); 
	        System.out.println("emailRequest.getProposerTitle()==="+emailRequest.getProposerTitle()); 
            System.out.println("emailRequest.getProposerSurname()==="+emailRequest.getProposerSurname());
            System.out.println("emailRequest.getAnnualPrice().toString()==="+emailRequest.getAnnualPrice().toString());
            System.out.println("emailRequest.getVehicleRegistration()===="+emailRequest.getVehicleRegistration());
            System.out.println("eemailRequest.getVoluntaryExcess().toString()===="+emailRequest.getVoluntaryExcess().toString());
	        values.add("NEW_QUOTE_REF_NO_FIELD", emailRequest.getQuoteReference());
	        values.add("COVER_START_DATE_FIELD",emailRequest.getStartDate());
	        values.add("EMAIL_FIELD",emailRequest.getProposerEmail());
	        values.add("TITLE_FIELD",emailRequest.getProposerTitle());
	        values.add("LASTNAME_FIELD",emailRequest.getProposerSurname());
	        values.add("QP_ONE_OFF_FIELD",emailRequest.getAnnualPrice().toString());
	        values.add("REGISTRATION_NUMBER_FIELD",emailRequest.getVehicleRegistration());
	        values.add("VOLUNTARY_EXCESS_FIELD", emailRequest.getVoluntaryExcess().toString());
	        return values;
	    }

	 
	 public void sendPaymentSuccessMessage(PaymentMessageDTO paymentMessage) {
		String quoteReference = paymentMessage.getQuoteReference();
		Mono<QuoteMessageVO> quoteMessageVO = salesCommsDao.getQuoteDetailsbyQuoteReference(quoteReference);
		 String value= quoteMessageVO.map(item -> adaptQuoteVOToQuoteDTO(item)).map(item1 -> getEmailRequestDetails(item1))
				.flatMap(item2 -> sendEmail(item2)).block();
				//.switchIfEmpty(Mono.error(new RuntimeException("Error in sending email for the quoteReference"+ quoteReference)));
	   System.out.println("value===="+value);
		 
     }

	public EmailRequestDTO getEmailRequestDetails(QuoteMessageDTO item) {
			String  quoteRef= item.getQuoteReference();
			QuoteContentDTO quoteContentDTO=item.getQuoteContent();
			Optional<DriverDTO> driverDTO=quoteContentDTO.getMotorRiskData()
					.getDrivers().stream()
					.filter(dri -> dri.isMainDriver())
					.findFirst();
			String proposerSurname = driverDTO.get().getLastName();
			String proposerTitle = driverDTO.get().getTitle();
			String proposerEmail = driverDTO.get().getContactInformation().getEmail().get(0);
			String coverStartDate = quoteContentDTO.getMotorRiskData().getMotorQuoteOptions().getCoverStartDate();
			String dateValue=changeStringFormat(coverStartDate);
			return new EmailRequestDTO(quoteRef, new BigDecimal(0), proposerTitle, proposerSurname, proposerEmail,
					"vechicleNumber", dateValue, new BigDecimal(0));
	}

	private String changeStringFormat(String coverStartDate) {
		String dateValue=coverStartDate.substring(0, coverStartDate.indexOf("T"));
		return dateValue.replace("-", "/");
	}	
	 
}
