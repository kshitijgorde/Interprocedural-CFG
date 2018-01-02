// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MediaTracker;

public final class aJ extends q implements aB, Runnable
{
    private Thread a;
    private MediaTracker a;
    private av a;
    private int b;
    private int c;
    private int d;
    private Image b;
    private c a;
    public boolean a;
    public int a;
    
    public final void a() {
        if (this.a.a() == 0) {
            return;
        }
        if (this.a == null) {
            this.a = new Thread(this, "ScrollingBanner");
        }
        if (this.a.isAlive()) {
            return;
        }
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Throwable t) {}
        if (this.a.a() > 0) {
            this.c = 0;
            this.d = 0;
            this.a = (c)this.a.a(0);
            final Image a = this.a.a;
            this.b = a;
            super.a = a;
            if (super.a != null) {
                this.a(super.a.getWidth(this), super.a.getHeight(this));
            }
            try {
                this.a.start();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final synchronized String a(final Object o) {
        final aJ aj = this;
        final int c = this.c;
        this = aj;
        final c c2;
        if ((c2 = ((c > -1 && c < this.a.a()) ? ((c)this.a.a(c)) : null)) != null) {
            return bm.a(aS.a(473), new String[] { c2.d, c2.b });
        }
        return null;
    }
    
    public final void run() {
        while (!this.a) {
            if (this.a.a() <= 0) {
                try {
                    this.a(1, 1);
                    if (this.a == null) {
                        return;
                    }
                    if (this.a.isAlive()) {
                        this.a.interrupt();
                        synchronized (this.a) {
                            this.a.wait();
                            return;
                        }
                    }
                    this.a.stop();
                    return;
                }
                catch (InterruptedException ex) {
                    this.a = null;
                    continue;
                }
                break;
            }
            if (!this.getParent().isVisible()) {
                this.getParent().setVisible(true);
            }
            try {
                if (this.d > 24) {
                    this.d = 0;
                    if (this.b == 7) {
                        this.b = 0;
                    }
                    else {
                        ++this.b;
                    }
                    int c;
                    if (this.c < this.a.a() - 1) {
                        c = this.c + 1;
                    }
                    else {
                        c = 0;
                    }
                    super.a = this.b;
                    final c a = (c)this.a.a(c);
                    this.b = a.a;
                    this.a.checkID(a.i, true);
                    Thread.sleep(this.a * 1000);
                    this.a = a;
                    this.c = c;
                    this.postEvent(new Event(this, 7689, this.a((Object)null)));
                }
                else {
                    if (this.d == 0) {
                        this.a.waitForID(((c)this.a.a(this.c)).i);
                    }
                    Thread.sleep(20L);
                    final Graphics graphics = this.getGraphics();
                    this.paint(graphics);
                    graphics.dispose();
                    ++this.d;
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public final synchronized void a(final c c) {
        if (this.a.a() == 0 && this.a == null) {
            this.a = new MediaTracker(this);
        }
        this.a.a(c);
        if (c.a == null) {
            return;
        }
        this.a.addImage(c.a, c.i);
        this.a(c.a.getWidth(this), c.a.getHeight(this));
    }
    
    public final synchronized void a(final int n) {
        this.a.b(n);
    }
    
    public final void paint(final Graphics graphics) {
        if (super.a != null && graphics != null) {
            if (this.d == 0) {
                graphics.drawImage(super.a, 0, 0, this);
                return;
            }
            int n = 0;
            int n2 = 0;
            final int width = this.size().width;
            final int height = this.size().height;
            final int n3 = this.d * width / 24;
            final int n4 = this.d * height / 24;
            if (this.a.a() > 1) {
                int n5 = 0;
                int n6 = 0;
                switch (this.b) {
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
                graphics.drawImage(this.b, n5, n6, this);
            }
            if (this.b % 2 == 0) {
                graphics.drawImage(super.a, n, n2, this);
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
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
    
    public aJ() {
        this.a = new Thread(this, "ScrollingBanner");
        this.a = new av();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a = false;
        this.a = 7;
    }
}
