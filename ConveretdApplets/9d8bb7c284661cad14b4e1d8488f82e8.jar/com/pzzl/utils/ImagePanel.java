// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

public class ImagePanel extends Panel2
{
    Image \u00c2;
    boolean showBorder;
    boolean hideString;
    String \u00c3;
    int \u00c4;
    int \u00c5;
    int \u00c6;
    int \u00c7;
    int \u00fe;
    int \u00ff;
    Color \u00cd;
    int \u0100;
    
    public ImagePanel() {
        this.showBorder = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u0100 = 1;
    }
    
    public ImagePanel(final int n, final int n2, final int n3, final int n4) {
        this.showBorder = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u0100 = 1;
        this.reshape(n, n2, n3, n4);
    }
    
    public ImagePanel(final Image \u00e2, final int n, final int n2) {
        this.showBorder = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u0100 = 1;
        this.setSize(n, n2);
        this.\u00c2 = \u00e2;
    }
    
    public ImagePanel(final Image \u00e2) {
        this.showBorder = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u0100 = 1;
        this.\u00c2 = \u00e2;
    }
    
    public void setBorderColor(final Color \u00ed) {
        this.\u00cd = \u00ed;
        this.repaint();
    }
    
    public void setBorderWidth(final int \u0101) {
        this.\u0100 = \u0101;
    }
    
    public void setString(final String \u00e3) {
        this.\u00c3 = \u00e3;
        this.repaint();
    }
    
    public void setImage(final Image \u00e2) {
        this.\u00c2 = \u00e2;
        this.repaint();
    }
    
    public void setImage(final Image \u00e2, final int \u00e6, final int \u00e7) {
        this.\u00c6 = \u00e6;
        this.\u00c7 = \u00e7;
        this.\u00c2 = \u00e2;
        this.repaint();
    }
    
    public void setImagePosition(final int \u00fe, final int \u00ff) {
        this.\u00fe = \u00fe;
        this.\u00ff = \u00ff;
        this.repaint();
    }
    
    public void setStringPosition(final int \u00e4, final int \u00e5) {
        this.\u00c4 = \u00e4;
        this.\u00c5 = \u00e5;
    }
    
    public void hideString() {
        this.hideString = true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void showBorder(final boolean showBorder) {
        this.showBorder = showBorder;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00c2 != null) {
            if (this.\u00c6 == 0 || this.\u00c7 == 0) {
                graphics.drawImage(this.\u00c2, this.\u00fe, this.\u00ff, this);
            }
            else {
                graphics.drawImage(this.\u00c2, this.\u00fe, this.\u00ff, this.\u00c6, this.\u00c7, this);
            }
        }
        else {
            graphics.clearRect(0, 0, this.size().width, this.size().height);
        }
        if (this.\u00c3 != null && !this.hideString) {
            final int stringWidth = this.getFontMetrics(this.getFont()).stringWidth(this.\u00c3);
            final int size = this.getFont().getSize();
            if (this.\u00c4 == 0 && this.\u00c5 == 0) {
                this.\u00c4 = this.size().width / 2 - stringWidth / 2;
                this.\u00c5 = this.size().height / 2 + size / 2;
            }
            graphics.drawString(this.\u00c3, this.\u00c4, this.\u00c5);
        }
        if (this.showBorder) {
            graphics.setColor(this.\u00cd);
            for (int i = 0; i < this.\u0100; ++i) {
                graphics.drawRect(i, i, this.size().width - 1 - i * 2, this.size().height - 1 - i * 2);
            }
        }
    }
}
