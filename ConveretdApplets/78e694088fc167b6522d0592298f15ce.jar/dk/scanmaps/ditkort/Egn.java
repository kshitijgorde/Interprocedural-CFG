// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Point;

public class Egn
{
    Point upper;
    Point lower;
    String productnumber;
    String region;
    
    public Egn(final Point upper, final Point lower, final String productnumber, final String region) {
        this.upper = upper;
        this.lower = lower;
        this.productnumber = productnumber;
        this.region = region;
    }
    
    public Point getUpperPoint() {
        return this.upper;
    }
    
    public Point getLowerPoint() {
        return this.lower;
    }
    
    public String getProductNo() {
        return this.productnumber;
    }
    
    public String getRegionName() {
        return this.region;
    }
}
