// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import org.jfree.text.TextBox;
import java.io.Serializable;

public class PieLabelRecord implements Comparable, Serializable
{
    private Comparable key;
    private double angle;
    private double baseY;
    private double allocatedY;
    private TextBox label;
    private double labelHeight;
    private double gap;
    private double linkPercent;
    
    public PieLabelRecord(final Comparable key, final double angle, final double baseY, final TextBox label, final double labelHeight, final double gap, final double linkPercent) {
        this.key = key;
        this.angle = angle;
        this.baseY = baseY;
        this.allocatedY = baseY;
        this.label = label;
        this.labelHeight = labelHeight;
        this.gap = gap;
        this.linkPercent = linkPercent;
    }
    
    public double getBaseY() {
        return this.baseY;
    }
    
    public void setBaseY(final double base) {
        this.baseY = base;
    }
    
    public double getLowerY() {
        return this.allocatedY - this.labelHeight / 2.0;
    }
    
    public double getUpperY() {
        return this.allocatedY + this.labelHeight / 2.0;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public Comparable getKey() {
        return this.key;
    }
    
    public TextBox getLabel() {
        return this.label;
    }
    
    public double getLabelHeight() {
        return this.labelHeight;
    }
    
    public double getAllocatedY() {
        return this.allocatedY;
    }
    
    public void setAllocatedY(final double y) {
        this.allocatedY = y;
    }
    
    public double getGap() {
        return this.gap;
    }
    
    public double getLinkPercent() {
        return this.linkPercent;
    }
    
    public int compareTo(final Object obj) {
        int result = 0;
        if (obj instanceof PieLabelRecord) {
            final PieLabelRecord plr = (PieLabelRecord)obj;
            if (this.baseY < plr.baseY) {
                result = -1;
            }
            else if (this.baseY > plr.baseY) {
                result = 1;
            }
        }
        return result;
    }
    
    public String toString() {
        return this.baseY + ", " + this.key.toString();
    }
}
