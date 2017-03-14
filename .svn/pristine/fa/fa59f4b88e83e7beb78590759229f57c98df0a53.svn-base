package com.chechemotel.logs.common;

public class MongoDaoException extends Exception {
	private static final long serialVersionUID = -1565610154291318963L;

	public static final int NOKEY 			= -1000;
	public static final int DUPLICATE 		= -1001;
	public static final int WRITECONCERN 	= -1002;
	public static final int SERVICECONFIG 	= -1003;
	public static final int FIELDITYPE	 	= -1004;
	public static final int DATACORRUPT	 	= -1005;
		
	private int code;

	public MongoDaoException() {
		super();
	}

	public MongoDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public MongoDaoException(String message) {
		super(message);
	}

	public MongoDaoException(Throwable cause) {
		super(cause);
	}

	public MongoDaoException(int code) {
		this();
		this.code = code;
	}

	public MongoDaoException(int code, String message) {
		this(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
