// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

public class Transform3D implements Cloneable
{
    public double m00;
    public double m10;
    public double m20;
    public double m30;
    public double m01;
    public double m11;
    public double m21;
    public double m31;
    public double m02;
    public double m12;
    public double m22;
    public double m32;
    
    public Transform3D() {
        this.m00 = 1.0;
        this.m11 = 1.0;
        this.m22 = 1.0;
    }
    
    public Transform3D(final double n, final double n2, final double n3) {
        this.m00 = 1.0;
        this.m11 = 1.0;
        this.m22 = 1.0;
        this.rotate(n, n2, n3);
    }
    
    public Transform3D(final double m00, final double m2, final double m3, final double m4, final double m5, final double m6, final double m7, final double m8, final double m9, final double m10, final double m11, final double m12) {
        this.m00 = 1.0;
        this.m11 = 1.0;
        this.m22 = 1.0;
        this.m00 = m00;
        this.m10 = m2;
        this.m20 = m3;
        this.m30 = m4;
        this.m01 = m5;
        this.m11 = m6;
        this.m21 = m7;
        this.m31 = m8;
        this.m02 = m9;
        this.m12 = m10;
        this.m22 = m11;
        this.m32 = m12;
    }
    
    public Transform3D(final double[][] array) {
        this.m00 = 1.0;
        this.m11 = 1.0;
        this.m22 = 1.0;
        this.m00 = array[0][0];
        this.m10 = array[1][0];
        this.m20 = array[2][0];
        this.m30 = array[3][0];
        this.m01 = array[0][1];
        this.m11 = array[1][1];
        this.m21 = array[2][1];
        this.m31 = array[3][1];
        this.m02 = array[0][2];
        this.m12 = array[1][2];
        this.m22 = array[2][2];
        this.m32 = array[3][2];
    }
    
    public void setToIdentity() {
        this.m00 = 1.0;
        this.m10 = 0.0;
        this.m20 = 0.0;
        this.m30 = 0.0;
        this.m01 = 0.0;
        this.m11 = 1.0;
        this.m21 = 0.0;
        this.m31 = 0.0;
        this.m02 = 0.0;
        this.m12 = 0.0;
        this.m22 = 1.0;
        this.m32 = 0.0;
    }
    
    public void rotate(final double n, final double n2, final double n3) {
        this.rotateX(n);
        this.rotateY(n2);
        this.rotateZ(n3);
    }
    
    public void rotateX(final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        this.concatenate(new Transform3D(1.0, 0.0, 0.0, 0.0, 0.0, cos, sin, 0.0, 0.0, -sin, cos, 0.0));
    }
    
    public void rotateY(final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        this.concatenate(new Transform3D(cos, 0.0, -sin, 0.0, 0.0, 1.0, 0.0, 0.0, sin, 0.0, cos, 0.0));
    }
    
    public void rotateZ(final double n) {
        final double sin = Math.sin(n);
        final double cos = Math.cos(n);
        this.concatenate(new Transform3D(cos, sin, 0.0, 0.0, -sin, cos, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0));
    }
    
    public void scale(final double n, final double n2, final double n3) {
        this.concatenate(new Transform3D(n, 0.0, 0.0, 0.0, 0.0, n2, 0.0, 0.0, 0.0, 0.0, n3, 0.0));
    }
    
    public void translate(final double n, final double n2, final double n3) {
        this.concatenate(new Transform3D(1.0, 0.0, 0.0, n, 0.0, 1.0, 0.0, n2, 0.0, 0.0, 1.0, n3));
    }
    
    public void concatenate(final Transform3D transform3D) {
        final double m00 = this.m00;
        final double m2 = this.m10;
        final double m3 = this.m20;
        final double m4 = this.m30;
        final double m5 = this.m01;
        final double m6 = this.m11;
        final double m7 = this.m21;
        final double m8 = this.m31;
        final double m9 = this.m02;
        final double m10 = this.m12;
        final double m11 = this.m22;
        final double m12 = this.m32;
        this.m00 = m00 * transform3D.m00 + m5 * transform3D.m10 + m9 * transform3D.m20;
        this.m01 = m00 * transform3D.m01 + m5 * transform3D.m11 + m9 * transform3D.m21;
        this.m02 = m00 * transform3D.m02 + m5 * transform3D.m12 + m9 * transform3D.m22;
        this.m10 = m2 * transform3D.m00 + m6 * transform3D.m10 + m10 * transform3D.m20;
        this.m11 = m2 * transform3D.m01 + m6 * transform3D.m11 + m10 * transform3D.m21;
        this.m12 = m2 * transform3D.m02 + m6 * transform3D.m12 + m10 * transform3D.m22;
        this.m20 = m3 * transform3D.m00 + m7 * transform3D.m10 + m11 * transform3D.m20;
        this.m21 = m3 * transform3D.m01 + m7 * transform3D.m11 + m11 * transform3D.m21;
        this.m22 = m3 * transform3D.m02 + m7 * transform3D.m12 + m11 * transform3D.m22;
        this.m30 = m4 * transform3D.m00 + m8 * transform3D.m10 + m12 * transform3D.m20 + transform3D.m30;
        this.m31 = m4 * transform3D.m01 + m8 * transform3D.m11 + m12 * transform3D.m21 + transform3D.m31;
        this.m32 = m4 * transform3D.m02 + m8 * transform3D.m12 + m12 * transform3D.m22 + transform3D.m32;
    }
    
    public double[][] getMatrix() {
        return new double[][] { { this.m00, this.m10, this.m20, this.m30 }, { this.m01, this.m11, this.m21, this.m31 }, { this.m02, this.m12, this.m22, this.m32 }, { 0.0, 0.0, 0.0, 1.0 } };
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof Transform3D)) {
            return false;
        }
        final Transform3D transform3D = (Transform3D)o;
        return this.m00 == transform3D.m00 && this.m10 == transform3D.m10 && this.m20 == transform3D.m20 && this.m30 == transform3D.m30 && this.m01 == transform3D.m01 && this.m11 == transform3D.m11 && this.m21 == transform3D.m21 && this.m31 == transform3D.m31 && this.m02 == transform3D.m02 && this.m12 == transform3D.m12 && this.m22 == transform3D.m22 && this.m32 == transform3D.m32;
    }
    
    public void transform(final Point3D point3D) {
        this.transform(point3D, point3D);
    }
    
    public Point3D transform(final Point3D point3D, final Point3D point3D2) {
        final double x = point3D.x * this.m00 + point3D.y * this.m10 + point3D.z * this.m20 + this.m30;
        final double y = point3D.x * this.m01 + point3D.y * this.m11 + point3D.z * this.m21 + this.m31;
        final double z = point3D.x * this.m02 + point3D.y * this.m12 + point3D.z * this.m22 + this.m32;
        if (point3D2 == null) {
            return new Point3D(x, y, z);
        }
        point3D2.x = x;
        point3D2.y = y;
        point3D2.z = z;
        return point3D2;
    }
    
    public Polygon3D transform(final Polygon3D polygon3D, Polygon3D polygon3D2) {
        if (polygon3D2 == null) {
            polygon3D2 = new Polygon3D(polygon3D.npoints);
        }
        if (polygon3D2.xpoints.length < polygon3D.npoints) {
            polygon3D2.setCapacity(polygon3D.npoints);
        }
        int n = polygon3D.npoints - 1;
        while (--n >= 0) {
            polygon3D2.xpoints[n] = polygon3D.xpoints[n] * this.m00 + polygon3D.ypoints[n] * this.m10 + polygon3D.zpoints[n] * this.m20 + this.m30;
            polygon3D2.ypoints[n] = polygon3D.xpoints[n] * this.m01 + polygon3D.ypoints[n] * this.m11 + polygon3D.zpoints[n] * this.m21 + this.m31;
            polygon3D2.ypoints[n] = polygon3D.xpoints[n] * this.m02 + polygon3D.ypoints[n] * this.m12 + polygon3D.zpoints[n] * this.m22 + this.m32;
        }
        return polygon3D2;
    }
    
    public void transform(final float[] array, final int n, final float[] array2, final int n2, final int n3) {
        for (int n4 = n + n3 * 3, n5 = n2 * 3, i = n; i < n4; i += 3, n5 += 3) {
            array2[n5] = (float)(array[i] * this.m00 + array[i + 1] * this.m10 + array[i + 2] * this.m20 + this.m30);
            array2[n5 + 1] = (float)(array[i] * this.m01 + array[i + 1] * this.m11 + array[i + 2] * this.m21 + this.m31);
            array2[n5 + 2] = (float)(array[i] * this.m02 + array[i + 1] * this.m12 + array[i + 2] * this.m22 + this.m32);
        }
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public String toString() {
        return "{" + this.m00 + "," + this.m10 + "," + this.m20 + "," + this.m30 + "\n" + this.m01 + "," + this.m11 + "," + this.m21 + "," + this.m31 + "\n" + this.m02 + "," + this.m12 + "," + this.m22 + "," + this.m32 + "}";
    }
    
    public boolean isNaN() {
        return Double.isNaN(this.m00) || Double.isNaN(this.m10) || Double.isNaN(this.m20) || Double.isNaN(this.m30) || Double.isNaN(this.m01) || Double.isNaN(this.m11) || Double.isNaN(this.m21) || Double.isNaN(this.m31) || Double.isNaN(this.m02) || Double.isNaN(this.m12) || Double.isNaN(this.m22) || Double.isNaN(this.m32);
    }
    
    public void setTransform(final Transform3D transform3D) {
        this.m00 = transform3D.m00;
        this.m10 = transform3D.m10;
        this.m20 = transform3D.m20;
        this.m30 = transform3D.m30;
        this.m01 = transform3D.m01;
        this.m11 = transform3D.m11;
        this.m21 = transform3D.m21;
        this.m31 = transform3D.m31;
        this.m02 = transform3D.m02;
        this.m12 = transform3D.m12;
        this.m22 = transform3D.m22;
        this.m32 = transform3D.m32;
    }
}
