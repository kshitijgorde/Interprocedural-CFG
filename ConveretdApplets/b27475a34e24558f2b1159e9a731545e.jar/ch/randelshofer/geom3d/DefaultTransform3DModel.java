// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

import ch.randelshofer.beans.AbstractStateModel;

public class DefaultTransform3DModel extends AbstractStateModel implements Transform3DModel
{
    private Transform3D transform;
    
    public DefaultTransform3DModel() {
        this.transform = new Transform3D();
    }
    
    public DefaultTransform3DModel(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10, final double n11, final double n12) {
        this.transform = new Transform3D(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public DefaultTransform3DModel(final double[][] array) {
        this.transform = new Transform3D(array);
    }
    
    public void setToIdentity() {
        this.transform.setToIdentity();
        this.fireStateChanged();
    }
    
    public void rotateX(final double n) {
        this.transform.rotateX(n);
        this.fireStateChanged();
    }
    
    public void rotateY(final double n) {
        this.transform.rotateY(n);
        this.fireStateChanged();
    }
    
    public void rotateZ(final double n) {
        this.transform.rotateZ(n);
        this.fireStateChanged();
    }
    
    public void scale(final double n, final double n2, final double n3) {
        this.transform.scale(n, n2, n3);
        this.fireStateChanged();
    }
    
    public void translate(final double n, final double n2, final double n3) {
        this.transform.translate(n, n2, n3);
        this.fireStateChanged();
    }
    
    public void concatenate(final Transform3D transform3D) {
        this.transform.concatenate(transform3D);
        this.fireStateChanged();
    }
    
    public void setTransform(final Transform3D transform) {
        this.transform.setTransform(transform);
        this.fireStateChanged();
    }
    
    public Transform3D getTransform() {
        return (Transform3D)this.transform.clone();
    }
    
    public Transform3D getTransform(final Transform3D transform3D) {
        transform3D.setTransform(this.transform);
        return transform3D;
    }
    
    public void rotate(final double n, final double n2, final double n3) {
        this.transform.rotate(n, n2, n3);
        this.fireStateChanged();
    }
}
