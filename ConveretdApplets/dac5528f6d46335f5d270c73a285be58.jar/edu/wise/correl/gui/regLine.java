// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.correl.gui;

import java.awt.Color;
import java.awt.Graphics;

public class regLine
{
    protected int regType;
    int x1;
    int x2;
    int y1;
    int y2;
    protected double slope;
    protected double intercept;
    protected static boolean active;
    
    regLine() {
        this(1, 1, 2, 2);
    }
    
    regLine(final int[] array) {
        this(array[0], array[1], array[2], array[3]);
    }
    
    regLine(final int x1, final int y1, final int x2, final int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.slope = -999.0;
        this.intercept = -999.0;
        this.screenLine(x1, y1, x2, y2);
    }
    
    public boolean gotFocus(final int n, final int n2) {
        return regLine.active = this.inside(n, n2);
    }
    
    private boolean inside(final int n, final int n2) {
        return Math.abs(n2 - this.predY(n)) < 5;
    }
    
    private void screenLine(final int n, final int n2, final int n3, final int n4) {
        this.screenLine(new int[] { n, n2, n3, n4 });
    }
    
    private void screenLine(final int[] array) {
        this.slope = (array[3] - array[1]) / (array[2] - array[0]);
        this.intercept = -array[0] + array[1];
    }
    
    public int predY(final int n) {
        return this.predY((double)n);
    }
    
    public int predY(final double n) {
        return (int)Math.round(this.slope * n + this.intercept);
    }
    
    public void update(final int[] array) {
        this.update(array[0], array[1], array[2], array[3]);
    }
    
    public void update(final int x1, final int y1, final int x2, final int y2) {
        this.screenLine(this.x1 = x1, this.y1 = y1, this.x2 = x2, this.y2 = y2);
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.x1, this.y1, this.x2, this.y2);
    }
    
    public void paint(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.setColor(Color.black);
        if (!regLine.active) {
            graphics.drawLine(n, n2, n3, n4);
        }
        else {
            graphics.drawLine(n, n2, n3, n4);
            graphics.drawLine(n, n2 - 1, n3, n4 - 1);
        }
    }
    
    public String toString() {
        return "Y' = " + this.slope + " X +" + this.intercept + " active=" + regLine.active + ", coords={" + this.x1 + ", " + this.y1 + ", " + this.x2 + ", " + this.y2 + "}";
    }
    
    public boolean getActive() {
        return regLine.active;
    }
    
    public void setActive(final boolean active) {
        regLine.active = active;
    }
}
