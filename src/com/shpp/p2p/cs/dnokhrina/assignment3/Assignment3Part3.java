package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.util.Scanner;

//      Task: Elevation to a power
public class Assignment3Part3 {
    static final Scanner SCANNER = new Scanner(System.in);

    boolean testingMode = false;
    /*  if testingMode = false, base and exponent will get new values from input.
        if testingMode = true, values specified below will be used.         */

    static double base = 0;
    static int exponent = 0;

    private static double raiseToPower(double base, int exponent) {
        boolean exponentIsNegative = false;
        double result = 1;

        //Filter "edge cases"
        if (exponent == 0) return 1;
        else if (base == 0) return 0;

        /*  Even if exponent is negative - make it positive and continue as normal.
        The result will be flipped in the end to account for the fact that the exponent is negative         */
        else if (exponent < 0) {
            exponentIsNegative = true;
            exponent = -exponent;
        }

        for (int i = 0; i < exponent; i++) result *= base;

        if (exponentIsNegative) result = 1 / result;
        return result;
    }

    void main(String[] args) {
        if (!testingMode) {
            //Get base and exponent from input
            scanBase();
            scanExponent();
        }
        System.out.print("Result: " + raiseToPower(base, exponent));
    }

    public static void scanBase() {
        System.out.print("base = ");
        base = SCANNER.nextDouble();
    }

    public static void scanExponent() {
        System.out.print("exponent = ");
        exponent = SCANNER.nextInt();
    }
}