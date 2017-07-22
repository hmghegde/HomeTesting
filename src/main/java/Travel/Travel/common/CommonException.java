package Travel.Travel.common;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommonException(String myExp) {
		super(myExp);
	}

	/**
	 * Overloaded the EMM Exception class with respective exception.
	 * 
	 * @param e
	 * @param expMessage
	 */
	public CommonException(Exception e, String expMessage) {

		super(expMessage, e);
	}

	public CommonException() {

	}
}
