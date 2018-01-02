// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class cj
{
    private bH q;
    public int q;
    
    private cj(final bH q) {
        this.q = q;
    }
    
    public cj(final bH bh, final int q) {
        this(bh);
        this.q = q;
    }
    
    public final void q(final String s) {
        a.q(s, this.q, this.q);
    }
    
    public final void w(final String s) {
        this.q("/PublicWrite " + s);
    }
    
    public final void q(final int n) {
        this.q("/ColorNick " + n);
    }
    
    public final void w(final int n) {
        this.q("/BackColorNick " + n);
    }
    
    public final void e(final String s) {
        this.q("/KickBots " + s);
    }
    
    public final void q(final String s, final boolean b) {
        final cJ cj;
        (cj = new cJ(66816, 1)).w = this.q;
        cj.q(0, 0, s);
        cj.q(0, 0, b);
        this.q.q(cj);
    }
    
    public final void q(final bp bp) {
        bp.r = true;
        this.q.q.e();
        final cJ cj;
        (cj = new cJ(4198513, 1)).w = bp.q();
        this.q.q(cj);
    }
    
    public final void q(final String s, final int n) {
        final cJ cj;
        (cj = new cJ(67339, 1)).q(0, 0, -999);
        cj.q(0, 0, s);
        cj.q(0, 1, n);
        cj.w = -1;
        cj.q = -1;
        this.q.q(cj);
    }
    
    public final void w(final String s, final int n) {
        final cJ cj;
        (cj = new cJ(67339, 1)).q(0, 0, -999);
        cj.q(0, 0, s);
        cj.q(0, 0, true);
        cj.q(0, 1, n);
        cj.w = -1;
        cj.q = -1;
        this.q.q(cj);
    }
    
    public final void e(final int w) {
        final bV bv;
        if ((bv = (bV)this.q.e.w(w)) == null) {
            return;
        }
        bq bq = null;
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.q.t.q(); ++i) {
            if ((bq = (bq)this.q.t.q(i)).getName().equals(bv.getName())) {
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
            final cJ cj;
            (cj = new cJ(67074, 1)).q(0, 0, this.q.q());
            cj.w = w;
            this.q.q(cj);
            return;
        }
        if (bq.s == null) {
            final bq bq2 = bq;
            final long n3;
            final long n2 = (n3 = (long)(Math.random() * 60.0 * 60.0 * 24.0 * 2.0)) / 43200L;
            final long n4 = (n3 - n2 * 12L * 60L * 60L) / 3600L;
            bq2.s = ((n2 > 0L) ? cv.q(cv.q("%1 days,"), "" + n2) : "") + ((n4 > 0L) ? cv.q(cv.q("%1 hours,"), "" + n4) : "") + " " + cv.q(cv.q("%1 minutes"), "" + (n3 - n2 * 12L * 60L * 60L - n4 * 60L * 60L) / 60L);
        }
        final cJ cj2;
        (cj2 = new cJ(67073, 1)).q(0, 0, w);
        cj2.q(0, 1, -999);
        cj2.q(0, 0, bq.getName());
        cj2.q(0, 1, bq.s);
        cj2.q(0, 2, bq.a);
        cj2.q(0, 3, bq.i);
        this.q.e(cj2);
    }
    
    public cj() {
    }
}
