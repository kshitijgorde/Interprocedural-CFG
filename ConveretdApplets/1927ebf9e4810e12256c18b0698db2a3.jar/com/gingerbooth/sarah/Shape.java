// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.Color;
import java.util.Vector;

public class Shape implements MetronomeEventListener
{
    protected Vector vertices;
    protected int color;
    protected int color2;
    protected boolean spinner;
    protected boolean allVandESpin;
    protected int spinAxis;
    protected int beatBegin;
    private int relationToVertex;
    
    public Shape() {
        this.color = -1;
        this.color2 = 210;
        this.spinner = false;
        this.allVandESpin = true;
        this.vertices = new Vector(1, 1);
    }
    
    public Object clone() {
        final Shape shape = new Shape();
        shape.setFrom(this);
        return shape;
    }
    
    public void flip(final double n, final double n2, final double n3, final double n4) {
    }
    
    public double[] getCenter() {
        final Vertex vertex = this.vertices.elementAt(0);
        final double[] array = { vertex.getX(), vertex.getY() };
        final int size = this.vertices.size();
        for (int i = 1; i < size; ++i) {
            final Vertex vertex2 = this.vertices.elementAt(i);
            final double[] array2 = array;
            final int n = 0;
            array2[n] += vertex2.getX();
            final double[] array3 = array;
            final int n2 = 1;
            array3[n2] += vertex2.getY();
        }
        final double[] array4 = array;
        final int n3 = 0;
        array4[n3] /= size;
        final double[] array5 = array;
        final int n4 = 1;
        array5[n4] /= size;
        return array;
    }
    
    public Color getColor() {
        return Tableau.palette.getColor(this.color);
    }
    
    public int getColorId() {
        return this.color;
    }
    
    public int getNumHoles() {
        return 0;
    }
    
    public double[][] getPts() {
        final int size = this.vertices.size();
        final double[] array = new double[size];
        final double[] array2 = new double[size];
        final double[][] array3 = new double[2][];
        array3[0] = array;
        array3[1] = array2;
        for (int i = 0; i < size; ++i) {
            array[i] = ((Vertex)this.vertices.elementAt(i)).getX();
            array2[i] = ((Vertex)this.vertices.elementAt(i)).getY();
        }
        return array3;
    }
    
    public Vertex getVertex(final int n) {
        return this.vertices.elementAt(n);
    }
    
    public Vector getVertices() {
        return this.vertices;
    }
    
    public double getX() {
        return this.vertices.elementAt(0).getX();
    }
    
    public double getY() {
        return this.vertices.elementAt(0).getY();
    }
    
    public void moveBy(final double n, final double n2) {
        for (int size = this.vertices.size(), i = 0; i < size; ++i) {
            final Vertex vertex = this.vertices.elementAt(i);
            vertex.setX(vertex.getX() + n);
            vertex.setY(vertex.getY() + n2);
        }
    }
    
    public static double[][] moveBy(final double n, final double n2, final double[][] array) {
        for (int length = array[0].length, i = 0; i < length; ++i) {
            final double[] array2 = array[0];
            final int n3 = i;
            array2[n3] += n;
            final double[] array3 = array[1];
            final int n4 = i;
            array3[n4] += n2;
        }
        return array;
    }
    
    public int nextVertex(final int n) {
        int n2 = n + 1;
        if (n2 == this.vertices.size()) {
            n2 = 0;
        }
        return n2;
    }
    
    public void onBeat(final Metronome metronome) {
    }
    
    public int pickVertex(final double n, final double n2, final boolean b, final Tableau tableau) {
        final int relationTo = this.relationTo(n, n2);
        if (relationTo == 2) {
            return this.relationToVertex;
        }
        if (relationTo == 1) {
            return this.relationToVertex;
        }
        int n3 = -1;
        double pickResolution;
        if (relationTo == 0) {
            if (!b) {
                return -1;
            }
            pickResolution = tableau.getPickResolution();
        }
        else {
            pickResolution = Double.POSITIVE_INFINITY;
        }
        for (int i = 0; i < this.vertices.size(); ++i) {
            final double distanceTo = this.vertices.elementAt(i).distanceTo(n, n2);
            if (distanceTo < pickResolution) {
                n3 = i;
                pickResolution = distanceTo;
            }
        }
        if (n3 == -1) {
            return -1;
        }
        final Vertex vertex = this.vertices.elementAt(n3);
        final double angleToward = vertex.angleToward(n, n2);
        if (Math.abs(angleToward - Vertex.getRadian(vertex.getAngleOut())) < Math.abs(angleToward - Vertex.getRadian(vertex.getAngleIn()))) {
            return n3;
        }
        if (n3 == 0) {
            return this.vertices.size() - 1;
        }
        return n3 - 1;
    }
    
    public int relationTo(final double n, final double n2) {
        int i = 0;
        while (i < this.vertices.size()) {
            final Vertex vertex = this.vertices.elementAt(i);
            this.relationToVertex = i;
            switch (vertex.ptInAngle(n, n2)) {
                case 0: {
                    return 0;
                }
                case 2: {
                    return 2;
                }
                case 1: {
                    return 1;
                }
                default: {
                    ++i;
                    continue;
                }
            }
        }
        return 4;
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    public void setColor2(final int color2) {
        this.color2 = color2;
    }
    
    public void setFrom(final Shape shape) {
        this.vertices = (Vector)shape.vertices.clone();
        for (int i = 0; i < shape.vertices.size(); ++i) {
            this.vertices.setElementAt(((Vertex)this.vertices.elementAt(i)).clone(), i);
        }
        this.color = shape.color;
        this.color2 = shape.color2;
    }
    
    public String toString() {
        final int size = this.vertices.size();
        String s = String.valueOf(String.valueOf(this.getClass())) + " vertices(" + this.vertices.size() + ") : ";
        if (size > 0) {
            for (int i = 0; i < size - 1; ++i) {
                s = String.valueOf(s) + this.vertices.elementAt(i) + ",";
            }
            s = String.valueOf(s) + this.vertices.elementAt(size - 1);
        }
        return s;
    }
    
    public void turnClick(final int n) {
        final int size = this.vertices.size();
        final Vector vertices = new Vector<Vertex>(size);
        final Vertex vertex = this.vertices.elementAt(0);
        final Vertex vertex2 = new Vertex(vertex.getAngleIn() - n, vertex.getAngleOut() - n, vertex.x, vertex.y);
        vertices.addElement(vertex2);
        Vertex vertex3 = vertex2;
        for (int i = 1; i < size; ++i) {
            final Vertex vertex4 = new Vertex(vertex3, this.vertices.elementAt(i).getAngleOut() - n);
            vertices.addElement(vertex4);
            vertex3 = vertex4;
        }
        this.vertices = vertices;
    }
}
