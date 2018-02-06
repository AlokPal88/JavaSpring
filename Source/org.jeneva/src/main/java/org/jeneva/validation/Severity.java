package org.jeneva.validation;

/**
 * Defines Severity levels
 */
public enum Severity {
	None(0),
	Low(1),
	High(2),
	Fatal(3);

	private int value;

	Severity(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}