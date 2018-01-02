// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;

class aF extends L implements aj
{
    private boolean h;
    private Event a;
    private String c;
    private String v;
    private int c;
    private final am b;
    
    public void invalidate() {
        super.invalidate();
        this.h = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.b.k) {
            this.b.c();
        }
        if (!this.h) {
            this.b.h();
            this.h = true;
        }
        final Rectangle clipRect = graphics.getClipRect();
        final Dimension size = this.size();
        int b = this.b.b(clipRect.y);
        int b2 = this.b.b(clipRect.y + clipRect.height);
        final int e = this.b.e();
        final int d = this.b.d(e);
        if (b2 >= e) {
            b2 = e - 1;
        }
        if (b < 0) {
            b = 0;
        }
        for (int i = b; i <= b2; ++i) {
            this.b.a(i, graphics);
        }
        if (d < size.height) {
            graphics.clearRect(0, d, size.width, size.height - d);
        }
    }
    
    public String a(final Object o) {
        return this.v;
    }
    
    public boolean handleEvent(final Event a) {
        int b = this.b.b(a.y);
        final int f = this.b.f();
        final int e = this.b.e();
        final int c = this.b.c(a.x);
        final aB ab = (c < 0) ? null : this.b.b(c);
        bf arg;
        if (b >= 0 && b < e) {
            arg = am.a(this.b).elementAt(b);
        }
        else {
            arg = null;
        }
        switch (a.id) {
            case 503:
            case 504: {
                if (arg == null) {
                    this.v = this.b.d();
                }
                else if (ab != null) {
                    this.v = arg.a.b(ab.c());
                }
                if (this.v == null || !this.v.equals(this.c)) {
                    this.postEvent(new Event(this, 7689, this.v));
                    this.c = this.v;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.a = a;
                this.a.arg = arg;
                if (arg != null && arg.b && a.clickCount > 1 && a.clickCount != 3 && b == f && b >= 0) {
                    final Event event = new Event(this.b, a.when, 1001, a.x, a.y, c, a.modifiers, arg);
                    if (ab != null && !ab.a(event, arg.a)) {
                        this.postEvent(event);
                    }
                }
                else {
                    this.b.i(b);
                }
                return true;
            }
            case 502: {
                if (arg != null && arg.b && this.a != null && arg == this.a.arg) {
                    final Event event2 = new Event(this.b, a.when, 6301, a.x, a.y, c, a.modifiers, arg);
                    if (ab != null && !ab.a(event2, arg.a)) {
                        this.postEvent(event2);
                    }
                }
                if (this.c >= 0) {
                    final int width = this.size().width;
                    final int d = this.b.d(this.c);
                    final Graphics graphics = this.getGraphics();
                    graphics.setColor((this.c == this.b.f()) ? this.b.a : this.b.b);
                    graphics.drawLine(0, d, width, d);
                    graphics.setColor(Color.white);
                    graphics.drawLine(0, d - 1, width, d - 1);
                    this.c = -1;
                    graphics.dispose();
                }
                break;
            }
            case 506: {
                if (f == -1 || f == b) {
                    return true;
                }
                if (b < 0) {
                    b = 0;
                }
                else if (b >= e) {
                    b = e - 1;
                }
                this.b.i(b);
                return true;
            }
        }
        return super.handleEvent(a);
    }
    
    public aF(final am b, final am am) {
        this.b = b;
        this.h = false;
        this.c = -1;
    }
}
