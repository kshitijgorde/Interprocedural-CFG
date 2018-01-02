// 
// Decompiled by Procyon v0.5.30
// 

public class Cartesian
{
    public double x;
    public double y;
    public double z;
    
    public Cartesian(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static Cartesian xHat() {
        return new Cartesian(1.0, 0.0, 0.0);
    }
    
    public static Cartesian yHat() {
        return new Cartesian(0.0, 1.0, 0.0);
    }
    
    public static Cartesian zHat() {
        return new Cartesian(0.0, 0.0, 1.0);
    }
    
    public double dotProduct(final Cartesian cartesian) {
        return this.x * cartesian.x + this.y * cartesian.y + this.z * cartesian.z;
    }
    
    public double squaredMagnitude() {
        return this.dotProduct(this);
    }
    
    public double magnitude() {
        return Math.sqrt(this.squaredMagnitude());
    }
    
    public Cartesian crossProduct(final Cartesian cartesian) {
        return new Cartesian(this.y * cartesian.z - this.z * cartesian.y, this.z * cartesian.x - this.x * cartesian.z, this.x * cartesian.y - this.y * cartesian.x);
    }
    
    public Cartesian plus(final Cartesian cartesian) {
        return new Cartesian(this.x + cartesian.x, this.y + cartesian.y, this.z + cartesian.z);
    }
    
    public Cartesian negative() {
        return new Cartesian(-this.x, -this.y, -this.z);
    }
    
    public Cartesian scalarMult(final double n) {
        return new Cartesian(this.x * n, this.y * n, this.z * n);
    }
    
    public Cartesian makeUnitLength() {
        return this.scalarMult(1.0 / this.magnitude());
    }
    
    public double angleInPlane(final Cartesian cartesian, final Cartesian cartesian2) {
        return Math.atan2(this.dotProduct(cartesian2), this.dotProduct(cartesian));
    }
    
    public static Cartesian latLongToUnitVector(final double n, final double n2) {
        return new Cartesian(Math.cos(n) * Math.cos(n2), Math.cos(n) * Math.sin(n2), Math.sin(n));
    }
    
    public double angleBetween(final Cartesian cartesian) {
        return Math.acos(this.makeUnitLength().dotProduct(cartesian.makeUnitLength()));
    }
}
