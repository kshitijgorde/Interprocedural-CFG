// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.common;

public class Mat22
{
    public Vec2 col1;
    public Vec2 col2;
    
    public String toString() {
        String s = "";
        s = String.valueOf(s) + "[" + this.col1.x + "," + this.col2.x + "]\n";
        s = String.valueOf(s) + "[" + this.col1.y + "," + this.col2.y + "]";
        return s;
    }
    
    public Mat22() {
        this(new Vec2(), new Vec2());
    }
    
    public Mat22(final float angle) {
        this();
        this.setAngle(angle);
    }
    
    public Mat22(final Vec2 c1, final Vec2 c2) {
        this.col1 = c1.clone();
        this.col2 = c2.clone();
    }
    
    public Mat22(final float col1x, final float col2x, final float col1y, final float col2y) {
        this.col1 = new Vec2(col1x, col1y);
        this.col2 = new Vec2(col2x, col2y);
    }
    
    public void set(final Mat22 m) {
        this.col1.x = m.col1.x;
        this.col1.y = m.col1.y;
        this.col2.x = m.col2.x;
        this.col2.y = m.col2.y;
    }
    
    public Mat22 clone() {
        return new Mat22(this.col1.clone(), this.col2.clone());
    }
    
    public void set(final float angle) {
        final float c = (float)Math.cos(angle);
        final float s = (float)Math.sin(angle);
        this.col1.x = c;
        this.col2.x = -s;
        this.col1.y = s;
        this.col2.y = c;
    }
    
    public void setIdentity() {
        this.col1.x = 1.0f;
        this.col2.x = 0.0f;
        this.col1.y = 0.0f;
        this.col2.y = 1.0f;
    }
    
    public void setZero() {
        this.col1.x = 0.0f;
        this.col2.x = 0.0f;
        this.col1.y = 0.0f;
        this.col2.y = 0.0f;
    }
    
    public void setAngle(final float angle) {
        final float c = (float)Math.cos(angle);
        final float s = (float)Math.sin(angle);
        this.col1.x = c;
        this.col2.x = -s;
        this.col1.y = s;
        this.col2.y = c;
    }
    
    public void set(final Vec2 c1, final Vec2 c2) {
        this.col1.x = c1.x;
        this.col2.x = c2.x;
        this.col1.y = c1.y;
        this.col2.y = c2.y;
    }
    
    public Mat22 invert() {
        final float a = this.col1.x;
        final float b = this.col2.x;
        final float c = this.col1.y;
        final float d = this.col2.y;
        final Mat22 B = new Mat22();
        float det = a * d - b * c;
        det = 1.0f / det;
        B.col1.x = det * d;
        B.col2.x = -det * b;
        B.col1.y = -det * c;
        B.col2.y = det * a;
        return B;
    }
    
    public Mat22 abs() {
        return new Mat22(this.col1.abs(), this.col2.abs());
    }
    
    public static Mat22 abs(final Mat22 R) {
        return R.abs();
    }
    
    public Vec2 mul(final Vec2 v) {
        return new Vec2(this.col1.x * v.x + this.col2.x * v.y, this.col1.y * v.x + this.col2.y * v.y);
    }
    
    public Mat22 mul(final Mat22 R) {
        final Mat22 C = new Mat22();
        C.set(this.mul(R.col1), this.mul(R.col2));
        return C;
    }
    
    public Mat22 mulT(final Mat22 B) {
        final Vec2 c1 = new Vec2(Vec2.dot(this.col1, B.col1), Vec2.dot(this.col2, B.col1));
        final Vec2 c2 = new Vec2(Vec2.dot(this.col1, B.col2), Vec2.dot(this.col2, B.col2));
        final Mat22 C = new Mat22();
        C.set(c1, c2);
        return C;
    }
    
    public Vec2 mulT(final Vec2 v) {
        return new Vec2(Vec2.dot(v, this.col1), Vec2.dot(v, this.col2));
    }
    
    public Mat22 add(final Mat22 B) {
        return new Mat22(this.col1.add(B.col1), this.col2.add(B.col2));
    }
    
    public Mat22 addLocal(final Mat22 B) {
        this.col1.addLocal(B.col1);
        this.col2.addLocal(B.col2);
        return this;
    }
    
    public Vec2 solve(final Vec2 b) {
        final float a11 = this.col1.x;
        final float a12 = this.col2.x;
        final float a13 = this.col1.y;
        final float a14 = this.col2.y;
        float det = a11 * a14 - a12 * a13;
        assert det != 0.0f;
        det = 1.0f / det;
        final Vec2 x = new Vec2(det * (a14 * b.x - a12 * b.y), det * (a11 * b.y - a13 * b.x));
        return x;
    }
    
    public static Vec2 mul(final Mat22 R, final Vec2 v) {
        return R.mul(v);
    }
    
    public static Mat22 mul(final Mat22 A, final Mat22 B) {
        return A.mul(B);
    }
    
    public static Vec2 mulT(final Mat22 R, final Vec2 v) {
        return R.mulT(v);
    }
    
    public static Mat22 mulT(final Mat22 A, final Mat22 B) {
        return A.mulT(B);
    }
}
