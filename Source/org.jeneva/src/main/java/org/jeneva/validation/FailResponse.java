package org.jeneva.validation;

import org.jeneva.validation.impl.FailureList;

/**
 * Represents validation summary
 */
public class FailResponse {

	private IFailureList failures;

	/**
	 * Initializes new instance of the FailRespnse class
	 */
	public FailResponse() {
		this.failures = null;
	}

	/**
	 * Initializes new instance of the FailRespnse class with a list of failures
	 * @param failures initial list of failures
	 */
	public FailResponse(IFailureList failures) {
		this.failures = failures;
	}

	/**
	 * Initializes new instance of FailRespnse class with one failure in it
	 * @param message failure message
	 */
	public FailResponse(String message)
	{
		this.failures = new FailureList();
		this.failures.fail(null, message);
	}

	/**
	 * Gets list of failures
	 * @return
	 */
	public IFailureList getFailures()					{ return this.failures; }

	/**
	 * Sets failures
	 * @param value
	 */
	public void setFailures(IFailureList value)			{ this.failures = value; }
}