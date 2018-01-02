// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class HalfHexagon extends Shape
{
    public static int defaultColor;
    
    static {
        HalfHexagon.defaultColor = 206;
    }
    
    public HalfHexagon() {
        super.color = HalfHexagon.defaultColor;
        super.vertices = new Vector(4, 1);
        final Vertex vertex = new Vertex(6, 4, 0.0, 0.0);
        super.vertices.addElement(vertex);
        final Vertex vertex2 = new Vertex(vertex, 6);
        super.vertices.addElement(vertex2);
        final Vertex vertex3 = new Vertex(vertex2, 8);
        super.vertices.addElement(vertex3);
        super.vertices.addElement(new Vertex(vertex3, 0));
    }
    
    public Object clone() {
        final HalfHexagon halfHexagon = new HalfHexagon();
        halfHexagon.vertices = (Vector)super.vertices.clone();
        for (int i = 0; i < super.vertices.size(); ++i) {
            halfHexagon.vertices.setElementAt(((Vertex)halfHexagon.vertices.elementAt(i)).clone(), i);
        }
        halfHexagon.color = super.color;
        return halfHexagon;
    }
}
