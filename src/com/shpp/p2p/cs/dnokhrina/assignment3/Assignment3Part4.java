package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.awt.*;

/*      Task: So, you'll need to build a pyramid out of bricks.
              Each row contains one fewer brick than the one below it.*/
public class Assignment3Part4 extends DrawShapes {
    // The window's parameters
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 500;

    final int BRICK_HEIGHT = 15;
    final int BRICK_WIDTH = 20;
    final int BRICKS_IN_BASE = 5;

    public void run() {
        drawPyramid(BRICKS_IN_BASE);
    }

    //draw a pyramid
    public void drawPyramid(int brickInBase) {
        for (int i = brickInBase; i > 0; i--) { //from bottom to top
            /*  to center the pyramid vertically:
                y = height of the window - height of all rows below it.
                (i-1) since the coordinates are counted from the top corner ==> need to leave space for the first row.
            */
            int y = getHeight() - BRICK_HEIGHT * (BRICKS_IN_BASE - (i - 1)) - 1;
            drawRow(y, i);
        }
    }

    //draw a row
    public void drawRow(int y, int amountOfBricks) {
        int x = (getWidth() - amountOfBricks * BRICK_WIDTH) / 2;
        for (int i = 0; i < amountOfBricks; i++) drawBrick(x + BRICK_WIDTH * i, y);
    }

    //draw a brick
    public void drawBrick(int x, int y) {
        drawRect(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.BLACK, Color.white);
    }
}