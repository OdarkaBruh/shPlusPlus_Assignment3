package com.shpp.p2p.cs.dnokhrina.assignment3;

//  *insert sine graph*, but now it's Y coordinates
public class SinMovement {
    /* Can change this two:
        "Sin" determines the starting position and later keeps new angle value.
        "sinStep" determines how “wavy” the line is. The higher the value, the wavier the line will be (⁓)      */
    double sin = 0.0;
    final double sinStep = 4.0 / 100;

    double currentY = 0;
    double heightRestriction; //or, more precisely, half of it, because of how the sine function works.

    SinMovement(double yFrom, double yTo){
        heightRestriction = (yTo - yFrom)/2;
    }

    /* I won't rant too much about how the sine function works. Those who checked the previous assignment got enough:
       If you're really curious, here's an explanation from my previous work: https://docs.google.com/document/d/1DNAigtZIlYE0To9kXEcvb9tF94TUP1CeZquODlxiSCM/edit?usp=sharing

       Simply put, the Math.sin function returns a value between 1 and -1 => multiply by heightRestriction =>
       => now it returns a value between +heightRestriction and -heightRestriction
       => add heightRestriction => now it returns between 2*heightRestriction and 0 =>
       => It won't escape the top of the screen.    */
    public double getNextCoordinate(){
        currentY = heightRestriction * Math.sin(sin) + heightRestriction;
        sin+=sinStep;
        return currentY;
    }

    //saves the coordinates, calls to calculate new coordinates, and returns the difference
    public double getDeltaY() {
        double oldValue = currentY;
        return getNextCoordinate() - oldValue;
    }
}
