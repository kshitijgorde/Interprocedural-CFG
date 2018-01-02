// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Container;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class y extends MouseAdapter
{
    final /* synthetic */ f a;
    
    y(final f a) {
        this.a = a;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int q = h.q;
        this.a.requestFocus();
        final dt b = f.b(this.a);
        Label_0039: {
            if (q == 0) {
                if (b == null) {
                    break Label_0039;
                }
                f.b(this.a);
            }
            b.a((Component)this.a);
        }
        final TWViewer e = ds.e;
        if (q == 0) {
            if (e == null) {
                return;
            }
            final TWViewer e2 = ds.e;
        }
        Container container;
        final k k = (k)(container = e.o());
        if (q == 0) {
            if (k == null) {
                return;
            }
            container = this.a.getParent();
        }
        Container parent = container;
        bn bn2 = null;
        k j = null;
        Label_0142: {
            bn bn = null;
            bn h = null;
            Label_0132: {
                k i = null;
                Label_0125: {
                    while (true) {
                        Label_0079: {
                            if (q == 0) {
                                break Label_0079;
                            }
                            parent = parent.getParent();
                        }
                        if (parent != null) {
                            bn = (bn)parent;
                            h = ds.e.o().h();
                            if (q != 0 || q != 0) {
                                break Label_0132;
                            }
                            if (bn != h) {
                                bn2 = (bn)parent;
                                i = (j = ds.e.o());
                                if (q != 0) {
                                    break Label_0125;
                                }
                                if (bn2 != i) {
                                    continue;
                                }
                            }
                        }
                        break;
                    }
                    final k o;
                    j = (o = ds.e.o());
                }
                if (q != 0) {
                    break Label_0142;
                }
                i.h();
            }
            if (bn == h) {
                return;
            }
            j = ds.e.o();
        }
        if (bn2 != j) {
            ds.e.o().d(false);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int q = h.q;
        int popupTrigger;
        final boolean b = (popupTrigger = (mouseEvent.isPopupTrigger() ? 1 : 0)) != 0;
        Label_0027: {
            if (q == 0) {
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
        final TWViewer twViewer = (TWViewer)(container = ds.e);
        if (q == 0) {
            if (twViewer == null) {
                return;
            }
            container = this.a.getParent();
        }
        Container parent = container;
        Label_0122: {
            bp bp2 = null;
            bp bp = null;
            bp bp3 = null;
            final bp bp4;
            Label_0105: {
                Container container2 = null;
                Label_0091: {
                    while (true) {
                        Label_0054: {
                            if (q == 0) {
                                break Label_0054;
                            }
                            parent = parent.getParent();
                        }
                        if (parent != null) {
                            Container container4;
                            final Container container3;
                            container2 = (container3 = (container4 = parent));
                            if (q != 0) {
                                break Label_0091;
                            }
                            bp = (bp2 = ds.e.t());
                            if (q != 0) {
                                break Label_0105;
                            }
                            if (container2 != bp) {
                                bp3 = (bp)(container4 = parent);
                                if (q != 0) {
                                    break Label_0091;
                                }
                                if (bp3 != ds.e.r()) {
                                    continue;
                                }
                            }
                        }
                        break;
                    }
                    bp4 = (bp)parent;
                }
                if (q == 0 && container2 == null) {
                    break Label_0122;
                }
                final bp t;
                bp2 = (t = ds.e.t());
            }
            if (q == 0) {
                if (bp3 == bp) {
                    return;
                }
                bp2 = ds.e.r();
            }
            if (bp4 == bp2) {
                return;
            }
        }
        ds.e.a(this.a, mouseEvent.getX(), mouseEvent.getY());
    }
}
