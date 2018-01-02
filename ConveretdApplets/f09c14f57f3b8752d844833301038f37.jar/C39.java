import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Component;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class C39 implements Runnable
{
    Thread h;
    int i;
    Color j;
    Vector k;
    C49 l;
    boolean m;
    boolean n;
    
    public void a() {
        try {
            if (this.h != null && !this.n) {
                this.n = true;
                this.h.suspend();
            }
        }
        catch (Throwable t) {
            System.out.println("Error $343222 " + t);
        }
    }
    
    public void c() {
        try {
            if (this.h != null) {
                this.n = false;
                this.i = 0;
                this.h.resume();
            }
        }
        catch (Throwable t) {
            System.out.println("Error $2422223 " + t);
        }
    }
    
    public void run() {
        if (this.l.cf.P.version >= 1.3) {
            while (!this.m) {
                try {
                    Thread.sleep(1000L);
                    if (this.i >= 1) {
                        continue;
                    }
                    this.g();
                    this.i = 1;
                }
                catch (InterruptedException ex) {}
            }
        }
        else {
            while (!this.m) {
                try {
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex2) {}
                this.g();
            }
        }
    }
    
    public C39(final C49 l) {
        this.i = 1;
        this.m = false;
        this.n = false;
        this.l = l;
        this.k = new Vector();
    }
    
    public void g() {
        final Graphics graphics = this.l.getGraphics();
        if (graphics != null) {
            final C15 c15 = new C15(this.l, graphics, this.l.size().width, this.l.size().height);
            final Enumeration<C28> elements = (Enumeration<C28>)this.k.elements();
            while (elements.hasMoreElements()) {
                final C28 c16 = elements.nextElement();
                if (c16 instanceof C28) {
                    c15.B(this.j);
                    final boolean d = c16.d();
                    c16.e(true);
                    c15.n(true);
                    this.l.J(c15, c16);
                    c16.e(d);
                }
            }
            if (graphics != null) {
                graphics.dispose();
                graphics.finalize();
            }
        }
    }
}
