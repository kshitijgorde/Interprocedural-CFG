// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class Sliver extends Shape
{
    public static int defaultColor;
    public int rotation;
    
    static {
        Sliver.defaultColor = 210;
    }
    
    public Sliver() {
        this.rotation = 0;
        super.color = Sliver.defaultColor;
        super.vertices = new Vector(4, 1);
        final Vertex vertex = new Vertex(6, 1, 0.0, 0.0);
        super.vertices.addElement(vertex);
        final Vertex vertex2 = new Vertex(vertex, 6);
        super.vertices.addElement(vertex2);
        final Vertex vertex3 = new Vertex(vertex2, 7);
        super.vertices.addElement(vertex3);
        super.vertices.addElement(new Vertex(vertex3, 0));
    }
    
    public Object clone() {
        final Sliver sliver = new Sliver();
        sliver.vertices = (Vector)super.vertices.clone();
        for (int i = 0; i < super.vertices.size(); ++i) {
            sliver.vertices.setElementAt(((Vertex)sliver.vertices.elementAt(i)).clone(), i);
        }
        sliver.color = super.color;
        return sliver;
    }
    
    public void turnClick(final int n) {
        this.rotation += n;
        this.rotation %= 12;
        super.turnClick(n);
    }
    
    public void xorablePts(final Pick pick) {
        if (this.rotation % 12 == 2 || this.rotation % 12 == 8) {
            final int[][] pixelPts = new int[2][6];
            pixelPts[0][0] = pick.pixelPts[0][0];
            pixelPts[1][0] = pick.pixelPts[1][0];
            pixelPts[0][1] = pick.pixelPts[0][1];
            pixelPts[1][1] = pick.pixelPts[1][1];
            if (this.rotation == 2) {
                pixelPts[0][2] = pick.pixelPts[0][1] + 1;
                pixelPts[1][2] = pick.pixelPts[1][1];
            }
            else {
                pixelPts[0][2] = pick.pixelPts[0][1] - 1;
                pixelPts[1][2] = pick.pixelPts[1][1];
            }
            pixelPts[0][3] = pick.pixelPts[0][2];
            pixelPts[1][3] = pick.pixelPts[1][2];
            pixelPts[0][4] = pick.pixelPts[0][3];
            pixelPts[1][4] = pick.pixelPts[1][3];
            if (this.rotation == 2) {
                pixelPts[0][5] = pick.pixelPts[0][3] - 1;
                pixelPts[1][5] = pick.pixelPts[1][3];
            }
            else {
                pixelPts[0][5] = pick.pixelPts[0][3] + 1;
                pixelPts[1][5] = pick.pixelPts[1][3];
            }
            pick.pixelPts = pixelPts;
        }
    }
}
