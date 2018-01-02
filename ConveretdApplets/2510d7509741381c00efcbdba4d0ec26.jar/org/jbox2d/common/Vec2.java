// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.common;

public class Vec2
{
    public static final boolean watchCreations = true;
    public static int creationCount;
    public float x;
    public float y;
    
    static {
        Vec2.creationCount = 0;
    }
    
    public Vec2() {
        this(0.0f, 0.0f);
    }
    
    public Vec2(final float x, final float y) {
        ++Vec2.creationCount;
        this.x = x;
        this.y = y;
    }
    
    public void setZero() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
    
    public void set(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public void set(final Vec2 v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    public Vec2 add(final Vec2 v) {
        return new Vec2(this.x + v.x, this.y + v.y);
    }
    
    public Vec2 sub(final Vec2 v) {
        return new Vec2(this.x - v.x, this.y - v.y);
    }
    
    public Vec2 mul(final float a) {
        return new Vec2(this.x * a, this.y * a);
    }
    
    public Vec2 negate() {
        return new Vec2(-this.x, -this.y);
    }
    
    public Vec2 negateLocal() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }
    
    public Vec2 addLocal(final Vec2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }
    
    public Vec2 subLocal(final Vec2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }
    
    public Vec2 mulLocal(final float a) {
        this.x *= a;
        this.y *= a;
        return this;
    }
    
    public float length() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }
    
    public float lengthSquared() {
        return this.x * this.x + this.y * this.y;
    }
    
    public float normalize() {
        final float length = this.length();
        if (length < 1.1920929E-7f) {
            return 0.0f;
        }
        final float invLength = 1.0f / length;
        this.x *= invLength;
        this.y *= invLength;
        return length;
    }
    
    public boolean isValid() {
        return this.x != Float.NaN && this.x != Float.NEGATIVE_INFINITY && this.x != Float.POSITIVE_INFINITY && this.y != Float.NaN && this.y != Float.NEGATIVE_INFINITY && this.y != Float.POSITIVE_INFINITY;
    }
    
    public Vec2 abs() {
        return new Vec2(Math.abs(this.x), Math.abs(this.y));
    }
    
    public Vec2 clone() {
        return new Vec2(this.x, this.y);
    }
    
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
    
    public static Vec2 abs(final Vec2 a) {
        return new Vec2(Math.abs(a.x), Math.abs(a.y));
    }
    
    public static float dot(final Vec2 a, final Vec2 b) {
        return a.x * b.x + a.y * b.y;
    }
    
    public static float cross(final Vec2 a, final Vec2 b) {
        return a.x * b.y - a.y * b.x;
    }
    
    public static Vec2 cross(final Vec2 a, final float s) {
        return new Vec2(s * a.y, -s * a.x);
    }
    
    public static Vec2 cross(final float s, final Vec2 a) {
        return new Vec2(-s * a.y, s * a.x);
    }
    
    public static Vec2 min(final Vec2 a, final Vec2 b) {
        return new Vec2((a.x < b.x) ? a.x : b.x, (a.y < b.y) ? a.y : b.y);
    }
    
    public static Vec2 max(final Vec2 a, final Vec2 b) {
        return new Vec2((a.x > b.x) ? a.x : b.x, (a.y > b.y) ? a.y : b.y);
    }
}
