// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.graph;

import java.awt.Panel;

public class HotSpot extends Panel
{
    protected int xmin;
    protected int xmax;
    protected int ymin;
    protected int ymax;
    
    public boolean inside(final int n, final int n2) {
        return n >= this.xmin && n <= this.xmax && n2 >= this.ymin && n2 <= this.ymax;
    }
    
    public void set(final int xmin, final int ymin, final int n, final int n2) {
        this.xmin = xmin;
        this.xmax = xmin + n;
        this.ymin = ymin;
        this.ymax = ymin + n2;
    }
    
    public int getXMin() {
        return this.xmin;
    }
    
    public int getYMin() {
        return this.ymin;
    }
    
    public int getXMax() {
        return this.xmax;
    }
    
    public int getYMax() {
        return this.ymax;
    }
    
    public int getMidPt_x() {
        return this.xmin + Math.round((this.xmax - this.xmin) / 2);
    }
    
    public int getMidPt_y() {
        return this.ymin + Math.round((this.ymax - this.ymin) / 2);
    }
    
    public String toString() {
        return this.getYMin() + ", " + this.getMidPt_y() + ", " + this.getXMax();
    }
}
