// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graph;

import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

class LoadMessage extends Thread
{
    Graph2D g2d;
    String message;
    String newmessage;
    long visible;
    long invisible;
    Color foreground;
    Graphics lg;
    Font f;
    
    public LoadMessage(final Graph2D g2d) {
        this.message = "Loading Data ... Please Wait!";
        this.newmessage = null;
        this.visible = 500L;
        this.invisible = 200L;
        this.foreground = Color.red;
        this.lg = null;
        this.f = null;
        this.g2d = g2d;
    }
    
    public LoadMessage(final Graph2D graph2D, final String message) {
        this(graph2D);
        this.message = message;
    }
    
    public LoadMessage(final Graph2D graph2D, final String s, final long visible, final long invisible) {
        this(graph2D, s);
        this.visible = visible;
        this.invisible = invisible;
    }
    
    public void begin() {
        this.g2d.clearAll = false;
        this.g2d.paintAll = false;
        super.start();
    }
    
    public void end() {
        super.stop();
        this.g2d.clearAll = true;
        this.g2d.paintAll = true;
        if (this.lg != null) {
            this.lg.dispose();
        }
        this.g2d.repaint();
    }
    
    public void run() {
        int n = 1;
        int stringWidth = 0;
        int ascent = 0;
        int n2 = 0;
        int n3 = 0;
        this.setPriority(1);
        while (true) {
            if (this.newmessage != null && n != 0) {
                this.message = this.newmessage;
                this.newmessage = null;
            }
            if (this.lg == null) {
                this.lg = this.g2d.getGraphics();
                if (this.lg != null) {
                    this.lg = this.lg.create();
                }
            }
            if (this.lg != null) {
                if (this.f != null) {
                    this.lg.setFont(this.f);
                }
                final FontMetrics fontMetrics = this.lg.getFontMetrics(this.lg.getFont());
                stringWidth = fontMetrics.stringWidth(this.message);
                ascent = fontMetrics.getAscent();
            }
            else {
                n = 0;
            }
            if (n != 0) {
                this.lg.setColor(this.foreground);
                final Rectangle bounds = this.g2d.getBounds();
                n2 = bounds.x + (bounds.width - stringWidth) / 2;
                n3 = bounds.y + (bounds.height + ascent) / 2;
                this.lg.drawString(this.message, n2, n3);
                this.g2d.repaint();
                try {
                    Thread.sleep(this.visible);
                }
                catch (Exception ex) {}
            }
            else {
                if (this.lg != null) {
                    this.lg.setColor(this.g2d.getBackground());
                    this.lg.drawString(this.message, n2, n3);
                    this.g2d.repaint();
                }
                try {
                    Thread.sleep(this.invisible);
                }
                catch (Exception ex2) {}
            }
            n = ((n == 0) ? 1 : 0);
            if (this.lg != null) {
                this.lg.dispose();
            }
        }
    }
    
    public void setFont(final Font f) {
        this.f = f;
    }
    
    public void setForeground(final Color foreground) {
        if (foreground == null) {
            return;
        }
        this.foreground = foreground;
    }
    
    public void setMessage(final String newmessage) {
        if (newmessage == null) {
            return;
        }
        this.newmessage = newmessage;
    }
}
