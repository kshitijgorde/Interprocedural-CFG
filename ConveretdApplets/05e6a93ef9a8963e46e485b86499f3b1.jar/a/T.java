// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class T
{
    public co q;
    public int q;
    
    public T() {
    }
    
    public T(final co q) {
        this.q = q;
    }
    
    public T(final co co, final int q) {
        this(co);
        this.q = q;
    }
    
    public void q(final int q) {
        this.q = q;
    }
    
    public void q(final String s) {
        cs.q(s, this.q, this.q);
    }
    
    public void w(final String s) {
        this.q("/PublicWrite " + s);
    }
    
    public void w(final int n) {
        this.q("/ColorNick " + n);
    }
    
    public void e(final int n) {
        this.q("/BackColorNick " + n);
    }
    
    public void q() {
        this.q("/Ping");
    }
    
    public void w() {
        this.q("/AllIP");
    }
    
    public void e(final String s) {
        this.q("/KickBots " + s);
    }
    
    public void q(final String s, final boolean b) {
        final cp cp;
        (cp = new cp(66816, 1)).w = this.q;
        cp.q(0, 0, s);
        cp.q(0, 0, b);
        this.q.r(cp);
    }
    
    public void q(l l) {
        l.r = true;
        this.q.q.e();
        final cp cp = new cp(4198513, 1);
        cp.w = (l = l).a;
        this.q.r(cp);
    }
    
    public void q(final String s, final int n) {
        final cp cp;
        (cp = new cp(67339, 1)).q(0, 0, -999);
        cp.q(0, 0, s);
        cp.q(0, 1, n);
        cp.w = -1;
        cp.q = -1;
        this.q.r(cp);
    }
    
    public void w(final String s, final int n) {
        final cp cp;
        (cp = new cp(67339, 1)).q(0, 0, -999);
        cp.q(0, 0, s);
        cp.q(0, 0, true);
        cp.q(0, 1, n);
        cp.w = -1;
        cp.q = -1;
        this.q.r(cp);
    }
    
    public void r(final int w) {
        final as as;
        if ((as = (as)this.q.e.w(w)) == null) {
            return;
        }
        bp bp = null;
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.q.t.q; ++i) {
            if ((bp = (bp)this.q.t.q(i)).o.equals(as.o)) {
                b = true;
                break;
            }
        }
        final int n;
        if ((n = 1000 - (int)(System.currentTimeMillis() - currentTimeMillis)) > 0) {
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
        }
        if (!b) {
            final cp cp;
            (cp = new cp(67074, 1)).q(0, 0, this.q.a);
            cp.w = w;
            this.q.r(cp);
            return;
        }
        if (bp.s == null) {
            final bp bp2 = bp;
            final long n3;
            final long n2 = (n3 = (long)(Math.random() * 60.0 * 60.0 * 24.0 * 2.0)) / 43200L;
            final long n4 = (n3 - n2 * 12L * 60L * 60L) / 3600L;
            bp2.s = ((n2 > 0L) ? t.q(al.q("%1 days,"), "" + n2) : "") + ((n4 > 0L) ? t.q(al.q("%1 hours,"), "" + n4) : "") + " " + t.q(al.q("%1 minutes"), "" + (n3 - n2 * 12L * 60L * 60L - n4 * 60L * 60L) / 60L);
        }
        final cp cp2;
        (cp2 = new cp(67073, 1)).q(0, 0, w);
        cp2.q(0, 1, -999);
        cp2.q(0, 0, bp.o);
        cp2.q(0, 1, bp.s);
        cp2.q(0, 2, bp.a);
        cp2.q(0, 3, bp.u);
        this.q.y(cp2);
    }
}
