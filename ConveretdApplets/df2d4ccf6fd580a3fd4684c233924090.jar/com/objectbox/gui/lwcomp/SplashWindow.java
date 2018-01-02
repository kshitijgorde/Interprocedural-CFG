// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;

public class SplashWindow extends Window
{
    Image splashIm;
    int w;
    int h;
    
    public SplashWindow(final Frame frame, final Image splashIm, final int w, final int h) {
        super(frame);
        this.splashIm = splashIm;
        this.w = w;
        this.h = h;
        this.setSize(this.w, this.h);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Rectangle bounds = this.getBounds();
        this.setLocation((screenSize.width - bounds.width) / 2, (screenSize.height - bounds.height) / 2);
        this.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        if (this.splashIm != null) {
            graphics.drawImage(this.splashIm, 0, 0, this.w, this.h, this);
        }
    }
}
