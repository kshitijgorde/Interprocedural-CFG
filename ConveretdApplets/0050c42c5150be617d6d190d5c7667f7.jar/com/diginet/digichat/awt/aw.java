// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.net.MalformedURLException;
import java.net.URL;
import com.diginet.digichat.util.i;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Event;
import com.diginet.digichat.util.StringSubst;
import com.esial.util.LanguageSupport;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import com.diginet.digichat.common.be;
import java.awt.Image;
import com.diginet.digichat.util.k;
import java.awt.MediaTracker;
import com.diginet.digichat.util.p;

public class aw extends aa implements Runnable, p
{
    private Thread a;
    private MediaTracker b;
    private k c;
    private int d;
    private int e;
    private int f;
    private Image g;
    private be h;
    private boolean i;
    private int j;
    
    public final void c() {
        this.i = true;
    }
    
    public final void d() {
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
            this.h = (be)this.c.c(0);
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
    
    public final synchronized String a(final Object o) {
        final be a = this.a(this.e);
        if (a != null) {
            return StringSubst.Substitute(LanguageSupport.translate("Click here to visit %1 (%2)."), new String[] { a.getName(), a.b });
        }
        return null;
    }
    
    public final be a(final int n) {
        if (n > -1 && n < this.c.b()) {
            return (be)this.c.c(n);
        }
        return null;
    }
    
    public final void run() {
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
                        final be h = (be)this.c.c(e);
                        this.g = h.f;
                        this.b.checkID(h.x(), true);
                        Thread.sleep(this.j * 1000);
                        this.h = h;
                        this.e = e;
                        this.postEvent(new Event(this, 7689, this.a((Object)null)));
                    }
                    else {
                        if (this.f == 0) {
                            this.b.waitForID(((be)this.c.c(this.e)).x());
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
    
    public final synchronized void a(final be be) {
        if (this.c.b() == 0) {
            this.b = new MediaTracker(this);
        }
        this.c.a(be);
        this.b.addImage(be.f, be.x());
        this.a(be.f.getWidth(this), be.f.getHeight(this));
    }
    
    public final void paint(final Graphics graphics) {
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
    
    public final boolean handleEvent(final Event event) {
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
    
    public final void b(final int j) {
        this.j = j;
    }
    
    public aw() {
        this.a = new Thread(this, "ScrollingBanner");
        this.c = new k();
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.i = false;
        this.j = 7;
    }
}
