package com.shpp.p2p.cs.dnokhrina.assignment3;

import java.awt.*;

import com.shpp.cs.a.graphics.WindowProgram;

import acm.graphics.*;

public class DrawShapes extends WindowProgram{
    //draw a circle (not filled, black outline)
    public GOval drawCircle(double x, double y, double width, double height) {
        GOval circle = new GOval(x, y, width, height);
        add(circle);
        return circle;
    }

    //draw a circle (filled with color, but black outline)
    public GOval drawCircle(double x, double y, double width, double height, Color c) {
        GOval circle = drawCircle(x, y, width, height);
        circle.setFilled(true);
        circle.setFillColor(c);
        return circle;
    }

    //draw a circle (filled with color, colored outline)
    public GOval drawCircle(double x, double y, double width, double height, Color mainColor, Color borderColor) {
        GOval circle = drawCircle(x, y, width, height, mainColor);
        circle.setColor(borderColor);
        return circle;
    }

    //Didn't found method to set up border's width, so this method is implementing this
    //by drawing a slightly larger circle filled with border color before drawing the main one.
    public void drawCircleThick(double x, double y, double width, double height, int offset) {
        drawCircle(x-offset, y-offset, width+offset*2, height+offset*2);
        drawCircle(x, y, width, height);
    }

    public void drawCircleThick(double x, double y, double width, double height, Color mainColor, Color borderColor, int offset) {
        drawCircle(x-offset, y-offset, width+offset*2, height+offset*2, borderColor, borderColor);
        drawCircle(x, y, width, height, mainColor, borderColor);
    }

    //Draw an outline of rect (not filled, black border)
    //Name is the same, but one less argument (no color)
    public GRect drawRect(double x, double y, double  w, double h) {
        GRect rect = new GRect(x, y, w, h);
        add(rect);
        return rect;
    }

    //Draw a rect filled with color
    public GRect drawRect(double x, double y, double  w, double h, Color mainColor, Color borderColor) {
        GRect rect = drawRect(x, y, w, h);
        rect.setColor(borderColor);
        rect.setFilled(true);
        rect.setFillColor(mainColor);
        return rect;
    }

    public void drawBackground(Color c){
        drawRect(0,0,getWidth(), getHeight(), c, c);
    }
    public void drawDefaultBackground(){
        drawBackground(new Color(236, 226,206));
    }

}
