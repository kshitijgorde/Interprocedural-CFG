// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.draw;

import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

public class Marker
{
    private Vector markerVertices;
    private Color color;
    
    public Marker() {
        this.markerVertices = new Vector(10);
    }
    
    public Marker(final Vector points) {
        this.markerVertices = new Vector(10);
        this.markerVertices = points;
    }
    
    public Marker(final Marker marker) {
        this.markerVertices = new Vector(10);
        for (int i = 0; i < marker.getNumberOfVertices(); ++i) {
            final Vertex orgVertex = marker.getVertex(i);
            this.addVertex(new Vertex(orgVertex.draw, orgVertex.x, orgVertex.y));
        }
    }
    
    public void addVertex(final Vertex v) {
        this.markerVertices.addElement(v);
    }
    
    public Vertex getVertex(final int i) {
        return this.markerVertices.elementAt(i);
    }
    
    public int getNumberOfVertices() {
        return this.markerVertices.size();
    }
    
    public void setColor(final Color c) {
        this.color = c;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void draw(final Graphics g, final int scale, final int x, final int y) {
        int x2 = x;
        int y2 = y;
        if (this.color != null) {
            g.setColor(this.color);
        }
        if (scale <= 0) {
            return;
        }
        if (this.markerVertices == null) {
            return;
        }
        if (scale > 1 && this.markerVertices.size() <= 2) {
            final int pos = 1 * scale / 2;
            g.drawLine(x + pos, y + pos, x + pos, y - pos);
            g.drawLine(x + pos, y - pos, x - pos, y - pos);
            g.drawLine(x - pos, y - pos, x - pos, y + pos);
            g.drawLine(x - pos, y + pos, x + pos, y + pos);
            return;
        }
        for (int i = 0; i < this.markerVertices.size(); ++i) {
            final Vertex vertex = this.markerVertices.elementAt(i);
            if (vertex.draw) {
                final int x3 = x + vertex.x * scale;
                final int y3 = y + vertex.y * scale;
                g.drawLine(x2, y2, x3, y3);
                x2 = x3;
                y2 = y3;
            }
            else {
                x2 = x + vertex.x * scale;
                y2 = y + vertex.y * scale;
            }
        }
    }
    
    public void drawFilledMarker(final Graphics g, final int scale, final int x, final int y) {
        if (scale <= 0) {
            return;
        }
        if (this.markerVertices == null) {
            return;
        }
        final Polygon poly = new Polygon();
        for (int i = 0; i < this.markerVertices.size(); ++i) {
            final Vertex vertex = this.markerVertices.elementAt(i);
            if (!vertex.draw) {
                throw new IllegalStateException("Marker's points are not contiguous");
            }
            poly.addPoint(x + vertex.x * scale, y + vertex.y * scale);
        }
        if (this.color != null) {
            g.setColor(this.color);
        }
        g.drawPolygon(poly);
        g.fillPolygon(poly);
    }
    
    public static class Vertex
    {
        public boolean draw;
        public int x;
        public int y;
        
        public Vertex() {
        }
        
        public Vertex(final boolean draw, final int x, final int y) {
            this.draw = draw;
            this.x = x;
            this.y = y;
        }
    }
}
