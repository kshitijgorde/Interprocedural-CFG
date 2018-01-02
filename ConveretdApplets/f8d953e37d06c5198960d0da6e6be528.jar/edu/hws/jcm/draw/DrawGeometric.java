// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Graphics;
import java.awt.Color;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.Computable;

public class DrawGeometric extends Drawable implements Computable
{
    public static final int LINE_ABSOLUTE = 0;
    public static final int INFINITE_LINE_ABSOLUTE = 1;
    public static final int RECT_ABSOLUTE = 2;
    public static final int OVAL_ABSOLUTE = 3;
    public static final int LINE_RELATIVE = 4;
    public static final int INFINITE_LINE_RELATIVE = 5;
    public static final int RECT_RELATIVE = 6;
    public static final int OVAL_RELATIVE = 7;
    public static final int LINE_CENTERED = 8;
    public static final int RECT_CENTERED = 9;
    public static final int OVAL_CENTERED = 10;
    public static final int CROSS = 11;
    protected int shape;
    protected Value x1;
    protected Value x2;
    protected Value y1;
    protected Value y2;
    protected int h;
    protected int v;
    protected double a;
    protected double b;
    protected double c;
    protected double d;
    protected Color color;
    protected Color fillColor;
    protected int lineWidth;
    private boolean changed;
    
    public DrawGeometric() {
        this.h = 10;
        this.v = 10;
        this.a = Double.NaN;
        this.color = Color.black;
        this.lineWidth = 1;
        this.changed = true;
    }
    
    public DrawGeometric(final int shape, final Value value, final Value value2, final Value value3, final Value value4) {
        this.h = 10;
        this.v = 10;
        this.a = Double.NaN;
        this.color = Color.black;
        this.lineWidth = 1;
        this.changed = true;
        this.setShape(shape);
        this.setPoints(value, value2, value3, value4);
    }
    
    public DrawGeometric(final int shape, final Value value, final Value value2, final int n, final int n2) {
        this.h = 10;
        this.v = 10;
        this.a = Double.NaN;
        this.color = Color.black;
        this.lineWidth = 1;
        this.changed = true;
        this.setShape(shape);
        this.setPoints(value, value2, n, n2);
    }
    
    public void setShape(final int shape) {
        if (shape < 0 || shape > 11) {
            throw new IllegalArgumentException("Internal error:  Illegal value for shape of DrawGeometric object.");
        }
        this.shape = shape;
        this.needsRedraw();
    }
    
    public void setPoints(final Value x1, final Value y1, final Value x2, final Value y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.compute();
    }
    
    public void setPoints(final Value x1, final Value y1, final int h, final int v) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = null;
        this.y2 = null;
        this.h = h;
        this.v = v;
        this.compute();
    }
    
    public void setX1(final Value x1) {
        this.x1 = x1;
        this.compute();
    }
    
    public Value getX1() {
        return this.x1;
    }
    
    public void setX2(final Value x2) {
        this.x2 = x2;
        this.compute();
    }
    
    public Value getX2() {
        return this.x2;
    }
    
    public void setY1(final Value y1) {
        this.y1 = y1;
        this.compute();
    }
    
    public Value getY1() {
        return this.y1;
    }
    
    public void setY2(final Value y2) {
        this.y2 = y2;
        this.compute();
    }
    
    public Value getY2() {
        return this.y2;
    }
    
    public void setH(final int h) {
        this.h = h;
        this.x2 = null;
        this.compute();
    }
    
    public int getH() {
        return this.h;
    }
    
    public void setV(final int v) {
        this.v = v;
        this.y2 = null;
        this.needsRedraw();
    }
    
    public int getV() {
        return this.v;
    }
    
    public void setColor(final Color color) {
        this.color = ((color == null) ? Color.black : color);
        this.needsRedraw();
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
        this.needsRedraw();
    }
    
    public Color getFillColor() {
        return this.fillColor;
    }
    
    public void setLineWidth(final int lineWidth) {
        if (lineWidth != this.lineWidth) {
            this.lineWidth = lineWidth;
            if (this.lineWidth > 10) {
                this.lineWidth = 10;
            }
            else if (this.lineWidth < 0) {
                this.lineWidth = 0;
            }
            this.needsRedraw();
        }
    }
    
    public int getLineWidth() {
        return this.lineWidth;
    }
    
    public void compute() {
        this.changed = true;
        this.needsRedraw();
    }
    
    private void doValues() {
        if (this.x1 != null) {
            this.a = this.x1.getVal();
        }
        if (this.y1 != null) {
            this.b = this.y1.getVal();
        }
        if (this.x2 != null) {
            this.c = this.x2.getVal();
        }
        if (this.y2 != null) {
            this.d = this.y2.getVal();
        }
        this.changed = false;
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (this.changed) {
            this.doValues();
        }
        if (super.coords == null || this.x1 == null || this.y1 == null || Double.isNaN(this.a) || Double.isNaN(this.b) || Double.isInfinite(this.a) || Double.isInfinite(this.b)) {
            return;
        }
        if (this.x2 != null && (Double.isNaN(this.c) || Double.isInfinite(this.c))) {
            return;
        }
        if (this.y2 != null && (Double.isNaN(this.d) || Double.isInfinite(this.d))) {
            return;
        }
        double xToPixelDouble = this.xToPixelDouble(this.a);
        double yToPixelDouble = this.yToPixelDouble(this.b);
        double n;
        if (this.x2 == null) {
            n = this.h;
        }
        else if (this.shape <= 3) {
            n = this.xToPixelDouble(this.c) - xToPixelDouble;
        }
        else {
            n = this.c / super.coords.getPixelWidth();
        }
        double n2;
        if (this.y2 == null) {
            n2 = -this.v;
        }
        else if (this.shape <= 3) {
            n2 = this.yToPixelDouble(this.d) - yToPixelDouble;
        }
        else {
            n2 = -this.d / super.coords.getPixelHeight();
        }
        if (this.shape == 1 || this.shape == 5) {
            this.drawInfiniteLine(graphics, xToPixelDouble, yToPixelDouble, n, n2);
        }
        else if (this.shape == 11) {
            this.drawCross(graphics, (int)xToPixelDouble, (int)yToPixelDouble, (int)(Math.abs(n) + 0.5), (int)(Math.abs(n2) + 0.5));
        }
        else if (this.shape == 4 || this.shape == 0) {
            this.drawLine(graphics, (int)xToPixelDouble, (int)yToPixelDouble, (int)(xToPixelDouble + n), (int)(yToPixelDouble + n2));
        }
        else if (this.shape == 8) {
            this.drawLine(graphics, (int)(xToPixelDouble - Math.abs(n) + 1.0), (int)(yToPixelDouble - Math.abs(n2) + 1.0), (int)(xToPixelDouble + Math.abs(n)), (int)(yToPixelDouble + Math.abs(n2)));
        }
        else if (this.shape <= 7) {
            if (n < 0.0) {
                n = -n;
                xToPixelDouble -= n;
            }
            if (n2 < 0.0) {
                n2 = -n2;
                yToPixelDouble -= n2;
            }
            this.drawShape(graphics, (int)xToPixelDouble, (int)yToPixelDouble, (int)(n + 0.5), (int)(n2 + 0.5));
        }
        else {
            this.drawShape(graphics, (int)(xToPixelDouble - Math.abs(n) + 1.0), (int)(yToPixelDouble - Math.abs(n2) + 1.0), (int)(2.0 * Math.abs(n) - 0.5), (int)(2.0 * Math.abs(n2) - 0.5));
        }
    }
    
    private double xToPixelDouble(final double n) {
        return super.coords.getLeft() + super.coords.getGap() + (n - super.coords.getXmin()) / (super.coords.getXmax() - super.coords.getXmin()) * (super.coords.getWidth() - 2 * super.coords.getGap() - 1);
    }
    
    private double yToPixelDouble(final double n) {
        return super.coords.getTop() + super.coords.getGap() + (super.coords.getYmax() - n) / (super.coords.getYmax() - super.coords.getYmin()) * (super.coords.getHeight() - 2 * super.coords.getGap() - 1);
    }
    
    private void drawLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        graphics.setColor(this.color);
        if (abs == 0 && abs2 == 0) {
            graphics.drawLine(n, n2, n, n2);
        }
        else if (abs > abs2) {
            for (int i = 0; i < this.lineWidth; ++i) {
                graphics.drawLine(n, n2 - this.lineWidth / 2 + i, n3, n4 - this.lineWidth / 2 + i);
            }
        }
        else {
            for (int j = 0; j < this.lineWidth; ++j) {
                graphics.drawLine(n - this.lineWidth / 2 + j, n2, n3 - this.lineWidth / 2 + j, n4);
            }
        }
    }
    
    private void drawShape(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n > super.coords.getLeft() + super.coords.getWidth() || n2 > super.coords.getTop() + super.coords.getHeight() || n + n3 < super.coords.getLeft() || n2 + n4 < super.coords.getTop()) {
            return;
        }
        if (this.fillColor != null) {
            graphics.setColor(this.fillColor);
            if (this.shape == 2 || this.shape == 6 || this.shape == 9) {
                graphics.fillRect(n, n2, n3, n4);
            }
            else {
                graphics.fillOval(n, n2, n3, n4);
            }
        }
        graphics.setColor(this.color);
        if (this.shape == 2 || this.shape == 6 || this.shape == 9) {
            for (int i = 0; i < this.lineWidth; ++i) {
                graphics.drawRect(n + i, n2 + i, n3 - 2 * i, n4 - 2 * i);
            }
        }
        else {
            for (int j = 0; j < this.lineWidth; ++j) {
                graphics.drawOval(n + j, n2 + j, n3 - 2 * j, n4 - 2 * j);
            }
        }
    }
    
    private void drawCross(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (n - n3 > super.coords.getLeft() + super.coords.getWidth() || n2 - n4 > super.coords.getTop() + super.coords.getHeight() || n + n3 < super.coords.getLeft() || n2 + n4 < super.coords.getTop()) {
            return;
        }
        final int n5 = n - this.lineWidth / 2;
        final int n6 = n2 - this.lineWidth / 2;
        graphics.setColor(this.color);
        for (int i = 0; i < this.lineWidth; ++i) {
            graphics.drawLine(n - n3, n6 + i, n + n3, n6 + i);
        }
        for (int j = 0; j < this.lineWidth; ++j) {
            graphics.drawLine(n5 + j, n2 - n4, n5 + j, n2 + n4);
        }
    }
    
    private void drawInfiniteLine(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        if (Math.abs(n3) < 1.0E-10 && Math.abs(n4) < 1.0E-10) {
            return;
        }
        graphics.setColor(this.color);
        if (Math.abs(n4) > Math.abs(n3)) {
            final double n5 = n3 / n4;
            final int n6 = super.coords.getTop() - 5;
            final int n7 = super.coords.getTop() + super.coords.getHeight() + 5;
            final int n8 = (int)(n5 * (n6 - n2) + n);
            final int n9 = (int)(n5 * (n7 - n2) + n);
            if (Math.abs(n8) < 20000 && Math.abs(n9) < 20000) {
                for (int i = 0; i < this.lineWidth; ++i) {
                    graphics.drawLine(n8 - this.lineWidth / 2 + i, n6, n9 - this.lineWidth / 2 + i, n7);
                }
            }
        }
        else {
            final double n10 = n4 / n3;
            final int n11 = super.coords.getLeft() - 5;
            final int n12 = super.coords.getLeft() + super.coords.getWidth() + 5;
            final int n13 = (int)(n10 * (n11 - n) + n2);
            final int n14 = (int)(n10 * (n12 - n) + n2);
            if (Math.abs(n13) < 20000 && Math.abs(n14) < 20000) {
                for (int j = 0; j < this.lineWidth; ++j) {
                    graphics.drawLine(n11, n13 - this.lineWidth / 2 + j, n12, n14 - this.lineWidth / 2 + j);
                }
            }
        }
    }
}
