// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

public class Transform3D
{
    protected Vector3D location;
    private float angleX;
    private float angleY;
    private float angleZ;
    private float cosAngleX;
    private float sinAngleX;
    private float cosAngleY;
    private float sinAngleY;
    private float cosAngleZ;
    private float sinAngleZ;
    
    public Transform3D() {
        this(0.0f, 0.0f, 0.0f);
    }
    
    public Transform3D(final float x, final float y, final float z) {
        this.location = new Vector3D(x, y, z);
        this.setAngle(0.0f, 0.0f, 0.0f);
    }
    
    public Transform3D(final Transform3D v) {
        this.location = new Vector3D();
        this.setTo(v);
    }
    
    public Object clone() {
        return new Transform3D(this);
    }
    
    public void setTo(final Transform3D v) {
        this.location.setTo(v.location);
        this.angleX = v.angleX;
        this.angleY = v.angleY;
        this.angleZ = v.angleZ;
        this.cosAngleX = v.cosAngleX;
        this.sinAngleX = v.sinAngleX;
        this.cosAngleY = v.cosAngleY;
        this.sinAngleY = v.sinAngleY;
        this.cosAngleZ = v.cosAngleZ;
        this.sinAngleZ = v.sinAngleZ;
    }
    
    public Vector3D getLocation() {
        return this.location;
    }
    
    public float getCosAngleX() {
        return this.cosAngleX;
    }
    
    public float getSinAngleX() {
        return this.sinAngleX;
    }
    
    public float getCosAngleY() {
        return this.cosAngleY;
    }
    
    public float getSinAngleY() {
        return this.sinAngleY;
    }
    
    public float getCosAngleZ() {
        return this.cosAngleZ;
    }
    
    public float getSinAngleZ() {
        return this.sinAngleZ;
    }
    
    public float getAngleX() {
        return this.angleX;
    }
    
    public float getAngleY() {
        return this.angleY;
    }
    
    public float getAngleZ() {
        return this.angleZ;
    }
    
    public void setAngleX(final float angleX) {
        this.angleX = angleX;
        this.cosAngleX = (float)Math.cos(angleX);
        this.sinAngleX = (float)Math.sin(angleX);
    }
    
    public void setAngleY(final float angleY) {
        this.angleY = angleY;
        this.cosAngleY = (float)Math.cos(angleY);
        this.sinAngleY = (float)Math.sin(angleY);
    }
    
    public void setAngleZ(final float angleZ) {
        this.angleZ = angleZ;
        this.cosAngleZ = (float)Math.cos(angleZ);
        this.sinAngleZ = (float)Math.sin(angleZ);
    }
    
    public void setAngle(final float angleX, final float angleY, final float angleZ) {
        this.setAngleX(angleX);
        this.setAngleY(angleY);
        this.setAngleZ(angleZ);
    }
    
    public void rotateAngleX(final float angle) {
        if (angle != 0.0f) {
            this.setAngleX(this.getAngleX() + angle);
        }
    }
    
    public void rotateAngleY(final float angle) {
        if (angle != 0.0f) {
            this.setAngleY(this.getAngleY() + angle);
        }
    }
    
    public void rotateAngleZ(final float angle) {
        if (angle != 0.0f) {
            this.setAngleZ(this.getAngleZ() + angle);
        }
    }
    
    public void rotateAngle(final float angleX, final float angleY, final float angleZ) {
        this.rotateAngleX(angleX);
        this.rotateAngleY(angleY);
        this.rotateAngleZ(angleZ);
    }
}
