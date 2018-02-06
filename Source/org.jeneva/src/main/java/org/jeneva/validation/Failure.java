package org.jeneva.validation;

/**
 * represents a single validation failure
 */
public class Failure {

	private String key;
	private String text;

	/**
	 * Initializes a new instance of the Failure class
	 * @param key key (property path)
	 * @param text failure message
	 */
	public Failure(String key, String text) {
		this.key = key;
		this.text = text;
	}

	/**
	 * Gets failure key/path
	 * @return
	 */
	public String getKey()									{ return this.key; }

	/**
	 * Sets failure key/path
	 * @param value
	 */
	public void setKey(String value)						{ this.key = value; }


	/**
	 * Gets failure text
	 * @return
	 */
	public String getText()									{ return this.text; }

	/**
	 * Sets failure text
	 * @param value
	 */
	public void setText(String value)						{ this.text = value; }
}