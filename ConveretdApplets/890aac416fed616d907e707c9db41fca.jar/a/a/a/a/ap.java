// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class ap extends bo
{
    public int s;
    public int r;
    public int n;
    public int m;
    public int l;
    public int p;
    public boolean j;
    int[] x;
    public boolean v;
    public boolean i;
    public int k;
    public boolean o;
    boolean t;
    boolean u;
    public Image w;
    public Image g;
    boolean q;
    protected static char[] h;
    
    static {
        ap.h = new char[] { 'i', 'm', 'a', 'g', 'e', '\0' };
    }
    
    ap() {
        this.s = 0;
        this.r = 0;
        this.n = 0;
        this.m = 0;
        this.l = 0;
        this.p = 0;
        this.j = false;
        this.x = null;
        this.v = false;
        this.i = false;
        this.k = 0;
        this.o = false;
        this.t = true;
        this.u = true;
        this.w = null;
        this.g = null;
        this.q = false;
        super.case = ap.h;
    }
    
    public void a(final bh bh, final an an, final aq aq) {
    }
    
    protected void do() {
        float n = this.s;
        while (n > 1.0f) {
            n /= 2.0f;
            if (n != (int)n) {
                this.k = 0;
                return;
            }
            ++this.k;
        }
    }
    
    void if(final long n) {
        super.goto = true;
    }
    
    public long a(final int n, final int n2) {
        if (n < 0 || n2 < 0 || n >= this.s || n2 >= this.r) {
            return 0L;
        }
        return this.x[n + n2 * this.s];
    }
    
    public boolean a(final long n) {
        if (super.goto && this.w != null) {
            try {
                this.g.getGraphics().drawImage(this.w, 0, 0, null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final PixelGrabber pixelGrabber = new PixelGrabber(this.g, 0, 0, this.s, this.r, this.x, 0, this.s);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return true;
        }
        return false;
    }
    
    public void for() {
    }
    
    public void a(final boolean t) {
        this.t = t;
    }
    
    public void do(final boolean u) {
        this.u = u;
    }
    
    public void if() {
        if (super.long != null) {
            super.long.a();
        }
        super.long = null;
        this.x = null;
    }
    
    public void if(final boolean b) {
    }
    
    public ap a(final ap ap, final aq aq) {
        return null;
    }
    
    public void int() {
    }
}
