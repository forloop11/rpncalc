/******************************************************************************
 * rpn.java
 * 
 * A simple console based RPN calculator with an optional persistent stack.
 * 
 *  Written by Michael Fross.  Copyright 2011-2019.  All rights reserved.
 *  
 *  License: GNU General Public License v3.
 *           http://www.gnu.org/licenses/gpl-3.0.html
 *           
 ******************************************************************************/
package org.fross.rpn;

import com.diogonunes.jcdp.color.api.Ansi.FColor;

/**
 * Help(): Display the help page when users enters 'h' command.
 * 
 * @author michael.d.fross
 *
 */
public class Help {
	/**
	 * Display(): Prints help in color using the JCDP library in the output module.
	 */
	public static void Display() {
		Output.printColorln(FColor.YELLOW,
				"\n+----------------------------------------------------------------------+");
		Output.printColorln(FColor.YELLOW,
				"+              RPN Calculator v" + Main.VERSION + "  Help Document               +");
		Output.printColorln(FColor.YELLOW, "+----------------------------------------------------------------------+");
		Output.printColorln(FColor.WHITE, "            RPN is a simple reverse polish notation calculator");
		Output.printColorln(FColor.WHITE, "               https://bitbucket.org/frossm/rpn/src/default\n");

		Output.printColorln(FColor.YELLOW, "Command Line Options:");
		Output.printColorln(FColor.WHITE, " -l     Load saved named stack. Create the stack if it does not exist");
		Output.printColorln(FColor.WHITE, " -D     Start in debug mode.  Same as using the 'debug' command\n");

		Output.printColorln(FColor.YELLOW, "Operands:");
		Output.printColorln(FColor.WHITE, " +      Addition:  Add last two stack elements");
		Output.printColorln(FColor.WHITE, " -      Subtraction: Subtract last element from previous element");
		Output.printColorln(FColor.WHITE, " *      Multiplication: Muliply last two stack items");
		Output.printColorln(FColor.WHITE, " /      Division: Divide second to last item by last stack item");
		Output.printColorln(FColor.WHITE, " ^      Power:  Calculate second to last item to the power of the last item");
		Output.printColorln(FColor.WHITE, " %      Percent: Turn the last stack item into a percentage (multiplied by 0.01)");

		Output.printColorln(FColor.YELLOW, "Commands:");
		Output.printColorln(FColor.WHITE, " s      Change sign of last element");
		Output.printColorln(FColor.WHITE, " c      Clear everything from the visible stack");
		Output.printColorln(FColor.WHITE, " d      Delete the last item in the stack");
		Output.printColorln(FColor.WHITE, " f      Flip last two stack items");
		Output.printColorln(FColor.WHITE, " copy   Copy the item at the top of the stack");
		Output.printColorln(FColor.WHITE, " pi     Insert the value of PI onto the end of the stack");
		Output.printColorln(FColor.WHITE, " sqrt   Perform a square root on the last stack number\n");
		Output.printColorln(FColor.WHITE, " ss     Swap primary stack to secondary.  You can swap them back at a later time");
		Output.printColorln(FColor.WHITE, " load   Load a saved named stack. It will be created if it doesn't exist");
		Output.printColorln(FColor.WHITE, " debug  Toggle DEBUG mode on/off");
		Output.printColorln(FColor.WHITE, " h|?    Show this help information.  Either key will work.");
		Output.printColorln(FColor.WHITE, " x      Exit Calculator\n");

		Output.printColorln(FColor.WHITE, "Note: You can place an operand at the end of a number and execute in one step.");
		Output.printColorln(FColor.WHITE, "For Example:  To add two numbers:   2 <enter> 3+ <enter>   will produce 5.\n");
	}
}