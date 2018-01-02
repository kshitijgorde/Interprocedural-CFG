// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

public class HighLow
{
    public static final int OPEN = 0;
    public static final int CLOSE = 1;
    private Line2D line;
    private Rectangle2D bounds;
    private double open;
    private double close;
    private Stroke stroke;
    private Paint paint;
    private double tickSize;
    
    public HighLow(final double x, final double high, final double low) {
        this(x, high, low, high, low, new BasicStroke(), Color.blue);
    }
    
    public HighLow(final double x, final double high, final double low, final double open, final double close) {
        this(x, high, low, open, close, new BasicStroke(), Color.blue);
    }
    
    public HighLow(final double x, final double high, final double low, final double open, final double close, final Stroke stroke, final Paint paint) {
        this.tickSize = 2.0;
        this.line = new Line2D.Double(x, high, x, low);
        this.bounds = new Rectangle2D.Double(x - this.tickSize, high, 2.0 * this.tickSize, low - high);
        this.open = open;
        this.close = close;
        this.stroke = stroke;
        this.paint = paint;
    }
    
    public void setTickSize(final double newSize) {
        this.tickSize = newSize;
    }
    
    public double getTickSize() {
        return this.tickSize;
    }
    
    public Line2D getLine() {
        return this.line;
    }
    
    public Rectangle2D getBounds() {
        return this.bounds;
    }
    
    public double getValue(final int valueType) {
        if (valueType == 0) {
            return this.open;
        }
        return this.close;
    }
    
    public void setValue(final int type, final double value) {
        if (type == 0) {
            this.open = value;
        }
        else {
            this.close = value;
        }
    }
    
    public Line2D getOpenTickLine() {
        return this.getTickLine(this.getLine().getX1(), this.getValue(0), -1.0 * this.getTickSize());
    }
    
    public Line2D getCloseTickLine() {
        return this.getTickLine(this.getLine().getX1(), this.getValue(1), this.getTickSize());
    }
    
    private Line2D getTickLine(final double x, final double value, final double width) {
        return new Line2D.Double(x, value, x + width, value);
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
}
