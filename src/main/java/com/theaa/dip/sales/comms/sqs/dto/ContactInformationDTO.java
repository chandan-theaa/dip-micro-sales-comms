package com.theaa.dip.sales.comms.sqs.dto;

import java.util.ArrayList;

public class ContactInformationDTO {

	private ArrayList < String > email = new ArrayList < String > ();

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

	
}
