// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;

public class DrawnRectangle extends Rectangle
{
    protected static int _defaultThickness;
    protected Component drawInto;
    private int thick;
    private Color lineColor;
    private Color fillColor;
    
    public DrawnRectangle(final Component component) {
        this(component, DrawnRectangle._defaultThickness, 0, 0, 0, 0);
    }
    
    public DrawnRectangle(final Component component, final int n) {
        this(component, n, 0, 0, 0, 0);
    }
    
    public DrawnRectangle(final Component component, final int n, final int n2, final int n3, final int n4) {
        this(component, DrawnRectangle._defaultThickness, n, n2, n3, n4);
    }
    
    public DrawnRectangle(final Component drawInto, final int thick, final int n, final int n2, final int n3, final int n4) {
        Assert.notNull(drawInto);
        Assert.notFalse(thick > 0);
        this.drawInto = drawInto;
        this.thick = thick;
        this.setBounds(n, n2, n3, n4);
    }
    
    public Component component() {
        return this.drawInto;
    }
    
    public int getThickness() {
        return this.thick;
    }
    
    public void setThickness(final int thick) {
        this.thick = thick;
    }
    
    public void setLineColor(final Color lineColor) {
        this.lineColor = lineColor;
    }
    
    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }
    
    public void fill() {
        this.fill(this.getFillColor());
    }
    
    public Color getLineColor() {
        if (this.lineColor == null) {
            this.lineColor = SystemColor.controlShadow;
        }
        return this.lineColor;
    }
    
    public Color getFillColor() {
        if (this.fillColor == null) {
            this.fillColor = this.drawInto.getBackground();
        }
        return this.fillColor;
    }
    
    public Rectangle getInnerBounds() {
        return new Rectangle(super.x + this.thick, super.y + this.thick, super.width - this.thick * 2, super.height - this.thick * 2);
    }
    
    public void paint() {
        this.paintFlat(this.drawInto.getGraphics(), this.getLineColor());
    }
    
    private void paintFlat(final Graphics graphics, final Color color) {
        if (graphics != null) {
            graphics.setColor(color);
            for (int i = 0; i < this.thick; ++i) {
                graphics.drawRect(super.x + i, super.y + i, super.width - i * 2 - 1, super.height - i * 2 - 1);
            }
            graphics.dispose();
        }
    }
    
    public void clearInterior() {
        this.fill(this.drawInto.getBackground());
    }
    
    public void clearExterior() {
        this.paintFlat(this.drawInto.getGraphics(), this.drawInto.getBackground());
    }
    
    public void clear() {
        this.clearExterior();
        this.clearInterior();
    }
    
    public void fill(final Color color) {
        final Graphics graphics = this.drawInto.getGraphics();
        if (graphics != null) {
            final Rectangle innerBounds = this.getInnerBounds();
            graphics.setColor(color);
            graphics.fillRect(innerBounds.x, innerBounds.y, innerBounds.width, innerBounds.height);
            this.setFillColor(color);
            graphics.dispose();
        }
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(super.toString()).concat(String.valueOf("["))).concat(String.valueOf(this.paramString()))).concat(String.valueOf("]"));
    }
    
    public String paramString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("color=").concat(String.valueOf(this.getLineColor()))).concat(String.valueOf(",thickness="))).concat(String.valueOf(this.thick))).concat(String.valueOf(",fillColor="))).concat(String.valueOf(this.getFillColor()));
    }
    
    protected Color brighter() {
        return this.getLineColor().brighter().brighter().brighter().brighter();
    }
    
    static {
        DrawnRectangle._defaultThickness = 2;
    }
}
