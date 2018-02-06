package org.jeneva.validation.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import org.jeneva.validation.IMath;

/**
 * Defines arithmetic operations.
 * All methods return 'false' if one of the parameters is NULL, otherwise arithmetic result is returned.
 *
 */
public class Math implements IMath {

	public boolean IsLess(Long a, Long b) {
		if(null == a || null == b) return false;
		return a < b;
	}

	public boolean isGreater(Long a, Long b) {
		if(null == a || null == b) return false;
		return a > b;
	}

	public boolean isLessOrEqual(Long a, Long b) {
		if(null == a || null == b) return false;
		return a <= b;
	}

	public boolean isGreaterOrEqual(Long a, Long b) {
		if(null == a || null == b) return false;
		return a >= b;
	}

	public boolean isEqual(Long a, Long b) {
		if(null == a || null == b) return false;
		return a == b;
	}

	public boolean isNotEqual(Long a, Long b) {
		if(null == a || null == b) return false;
		return a != b;
	}




	public boolean isLess(Integer a, Integer b) {
		if(null == a || null == b) return false;
		return a < b;
	}

	public boolean isGreater(Integer a, Integer b) {
		if(null == a || null == b) return false;
		return a > b;
	}

	public boolean isLessOrEqual(Integer a, Integer b) {
		if(null == a || null == b) return false;
		return a <= b;
	}

	public boolean isGreaterOrEqual(Integer a, Integer b) {
		if(null == a || null == b) return false;
		return a >= b;
	}

	public boolean isEqual(Integer a, Integer b) {
		if(null == a || null == b) return false;
		return a == b;
	}

	public boolean isNotEqual(Integer a, Integer b) {
		if(null == a || null == b) return false;
		return a != b;
	}




	public boolean isLess(Short a, Short b) {
		if(null == a || null == b) return false;
		return a < b;
	}

	public boolean isGreater(Short a, Short b) {
		if(null == a || null == b) return false;
		return a > b;
	}

	public boolean isLessOrEqual(Short a, Short b) {
		if(null == a || null == b) return false;
		return a <= b;
	}

	public boolean isGreaterOrEqual(Short a, Short b) {
		if(null == a || null == b) return false;
		return a >= b;
	}

	public boolean isEqual(Short a, Short b) {
		if(null == a || null == b) return false;
		return a == b;
	}

	public boolean isNotEqual(Short a, Short b) {
		if(null == a || null == b) return false;
		return a != b;
	}




	public boolean isLess(Byte a, Byte b) {
		if(null == a || null == b) return false;
		return a < b;
	}

	public boolean isGreater(Byte a, Byte b) {
		if(null == a || null == b) return false;
		return a > b;
	}

	public boolean isLessOrEqual(Byte a, Byte b) {
		if(null == a || null == b) return false;
		return a <= b;
	}

	public boolean isGreaterOrEqual(Byte a, Byte b) {
		if(null == a || null == b) return false;
		return a >= b;
	}

	public boolean isEqual(Byte a, Byte b) {
		if(null == a || null == b) return false;
		return a == b;
	}

	public boolean isNotEqual(Byte a, Byte b) {
		if(null == a || null == b) return false;
		return a != b;
	}




	public boolean isLess(Double a, Double b, int decimals) {
		if(null == a || null == b) return false;
		a = this.round(a, decimals);
		b = this.round(b, decimals);
		return a < b;
	}

	public boolean isGreater(Double a, Double b, int decimals) {
		if(null == a || null == b) return false;
		a = this.round(a, decimals);
		b = this.round(b, decimals);
		return a > b;
	}

	public boolean isLessOrEqual(Double a, Double b, int decimals) {
		if(null == a || null == b) return false;
		a = this.round(a, decimals);
		b = this.round(b, decimals);
		return a <= b;
	}

	public boolean isGreaterOrEqual(Double a, Double b, int decimals) {
		if(null == a || null == b) return false;
		a = this.round(a, decimals);
		b = this.round(b, decimals);
		return a >= b;
	}

	public boolean isEqual(Double a, Double b, int decimals) {
		if(null == a || null == b) return false;
		a = this.round(a, decimals);
		b = this.round(b, decimals);
		return a == b;
	}

	public boolean isNotEqual(Double a, Double b, int decimals) {
		if(null == a || null == b) return false;
		a = this.round(a, decimals);
		b = this.round(b, decimals);
		return a != b;
	}




	public boolean isLess(BigDecimal a, BigDecimal b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa < bb;
	}

	public boolean isGreater(BigDecimal a, BigDecimal b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa > bb;
	}

	public boolean isLessOrEqual(BigDecimal a, BigDecimal b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa <= bb;
	}

	public boolean isGreaterOrEqual(BigDecimal a, BigDecimal b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa >= bb;
	}

	public boolean isEqual(BigDecimal a, BigDecimal b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa == bb;
	}

	public boolean isNotEqual(BigDecimal a, BigDecimal b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa != bb;
	}




	public boolean isLess(Float a, Float b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa < bb;
	}

	public boolean isGreater(Float a, Float b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa > bb;
	}

	public boolean isLessOrEqual(Float a, Float b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa <= bb;
	}

	public boolean isGreaterOrEqual(Float a, Float b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa >= bb;
	}

	public boolean isEqual(Float a, Float b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa == bb;
	}

	public boolean isNotEqual(Float a, Float b, int decimals) {
		if(null == a || null == b) return false;
		double aa = this.round(a.doubleValue(), decimals);
		double bb = this.round(b.doubleValue(), decimals);
		return aa != bb;
	}




	public boolean isLess(BigInteger a, BigInteger b) {
		if(null == a || null == b) return false;
		return a.longValue() < b.longValue();
	}

	public boolean isGreater(BigInteger a, BigInteger b) {
		if(null == a || null == b) return false;
		return a.longValue() > b.longValue();
	}

	public boolean isLessOrEqual(BigInteger a, BigInteger b) {
		if(null == a || null == b) return false;
		return a.longValue() <= b.longValue();
	}

	public boolean isGreaterOrEqual(BigInteger a, BigInteger b) {
		if(null == a || null == b) return false;
		return a.longValue() >= b.longValue();
	}

	public boolean isEqual(BigInteger a, BigInteger b) {
		if(null == a || null == b) return false;
		return a == b;
	}

	public boolean isNotEqual(BigInteger a, BigInteger b) {
		if(null == a || null == b) return false;
		return a != b;
	}




	public boolean isLess(Date a, Date b, boolean noTime) {
		if(null == a || null == b) return false;
		if(noTime) {
			a = this.getDateWithoutTime(a);
			b = this.getDateWithoutTime(b);
		}

		return a.before(b);
	}

	public boolean isGreater(Date a, Date b, boolean noTime) {
		if(null == a || null == b) return false;
		if(noTime) {
			a = this.getDateWithoutTime(a);
			b = this.getDateWithoutTime(b);
		}

		return a.after(b);
	}

	public boolean isLessOrEqual(Date a, Date b, boolean noTime) {
		if(null == a || null == b) return false;
		if(noTime) {
			a = this.getDateWithoutTime(a);
			b = this.getDateWithoutTime(b);
		}

		return a.before(b) || a.equals(b);
	}

	public boolean isGreaterOrEqual(Date a, Date b, boolean noTime) {
		if(null == a || null == b) return false;
		if(noTime) {
			a = this.getDateWithoutTime(a);
			b = this.getDateWithoutTime(b);
		}

		return a.after(b) || a.equals(b);
	}

	public boolean isEqual(Date a, Date b, boolean noTime) {
		if(null == a || null == b) return false;
		if(noTime) {
			a = this.getDateWithoutTime(a);
			b = this.getDateWithoutTime(b);
		}

		return a.equals(b);
	}

	public boolean isNotEqual(Date a, Date b, boolean noTime) {
		if(null == a || null == b) return false;
		if(noTime) {
			a = this.getDateWithoutTime(a);
			b = this.getDateWithoutTime(b);
		}

		return !a.equals(b);
	}

	private double round(double value, int decimals) {
		int m = (int)java.lang.Math.pow(10, decimals);
		value = m * value;
		return (double)java.lang.Math.round(value) / m;
	}

	private Date getDateWithoutTime(Date value) {
		Calendar date = Calendar.getInstance();
		date.setTime(value);
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);
		date.clear();
		date.set(year, month, day);
		return date.getTime();
	}
}