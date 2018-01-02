// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

import java.awt.Point;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;

public class Border extends Panel
{
    protected int thickness;
    protected int gap;
    protected DrawnRectangle border;
    protected Component borderMe;
    protected static int _defaultThickness;
    protected static int _defaultGap;
    
    public Border() {
        this(new HintPanel(), Border._defaultThickness, Border._defaultGap);
    }
    
    public Border(final Component component) {
        this(component, Border._defaultThickness, Border._defaultGap);
    }
    
    public Border(final Component component, final int n) {
        this(component, n, Border._defaultGap);
    }
    
    public Border(final Component borderMe, final int thickness, final int gap) {
        this.borderMe = borderMe;
        this.thickness = thickness;
        this.gap = gap;
        this.setLayout(new BorderLayout());
        this.add(borderMe, "Center");
    }
    
    public Component getComponent() {
        return this.borderMe;
    }
    
    public Rectangle getInnerBounds() {
        return this.border().getInnerBounds();
    }
    
    public void setLineColor(final Color lineColor) {
        this.border().setLineColor(lineColor);
        this.repaint();
    }
    
    public Color getLineColor() {
        return this.border().getLineColor();
    }
    
    public void paint(final Graphics graphics) {
        this.border().paint();
        super.paint(graphics);
    }
    
    public Insets getInsets() {
        return new Insets(this.thickness + this.gap, this.thickness + this.gap, this.thickness + this.gap, this.thickness + this.gap);
    }
    
    public void setSize(final int n, final int n2) {
        final Point location = this.getLocation();
        this.setBounds(location.x, location.y, n, n2);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.border().setSize(n3, n4);
    }
    
    protected String paramString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(super.paramString()).concat(String.valueOf(",border="))).concat(String.valueOf(this.border().toString()))).concat(String.valueOf(",thickness="))).concat(String.valueOf(this.thickness))).concat(String.valueOf(",gap="))).concat(String.valueOf(this.gap));
    }
    
    protected DrawnRectangle border() {
        if (this.border == null) {
            this.border = new DrawnRectangle(this, this.thickness);
        }
        return this.border;
    }
    
    public void setHint(final String bubbleHelp) {
        if (this.borderMe instanceof HintPanel) {
            ((HintPanel)this.borderMe).setBubbleHelp(bubbleHelp);
        }
    }
    
    public void setThickness(final int n) {
        this.thickness = n;
        this.border().setThickness(n);
        this.repaint();
    }
    
    public int getThickness() {
        return this.thickness;
    }
    
    public void setGap(final int gap) {
        this.gap = gap;
        this.repaint();
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public void setFillColor(final Color fillColor) {
        this.border().setFillColor(fillColor);
        this.repaint();
    }
    
    public Color getFillColor() {
        return this.border().getFillColor();
    }
    
    static {
        Border._defaultThickness = 2;
        Border._defaultGap = 0;
    }
}
