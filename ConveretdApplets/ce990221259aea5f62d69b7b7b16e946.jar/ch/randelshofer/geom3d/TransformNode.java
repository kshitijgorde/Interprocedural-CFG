// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

import java.util.Enumeration;
import java.util.Vector;

public class TransformNode implements SceneNode
{
    private Vector children;
    private Transform3D transform;
    private boolean isVisible;
    
    public TransformNode() {
        this.children = new Vector();
        this.transform = new Transform3D();
        this.isVisible = true;
    }
    
    public void addChild(final SceneNode sceneNode) {
        this.children.addElement(sceneNode);
    }
    
    public SceneNode getChild(final int n) {
        return this.children.elementAt(n);
    }
    
    public void setTransform(final Transform3D transform) {
        this.transform = transform;
    }
    
    public Transform3D getTransform() {
        return this.transform;
    }
    
    public void setVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public void addVisibleFaces(final Vector vector, final Transform3D transform3D, final Point3D point3D) {
        if (this.isVisible) {
            final Transform3D transform3D2 = (Transform3D)this.transform.clone();
            transform3D2.concatenate(transform3D);
            final Enumeration<SceneNode> elements = this.children.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().addVisibleFaces(vector, transform3D2, point3D);
            }
        }
    }
}
