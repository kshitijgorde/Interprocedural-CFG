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

public class aH extends Panel implements Runnable
{
    private int C;
    private int j;
    private int k;
    private boolean Z;
    private boolean r;
    Image k;
    private Object b;
    private boolean e;
    public boolean aa;
    public Font e;
    public Color j;
    public Color k;
    public int width;
    public int height;
    private int m;
    public int p;
    private Vector b;
    private Image l;
    private Thread d;
    public int am;
    public String J;
    public String K;
    public int an;
    public boolean ab;
    public boolean debug;
    public Applet a;
    public boolean ac;
    public as g;
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.Z) {
            this.a(n);
        }
        this.Z = false;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int j, final int n) {
        this.j = j;
        if (this.aa) {
            this.r = true;
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.j = 0;
        this.r = false;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int j, final int n) {
        this.j = j;
        return this.Z = true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.C = this.p;
        this.j = n;
        this.k = n;
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.setSize(this.width, this.height);
        graphics.drawImage(this.l, 0, 0, this);
        this.e = true;
        synchronized (this.b) {
            this.b.notifyAll();
        }
    }
    
    public void stop() {
        try {
            if (this.d != null) {
                this.d.stop();
            }
        }
        catch (Exception ex) {}
        this.d = null;
    }
    
    public void start() {
        if (this.d == null) {
            this.d = new Thread(this);
        }
        if (!this.d.isAlive()) {
            this.d.start();
        }
    }
    
    public static void a(final Vector vector) {
        vector.removeAllElements();
    }
    
    public void run() {
        this.e = new Font(this.J, 0, this.am);
        try {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        catch (Exception ex3) {}
        this.setBackground(this.k);
        while (true) {
            if (this.ac) {
                a(this.b);
                final bm bm = new bm(this, this.width, this.height, this.K);
                if (this.l != null) {
                    this.l.flush();
                    this.l = null;
                }
                if (this.k != null) {
                    this.k.flush();
                    this.k = null;
                }
                this.l = this.createImage(this.width, this.height);
                this.k = this.createImage(this.width, this.height);
                this.ac = false;
            }
            if (this.Z) {
                this.p = this.C + this.k - this.j;
            }
            else if (this.ab) {
                if (!this.r) {
                    ++this.p;
                }
                if (this.p >= this.m) {
                    this.p = 0;
                }
            }
            else {
                if (!this.r) {
                    --this.p;
                }
                if (this.p <= 0) {
                    this.p = this.m;
                }
            }
            this.l.getGraphics().drawImage(this.k, 0, 0, this);
            this.e = false;
            this.repaint();
            this.c();
            synchronized (this.b) {
                while (!this.e) {
                    try {
                        this.b.wait(100L);
                    }
                    catch (InterruptedException ex) {
                        System.out.println("Error in synchronized wait(): " + ex);
                        System.exit(0);
                    }
                }
            }
            if (this.an > 0) {
                try {
                    Thread.sleep(this.an);
                }
                catch (InterruptedException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    private void c() {
        final Graphics graphics = this.k.getGraphics();
        graphics.setColor(this.k);
        graphics.fillRect(0, 0, this.width, this.height);
        for (int i = 0; i < this.b.size(); ++i) {
            final bl bl = this.b.elementAt(i);
            if (bl.b(this.p, this.p + this.width)) {
                graphics.drawImage(bl.a(true), doook.bl.b(bl) - this.p, 0, null);
            }
        }
    }
    
    public void a(final int n) {
        for (int i = 0; i < this.b.size(); ++i) {
            final bl bl = this.b.elementAt(i);
            if (bl.b(this.p, this.p + this.width) && doook.bl.a(bl) && doook.bl.b(bl) - this.p < n && doook.bl.b(bl) + doook.bl.a(bl) - this.p > n && n > 0) {
                doook.bl.a(bl);
            }
        }
    }
    
    private int b(final String s) {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.e);
        return graphics.getFontMetrics().stringWidth(s);
    }
    
    static Vector a(final aH ah) {
        return ah.b;
    }
    
    static void a(final aH ah, final int m) {
        ah.m = m;
    }
    
    static int a(final aH ah) {
        return ah.m;
    }
    
    static Color a(final aH ah) {
        return ah.j;
    }
    
    static Font a(final aH ah) {
        return ah.e;
    }
    
    static Color b(final aH ah) {
        return ah.k;
    }
    
    static int a(final aH ah, final String s) {
        return ah.b(s);
    }
    
    public aH(final Applet a) {
        this.am = 12;
        this.J = "";
        this.K = "";
        this.an = 20;
        this.ab = false;
        this.ac = true;
        this.a = a;
        this.d = new Thread(this);
        this.l = null;
        this.k = null;
        this.b = new Vector();
        this.p = 0;
        this.k = Color.black;
        this.j = Color.white;
        this.aa = false;
        this.e = false;
        this.b = new Object();
        this.r = false;
        this.Z = false;
        this.debug = false;
    }
}
