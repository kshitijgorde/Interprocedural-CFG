// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;

class s extends aF implements aO
{
    private boolean a;
    private Event a;
    private String b;
    private String g;
    private int i;
    private final u c;
    
    public void invalidate() {
        super.invalidate();
        this.a = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.c.n) {
            this.c.f();
        }
        if (!this.a) {
            this.c.h();
            this.a = true;
        }
        final Rectangle clipRect = graphics.getClipRect();
        final Dimension size = this.size();
        int a = this.c.a(clipRect.y);
        int a2 = this.c.a(clipRect.y + clipRect.height);
        final int c = this.c.c();
        final int c2 = this.c.c(c);
        if (a2 >= c) {
            a2 = c - 1;
        }
        if (a < 0) {
            a = 0;
        }
        for (int i = a; i <= a2; ++i) {
            this.c.a(i, graphics);
        }
        if (c2 < size.height) {
            graphics.clearRect(0, c2, size.width, size.height - c2);
        }
    }
    
    public String a(final Object o) {
        return this.g;
    }
    
    public boolean handleEvent(final Event a) {
        int a2 = this.c.a(a.y);
        final int d = this.c.d();
        final int c = this.c.c();
        final int b = this.c.b(a.x);
        final p p = (b < 0) ? null : this.c.b(b);
        m arg;
        if (a2 >= 0 && a2 < c) {
            arg = u.a(this.c).elementAt(a2);
        }
        else {
            arg = null;
        }
        switch (a.id) {
            case 503:
            case 504: {
                if (arg == null) {
                    this.g = this.c.c();
                }
                else if (p != null) {
                    this.g = arg.a.a(p.b());
                }
                if (this.g == null || !this.g.equals(this.b)) {
                    this.postEvent(new Event(this, 7689, this.g));
                    this.b = this.g;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.a = a;
                this.a.arg = arg;
                if (arg != null && arg.d && a.clickCount > 1 && a.clickCount != 3 && a2 == d && a2 >= 0) {
                    final Event event = new Event(this.c, a.when, 1001, a.x, a.y, b, a.modifiers, arg);
                    if (p != null && !p.a(event, arg.a)) {
                        this.postEvent(event);
                    }
                }
                else {
                    this.c.g(a2);
                }
                return true;
            }
            case 502: {
                if (arg != null && arg.d && this.a != null && arg == this.a.arg) {
                    final Event event2 = new Event(this.c, a.when, 6301, a.x, a.y, b, a.modifiers, arg);
                    if (p != null && !p.a(event2, arg.a)) {
                        this.postEvent(event2);
                    }
                }
                if (this.i >= 0) {
                    final int width = this.size().width;
                    final int c2 = this.c.c(this.i);
                    final Graphics graphics = this.getGraphics();
                    graphics.setColor((this.i == this.c.d()) ? this.c.d : this.c.e);
                    graphics.drawLine(0, c2, width, c2);
                    graphics.setColor(Color.white);
                    graphics.drawLine(0, c2 - 1, width, c2 - 1);
                    this.i = -1;
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
                this.c.g(a2);
                return true;
            }
        }
        return super.handleEvent(a);
    }
    
    public s(final u c, final u u) {
        this.c = c;
        this.a = false;
        this.i = -1;
    }
}
