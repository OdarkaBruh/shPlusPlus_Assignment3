package com.shpp.p2p.cs.dnokhrina.assignment3;

import acm.graphics.GOval;

import java.awt.*;

//      Task: 5 seconds of any animation
public class Assignment3Part6 extends DrawShapes {
    // The window's parameters
    public static final int APPLICATION_WIDTH = 960;
    public static final int APPLICATION_HEIGHT = 700;

    private static final double ANIMATION_DURATION = 1000.0 * 5;
    /* The amount of time to pause between frames (48fps). */
    private static final double PAUSE_TIME = 1000.0 / 48;
    private static final boolean STOP_WHEN_TIMER_RUNS_OUT = true;

    static final double CIRCLE_DIAMETER = 50;

    static final double PADDING = CIRCLE_DIAMETER / 2;
    static final double INVISIBLE_PADDING = CIRCLE_DIAMETER / 2;
    static final double ALL_PADDINGS = PADDING+INVISIBLE_PADDING;

    public void run() {
        drawBackground();
        drawMainCircle();
    }

    void drawMainCircle() {
        /*create the main circle
            Its movement are restricted by padding (yellow background) +
            + invisible padding inside white rectangle (by default, the same as padding)        */
        CircleMoving c = new CircleMoving(ALL_PADDINGS, getWidth() - ALL_PADDINGS,
                ALL_PADDINGS, getHeight() - ALL_PADDINGS, CIRCLE_DIAMETER, this);
        add(c.circle);

        //Start tracking start time.
        double startTime = System.currentTimeMillis();
        System.out.println("Timer started!");

        //Move as long as time in range, or it's test mode.
        do {
            c.move();
            pause(PAUSE_TIME);
        } while ((System.currentTimeMillis()-startTime < ANIMATION_DURATION) || !STOP_WHEN_TIMER_RUNS_OUT);

        //End time to check task's condition.
        double endTime = System.currentTimeMillis();
        System.out.println("Timer finished! It took: " + (endTime - startTime));
    }

    //Parent method for all background methods.
    private void drawBackground() {
        drawDefaultBackground(); //yellow "border"

        //The size of white area (aka where circle will be moving)
        double backgroundAreaX = getWidth()-PADDING*2;
        double backgroundAreaY = getHeight()-PADDING*2;

        //Draw the white area which will be filled with lines
        final Color mainBackgroundColor = new Color(255, 255, 255);
        drawRect(PADDING, PADDING, backgroundAreaX, backgroundAreaY, mainBackgroundColor, mainBackgroundColor); //Background for a background :D

        /*  Draw horizontal lines and then vertical. This method can be confusing , so to simplify:
                Imagine a rectangle that is 1 wide and 20 high.
                Now, just draw at the same coordinated a rectangle that is 20 wide and 1 high.

                The result is the same rectangle, but rotated. Now add a loop to draw other lines,
                and you'll get this method.         */
        drawGridLine(backgroundAreaX, backgroundAreaY, true);
        drawGridLine(backgroundAreaY, backgroundAreaX, false);
    }

    //Draws grid lines for a background.
    void drawGridLine(double areaWidth, double lineLength, boolean isVertical) {
        final Color GRID_COLOR = new Color(221, 221, 221, 100);
        final int GRID_LINE_WIDTH = 3;
        final int BETWEEN_LINES = GRID_LINE_WIDTH*10;
        //  You can change values above, if you want.

        /*  The number of "BETWEEN_LINES" will be 1 less than lines,
            and I haven't found a better solution than adding one "BETWEEN_LINES"
            on opposite side (areaWidth) to compensate for it        */
        final int NUMBER_OF_LINES = (int) ((areaWidth+BETWEEN_LINES)/(BETWEEN_LINES+GRID_LINE_WIDTH));

        //  the first offset, so that the lines are centered (the first and last offsets will be smaller than the others
        final double START_OFFSET = PADDING + (areaWidth+BETWEEN_LINES)%(BETWEEN_LINES+GRID_LINE_WIDTH)/2;

        /*  draws rows vertically (if...) or horizontally (else).
            To switch orientation, just swap x <=> y coordinates and line width <=> line height.  */
        for (int i = 0; i < NUMBER_OF_LINES; i++) {
            double nextRow = START_OFFSET + (BETWEEN_LINES+GRID_LINE_WIDTH)*i; //get new coordinates

            /* You need to pass ( "nextRow", GRID_LINE_WIDTH, lineLength )
                at different spots, depending on orientation.*/
            if (isVertical) drawRect(nextRow, PADDING, GRID_LINE_WIDTH, lineLength, GRID_COLOR, GRID_COLOR);
            else drawRect(PADDING, nextRow, lineLength, GRID_LINE_WIDTH, GRID_COLOR, GRID_COLOR);
        }
    }

    //Calling from another method doesn't work :'
    public void addShape(GOval oval){
        add(oval);
    }
}