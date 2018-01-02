// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import javax.swing.event.ChangeListener;

public interface Model
{
    ModelPoint getOrigin();
    
    ModelVector getDefaultVector();
    
    Isometry getIdentity();
    
    Isometry getRotation(final double p0);
    
    Isometry getRotation(final ModelPoint p0, final double p1);
    
    Isometry getTranslation(final ModelPoint p0);
    
    Isometry getTranslation(final ModelPoint p0, final double p1);
    
    void getTranslation(final Isometry p0, final ModelPoint p1, final double p2);
    
    Isometry getTranslation(final ModelPoint p0, final ModelPoint p1);
    
    Isometry getTranslation(final ModelPoint p0, final ModelPoint p1, final double p2);
    
    Isometry getTranslation(final ModelVector p0, final double p1);
    
    void getTranslation(final Isometry p0, final ModelVector p1, final double p2);
    
    double dist(final ModelPoint p0);
    
    double dist2(final ModelPoint p0);
    
    double dist(final ModelPoint p0, final ModelPoint p1);
    
    double dist2(final ModelPoint p0, final ModelPoint p1);
    
    double getDistance(final ModelPoint p0, final ModelPoint p1, final ModelPoint p2, final boolean p3, final boolean p4);
    
    ModelPoint getProjection(final ModelPoint p0, final ModelPoint p1, final ModelPoint p2, final boolean p3, final boolean p4);
    
    double getAngle(final ModelPoint p0, final ModelPoint p1, final ModelPoint p2);
    
    double product(final ModelVector p0, final ModelVector p1);
    
    double length2(final ModelVector p0);
    
    double length(final ModelVector p0);
    
    ModelPoint exp(final ModelVector p0, final double p1);
    
    ModelVector distanceGradient(final ModelPoint p0, final ModelPoint p1);
    
    void distanceGradient(final ModelPoint p0, final ModelPoint p1, final ModelVector p2);
    
    Isometry getInversViewMatrix();
    
    Isometry getViewMatrix();
    
    void setViewMatrix(final Isometry p0);
    
    void addChangeListener(final ChangeListener p0);
    
    void removeChangeListener(final ChangeListener p0);
    
    ChangeListener[] getChangeListeners();
}
