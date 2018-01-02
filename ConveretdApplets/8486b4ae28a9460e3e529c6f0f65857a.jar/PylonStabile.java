// 
// Decompiled by Procyon v0.5.30
// 

public class PylonStabile extends Stabile
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
    
    public PylonStabile(final PPoint pPoint, final double n) {
        super(pPoint);
        this.capRadius = n / 8.0;
        this.capLength = this.capRadius * 3.141592653589793;
        this.sideLength = n * 2.748893571891069;
        this.totalPerimeter = this.sideLength * 2.0 + this.capLength * 2.0;
        this.dom1 = this.sideLength / 2.0;
        this.dom2 = this.dom1 + this.capLength;
        this.dom3 = this.dom2 + this.sideLength;
        this.dom4 = this.dom3 + this.capLength;
        this.dom5 = this.dom4 + this.sideLength / 2.0;
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
                n2 = 0.0;
                break;
            }
            case 2: {
                n2 = (canonicalDistance - this.sideLength / 2.0) / this.capRadius;
                break;
            }
            case 3: {
                n2 = 3.141592653589793;
                break;
            }
            case 4: {
                n2 = (canonicalDistance - this.sideLength / 2.0 - this.capLength - this.sideLength) / this.capRadius + 3.141592653589793;
                break;
            }
            case 5: {
                n2 = 0.0;
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
        double n3 = 0.0;
        final double canonicalDistance = this.getCanonicalDistance(n);
        switch (this.getDomain(canonicalDistance)) {
            case 1: {
                n2 = this.capRadius;
                n3 = canonicalDistance;
                break;
            }
            case 2: {
                final double n4 = (canonicalDistance - this.sideLength / 2.0) / this.capRadius;
                n2 = Math.cos(n4) * this.capRadius;
                n3 = Math.sin(n4) * this.capRadius + this.sideLength / 2.0;
                break;
            }
            case 3: {
                final double n5 = canonicalDistance - this.sideLength / 2.0 - this.capLength;
                n2 = 0.0 - this.capRadius;
                n3 = this.sideLength / 2.0 - n5;
                break;
            }
            case 4: {
                final double n6 = (canonicalDistance - this.sideLength / 2.0 - this.capLength - this.sideLength) / this.capRadius + 3.141592653589793;
                n2 = Math.cos(n6) * this.capRadius;
                n3 = Math.sin(n6) * this.capRadius - this.sideLength / 2.0;
                break;
            }
            case 5: {
                final double n7 = canonicalDistance - this.sideLength / 2.0 - this.capLength - this.sideLength - this.capLength;
                n2 = this.capRadius;
                n3 = -(this.sideLength / 2.0) + n7;
                break;
            }
        }
        return new PPoint(super.center, n2, n3);
    }
    
    public String toString() {
        return "PylonStabile[c=" + super.center + ", caprad=" + this.capRadius + ", sideLen=" + this.sideLength + "]";
    }
}
