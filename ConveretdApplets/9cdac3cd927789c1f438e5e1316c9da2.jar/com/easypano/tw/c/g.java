// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Graphics;
import java.awt.Cursor;

public class g extends c
{
    public static final int S = 1;
    public static final int T = 2;
    protected static final int U = 20;
    protected static final int V = 14;
    protected int W;
    
    public g(final com.easypano.tw.g g) {
        super(g, false);
        this.W = 2;
        g.b(false);
        g.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void f(final int w) {
        int n = w;
        int n3;
        final int n2 = n3 = 1;
        Label_0016: {
            if (!com.easypano.tw.c.a.O) {
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
        final boolean o = com.easypano.tw.c.a.O;
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
        final boolean o = com.easypano.tw.c.a.O;
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