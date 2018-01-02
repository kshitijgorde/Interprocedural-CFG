// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Canvas;

class HeaderCanvas extends Canvas
{
    String[] text;
    Point[] pos;
    Font[] font;
    Color[] color1;
    Color[] color2;
    int count;
    Color borderColor;
    
    public void addCaption(final String s, final Point p, final Font f, final Color c1, final Color c2) {
        this.text[this.count] = s;
        this.pos[this.count] = p;
        this.font[this.count] = f;
        this.color1[this.count] = c1;
        this.color2[this.count] = c2;
        ++this.count;
    }
    
    private void drawBorder(final Graphics g) {
        if (this.borderColor != null) {
            g.setColor(this.borderColor);
            final Rectangle bounds = this.bounds();
            g.drawRect(bounds.x, bounds.y, bounds.width, bounds.y);
        }
    }
    
    public void paint(final Graphics g) {
        for (int x = 0; x < this.count; ++x) {
            g.setColor(this.color1[x]);
            g.setFont(this.font[x]);
            g.drawString(this.text[x], this.pos[x].x, this.pos[x].y);
        }
        this.drawBorder(g);
    }
    
    protected HeaderCanvas(final int width, final int height) {
        this.count = 0;
        this.borderColor = null;
        this.setSize(width, height);
    }
    
    protected HeaderCanvas() {
        this.count = 0;
        this.borderColor = null;
    }
}
