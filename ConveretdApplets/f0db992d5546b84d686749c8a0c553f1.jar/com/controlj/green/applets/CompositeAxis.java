// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.awt.Component;
import java.awt.Graphics;
import com.controlj.green.applets.draw.Range;
import java.util.Vector;

public class CompositeAxis extends Axis
{
    private Vector rangeList;
    
    public CompositeAxis() {
        this.rangeList = new Vector(4);
    }
    
    public CompositeAxis(final Range range) {
        super(range);
        this.rangeList = new Vector(4);
    }
    
    public void addRange(final Range range) {
        this.rangeList.addElement(range);
    }
    
    public void removeRange(final Range range) {
        this.rangeList.removeElement(range);
    }
    
    public Range getRange(final int i) {
        return this.rangeList.elementAt(i);
    }
    
    public int numRanges() {
        return this.rangeList.size();
    }
    
    public int calculateBreadth(final Graphics g, final int minBreadth) {
        this.synchRanges();
        return super.calculateBreadth(g, minBreadth);
    }
    
    public void drawAxis(final Graphics g, final Component g2d, final int x, final int y, final int axisLen, final int gridLength) {
        this.synchRanges();
        super.drawAxis(g, g2d, x, y, axisLen, gridLength);
    }
    
    private void synchRanges() {
        for (int i = 0; i < this.rangeList.size(); ++i) {
            this.getRange(i).syncScale(this.getRange());
        }
    }
}
