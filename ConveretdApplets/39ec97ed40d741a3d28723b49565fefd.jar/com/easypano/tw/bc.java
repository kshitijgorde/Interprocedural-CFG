// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Cursor;
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
        final boolean q = g.q;
        final bt b = this.b;
        if (!q) {
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
        Label_0092:
            while (true) {
                Label_0086: {
                    if (!q) {
                        break Label_0086;
                    }
                    final boolean a;
                    b3 = (a = l[n].a(501, x, y));
                    if (b4 && !q) {
                        break Label_0092;
                    }
                    ++n;
                }
                if (n < length) {
                    continue;
                }
                break;
            }
            b4 = b3;
            if (q) {
                continue;
            }
            break;
        }
        if (b4) {
            this.a = true;
            if (!q) {
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
        final boolean q = g.q;
        bc bc = this;
        if (!q) {
            Label_0117: {
                if (bt.a(this.b)) {
                    bc bc2 = this;
                    if (!q) {
                        if (this.a) {
                            final du[] l = bt.c(this.b).l();
                            final int length = l.length;
                            final int x = mouseEvent.getX();
                            final int y = mouseEvent.getY();
                            int n = 0;
                            while (true) {
                                Label_0098: {
                                    if (!q) {
                                        break Label_0098;
                                    }
                                    if (l[n].a(502, x, y) && !q) {
                                        break Label_0117;
                                    }
                                    ++n;
                                }
                                if (n >= length) {
                                    break Label_0117;
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
        final boolean q = g.q;
        final bt b = this.b;
        if (!q) {
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
                if (!q) {
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
        final boolean q = g.q;
        int n2;
        boolean v;
        final int n = (v = ((n2 = (this.a ? 1 : 0)) != 0)) ? 1 : 0;
        if (!q) {
            if (n != 0) {
                return;
            }
            final int n3;
            v = ((n3 = (n2 = (bt.a(this.b) ? 1 : 0))) != 0);
        }
        if (!q) {
            if (n == 0) {
                return;
            }
            n2 = ((v = bt.b(this.b).v) ? 1 : 0);
        }
        if (!q) {
            if (!v) {
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
                    if (!q) {
                        if (n6 < n7) {
                            final int n8 = n4;
                            final int n9 = -5;
                            if (q) {
                                break Label_0106;
                            }
                            if (n8 > n9) {
                                n4 = 0;
                            }
                        }
                        if (q) {
                            break Label_0134;
                        }
                    }
                }
                if (n6 < n7) {
                    n10 = n5;
                    if (q) {
                        break Label_0134;
                    }
                    if (n10 > -5) {
                        n5 = 0;
                    }
                }
                b = this.b;
                if (q) {
                    break Label_0216;
                }
                bt.f(b);
            }
            switch (n10) {
                case 1: {
                    bt.i(this.b).a(bt.l(this.b) * n4 / this.b.getBounds().width, bt.m(this.b) * n5 / this.b.getBounds().height, 0.0);
                    if (q) {
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
        final boolean q = g.q;
        final bt b = this.b;
        if (!q) {
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
        Label_0092:
            while (true) {
                Label_0086: {
                    if (!q) {
                        break Label_0086;
                    }
                    final boolean a;
                    b3 = (a = l[n].a(503, x, y));
                    if (b4 && !q) {
                        break Label_0092;
                    }
                    ++n;
                }
                if (n < length) {
                    continue;
                }
                break;
            }
            b4 = b3;
            if (q) {
                continue;
            }
            break;
        }
        if (!b4) {
            this.b.setCursor(Cursor.getPredefinedCursor(0));
            this.b.a((e)null);
        }
    }
}