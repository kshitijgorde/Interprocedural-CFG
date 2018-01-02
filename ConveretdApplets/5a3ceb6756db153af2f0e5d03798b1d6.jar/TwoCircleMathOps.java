// 
// Decompiled by Procyon v0.5.30
// 

class TwoCircleMathOps extends DoubleMathOps
{
    private final double r1;
    private final double r2;
    
    public TwoCircleMathOps(final double r1, final double r2) {
        if (r1 < 0.0 || r2 < 0.0) {
            throw new IllegalArgumentException("Negative radii specified.");
        }
        this.r1 = r1;
        this.r2 = r2;
    }
    
    public Comparable f(final Comparable x) {
        final double d = (double)x;
        return new Double(this.r1 * this.r1 * Math.acos((d * d + this.r1 * this.r1 - this.r2 * this.r2) / (2.0 * d * this.r1)) + this.r2 * this.r2 * Math.acos((d * d + this.r2 * this.r2 - this.r1 * this.r1) / (2.0 * d * this.r2)) - 0.5 * Math.sqrt((-1.0 * d + this.r1 + this.r2) * (d + this.r1 - this.r2) * (d - this.r1 + this.r2) * (d + this.r1 + this.r2)));
    }
}
