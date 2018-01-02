// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class Square extends Shape
{
    public static int defaultColor;
    
    static {
        Square.defaultColor = 190;
    }
    
    public Square() {
        super.color = Square.defaultColor;
        super.vertices = new Vector(3, 1);
        final Vertex vertex = new Vertex(6, 3, 0.0, 0.0);
        super.vertices.addElement(vertex);
        final Vertex vertex2 = new Vertex(vertex, 6);
        super.vertices.addElement(vertex2);
        final Vertex vertex3 = new Vertex(vertex2, 9);
        super.vertices.addElement(vertex3);
        super.vertices.addElement(new Vertex(vertex3, 0));
    }
    
    public Object clone() {
        final Square square = new Square();
        square.vertices = (Vector)super.vertices.clone();
        for (int i = 0; i < super.vertices.size(); ++i) {
            square.vertices.setElementAt(((Vertex)square.vertices.elementAt(i)).clone(), i);
        }
        square.color = super.color;
        return square;
    }
}
