// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Image;

final class bq extends ay
{
    private int q;
    private boolean q;
    private boolean w;
    private Image q;
    private int w;
    private int e;
    private final aF q;
    private final aF w;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                if (this.q) {
                    boolean b = false;
                    final r q;
                    if ((q = this.q.q(event)) != null && q.r != -1) {
                        for (int i = 0; i < q.e; ++i) {
                            if (q.r >= q.w[i * 2] && q.r <= q.w[i * 2 + 1]) {
                                b = true;
                                break;
                            }
                        }
                    }
                    try {
                        if (b && !this.w) {
                            this.w = true;
                            this.setCursor(Cursor.getPredefinedCursor(12));
                        }
                        else if (!b) {
                            this.w = false;
                            this.setCursor(Cursor.getPredefinedCursor(0));
                        }
                    }
                    catch (Throwable t) {
                        this.q = false;
                    }
                    break;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.q = event.y;
                final r q2;
                if ((q2 = this.q.q(event)) != null && q2.r != -1) {
                    for (int j = 0; j < q2.e; ++j) {
                        if (q2.r >= q2.w[j * 2] && q2.r <= q2.w[j * 2 + 1]) {
                            String s;
                            final String lowerCase = (s = q2.q.substring(q2.w[j * 2], q2.w[j * 2 + 1])).toLowerCase();
                            final int index = s.indexOf(64);
                            final int index2 = s.indexOf("://");
                            if (index > 0 && index2 == -1 && lowerCase.indexOf("mailto:") == -1) {
                                s = "mailto:" + s;
                            }
                            try {
                                this.postEvent(new Event(this.q, event.when, 1001, event.x, event.y, event.key, event.modifiers, new URL(s)));
                            }
                            catch (Exception ex) {}
                            break;
                        }
                    }
                }
                this.q.q = q2;
                return true;
            }
            case 502: {
                final r q3 = this.q.q(event);
                if (event.metaDown()) {
                    this.w.q.q(event.x, event.y, q3);
                    this.w.q = System.currentTimeMillis();
                }
                else if (q3 != null && q3 == this.q.q) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, q3));
                    this.q.q = null;
                }
                return true;
            }
            case 506: {
                ((bG)(this.q.q = null)).setValue(this.q.w - (event.y - this.q));
                final int n;
                if ((n = this.q.w - this.q.q.getValue()) != 0) {
                    this.q.q(n);
                }
                this.q = event.y;
                return true;
            }
            case 1001: {
                final r r;
                if (event.arg instanceof r && (r = (r)event.arg).y != -1) {
                    this.q.q.t(r.y);
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint(6, 0, 24, this.size().height);
            return false;
        }
        return true;
    }
    
    public final synchronized void invalidate() {
        super.invalidate();
        this.q.q = false;
    }
    
    public final void paint(final Graphics graphics) {
        if (!this.q.q) {
            this.q.q();
            this.q.q = true;
        }
        this.q(graphics, 0, this.size().height);
    }
    
    public final int q(final Graphics graphics, int n, final int n2) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.q.q == null) {
            this.q.q = aS.w.w();
        }
        n = ((this.q.q != null) ? this.q.q.getWidth(null) : -1);
        int n3 = (this.q.q != null) ? this.q.q.getHeight(null) : -1;
        final boolean b = this.q.w && this.q.q != null;
        final boolean b2 = n >= 0 && n3 >= 0;
        if (this.q == null || (this.getSize().height != 0 && this.getSize().height != this.w) || (this.getSize().width != 0 && this.getSize().width != this.e)) {
            this.w = this.getSize().height;
            this.e = this.getSize().width;
            this.q = cs.q(this, this.e, this.w, "mpe.a");
        }
        final Graphics graphics2 = this.q.getGraphics();
        if (b && b2) {
            final Dimension size = this.getSize();
            final Insets insets = this.q.getInsets();
            final int n4 = size.width - insets.right - insets.left + 30;
            final int n5 = size.height - insets.bottom - insets.top + 30;
            if (aS.w.h()) {
                n3 = n3 * n4 / n;
                n = n4;
            }
            int i;
            for (i = this.q.e; i >= n3; i -= n3) {}
            graphics2.clearRect(0, -i, n, n3 - i);
            graphics2.drawImage(this.q.q, 0, -i, n, n3, null);
            graphics2.drawImage(this.q.q, 0, n3 - i, n, n3, null);
            for (int j = 0; j <= n4 / n; ++j) {
                for (int k = 0; k <= n5 / n3; ++k) {
                    if (j + k > 0) {
                        graphics2.copyArea(0, 0, n, n3, j * n, k * n3);
                    }
                }
            }
        }
        else if (!b2) {
            graphics2.setColor(aS.w.a);
            graphics2.fillRect(0, 0, this.e, this.w);
        }
        int n6 = -this.q.w;
        for (int l = 0; l < this.q.w(); ++l) {
            final r q = this.q.q(l);
            if (n6 + q.q >= 0) {
                this.q.q(q, n6, graphics2);
            }
            if ((n6 += q.q) >= n2 + 0) {
                break;
            }
        }
        graphics2.dispose();
        graphics.drawImage(this.q, 0, 0, null);
        return (int)(System.currentTimeMillis() - currentTimeMillis);
    }
    
    bq(aF w, final aF q) {
        this.w = w;
        this.q = 0;
        this.q = true;
        this.w = false;
        this.w = 0;
        this.e = 0;
        this.q = q;
        final aF af = w;
        final aF q2 = this.q;
        final W w2 = (W)this.q.q;
        w = q2;
        cC q3;
        if (bL.q()) {
            q3 = new bZ(w, w2);
        }
        else {
            q3 = new bz(w, w2);
        }
        af.q = q3;
    }
}