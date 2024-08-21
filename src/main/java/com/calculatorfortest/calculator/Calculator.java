package com.calculatorfortest.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/*
 * This class uses the enum Operation not only as
 * a list of operations but also as the source of
 * the implementations because each new operation
 * has an associated lambda. This allows Calculator
 * to remain unchanged when operations are added or
 * modified.
 * 
 * Exceptions: Throws IllegalOperationArgumentException -- I believe the
 * correct place to deal with such exceptions would for example be in a CLI
 * which utilizes Calculator. A user could easily enter too many arguments
 * or a zero divisor and should be given an opportunity to correct
 * their input instead of the program crashing. 
 * 
 * As far as unsupported operations, this is not possible since using the
 * enum approach. An attempt to use for example calculate(Operation.FOO,1,2) would
 * be caught at compile time. If we provided a CLI which translated strings
 * to arithmetic operations, then an unsupported operation exception would
 * need to be thrown and handled by perhaps showing all strings they can
 * choose from.
 * 
 * Usage:
 * 
 * 1. A static method calculate allows various calculator functions
 * such as ADD, SUBTRACT, etc. to be executed on their operands. 
 * The number of operands varies: ADD takes two, but SQUARE takes only
 * one. An Exception is thrown for incorrect number of arguments which is
 * not a compile error.
 * 
 * 2. Chaining is done by instantiating Calculator, invoking the init method
 * and then performing an arbitrary number of chained operations and then
 * getting the result. For example:
 * 
 * Calculator c = new Calculator();
 * double result = c.init(3).apply(Operation.MULTIPLY, 10).apply(Operation.ADD, 12).getResult();
 * 
 * 3. The getOperationNames() method returns a list of string that show all current operations.
 * This would be used in a CLI when for example the user requested help.
 * 
 */
public class Calculator {
	//used for chaining
    private double currentValue;
    
    public Calculator init(double initValue) {
    	this.currentValue = initValue;
    	return this;
    }
    public Calculator apply(Operation operation, double... operands) throws IllegalOperationArgumentException {
        double[] updatedOperands = new double[operands.length + 1];
        updatedOperands[0] = this.currentValue;
        System.arraycopy(operands, 0, updatedOperands, 1, operands.length);
        this.currentValue = operation.apply(updatedOperands);
        return this;
    }
    // Get current value/result of calculations
    public double getResult() {
        return currentValue;
    }

    // Static method for direct calculation with variable arguments
    public static double calculate(Operation operation, double... operands) throws IllegalOperationArgumentException {
        return operation.apply(operands);
    }
    /*
     * Provides a list of the names of the operations available
     */
    public static List<String> getOperationNames() {
        return Arrays.stream(Operation.values())
                     .map(Enum::name)
                     .collect(Collectors.toList());
    }
}