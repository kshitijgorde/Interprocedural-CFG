// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.net.MalformedURLException;
import java.net.URL;
import com.diginet.digichat.util.l;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.Event;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import com.diginet.digichat.client.h;
import com.diginet.digichat.common.bn;
import java.awt.Image;
import com.diginet.digichat.util.n;
import java.awt.MediaTracker;
import com.diginet.digichat.util.s;

public class a8 extends a9 implements Runnable, s
{
    private Thread a;
    private MediaTracker b;
    private n c;
    private int d;
    private int e;
    private int f;
    private Image g;
    private bn h;
    private boolean i;
    private int j;
    private h hUser;
    
    public void a() {
        this.i = true;
    }
    
    public void b() {
        if (this.a.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.c.b() > 0) {
            this.e = 0;
            this.f = 0;
            this.h = (bn)this.c.d(0);
            final Image f = this.h.f;
            this.g = f;
            super.b = f;
            this.a(super.b.getWidth(this), super.b.getHeight(this));
            try {
                this.a.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public synchronized String a(final Object o) {
        final bn a = this.a(this.e);
        if (a != null) {
            return a5.a(com.esial.util.c.a("Click here to visit %1 (%2)."), new String[] { a.x(), a.b });
        }
        return null;
    }
    
    public bn a(final int n) {
        if (n > -1 && n < this.c.b()) {
            return (bn)this.c.d(n);
        }
        return null;
    }
    
    public void run() {
        while (!this.i) {
            if (this.c.b() > 0) {
                if (!this.getParent().isVisible()) {
                    this.getParent().setVisible(true);
                }
                try {
                    if (this.f > 24) {
                        this.f = 0;
                        if (this.d == 7) {
                            this.d = 0;
                        }
                        else {
                            ++this.d;
                        }
                        int e;
                        if (this.e < this.c.b() - 1) {
                            e = this.e + 1;
                        }
                        else {
                            e = 0;
                        }
                        super.b = this.g;
                        final bn h = (bn)this.c.d(e);
                        if (h.f == null) {
                            h.f = this.hUser.a(String.valueOf("banners/").concat(String.valueOf(h.a)), 30);
                        }
                        this.g = h.f;
                        this.b.checkID(h.w(), true);
                        if (this.b.isErrorID(h.w()) || h.f.getWidth(this) < 1 || h.f.getHeight(this) < 1) {
                            this.c.f(e);
                        }
                        else {
                            Thread.sleep(this.j * 1000);
                            this.h = h;
                            this.e = e;
                            this.postEvent(new Event(this, 7689, this.a((Object)null)));
                        }
                    }
                    else {
                        if (this.f == 0) {
                            this.b.waitForID(((bn)this.c.d(this.e)).w());
                        }
                        Thread.sleep(20L);
                        final Graphics graphics = this.getGraphics();
                        this.paint(graphics);
                        graphics.dispose();
                        ++this.f;
                    }
                }
                catch (Exception ex) {}
            }
            else {
                if (this.getParent().isVisible()) {
                    this.getParent().setVisible(false);
                    ImageObserver parent;
                    a8 a8;
                    for (parent = this, a8 = this; parent != null && !(a8 instanceof Window); a8 = (a8)parent, parent = ((a8)parent).getParent()) {
                        ((a8)parent).invalidate();
                    }
                    a8.validate();
                }
                try {
                    synchronized (this.a) {
                        this.a.wait();
                    }
                    // monitorexit(this.a)
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public synchronized void a(final bn bn) {
        if (this.c.b() == 0) {
            if (bn.f == null) {
                bn.f = this.hUser.a(String.valueOf("banners/").concat(String.valueOf(bn.a)), 30);
            }
            (this.b = new MediaTracker(this)).addImage(bn.f, bn.w());
            this.a(bn.f.getWidth(this), bn.f.getHeight(this));
        }
        this.c.a(bn);
    }
    
    public void paint(final Graphics graphics) {
        if (super.b != null && graphics != null) {
            if (this.f == 0) {
                graphics.drawImage(super.b, 0, 0, this);
            }
            else {
                int n = 0;
                int n2 = 0;
                final int width = this.size().width;
                final int height = this.size().height;
                final int n3 = this.f * width / 24;
                final int n4 = this.f * height / 24;
                if (this.c.b() > 1) {
                    int n5 = 0;
                    int n6 = 0;
                    switch (this.d) {
                        case 0:
                        case 5: {
                            n = -n3;
                            n5 = n + width;
                            n6 = n2;
                            break;
                        }
                        case 1:
                        case 4: {
                            n = n3;
                            n5 = n - width;
                            n6 = n2;
                            break;
                        }
                        case 2:
                        case 7: {
                            n2 = -n4;
                            n5 = n;
                            n6 = n2 + height;
                            break;
                        }
                        default: {
                            n2 = n4;
                            n5 = n;
                            n6 = n2 - height;
                            break;
                        }
                    }
                    graphics.drawImage(this.g, n5, n6, this);
                }
                if (this.d % 2 == 0) {
                    graphics.drawImage(super.b, n, n2, this);
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                if (this.inside(event.x, event.y) && this.h.b != null) {
                    try {
                        this.postEvent(new Event(this, 1001, new URL(this.h.b)));
                    }
                    catch (MalformedURLException ex) {}
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public void b(final int j) {
        this.j = j;
    }
    
    public a8(final h hUser) {
        this.a = new Thread(this, "ScrollingBanner");
        this.c = new n();
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.i = false;
        this.j = 7;
        this.hUser = hUser;
    }
}
