/**
 * 
 */
package com.codurance.socialnetworking.app.exceptions;

/**
 * @author Pinki Mittal
 *
 */
public class UserNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5286376166796104682L;

	/**
	 * 
	 */
	public UserNotExistsException() {
		super();
	}

	/**
	 * @param message
	 */
	public UserNotExistsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserNotExistsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserNotExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
