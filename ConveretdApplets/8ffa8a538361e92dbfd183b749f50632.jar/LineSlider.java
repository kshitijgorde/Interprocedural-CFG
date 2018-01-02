// 
// Decompiled by Procyon v0.5.30
// 

public class LineSlider extends PointElement
{
    PointElement A;
    PointElement B;
    double initx;
    double inity;
    double initz;
    boolean segment;
    
    LineSlider(final PointElement a, final PointElement b, final double n, final double n2, final double n3, final boolean segment) {
        super.dimension = 0;
        super.dragable = true;
        this.A = a;
        this.B = b;
        this.initx = n;
        super.x = n;
        this.inity = n2;
        super.y = n2;
        this.initz = n3;
        super.z = n3;
        this.segment = segment;
        if (this.A.AP == this.B.AP) {
            super.AP = this.A.AP;
        }
    }
    
    protected void reset() {
        super.x = this.initx;
        super.y = this.inity;
        super.z = this.initz;
        this.toLine(this.A, this.B, this.segment);
    }
    
    protected void update() {
        this.toLine(this.A, this.B, this.segment);
    }
    
    protected boolean drag(double x, double y) {
        x -= this.A.x;
        y -= this.A.y;
        final double n = this.B.x - this.A.x;
        final double n2 = this.B.y - this.A.y;
        double n3 = (x * n + y * n2) / (n * n + n2 * n2);
        if (this.segment) {
            if (n3 < 0.0) {
                n3 = 0.0;
            }
            else if (n3 > 1.0) {
                n3 = 1.0;
            }
        }
        x = this.A.x + n * n3;
        y = this.A.y + n2 * n3;
        if ((x - super.x) * (x - super.x) + (x - super.y) * (x - super.y) < 0.5) {
            return false;
        }
        super.x = x;
        super.y = y;
        super.z = this.A.z + (this.B.z - this.A.z) * n3;
        return true;
    }
}
