// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.layout;

public class Bounds
{
    public float x;
    public float y;
    public float width;
    public float height;
    
    public Bounds() {
    }
    
    public Bounds(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean parse(final String s) {
        try {
            if (s == null) {
                return false;
            }
            final String[] split = s.split("\\s*+,\\s*+");
            if (split.length == 2) {
                this.width = Float.parseFloat(split[0]);
                this.height = Float.parseFloat(split[1]);
                return true;
            }
            if (split.length == 4) {
                this.x = Float.parseFloat(split[0]);
                this.y = Float.parseFloat(split[1]);
                this.width = Float.parseFloat(split[2]);
                this.height = Float.parseFloat(split[3]);
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("%.0f, %.0f, %.0f, %.0f", this.x, this.y, this.width, this.height);
    }
}
