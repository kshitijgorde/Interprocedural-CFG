// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class TimerCanvas extends Canvas
{
    String \u0104;
    Image \u00c2;
    Image \u00e1;
    Color \u0108;
    Font \u0109;
    int \u010a;
    int \u010b;
    int \u010c;
    
    public TimerCanvas(final Image \u00e2) {
        this.\u0108 = Color.black;
        this.\u0109 = new Font("Roman", 1, 12);
        this.\u00c2 = \u00e2;
        this.\u010a = 0;
        this.\u010b = 15;
        this.\u010c = 0;
    }
    
    public void setFontColor(final Color \u0109) {
        this.\u0108 = \u0109;
    }
    
    public void setText(final String \u0105) {
        this.\u0104 = \u0105;
        if (this.\u010c != \u0105.length()) {
            final Font \u0109 = new Font("Roman", 1, this.getFontSize(\u0105));
            final FontMetrics fontMetrics = this.getFontMetrics(\u0109);
            final int stringWidth = fontMetrics.stringWidth(\u0105);
            final int size = fontMetrics.getFont().getSize();
            fontMetrics.getAscent();
            final int descent = fontMetrics.getDescent();
            this.\u010a = this.size().width / 2 - stringWidth / 2;
            this.\u010b = this.size().height / 2 + (size - descent) / 2;
            this.\u0109 = \u0109;
            this.\u010c = \u0105.length();
            return;
        }
        this.repaint();
    }
    
    public int getFontSize(final String s) {
        int n = 40;
        boolean b = true;
        while (b) {
            final FontMetrics fontMetrics = this.getFontMetrics(new Font("Roman", 1, n));
            final int stringWidth = fontMetrics.stringWidth(s);
            final int size = fontMetrics.getFont().getSize();
            if (stringWidth < this.size().width - 4 && size < this.size().height) {
                b = false;
            }
            else {
                if (--n != 0) {
                    continue;
                }
                n = 12;
                this.\u0104 = "error";
            }
        }
        return n;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00e1 == null) {
            this.\u00e1 = this.createImage(this.size().width, this.size().height);
        }
        else {
            final Graphics graphics2 = this.\u00e1.getGraphics();
            graphics2.clearRect(0, 0, this.size().width, this.size().height);
            if (this.\u00c2 != null) {
                graphics2.drawImage(this.\u00c2, 0, 0, null);
            }
            if (this.\u0104 != null) {
                graphics2.setFont(this.\u0109);
                graphics2.setColor(this.\u0108);
                graphics2.drawString(this.\u0104, this.\u010a, this.\u010b);
            }
        }
        graphics.drawImage(this.\u00e1, 0, 0, null);
    }
}
