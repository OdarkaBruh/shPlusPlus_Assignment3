package com.shpp.p2p.cs.dnokhrina.assignment3;

import acm.graphics.GOval;

import java.awt.*;

//The main circle which changes color and moves.
public class CircleMoving extends DrawShapes {
    final boolean LEAVE_TRAIL = true; // aka colored dots
    final int TRAIL_SIZE = 2;

    /*  I haven't found a better way to call "addShape()". I can't make method static, and
        I can't call "add()" (or I can, but it won't be added).     */
    Assignment3Part6 parent;

    double circleDiameter;
    GOval circle;

    /*  X-Movement will be linear, gradually accelerating toward the center and
        then slowing down to a standard speed as it approaches the walls.      (...is it still linear movement if it's accelerating...) */
    LinearMovement movementX;
    // The Y-Movement will look like a sine graph.
    SinMovement movementY;

    Color currentColor;
    CalculateColor colorManager;

    CircleMoving(double xFrom, double xTo, double yFrom, double yTo, double circleDiameter, Assignment3Part6 assignment3Part6) {
        parent = assignment3Part6; //just to call addShape()
        createCircle(xFrom, yFrom, circleDiameter);

        /*  minus the diameter of the circle, because its coordinates start from the top-right corner =>
            => he will go outside the boundaries   */
        movementX = new LinearMovement(xFrom, xTo-circleDiameter);
        movementY = new SinMovement(yFrom, yTo-circleDiameter);

        colorManager = new CalculateColor(xFrom, xTo-circleDiameter, yFrom-circleDiameter, yTo);
    }

    //create circle of given diameter and at given coordinated.
    void createCircle(double xFrom, double yFrom, double circleDiameter) {
        this.circleDiameter = circleDiameter;

        circle = drawCircle(xFrom, yFrom, circleDiameter, circleDiameter);
        circle.setFilled(true);
        circle.setVisible(true);
    }

    //moves the circle and calls to change color.
    void move() {
        //get delta coordinates from each movement and move it.
        double deltaX = movementX.getDeltaX();
        double deltaY = movementY.getDeltaY();
        circle.move(deltaX, deltaY);

        //Call to change color
        shiftColor(movementX.currentX, movementY.currentY);

        //and call to leave trail if needed
        if (LEAVE_TRAIL) leave_trail();
    }

    //Calls colorManager to get new color and sets it.
    void shiftColor(double x, double y){
        currentColor = colorManager.getColor(x, y);
        circle.setFillColor(currentColor);
        circle.setColor(currentColor);
    }

    //The coordinates start in the top-left corner of the circle => add an offset so that the shape is centered.
    final double trailOffset = TRAIL_SIZE + circleDiameter
            + (Assignment3Part6.ALL_PADDINGS) ; //and add offsets
    void leave_trail(){
        parent.addShape(drawCircle(movementX.currentX+trailOffset/2, movementY.currentY+trailOffset+trailOffset/2,
                TRAIL_SIZE, TRAIL_SIZE, currentColor, currentColor));
    }
}
