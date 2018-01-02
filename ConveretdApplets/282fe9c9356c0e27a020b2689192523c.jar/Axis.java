// 
// Decompiled by Procyon v0.5.30
// 

public class Axis
{
    public double x1;
    public double x2;
    public double xd;
    public double y1;
    public double y2;
    public double yd;
    public int xinc;
    public int yinc;
    
    public Axis(final double x1, final double x2, final double y1, final double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.xd = x2 - x1;
        this.yd = y2 - y1;
        this.xinc = ((x2 - x1 > 0.0) ? 1 : -1);
        this.yinc = ((y2 - y1 > 0.0) ? 1 : -1);
    }
    
    public LongPair locateRatio(final LongPair p1) {
        final double xr = (p1.x - this.x1) / (this.x2 - this.x1);
        final double yr = (p1.y - this.y1) / (this.y2 - this.y1);
        return new LongPair(xr, yr);
    }
}
