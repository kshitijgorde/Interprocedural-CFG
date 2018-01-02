// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.a;

import java.awt.Rectangle;
import java.awt.Graphics;
import com.easypano.tw.e;
import java.awt.Color;
import com.easypano.tw.m;

public class n extends k
{
    protected m c;
    private Color d;
    private Color e;
    private Color f;
    private Color g;
    private Color h;
    private Color i;
    private double j;
    
    public n(final m c) {
        super(c);
        this.c = null;
        this.d = new Color(58, 110, 165);
        this.e = new Color(102, 102, 102);
        this.f = new Color(191, 191, 191);
        this.g = new Color(0, 0, 0);
        this.h = new Color(80, 106, 133);
        this.i = new Color(156, 182, 210);
        this.j = 0.0;
        this.c = c;
        this.a(this.d);
    }
    
    public void c(final Graphics graphics) {
        this.e(graphics);
        this.f(graphics);
    }
    
    protected void e(final Graphics graphics) {
        final boolean o = com.easypano.tw.a.a.O;
        final Rectangle bounds = this.c.getBounds();
        graphics.setColor(this.e);
        graphics.drawLine(1, 0, this.c.getBounds().width - 2, 0);
        graphics.drawLine(this.c.getBounds().width - 1, 1, bounds.width - 1, bounds.height - 2);
        graphics.drawLine(1, bounds.height - 1, bounds.width - 2, bounds.height - 1);
        graphics.drawLine(0, 1, 0, bounds.height - 2);
        graphics.setColor(this.d);
        final int n = (bounds.width - 2) * this.c.e() / 100;
        final int n2 = bounds.height - 2;
        graphics.fillRect(1, 1, n, n2);
        graphics.setColor(this.c.getBackground());
        graphics.draw3DRect(n + 1, 1, bounds.width - n - 3, n2 - 1, false);
        graphics.setColor(this.d);
        graphics.draw3DRect(1, 1, n - 1, n2 - 1, true);
        if (com.easypano.tw.e.k) {
            com.easypano.tw.a.a.O = !o;
        }
    }
    
    protected void f(final Graphics graphics) {
    }
    
    public void a(final Color d) {
        this.d = d;
    }
}
