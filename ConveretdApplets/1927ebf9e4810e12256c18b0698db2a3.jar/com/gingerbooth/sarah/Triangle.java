// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.util.Vector;

public class Triangle extends Shape
{
    public static int defaultColor;
    
    static {
        Triangle.defaultColor = 140;
    }
    
    public Triangle() {
        super.color = Triangle.defaultColor;
        super.vertices = new Vector(3, 1);
        final Vertex vertex = new Vertex(6, 4, 0.0, 0.0);
        super.vertices.addElement(vertex);
        final Vertex vertex2 = new Vertex(vertex, 8);
        super.vertices.addElement(vertex2);
        super.vertices.addElement(new Vertex(vertex2, 0));
    }
    
    public Object clone() {
        final Triangle triangle = new Triangle();
        triangle.vertices = (Vector)super.vertices.clone();
        for (int i = 0; i < super.vertices.size(); ++i) {
            triangle.vertices.setElementAt(((Vertex)triangle.vertices.elementAt(i)).clone(), i);
        }
        triangle.color = super.color;
        triangle.color2 = super.color2;
        return triangle;
    }
}
