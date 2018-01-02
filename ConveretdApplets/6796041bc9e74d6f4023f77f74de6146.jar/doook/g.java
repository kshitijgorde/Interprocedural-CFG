// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;

class g extends h implements aB
{
    private boolean a;
    private Event a;
    private String d;
    private String e;
    private int b;
    private final l b;
    
    public void invalidate() {
        super.invalidate();
        this.a = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.b.p) {
            this.b.a();
        }
        if (!this.a) {
            this.b.e();
            this.a = true;
        }
        final Rectangle clipRect = graphics.getClipRect();
        final Dimension size = this.size();
        int a = this.b.a(clipRect.y);
        int a2 = this.b.a(clipRect.y + clipRect.height);
        final int c = this.b.c();
        final int c2 = this.b.c(c);
        if (a2 >= c) {
            a2 = c - 1;
        }
        if (a < 0) {
            a = 0;
        }
        for (int i = a; i <= a2; ++i) {
            this.b.a(i, graphics);
        }
        if (c2 < size.height) {
            graphics.clearRect(0, c2, size.width, size.height - c2);
        }
    }
    
    public String a(final Object o) {
        return this.e;
    }
    
    public boolean handleEvent(final Event a) {
        int a2 = this.b.a(a.y);
        final int d = this.b.d();
        final int c = this.b.c();
        final int b = this.b.b(a.x);
        final j j = (b < 0) ? null : this.b.b(b);
        d arg;
        if (a2 >= 0 && a2 < c) {
            arg = l.a(this.b).elementAt(a2);
        }
        else {
            arg = null;
        }
        switch (a.id) {
            case 503:
            case 504: {
                if (arg == null) {
                    this.e = this.b.c();
                }
                else if (j != null) {
                    this.e = arg.a.c(j.b());
                }
                if (this.e == null || !this.e.equals(this.d)) {
                    this.postEvent(new Event(this, 7689, this.e));
                    this.d = this.e;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.a = a;
                this.a.arg = arg;
                if (arg != null && arg.d && a.clickCount > 1 && a.clickCount != 3 && a2 == d && a2 >= 0) {
                    final Event event = new Event(this.b, a.when, 1001, a.x, a.y, b, a.modifiers, arg);
                    if (j != null && !j.a(event, arg.a)) {
                        this.postEvent(event);
                    }
                }
                else {
                    this.b.h(a2);
                }
                return true;
            }
            case 502: {
                if (arg != null && arg.d && this.a != null && arg == this.a.arg) {
                    final Event event2 = new Event(this.b, a.when, 6301, a.x, a.y, b, a.modifiers, arg);
                    if (j != null && !j.a(event2, arg.a)) {
                        this.postEvent(event2);
                    }
                }
                if (this.b >= 0) {
                    final int width = this.size().width;
                    final int c2 = this.b.c(this.b);
                    final Graphics graphics = this.getGraphics();
                    graphics.setColor((this.b == this.b.d()) ? this.b.d : this.b.e);
                    graphics.drawLine(0, c2, width, c2);
                    graphics.setColor(Color.white);
                    graphics.drawLine(0, c2 - 1, width, c2 - 1);
                    this.b = -1;
                    graphics.dispose();
                }
                break;
            }
            case 506: {
                if (d == -1 || d == a2) {
                    return true;
                }
                if (a2 < 0) {
                    a2 = 0;
                }
                else if (a2 >= c) {
                    a2 = c - 1;
                }
                this.b.h(a2);
                return true;
            }
        }
        return super.handleEvent(a);
    }
    
    public g(final l b, final l l) {
        this.b = b;
        this.a = false;
        this.b = -1;
    }
}
