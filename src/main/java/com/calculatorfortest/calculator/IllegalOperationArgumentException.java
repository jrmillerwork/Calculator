package com.calculatorfortest.calculator;
/*
 * This is checked exception that is thrown when 
 * an Operation is invoked with the wrong number 
 * of arguments (like ADD with just one) or a zero
 * is supplied as a divisor.
 * 
 * These should be propagated to, for example, a CLI so
 * a user can reviser their choice of arguments.
 */
@SuppressWarnings("serial")
public class IllegalOperationArgumentException extends Exception {

	public IllegalOperationArgumentException(String message) {
		super(message);
	}

	
}
