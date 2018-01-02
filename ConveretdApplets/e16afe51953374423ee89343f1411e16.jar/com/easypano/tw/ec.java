// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Component;

final class ec extends eb
{
    final /* synthetic */ du b;
    
    ec(final du b) {
        this.b = b;
    }
    
    public void run() {
        final boolean q = h.q;
        while (true) {
            while (true) {
                Label_0313: {
                    if (!q) {
                        break Label_0313;
                    }
                    try {
                        du.a(this.b, System.currentTimeMillis());
                        final du b = this.b;
                        du.a(b, (int)(du.a(b) + (du.b(this.b) - du.c(this.b))));
                        du.b(this.b, du.b(this.b));
                        final int a = du.a(this.b);
                        Label_0297: {
                            final du b2;
                            Label_0132: {
                                Component component = null;
                                Label_0125: {
                                    if (!q) {
                                        if (a < du.d(this.b)) {
                                            du.e(this.b).setVisible(false);
                                            if (!q) {
                                                break Label_0297;
                                            }
                                        }
                                        final bu bu = (bu)(component = du.e(this.b));
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
                                    component = du.f(b2);
                                }
                                if (component == null) {
                                    break Label_0297;
                                }
                                final du b3 = this.b;
                            }
                            Component component4;
                            Component h;
                            Component component3;
                            final Component component2 = component3 = (h = (component4 = du.g(b2)));
                            if (!q) {
                                if (component2 == null) {
                                    break Label_0297;
                                }
                                h = (component3 = (component4 = du.f(this.b)));
                            }
                            if (!q) {
                                if (component3 == du.g(this.b)) {
                                    break Label_0297;
                                }
                                h = du.h(this.b);
                            }
                            final Component f;
                            Label_0205: {
                                if (!q) {
                                    if (h != null) {
                                        f = du.f(this.b);
                                        if (q) {
                                            break Label_0205;
                                        }
                                        if (f == du.h(this.b)) {
                                            break Label_0297;
                                        }
                                    }
                                    du.f(this.b);
                                }
                            }
                            du.e(this.b).a(true, f.getLocationOnScreen().x - du.g(this.b).getLocationOnScreen().x + du.i(this.b), du.f(this.b).getLocationOnScreen().y - du.g(this.b).getLocationOnScreen().y + du.j(this.b) + du.a, du.g(this.b).getBounds());
                        }
                        Thread.sleep(200L);
                    }
                    catch (Exception ex) {
                        dt.a(200L);
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
