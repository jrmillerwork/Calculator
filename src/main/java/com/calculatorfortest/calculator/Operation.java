package com.calculatorfortest.calculator;

import java.util.function.Function;
/*
 * This enum should be the only file that need be modified to change the operations
 * that the Calculator class performs.
 * 
 * In order to add an operation, a new enum is added. This must have a lambda to implement the operation.
 * Part of the lambda is to prevent the incorrect number of arguments or any other illegal
 * operation by throwing an exception.
 * 
 * Note that the same lambdas are used in the calculate method of of Calculator and
 * also when chaining. This is discussed in more detail in Calculator.java.
 */

@FunctionalInterface
interface OperationFunction {
    double apply(double... operands) throws IllegalOperationArgumentException;
}

public enum Operation {
    ADD(operands -> {
    	if (operands.length != 2) {
            throw new IllegalOperationArgumentException("ADD operation requires exactly 2 operands.");
        }
       
        return (operands[0] + operands[1]);
    }),
    SUBTRACT(operands -> {
        if (operands.length != 2) {
            throw new IllegalOperationArgumentException("SUBTRACT operation requires exactly 2 operands.");
        }
        
        return (operands[0] - operands[1]);
    }),
    MULTIPLY(operands -> {
    	if (operands.length != 2) {
    		throw new IllegalOperationArgumentException("MULTIPLY operation requires exactly 2 operands.");
    	}
        
        return (operands[0]*operands[1]);
    }),
    DIVIDE(operands -> {
        if (operands.length != 2) {
            throw new IllegalOperationArgumentException("DIVIDE operation requires exactly 2 operands.");
        }
        if (operands[1] == 0) throw new IllegalOperationArgumentException("Cannot divide by zero.");
        
        return operands[0]/operands[1];
    }),
    SQUARE(operands -> {
        if (operands.length != 1) {
            throw new IllegalOperationArgumentException("SQUARE operation requires exactly one operand.");
        }
        return Math.pow(operands[0], 2);
    });

    private final OperationFunction operation;

    Operation(OperationFunction operation) {
        this.operation = operation;
    }

    public double apply(double... operands) throws IllegalOperationArgumentException {
        return operation.apply(operands);
    }
}