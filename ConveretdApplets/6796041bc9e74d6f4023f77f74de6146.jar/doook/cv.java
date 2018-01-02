// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Cursor;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.applet.Applet;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

public class cv extends Panel implements Runnable
{
    private int o;
    private int j;
    private int k;
    private boolean ad;
    private boolean k;
    Image x;
    private Object e;
    private boolean e;
    public boolean as;
    public Font f;
    public Color s;
    public Color t;
    public int width;
    public int height;
    private int m;
    public int ap;
    private Vector b;
    private Image y;
    private Thread e;
    public int aJ;
    public String af;
    public String ag;
    public int aK;
    public boolean at;
    public boolean debug;
    public Applet a;
    public boolean au;
    public t l;
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.ad) {
            this.a(n);
        }
        this.ad = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int j, final int n) {
        this.j = j;
        if (this.as) {
            this.k = true;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.j = 0;
        this.k = false;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int j, final int n) {
        this.j = j;
        return this.ad = true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.o = this.ap;
        this.j = n;
        this.k = n;
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.setSize(this.width, this.height);
        graphics.drawImage(this.y, 0, 0, this);
        synchronized (this.e = true) {
            this.e.notifyAll();
        }
    }
    
    public void stop() {
        try {
            if (this.e != null) {
                this.e.stop();
            }
        }
        catch (Exception ex) {}
        this.e = null;
    }
    
    public void start() {
        if (this.e == null) {
            this.e = new Thread(this);
        }
        if (!this.e.isAlive()) {
            this.e.start();
        }
    }
    
    public static void a(final Vector vector) {
        vector.removeAllElements();
    }
    
    public void run() {
        this.f = new Font(this.af, 0, this.aJ);
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Exception ex3) {}
        this.setBackground(this.t);
        while (true) {
            if (this.au) {
                a(this.b);
                final aM am = new aM(this, this.width, this.height, this.ag);
                if (this.y != null) {
                    this.y.flush();
                    this.y = null;
                }
                if (this.x != null) {
                    this.x.flush();
                    this.x = null;
                }
                this.y = this.createImage(this.width, this.height);
                this.x = this.createImage(this.width, this.height);
                this.au = false;
            }
            if (this.ad) {
                this.ap = this.o + this.k - this.j;
            }
            else if (this.at) {
                if (!this.k) {
                    ++this.ap;
                }
                if (this.ap >= this.m) {
                    this.ap = 0;
                }
            }
            else {
                if (!this.k) {
                    --this.ap;
                }
                if (this.ap <= 0) {
                    this.ap = this.m;
                }
            }
            this.y.getGraphics().drawImage(this.x, 0, 0, this);
            this.e = false;
            this.repaint();
            this.c();
            synchronized (this.e) {
                while (!this.e) {
                    try {
                        this.e.wait(100L);
                    }
                    catch (InterruptedException ex) {
                        System.out.println("Error in synchronized wait(): " + ex);
                        System.exit(0);
                    }
                }
            }
            if (this.aK > 0) {
                try {
                    Thread.sleep(this.aK);
                }
                catch (InterruptedException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    private void c() {
        final Graphics graphics = this.x.getGraphics();
        graphics.setColor(this.t);
        graphics.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < this.b.size(); ++i) {
            final aL al = this.b.elementAt(i);
            if (al.a(this.ap, this.ap + this.width)) {
                graphics.drawImage(al.a(true), aL.b(al) - this.ap, 0, null);
            }
        }
    }
    
    public void a(final int n) {
        for (int i = 0; i < this.b.size(); ++i) {
            final aL al = this.b.elementAt(i);
            if (al.a(this.ap, this.ap + this.width) && aL.a(al) && aL.b(al) - this.ap < n && aL.b(al) + aL.a(al) - this.ap > n && n > 0) {
                aL.a(al);
            }
        }
    }
    
    private int b(final String s) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.f);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    static Vector a(final cv cv) {
        return cv.b;
    }
    
    static void a(final cv cv, final int m) {
        cv.m = m;
    }
    
    static int a(final cv cv) {
        return cv.m;
    }
    
    static Color a(final cv cv) {
        return cv.s;
    }
    
    static Font a(final cv cv) {
        return cv.f;
    }
    
    static Color b(final cv cv) {
        return cv.t;
    }
    
    static int a(final cv cv, final String s) {
        return cv.b(s);
    }
    
    public cv(final Applet a) {
        this.aJ = 12;
        this.af = "";
        this.ag = "";
        this.aK = 20;
        this.at = false;
        this.au = true;
        this.a = a;
        this.e = new Thread(this);
        this.y = null;
        this.x = null;
        this.b = new Vector();
        this.ap = 0;
        this.t = Color.black;
        this.s = Color.white;
        this.as = false;
        this.e = false;
        this.e = new Object();
        this.k = false;
        this.ad = false;
        this.debug = false;
    }
}
