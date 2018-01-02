// 
// Decompiled by Procyon v0.5.30
// 

package com.island.clients.ds.bv;

import java.awt.Rectangle;
import java.awt.Label;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class DataLabel
{
    int x;
    int y;
    int w;
    int h;
    int alignment;
    String text;
    Color bgColor;
    Color color;
    boolean visible;
    Font font;
    
    public DataLabel() {
        this.color = Color.black;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public boolean getVisible() {
        return this.visible;
    }
    
    public void paint(final Graphics g) {
        g.setFont(this.font);
        final FontMetrics fontmetrics = g.getFontMetrics();
        if (this.bgColor != null) {
            g.setColor(this.bgColor);
            g.fillRect(this.x, this.y, this.w, this.h);
        }
        int i = 0;
        switch (this.alignment) {
            case 0: {
                i = this.x;
                break;
            }
            case 2: {
                i = this.x + this.w - fontmetrics.stringWidth(this.text) - 1;
                break;
            }
            default: {
                i = this.x + this.w / 2 - fontmetrics.stringWidth(this.text) / 2;
                break;
            }
        }
        g.setColor(this.color);
        if (this.font != null) {
            g.setFont(this.font);
        }
        g.drawString(this.text, i, this.y + (this.h - fontmetrics.getHeight()) / 2 + fontmetrics.getAscent());
    }
    
    public void setAlignment(final int i) {
        this.alignment = i;
    }
    
    public void setBackground(final Color color1) {
        this.bgColor = color1;
    }
    
    public void setBounds(final int i, final int j, final int k, final int l) {
        this.x = i;
        this.y = j;
        this.w = k;
        this.h = l;
    }
    
    public void changeBounds(final Label startingLabel, final int i) {
        final Rectangle rectangle = startingLabel.getBounds();
        this.setBounds(rectangle.x, rectangle.y + (i + 1) * (rectangle.height + 2), rectangle.width, rectangle.height);
    }
    
    public void setColor(final Color color1) {
        this.color = color1;
    }
    
    public void setFont(final Font font1) {
        this.font = font1;
    }
    
    public void setText(final String s) {
        this.text = s;
    }
    
    public void setVisible(final boolean flag) {
        this.visible = flag;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
