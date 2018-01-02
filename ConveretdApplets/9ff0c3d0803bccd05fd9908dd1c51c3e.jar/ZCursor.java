import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ZCursor
{
    Color cursorcolor;
    Color bgcolor;
    boolean shown;
    int t;
    int l;
    int w;
    int h;
    Component parent;
    
    ZCursor(final Color cursorcolor, final Color bgcolor, final Component parent) {
        this.shown = false;
        this.cursorcolor = cursorcolor;
        this.bgcolor = bgcolor;
        this.parent = parent;
    }
    
    ZCursor(final Component parent) {
        this(Color.green, Color.yellow, parent);
    }
    
    ZCursor() {
        this(Color.green, Color.yellow, null);
    }
    
    synchronized void show() {
        if (!this.shown) {
            this.shown = true;
            if (this.parent != null) {
                final Graphics g = this.parent.getGraphics();
                if (g != null) {
                    g.setColor(this.cursorcolor);
                    g.fillRect(this.l, this.t, this.w, this.h);
                }
            }
        }
    }
    
    synchronized void hide() {
        if (this.shown) {
            this.shown = false;
            if (this.parent != null) {
                this.parent.repaint(this.l, this.t, this.w, this.h);
            }
        }
    }
    
    synchronized void redraw(final Graphics g) {
        if (this.shown) {
            g.setColor(this.cursorcolor);
            g.fillRect(this.l, this.t, this.w, this.h);
        }
    }
    
    synchronized void move(final int l, final int t) {
        final boolean wasshown = this.shown;
        if (wasshown) {
            this.hide();
        }
        this.l = l;
        this.t = t;
        if (wasshown) {
            this.show();
        }
    }
    
    synchronized void size(final int w, final int h) {
        final boolean wasshown = this.shown;
        if (wasshown) {
            this.hide();
        }
        this.w = w;
        this.h = h;
        if (wasshown) {
            this.show();
        }
    }
    
    synchronized void setcolors(final Color cursorcolor, final Color bgcolor) {
        final boolean wasshown = this.shown;
        if (wasshown) {
            this.hide();
        }
        this.cursorcolor = cursorcolor;
        this.bgcolor = bgcolor;
        if (wasshown) {
            this.show();
        }
    }
}
