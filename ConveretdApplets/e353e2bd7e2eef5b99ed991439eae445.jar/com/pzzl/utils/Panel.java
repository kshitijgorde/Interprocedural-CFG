// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;

public class Panel extends java.awt.Panel
{
    Image \u00cd;
    boolean \u00d0;
    
    public Panel() {
        this.\u00d0 = false;
        this.\u00d0 = false;
    }
    
    public Panel(final boolean \u00f0) {
        this.\u00d0 = false;
        this.\u00d0 = \u00f0;
    }
    
    public Panel(final int n, final int n2, final boolean \u00f0) {
        this.\u00d0 = false;
        this.\u00d0 = \u00f0;
    }
    
    public Panel(final int n, final int n2, final Image \u00ed) {
        this.\u00d0 = false;
        this.resize(n, n2);
        this.\u00cd = \u00ed;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return super.mouseDown(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00d0) {
            graphics.setColor(Color.black);
            graphics.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        }
        if (this.\u00cd != null) {
            graphics.drawImage(this.\u00cd, 0, 0, this.\u00cd.getWidth(this), this.\u00cd.getHeight(this), this);
        }
    }
}
