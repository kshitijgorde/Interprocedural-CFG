// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

public class Vector3D
{
    public float x;
    public float y;
    public float z;
    
    public Vector3D() {
        this(0.0f, 0.0f, 0.0f);
    }
    
    public Vector3D(final Vector3D v) {
        this(v.x, v.y, v.z);
    }
    
    public Vector3D(final float x, final float y, final float z) {
        this.setTo(x, y, z);
    }
    
    @Override
    public boolean equals(final Object obj) {
        final Vector3D v = (Vector3D)obj;
        return v.x == this.x && v.y == this.y && v.z == this.z;
    }
    
    public boolean equals(final float x, final float y, final float z) {
        return this.x == x && this.y == y && this.z == z;
    }
    
    public void setTo(final Vector3D v) {
        this.setTo(v.x, v.y, v.z);
    }
    
    public void setTo(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void add(final float x, final float y, final float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }
    
    public void subtract(final float x, final float y, final float z) {
        this.add(-x, -y, -z);
    }
    
    public void add(final Vector3D v) {
        this.add(v.x, v.y, v.z);
    }
    
    public void subtract(final Vector3D v) {
        this.add(-v.x, -v.y, -v.z);
    }
    
    public void multiply(final float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }
    
    public void divide(final float s) {
        this.x /= s;
        this.y /= s;
        this.z /= s;
    }
    
    public float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    public void normalize() {
        this.divide(this.length());
    }
    
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
    
    public void rotateX(final float cosAngle, final float sinAngle) {
        final float newY = this.y * cosAngle - this.z * sinAngle;
        final float newZ = this.y * sinAngle + this.z * cosAngle;
        this.y = newY;
        this.z = newZ;
    }
    
    public void rotateY(final float cosAngle, final float sinAngle) {
        final float newX = this.z * sinAngle + this.x * cosAngle;
        final float newZ = this.z * cosAngle - this.x * sinAngle;
        this.x = newX;
        this.z = newZ;
    }
    
    public void rotateZ(final float cosAngle, final float sinAngle) {
        final float newX = this.x * cosAngle - this.y * sinAngle;
        final float newY = this.x * sinAngle + this.y * cosAngle;
        this.x = newX;
        this.y = newY;
    }
    
    public void rotateX(final float radAngle) {
        this.rotateX((float)Math.cos(radAngle), (float)Math.sin(radAngle));
    }
    
    public void rotateY(final float radAngle) {
        this.rotateY((float)Math.cos(radAngle), (float)Math.sin(radAngle));
    }
    
    public void rotateZ(final float radAngle) {
        this.rotateZ((float)Math.cos(radAngle), (float)Math.sin(radAngle));
    }
    
    public void rotateX(final int degAngle) {
        this.rotateX((float)(3.141592653589793 * degAngle / 180.0));
    }
    
    public void rotateY(final int degAngle) {
        this.rotateY((float)(3.141592653589793 * degAngle / 180.0));
    }
    
    public void rotateZ(final int degAngle) {
        this.rotateZ((float)(3.141592653589793 * degAngle / 180.0));
    }
    
    public void add(final Transform3D xform) {
        this.addRotation(xform);
        this.add(xform.getLocation());
    }
    
    public void subtract(final Transform3D xform) {
        this.subtract(xform.getLocation());
        this.subtractRotation(xform);
    }
    
    public void addRotation(final Transform3D xform) {
        this.rotateX(xform.getCosAngleX(), xform.getSinAngleX());
        this.rotateZ(xform.getCosAngleZ(), xform.getSinAngleZ());
        this.rotateY(xform.getCosAngleY(), xform.getSinAngleY());
    }
    
    public void subtractRotation(final Transform3D xform) {
        this.rotateY(xform.getCosAngleY(), -xform.getSinAngleY());
        this.rotateZ(xform.getCosAngleZ(), -xform.getSinAngleZ());
        this.rotateX(xform.getCosAngleX(), -xform.getSinAngleX());
    }
    
    public void setToCrossProduct(final Vector3D u, final Vector3D v) {
        final float x = u.y * v.z - u.z * v.y;
        final float y = u.z * v.x - u.x * v.z;
        final float z = u.x * v.y - u.y * v.x;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float getDotProduct(final Vector3D temp1) {
        return this.x * temp1.x + this.y * temp1.y + this.z * temp1.z;
    }
    
    public double distance(final Vector3D from) {
        return Math.sqrt(Math.pow(this.x - from.x, 2.0) + Math.pow(this.y - from.y, 2.0) + Math.pow(this.z - from.z, 2.0));
    }
    
    public void rotateAroundVector(final Vector3D ref, final float angle) {
        final float angleRad = (float)(3.141592653589793 * angle / 180.0);
        final float u = ref.x;
        final float v = ref.y;
        final float w = ref.z;
        final float ux = u * this.x;
        final float uy = u * this.y;
        final float uz = u * this.z;
        final float vx = v * this.x;
        final float vy = v * this.y;
        final float vz = v * this.z;
        final float wx = w * this.x;
        final float wy = w * this.y;
        final float wz = w * this.z;
        final float sa = (float)Math.sin(angleRad);
        final float ca = (float)Math.cos(angleRad);
        this.x = u * (ux + vy + wz) + (this.x * (v * v + w * w) - u * (vy + wz)) * ca + (-wy + vz) * sa;
        this.y = v * (ux + vy + wz) + (this.y * (u * u + w * w) - v * (ux + wz)) * ca + (wx - uz) * sa;
        this.z = w * (ux + vy + wz) + (this.z * (u * u + v * v) - w * (ux + vy)) * ca + (-vx + uy) * sa;
    }
}
