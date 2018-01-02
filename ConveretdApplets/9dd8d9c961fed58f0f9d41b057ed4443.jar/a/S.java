// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class S
{
    public cq q;
    public int q;
    
    public S() {
    }
    
    public S(final cq q) {
        this.q = q;
    }
    
    public S(final cq cq, final int q) {
        this(cq);
        this.q = q;
    }
    
    public void q(final int q) {
        this.q = q;
    }
    
    public void q(final String s) {
        cu.q(s, this.q, this.q);
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
        final cr cr;
        (cr = new cr(66816, 1)).w = this.q;
        cr.q(0, 0, s);
        cr.q(0, 0, b);
        this.q.r(cr);
    }
    
    public void q(l l) {
        l.r = true;
        this.q.q.e();
        final cr cr = new cr(4198513, 1);
        cr.w = (l = l).s;
        this.q.r(cr);
    }
    
    public void q(final String s, final int n) {
        final cr cr;
        (cr = new cr(67339, 1)).q(0, 0, -999);
        cr.q(0, 0, s);
        cr.q(0, 1, n);
        cr.w = -1;
        cr.q = -1;
        this.q.r(cr);
    }
    
    public void w(final String s, final int n) {
        final cr cr;
        (cr = new cr(67339, 1)).q(0, 0, -999);
        cr.q(0, 0, s);
        cr.q(0, 0, true);
        cr.q(0, 1, n);
        cr.w = -1;
        cr.q = -1;
        this.q.r(cr);
    }
    
    public void r(final int w) {
        final ar ar;
        if ((ar = (ar)this.q.e.w(w)) == null) {
            return;
        }
        bq bq = null;
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.q.t.q; ++i) {
            if ((bq = (bq)this.q.t.q(i)).o.equals(ar.o)) {
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
            final cr cr;
            (cr = new cr(67074, 1)).q(0, 0, this.q.s);
            cr.w = w;
            this.q.r(cr);
            return;
        }
        if (bq.s == null) {
            final bq bq2 = bq;
            final long n3;
            final long n2 = (n3 = (long)(Math.random() * 60.0 * 60.0 * 24.0 * 2.0)) / 43200L;
            final long n4 = (n3 - n2 * 12L * 60L * 60L) / 3600L;
            bq2.s = ((n2 > 0L) ? s.q(ak.q("%1 days,"), "" + n2) : "") + ((n4 > 0L) ? s.q(ak.q("%1 hours,"), "" + n4) : "") + " " + s.q(ak.q("%1 minutes"), "" + (n3 - n2 * 12L * 60L * 60L - n4 * 60L * 60L) / 60L);
        }
        final cr cr2;
        (cr2 = new cr(67073, 1)).q(0, 0, w);
        cr2.q(0, 1, -999);
        cr2.q(0, 0, bq.o);
        cr2.q(0, 1, bq.s);
        cr2.q(0, 2, bq.a);
        cr2.q(0, 3, bq.u);
        this.q.y(cr2);
    }
}
