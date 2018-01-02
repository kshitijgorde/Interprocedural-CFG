// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.editors;

import java.awt.Insets;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Panel;

public class ColorToggle extends Panel
{
    private boolean state;
    private Color borderColor;
    private Color color;
    private String colorInit;
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBorderColor());
        graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, !this.getState());
        if (this.getColor() == null) {
            final FontMetrics fontMetrics = this.getFontMetrics(graphics.getFont());
            graphics.setColor(Color.white);
            graphics.fillRect(2, 2, size.width - 5, size.height - 5);
            graphics.setColor(Color.black);
            graphics.drawString("<default>", (size.width - 10 - fontMetrics.stringWidth("<default>")) / 2, (fontMetrics.getAscent() + size.height) / 2);
        }
        else {
            graphics.setColor(this.getColor());
            graphics.fillRect(2, 2, size.width - 5, size.height - 5);
        }
    }
    
    public void setColorInit(final String colorInit) {
        this.colorInit = colorInit;
    }
    
    public void setBorderColor(final Color borderColor) {
        this.borderColor = borderColor;
    }
    
    public Insets getInsets() {
        return new Insets(7, 40, 7, 40);
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public void setState(final boolean state) {
        this.state = state;
        this.invalidate();
        this.getParent().validate();
        this.repaint();
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public String getColorInit() {
        return this.colorInit;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public ColorToggle(final String colorInit, final Color color) {
        this.state = false;
        this.borderColor = Color.lightGray;
        this.color = null;
        this.setColorInit(colorInit);
        this.setColor(color);
    }
    
    public void setColor(final Color color) {
        if (color == null) {
            super.setBackground(Color.white);
            this.repaint();
        }
        else if (this.color == null && this.getComponentCount() != 0) {
            this.removeAll();
            this.repaint();
        }
        this.color = color;
    }
}
