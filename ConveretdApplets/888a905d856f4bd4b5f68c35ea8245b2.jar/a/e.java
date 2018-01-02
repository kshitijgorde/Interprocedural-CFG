// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Event;

final class e extends bd implements bf
{
    private boolean q;
    private Event q;
    private String q;
    private String w;
    private int q;
    private final cc q;
    private final cc w;
    
    public final void invalidate() {
        super.invalidate();
        this.q = false;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.q.q) {
            this.q.q();
        }
        if (!this.q) {
            this.q.e();
            this.q = true;
        }
        final Rectangle clipRect = graphics.getClipRect();
        final Dimension size = this.size();
        int q = this.q.q(clipRect.y);
        int q2 = this.q.q(clipRect.y + clipRect.height);
        final int q3 = this.q.q();
        final int e = this.q.e(q3);
        if (q2 >= q3) {
            q2 = q3 - 1;
        }
        if (q < 0) {
            q = 0;
        }
        for (int i = q; i <= q2; ++i) {
            this.q.q(i, graphics);
        }
        if (e < size.height) {
            graphics.clearRect(0, e, size.width, size.height - e);
        }
    }
    
    public final String q(final Object o) {
        return this.w;
    }
    
    public final boolean handleEvent(final Event q) {
        int q2 = this.q.q(q.y);
        final int w = this.q.w();
        final int q3 = this.q.q();
        final int w2;
        final aX ax = ((w2 = this.q.w(q.x)) >= 0) ? this.q.q(w2) : null;
        cf arg;
        if (q2 >= 0 && q2 < q3) {
            arg = cc.q(this.q).elementAt(q2);
        }
        else {
            arg = null;
        }
        switch (q.id) {
            case 503:
            case 504: {
                if (arg == null) {
                    this.w = this.q.q();
                }
                else if (ax != null) {
                    this.w = arg.q.q();
                }
                if (this.w == null || !this.w.equals(this.q)) {
                    this.postEvent(new Event(this, 7689, this.w));
                    this.q = this.w;
                    break;
                }
                break;
            }
            case 501: {
                if (q.metaDown()) {
                    if (this.w.q != null && arg != null) {
                        this.w.q.q(q.x, q.y, arg.q);
                    }
                    this.w.q = System.currentTimeMillis();
                }
                else {
                    if (cc.q(this.w) && q2 < cc.w(this.w).size() && arg != null && arg.q instanceof dh) {
                        if (cc.q(this.w).q((Object)arg.q)) {
                            cc.q(this.w).w(arg.q);
                        }
                        else {
                            cc.q(this.w).q((dh)arg.q);
                        }
                    }
                    this.repaint();
                    if (q.clickCount < 2) {
                        this.requestFocus();
                    }
                    this.q = q;
                    this.q.arg = arg;
                    if (arg != null && arg.q && q.clickCount > 1 && q.clickCount != 3 && q2 == w && q2 >= 0) {
                        final Event event = new Event(this.q, q.when, 1001, q.x, q.y, w2, q.modifiers, arg);
                        if (ax != null && !ax.q(event, arg.q)) {
                            this.postEvent(event);
                        }
                    }
                    else {
                        this.q.q(q2);
                    }
                }
                return true;
            }
            case 502: {
                if (arg != null && arg.q && this.q != null && arg == this.q.arg) {
                    final Event event2 = new Event(this.q, q.when, 6301, q.x, q.y, w2, q.modifiers, arg);
                    if (ax != null && !ax.q(event2, arg.q)) {
                        this.postEvent(event2);
                    }
                }
                if (this.q >= 0) {
                    final int width = this.size().width;
                    final int e = this.q.e(this.q);
                    final Graphics graphics;
                    (graphics = this.getGraphics()).setColor((this.q != this.q.w()) ? this.q.w : this.q.q);
                    graphics.drawLine(0, e, width, e);
                    graphics.setColor(Color.white);
                    graphics.drawLine(0, e - 1, width, e - 1);
                    this.q = -1;
                    graphics.dispose();
                    break;
                }
                break;
            }
            case 506: {
                if (w == -1 || w == q2) {
                    return true;
                }
                if (q2 < 0) {
                    q2 = 0;
                }
                else if (q2 >= q3) {
                    q2 = q3 - 1;
                }
                this.q.q(q2);
                return true;
            }
        }
        return super.handleEvent(q);
    }
    
    public e(final cc w, final cc q) {
        this.w = w;
        this.q = false;
        this.q = -1;
        this.q = q;
    }
}
