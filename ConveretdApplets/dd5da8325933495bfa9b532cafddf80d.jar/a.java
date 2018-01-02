import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Applet implements Runnable
{
    public d a;
    private int b;
    public boolean c;
    public String d;
    
    a() {
        this.a = null;
        this.b = 0;
        this.c = false;
        this.d = null;
    }
    
    public void run() {
    }
    
    public boolean a() {
        return this.a != null && this.a.bn;
    }
    
    public void a(final boolean b) {
        if (this.a != null) {
            this.a.e(b);
        }
    }
    
    public void b(final boolean b) {
        if (this.a != null) {
            this.a.f(b);
        }
    }
    
    public int b() {
        if (this.a != null) {
            return this.a.bC();
        }
        return 0;
    }
    
    public void a(final int n) {
        if (this.a != null) {
            this.a.T(n);
        }
    }
    
    public void c() {
    }
    
    public synchronized void a(final d d, final Graphics graphics, final float n) {
    }
    
    public boolean a(final String s) {
        return false;
    }
    
    public void c(final boolean b) {
    }
    
    public void b(final int b) {
        this.b = b;
    }
    
    public boolean d() {
        return this.b == 0;
    }
}
