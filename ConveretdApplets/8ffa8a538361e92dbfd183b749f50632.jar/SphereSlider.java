// 
// Decompiled by Procyon v0.5.30
// 

public class SphereSlider extends PointElement
{
    SphereElement S;
    double initx;
    double inity;
    double initz;
    
    SphereSlider(final SphereElement s, final double n, final double n2, final double n3) {
        super.dimension = 0;
        super.dragable = true;
        this.S = s;
        this.initx = n;
        super.x = n;
        this.inity = n2;
        super.y = n2;
        this.initz = n3;
        super.z = n3;
    }
    
    protected void reset() {
        super.x = this.initx;
        super.y = this.inity;
        super.z = this.initz;
        this.toSphere(this.S.Center, this.S.radius());
    }
    
    protected void update() {
        this.toSphere(this.S.Center, this.S.radius());
    }
    
    protected boolean drag(double n, double n2) {
        final double n3 = (this.S.Center.x - n) * (this.S.Center.x - n) + (this.S.Center.y - n2) * (this.S.Center.y - n2);
        final double radius2 = this.S.radius2();
        if (n3 <= radius2) {
            super.x = n;
            super.y = n2;
            if (super.z > this.S.Center.z) {
                super.z = this.S.Center.z + Math.sqrt(radius2 - n3);
            }
            else {
                super.z = this.S.Center.z - Math.sqrt(radius2 - n3);
            }
        }
        else {
            n -= this.S.Center.x;
            n2 -= this.S.Center.y;
            final double sqrt = Math.sqrt((n * n + n2 * n2) / radius2);
            n = n / sqrt + this.S.Center.x;
            n2 = n2 / sqrt + this.S.Center.y;
            if ((n - super.x) * (n - super.x) + (n2 - super.y) * (n2 - super.y) < 0.5) {
                return false;
            }
            super.x = n;
            super.y = n2;
            super.z = this.S.Center.z;
        }
        return true;
    }
}
