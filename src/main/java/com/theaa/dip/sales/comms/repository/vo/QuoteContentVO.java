package com.theaa.dip.sales.comms.repository.vo;


public class QuoteContentVO {

	private String reference;

	private MotorRiskDataVO motorRiskData;

	private VehicleDetailsVO vehicleDetails;
	
	public String getReference() {
		return reference;
	}

	public VehicleDetailsVO getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(VehicleDetailsVO vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public MotorRiskDataVO getMotorRiskData() {
		return motorRiskData;
	}

	public void setMotorRiskData(MotorRiskDataVO motorRiskData) {
		this.motorRiskData = motorRiskData;
	}
	
}
