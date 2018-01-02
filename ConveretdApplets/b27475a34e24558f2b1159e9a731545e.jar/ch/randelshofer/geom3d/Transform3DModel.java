// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

import ch.randelshofer.gui.event.ChangeListener;

public interface Transform3DModel
{
    void setToIdentity();
    
    void rotate(final double p0, final double p1, final double p2);
    
    void rotateX(final double p0);
    
    void rotateY(final double p0);
    
    void rotateZ(final double p0);
    
    void scale(final double p0, final double p1, final double p2);
    
    void translate(final double p0, final double p1, final double p2);
    
    void concatenate(final Transform3D p0);
    
    Transform3D getTransform();
    
    Transform3D getTransform(final Transform3D p0);
    
    void setTransform(final Transform3D p0);
    
    void addChangeListener(final ChangeListener p0);
    
    void removeChangeListener(final ChangeListener p0);
}
