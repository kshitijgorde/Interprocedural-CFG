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

public class aE extends Panel implements Runnable
{
    private int q;
    private int r;
    private int s;
    private boolean r;
    private boolean s;
    Image b;
    private Object b;
    private boolean t;
    public boolean j;
    public Font d;
    public Color d;
    public Color e;
    public int d;
    public int g;
    private int y;
    public int h;
    private Vector g;
    private Image o;
    private Thread c;
    public int i;
    public String e;
    public String f;
    public int k;
    public boolean k;
    public boolean l;
    public Applet a;
    public boolean p;
    public be i;
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.r) {
            this.b(n);
        }
        this.r = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int r, final int n) {
        this.r = r;
        if (this.j) {
            this.s = true;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.r = 0;
        this.s = false;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int r, final int n) {
        this.r = r;
        return this.r = true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.q = this.h;
        this.r = n;
        this.s = n;
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.setSize(this.d, this.g);
        graphics.drawImage(this.o, 0, 0, this);
        this.t = true;
        synchronized (this.b) {
            this.b.notifyAll();
        }
    }
    
    public void c() {
        try {
            if (this.c != null) {
                this.c.stop();
            }
        }
        catch (Exception ex) {}
        this.c = null;
    }
    
    public void a() {
        if (this.c == null) {
            this.c = new Thread(this);
        }
        if (!this.c.isAlive()) {
            this.c.start();
        }
    }
    
    public static void a(final Vector vector) {
        vector.removeAllElements();
    }
    
    public void run() {
        this.d = new Font(this.e, 0, this.i);
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Exception ex3) {}
        this.setBackground(this.e);
        while (true) {
            if (this.p) {
                a(this.g);
                final bv bv = new bv(this, this.d, this.g, this.f);
                if (this.o != null) {
                    this.o.flush();
                    this.o = null;
                }
                if (this.b != null) {
                    this.b.flush();
                    this.b = null;
                }
                this.o = this.createImage(this.d, this.g);
                this.b = this.createImage(this.d, this.g);
                this.p = false;
            }
            if (this.r) {
                this.h = this.q + this.s - this.r;
            }
            else if (this.k) {
                if (!this.s) {
                    ++this.h;
                }
                if (this.h >= this.y) {
                    this.h = 0;
                }
            }
            else {
                if (!this.s) {
                    --this.h;
                }
                if (this.h <= 0) {
                    this.h = this.y;
                }
            }
            this.o.getGraphics().drawImage(this.b, 0, 0, this);
            this.t = false;
            this.repaint();
            this.b();
            synchronized (this.b) {
                while (!this.t) {
                    try {
                        this.b.wait(100L);
                    }
                    catch (InterruptedException ex) {
                        System.out.println("Error in synchronized wait(): " + ex);
                        System.exit(0);
                    }
                }
            }
            if (this.k > 0) {
                try {
                    Thread.sleep(this.k);
                }
                catch (InterruptedException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    private void b() {
        final Graphics graphics = this.b.getGraphics();
        graphics.setColor(this.e);
        graphics.fillRect(0, 0, this.d, this.g);
        for (int i = 0; i < this.g.size(); ++i) {
            final aQ aq = this.g.elementAt(i);
            if (aq.b(this.h, this.h + this.d)) {
                graphics.drawImage(aq.a(true), aQ.b(aq) - this.h, 0, null);
            }
        }
    }
    
    public void b(final int n) {
        for (int i = 0; i < this.g.size(); ++i) {
            final aQ aq = this.g.elementAt(i);
            if (aq.b(this.h, this.h + this.d) && aQ.a(aq) && aQ.b(aq) - this.h < n && aQ.b(aq) + aQ.a(aq) - this.h > n && n > 0) {
                aQ.a(aq);
            }
        }
    }
    
    private int a(final String s) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.d);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    static Vector a(final aE ae) {
        return ae.g;
    }
    
    static void a(final aE ae, final int y) {
        ae.y = y;
    }
    
    static int a(final aE ae) {
        return ae.y;
    }
    
    static Color a(final aE ae) {
        return ae.d;
    }
    
    static Font a(final aE ae) {
        return ae.d;
    }
    
    static Color b(final aE ae) {
        return ae.e;
    }
    
    static int a(final aE ae, final String s) {
        return ae.a(s);
    }
    
    public aE(final Applet a) {
        this.i = 12;
        this.e = "";
        this.f = "";
        this.k = 20;
        this.k = false;
        this.p = true;
        this.a = a;
        this.c = new Thread(this);
        this.o = null;
        this.b = null;
        this.g = new Vector();
        this.h = 0;
        this.e = Color.black;
        this.d = Color.white;
        this.j = false;
        this.t = false;
        this.b = new Object();
        this.s = false;
        this.r = false;
        this.l = false;
    }
}
