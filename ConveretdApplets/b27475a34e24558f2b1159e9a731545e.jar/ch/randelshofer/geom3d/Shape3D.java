// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Shape3D implements SceneNode
{
    private float[] coords;
    private int[][] faces;
    private Color[][] colors;
    private boolean isVisible;
    private boolean isWireframe;
    private boolean isReduced;
    private int reducedFaceCount;
    private Face3D[] faces3D;
    private ActionListener[] faceActions;
    private Transform3D transform;
    
    public Shape3D(final float[] array, final int[][] array2, final Color[][] array3) {
        this(array, array2, array3, array2.length);
    }
    
    public Shape3D(final float[] coords, final int[][] faces, final Color[][] colors, final int reducedFaceCount) {
        this.isVisible = true;
        this.isWireframe = false;
        this.isReduced = false;
        this.transform = new Transform3D();
        this.coords = coords;
        this.faces = faces;
        this.colors = colors;
        this.reducedFaceCount = reducedFaceCount;
    }
    
    public float[] getCoords() {
        return this.coords;
    }
    
    public int[][] getFaces() {
        return this.faces;
    }
    
    public boolean isVisible() {
        return this.isVisible;
    }
    
    public void setVisible(final boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    public boolean isRecuced() {
        return this.isReduced;
    }
    
    public void setReduced(final boolean isReduced) {
        this.isReduced = isReduced;
    }
    
    public void setTransform(final Transform3D transform) {
        this.transform = transform;
    }
    
    private void createFaces() {
        if (this.faces3D == null) {
            this.faces3D = new Face3D[this.faces.length];
            for (int i = 0; i < this.faces.length; ++i) {
                final Face3D[] faces3D = this.faces3D;
                final int n = i;
                final float[] coords = this.coords;
                final int[] array = this.faces[i];
                Color[] array3;
                if (this.isWireframe) {
                    final Color[] array2 = array3 = new Color[2];
                    array2[0] = null;
                    array2[1] = this.colors[i][1];
                }
                else {
                    array3 = this.colors[i];
                }
                faces3D[n] = new Face3D(coords, array, array3);
                if (this.faceActions != null) {
                    this.faces3D[i].setAction(this.faceActions[i]);
                }
            }
        }
    }
    
    public void addVisibleFaces(final Vector vector, final Transform3D transform3D, final Point3D point3D) {
        if (this.isVisible) {
            final Transform3D transform3D2 = (Transform3D)this.transform.clone();
            transform3D2.concatenate(transform3D);
            final float[] coords = new float[this.coords.length];
            transform3D2.transform(this.coords, 0, coords, 0, this.coords.length / 3);
            this.createFaces();
            for (int i = 0; i < (this.isReduced ? this.reducedFaceCount : this.faces.length); ++i) {
                this.faces3D[i].setCoords(coords);
                if (this.faces3D[i].isVisible(point3D)) {
                    vector.addElement(this.faces3D[i]);
                }
            }
        }
    }
    
    public void setAction(final int n, final ActionListener actionListener) {
        this.faces3D = null;
        if (this.faceActions == null) {
            this.faceActions = new ActionListener[this.faces.length];
        }
        this.faceActions[n] = actionListener;
    }
    
    public void setBackgroundColor(final int n, final Color color) {
        this.colors[n][0] = color;
    }
    
    public void setBorderColor(final int n, final Color color) {
        this.colors[n][1] = color;
    }
    
    public int getFaceCount() {
        return this.faces.length;
    }
    
    public boolean isWireframe() {
        return this.isWireframe;
    }
    
    public void setWireframe(final boolean isWireframe) {
        this.isWireframe = isWireframe;
        this.faces3D = null;
    }
}
