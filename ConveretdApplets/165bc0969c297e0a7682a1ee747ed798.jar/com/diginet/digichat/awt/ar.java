// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;
import com.diginet.digichat.util.s;

class ar extends aq implements s
{
    private boolean a;
    private Event b;
    private String c;
    private String d;
    private int e;
    private final ao f;
    private MenuPopup mpuContx;
    
    public void invalidate() {
        super.invalidate();
        this.a = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.f.z) {
            this.f.a();
        }
        if (!this.a) {
            this.f.h();
            this.a = true;
        }
        final Rectangle clipRect = graphics.getClipRect();
        final Dimension size = this.size();
        int a = this.f.a(clipRect.y);
        int a2 = this.f.a(clipRect.y + clipRect.height);
        final int e = this.f.e();
        final int d = this.f.d(e);
        if (a2 >= e) {
            a2 = e - 1;
        }
        if (a < 0) {
            a = 0;
        }
        for (int i = a; i <= a2; ++i) {
            this.f.a(i, graphics);
        }
        if (d < size.height) {
            graphics.clearRect(0, d, size.width, size.height - d);
        }
    }
    
    public String a(final Object o) {
        return this.d;
    }
    
    public void setPopup(final MenuPopup mpuContx) {
        this.mpuContx = mpuContx;
    }
    
    public boolean handleEvent(final Event b) {
        int a = this.f.a(b.y);
        final int f = this.f.f();
        final int e = this.f.e();
        final int c = this.f.c(b.x);
        final ap ap = (c >= 0) ? this.f.e(c) : null;
        this.getLocation();
        av arg;
        if (a >= 0 && a < e) {
            arg = ao.a(this.f).elementAt(a);
        }
        else {
            arg = null;
        }
        switch (b.id) {
            case 503:
            case 504: {
                if (arg == null) {
                    this.d = this.f.d();
                }
                else if (ap != null) {
                    this.d = arg.a.f(ap.e());
                }
                if (this.d == null || !this.d.equals(this.c)) {
                    this.postEvent(new Event(this, 7689, this.d));
                    this.c = this.d;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.b = b;
                this.b.arg = arg;
                if (arg != null && arg.e && b.clickCount > 1 && b.clickCount != 3 && a == f && a >= 0) {
                    final Event event = new Event(this.f, b.when, 1001, b.x, b.y, c, b.modifiers, arg);
                    if (ap != null && !ap.a(event, arg.a)) {
                        this.postEvent(event);
                    }
                }
                else {
                    this.f.k(a);
                    if (this.mpuContx != null && b.metaDown() && this.f.inside(a)) {
                        this.mpuContx.show(this, b.x, b.y);
                    }
                }
                return true;
            }
            case 502: {
                if (arg != null && arg.e && this.b != null && arg == this.b.arg) {
                    final Event event2 = new Event(this.f, b.when, 6301, b.x, b.y, c, b.modifiers, arg);
                    if (ap != null && !ap.a(event2, arg.a)) {
                        this.postEvent(event2);
                    }
                }
                if (this.e >= 0) {
                    final int width = this.size().width;
                    final int d = this.f.d(this.e);
                    final Graphics graphics = this.getGraphics();
                    graphics.setColor((this.e != this.f.f()) ? this.f.h : this.f.g);
                    graphics.drawLine(0, d, width, d);
                    graphics.setColor(Color.white);
                    graphics.drawLine(0, d - 1, width, d - 1);
                    this.e = -1;
                    graphics.dispose();
                }
                break;
            }
            case 506: {
                if (f == -1 || f == a) {
                    return true;
                }
                if (a < 0) {
                    a = 0;
                }
                else if (a >= e) {
                    a = e - 1;
                }
                this.f.k(a);
                return true;
            }
        }
        return super.handleEvent(b);
    }
    
    public ar(final ao f, final ao ao) {
        this.f = f;
        this.a = false;
        this.e = -1;
        this.mpuContx = null;
    }
}
