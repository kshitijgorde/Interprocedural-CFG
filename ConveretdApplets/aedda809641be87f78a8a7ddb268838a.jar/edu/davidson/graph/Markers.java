// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graph;

import java.awt.Graphics;
import java.util.Vector;

public class Markers
{
    public static int TYPE_DEFAULT;
    public static int TYPE_CIRCLE;
    public static int TYPE_SQUARE;
    protected int last;
    protected int max;
    protected Vector[] vert;
    protected MarkerStyle[] styles;
    
    public Markers() {
        this.max = 10;
        this.last = 0;
        this.vert = new Vector[this.max];
        this.styles = new MarkerStyle[this.max];
    }
    
    public void AddMarker(final int n, final int size, final int type) {
        this.AddMarker(n, 1, new boolean[] { false }, new int[] { 0 }, new int[] { 0 });
        this.styles[n - 1].type = type;
        this.styles[n - 1].size = size;
    }
    
    public int getMarkerSize(final int n) {
        return this.styles[n - 1].size;
    }
    
    public void AddMarker(int last, final int n, final boolean[] array, final int[] array2, final int[] array3) {
        if (last < 1 || last > this.max) {
            return;
        }
        if (n <= 0) {
            return;
        }
        --last;
        this.last = last;
        this.vert[last] = new Vector();
        for (int i = 0; i < n; ++i) {
            final MarkerVertex markerVertex = new MarkerVertex();
            markerVertex.draw = array[i];
            markerVertex.x = array2[i];
            markerVertex.y = array3[i];
            this.vert[last].addElement(markerVertex);
        }
        this.styles[last] = new MarkerStyle();
        this.styles[last].type = 0;
    }
    
    public void AddMarker(final int n, final boolean[] array, final int[] array2, final int[] array3) {
        this.AddMarker(this.last + 1, n, array, array2, array3);
    }
    
    public void DeleteMarker(final int n) {
        if (n < 1 || n > this.max) {
            return;
        }
        this.vert[n - 1] = null;
        this.styles[n - 1] = null;
    }
    
    public void ClearMarkers() {
        if (this.last == 0) {
            return;
        }
        for (int i = 0; i < this.max; ++i) {
            this.vert[i] = null;
            this.styles[i] = null;
        }
        this.last = 0;
    }
    
    public void drawCircle(final Graphics graphics, final double n, final int n2, final int n3) {
        final int n4 = (int)Math.round(4 * n);
        graphics.fillOval(n2 - n4, n3 - n4, 2 * n4 + 1, 2 * n4 + 1);
    }
    
    public void drawSquare(final Graphics graphics, final double n, final int n2, final int n3) {
        final int n4 = (int)Math.round(4 * n);
        graphics.fillRect(n2 - n4, n3 - n4, 2 * n4 + 1, 2 * n4 + 1);
    }
    
    public void draw(final Graphics graphics, final int n, final double n2, final int n3, final int n4) {
        int n5 = n3;
        int n6 = n4;
        if (n < 1 || n > this.max) {
            return;
        }
        if (n2 <= 0) {
            return;
        }
        final Vector vector = this.vert[n - 1];
        if (vector == null) {
            return;
        }
        if (this.styles[n - 1].type == Markers.TYPE_CIRCLE) {
            this.drawCircle(graphics, n2, n3, n4);
            return;
        }
        if (this.styles[n - 1].type == Markers.TYPE_SQUARE) {
            this.drawSquare(graphics, n2, n3, n4);
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final MarkerVertex markerVertex = vector.elementAt(i);
            if (markerVertex.draw) {
                final int n7 = n3 + (int)(markerVertex.x * n2);
                final int n8 = n4 + (int)(markerVertex.y * n2);
                graphics.drawLine(n5, n6, n7, n8);
                n5 = n7;
                n6 = n8;
            }
            else {
                n5 = n3 + (int)(markerVertex.x * n2);
                n6 = n4 + (int)(markerVertex.y * n2);
            }
        }
    }
    
    static {
        Markers.TYPE_DEFAULT = 0;
        Markers.TYPE_CIRCLE = 1;
        Markers.TYPE_SQUARE = 2;
    }
}
