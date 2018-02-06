package org.jeneva.validation;

/**
 * Represents Validation exception
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private IFailureList failures;

	/**
	 * Initializes new instance of the ValidationException class
	 * @param errors list of failures
	 */
	public ValidationException(IFailureList errors)
	{
		this.failures = errors;
	}

	/**
	 * Generates FailResponse object
	 * @return FailResponse instance
	 */
	public FailResponse buildFailResponse() {
		FailResponse response = new FailResponse();
		response.setFailures(this.failures);
		return response;
	}

	/**
	 * Gets list of failures
	 */
	public IFailureList getFailures() {
		return this.failures;
	}
}