// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Cursor;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;

final class bc extends MouseAdapter implements MouseMotionListener
{
    private boolean a;
    final /* synthetic */ bt b;
    
    bc(final bt b) {
        this.b = b;
        this.a = false;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int q = h.q;
        bt.h(this.b).b(false);
        final bt b = this.b;
        if (q == 0) {
            if (!bt.a(b)) {
                return;
            }
            final bt b2 = this.b;
        }
        final du[] l = bt.c(b).l();
        final int length = l.length;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        boolean b3 = false;
        int n = 0;
        boolean b4 = false;
        while (true) {
        Label_0103:
            while (true) {
                Label_0097: {
                    if (q == 0) {
                        break Label_0097;
                    }
                    final boolean a;
                    b3 = (a = l[n].a(501, x, y));
                    if (b4 && q == 0) {
                        break Label_0103;
                    }
                    ++n;
                }
                if (n < length) {
                    continue;
                }
                break;
            }
            b4 = b3;
            if (q != 0) {
                continue;
            }
            break;
        }
        if (b4) {
            this.a = true;
            if (q == 0) {
                return;
            }
        }
        bt.i(this.b).h();
        bt.i(this.b).a(0.0, 0.0, 0.0);
        this.b.requestFocus();
        bt.a(this.b, mouseEvent.getX());
        bt.b(this.b, mouseEvent.getY());
        bt.i(this.b).e();
        this.a = false;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int q = h.q;
        int n3;
        int n2;
        final int n = n2 = (n3 = (mouseEvent.isPopupTrigger() ? 1 : 0));
        bc bc = null;
        Label_0165: {
            Label_0064: {
                Label_0052: {
                    Label_0034: {
                        if (q == 0) {
                            if (n != 0) {
                                break Label_0034;
                            }
                            n3 = (n2 = (mouseEvent.getModifiers() & 0x8));
                        }
                        if (q != 0) {
                            break Label_0064;
                        }
                        if (n2 != 8) {
                            break Label_0052;
                        }
                    }
                    ds.e.a(this.b, mouseEvent.getX(), mouseEvent.getY());
                }
                bc = this;
                if (q != 0) {
                    break Label_0165;
                }
                n3 = (bt.a(this.b) ? 1 : 0);
            }
            Label_0164: {
                if (n3 != 0) {
                    bc bc2 = this;
                    if (q == 0) {
                        if (this.a) {
                            final du[] l = bt.c(this.b).l();
                            final int length = l.length;
                            final int x = mouseEvent.getX();
                            final int y = mouseEvent.getY();
                            int n4 = 0;
                            while (true) {
                                Label_0145: {
                                    if (q == 0) {
                                        break Label_0145;
                                    }
                                    if (l[n4].a(502, x, y) && q == 0) {
                                        break Label_0164;
                                    }
                                    ++n4;
                                }
                                if (n4 >= length) {
                                    break Label_0164;
                                }
                                continue;
                            }
                        }
                        else {
                            bc2 = this;
                        }
                    }
                    bt.i(bc2.b).d();
                }
            }
            bc = this;
        }
        bc.a = false;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        final int q = h.q;
        final bt b = this.b;
        if (q == 0) {
            if (!bt.a(b)) {
                return;
            }
            final bt b2 = this.b;
        }
        final du[] l = bt.c(b).l();
        final int length = l.length;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        int n = 0;
        while (true) {
            Label_0072: {
                if (q == 0) {
                    break Label_0072;
                }
                l[n].a(505, x, y);
                ++n;
            }
            if (n < length) {
                continue;
            }
            break;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int q = h.q;
        int n2;
        boolean x;
        final int n = (x = ((n2 = (this.a ? 1 : 0)) != 0)) ? 1 : 0;
        if (q == 0) {
            if (n != 0) {
                return;
            }
            final int n3;
            x = ((n3 = (n2 = (bt.a(this.b) ? 1 : 0))) != 0);
        }
        if (q == 0) {
            if (n == 0) {
                return;
            }
            n2 = ((x = bt.b(this.b).x) ? 1 : 0);
        }
        if (q == 0) {
            if (!x) {
                return;
            }
            n2 = mouseEvent.getX() - bt.j(this.b);
        }
        int n4 = n2;
        int n5 = mouseEvent.getY() - bt.k(this.b);
        final int n6 = n4;
        final int n7 = 5;
        final bt b;
        Label_0216: {
            final int n10;
            Label_0134: {
                Label_0106: {
                    if (q == 0) {
                        if (n6 < n7) {
                            final int n8 = n4;
                            final int n9 = -5;
                            if (q != 0) {
                                break Label_0106;
                            }
                            if (n8 > n9) {
                                n4 = 0;
                            }
                        }
                        if (q != 0) {
                            break Label_0134;
                        }
                    }
                }
                if (n6 < n7) {
                    n10 = n5;
                    if (q != 0) {
                        break Label_0134;
                    }
                    if (n10 > -5) {
                        n5 = 0;
                    }
                }
                b = this.b;
                if (q != 0) {
                    break Label_0216;
                }
                bt.f(b);
            }
            switch (n10) {
                case 1: {
                    bt.i(this.b).a(bt.l(this.b) * n4 / this.b.getBounds().width, bt.m(this.b) * n5 / this.b.getBounds().height, 0.0);
                    if (q != 0) {
                        break;
                    }
                    return;
                }
            }
            final bt b2 = this.b;
        }
        bt.i(b).a(bt.l(this.b) * n4 / this.b.getBounds().width, -bt.m(this.b) * n5 / this.b.getBounds().height, 0.0);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int q = h.q;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final bt b = this.b;
        Label_0141: {
            if (q == 0) {
                if (bt.a(b)) {
                    final du[] l = bt.c(this.b).l();
                    final int length = l.length;
                    boolean b2 = false;
                    int n = 0;
                    boolean b3 = false;
                    while (true) {
                    Label_0093:
                        while (true) {
                            Label_0086: {
                                if (q == 0) {
                                    break Label_0086;
                                }
                                final boolean a;
                                b2 = (a = l[n].a(503, x, y));
                                if (b3 && q == 0) {
                                    break Label_0093;
                                }
                                ++n;
                            }
                            if (n < length) {
                                continue;
                            }
                            break;
                        }
                        b3 = b2;
                        if (q != 0) {
                            continue;
                        }
                        break;
                    }
                    if (!b3) {
                        this.b.setCursor(Cursor.getPredefinedCursor(0));
                        final bt b4 = this.b;
                        if (q != 0) {
                            break Label_0141;
                        }
                        if (b4.Q != null) {
                            this.b.a((f)null);
                        }
                    }
                }
                final bt b5 = this.b;
            }
        }
        final dt n2 = bt.n(b);
        if (q == 0) {
            if (n2 == null) {
                return;
            }
            bt.n(this.b);
        }
        n2.a(this.b, this.b.Q, x, y);
    }
}
