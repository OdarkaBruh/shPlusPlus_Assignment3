package com.shpp.p2p.cs.dnokhrina.assignment3;

// Manages linear movement of X
public class LinearMovement {
    final double STEP = 3.5; //X step

    /*  The circle accelerates depending on the distance to the center, if  SPEED_UP = true
        It's pretty subtle, so you can experiment with the SPEED_UP_STEP_SPEED */
    final boolean SPEED_UP = true;
    final double SPEED_UP_STEP_SPEED = (double) 2 / 100;

    double[] xRange;
    double currentX;
    boolean moveRight = true;

    LinearMovement(double xFrom, double xTo) {
        xRange = new double[]{xFrom, xTo};
        currentX = xFrom;
    }

    //main managing method for movement
    double move() {
        //if moves left => subtract (not add) step.
        currentX = currentX + (moveRight ? STEP : -STEP);

        //if speeds up => add acceleration
        if (SPEED_UP) {
            /*  currentX-xRange[0]           <=== how far from left wall
                xRange[1] - currentX         <=== how far from right wall

                Need the minimum value of two because it will be closest half towards the center
                Additionally, you can switch "Math.min" to "Math.max" and now it will be accelerating towards walls */
            double distanceToClosestWall = Math.min(currentX-xRange[0],xRange[1]-currentX);

            currentX += (moveRight ? distanceToClosestWall : -distanceToClosestWall) * SPEED_UP_STEP_SPEED;
        }

        //if inside range => just return it.
        if (currentX > xRange[0] && currentX < xRange[1]) return currentX;

        //otherwise, return it to the valid range and reverse its direction.
        else if (currentX < xRange[0]) currentX = xRange[0];
        else if (currentX > xRange[1]) currentX = xRange[1];
        moveRight = !moveRight;
        return currentX;
    }

    //saves the coordinates, calls to calculate new coordinates, and returns the difference
    public double getDeltaX() {
        double oldX = currentX;
        return (move() - oldX);
    }
}
