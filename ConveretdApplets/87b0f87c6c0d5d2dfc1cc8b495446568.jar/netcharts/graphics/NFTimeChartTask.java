// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFDate;
import netcharts.util.NFColor;
import java.awt.Color;

class NFTimeChartTask
{
    public Object start;
    public Object duration;
    public Color color;
    public NFLabel label;
    public double xmin;
    public double xmax;
    
    public String toString() {
        return "(" + this.start + "," + this.duration + "," + NFColor.toString(this.color) + "," + this.xmin + "," + this.xmax + ")";
    }
    
    public void setRange(final NFAxis nfAxis) {
        final double value = nfAxis.getValue(this.start);
        double value2;
        if (this.duration instanceof NFDate) {
            value2 = nfAxis.getValue(this.duration);
        }
        else {
            value2 = value + nfAxis.getValue(this.duration);
        }
        if (value < value2) {
            this.xmin = value;
            this.xmax = value2;
        }
        else {
            this.xmin = value2;
            this.xmax = value;
        }
    }
}
