package com.shpp.p2p.cs.dnokhrina.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;

import java.awt.*;

//      Task: 5 seconds of any animation
public class Assignment3Part6 extends AnimateShapes {
    // The window's parameters
    public static final int APPLICATION_WIDTH = 960;
    public static final int APPLICATION_HEIGHT = 700;

    private static final double ANIMATION_DURATION = 1000.0 * 1;
    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 30;

    static final Color SQUAREOO_COLOR = new Color(127,89,176);
    static final Color SQUAREO1_COLOR = new Color(99,197,234);
    static final Color SQUARE1O_COLOR = new Color(234,75,139);
    static final Color SQUARE11_COLOR = new Color(252,180,36);

    static final short CIRCLE_DIAMETER = 50;

    static final int PADDING = CIRCLE_DIAMETER / 2;
    static final int INVISIBLE_CIRCLE_PADDING = PADDING;
    static final int ALL_PADDINGS = PADDING+INVISIBLE_CIRCLE_PADDING;

    static double startTime;

    public void run() {
        drawDefaultBackground();
        int middleX = getWidth() / 2;
        int middleY = getHeight() / 2;

        int squareWidth = middleX-PADDING;
        int squareHeight = middleY-PADDING;

        //final GOval ball = drawCircle(100,100,100,100, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        final GRect square00 = drawRect(PADDING, PADDING, squareWidth, squareHeight, SQUAREOO_COLOR);
        final GRect square01 = drawRect(middleX, PADDING, squareWidth, squareHeight, SQUAREO1_COLOR);
        final GRect square10 = drawRect(PADDING, middleY, squareWidth, squareHeight, SQUARE1O_COLOR);
        final GRect square11 = drawRect(middleX, middleY, squareWidth, squareHeight, SQUARE11_COLOR);

        final GOval ball = drawCircle(PADDING, PADDING, CIRCLE_DIAMETER, CIRCLE_DIAMETER, Color.white, Color.white);

        final GOval startDot = drawCircle(PADDING, middleY-CIRCLE_DIAMETER/2, 2, 2, Color.red, Color.red);
        final GOval endDot = drawCircle(getWidth()-PADDING-CIRCLE_DIAMETER, middleY-CIRCLE_DIAMETER/2, 2, 2, Color.red, Color.red);

        startTime = System.currentTimeMillis();
        drawDotsLine(middleY);
    }

    void idleAnimation(GOval ball) {
        System.out.println("Started!");
        int i = 0;
        while ((System.currentTimeMillis() - startTime) < ANIMATION_DURATION) {
            i += 1;
            ball.move(0, 1);
            pause(PAUSE_TIME);
            System.out.println(i + " " + (System.currentTimeMillis() - startTime));
        }
        System.out.println(System.currentTimeMillis());
    }

    void drawDotsLine(int middleY){
        final int offsetX = CIRCLE_DIAMETER + CIRCLE_DIAMETER/2;
        final int stepX = 5;
        final int dotDiameter = 2;

        final int moveZoneY = ((getHeight())/2);
        int currentX = PADDING + CIRCLE_DIAMETER/2;
        double sin = 0.0;
        double sinStep = 0.6 / 10;
        while (currentX < getWidth() - PADDING - CIRCLE_DIAMETER - CIRCLE_DIAMETER/2) {
            double offsetY = Math.sin(sin)*(moveZoneY-ALL_PADDINGS - (double) CIRCLE_DIAMETER /2);
            System.out.println(offsetY);
            drawCircle(currentX, moveZoneY+ALL_PADDINGS-CIRCLE_DIAMETER-CIRCLE_DIAMETER/2+offsetY, CIRCLE_DIAMETER, CIRCLE_DIAMETER, Color.white, Color.WHITE);
            currentX+=stepX;
            sin+=sinStep;
        }
    }

    GRect drawRect(double x, double y, double w, double h, Color c) {
        return drawRect(x, y, w, h, c, c);
    }
}