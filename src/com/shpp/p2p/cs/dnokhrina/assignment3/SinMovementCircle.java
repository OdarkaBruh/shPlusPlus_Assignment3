package com.shpp.p2p.cs.dnokhrina.assignment3;

import acm.graphics.GOval;

import java.awt.*;

public class SinMovementCircle extends DrawShapes {
    double circleDiameter;
    GOval circle;

    SinMovement sinMove;

    SinMovementCircle(double windowWidth, double windowHeight, double circleDiameter, double addOffsetFromEdge) {
        this.circleDiameter = circleDiameter;
        circle = drawCircleThick(addOffsetFromEdge, addOffsetFromEdge, circleDiameter, circleDiameter, Color.LIGHT_GRAY,  Color.WHITE, 1);

        sinMove = new SinMovement(addOffsetFromEdge, windowWidth - addOffsetFromEdge,
                addOffsetFromEdge, windowHeight - addOffsetFromEdge);

        sinMove.setOffsets(circleDiameter, circleDiameter);
    }
    public void setColorCircle(Color color) {
        circle.setColor(color);
    }

    public boolean moveCircle() {
        if  (sinMove.currentX < sinMove.xTo) circle.move(sinMove.getDeltaX(),sinMove.getDeltaY());
        else return false;
        return true;
    }
}
