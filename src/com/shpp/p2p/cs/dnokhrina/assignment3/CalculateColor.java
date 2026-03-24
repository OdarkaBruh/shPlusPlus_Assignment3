package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.awt.*;

/* General Algorithm of change:
    R-value will change depending on X: The further to the right the circle shifts, the greater the change in R.
    G-value will change depending on Y: The further down the circle shifts, the greater the change in G.

    But it's a 2D program, so I've run out of axes to which I can map the values. But if you've seen any video about 4D,
    we still have time, so now the B-value corresponds to time. */
public class CalculateColor extends DrawShapes {
    /* Technically, RGB values must be between 0 and 255, but you can always experiment and set them,
        for example, between 100 and 200. But any value less than 0 or greater than 255
        will cause the program to crash, because that's how RGB works.

        This may cause flashing colors, so if that's a trigger for you, set the range to {150,255}.
        The color change will be less noticeable, but much easier on the eyes :'*/
    final int[] R_VALUE_RANGE = {0, 255};
    final int[] G_VALUE_RANGE = {0, 255};
    final int[] B_VALUE_RANGE = {0, 255};

    /*  These variables are for the B-value (i.e., time), but we will use the number of times the function was called
        rather than the time in milliseconds.*/
    final int[] counterValueRange = {1, 100}; //determines how quickly the value of B will change from min to max
    final double colorStepThird = (double) (B_VALUE_RANGE[1] - B_VALUE_RANGE[0])
            / (counterValueRange[1] - counterValueRange[0]); //How much B-value will change per 1 call.
    double counterCurrentValue = counterValueRange[0];
    boolean reverseValue = false; //do we need to go back (i.e. from max to min)?

    double colorStepX; // How much R-value will change per 1 coordinate.
    double colorStepY; // How much G-value will change per 1 coordinate.

    //They work as offset (so that the color change is only applied within the area that the circle can reach)
    double xCoordinateStartFrom;
    double yCoordinateStartFrom;

    CalculateColor(double fromX, double toX, double fromY, double toY) {
        xCoordinateStartFrom = fromX;
        yCoordinateStartFrom = fromY;

        colorStepX = (double) (R_VALUE_RANGE[1] - R_VALUE_RANGE[0]) / (toX - fromX);
        colorStepY = (double) (G_VALUE_RANGE[1] - G_VALUE_RANGE[0]) / (toY - fromY);
    }

    Color getColor(double currentX, double currentY){
        //Just multiply shifted to (0;0) coordinates with how much color changes per 1 pixel.
        int rValue = (int) ((currentX - xCoordinateStartFrom) * colorStepX);
        int gValue = (int) ((currentY - yCoordinateStartFrom) * colorStepY);

        //multiply the counter value and the amount by which B should be changed on each call (colorStep)
        int bValue = (int) (counterCurrentValue*colorStepThird);

        //If B is outside the range => move it back and reverse the movement direction.
        if (bValue < R_VALUE_RANGE[0]) {
            bValue = R_VALUE_RANGE[0];
            reverseValue = !reverseValue;
        } else if (bValue > R_VALUE_RANGE[1]) {
            bValue = R_VALUE_RANGE[1];
            reverseValue = !reverseValue;
        }

        /*  if "reverseValue == true" => counter is increasing => add one to it.
            if false => it's decreasing => subtract    */
        counterCurrentValue += (!reverseValue ? 1 : -1);
        return new Color(rValue, gValue, bValue);
    }
}
