// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

import java.awt.Rectangle;

public class ViewWindow
{
    private Rectangle bounds;
    private float angle;
    private float distanceToCamera;
    private float elevationToCamera;
    
    public ViewWindow(final int left, final int top, final int width, final int height, final float angle) {
        this.bounds = new Rectangle();
        this.angle = angle;
        this.setBounds(left, top, width, height);
    }
    
    public void setBounds(final int left, final int top, final int width, final int height) {
        this.bounds.x = left;
        this.bounds.y = top;
        this.bounds.width = width;
        this.bounds.height = height;
        this.distanceToCamera = this.bounds.width / 2 / (float)Math.tan(this.angle / 2.0f);
        this.elevationToCamera = this.bounds.height / 2;
    }
    
    public void setAngle(final float angle) {
        this.angle = angle;
        this.distanceToCamera = this.bounds.width / 2 / (float)Math.tan(angle / 2.0f);
    }
    
    public float getAngle() {
        return this.angle;
    }
    
    public int getWidth() {
        return this.bounds.width;
    }
    
    public int getHeight() {
        return this.bounds.height;
    }
    
    public int getTopOffset() {
        return this.bounds.y;
    }
    
    public int getLeftOffset() {
        return this.bounds.x;
    }
    
    public float getDistance() {
        return this.distanceToCamera;
    }
    
    public float getElevation() {
        return this.elevationToCamera;
    }
    
    public float convertFromViewXToScreenX(final float x) {
        return x + this.bounds.x + this.bounds.width / 2;
    }
    
    public float convertFromViewYToScreenY(final float y) {
        return -y + this.bounds.y + this.bounds.height / 2;
    }
    
    public float convertFromScreenXToViewX(final float x) {
        return x - this.bounds.x - this.bounds.width / 2;
    }
    
    public float convertFromScreenYToViewY(final float y) {
        return -y + this.bounds.y + this.bounds.height / 2;
    }
    
    public void project(final Vector3D v) {
        v.x = this.distanceToCamera * v.x / v.z;
        v.y = this.distanceToCamera * v.y / v.z;
        v.x = this.convertFromViewXToScreenX(v.x);
        v.y = this.convertFromViewYToScreenY(v.y);
    }
    
    public void backProject(final Vector3D v) {
        v.z += this.distanceToCamera;
        v.x = this.convertFromScreenXToViewX(v.x);
        v.y = this.convertFromScreenYToViewY(v.y);
        v.x = v.z * v.x / this.distanceToCamera;
        v.y = v.z * v.y / this.distanceToCamera;
    }
}
