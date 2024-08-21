package com.calculatorfortest.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTest {

    @Test
    public void testAdd() throws IllegalOperationArgumentException {
        assertEquals(5.0, Operation.ADD.apply(2, 3), "2 + 3 should equal 5");
    }

    @Test
    public void testSubtract() throws IllegalOperationArgumentException {
        assertEquals(1.0, Operation.SUBTRACT.apply(3, 2), "3 - 2 should equal 1");
    }

    @Test
    public void testMultiply() throws IllegalOperationArgumentException {
        assertEquals(6.0, Operation.MULTIPLY.apply(2, 3), "2 * 3 should equal 6");
    }

    @Test
    public void testDivide() throws IllegalOperationArgumentException {
        assertEquals(2.0, Operation.DIVIDE.apply(4, 2), "4 / 2 should equal 2");
    }

    @Test
    public void testDivideByZero() {
        assertThrows(IllegalOperationArgumentException.class, () -> Operation.DIVIDE.apply(4, 0), "Division by zero should throw ArithmeticException");
    }

    @Test
    public void testSquare() throws IllegalOperationArgumentException {
        assertEquals(4.0, Operation.SQUARE.apply(2), "Square of 2 should equal 4");
    }

    @Test
    public void testAddIncorrectOperandCount() {
        assertThrows(IllegalOperationArgumentException.class, () -> Operation.ADD.apply(1), "ADD operation with incorrect operand count should throw IllegalArgumentException");
    }

    @Test
    public void testSubtractIncorrectOperandCount() {
        assertThrows(IllegalOperationArgumentException.class, () -> Operation.SUBTRACT.apply(1), "SUBTRACT operation with incorrect operand count should throw IllegalArgumentException");
    }

    @Test
    public void testMultiplyIncorrectOperandCount() {
        assertThrows(IllegalOperationArgumentException.class, () -> Operation.MULTIPLY.apply(1), "MULTIPLY operation with incorrect operand count should throw IllegalArgumentException");
    }

    @Test
    public void testDivideIncorrectOperandCount() {
        assertThrows(IllegalOperationArgumentException.class, () -> Operation.DIVIDE.apply(1), "DIVIDE operation with incorrect operand count should throw IllegalArgumentException");
    }

    @Test
    public void testSquareIncorrectOperandCount() {
        assertThrows(IllegalOperationArgumentException.class, () -> Operation.SQUARE.apply(1, 2), "SQUARE operation with incorrect operand count should throw IllegalArgumentException");
    }
}
