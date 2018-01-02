// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MediaTracker;

public class D extends B implements aB, Runnable
{
    private Thread b;
    private MediaTracker b;
    private aG t;
    private int a;
    private int b;
    private int Z;
    private Image h;
    private at a;
    private boolean k;
    private int o;
    
    public void a() {
        this.k = true;
    }
    
    public void c() {
        if (this.b.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.t.b() > 0) {
            this.b = 0;
            this.Z = 0;
            this.a = (at)this.t.a(0);
            final Image g = this.a.g;
            this.h = g;
            super.e = g;
            this.a(super.e.getWidth(this), super.e.getHeight(this));
            try {
                this.b.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public synchronized String a(final Object o) {
        final at a = this.a(this.b);
        if (a != null) {
            return am.a(ao.e("Click here to visit %1 (%2)."), new String[] { a.f(), a.R });
        }
        return null;
    }
    
    public at a(final int n) {
        if (n > -1 && n < this.t.b()) {
            return (at)this.t.a(n);
        }
        return null;
    }
    
    public void run() {
        while (!this.k) {
            if (this.t.b() > 0) {
                if (!this.getParent().isVisible()) {
                    this.getParent().setVisible(true);
                }
                try {
                    if (this.Z > 24) {
                        this.Z = 0;
                        if (this.a == 7) {
                            this.a = 0;
                        }
                        else {
                            ++this.a;
                        }
                        int b;
                        if (this.b < this.t.b() - 1) {
                            b = this.b + 1;
                        }
                        else {
                            b = 0;
                        }
                        super.e = this.h;
                        final at a = (at)this.t.a(b);
                        this.h = a.g;
                        this.b.checkID(a.h(), true);
                        Thread.sleep(this.o * 1000);
                        this.a = a;
                        this.b = b;
                        this.postEvent(new Event(this, 7689, this.a((Object)null)));
                    }
                    else {
                        if (this.Z == 0) {
                            this.b.waitForID(((at)this.t.a(this.b)).h());
                        }
                        Thread.sleep(20L);
                        final Graphics graphics = this.getGraphics();
                        this.paint(graphics);
                        graphics.dispose();
                        ++this.Z;
                    }
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    synchronized (this.b) {
                        this.b.wait();
                    }
                    // monitorexit(this.b)
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public synchronized void a(final at at) {
        if (this.t.b() == 0) {
            this.b = new MediaTracker(this);
        }
        this.t.a(at);
        this.b.addImage(at.g, at.h());
        this.a(at.g.getWidth(this), at.g.getHeight(this));
    }
    
    public void paint(final Graphics graphics) {
        if (super.e != null && graphics != null) {
            if (this.Z == 0) {
                graphics.drawImage(super.e, 0, 0, this);
            }
            else {
                int n = 0;
                int n2 = 0;
                final int width = this.size().width;
                final int height = this.size().height;
                final int n3 = this.Z * width / 24;
                final int n4 = this.Z * height / 24;
                if (this.t.b() > 1) {
                    int n5 = 0;
                    int n6 = 0;
                    switch (this.a) {
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
                    graphics.drawImage(this.h, n5, n6, this);
                }
                if (this.a % 2 == 0) {
                    graphics.drawImage(super.e, n, n2, this);
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                if (this.inside(event.x, event.y) && this.a.R != null) {
                    try {
                        this.postEvent(new Event(this, 1001, new URL(this.a.R)));
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
    
    public void b(final int o) {
        this.o = o;
    }
    
    public D() {
        this.b = new Thread(this, "ScrollingBanner");
        this.t = new aG();
        this.a = 0;
        this.b = 0;
        this.Z = 0;
        this.k = false;
        this.o = 7;
    }
}
