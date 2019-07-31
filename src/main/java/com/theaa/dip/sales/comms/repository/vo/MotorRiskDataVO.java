package com.theaa.dip.sales.comms.repository.vo;

import java.util.ArrayList;


public class MotorRiskDataVO {

	private ArrayList<DriverVO> drivers = new ArrayList<DriverVO>();
	private MotorQuoteOptionsVO motorQuoteOptions;

	public ArrayList<DriverVO> getDrivers() {
		return drivers;
	}

	public void setDrivers(ArrayList<DriverVO> drivers) {
		this.drivers = drivers;
	}

	public MotorQuoteOptionsVO getMotorQuoteOptions() {
		return motorQuoteOptions;
	}

	public void setMotorQuoteOptions(MotorQuoteOptionsVO motorQuoteOptions) {
		this.motorQuoteOptions = motorQuoteOptions;
	}

	 
}
