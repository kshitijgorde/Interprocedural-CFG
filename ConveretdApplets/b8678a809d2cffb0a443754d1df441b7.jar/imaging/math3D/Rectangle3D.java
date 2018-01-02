// 
// Decompiled by Procyon v0.5.30
// 

package imaging.math3D;

public class Rectangle3D
{
    private Vector3D origin;
    private Vector3D directionU;
    private Vector3D directionV;
    private Vector3D normal;
    private float width;
    private float height;
    
    public Rectangle3D() {
        this.origin = new Vector3D();
        this.directionU = new Vector3D(1.0f, 0.0f, 0.0f);
        this.directionV = new Vector3D(0.0f, 1.0f, 0.0f);
        this.width = 0.0f;
        this.height = 0.0f;
    }
    
    public Rectangle3D(final Vector3D origin, final Vector3D directionU, final Vector3D directionV, final float width, final float height) {
        this.origin = new Vector3D(origin);
        (this.directionU = new Vector3D(directionU)).normalize();
        (this.directionV = new Vector3D(directionV)).normalize();
        this.width = width;
        this.height = height;
    }
    
    public void setTo(final Rectangle3D rect) {
        this.origin.setTo(rect.origin);
        this.directionU.setTo(rect.directionU);
        this.directionV.setTo(rect.directionV);
        this.width = rect.width;
        this.height = rect.height;
    }
    
    public Vector3D getOrigin() {
        return this.origin;
    }
    
    public Vector3D getDirectionU() {
        return this.directionU;
    }
    
    public Vector3D getDirectionV() {
        return this.directionV;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setWidth(final float width) {
        this.width = width;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(final float height) {
        this.height = height;
    }
    
    protected Vector3D calcNormal() {
        if (this.normal == null) {
            this.normal = new Vector3D();
        }
        this.normal.setToCrossProduct(this.directionU, this.directionV);
        this.normal.normalize();
        return this.normal;
    }
    
    public Vector3D getNormal() {
        this.calcNormal();
        return this.normal;
    }
    
    public void setNormal(final Vector3D n) {
        if (this.normal == null) {
            this.normal = new Vector3D(n);
        }
        else {
            this.normal.setTo(n);
        }
    }
    
    public void add(final Vector3D u) {
        this.origin.add(u);
    }
    
    public void subtract(final Vector3D u) {
        this.origin.subtract(u);
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
        this.origin.addRotation(xform);
        this.directionU.addRotation(xform);
        this.directionV.addRotation(xform);
    }
    
    public void subtractRotation(final Transform3D xform) {
        this.origin.subtractRotation(xform);
        this.directionU.subtractRotation(xform);
        this.directionV.subtractRotation(xform);
    }
}
