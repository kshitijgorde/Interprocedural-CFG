// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class x extends MouseAdapter
{
    final /* synthetic */ e a;
    
    x(final e a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean q = g.q;
        this.a.requestFocus();
        final du b = e.b(this.a);
        Label_0039: {
            if (!q) {
                if (b == null) {
                    break Label_0039;
                }
                e.b(this.a);
            }
            b.a((Component)this.a);
        }
        final TWViewer e = dt.e;
        if (!q) {
            if (e == null) {
                return;
            }
            final TWViewer e2 = dt.e;
        }
        Container container;
        final j j = (j)(container = e.q());
        if (!q) {
            if (j == null) {
                return;
            }
            container = this.a.getParent();
        }
        Container parent = container;
        bo bo2 = null;
        j k = null;
        Label_0142: {
            bo bo = null;
            bo h = null;
            Label_0132: {
                j i = null;
                Label_0125: {
                    while (true) {
                        Label_0079: {
                            if (!q) {
                                break Label_0079;
                            }
                            parent = parent.getParent();
                        }
                        if (parent != null) {
                            bo = (bo)parent;
                            h = dt.e.q().h();
                            if (q || q) {
                                break Label_0132;
                            }
                            if (bo != h) {
                                bo2 = (bo)parent;
                                i = (k = dt.e.q());
                                if (q) {
                                    break Label_0125;
                                }
                                if (bo2 != i) {
                                    continue;
                                }
                            }
                        }
                        break;
                    }
                    final j q2;
                    k = (q2 = dt.e.q());
                }
                if (q) {
                    break Label_0142;
                }
                i.h();
            }
            if (bo == h) {
                return;
            }
            k = dt.e.q();
        }
        if (bo2 != k) {
            dt.e.q().d(false);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean q = g.q;
        int popupTrigger;
        final boolean b = (popupTrigger = (mouseEvent.isPopupTrigger() ? 1 : 0)) != 0;
        Label_0027: {
            if (!q) {
                if (b) {
                    break Label_0027;
                }
                popupTrigger = (mouseEvent.getModifiers() & 0x8);
            }
            if (popupTrigger != 8) {
                return;
            }
        }
        Container container;
        final TWViewer twViewer = (TWViewer)(container = dt.e);
        if (!q) {
            if (twViewer == null) {
                return;
            }
            container = this.a.getParent();
        }
        Container parent = container;
        Label_0122: {
            bq bq2 = null;
            bq bq = null;
            bq bq3 = null;
            final bq bq4;
            Label_0105: {
                Container container2 = null;
                Label_0091: {
                    while (true) {
                        Label_0054: {
                            if (!q) {
                                break Label_0054;
                            }
                            parent = parent.getParent();
                        }
                        if (parent != null) {
                            Container container4;
                            final Container container3;
                            container2 = (container3 = (container4 = parent));
                            if (q) {
                                break Label_0091;
                            }
                            bq = (bq2 = dt.e.v());
                            if (q) {
                                break Label_0105;
                            }
                            if (container2 != bq) {
                                bq3 = (bq)(container4 = parent);
                                if (q) {
                                    break Label_0091;
                                }
                                if (bq3 != dt.e.t()) {
                                    continue;
                                }
                            }
                        }
                        break;
                    }
                    bq4 = (bq)parent;
                }
                if (!q && container2 == null) {
                    break Label_0122;
                }
                final bq v;
                bq2 = (v = dt.e.v());
            }
            if (!q) {
                if (bq3 == bq) {
                    return;
                }
                bq2 = dt.e.t();
            }
            if (bq4 == bq2) {
                return;
            }
        }
        dt.e.a(this.a, mouseEvent.getX(), mouseEvent.getY());
    }
}
