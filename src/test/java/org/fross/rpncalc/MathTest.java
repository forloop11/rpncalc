/******************************************************************************
 * RPNCalc
 * 
 * RPNCalc is is an easy to use console based RPN calculator
 * 
 *  Copyright (c) 2013-2023 Michael Fross
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *           
 ******************************************************************************/
package org.fross.rpncalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * @author Michael Fross (michael@fross.org)
 *
 */
class MathTest {
	/**
	 * Test method for {@link org.fross.rpncalc.Math#Add(org.fross.rpncalc.StackObj)}.
	 */
	@Test
	void testAdd() {
		StackObj stk = new StackObj();

		// Add test data to stack
		stk.push(1.23456);
		stk.push(4.56789);

		// Test #1
		assertEquals(5.80245, Math.Parse("+", stk).peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #2
		stk.push(-1.1);
		Math.Parse("+", stk);
		StackCommands.cmdRound(stk, "5");
		assertEquals(4.70245, stk.peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #3 - Scientific Notation
		stk.clear();
		stk.push(123.3222E27);
		stk.push(45.654321e33);
		assertEquals(2, stk.size());
		Math.Parse("+", stk);
		assertEquals("45.6544443222E+33", stk.pop().toEngineeringString());

	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#Subtract(org.fross.rpncalc.StackObj)}.
	 */
	@Test
	void testSubtract() {
		StackObj stk = new StackObj();

		// Add test data to stack
		stk.push(1.23456);
		stk.push(4.56789);

		// Test #1
		assertEquals(-3.33333, Math.Parse("-", stk).peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #2
		stk.push(-3.12);
		Math.Parse("-", stk);
		StackCommands.cmdRound(stk, "5");
		assertEquals(-0.21333, stk.peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #3
		stk.push(6.678);
		Math.Parse("-", stk);
		StackCommands.cmdRound(stk, "5");
		assertEquals(-6.89133, stk.peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #4 - Scientific Notation
		stk.clear();
		stk.push(123.3222E27);
		stk.push(45.654321e30);
		assertEquals(2, stk.size());
		Math.Parse("-", stk);
		assertEquals("-45.5309988E+30", stk.pop().toEngineeringString());
	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#Multiply(org.fross.rpncalc.StackObj)}.
	 */
	@Test
	void testMultiply() {
		StackObj stk = new StackObj();

		// Add test data to stack
		stk.push(1.23456);
		stk.push(4.56789);

		// Test #1
		Math.Parse("*", stk);
		StackCommands.cmdRound(stk, "5");
		assertEquals(5.63933, stk.peek().doubleValue());

		// Test #2
		stk.push(-99.88);
		Math.Parse("*", stk);
		StackCommands.cmdRound(stk, "5");
		assertEquals(-563.25628, stk.peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #3 - Scientific Notation
		stk.clear();
		stk.push(123.3222E27);
		stk.push(45.654321e30);
		assertEquals(2, stk.size());
		Math.Parse("*", stk);
		assertEquals("5.6301913052262E+60", stk.pop().toEngineeringString());
	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#Divide(org.fross.rpncalc.StackObj)}.
	 */
	@Test
	void testDivide() {
		StackObj stk = new StackObj();

		// Add test data to stack
		stk.clear();
		stk.push(1.23456);
		stk.push(4.56789);

		// Test #1
		Math.Parse("/", stk);
		StackCommands.cmdRound(stk, "7");
		assertEquals(0.2702692, stk.peek().doubleValue());

		// Test #2
		stk.push(-8.554);
		Math.Parse("/", stk);
		StackCommands.cmdRound(stk, "10");
		assertEquals(-0.0315956512, stk.peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #3
		// Make sure we can't divide by zero
		stk.clear();
		stk.push(123.00);
		stk.push(0.0);
		assertEquals(2, stk.size());
		Math.Parse("/", stk);
		assertEquals(2, stk.size());
		assertEquals(0, stk.pop().doubleValue());
		assertEquals(123, stk.pop().doubleValue());

		// Test #4 - Scientific Notation
		stk.clear();
		stk.push(123.3222E27);
		stk.push(45.654321e30);
		assertEquals(2, stk.size());
		Math.Parse("/", stk);
		StackCommands.cmdRound(stk, "20");
		assertEquals("0.00270121638650589065", stk.pop().toEngineeringString());

	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#Power(org.fross.rpncalc.StackObj)}.
	 */
	@Test
	void testPower() {
		StackObj stk = new StackObj();

		// Add test data to stack
		stk.push(1.23456);
		stk.push(4.56789);

		// Test #1
		Math.Parse("^", stk);
		StackCommands.cmdRound(stk, "7");
		assertEquals(2.3229978, stk.peek().doubleValue());

		// Test #2
		stk.push(3.0);
		Math.Parse("^", stk);
		StackCommands.cmdRound(stk, "7");
		assertEquals(12.5356367, stk.peek().doubleValue());
		assertEquals(1, stk.size());

		// Test #3
		stk.clear();
		stk.push(123.456E10);
		stk.push(2.0);
		Math.Parse("^", stk);
		assertEquals("1524138393600000000000000", stk.peek().toPlainString());
		assertEquals("1.5241383936E+24", stk.peek().toEngineeringString());
		assertEquals(1, stk.size());

	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#GreatestCommonDivisor(long, long)}.
	 */
	@Test
	void testGreatestCommonDivisor() {
		assertEquals(10, Math.GreatestCommonDivisor(90, 100));
		assertEquals(3, Math.GreatestCommonDivisor(123, 456));
		assertEquals(3, Math.GreatestCommonDivisor(123, 225));
		assertEquals(35, Math.GreatestCommonDivisor(35, 70));
		assertEquals(2, Math.GreatestCommonDivisor(36, 70));

	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#isNumeric(java.lang.String)}.
	 */
	@Test
	void testIsNumeric() {
		assertTrue(Math.isNumeric("432123434232334.325474458"));
		assertTrue(Math.isNumeric("-123.4567"));
		assertTrue(Math.isNumeric("1"));
		assertTrue(Math.isNumeric(".000002"));
		assertFalse(Math.isNumeric("-123.4a567"));
		assertFalse(Math.isNumeric("Nope"));
		assertFalse(Math.isNumeric("1x22"));
		assertFalse(Math.isNumeric("--123"));
		assertFalse(Math.isNumeric(" "));
		assertFalse(Math.isNumeric(""));
		assertFalse(Math.isNumeric("!"));
		assertFalse(Math.isNumeric("-"));

	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#mean(org.fross.rpncalc.StackObj)}.
	 */
	@Test
	void testMeanIfStackObjIsProvided() {
		StackObj stk = new StackObj();

		// Add test data to stack
		stk.push(1.23456);
		stk.push(4.56789);
		stk.push(10.234);
		stk.push(12.1354);
		stk.push(-1.23);

		assertEquals(5.38837, Math.mean(stk).doubleValue());
	}

	/**
	 * Test method for {@link org.fross.rpncalc.Math#mean(java.lang.Double[])}.
	 */
	@Test
	void testMeanIfDoubleArrayIsProvided() {
		// Test #1
		Double[] arry1 = { 1.23456, 4.56789, 10.234, 12.1354, -1.23 };
		assertEquals(5.38837, Math.mean(arry1).doubleValue());

		// Test #2
		Double[] arry2 = { 1.23456, 4.56789, 10.234, 12.1354, -1.23 };
		assertEquals(5.38837, Math.mean(arry2).doubleValue());

	}

	/**
	 * Test the math median command The cmdMedian method is also tested in the StackCommandsTest file
	 */
	@Test
	void testMedian() {
		StackObj stk = new StackObj();

		// Test #1
		Double[] testValues1 = { -23.11, 55.22, 23.22, -1.01, 4.22, 12.22, 41.01, -0.1, 23.0, 1000.0 };

		// Build the stack
		for (int i = 0; i < testValues1.length; i++) {
			stk.push(testValues1[i]);
		}

		assertEquals(10, stk.size());
		assertEquals(17.61, Math.median(stk).doubleValue());
		assertEquals(10, stk.size());

		// Test #2
		Double[] testValues2 = { 43.39, 26.20739, 87.59777, 55.98073, 36.38447, 39.96893, 93.32821, 74.68383, 14.7644, 79.13016, 94.21511, 38.45116, 89.67177,
				25.71, 70.48159, 57.75962, 80.24972, 82.27109, 8.14497, 75.00809, 22.74851, 85.22599, 29.16305, 85.22427, 56.10867 };

		// Build the stack
		stk.clear();
		for (int i = 0; i < testValues2.length; i++) {
			stk.push(testValues2[i]);
		}

		assertEquals(25, stk.size());
		assertEquals(57.75962, Math.median(stk).doubleValue());
		assertEquals(25, stk.size());
	}

	/**
	 * Test factorial
	 */
	@Test
	void testFactorial() {
		assertEquals(new BigDecimal("24"), Math.factorial(4));
		assertEquals(new BigDecimal("120"), Math.factorial(5));
		assertEquals(new BigDecimal("720"), Math.factorial(6));
		assertEquals(new BigDecimal("5040"), Math.factorial(7));
		assertEquals(new BigDecimal("40320"), Math.factorial(8));
		assertEquals(new BigDecimal("362880"), Math.factorial(9));
		assertEquals(new BigDecimal("3628800"), Math.factorial(10));
		assertEquals(new BigDecimal("39916800"), Math.factorial(11));
		assertEquals(new BigDecimal("479001600"), Math.factorial(12));
		assertEquals(new BigDecimal("6227020800"), Math.factorial(13));
		assertEquals(new BigDecimal("87178291200"), Math.factorial(14));
		assertEquals(new BigDecimal("1307674368000"), Math.factorial(15));
		assertEquals(new BigDecimal("20922789888000"), Math.factorial(16));
		assertEquals(new BigDecimal("355687428096000"), Math.factorial(17));
		assertEquals(new BigDecimal("6402373705728000"), Math.factorial(18));
		assertEquals(new BigDecimal("121645100408832000"), Math.factorial(19));
		assertEquals(new BigDecimal("2432902008176640000"), Math.factorial(20));
	}

}
