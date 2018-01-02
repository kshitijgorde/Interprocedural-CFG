// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MediaTracker;

public final class do extends bl implements bf, Runnable
{
    private Thread q;
    private MediaTracker q;
    private M q;
    private int w;
    private int e;
    private int r;
    private Image w;
    private C q;
    public boolean q;
    public int q;
    private boolean w;
    
    public final void q() {
        if (this.q.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.q.q > 0) {
            this.e = 0;
            this.r = 0;
            this.q = (C)this.q.q(0);
            final Image q = this.q.q;
            this.w = q;
            super.q = q;
            this.q(super.q.getWidth(this), super.q.getHeight(this));
            try {
                this.q.start();
            }
            catch (Exception ex) {}
        }
    }
    
    public final String q(final Object o) {
        final int e = this.e;
        final C c;
        if ((c = ((e >= 0 && e < this.q.q) ? ((C)this.q.q(e)) : null)) != null) {
            return B.q(be.w("Click here to visit %1 (%2)."), new String[] { c.a, c.w });
        }
        return null;
    }
    
    public final void run() {
        while (!this.q) {
            if (this.q.q <= 0) {
                try {
                    synchronized (this.q) {
                        this.q.wait();
                    }
                }
                catch (InterruptedException ex) {
                    continue;
                }
                break;
            }
            if (!this.getParent().isVisible()) {
                this.getParent().setVisible(true);
            }
            try {
                if (this.r > 24) {
                    this.r = 0;
                    if (this.w == 7) {
                        this.w = 0;
                    }
                    else {
                        ++this.w;
                    }
                    int e;
                    if (this.e < this.q.q - 1) {
                        e = this.e + 1;
                    }
                    else {
                        e = 0;
                    }
                    super.q = this.w;
                    C q = (C)this.q.q(e);
                    int n = 0;
                    while (q.q.getWidth(null) < 0) {
                        e = ++e % this.q.q;
                        q = (C)this.q.q(e);
                        if (n++ > this.q.q) {
                            break;
                        }
                    }
                    this.w = q.q;
                    this.q.checkID(q.s, true);
                    Thread.sleep(this.q * 1000);
                    this.q = q;
                    this.e = e;
                    this.postEvent(new Event(this, 7689, this.q((Object)null)));
                }
                else {
                    if (this.r == 0) {
                        this.q.waitForID(((C)this.q.q(this.e)).s);
                    }
                    Thread.sleep(20L);
                    final Graphics graphics = this.getGraphics();
                    this.paint(graphics);
                    if (graphics != null) {
                        graphics.dispose();
                    }
                    ++this.r;
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public final synchronized void q(final C c) {
        if (c.q == null) {
            return;
        }
        if (this.q.q == 0) {
            this.q = new MediaTracker(this);
        }
        this.q.q(c);
        this.q.addImage(c.q, c.s);
        this.q(c.q.getWidth(this), c.q.getHeight(this));
    }
    
    public final void paint(final Graphics graphics) {
        if (super.q != null && graphics != null) {
            if (this.r == 0) {
                if (this.w) {
                    this.postEvent(new Event(this, 7688, null));
                    this.w = false;
                }
                graphics.drawImage(super.q, 0, 0, this);
                return;
            }
            int n = 0;
            int n2 = 0;
            final int width = this.size().width;
            final int height = this.size().height;
            final int n3 = this.r * width / 24;
            final int n4 = this.r * height / 24;
            if (this.q.q > 1) {
                int n5 = 0;
                int n6 = 0;
                switch (this.w) {
                    case 0:
                    case 5: {
                        n5 = (n = -n3) + width;
                        n6 = 0;
                        break;
                    }
                    case 1:
                    case 4: {
                        n5 = (n = n3) - width;
                        n6 = 0;
                        break;
                    }
                    case 2:
                    case 7: {
                        n2 = -n4;
                        n5 = 0;
                        n6 = n2 + height;
                        break;
                    }
                    default: {
                        n2 = n4;
                        n5 = 0;
                        n6 = n2 - height;
                        break;
                    }
                }
                graphics.drawImage(this.w, n5, n6, this);
            }
            if (this.w % 2 == 0) {
                graphics.drawImage(super.q, n, n2, this);
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 502: {
                if (this.inside(event.x, event.y) && this.q.w != null) {
                    try {
                        this.postEvent(new Event(this, 1001, new URL(this.q.w)));
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
    
    public do() {
        this.w = true;
        this.q = new Thread(this, "ScrollingBanner");
        this.q = new M();
        this.w = 0;
        this.e = 0;
        this.r = 0;
        this.q = false;
        this.q = 7;
    }
}
