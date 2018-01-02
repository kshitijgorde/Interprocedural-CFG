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

public class N extends M implements aO, Runnable
{
    private Thread b;
    private MediaTracker a;
    private aT b;
    private int h;
    private int i;
    private int B;
    private Image f;
    private aj a;
    private boolean r;
    private int C;
    public at f;
    
    public void f() {
        this.r = true;
    }
    
    public void c() {
        if (this.b.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.b.b() > 0) {
            this.i = 0;
            this.B = 0;
            this.a = (aj)this.b.a(0);
            final Image e = this.a.e;
            this.f = e;
            super.b = e;
            this.a(super.b.getWidth(this), super.b.getHeight(this));
            try {
                this.b.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public synchronized String a(final Object o) {
        final aj a = this.a(this.i);
        if (a != null) {
            return H.a(ar.b("Click here to visit %1 (%2)."), new String[] { a.d(), a.o });
        }
        return null;
    }
    
    public aj a(final int n) {
        if (n > -1 && n < this.b.b()) {
            return (aj)this.b.a(n);
        }
        return null;
    }
    
    public void run() {
        while (!this.r) {
            if (this.b.b() > 0) {
                if (!this.getParent().isVisible()) {
                    this.getParent().setVisible(true);
                }
                try {
                    if (this.B > 24) {
                        this.B = 0;
                        if (this.h == 7) {
                            this.h = 0;
                        }
                        else {
                            ++this.h;
                        }
                        int i;
                        if (this.i < this.b.b() - 1) {
                            i = this.i + 1;
                        }
                        else {
                            i = 0;
                        }
                        super.b = this.f;
                        final aj a = (aj)this.b.a(i);
                        this.f = a.e;
                        this.a.checkID(a.e(), true);
                        Thread.sleep(this.C * 1000);
                        this.a = a;
                        this.i = i;
                        this.postEvent(new Event(this, 7689, this.a((Object)null)));
                    }
                    else {
                        if (this.B == 0) {
                            this.a.waitForID(((aj)this.b.a(this.i)).e());
                        }
                        Thread.sleep(20L);
                        final Graphics graphics = this.getGraphics();
                        this.paint(graphics);
                        graphics.dispose();
                        ++this.B;
                    }
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    synchronized (this.b) {
                        this.b.wait();
                    }
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public synchronized void a(final aj aj) {
        if (this.b.b() == 0) {
            this.a = new MediaTracker(this);
        }
        this.b.a(aj);
        this.a.addImage(aj.e, aj.e());
        this.a(aj.e.getWidth(this), aj.e.getHeight(this));
    }
    
    public void paint(final Graphics graphics) {
        if (super.b != null && graphics != null) {
            if (this.B == 0) {
                graphics.drawImage(super.b, 0, 0, this);
            }
            else {
                int n = 0;
                int n2 = 0;
                final int width = this.size().width;
                final int height = this.size().height;
                final int n3 = this.B * width / 24;
                final int n4 = this.B * height / 24;
                if (this.b.b() > 1) {
                    int n5 = 0;
                    int n6 = 0;
                    switch (this.h) {
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
                    graphics.drawImage(this.f, n5, n6, this);
                }
                if (this.h % 2 == 0) {
                    graphics.drawImage(super.b, n, n2, this);
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                if (this.inside(event.x, event.y) && this.a.o != null) {
                    try {
                        this.postEvent(new Event(this, 1001, new URL(this.a.o)));
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
    
    public void b(final int c) {
        this.C = c;
    }
    
    public N() {
        this.b = new Thread(this, "ScrollingBanner");
        this.b = new aT();
        this.h = 0;
        this.i = 0;
        this.B = 0;
        this.r = false;
        this.C = 7;
    }
}
