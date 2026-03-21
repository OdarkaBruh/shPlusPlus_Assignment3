package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.util.Scanner;

/*      Task:
            Take any positive integer and call it n
                If n is even, divide it by 2
                If n is odd, multiply it by 3 and add 1
            Continue this process until n equals 1
 */
public class Assignment3Part2 {
    static final Scanner SCANNER = new Scanner(System.in);
    static int numberN;

    public static void main(String[] args) {
        scanN();

        //Check if inputted N is a positive number
        while (numberN < 1) {
            System.out.println("N must be a positive number");
            scanN();
        }
        SCANNER.close();

        //Follow the given instructions depending on whether n is even or odd, until n == 1
        while (numberN != 1) {
            if (numberN % 2 == 0) numberIsEven();
            else numberIsOdd();
        }
        System.out.print("n = " + numberN);
    }

    //Scan input for n
    public static void scanN() {
        System.out.print("n = ");
        numberN = SCANNER.nextInt();
    }

    //n = (2, 4, 6...)
    public static void numberIsEven() {
        System.out.print(numberN + " - even, so divide by 2, and the result is ");
        numberN = numberN / 2;
        System.out.println(numberN);
    }

    //n = (3, 5, 7...)
    public static void numberIsOdd() {
        System.out.print(numberN + " - odd, so multiply by 3 and add 1, and the result is ");
        numberN = numberN * 3 + 1;
        System.out.println(numberN);
    }
}