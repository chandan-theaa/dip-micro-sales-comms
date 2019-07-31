package com.theaa.dip.sales.comms.sqs.dto;


public class QuoteContentDTO {

	private String reference;

	private MotorRiskDataDTO motorRiskData;

	private VehicleDetailsDTO vehicleDetails;

	
	public String getReference() {
		return reference;
	}

	public VehicleDetailsDTO getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(VehicleDetailsDTO vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public MotorRiskDataDTO getMotorRiskData() {
		return motorRiskData;
	}

	public void setMotorRiskData(MotorRiskDataDTO motorRiskData) {
		this.motorRiskData = motorRiskData;
	}
	
}
