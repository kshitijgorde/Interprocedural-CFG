// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.awt.Image;

public class ImageHyperLinkPanel extends Panel2
{
    Image \u00c2;
    Image \u00f9;
    Image \u00fa;
    String \u00fb;
    Applet \u00fc;
    String \u00fd;
    int \u00fe;
    int \u00ff;
    
    public ImageHyperLinkPanel(final Applet \u00fc, final Image image, final Image \u00fa, final String \u00fb) {
        this.\u00fd = "_blank";
        this.\u00fc = \u00fc;
        this.\u00c2 = image;
        this.\u00f9 = image;
        this.\u00fa = \u00fa;
        this.\u00fb = \u00fb;
        this.setImage(this.\u00c2);
    }
    
    public ImageHyperLinkPanel(final Applet \u00fc, final Image image, final Image \u00fa, final String \u00fb, final String \u00fd) {
        this.\u00fd = "_blank";
        this.\u00fd = \u00fd;
        this.\u00fc = \u00fc;
        this.\u00c2 = image;
        this.\u00f9 = image;
        this.\u00fa = \u00fa;
        this.\u00fb = \u00fb;
        this.setImage(this.\u00c2);
    }
    
    public void setImage(final Image \u00e2) {
        this.\u00c2 = \u00e2;
        this.repaint();
    }
    
    public void activateHyperlink() {
        final String \u00fb = this.\u00fb;
        try {
            if (!this.\u00fd.equals("")) {
                this.\u00fc.getAppletContext().showDocument(new URL(\u00fb), this.\u00fd);
                return;
            }
            this.\u00fc.getAppletContext().showDocument(new URL(\u00fb));
        }
        catch (MalformedURLException ex) {}
    }
    
    public void setImagePosition(final int \u00fe, final int \u00ff) {
        this.\u00fe = \u00fe;
        this.\u00ff = \u00ff;
        this.repaint();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.activateHyperlink();
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setImage(this.\u00fa);
        this.setCursor(new Cursor(12));
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setImage(this.\u00f9);
        this.setCursor(new Cursor(0));
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00c2 != null) {
            graphics.drawImage(this.\u00c2, this.\u00fe, this.\u00ff, this);
        }
    }
}
