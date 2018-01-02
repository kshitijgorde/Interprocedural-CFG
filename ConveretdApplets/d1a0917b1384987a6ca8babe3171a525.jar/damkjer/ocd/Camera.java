// 
// Decompiled by Procyon v0.5.30
// 

package damkjer.ocd;

import processing.core.PApplet;

public class Camera
{
    private static final float TWO_PI = 6.2831855f;
    private static final float PI = 3.1415927f;
    private static final float HALF_PI = 1.5707964f;
    private static final float TOL = 1.0E-5f;
    private PApplet parent;
    public float azimuth;
    public float elevation;
    public float roll;
    private float cameraX;
    private float cameraY;
    private float cameraZ;
    private float targetX;
    private float targetY;
    private float targetZ;
    private float upX;
    private float upY;
    private float upZ;
    private float fov;
    private float aspect;
    private float nearClip;
    private float farClip;
    private float shotLength;
    private float dx;
    private float dy;
    private float dz;
    
    public Camera(final PApplet pApplet) {
        this(pApplet, pApplet.height / 2.0f / pApplet.tan(0.5235988f));
    }
    
    public Camera(final PApplet pApplet, final float n) {
        this(pApplet, 0.0f, 0.0f, n);
    }
    
    public Camera(final PApplet pApplet, final float n, final float n2, final float n3) {
        this(pApplet, n, n2, n3, 0.0f, 0.0f, 0.0f);
    }
    
    public Camera(final PApplet pApplet, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this(pApplet, n, n2, n3, n4, n5, n6, 0.0f, 1.0f, 0.0f, 1.0471976f, 1.0f * pApplet.width / pApplet.height, 0.0f, 0.0f);
        this.nearClip = this.shotLength / 10.0f;
        this.farClip = this.shotLength * 10.0f;
    }
    
    public Camera(final PApplet parent, final float cameraX, final float cameraY, final float cameraZ, final float targetX, final float targetY, final float targetZ, final float upX, final float upY, final float upZ, final float fov, final float aspect, final float nearClip, final float farClip) {
        this.parent = parent;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.cameraZ = cameraZ;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.upX = upX;
        this.upY = upY;
        this.upZ = upZ;
        this.fov = fov;
        this.aspect = aspect;
        this.nearClip = nearClip;
        this.farClip = farClip;
        this.dx = cameraX - targetX;
        this.dy = cameraY - targetY;
        this.dz = cameraZ - targetZ;
        this.shotLength = this.sqrt(this.dx * this.dx + this.dy * this.dy + this.dz * this.dz);
        this.azimuth = this.atan2(this.dx, this.dz);
        this.elevation = this.atan2(this.dy, this.sqrt(this.dz * this.dz + this.dx * this.dx));
        if (this.elevation > 1.5707864f) {
            this.upY = 0.0f;
            this.upZ = -1.0f;
        }
        if (this.elevation < -1.5707864f) {
            this.upY = 0.0f;
            this.upZ = 1.0f;
        }
    }
    
    public void feed() {
        this.parent.perspective(this.fov, this.aspect, this.nearClip, this.farClip);
        this.parent.camera(this.cameraX, this.cameraY, this.cameraZ, this.targetX, this.targetY, this.targetZ, this.upX, this.upY, this.upZ);
    }
    
    public void aim(final float targetX, final float targetY, final float targetZ) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.dx = this.cameraX - targetX;
        this.dy = this.cameraY - targetY;
        this.dz = this.cameraZ - targetZ;
        this.shotLength = this.sqrt(this.dx * this.dx + this.dy * this.dy + this.dz * this.dz);
        this.azimuth = this.atan2(this.dx, this.dz);
        this.elevation = this.constrain(this.atan2(this.dy, this.sqrt(this.dz * this.dz + this.dx * this.dx)), -1.5707864f, 1.5707864f);
        this.updateUp();
    }
    
    public void jump(final float cameraX, final float cameraY, final float cameraZ) {
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.cameraZ = cameraZ;
        this.dx = this.cameraX - this.targetX;
        this.dy = this.cameraY - this.targetY;
        this.dz = this.cameraZ - this.targetZ;
        this.shotLength = this.sqrt(this.dx * this.dx + this.dy * this.dy + this.dz * this.dz);
        this.azimuth = this.atan2(this.dx, this.dz);
        this.elevation = this.constrain(this.atan2(this.dy, this.sqrt(this.dz * this.dz + this.dx * this.dx)), -1.5707864f, 1.5707864f);
        this.updateUp();
    }
    
    public void zoom(final float n) {
        this.fov = this.constrain(this.fov + n, 1.0E-5f, 3.1415827f);
    }
    
    public void truck(final float n) {
        final float n2 = this.dy * this.upZ - this.dz * this.upY;
        final float n3 = this.dx * this.upZ - this.dz * this.upX;
        final float n4 = this.dx * this.upY - this.dy * this.upX;
        final float sqrt = this.sqrt(n2 * n2 + n3 * n3 + n4 * n4);
        final float n5 = (sqrt < 1.0E-5f) ? 1.0f : sqrt;
        final float n6 = n2 / n5;
        final float n7 = n3 / n5;
        final float n8 = n4 / n5;
        this.cameraX -= n * n6;
        this.cameraY -= n * n7;
        this.cameraZ -= n * n8;
        this.targetX -= n * n6;
        this.targetY -= n * n7;
        this.targetZ -= n * n8;
    }
    
    public void boom(final float n) {
        this.cameraX += n * this.upX;
        this.cameraY += n * this.upY;
        this.cameraZ += n * this.upZ;
        this.targetX += n * this.upX;
        this.targetY += n * this.upY;
        this.targetZ += n * this.upZ;
    }
    
    public void dolly(final float n) {
        final float n2 = this.dx / this.shotLength;
        final float n3 = this.dy / this.shotLength;
        final float n4 = this.dz / this.shotLength;
        this.cameraX += n * n2;
        this.cameraY += n * n3;
        this.cameraZ += n * n4;
        this.targetX += n * n2;
        this.targetY += n * n3;
        this.targetZ += n * n4;
    }
    
    public void tilt(final float n) {
        this.elevation = this.constrain(this.elevation - n, -1.5707864f, 1.5707864f);
        this.targetX = this.cameraX - this.shotLength * this.sin(1.5707964f + this.elevation) * this.sin(this.azimuth);
        this.targetY = this.cameraY - -this.shotLength * this.cos(1.5707964f + this.elevation);
        this.targetZ = this.cameraZ - this.shotLength * this.sin(1.5707964f + this.elevation) * this.cos(this.azimuth);
        this.updateUp();
    }
    
    public void pan(final float n) {
        this.azimuth = (this.azimuth - n + 6.2831855f) % 6.2831855f;
        this.targetX = this.cameraX - this.shotLength * this.sin(1.5707964f + this.elevation) * this.sin(this.azimuth);
        this.targetY = this.cameraY - -this.shotLength * this.cos(1.5707964f + this.elevation);
        this.targetZ = this.cameraZ - this.shotLength * this.sin(1.5707964f + this.elevation) * this.cos(this.azimuth);
        this.updateUp();
    }
    
    public void roll(final float n) {
        this.roll += n;
        this.updateUp();
    }
    
    public void arc(final float n) {
        this.elevation = this.constrain(this.elevation + n, -1.5707864f, 1.5707864f);
        this.cameraX = this.targetX + this.shotLength * this.sin(1.5707964f + this.elevation) * this.sin(this.azimuth);
        this.cameraY = this.targetY + -this.shotLength * this.cos(1.5707964f + this.elevation);
        this.cameraZ = this.targetZ + this.shotLength * this.sin(1.5707964f + this.elevation) * this.cos(this.azimuth);
        this.updateUp();
    }
    
    public void circle(final float n) {
        this.azimuth = (this.azimuth + n + 6.2831855f) % 6.2831855f;
        this.cameraX = this.targetX + this.shotLength * this.sin(1.5707964f + this.elevation) * this.sin(this.azimuth);
        this.cameraY = this.targetY + -this.shotLength * this.cos(1.5707964f + this.elevation);
        this.cameraZ = this.targetZ + this.shotLength * this.sin(1.5707964f + this.elevation) * this.cos(this.azimuth);
        this.updateUp();
    }
    
    public void tumble(final float n, final float n2) {
        this.azimuth = (this.azimuth + n + 6.2831855f) % 6.2831855f;
        this.elevation = this.constrain(this.elevation + n2, -1.5707864f, 1.5707864f);
        this.cameraX = this.targetX + this.shotLength * this.sin(1.5707964f + this.elevation) * this.sin(this.azimuth);
        this.cameraY = this.targetY + -this.shotLength * this.cos(1.5707964f + this.elevation);
        this.cameraZ = this.targetZ + this.shotLength * this.sin(1.5707964f + this.elevation) * this.cos(this.azimuth);
        this.updateUp();
    }
    
    public void look(final float n, final float n2) {
        this.elevation = this.constrain(this.elevation - n2, -1.5707864f, 1.5707864f);
        this.azimuth = (this.azimuth - n + 6.2831855f) % 6.2831855f;
        this.targetX = this.cameraX - this.shotLength * this.sin(1.5707964f + this.elevation) * this.sin(this.azimuth);
        this.targetY = this.cameraY - -this.shotLength * this.cos(1.5707964f + this.elevation);
        this.targetZ = this.cameraZ - this.shotLength * this.sin(1.5707964f + this.elevation) * this.cos(this.azimuth);
        this.updateUp();
    }
    
    public void track(final float n, final float n2) {
        this.truck(n);
        this.boom(n2);
    }
    
    private void updateUp() {
        this.dx = this.cameraX - this.targetX;
        this.dy = this.cameraY - this.targetY;
        this.dz = this.cameraZ - this.targetZ;
        this.upX = -this.dx * this.dy;
        this.upY = this.dz * this.dz + this.dx * this.dx;
        this.upZ = -this.dz * this.dy;
        final float sqrt = this.sqrt(this.upX * this.upX + this.upY * this.upY + this.upZ * this.upZ);
        final float n = (sqrt < 1.0E-5f) ? 1.0f : sqrt;
        this.upX /= n;
        this.upY /= n;
        this.upZ /= n;
        if (this.roll != 0.0f) {
            final float n2 = this.dy * this.upZ - this.dz * this.upY;
            final float n3 = this.dx * this.upZ - this.dz * this.upX;
            final float n4 = this.dx * this.upY - this.dy * this.upX;
            final float sqrt2 = this.sqrt(n2 * n2 + n3 * n3 + n4 * n4);
            final float n5 = (sqrt2 < 0.001) ? 1.0f : sqrt2;
            final float n6 = n2 / n5;
            final float n7 = n3 / n5;
            final float n8 = n4 / n5;
            final float n9 = n6 * this.sin(this.roll);
            final float n10 = n7 * this.sin(this.roll);
            final float n11 = n8 * this.sin(this.roll);
            this.upX *= this.cos(this.roll);
            this.upY *= this.cos(this.roll);
            this.upZ *= this.cos(this.roll);
            this.upX += n9;
            this.upY += n10;
            this.upZ += n11;
        }
    }
    
    private final float radians(final float n) {
        final PApplet parent = this.parent;
        return PApplet.radians(n);
    }
    
    private final float sin(final float n) {
        return this.parent.sin(n);
    }
    
    private final float cos(final float n) {
        return this.parent.cos(n);
    }
    
    private final float tan(final float n) {
        return this.parent.tan(n);
    }
    
    private final float sqrt(final float n) {
        final PApplet parent = this.parent;
        return PApplet.sqrt(n);
    }
    
    private final float atan2(final float n, final float n2) {
        return this.parent.atan2(n, n2);
    }
    
    private final float degrees(final float n) {
        final PApplet parent = this.parent;
        return PApplet.degrees(n);
    }
    
    private final float constrain(final float n, final float n2, final float n3) {
        final PApplet parent = this.parent;
        return PApplet.constrain(n, n2, n3);
    }
}
