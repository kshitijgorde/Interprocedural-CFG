// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

class Y extends L
{
    private int e;
    private boolean j;
    private boolean m;
    private Image j;
    private Graphics b;
    private int d;
    private int g;
    private final bl a;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                if (this.j) {
                    boolean b = false;
                    final aN a = this.a.a(event);
                    if (a != null && a.c != -1) {
                        for (int i = 0; i < a.b; ++i) {
                            if (a.c >= a.f[2 * i] && a.c <= a.f[2 * i + 1]) {
                                b = true;
                                break;
                            }
                        }
                    }
                    try {
                        if (b && !this.m) {
                            this.m = true;
                            this.setCursor(Cursor.getPredefinedCursor(12));
                        }
                        else if (!b) {
                            this.m = false;
                            this.setCursor(Cursor.getPredefinedCursor(0));
                        }
                    }
                    catch (Throwable t) {
                        this.j = false;
                    }
                    break;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.e = event.y;
                final aN a2 = this.a.a(event);
                if (a2 != null && a2.c != -1) {
                    for (int j = 0; j < a2.b; ++j) {
                        if (a2.c >= a2.f[2 * j] && a2.c <= a2.f[2 * j + 1]) {
                            String s = a2.d.substring(a2.f[2 * j], a2.f[2 * j + 1]);
                            final String lowerCase = s.toLowerCase();
                            final int index = s.indexOf(64);
                            final int index2 = s.indexOf("://");
                            if (index > 0 && index2 == -1 && lowerCase.indexOf("mailto:") == -1) {
                                s = "mailto:" + s;
                            }
                            try {
                                this.postEvent(new Event(this.a, event.when, 1001, event.x, event.y, event.key, event.modifiers, new URL(s)));
                            }
                            catch (Exception ex) {}
                            break;
                        }
                    }
                }
                this.a.a = a2;
                return true;
            }
            case 502: {
                final aN a3 = this.a.a(event);
                if (a3 != null && a3 == this.a.a) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a3));
                    this.a.a = null;
                }
                return true;
            }
            case 506: {
                this.a.a = null;
                this.a.b.setValue(this.a.a - (event.y - this.e));
                final int n = this.a.a - this.a.b.getValue();
                if (n != 0) {
                    this.a.c(n);
                }
                this.e = event.y;
                return true;
            }
            case 1001: {
                if (!(event.arg instanceof aN)) {
                    break;
                }
                final aN an = (aN)event.arg;
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint(6, 0, 24, this.size().height);
            return false;
        }
        return true;
    }
    
    public synchronized void invalidate() {
        super.invalidate();
        this.a.q = false;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.a.q) {
            this.a.b();
            this.a.q = true;
        }
        this.a(graphics, 0, this.size().height);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (this.j == null || this.size().height != this.d || this.size().width != this.g) {
            this.d = this.size().height;
            this.g = this.size().width;
            this.j = this.createImage(this.g, this.d);
            this.b = this.j.getGraphics();
        }
        if (this.a.k) {
            if (this.a.a == null) {
                this.a.a = this.a.h.c.b();
            }
            if (this.a.a != null) {
                final Dimension size = this.size();
                final Insets insets = this.a.insets();
                final int n3 = size.width - insets.right - insets.left + 30;
                final int n4 = size.height - insets.bottom - insets.top + 30;
                int width = this.a.a.getWidth(null);
                int height = this.a.a.getHeight(null);
                if (this.a.h.c.g()) {
                    height = height * n3 / width;
                    width = n3;
                }
                int i;
                for (i = this.a.b; i >= height; i -= height) {}
                this.b.clearRect(0, -i, width, height - i);
                this.b.drawImage(this.a.a, 0, -i, width, height, null);
                this.b.drawImage(this.a.a, 0, height - i, width, height, null);
                for (int j = 0; j <= n3 / width; ++j) {
                    for (int k = 0; k <= n4 / height; ++k) {
                        if (j + k > 0) {
                            this.b.copyArea(0, 0, width, height, j * width, k * height);
                        }
                    }
                }
            }
        }
        int n5 = -this.a.a;
        for (int l = 0; l < this.a.a(); ++l) {
            final aN a = this.a.a(l);
            if (n5 + a.f >= n) {
                this.a.a(a, n5, this.b);
            }
            if ((n5 += a.f) >= n + n2) {
                break;
            }
        }
        graphics.drawImage(this.j, 0, 0, null);
    }
    
    Y(final bl a) {
        this.a = a;
        this.e = 0;
        this.j = true;
        this.m = false;
        this.j = null;
        this.b = null;
        this.d = 0;
        this.g = 0;
    }
}
