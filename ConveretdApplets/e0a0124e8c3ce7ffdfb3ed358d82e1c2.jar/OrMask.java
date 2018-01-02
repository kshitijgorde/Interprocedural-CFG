// 
// Decompiled by Procyon v0.5.30
// 

public class OrMask
{
    public int[] theMask;
    public int xOffset;
    public int yOffset;
    public int xmin;
    public int xmax;
    public int ymin;
    public int ymax;
    private int w;
    private int h;
    
    OrMask(final int[] theMask, final int xOffset, final int yOffset, final int xmin, final int ymin, final int xmax, final int ymax) {
        this.w = -1;
        this.h = -1;
        this.theMask = theMask;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }
    
    public void shiftBy(final int n, final int n2) {
        this.xOffset += n;
        this.yOffset += n2;
    }
    
    public int getWidth() {
        if (this.w == -1) {
            return Global.psplus;
        }
        return this.w;
    }
    
    public int getHeight() {
        if (this.h == -1) {
            return Global.psplus;
        }
        return this.h;
    }
    
    public void setWidth(final int w) {
        this.w = w;
    }
    
    public void setHeight(final int h) {
        this.h = h;
    }
}
