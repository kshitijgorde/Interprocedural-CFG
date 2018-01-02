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

public class y extends bp implements aj, Runnable
{
    private Thread a;
    private MediaTracker a;
    private k a;
    private int d;
    private int g;
    private int h;
    private Image e;
    private z a;
    private boolean g;
    private int k;
    public aW b;
    
    public void a() {
        this.g = true;
    }
    
    public void b() {
        if (this.a.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.a.a() > 0) {
            this.g = 0;
            this.h = 0;
            this.a = (z)this.a.a(0);
            final Image f = this.a.f;
            this.e = f;
            super.b = f;
            this.b(super.b.getWidth(this), super.b.getHeight(this));
            try {
                this.a.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public synchronized String a(final Object o) {
        final z a = this.a(this.g);
        if (a != null) {
            return aC.a(aG.a("Click here to visit %1 (%2)."), new String[] { a.g(), a.b });
        }
        return null;
    }
    
    public z a(final int n) {
        if (n > -1 && n < this.a.a()) {
            return (z)this.a.a(n);
        }
        return null;
    }
    
    public void run() {
        while (!this.g) {
            if (this.a.a() > 0) {
                if (!this.getParent().isVisible()) {
                    this.getParent().setVisible(true);
                }
                try {
                    if (this.h > 24) {
                        this.h = 0;
                        if (this.d == 7) {
                            this.d = 0;
                        }
                        else {
                            ++this.d;
                        }
                        int g;
                        if (this.g < this.a.a() - 1) {
                            g = this.g + 1;
                        }
                        else {
                            g = 0;
                        }
                        super.b = this.e;
                        final z a = (z)this.a.a(g);
                        this.e = a.f;
                        this.a.checkID(a.b(), true);
                        Thread.sleep(this.k * 1000);
                        this.a = a;
                        this.g = g;
                        this.postEvent(new Event(this, 7689, this.a((Object)null)));
                    }
                    else {
                        if (this.h == 0) {
                            this.a.waitForID(((z)this.a.a(this.g)).b());
                        }
                        Thread.sleep(20L);
                        final Graphics graphics = this.getGraphics();
                        this.paint(graphics);
                        graphics.dispose();
                        ++this.h;
                    }
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    synchronized (this.a) {
                        this.a.wait();
                    }
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public synchronized void a(final z z) {
        if (this.a.a() == 0) {
            this.a = new MediaTracker(this);
        }
        this.a.a(z);
        this.a.addImage(z.f, z.b());
        this.b(z.f.getWidth(this), z.f.getHeight(this));
    }
    
    public void paint(final Graphics graphics) {
        if (super.b != null && graphics != null) {
            if (this.h == 0) {
                graphics.drawImage(super.b, 0, 0, this);
            }
            else {
                int n = 0;
                int n2 = 0;
                final int width = this.size().width;
                final int height = this.size().height;
                final int n3 = this.h * width / 24;
                final int n4 = this.h * height / 24;
                if (this.a.a() > 1) {
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
                    graphics.drawImage(this.e, n5, n6, this);
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
                if (this.inside(event.x, event.y) && this.a.b != null) {
                    try {
                        this.postEvent(new Event(this, 1001, new URL(this.a.b)));
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
    
    public void c(final int k) {
        this.k = k;
    }
    
    public y() {
        this.a = new Thread(this, "ScrollingBanner");
        this.a = new k();
        this.d = 0;
        this.g = 0;
        this.h = 0;
        this.g = false;
        this.k = 7;
    }
}
