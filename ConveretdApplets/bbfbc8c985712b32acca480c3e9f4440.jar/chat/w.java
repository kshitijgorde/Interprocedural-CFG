// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;

final class w extends x implements aB
{
    private boolean a;
    private Event a;
    private String a;
    private String b;
    private int a;
    private final K a;
    
    public final void invalidate() {
        super.invalidate();
        this.a = false;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.a.a) {
            this.a.b();
        }
        if (!this.a) {
            this.a.d();
            this.a = true;
        }
        final Rectangle clipRect = graphics.getClipRect();
        final Dimension size = this.size();
        int i = this.a.a(clipRect.y);
        int a = this.a.a(clipRect.y + clipRect.height);
        final int a2 = this.a.a();
        final int c = this.a.c(a2);
        if (a >= a2) {
            a = a2 - 1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i <= a) {
            this.a.a(i, graphics);
            ++i;
        }
        if (c < size.height) {
            graphics.clearRect(0, c, size.width, size.height - c);
        }
    }
    
    public final String a(final Object o) {
        return this.b;
    }
    
    public final boolean handleEvent(final Event a) {
        int a2 = this.a.a(a.y);
        final int b = this.a.b();
        final int a3 = this.a.a();
        final int b2;
        final I i = ((b2 = this.a.b(a.x)) >= 0) ? this.a.a(b2) : null;
        y arg;
        if (a2 >= 0 && a2 < a3) {
            arg = K.a(this.a).elementAt(a2);
        }
        else {
            arg = null;
        }
        switch (a.id) {
            case 503:
            case 504: {
                if (arg == null) {
                    this.b = null;
                }
                else if (i != null) {
                    this.b = arg.a.a();
                }
                if (this.b == null || !this.b.equals(this.a)) {
                    this.postEvent(new Event(this, 7689, this.b));
                    this.a = this.b;
                    break;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.a = a;
                this.a.arg = arg;
                if (arg != null && arg.a && a.clickCount > 1 && a.clickCount != 3 && a2 == b && a2 >= 0) {
                    final Event event = new Event(this.a, a.when, 1001, a.x, a.y, b2, a.modifiers, arg);
                    if (i != null && !i.a(event, arg.a)) {
                        this.postEvent(event);
                    }
                }
                else {
                    this.a.a(a2);
                }
                return true;
            }
            case 502: {
                if (arg != null && arg.a && this.a != null && arg == this.a.arg) {
                    final Event event2 = new Event(this.a, a.when, 6301, a.x, a.y, b2, a.modifiers, arg);
                    if (i != null && !i.a(event2, arg.a)) {
                        this.postEvent(event2);
                    }
                }
                if (this.a >= 0) {
                    final int width = this.size().width;
                    final int c = this.a.c(this.a);
                    final Graphics graphics;
                    (graphics = this.getGraphics()).setColor((this.a != this.a.b()) ? this.a.b : this.a.a);
                    graphics.drawLine(0, c, width, c);
                    graphics.setColor(Color.white);
                    graphics.drawLine(0, c - 1, width, c - 1);
                    this.a = -1;
                    graphics.dispose();
                    break;
                }
                break;
            }
            case 506: {
                if (b == -1 || b == a2) {
                    return true;
                }
                if (a2 < 0) {
                    a2 = 0;
                }
                else if (a2 >= a3) {
                    a2 = a3 - 1;
                }
                this.a.a(a2);
                return true;
            }
        }
        return super.handleEvent(a);
    }
    
    public w(final K a) {
        this.a = a;
        this.a = false;
        this.a = -1;
    }
}
