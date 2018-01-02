// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.geom3d;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import ch.randelshofer.util.Comparable;

public class Face3D implements Comparable
{
    private float[] coords;
    private int[] vertices;
    private double zAvg;
    private Color[] colors;
    private ActionListener action;
    private Point3D normal;
    
    public Face3D(final float[] coords, final int[] vertices, final Color[] colors) {
        this.coords = coords;
        this.vertices = vertices;
        this.colors = colors;
        this.updateValues();
    }
    
    private void updateValues() {
        this.zAvg = 0.0;
        for (int i = 0; i < this.vertices.length; ++i) {
            this.zAvg += this.coords[this.vertices[i] * 3 + 2];
        }
        this.zAvg /= this.vertices.length;
        final int n = this.vertices[0] * 3;
        final int n2 = this.vertices[1] * 3;
        final int n3 = this.vertices[2] * 3;
        final double n4 = this.coords[n2] - this.coords[n];
        final double n5 = this.coords[n2 + 1] - this.coords[n + 1];
        final double n6 = this.coords[n2 + 2] - this.coords[n + 2];
        final double n7 = this.coords[n3] - this.coords[n];
        final double n8 = this.coords[n3 + 1] - this.coords[n + 1];
        final double n9 = this.coords[n3 + 2] - this.coords[n + 2];
        this.normal = new Point3D(n5 * n9 - n6 * n8, n6 * n7 - n4 * n9, n4 * n8 - n5 * n7);
    }
    
    public Color getBorderColor() {
        return this.colors[1];
    }
    
    public Color getFillColor() {
        return this.colors[0];
    }
    
    public void setAction(final ActionListener action) {
        this.action = action;
    }
    
    public boolean handleEvent(final MouseEvent mouseEvent) {
        if (this.action != null) {
            this.action.actionPerformed(new ActionEvent(mouseEvent, 1001, null, mouseEvent.getModifiers()));
        }
        return true;
    }
    
    public ActionListener getAction() {
        return this.action;
    }
    
    public boolean isVisible(final Point3D point3D) {
        return (this.coords[this.vertices[0] * 3] - point3D.x) * this.normal.x + (this.coords[this.vertices[0] * 3 + 1] - point3D.y) * this.normal.y + (this.coords[this.vertices[0] * 3 + 2] - point3D.z) * this.normal.z > 0.0;
    }
    
    public int[] getVertices() {
        return this.vertices;
    }
    
    public float[] getCoords() {
        return this.coords;
    }
    
    public void setCoords(final float[] coords) {
        this.coords = coords;
        this.updateValues();
    }
    
    public double getBrightness(final Point3D point3D, final double n, final double n2) {
        this.getNormal();
        final double n3 = point3D.x - this.normal.x;
        final double n4 = point3D.y - this.normal.y;
        final double n5 = point3D.z - this.normal.z;
        final double n6 = (this.normal.x * n3 + this.normal.y * n4 + this.normal.z * n5) / Math.sqrt((this.normal.x * this.normal.x + this.normal.y * this.normal.y + this.normal.z * this.normal.z) * (n3 * n3 + n4 * n4 + n5 * n5));
        if (n6 < 0.0) {
            return n2 - n6 * n;
        }
        return n2;
    }
    
    public int compareTo(final Object o) {
        final double n = this.zAvg - ((Face3D)o).zAvg;
        return (n > 0.0) ? 1 : ((n < 0.0) ? -1 : 0);
    }
    
    private Point3D getNormal() {
        return this.normal;
    }
}
