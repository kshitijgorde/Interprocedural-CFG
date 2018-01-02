// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;

final class eb extends ea
{
    final /* synthetic */ dt b;
    
    eb(final dt b) {
        this.b = b;
    }
    
    public void run() {
        final boolean q = g.q;
        while (true) {
            while (true) {
                Label_0313: {
                    if (!q) {
                        break Label_0313;
                    }
                    try {
                        dt.a(this.b, System.currentTimeMillis());
                        final dt b = this.b;
                        dt.a(b, (int)(dt.a(b) + (dt.b(this.b) - dt.c(this.b))));
                        dt.b(this.b, dt.b(this.b));
                        final int a = dt.a(this.b);
                        Label_0297: {
                            final dt b2;
                            Label_0132: {
                                Component component = null;
                                Label_0125: {
                                    if (!q) {
                                        if (a < dt.d(this.b)) {
                                            dt.e(this.b).setVisible(false);
                                            if (!q) {
                                                break Label_0297;
                                            }
                                        }
                                        final bu bu = (bu)(component = dt.e(this.b));
                                        if (q) {
                                            break Label_0125;
                                        }
                                        bu.isVisible();
                                    }
                                    if (a != 0) {
                                        break Label_0297;
                                    }
                                    b2 = this.b;
                                    if (q) {
                                        break Label_0132;
                                    }
                                    component = dt.f(b2);
                                }
                                if (component == null) {
                                    break Label_0297;
                                }
                                final dt b3 = this.b;
                            }
                            Component component4;
                            Component h;
                            Component component3;
                            final Component component2 = component3 = (h = (component4 = dt.g(b2)));
                            if (!q) {
                                if (component2 == null) {
                                    break Label_0297;
                                }
                                h = (component3 = (component4 = dt.f(this.b)));
                            }
                            if (!q) {
                                if (component3 == dt.g(this.b)) {
                                    break Label_0297;
                                }
                                h = dt.h(this.b);
                            }
                            final Component f;
                            Label_0205: {
                                if (!q) {
                                    if (h != null) {
                                        f = dt.f(this.b);
                                        if (q) {
                                            break Label_0205;
                                        }
                                        if (f == dt.h(this.b)) {
                                            break Label_0297;
                                        }
                                    }
                                    dt.f(this.b);
                                }
                            }
                            dt.e(this.b).a(true, f.getLocationOnScreen().x - dt.g(this.b).getLocationOnScreen().x + dt.i(this.b), dt.f(this.b).getLocationOnScreen().y - dt.g(this.b).getLocationOnScreen().y + dt.j(this.b) + dt.a, dt.g(this.b).getBounds());
                        }
                        Thread.sleep(200L);
                    }
                    catch (Exception ex) {
                        ds.a(200L);
                    }
                }
                if (super.a) {
                    continue;
                }
                break;
            }
            if (!q) {
                return;
            }
            continue;
        }
    }
}
