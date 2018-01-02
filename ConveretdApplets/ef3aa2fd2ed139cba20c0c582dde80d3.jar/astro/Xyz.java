// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class Xyz
{
    public double fX;
    public double fY;
    public double fZ;
    
    public Xyz() {
        final double fx = 0.0;
        this.fZ = fx;
        this.fY = fx;
        this.fX = fx;
    }
    
    public Xyz(final double fx, final double fy, final double fz) {
        this.fX = fx;
        this.fY = fy;
        this.fZ = fz;
    }
    
    public Xyz Rotate(final Matrix matrix) {
        return new Xyz(matrix.fA11 * this.fX + matrix.fA12 * this.fY + matrix.fA13 * this.fZ, matrix.fA21 * this.fX + matrix.fA22 * this.fY + matrix.fA23 * this.fZ, matrix.fA31 * this.fX + matrix.fA32 * this.fY + matrix.fA33 * this.fZ);
    }
    
    public Xyz Add(final Xyz xyz) {
        return new Xyz(this.fX + xyz.fX, this.fY + xyz.fY, this.fZ + xyz.fZ);
    }
    
    public Xyz Sub(final Xyz xyz) {
        return new Xyz(this.fX - xyz.fX, this.fY - xyz.fY, this.fZ - xyz.fZ);
    }
    
    public Xyz Mul(final double n) {
        return new Xyz(this.fX * n, this.fY * n, this.fZ * n);
    }
    
    public double Abs() {
        return Math.sqrt(this.fX * this.fX + this.fY * this.fY + this.fZ * this.fZ);
    }
}
