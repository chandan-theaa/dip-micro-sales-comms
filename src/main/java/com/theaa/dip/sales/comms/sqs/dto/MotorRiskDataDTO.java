package com.theaa.dip.sales.comms.sqs.dto;

import java.util.ArrayList;


public class MotorRiskDataDTO {

	private ArrayList<DriverDTO> drivers = new ArrayList<DriverDTO>();
	private MotorQuoteOptionsDTO motorQuoteOptions;
	
	
	

	public ArrayList<DriverDTO> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<DriverDTO> drivers) {
		this.drivers = drivers;
	}

	public MotorQuoteOptionsDTO getMotorQuoteOptions() {
		return motorQuoteOptions;
	}

	public void setMotorQuoteOptions(MotorQuoteOptionsDTO motorQuoteOptions) {
		this.motorQuoteOptions = motorQuoteOptions;
	}
	

}
