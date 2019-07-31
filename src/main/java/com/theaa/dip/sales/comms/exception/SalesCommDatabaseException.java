package com.theaa.dip.sales.comms.exception;

public class SalesCommDatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    protected SalesCommDatabaseException() {

    }

    public SalesCommDatabaseException(String msg) {
        super(msg);
    }

}