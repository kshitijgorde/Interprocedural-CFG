// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Graphics;
import com.easypano.tw.g;
import java.awt.Color;

public class f extends d
{
    protected Color T;
    protected Color U;
    protected Color V;
    protected Color W;
    
    public f(final g g) {
        super(g);
        this.T = new Color(0, 0, 255);
        this.U = new Color(255, 0, 0);
        this.V = new Color(255, 0, 0);
        this.W = null;
    }
    
    public void d(final Graphics graphics) {
        final boolean o = com.easypano.tw.c.a.O;
        final boolean e;
        final boolean b = e = super.k.e().e();
        Label_0113: {
            final boolean f;
            Label_0060: {
                if (!o) {
                    if (b) {
                        f = super.k.e().f();
                        if (o) {
                            break Label_0060;
                        }
                        if (f) {
                            this.W = this.U;
                            if (!o) {
                                break Label_0113;
                            }
                        }
                    }
                    super.k.e().f();
                }
            }
            f f2 = null;
            Label_0109: {
                if (!o) {
                    if (b) {
                        this.W = this.V;
                        if (!o) {
                            break Label_0113;
                        }
                    }
                    f2 = this;
                    if (o) {
                        break Label_0109;
                    }
                    super.k.e().d();
                }
                if (f) {
                    this.W = this.T;
                    if (!o) {
                        break Label_0113;
                    }
                }
                f2 = this;
            }
            f2.W = null;
        }
        if (this.W != null) {
            graphics.setColor(this.W);
            graphics.drawRect(0, 0, super.k.getBounds().width - 1, super.k.getBounds().height - 1);
        }
    }
    
    public void j(final Color t) {
        this.T = t;
    }
    
    public void k(final Color u) {
        this.U = u;
    }
    
    public void l(final Color v) {
        this.V = v;
    }
}