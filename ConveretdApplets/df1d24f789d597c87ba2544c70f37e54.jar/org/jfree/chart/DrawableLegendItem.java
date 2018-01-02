// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.Shape;

public class DrawableLegendItem
{
    private LegendItem item;
    private double x;
    private double y;
    private double width;
    private double height;
    private Shape marker;
    private boolean markerFilled;
    private Line2D line;
    private Stroke lineStroke;
    private Point2D labelPosition;
    
    public DrawableLegendItem(final LegendItem item) {
        this.item = item;
    }
    
    public LegendItem getItem() {
        return this.item;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getMaxX() {
        return this.getX() + this.getWidth();
    }
    
    public double getMaxY() {
        return this.getY() + this.getHeight();
    }
    
    public Shape getMarker() {
        return this.marker;
    }
    
    public void setMarker(final Shape marker) {
        this.marker = marker;
    }
    
    public boolean isMarkerFilled() {
        return this.markerFilled;
    }
    
    public void setMarkerFilled(final boolean filled) {
        this.markerFilled = filled;
    }
    
    public Stroke getLineStroke() {
        return this.lineStroke;
    }
    
    public void setLineStroke(final Stroke s) {
        this.lineStroke = s;
    }
    
    public void setLine(final Line2D l) {
        this.line = l;
    }
    
    public Line2D getLine() {
        return this.line;
    }
    
    public Point2D getLabelPosition() {
        return this.labelPosition;
    }
    
    public void setLabelPosition(final Point2D position) {
        this.labelPosition = position;
    }
    
    public void setBounds(final double x, final double y, final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void draw(final Graphics2D g2, final double xOffset, final double yOffset) {
    }
}
