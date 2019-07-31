package com.theaa.dip.sales.comms.repository.vo;

public class VehicleDetailsVO {
	private VehicleVO vehicle;
	private String vehicleUsage;
	private String vehicleKeptAtNight;
	private String vehicleKeptAtLocation;
	private float purchaseMonth;
	private float purchaseYear;
	private float annualMileage;
	private float value;
	private String garagedPostCode;
	private String registeredToCode;
	private String vehicleOwnerCode;
	private float numberOfSeats;
	private boolean rightHandDrive;
	
	
	public String getVehicleUsage() {
		return vehicleUsage;
	}

	public String getVehicleKeptAtNight() {
		return vehicleKeptAtNight;
	}

	public String getVehicleKeptAtLocation() {
		return vehicleKeptAtLocation;
	}

	public float getPurchaseMonth() {
		return purchaseMonth;
	}

	public float getPurchaseYear() {
		return purchaseYear;
	}

	public float getAnnualMileage() {
		return annualMileage;
	}

	public float getValue() {
		return value;
	}

	public String getGaragedPostCode() {
		return garagedPostCode;
	}

	public String getRegisteredToCode() {
		return registeredToCode;
	}

	public String getVehicleOwnerCode() {
		return vehicleOwnerCode;
	}

	public float getNumberOfSeats() {
		return numberOfSeats;
	}

	public boolean getRightHandDrive() {
		return rightHandDrive;
	}

	// Setter Methods



	public void setVehicleUsage(String vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}

	public VehicleVO getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleVO vehicle) {
		this.vehicle = vehicle;
	}

	public void setVehicleKeptAtNight(String vehicleKeptAtNight) {
		this.vehicleKeptAtNight = vehicleKeptAtNight;
	}

	public void setVehicleKeptAtLocation(String vehicleKeptAtLocation) {
		this.vehicleKeptAtLocation = vehicleKeptAtLocation;
	}

	public void setPurchaseMonth(float purchaseMonth) {
		this.purchaseMonth = purchaseMonth;
	}

	public void setPurchaseYear(float purchaseYear) {
		this.purchaseYear = purchaseYear;
	}

	public void setAnnualMileage(float annualMileage) {
		this.annualMileage = annualMileage;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public void setGaragedPostCode(String garagedPostCode) {
		this.garagedPostCode = garagedPostCode;
	}

	public void setRegisteredToCode(String registeredToCode) {
		this.registeredToCode = registeredToCode;
	}

	public void setVehicleOwnerCode(String vehicleOwnerCode) {
		this.vehicleOwnerCode = vehicleOwnerCode;
	}

	public void setNumberOfSeats(float numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public void setRightHandDrive(boolean rightHandDrive) {
		this.rightHandDrive = rightHandDrive;
	}
}
