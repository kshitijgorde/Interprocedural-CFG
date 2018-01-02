// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

public class WebTitleCanvas extends Canvas implements Cloneable
{
    public int w;
    public String text;
    public Color c;
    int fontSize;
    int textPos;
    boolean border;
    int canvH;
    
    public WebTitleCanvas(final int w, final String text, final Color background, final int fontSize, final int textPos, final int canvH, final boolean border) {
        this.c = Color.red;
        this.w = w;
        this.text = text;
        this.fontSize = fontSize;
        this.textPos = textPos;
        this.border = border;
        this.setBackground(background);
        this.canvH = canvH;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public void setWidth(final int w) {
        this.w = w;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(new Font("Dialog", 1, this.fontSize));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(this.c);
        graphics.drawString(this.text, this.w / 2 - fontMetrics.stringWidth(this.text) / 2, this.textPos);
        if (this.border) {
            graphics.setColor(this.c.darker());
            graphics.drawRect(0, 0, this.w - 1, this.textPos + 6);
        }
    }
    
    public synchronized Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.w, this.canvH);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
