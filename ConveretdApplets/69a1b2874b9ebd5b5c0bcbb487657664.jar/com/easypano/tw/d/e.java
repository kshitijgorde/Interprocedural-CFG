// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import com.easypano.tw.dt;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.easypano.tw.h;

public class e extends d
{
    public e(final h h) {
        super(h);
        super.k.a(true);
    }
    
    public void b(final Graphics graphics) {
        if (super.k.isOpaque()) {
            graphics.setColor(com.easypano.tw.d.a.g);
            graphics.fillRect(0, 0, super.k.getBounds().width, super.k.getBounds().height);
        }
    }
    
    protected void e(final Graphics graphics) {
        final boolean o = com.easypano.tw.d.a.O;
        e e = this;
        if (!o) {
            if (super.v == null) {
                return;
            }
            e = this;
        }
        final int s = e.S;
        if (!o) {
            Label_0136: {
                switch (s) {
                    case 32: {
                        graphics.drawImage(super.v, super.N.left, super.N.top, super.k.getBounds().width - super.N.left - super.N.right, super.k.getBounds().height - super.N.top - super.N.bottom, super.k);
                        if (o) {
                            break Label_0136;
                        }
                        return;
                    }
                    case 33: {
                        int width = super.v.getWidth(super.k);
                        int height = super.v.getHeight(super.k);
                        final int n = super.k.getBounds().width - super.N.left - super.N.right;
                        final int n2 = super.k.getBounds().height - super.N.top - super.N.bottom;
                        final int n3 = width;
                        Label_0353: {
                            if (!o && n3 <= n) {
                                final int n4 = height;
                                if (o) {
                                    break Label_0353;
                                }
                                if (n4 > n2) {
                                    goto Label_0238;
                                }
                            }
                            else {
                                final float n5 = n3 / height;
                                final float n6 = fcmpl(n5, n / n2);
                                Label_0292: {
                                    if (!o) {
                                        if (n6 > 0) {
                                            width = n;
                                            height = (int)(width / n5);
                                            if (!o) {
                                                break Label_0292;
                                            }
                                        }
                                        height = n2;
                                        final int n7 = (int)(height * n5);
                                    }
                                    width = (int)n6;
                                }
                                dt.a(super.v = super.v.getScaledInstance(width, height, 1));
                            }
                            graphics.drawImage(super.v, super.N.left + (n - width) / 2, super.N.top + (n2 - height) / 2, super.k);
                        }
                        if (o) {
                            break;
                        }
                        return;
                    }
                }
            }
            graphics.drawImage(super.v, super.N.left, super.N.top, super.k);
        }
    }
}
