// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.screenmodel;

import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

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
    
    ZCursor(final Component component) {
        this(Color.green, Color.yellow, component);
    }
    
    ZCursor() {
        this(Color.green, Color.yellow, null);
    }
    
    synchronized void show() {
        if (!this.shown) {
            this.shown = true;
            if (this.parent != null) {
                final Graphics graphics = this.parent.getGraphics();
                if (graphics != null) {
                    graphics.setColor(this.cursorcolor);
                    graphics.fillRect(this.l, this.t, this.w, this.h);
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
    
    synchronized void redraw(final Graphics graphics) {
        if (this.shown) {
            graphics.setColor(this.cursorcolor);
            graphics.fillRect(this.l, this.t, this.w, this.h);
        }
    }
    
    synchronized void move(final int l, final int t) {
        final boolean shown = this.shown;
        if (shown) {
            this.hide();
        }
        this.l = l;
        this.t = t;
        if (shown) {
            this.show();
        }
    }
    
    synchronized void size(final int w, final int h) {
        final boolean shown = this.shown;
        if (shown) {
            this.hide();
        }
        this.w = w;
        this.h = h;
        if (shown) {
            this.show();
        }
    }
    
    synchronized void setcolors(final Color cursorcolor, final Color bgcolor) {
        final boolean shown = this.shown;
        if (shown) {
            this.hide();
        }
        this.cursorcolor = cursorcolor;
        this.bgcolor = bgcolor;
        if (shown) {
            this.show();
        }
    }
}
