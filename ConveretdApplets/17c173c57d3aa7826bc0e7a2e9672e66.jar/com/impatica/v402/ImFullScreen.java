// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.awt.GraphicsDevice;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.security.Permission;
import java.awt.AWTPermission;

public class ImFullScreen
{
    boolean I;
    static boolean Z;
    static boolean C;
    
    final boolean I(final ImWindow imWindow) {
        return this.I(imWindow, false);
    }
    
    final boolean Z(final ImWindow imWindow) {
        return this.I(imWindow, true);
    }
    
    ImFullScreen(final boolean c) {
        ImFullScreen.C = c;
        if (c) {
            final String property = System.getProperty("os.version");
            if (property != null && property.compareTo("10.3.9") > 0) {
                ImFullScreen.Z = true;
            }
        }
        if (ImFullScreen.Z) {
            final SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                try {
                    securityManager.checkPermission(new AWTPermission("fullScreenExclusive"));
                }
                catch (SecurityException ex) {
                    if (c) {
                        ImFullScreen.Z = false;
                        this.I = true;
                    }
                    return;
                }
            }
            this.I(null, false);
        }
        else {
            this.I = true;
        }
    }
    
    final void I(final Graphics graphics, final int n, final int n2, final Color color, final Color color2) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setPaint(new GradientPaint(0.0f, n, color, 0.0f, n2, color2, true));
    }
    
    private boolean I(ImWindow fullScreenWindow, final boolean b) {
        if (!ImFullScreen.Z) {
            if (b) {
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                final int width = screenSize.width;
                int height = screenSize.height;
                if (ImFullScreen.C) {
                    height -= 22;
                }
                fullScreenWindow.setUndecorated(true);
                fullScreenWindow.setSize(width, height);
            }
        }
        else {
            final GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            this.I = defaultScreenDevice.isFullScreenSupported();
            if (this.I) {
                try {
                    if (b) {
                        fullScreenWindow.setUndecorated(true);
                    }
                    else if (fullScreenWindow != null) {
                        fullScreenWindow.setUndecorated(false);
                        fullScreenWindow = null;
                    }
                    defaultScreenDevice.setFullScreenWindow(fullScreenWindow);
                }
                catch (Throwable t) {
                    this.I = false;
                }
            }
        }
        return this.I;
    }
}
