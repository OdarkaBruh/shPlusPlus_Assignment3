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
    private static final double PAUSE_TIME = 1000.0 / 48;

    static final short CIRCLE_DIAMETER = 50;

    static final int PADDING = CIRCLE_DIAMETER / 2;
    static final int INVISIBLE_CIRCLE_PADDING = PADDING;

    static double startTime;

    public void run() {
        drawBackground();

        final GOval ball = drawCircle(PADDING, PADDING, CIRCLE_DIAMETER, CIRCLE_DIAMETER, Color.white, Color.white);

        startTime = System.currentTimeMillis();
        drawDots();
        //drawDotsLine(middleY);
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
    void drawDots() {
        SinMovementCircle c = new SinMovementCircle(getWidth(), getHeight(), CIRCLE_DIAMETER, INVISIBLE_CIRCLE_PADDING);
        add(c.circle);
        c.circle.sendToFront();
        c.circle.setVisible(true);

        while (c.moveCircle()){
            pause(PAUSE_TIME);
        }
    }
    private void drawBackground(){
        drawDefaultBackground();

        int middleX = getWidth() / 2;
        int middleY = getHeight() / 2;

        int squareWidth = middleX-PADDING;
        int squareHeight = middleY-PADDING;

        final GRect square00 = drawRect(PADDING, PADDING, squareWidth, squareHeight, CalculateColor.COLOR_LEFT_UP);
        final GRect square01 = drawRect(middleX, PADDING, squareWidth, squareHeight, CalculateColor.COLOR_RIGHT_UP);
        final GRect square10 = drawRect(PADDING, middleY, squareWidth, squareHeight, CalculateColor.COLOR_LEFT_DOWN);
        final GRect square11 = drawRect(middleX, middleY, squareWidth, squareHeight, CalculateColor.COLOR_RIGHT_DOWN);
    }

    GRect drawRect(double x, double y, double w, double h, Color c) {
        return drawRect(x, y, w, h, c, c);
    }
}