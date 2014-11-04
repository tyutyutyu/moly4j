package com.tyutyutyu.molyhuapi;

public class MolyHuException extends Exception {

	private static final long serialVersionUID = 1L;

	public MolyHuException() {
		super();
	}

	public MolyHuException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MolyHuException(String message, Throwable cause) {
		super(message, cause);
	}

	public MolyHuException(String message) {
		super(message);
	}

	public MolyHuException(Throwable cause) {
		super(cause);
	}

}
