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
        final boolean q = h.q;
        this.a.requestFocus();
        final du b = f.b(this.a);
        Label_0039: {
            if (!q) {
                if (b == null) {
                    break Label_0039;
                }
                f.b(this.a);
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
        final k k = (k)(container = e.o());
        if (!q) {
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
                            if (!q) {
                                break Label_0079;
                            }
                            parent = parent.getParent();
                        }
                        if (parent != null) {
                            bn = (bn)parent;
                            h = dt.e.o().h();
                            if (q || q) {
                                break Label_0132;
                            }
                            if (bn != h) {
                                bn2 = (bn)parent;
                                i = (j = dt.e.o());
                                if (q) {
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
                    j = (o = dt.e.o());
                }
                if (q) {
                    break Label_0142;
                }
                i.h();
            }
            if (bn == h) {
                return;
            }
            j = dt.e.o();
        }
        if (bn2 != j) {
            dt.e.o().d(false);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean q = h.q;
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
            bp bp2 = null;
            final TWViewer e;
            Label_0116: {
                Container container2 = null;
                bp r = null;
                Label_0109: {
                    bq bq = null;
                    Label_0091: {
                        while (true) {
                            Label_0054: {
                                if (!q) {
                                    break Label_0054;
                                }
                                parent = parent.getParent();
                            }
                            if (parent != null) {
                                final bp bp = bq = (bq)(container2 = (bp2 = (bp)parent));
                                if (q) {
                                    break Label_0091;
                                }
                                r = dt.e.r();
                                if (q) {
                                    break Label_0109;
                                }
                                if (bp != r) {
                                    final bp bp3 = bq = (bq)(container2 = (bp2 = (bp)parent));
                                    if (q) {
                                        break Label_0091;
                                    }
                                    if (bp3 != dt.e.q()) {
                                        continue;
                                    }
                                }
                            }
                            break;
                        }
                        container2 = (bq = (bq)(bp2 = (bp)parent));
                    }
                    if (!q) {
                        if (bq == null) {
                            break Label_0122;
                        }
                        bp2 = (bp)(container2 = parent);
                    }
                    e = dt.e;
                    if (q) {
                        break Label_0116;
                    }
                    e.r();
                }
                if (container2 == r) {
                    return;
                }
                bp2 = (bp)parent;
                final TWViewer e2 = dt.e;
            }
            if (bp2 == e.q()) {
                return;
            }
        }
        dt.e.a(this.a, mouseEvent.getX(), mouseEvent.getY());
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final du b = f.b(this.a);
        if (!h.q) {
            if (b == null) {
                return;
            }
            f.b(this.a);
        }
        b.a((Component)this.a);
    }
}
