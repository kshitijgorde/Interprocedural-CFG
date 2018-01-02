// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

public class NFDataPoint
{
    public double x;
    public double y;
    public double z;
    public String target;
    public String activeLabel;
    public boolean isNull;
    
    public NFDataPoint() {
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.z = Double.NaN;
        this.target = "";
        this.activeLabel = "";
        this.isNull = true;
    }
    
    public String toString() {
        return new String("x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",target=" + this.target + ",activeLabel=" + this.activeLabel + ",isNull=" + this.isNull);
    }
    
    public void setNull() {
        this.x = Double.NaN;
        this.y = Double.NaN;
        this.z = Double.NaN;
        this.target = "";
        this.activeLabel = "";
        this.isNull = true;
    }
}
