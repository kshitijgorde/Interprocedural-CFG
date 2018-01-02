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
    String \u00d4;
    Image \u00cd;
    Image \u00d9;
    Color \u00da;
    Font \u00db;
    int \u00dc;
    int \u00dd;
    int \u00de;
    
    public TimerCanvas(final Image \u00ed) {
        this.\u00da = Color.black;
        this.\u00db = new Font("Roman", 1, 12);
        this.\u00cd = \u00ed;
        this.\u00dc = 0;
        this.\u00dd = 15;
        this.\u00de = 0;
    }
    
    public void setFontColor(final Color \u00fa) {
        this.\u00da = \u00fa;
    }
    
    public void setText(final String \u00f4) {
        this.\u00d4 = \u00f4;
        if (this.\u00de != \u00f4.length()) {
            final Font \u00fb = new Font("Roman", 1, this.getFontSize(\u00f4));
            final FontMetrics fontMetrics = this.getFontMetrics(\u00fb);
            final int stringWidth = fontMetrics.stringWidth(\u00f4);
            final int size = fontMetrics.getFont().getSize();
            fontMetrics.getAscent();
            final int descent = fontMetrics.getDescent();
            this.\u00dc = this.size().width / 2 - stringWidth / 2;
            this.\u00dd = this.size().height / 2 + (size - descent) / 2;
            this.\u00db = \u00fb;
            this.\u00de = \u00f4.length();
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
                this.\u00d4 = "error";
            }
        }
        return n;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00d9 == null) {
            this.\u00d9 = this.createImage(this.size().width, this.size().height);
        }
        else {
            final Graphics graphics2 = this.\u00d9.getGraphics();
            graphics2.clearRect(0, 0, this.size().width, this.size().height);
            if (this.\u00cd != null) {
                graphics2.drawImage(this.\u00cd, 0, 0, null);
            }
            if (this.\u00d4 != null) {
                graphics2.setFont(this.\u00db);
                graphics2.setColor(this.\u00da);
                graphics2.drawString(this.\u00d4, this.\u00dc, this.\u00dd);
            }
        }
        graphics.drawImage(this.\u00d9, 0, 0, null);
    }
}
