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

public final class t extends c implements cn, Runnable
{
    private Thread q;
    private MediaTracker q;
    private cq q;
    private int q;
    private int w;
    private int e;
    private Image w;
    private aZ q;
    private boolean q;
    private int r;
    private boolean w;
    
    public final void q() {
        this.q = true;
    }
    
    public final void w() {
        if (this.q.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.q.q() > 0) {
            this.w = 0;
            this.e = 0;
            this.q = (aZ)this.q.q(0);
            final Image q = this.q.q;
            this.w = q;
            super.q = q;
            this.q(super.q.getWidth(this), super.q.getHeight(this));
            try {
                this.q.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final String q(final Object o) {
        final int w = this.w;
        final aZ az;
        if ((az = ((w >= 0 && w < this.q.q()) ? ((aZ)this.q.q(w)) : null)) != null) {
            return cv.q(cv.q("Click here to visit %1 (%2)."), new String[] { az.getName(), az.w });
        }
        return null;
    }
    
    public final void run() {
        while (!this.q) {
            if (this.q.q() <= 0) {
                try {
                    synchronized (this.q) {
                        this.q.wait();
                    }
                }
                catch (InterruptedException ex2) {
                    continue;
                }
                break;
            }
            if (!this.getParent().isVisible()) {
                this.getParent().setVisible(true);
            }
            try {
                if (this.e > 24) {
                    this.e = 0;
                    if (this.q == 7) {
                        this.q = 0;
                    }
                    else {
                        ++this.q;
                    }
                    int w;
                    if (this.w < this.q.q() - 1) {
                        w = this.w + 1;
                    }
                    else {
                        w = 0;
                    }
                    super.q = this.w;
                    aZ q = (aZ)this.q.q(w);
                    int n = 0;
                    while (q.q.getWidth(null) < 0) {
                        w = ++w % this.q.q();
                        q = (aZ)this.q.q(w);
                        if (n++ > this.q.q()) {
                            break;
                        }
                    }
                    this.w = q.q;
                    this.q.checkID(q.q(), true);
                    Thread.sleep(this.r * 1000);
                    this.q = q;
                    this.w = w;
                    this.postEvent(new Event(this, 7689, this.q((Object)null)));
                }
                else {
                    if (this.e == 0) {
                        this.q.waitForID(((aZ)this.q.q(this.w)).q());
                    }
                    Thread.sleep(20L);
                    final Graphics graphics = this.getGraphics();
                    this.paint(graphics);
                    if (graphics != null) {
                        graphics.dispose();
                    }
                    ++this.e;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final synchronized void q(final aZ az) {
        if (az.q == null) {
            return;
        }
        if (this.q.q() == 0) {
            this.q = new MediaTracker(this);
        }
        this.q.q(az);
        this.q.addImage(az.q, az.q());
        this.q(az.q.getWidth(this), az.q.getHeight(this));
    }
    
    public final void paint(final Graphics graphics) {
        if (super.q != null && graphics != null) {
            if (this.e == 0) {
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
            final int n3 = this.e * width / 24;
            final int n4 = this.e * height / 24;
            if (this.q.q() > 1) {
                int n5 = 0;
                int n6 = 0;
                switch (this.q) {
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
            if (this.q % 2 == 0) {
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
    
    public final void q(final int r) {
        this.r = r;
    }
    
    public t() {
        this.w = true;
        this.q = new Thread(this, "ScrollingBanner");
        this.q = new cq();
        this.q = 0;
        this.w = 0;
        this.e = 0;
        this.q = false;
        this.r = 7;
    }
}
