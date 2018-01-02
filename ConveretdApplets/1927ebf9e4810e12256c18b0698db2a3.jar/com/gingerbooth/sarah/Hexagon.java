// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class Hexagon extends Shape
{
    public static int defaultColor;
    
    static {
        Hexagon.defaultColor = 175;
    }
    
    public Hexagon() {
        super.color = Hexagon.defaultColor;
        super.vertices = new Vector(6, 1);
        final Vertex vertex = new Vertex(6, 2, 0.0, 0.0);
        super.vertices.addElement(vertex);
        final Vertex vertex2 = new Vertex(vertex, 4);
        super.vertices.addElement(vertex2);
        final Vertex vertex3 = new Vertex(vertex2, 6);
        super.vertices.addElement(vertex3);
        final Vertex vertex4 = new Vertex(vertex3, 8);
        super.vertices.addElement(vertex4);
        final Vertex vertex5 = new Vertex(vertex4, 10);
        super.vertices.addElement(vertex5);
        super.vertices.addElement(new Vertex(vertex5, 0));
    }
    
    public Object clone() {
        final Hexagon hexagon = new Hexagon();
        hexagon.vertices = (Vector)super.vertices.clone();
        for (int i = 0; i < super.vertices.size(); ++i) {
            hexagon.vertices.setElementAt(((Vertex)hexagon.vertices.elementAt(i)).clone(), i);
        }
        hexagon.color = super.color;
        return hexagon;
    }
}
