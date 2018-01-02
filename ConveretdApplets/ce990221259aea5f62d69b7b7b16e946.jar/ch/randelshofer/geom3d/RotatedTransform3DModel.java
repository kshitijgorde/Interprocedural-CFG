// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

import ch.randelshofer.gui.event.ChangeEvent;
import ch.randelshofer.gui.event.ChangeListener;
import ch.randelshofer.beans.AbstractStateModel;

public class RotatedTransform3DModel extends AbstractStateModel implements Transform3DModel, ChangeListener
{
    private Transform3D rotator;
    private Transform3DModel model;
    
    public RotatedTransform3DModel(final double n, final double n2, final double n3) {
        this.rotator = new Transform3D(n, n2, n3);
        (this.model = new DefaultTransform3DModel()).addChangeListener(this);
    }
    
    public RotatedTransform3DModel(final double n, final double n2, final double n3, final Transform3DModel model) {
        this.rotator = new Transform3D(n, n2, n3);
        (this.model = model).addChangeListener(this);
    }
    
    public void setModel(final Transform3DModel model) {
        this.model.removeChangeListener(this);
        (this.model = model).addChangeListener(this);
    }
    
    public void concatenate(final Transform3D transform3D) {
        this.model.concatenate(transform3D);
    }
    
    public Transform3D getTransform() {
        final Transform3D transform = this.model.getTransform();
        transform.concatenate(this.rotator);
        return transform;
    }
    
    public Transform3D getTransform(final Transform3D transform3D) {
        this.model.getTransform(transform3D);
        transform3D.concatenate(this.rotator);
        return transform3D;
    }
    
    public void rotate(final double n, final double n2, final double n3) {
        final Transform3D transform = this.getTransform();
        transform.rotate(n, n2, n3);
        transform.concatenate(this.rotator);
        this.model.setTransform(transform);
    }
    
    public void rotateX(final double n) {
        final Transform3D transform = this.getTransform();
        transform.rotateX(n);
        transform.concatenate(this.rotator);
        this.model.setTransform(transform);
    }
    
    public void rotateY(final double n) {
        final Transform3D transform = this.getTransform();
        transform.rotateY(n);
        transform.concatenate(this.rotator);
        this.model.setTransform(transform);
    }
    
    public void rotateZ(final double n) {
        final Transform3D transform = this.getTransform();
        transform.rotateZ(n);
        transform.concatenate(this.rotator);
        this.model.setTransform(transform);
    }
    
    public void scale(final double n, final double n2, final double n3) {
        this.model.scale(n, n2, n3);
    }
    
    public void setToIdentity() {
        this.model.setToIdentity();
    }
    
    public void setTransform(final Transform3D transform) {
        this.model.setTransform(transform);
    }
    
    public void translate(final double n, final double n2, final double n3) {
        this.model.translate(n, n2, n3);
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.fireStateChanged();
    }
}
