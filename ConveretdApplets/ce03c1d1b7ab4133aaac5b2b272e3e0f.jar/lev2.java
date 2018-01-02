import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Random;
import java.net.URL;
import java.io.PrintStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class lev2 extends Applet implements Runnable
{
    static PrintStream a;
    static PrintStream e;
    int i;
    int l;
    i d;
    l g;
    c j;
    c k;
    URL h;
    boolean f;
    Random c;
    Thread b;
    
    public void init() {
        lev2.a.println("The following classes are copyright (c) 1999-2004 by Numedeon, Inc. : Phrase Scroller WrapTextBox");
    }
    
    public void start() {
        lev2.e.println("VERSION: 120");
        this.setLayout(null);
        this.h = this.getCodeBase();
        this.i = this.bounds().width;
        this.l = this.bounds().height;
        (this.d = new i(600, 400, false, this.getImage(this.h, "bg.gif"))).reshape(0, 100, 600, 400);
        this.add(this.d);
        (this.g = new l(600, 100)).reshape(0, 0, this.g.f, this.g.i);
        this.add(this.g);
        this.d.a(this.d.d = new j(this.i / 2, this.l / 2, 20.0));
        this.d.a(this.d.a = new k(30, this.d.s / 2, 10, this.d.s / 2));
        this.d.a(this.d.f = new e(this.d.p - 15, this.d.s / 2, 10, this.d.s / 2));
        int n;
        int n2;
        do {
            n = Math.abs(this.c.nextInt()) % (this.i / 2) + this.i / 4;
            n2 = Math.abs(this.c.nextInt()) % (this.l / 2) + this.l / 4;
        } while (this.d.b(n, n2));
        final c j = new c(n, n2, 1, this.getImage(this.h, "blue-plus.gif"), this.getImage(this.h, "f1.gif"));
        j.a(false);
        this.d.a(j);
        this.j = j;
        int n3;
        int n4;
        do {
            n3 = Math.abs(this.c.nextInt()) % (this.i / 2) + this.i / 4;
            n4 = Math.abs(this.c.nextInt()) % (this.l / 2) + this.l / 4;
        } while (this.d.b(n3, n4));
        final c k = new c(n3, n4, -1, this.getImage(this.h, "blue-minus.gif"), this.getImage(this.h, "f2.gif"));
        k.a(false);
        this.d.a(k);
        this.k = k;
        int n5;
        int n6;
        do {
            n5 = Math.abs(this.c.nextInt()) % (this.i / 2) + this.i / 4;
            n6 = Math.abs(this.c.nextInt()) % (this.l / 2) + this.l / 4;
        } while (this.d.b(n5, n6));
        final c c = new c(n5, n6, -1, this.getImage(this.h, "purple-minus.gif"), this.getImage(this.h, "f3.gif"));
        c.a(true);
        this.d.a(c);
        int n7;
        int n8;
        do {
            n7 = Math.abs(this.c.nextInt()) % (this.i / 2) + this.i / 4;
            n8 = Math.abs(this.c.nextInt()) % (this.l / 2) + this.l / 4;
        } while (this.d.b(n7, n8));
        final c c2 = new c(n7, n8, 1, this.getImage(this.h, "purple-plus.gif"), this.getImage(this.h, "f4.gif"));
        c2.a(true);
        this.d.a(c2);
        (this.b = new Thread(this)).start();
    }
    
    public void run() {
        if (!this.f) {
            this.f = true;
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex) {}
            this.g.j.a();
            this.g.j.a(true, "Welcome to Hotspots!", "", Color.black, true, true);
        }
        try {
            Thread.sleep(3000L);
        }
        catch (Exception ex2) {}
        this.g.j.a();
        this.g.j.a(true, "You have 10 seconds to arrange your purple players.  Click on a purple player to pick her up, then click again to drop her where you want her.", "", Color.black, true, true);
        final k a = this.d.a;
        final k a2 = this.d.a;
        a.a(k.k);
        final e f = this.d.f;
        final e f2 = this.d.f;
        f.a(e.k);
        this.d.o.removeAllElements();
        this.d.repaint();
        try {
            Thread.sleep(2000L);
        }
        catch (Exception ex3) {}
        this.g.a(10);
        final int n = Math.abs(this.c.nextInt()) % 10;
        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(1000L);
            }
            catch (Exception ex4) {}
            if (i == n) {
                final int d = this.d.d.d;
                final int j = this.d.d.j;
                final int n2 = d;
                final int p = this.d.p;
                final i d2 = this.d;
                final int n3 = (n2 + (p - 10)) / 2;
                final int n4 = (j + this.d.s / 2) / 2;
                final int d3 = n3 + (Math.abs(this.c.nextInt() % 100) - 50);
                final int k = n4 + (Math.abs(this.c.nextInt() % 100) - 50);
                if (!this.d.a(d3, k) && !this.d.b(d3, k)) {
                    this.k.d = d3;
                    this.k.j = k;
                }
                final int n5 = d * 3 / 4;
                final int n6 = this.d.s / 2;
                final int d4 = n5 + (Math.abs(this.c.nextInt() % 100) - 50);
                final int l = n6 + (Math.abs(this.c.nextInt() % 100) - 50);
                if (!this.d.a(d4, l) && !this.d.b(d4, l)) {
                    this.j.d = d4;
                    this.j.j = l;
                }
                this.d.repaint();
            }
        }
        this.d.a();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.d && o instanceof b) {
            final b b = (b)o;
            this.g.a.setText(new StringBuffer().append(this.d.i).toString());
            this.g.d.setText(new StringBuffer().append(this.d.n).toString());
            if (b.c != null) {
                this.g.j.a();
                this.g.j.a(true, b.c, "", Color.black, true, true);
                (this.b = new Thread(this)).start();
            }
        }
        return super.action(event, o);
    }
    
    private final void a() {
        this.f = false;
        this.c = new Random(System.currentTimeMillis());
    }
    
    public lev2() {
        this.a();
    }
    
    static {
        lev2.a = System.out;
        lev2.e = System.err;
    }
}
