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

class ab extends aF
{
    private int c;
    private boolean c;
    private boolean j;
    private Image d;
    private Graphics a;
    private int B;
    private int o;
    private final bt a;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                if (this.c) {
                    boolean b = false;
                    final bv a = this.a.a(event);
                    if (a != null && a.i != -1) {
                        for (int i = 0; i < a.h; ++i) {
                            if (a.i >= a.g[2 * i] && a.i <= a.g[2 * i + 1]) {
                                b = true;
                                break;
                            }
                        }
                    }
                    try {
                        if (b && !this.j) {
                            this.j = true;
                            this.setCursor(Cursor.getPredefinedCursor(12));
                        }
                        else if (!b) {
                            this.j = false;
                            this.setCursor(Cursor.getPredefinedCursor(0));
                        }
                    }
                    catch (Throwable t) {
                        this.c = false;
                    }
                    break;
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.c = event.y;
                final bv a2 = this.a.a(event);
                if (a2 != null && a2.i != -1) {
                    for (int j = 0; j < a2.h; ++j) {
                        if (a2.i >= a2.g[2 * j] && a2.i <= a2.g[2 * j + 1]) {
                            String s = a2.p.substring(a2.g[2 * j], a2.g[2 * j + 1]);
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
                final bv a3 = this.a.a(event);
                if (a3 != null && a3 == this.a.a) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a3));
                    this.a.a = null;
                }
                return true;
            }
            case 506: {
                this.a.a = null;
                this.a.b.setValue(this.a.h - (event.y - this.c));
                final int n = this.a.h - this.a.b.getValue();
                if (n != 0) {
                    this.a.b(n);
                }
                this.c = event.y;
                return true;
            }
            case 1001: {
                if (!(event.arg instanceof bv)) {
                    break;
                }
                final bv bv = (bv)event.arg;
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
        this.a.v = false;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.a.v) {
            this.a.g();
            this.a.v = true;
        }
        this.a(graphics, 0, this.size().height);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (this.d == null || this.size().height != this.B || this.size().width != this.o) {
            this.B = this.size().height;
            this.o = this.size().width;
            this.d = this.createImage(this.o, this.B);
            this.a = this.d.getGraphics();
        }
        if (this.a.w) {
            if (this.a.b == null) {
                this.a.b = this.a.k.b.b();
            }
            if (this.a.b != null) {
                final Dimension size = this.size();
                final Insets insets = this.a.insets();
                final int n3 = size.width - insets.right - insets.left + 30;
                final int n4 = size.height - insets.bottom - insets.top + 30;
                int width = this.a.b.getWidth(null);
                int height = this.a.b.getHeight(null);
                if (this.a.k.b.m()) {
                    height = height * n3 / width;
                    width = n3;
                }
                int i;
                for (i = this.a.i; i >= height; i -= height) {}
                this.a.clearRect(0, -i, width, height - i);
                this.a.drawImage(this.a.b, 0, -i, width, height, null);
                this.a.drawImage(this.a.b, 0, height - i, width, height, null);
                for (int j = 0; j <= n3 / width; ++j) {
                    for (int k = 0; k <= n4 / height; ++k) {
                        if (j + k > 0) {
                            this.a.copyArea(0, 0, width, height, j * width, k * height);
                        }
                    }
                }
            }
        }
        int n5 = -this.a.h;
        for (int l = 0; l < this.a.b(); ++l) {
            final bv a = this.a.a(l);
            if (n5 + a.t >= n) {
                this.a.a(a, n5, this.a);
            }
            if ((n5 += a.t) >= n + n2) {
                break;
            }
        }
        graphics.drawImage(this.d, 0, 0, null);
    }
    
    ab(final bt a) {
        this.a = a;
        this.c = 0;
        this.c = true;
        this.j = false;
        this.d = null;
        this.a = null;
        this.B = 0;
        this.o = 0;
    }
}
