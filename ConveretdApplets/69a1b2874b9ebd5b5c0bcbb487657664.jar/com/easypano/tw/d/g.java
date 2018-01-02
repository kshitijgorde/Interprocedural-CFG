// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Graphics;
import java.awt.Cursor;
import com.easypano.tw.h;

public class g extends c
{
    public static final int S = 1;
    public static final int T = 2;
    protected static final int U = 20;
    protected static final int V = 14;
    protected int W;
    
    public g(final h h) {
        super(h, false);
        this.W = 2;
        h.b(false);
        h.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void f(final int w) {
        int n = w;
        int n3;
        final int n2 = n3 = 1;
        Label_0016: {
            if (!com.easypano.tw.d.a.O) {
                if (w == n2) {
                    break Label_0016;
                }
                n = w;
                n3 = 2;
            }
            if (n != n3) {
                return;
            }
        }
        this.W = w;
    }
    
    public int e() {
        return this.W;
    }
    
    public void e(final Graphics graphics) {
        final boolean o = com.easypano.tw.d.a.O;
        g g = this;
        Label_0024: {
            if (!o) {
                if (super.H == null) {
                    break Label_0024;
                }
                g = this;
            }
            g.e(graphics);
            if (!o) {
                return;
            }
        }
        graphics.setColor(super.k.getForeground());
        graphics.fillOval((super.k.getBounds().width - 14) / 2, (super.k.getBounds().height - 14) / 2, 14, 14);
        graphics.drawOval((super.k.getBounds().width - 20) / 2, (super.k.getBounds().height - 20) / 2, 20, 20);
    }
    
    public void a(final Graphics graphics) {
        final boolean o = com.easypano.tw.d.a.O;
        g g = this;
        if (!o) {
            switch (this.W) {
                case 1: {
                    g g2 = this;
                    if (!o) {
                        if (!super.k.e().f()) {
                            return;
                        }
                        g2 = this;
                    }
                    g2.a(graphics);
                    if (o) {
                        break;
                    }
                    return;
                }
            }
            g = this;
        }
        g.a(graphics);
    }
}
