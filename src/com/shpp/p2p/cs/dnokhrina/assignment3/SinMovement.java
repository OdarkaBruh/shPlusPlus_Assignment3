package com.shpp.p2p.cs.dnokhrina.assignment3;

public class SinMovement {
    double currentX = 0;
    double xStep = 1;

    double currentY = 0;

    double xFrom = 0;
    double xTo = 0;
    double yFrom = 0;
    double yTo = 0;

    double sin = 0.0;
    double sinStep = 0.125 / 10;

    double xOffset = -1;
    double yOffset = -1;

    SinMovement(double xFrom, double xTo, double yFrom, double yTo){
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
    }
    public void setOffsets (double xOffset, double yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;

        this.xTo -= xOffset;
        this.yTo -= yOffset;

        currentX+=xOffset;
        currentY+=yOffset;
    }


    public double getNextX(){
        currentX += xStep;
        return currentX;
    }

    public double getNextY(){
        currentY = ((double) (yTo - yFrom)) /2 * Math.sin(sin) + (double)(yTo - yFrom) /2 + (yOffset != -1 ? yOffset : 0);
        sin+=sinStep;
        return currentY;
    }

    public double getDeltaX() {
        double oldX = currentX;
        return getNextX() - oldX;
    }

    public double getDeltaY() {
        double oldY = currentY;
        return getNextY() - oldY;
    }
}
