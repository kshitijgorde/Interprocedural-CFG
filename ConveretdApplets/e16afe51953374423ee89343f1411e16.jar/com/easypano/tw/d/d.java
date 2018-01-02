// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.easypano.tw.h;

public class d extends c
{
    protected int S;
    
    public d(final h h) {
        super(h, false);
        this.S = 31;
    }
    
    protected void e(final Graphics graphics) {
        final boolean o = com.easypano.tw.d.a.O;
        d d = this;
        if (!o) {
            if (super.v == null) {
                return;
            }
            d = this;
        }
        final int s = d.S;
        if (!o) {
            Label_0072: {
                switch (s) {
                    case 31: {
                        graphics.drawImage(super.v, 0, 0, super.k);
                        if (o) {
                            break Label_0072;
                        }
                        return;
                    }
                    case 32: {
                        final Rectangle bounds = super.k.getBounds();
                        graphics.drawImage(super.v, 0, 0, bounds.width, bounds.height, super.k);
                        if (o) {
                            break;
                        }
                        return;
                    }
                }
            }
            super.v.getWidth(super.k);
        }
        int n = s;
        int height = super.v.getHeight(super.k);
        final int width = super.k.getBounds().width;
        final int height2 = super.k.getBounds().height;
        final int n2 = n;
        Label_0238: {
            if (!o && n2 <= width) {
                final int n3 = height;
                if (o) {
                    return;
                }
                if (n3 > height2) {
                    goto Label_0180;
                }
            }
            else {
                final float n4 = n2 / height;
                final float n5 = fcmpl(n4, width / height2);
                if (!o) {
                    if (n5 > 0) {
                        n = width;
                        height = (int)(n / n4);
                        if (!o) {
                            break Label_0238;
                        }
                    }
                    height = height2;
                    final int n6 = (int)(height * n4);
                }
                n = (int)n5;
            }
        }
        graphics.drawImage(super.v, (width - n) / 2, (height2 - height) / 2, n, height, super.k);
    }
    
    protected void f(final Graphics graphics) {
    }
    
    public void f(final int s) {
        final boolean o = com.easypano.tw.d.a.O;
        int n = s;
        int n2 = s;
        int n4;
        final int n3 = n4 = 31;
        Label_0030: {
            if (!o) {
                if (s == n3) {
                    break Label_0030;
                }
                n = s;
                n2 = s;
                final int n5;
                n4 = (n5 = 32);
            }
            if (!o) {
                if (n2 == n3) {
                    break Label_0030;
                }
                n = s;
                n4 = 33;
            }
            if (n != n4) {
                return;
            }
        }
        this.S = s;
    }
}
