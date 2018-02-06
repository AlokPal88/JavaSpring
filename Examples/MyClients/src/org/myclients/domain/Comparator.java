package org.myclients.domain;

public class Comparator {

	public static boolean areEqual(Object a, Object b) {
		if(null == a || null == b) {
			return false;
		}

		return a == b;
	}
}
