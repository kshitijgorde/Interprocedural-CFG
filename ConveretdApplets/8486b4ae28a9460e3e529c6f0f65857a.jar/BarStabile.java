// 
// Decompiled by Procyon v0.5.30
// 

public class BarStabile extends Stabile
{
    protected double capRadius;
    protected double capLength;
    protected double sideLength;
    protected double totalPerimeter;
    protected double dom1;
    protected double dom2;
    protected double dom3;
    protected double dom4;
    protected double dom5;
    
    public BarStabile(final PPoint pPoint, final double n) {
        super(pPoint);
        this.capRadius = n / 8.0;
        this.capLength = this.capRadius * 3.141592653589793;
        this.sideLength = n * 2.748893571891069;
        this.totalPerimeter = this.sideLength * 2.0 + this.capLength * 2.0;
        this.dom1 = this.capLength / 2.0;
        this.dom2 = this.dom1 + this.sideLength;
        this.dom3 = this.dom2 + this.capLength;
        this.dom4 = this.dom3 + this.sideLength;
        this.dom5 = this.dom4 + this.capLength / 2.0;
    }
    
    public double angleToDistance(final double n) {
        throw new RuntimeException("Cannot compute angleToDistance!");
    }
    
    protected double getCanonicalDistance(double n) {
        while (n < 0.0) {
            n += this.totalPerimeter;
        }
        if (n > this.totalPerimeter) {
            n -= (int)Math.floor(n / this.totalPerimeter) * this.totalPerimeter;
        }
        return n;
    }
    
    protected int getDomain(final double n) {
        if (n < this.dom1) {
            return 1;
        }
        if (n < this.dom2) {
            return 2;
        }
        if (n < this.dom3) {
            return 3;
        }
        if (n < this.dom4) {
            return 4;
        }
        return 5;
    }
    
    public double distanceToAngle(final double n) {
        double n2 = 0.0;
        final double canonicalDistance = this.getCanonicalDistance(n);
        switch (this.getDomain(canonicalDistance)) {
            case 1: {
                n2 = canonicalDistance / this.capRadius;
                break;
            }
            case 2: {
                n2 = 1.5707963267948966;
                break;
            }
            case 3: {
                n2 = (canonicalDistance - this.capLength / 2.0 - this.sideLength) / this.capRadius + 1.5707963267948966;
                break;
            }
            case 4: {
                n2 = 4.71238898038469;
                break;
            }
            case 5: {
                n2 = (canonicalDistance - this.capLength / 2.0 - this.sideLength - this.capLength - this.sideLength) / this.capRadius + 4.71238898038469;
                break;
            }
        }
        return n2;
    }
    
    public double distanceToNormal(final double n, final boolean b) {
        double distanceToAngle = this.distanceToAngle(n);
        if (b) {
            distanceToAngle += 3.141592653589793;
        }
        return distanceToAngle;
    }
    
    public PPoint distanceToPoint(final double n) {
        double n2 = 0.0;
        double capRadius = 0.0;
        final double canonicalDistance = this.getCanonicalDistance(n);
        switch (this.getDomain(canonicalDistance)) {
            case 1: {
                final double n3 = canonicalDistance / this.capRadius;
                n2 = Math.cos(n3) * this.capRadius + this.sideLength / 2.0;
                capRadius = Math.sin(n3) * this.capRadius;
                break;
            }
            case 2: {
                final double n4 = canonicalDistance - this.capLength / 2.0;
                capRadius = this.capRadius;
                n2 = this.sideLength / 2.0 - n4;
                break;
            }
            case 3: {
                final double n5 = (canonicalDistance - this.capLength / 2.0 - this.sideLength) / this.capRadius + 1.5707963267948966;
                n2 = Math.cos(n5) * this.capRadius - this.sideLength / 2.0;
                capRadius = Math.sin(n5) * this.capRadius;
                break;
            }
            case 4: {
                final double n6 = canonicalDistance - this.capLength / 2.0 - this.sideLength - this.capLength;
                capRadius = -this.capRadius;
                n2 = -(this.sideLength / 2.0) + n6;
                break;
            }
            case 5: {
                final double n7 = (canonicalDistance - this.capLength / 2.0 - this.sideLength - this.capLength - this.sideLength) / this.capRadius + 4.71238898038469;
                n2 = Math.cos(n7) * this.capRadius + this.sideLength / 2.0;
                capRadius = Math.sin(n7) * this.capRadius;
                break;
            }
        }
        return new PPoint(super.center, n2, capRadius);
    }
    
    public String toString() {
        return "BarStabile[c=" + super.center + ", caprad=" + this.capRadius + ", sideLen=" + this.sideLength + "]";
    }
}
