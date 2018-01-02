// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

public class Point3D
{
    public double x;
    public double y;
    public double z;
    
    public Point3D() {
    }
    
    public Point3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public String toString() {
        return "Point3D[" + this.x + ", " + this.y + ", " + this.z + "]";
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
}
