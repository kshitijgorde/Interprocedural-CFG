// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

public class ImagePanel extends Panel
{
    Image \u00cd;
    boolean showBorder;
    boolean hideString;
    String \u00cc;
    int \u00ce;
    int \u00cf;
    
    public ImagePanel() {
        this.showBorder = false;
        this.hideString = false;
    }
    
    public ImagePanel(final int n, final int n2, final int n3, final int n4) {
        this.showBorder = false;
        this.hideString = false;
        this.reshape(n, n2, n3, n4);
    }
    
    public ImagePanel(final Image \u00ed) {
        this.showBorder = false;
        this.hideString = false;
        this.\u00cd = \u00ed;
    }
    
    public void setString(final String \u00ec) {
        this.\u00cc = \u00ec;
        this.repaint();
    }
    
    public void setImage(final Image \u00ed) {
        this.\u00cd = \u00ed;
        this.repaint();
    }
    
    public void setStringPosition(final int \u00ee, final int \u00ef) {
        this.\u00ce = \u00ee;
        this.\u00cf = \u00ef;
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
        if (this.\u00cd != null) {
            graphics.drawImage(this.\u00cd, 0, 0, this);
        }
        if (this.\u00cc != null && !this.hideString) {
            final int stringWidth = this.getFontMetrics(this.getFont()).stringWidth(this.\u00cc);
            final int size = this.getFont().getSize();
            if (this.\u00ce == 0 && this.\u00cf == 0) {
                this.\u00ce = this.size().width / 2 - stringWidth / 2;
                this.\u00cf = this.size().height / 2 + size / 2;
            }
            graphics.drawString(this.\u00cc, this.\u00ce, this.\u00cf);
        }
        if (this.showBorder) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        }
    }
}
