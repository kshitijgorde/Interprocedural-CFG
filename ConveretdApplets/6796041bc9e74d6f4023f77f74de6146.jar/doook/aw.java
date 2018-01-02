// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;

class aw extends h
{
    private int g;
    private boolean m;
    private boolean b;
    private Image f;
    private Graphics a;
    private int Z;
    private int ai;
    private final ay b;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.requestFocus();
                this.g = event.y;
                final Z a = this.b.a(event);
                if (a != null && a.b != -1) {
                    for (int i = 0; i < a.a; ++i) {
                        if (a.b >= a.b[2 * i] && a.b <= a.b[2 * i + 1]) {
                            String s = a.l.substring(a.b[2 * i], a.b[2 * i + 1]);
                            final String lowerCase = s.toLowerCase();
                            final int index = s.indexOf(64);
                            final int index2 = s.indexOf("://");
                            if (index > 0 && index2 == -1 && lowerCase.indexOf("mailto:") == -1) {
                                s = "mailto:" + s;
                            }
                            try {
                                this.postEvent(new Event(this.b, event.when, 1001, event.x, event.y, event.key, event.modifiers, new URL(s)));
                            }
                            catch (Exception ex) {}
                            break;
                        }
                    }
                }
                this.b.a = a;
                return true;
            }
            case 502: {
                final Z a2 = this.b.a(event);
                if (a2 != null && a2 == this.b.a) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a2));
                    this.b.a = null;
                }
                return true;
            }
            case 506: {
                this.b.a = null;
                this.b.b.setValue(this.b.a - (event.y - this.g));
                final int n = this.b.a - this.b.b.getValue();
                if (n != 0) {
                    this.b.c(n);
                }
                this.g = event.y;
                return true;
            }
            case 1001: {
                if (!(event.arg instanceof Z)) {
                    break;
                }
                final Z z = (Z)event.arg;
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint(6, 0, 24, this.getSize().height);
            return false;
        }
        return true;
    }
    
    public synchronized void invalidate() {
        super.invalidate();
        this.b.s = false;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.b.s) {
            this.b.d();
            this.b.s = true;
        }
        this.a(graphics, 0, this.size().height);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (this.f == null || this.size().height != this.Z || this.size().width != this.ai) {
            this.Z = this.size().height;
            this.ai = this.size().width;
            this.f = this.createImage(this.ai, this.Z);
            this.a = this.f.getGraphics();
        }
        if (this.b.t) {
            if (this.b.e == null) {
                this.b.e = this.b.i.a.b();
            }
            if (this.b.e != null) {
                final Dimension size = this.size();
                final Insets insets = this.b.insets();
                final int n3 = size.width - insets.right - insets.left + 30;
                final int n4 = size.height - insets.bottom - insets.top + 30;
                int width = this.b.e.getWidth(null);
                int height = this.b.e.getHeight(null);
                if (this.b.i.a.h()) {
                    height = height * n3 / width;
                    width = n3;
                }
                int i;
                for (i = this.b.b; i >= height; i -= height) {}
                this.a.clearRect(0, -i, width, height - i);
                this.a.drawImage(this.b.e, 0, -i, width, height, null);
                this.a.drawImage(this.b.e, 0, height - i, width, height, null);
                for (int j = 0; j <= n3 / width; ++j) {
                    for (int k = 0; k <= n4 / height; ++k) {
                        if (j + k > 0) {
                            this.a.copyArea(0, 0, width, height, j * width, k * height);
                        }
                    }
                }
            }
        }
        int n5 = -this.b.a;
        for (int l = 0; l < this.b.b(); ++l) {
            final Z a = this.b.a(l);
            if (n5 + a.h >= n) {
                this.b.a(a, n5, this.a);
            }
            if ((n5 += a.h) >= n + n2) {
                break;
            }
        }
        graphics.drawImage(this.f, 0, 0, null);
    }
    
    public void a() {
        this.f = null;
        this.repaint();
    }
    
    aw(final ay b) {
        this.b = b;
        this.g = 0;
        this.m = true;
        this.b = false;
        this.f = null;
        this.a = null;
        this.Z = 0;
        this.ai = 0;
    }
}
