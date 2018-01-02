// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

public class Panel2 extends Panel
{
    Image \u00c2;
    boolean \u00ef;
    Color \u00cd;
    boolean mousePressed;
    Color \u00ca;
    Color \u00cb;
    Color \u00cc;
    Panel2 \u00c8;
    
    public Panel2() {
        this.\u00ef = false;
        this.\u00cd = Color.black;
        this.mousePressed = false;
        this.\u00ca = Color.black;
        this.\u00cb = Color.black;
        this.\u00cc = Color.black;
    }
    
    public Panel2(final boolean b) {
        this.\u00ef = false;
        this.\u00cd = Color.black;
        this.mousePressed = false;
        this.\u00ca = Color.black;
        this.\u00cb = Color.black;
        this.\u00cc = Color.black;
        if (b) {
            this.\u00ef = true;
            this.\u00cd = Color.black;
        }
    }
    
    public Panel2(final int n, final int n2, final boolean b) {
        this.\u00ef = false;
        this.\u00cd = Color.black;
        this.mousePressed = false;
        this.\u00ca = Color.black;
        this.\u00cb = Color.black;
        this.\u00cc = Color.black;
        if (b) {
            this.\u00ef = true;
            this.\u00cd = Color.black;
        }
    }
    
    public Panel2(final int n, final int n2, final Image \u00e2) {
        this.\u00ef = false;
        this.\u00cd = Color.black;
        this.mousePressed = false;
        this.\u00ca = Color.black;
        this.\u00cb = Color.black;
        this.\u00cc = Color.black;
        this.resize(n, n2);
        this.\u00c2 = \u00e2;
    }
    
    public void setImage(final Image \u00e2) {
        this.\u00c2 = \u00e2;
    }
    
    public void setParent(final Panel2 \u00e8) {
        this.\u00c8 = \u00e8;
    }
    
    public void setBorder(final boolean \u00ef) {
        this.\u00ef = \u00ef;
        this.repaint();
    }
    
    public void setBorderColor(final Color \u00ed) {
        this.\u00cd = \u00ed;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00ef) {
            graphics.setColor(this.\u00cd);
            graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        }
        if (this.\u00c2 != null) {
            graphics.drawImage(this.\u00c2, 0, 0, this.\u00c2.getWidth(this), this.\u00c2.getHeight(this), this);
        }
    }
}
