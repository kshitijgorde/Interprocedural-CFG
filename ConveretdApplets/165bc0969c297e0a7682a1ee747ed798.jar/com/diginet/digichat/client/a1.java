// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import com.diginet.digichat.awt.CommonStyle;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import com.diginet.digichat.awt.aq;

class a1 extends aq implements Runnable
{
    private int a;
    private boolean b;
    private boolean c;
    private Image d;
    private Graphics e;
    private int f;
    private int g;
    private Thread trdClick;
    private Event event;
    private final a0 h;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 503: {
                if (this.b) {
                    boolean b = false;
                    final bf a = this.h.a(event);
                    if (a != null && a.e != -1) {
                        for (int i = 0; i < a.d; ++i) {
                            if (a.e >= a.m[2 * i] && a.e <= a.m[2 * i + 1]) {
                                b = true;
                                break;
                            }
                        }
                    }
                    try {
                        if (b && !this.c) {
                            this.c = true;
                            this.setCursor(Cursor.getPredefinedCursor(12));
                        }
                        else if (!b) {
                            this.c = false;
                            this.setCursor(Cursor.getPredefinedCursor(0));
                        }
                    }
                    catch (Throwable t) {
                        this.b = false;
                    }
                }
                break;
            }
            case 501: {
                this.requestFocus();
                this.a = event.y;
                final bf a2 = this.h.a(event);
                if (a2 != null && a2.e != -1) {
                    for (int j = 0; j < a2.d; ++j) {
                        if (a2.e >= a2.m[2 * j] && a2.e <= a2.m[2 * j + 1]) {
                            String s = a2.i.substring(a2.m[2 * j], a2.m[2 * j + 1]);
                            final String lowerCase = s.toLowerCase();
                            final int index = s.indexOf(64);
                            final int index2 = s.indexOf("://");
                            if (index > 0 && index2 == -1 && lowerCase.indexOf("mailto:") == -1) {
                                s = String.valueOf("mailto:").concat(String.valueOf(s));
                            }
                            try {
                                this.postEvent(new Event(this.h, event.when, 1001, event.x, event.y, event.key, event.modifiers, new URL(s)));
                            }
                            catch (Exception ex) {}
                            break;
                        }
                    }
                }
                this.h.k = a2;
                return true;
            }
            case 502: {
                final boolean b2 = this.h.k != null && this.h.k.fCheck;
                final bf a3 = this.h.a(event);
                if (a3 != null && a3 == this.h.k) {
                    if (b2 && a3.fCheck) {
                        a3.nCheck = ((a3.nCheck == 2) ? 1 : 2);
                        this.repaint();
                        this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a3));
                    }
                    else if (this.trdClick == null) {
                        a3.fDouble = false;
                        this.event = new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, a3);
                        (this.trdClick = new Thread(this)).start();
                    }
                    else {
                        a3.fDouble = true;
                    }
                    this.h.k = null;
                }
                return true;
            }
            case 506: {
                this.h.k = null;
                this.h.i.setValue(this.h.d - (event.y - this.a));
                final int n = this.h.d - this.h.i.getValue();
                if (n != 0) {
                    this.h.c(n);
                }
                this.a = event.y;
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public void run() {
        try {
            Thread.sleep(400L);
        }
        catch (InterruptedException ex) {}
        this.trdClick = null;
        this.postEvent(this.event);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x30) == 0x0) {
            return true;
        }
        this.repaint();
        return (n & 0x20) == 0x0 && CommonStyle.fAnim;
    }
    
    public synchronized void invalidate() {
        super.invalidate();
        this.h.m = false;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.h.m) {
            this.h.c();
            this.h.m = true;
        }
        this.a(graphics, 0, this.size().height);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (this.d == null || this.size().height != this.f || this.size().width != this.g) {
            this.f = this.size().height;
            this.g = this.size().width;
            this.d = this.createImage(this.g, this.f);
            this.e = this.d.getGraphics();
        }
        if (this.h.n) {
            if (this.h.b == null) {
                this.h.b = this.h.l.cc.h();
            }
            if (this.h.b != null) {
                final Dimension size = this.size();
                final Insets insets = this.h.insets();
                final int n3 = size.width - insets.right - insets.left + 30;
                final int n4 = size.height - insets.bottom - insets.top + 30;
                int width = this.h.b.getWidth(null);
                int height = this.h.b.getHeight(null);
                if (this.h.l.cc.k()) {
                    height = height * n3 / width;
                    width = n3;
                }
                int i;
                for (i = this.h.e; i >= height; i -= height) {}
                this.e.clearRect(0, -i, width, height - i);
                this.e.drawImage(this.h.b, 0, -i, width, height, null);
                this.e.drawImage(this.h.b, 0, height - i, width, height, null);
                for (int j = 0; j <= n3 / width; ++j) {
                    for (int k = 0; k <= n4 / height; ++k) {
                        if (j + k > 0) {
                            this.e.copyArea(0, 0, width, height, j * width, k * height);
                        }
                    }
                }
            }
        }
        int n5 = -this.h.d;
        for (int l = 0; l < this.h.b(); ++l) {
            final bf a = this.h.a(l);
            if (n5 + a.b >= n) {
                this.h.a(a, n5, this.e);
            }
            if ((n5 += a.b) >= n + n2) {
                break;
            }
        }
        graphics.drawImage(this.d, 0, 0, null);
    }
    
    public void a() {
        this.d = null;
        this.repaint();
    }
    
    a1(final a0 h) {
        this.h = h;
        this.a = 0;
        this.b = true;
        this.c = false;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.trdClick = null;
    }
}
