// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Canvas;

public class TextCanvas extends Canvas
{
    int \u00d3;
    String \u00d4;
    
    public TextCanvas() {
        this.\u00d4 = null;
        this.repaint();
    }
    
    public TextCanvas(final String \u00f4, final Font font) {
        this.\u00d4 = \u00f4;
        this.setFont(font);
        this.repaint();
    }
    
    public void setText(final String \u00f4, final Font font) {
        this.\u00d4 = \u00f4;
        this.setFont(font);
        this.repaint();
    }
    
    public void setText(final String \u00f4) {
        this.\u00d4 = \u00f4;
        this.repaint();
    }
    
    public void setAlignment(final int n) {
        this.\u00d3 = this.\u00d3;
    }
    
    public void drawDescription(final Graphics graphics) {
        while (!this.textFits(graphics)) {
            this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), this.getFont().getSize() - 1));
        }
        graphics.setColor(this.getForeground());
        this.getFont().getSize();
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
        final int charWidth = fontMetrics.charWidth(' ');
        final int n = 0;
        int i = 0;
        int n2 = this.\u00d4.indexOf(" ", i);
        if (n2 == -1) {
            n2 = this.\u00d4.length();
        }
        int n3 = n + 5;
        int n4 = this.getFont().getSize();
        while (i < this.\u00d4.length()) {
            final String trim = this.\u00d4.substring(i, n2).trim();
            if (trim.equals("<n>")) {
                n3 = n + 5;
                n4 += (int)(this.getFont().getSize() * 1.3);
            }
            else {
                final int stringWidth = fontMetrics.stringWidth(trim);
                if (n3 + stringWidth > this.getSize().width - 5) {
                    n3 = n + 5;
                    n4 += (int)(this.getFont().getSize() * 1.3);
                }
                graphics.drawString(trim, n3, n4);
                n3 += stringWidth + charWidth;
            }
            i = n2 + 1;
            n2 = this.\u00d4.indexOf(" ", i);
            if (n2 < 0) {
                n2 = this.\u00d4.length();
            }
        }
    }
    
    public boolean textFits(final Graphics graphics) {
        this.getFont().getSize();
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
        final int charWidth = fontMetrics.charWidth(' ');
        final int n = 0;
        int i = 0;
        int n2 = this.\u00d4.indexOf(" ", i);
        if (n2 == -1) {
            n2 = this.\u00d4.length();
        }
        int n3 = n + 5;
        int n4 = this.getFont().getSize();
        while (i < this.\u00d4.length()) {
            final int stringWidth = fontMetrics.stringWidth(this.\u00d4.substring(i, n2));
            if (n3 + stringWidth > this.getSize().width - 5) {
                n3 = n + 5;
                n4 += (int)(this.getFont().getSize() * 1.3);
                if (n4 > this.getSize().height) {
                    return false;
                }
            }
            n3 += stringWidth + charWidth;
            i = n2 + 1;
            n2 = this.\u00d4.indexOf(" ", i);
            if (n2 < 0) {
                n2 = this.\u00d4.length();
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setColor(Color.black);
        graphics.setColor(Color.black);
        if (this.\u00d4 != null) {
            this.drawDescription(graphics);
        }
    }
}
