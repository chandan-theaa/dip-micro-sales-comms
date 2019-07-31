package com.theaa.dip.sales.comms.sqs.dto;


public class VehicleDTO {

	 private String regYear;
	 private String abiCode;

	public String getRegYear() {
	  return regYear;
	 }

	 public String getAbiCode() {
	  return abiCode;
	 }
	 public void setRegYear(String regYear) {
	  this.regYear = regYear;
	 }

	 public void setAbiCode(String abiCode) {
	  this.abiCode = abiCode;
	 }
}