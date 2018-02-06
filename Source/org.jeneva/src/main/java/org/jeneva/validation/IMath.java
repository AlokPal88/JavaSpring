package org.jeneva.validation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface IMath {

	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean IsLess(Long a, Long b);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreater(Long a, Long b);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLessOrEqual(Long a, Long b);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Long a, Long b);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isEqual(Long a, Long b);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isNotEqual(Long a, Long b);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLess(Integer a, Integer b);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreater(Integer a, Integer b);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLessOrEqual(Integer a, Integer b);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Integer a, Integer b);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isEqual(Integer a, Integer b);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isNotEqual(Integer a, Integer b);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLess(Short a, Short b);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreater(Short a, Short b);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLessOrEqual(Short a, Short b);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Short a, Short b);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isEqual(Short a, Short b);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isNotEqual(Short a, Short b);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLess(Byte a, Byte b);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreater(Byte a, Byte b);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLessOrEqual(Byte a, Byte b);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Byte a, Byte b);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isEqual(Byte a, Byte b);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isNotEqual(Byte a, Byte b);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isLess(Double a, Double b, int decimals);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isGreater(Double a, Double b, int decimals);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isLessOrEqual(Double a, Double b, int decimals);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Double a, Double b, int decimals);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isEqual(Double a, Double b, int decimals);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isNotEqual(Double a, Double b, int decimals);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isLess(BigDecimal a, BigDecimal b, int decimals);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isGreater(BigDecimal a, BigDecimal b, int decimals);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isLessOrEqual(BigDecimal a, BigDecimal b, int decimals);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(BigDecimal a, BigDecimal b, int decimals);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isEqual(BigDecimal a, BigDecimal b, int decimals);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isNotEqual(BigDecimal a, BigDecimal b, int decimals);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isLess(Float a, Float b, int decimals);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isGreater(Float a, Float b, int decimals);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isLessOrEqual(Float a, Float b, int decimals);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Float a, Float b, int decimals);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isEqual(Float a, Float b, int decimals);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param decimals number of decimals to round
	 * @return true if yes
	 */
	boolean isNotEqual(Float a, Float b, int decimals);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLess(BigInteger a, BigInteger b);

	/**
	 * Checks if a is greater than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreater(BigInteger a, BigInteger b);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isLessOrEqual(BigInteger a, BigInteger b);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(BigInteger a, BigInteger b);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isEqual(BigInteger a, BigInteger b);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @return true if yes
	 */
	boolean isNotEqual(BigInteger a, BigInteger b);






	/**
	 * Checks if a is less than b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param noTime true if time is discarded
	 * @return true if yes
	 */
	boolean isLess(Date a, Date b, boolean noTime);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param noTime true if time is discarded
	 * @return true if yes
	 */
	boolean isGreater(Date a, Date b, boolean noTime);

	/**
	 * Checks if a is less than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param noTime true if time is discarded
	 * @return true if yes
	 */
	boolean isLessOrEqual(Date a, Date b, boolean noTime);

	/**
	 * Checks if a is greater than or equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param noTime true if time is discarded
	 * @return true if yes
	 */
	boolean isGreaterOrEqual(Date a, Date b, boolean noTime);

	/**
	 * Checks if a is equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param noTime true if time is discarded
	 * @return true if yes
	 */
	boolean isEqual(Date a, Date b, boolean noTime);

	/**
	 * Checks if a is not equal to b (returns false if at least one of the parameters is NULL)
	 * @param a a
	 * @param b b
	 * @param noTime true if time is discarded
	 * @return true if yes
	 */
	boolean isNotEqual(Date a, Date b, boolean noTime);
}