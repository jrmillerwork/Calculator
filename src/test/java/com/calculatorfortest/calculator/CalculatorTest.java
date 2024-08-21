package com.calculatorfortest.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testInitialValue() {
        assertEquals(0, calculator.getResult(), "Initial value should be 0");
    }

    @Test
    void testInitMethod() {
        calculator.init(5);
        assertEquals(5, calculator.getResult(), "Init method should set the current value correctly");
    }

   
    @Test
    void testAddOperation() throws IllegalOperationArgumentException {
        calculator.init(1).apply(Operation.ADD, 1);
        assertEquals(2, calculator.getResult(), "Add operation should sum the operands");
    }

    @Test
    void testSubtractOperation() throws IllegalOperationArgumentException {
        calculator.init(10).apply(Operation.SUBTRACT, 5);
        assertEquals(5, calculator.getResult(), "Subtract operation should subtract the operands from the initial value");
    }

    @Test
    void testMultiplyOperation() throws IllegalOperationArgumentException {
        calculator.init(6).apply(Operation.MULTIPLY, 4);
        assertEquals(24, calculator.getResult(), "Multiply operation should multiply the operands");
    }

    @Test
    void testDivideOperation() throws IllegalOperationArgumentException  {
        calculator.init(20).apply(Operation.DIVIDE, 2);
        assertEquals(10, calculator.getResult(), "Divide operation should divide the initial value by the operand");
    }
    @Test
    void testChainAllOperations() throws IllegalOperationArgumentException {
    	double result = calculator.init(0).apply(Operation.ADD,100).apply(Operation.DIVIDE,2).apply(Operation.MULTIPLY,3).
    	apply(Operation.SUBTRACT, 120).apply(Operation.SQUARE).getResult();
    	assertEquals(result,900.0, "Demonstratiion of chaining all available operations"); 
    }
    @Test
    void testListOperations() {
    	List<String> allOps = Calculator.getOperationNames();
    	assertEquals(allOps.size(),5, "There are 5 available operations");
    	assertTrue(allOps.get(0).equals("ADD"), "ADD is first enum name");
    	assertTrue(allOps.get(1).equals("SUBTRACT"), "SUBTRACT is second enum name");
    	assertTrue(allOps.get(2).equals("MULTIPLY"), "MULTIPLY is third enum name");
    	assertTrue(allOps.get(3).equals("DIVIDE"), "DIVIDE is fourth enum name");
    	assertTrue(allOps.get(4).equals("SQUARE"), "SQUARE is fifth enum name");
    }
    @Test
    void testDivideByZeroOperation() {
        IllegalOperationArgumentException exception = assertThrows(IllegalOperationArgumentException.class, () -> {
            calculator.init(20).apply(Operation.DIVIDE, 0);
        }, "Divide by zero should throw IllegalOperationArgumentException");

        // Optionally, you can also assert details about the exception if needed
        assertEquals("Cannot divide by zero.", exception.getMessage(), "Exception message should indicate the divide by zero error");
    }
   
    @Test
    void testSquareOperation() throws IllegalOperationArgumentException {
        calculator.init(4).apply(Operation.SQUARE);
        assertEquals(16, calculator.getResult(), "Square operation should square the operand");
    }

    @Test
    void testSquareWithMultipleOperands() {
        IllegalOperationArgumentException thrown = assertThrows(IllegalOperationArgumentException.class, () -> calculator.apply(Operation.SQUARE, 4, 5),
                "Expected SQUARE to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("SQUARE operation requires exactly one operand"), "Exception message should indicate incorrect operand count for SQUARE");
    }

    @Test
    void testStaticCalculateMethodADDW2() throws IllegalOperationArgumentException {
        assertEquals(15, Calculator.calculate(Operation.ADD, 7, 8), "Static calculate method should correctly add operands");
    }
    @Test
    void testStaticCalculateMethodADDW3() {
        IllegalOperationArgumentException thrown = assertThrows(IllegalOperationArgumentException.class, () -> Calculator.calculate(Operation.ADD, 7, 8, 9),
            "Static calculate method should throw exception with more than 2 operands");
        assertEquals("ADD operation requires exactly 2 operands.", thrown.getMessage(), "Exception message should indicate incorrect operand count for ADD");
    }
   
}
