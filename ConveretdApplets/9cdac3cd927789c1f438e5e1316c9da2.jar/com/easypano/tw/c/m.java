// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import com.easypano.tw.dd;
import java.util.Vector;
import java.awt.Graphics;
import com.easypano.tw.e;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FontMetrics;

public class m extends k
{
    protected com.easypano.tw.k c;
    protected q d;
    private FontMetrics e;
    private Dimension f;
    protected Insets g;
    
    public m(final com.easypano.tw.k c) {
        super(c);
        this.d = new q();
        this.e = null;
        this.f = new Dimension(0, 0);
        this.g = new Insets(2, 2, 2, 2);
        this.c = c;
    }
    
    public q c() {
        return this.d;
    }
    
    public void a(final q d) {
        this.d = d;
    }
    
    public void c(final Graphics graphics) {
        final boolean o = com.easypano.tw.c.a.O;
        graphics.setFont(this.c.getFont());
        this.e = graphics.getFontMetrics(this.c.getFont());
        int stringWidth = 0;
        final int height = this.e.getHeight();
        final int f = this.c.f();
        final int descent = this.e.getDescent();
        final int leading = this.e.getLeading();
        final Vector f2 = this.c.e().f();
        final dd e = this.c.e();
        int top = this.g.top;
        this.f.width = 0;
        int n = 0;
        while (true) {
            while (true) {
                Label_0258: {
                    if (!o) {
                        break Label_0258;
                    }
                    stringWidth = this.e.stringWidth(f2.elementAt(n));
                    m m = this;
                    if (!o) {
                        if (this.f.width < stringWidth) {
                            this.f.width = stringWidth;
                        }
                        m = this;
                    }
                    final q d = m.d;
                    final String element = f2.elementAt(n);
                    int b;
                    final int n2 = b = e.b();
                    if (!o) {
                        if (n2 == n) {
                            b = 1;
                        }
                        else {
                            b = 0;
                        }
                    }
                    int a;
                    final int n3 = a = e.a();
                    if (!o) {
                        if (n3 == n) {
                            a = 1;
                        }
                        else {
                            a = 0;
                        }
                    }
                    int c;
                    final int n4 = c = e.c();
                    if (!o) {
                        if (n4 == n) {
                            c = 1;
                        }
                        else {
                            c = 0;
                        }
                    }
                    d.a(graphics, element, b != 0, a != 0, c != 0, this.g.left, top, stringWidth, f, height, descent, leading);
                    top += f;
                    ++n;
                }
                if (n < f2.size()) {
                    continue;
                }
                break;
            }
            final Dimension f3 = this.f;
            f3.width += this.g.left + this.g.right;
            this.f.height = f * f2.size() + this.g.top + this.g.bottom;
            if (!o) {
                return;
            }
            continue;
        }
    }
    
    public Insets b() {
        return this.g;
    }
    
    public Dimension a() {
        return this.f;
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.c = null;
        this.d = null;
        this.e = null;
    }
}
