// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Component;

public class DisplayUtilities
{
    private Component a;
    private Color b;
    private Frame c;
    private double d;
    private double e;
    private int f;
    private ArrayList g;
    private boolean h;
    
    public DisplayUtilities(final Component a, final Color b) {
        this.g = new ArrayList();
        this.h = false;
        this.a = a;
        this.b = b;
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        this.g.add(keyListener);
    }
    
    public boolean repaint(final boolean b) {
        this.updateScreenMode(b, false);
        if (this.c != null) {
            this.c.repaint();
            return false;
        }
        return true;
    }
    
    public boolean updateScreenMode(final boolean b, final boolean b2) {
        if (b) {
            if (this.c != null && !this.c.isActive()) {
                this.updateScreenMode(false, true);
                return false;
            }
            if (this.c != null && !b2) {
                return b;
            }
            (this.c = new a(this)).setUndecorated(true);
            this.c.setVisible(true);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this.c);
            for (int i = 0; i < this.g.size(); ++i) {
                this.c.addKeyListener((KeyListener)this.g.get(i));
            }
            this.c.setFocusable(true);
            this.c.requestFocus();
            this.c.requestFocusInWindow();
            this.c.setBackground(this.b);
            return b;
        }
        else {
            if (this.c == null && !b2) {
                return b;
            }
            for (int j = 0; j < this.g.size(); ++j) {
                this.a.removeKeyListener((KeyListener)this.g.get(j));
            }
            if (this.c != null) {
                for (int k = 0; k < this.g.size(); ++k) {
                    this.c.removeKeyListener((KeyListener)this.g.get(k));
                }
                this.c.setVisible(false);
                this.c.dispose();
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
                this.c = null;
            }
            for (int l = 0; l < this.g.size(); ++l) {
                this.a.addKeyListener((KeyListener)this.g.get(l));
            }
            this.a.setFocusable(true);
            this.a.requestFocus();
            this.a.requestFocusInWindow();
            this.a.setBackground(this.b);
            return b;
        }
    }
    
    public void drawImage(final Graphics graphics, final Image image, final double n, final DisplayUtilities$OverlayDrawer displayUtilities$OverlayDrawer) {
        final Graphics2D graphics2D;
        final Composite composite = (graphics2D = (Graphics2D)graphics).getComposite();
        graphics2D.setComposite(AlphaComposite.Src);
        final int width = graphics.getClipBounds().width;
        final int height = graphics.getClipBounds().height;
        if (this.e != width * n || this.d != height * n) {
            this.f = 2;
            this.e = width * n;
            this.d = height * n;
        }
        final int n2 = (int)((width - Math.min(width, n * height)) / 2.0);
        final int n3 = (int)((height - Math.min(height, width / n)) / 2.0);
        graphics.setColor(this.a.getBackground());
        if (this.f > 0) {
            --this.f;
            graphics.fillRect(0, 0, n2, height);
            graphics.fillRect(width - n2, 0, n2, height);
            graphics.fillRect(0, 0, width, n3);
            graphics.fillRect(0, height - n3, width, n3);
        }
        graphics.drawImage(image, n2, n3, width - (n2 << 1), height - (n3 << 1), null);
        graphics2D.setComposite(composite);
        if (displayUtilities$OverlayDrawer != null) {
            displayUtilities$OverlayDrawer.drawOverlay(graphics, n2, n3, graphics.getClipBounds().width, graphics.getClipBounds().height);
        }
    }
    
    public void init() {
    }
    
    public void dispose() {
        if (this.c != null) {
            this.c.dispose();
            this.c = null;
        }
    }
    
    static Component a(final DisplayUtilities displayUtilities) {
        return displayUtilities.a;
    }
}
