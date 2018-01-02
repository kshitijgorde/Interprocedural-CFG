// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class dP
{
    public cU q;
    public int q;
    
    public dP(final cU q) {
        this.q = q;
    }
    
    public dP(final cU cu, final int q) {
        this(cu);
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
    
    public final void q(final String s, final String s2) {
        this.q("/ChangeRoom " + s + ((s2 != null) ? (" " + s2) : ""));
    }
    
    public final void e(final String s) {
        this.q("/KickBots " + s);
    }
    
    public final void q(final String s, final boolean b) {
        final es es;
        (es = new es(66816, 1)).w = this.q;
        es.q(0, 0, s);
        es.q(0, 0, b);
        this.q.q(es);
    }
    
    public final void q(final cz cz) {
        cz.t = true;
        this.q.q.e();
        final es es;
        (es = new es(4198513, 1)).w = cz.q();
        this.q.q(es);
    }
    
    public final void q(final String s, final int n) {
        final es es;
        (es = new es(67339, 1)).q(0, 0, -999);
        es.q(0, 0, s);
        es.q(0, 1, n);
        es.w = -1;
        es.q = -1;
        this.q.q(es);
    }
    
    public final void w(final String s, final int n) {
        final es es;
        (es = new es(67339, 1)).q(0, 0, -999);
        es.q(0, 0, s);
        es.q(0, 0, true);
        es.q(0, 1, n);
        es.w = -1;
        es.q = -1;
        this.q.q(es);
    }
    
    public final void e(final int w) {
        final dj dj;
        if ((dj = (dj)this.q.e.w(w)) == null) {
            return;
        }
        cA ca = null;
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.q.t.q(); ++i) {
            if ((ca = (cA)this.q.t.q(i)).getName().equals(dj.getName())) {
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
            final es es;
            (es = new es(67074, 1)).q(0, 0, this.q.q());
            es.w = w;
            this.q.q(es);
            return;
        }
        if (ca.f == null) {
            final cA ca2 = ca;
            final long n3;
            final long n2 = (n3 = (long)(Math.random() * 60.0 * 60.0 * 24.0 * 2.0)) / 43200L;
            final long n4 = (n3 - n2 * 12L * 60L * 60L) / 3600L;
            ca2.f = ((n2 > 0L) ? ec.q(eb.q("%1 days,"), "" + n2) : "") + ((n4 > 0L) ? ec.q(eb.q("%1 hours,"), "" + n4) : "") + " " + ec.q(eb.q("%1 minutes"), "" + (n3 - n2 * 12L * 60L * 60L - n4 * 60L * 60L) / 60L);
        }
        final es es2;
        (es2 = new es(67073, 1)).q(0, 0, w);
        es2.q(0, 1, -999);
        es2.q(0, 0, ca.getName());
        es2.q(0, 1, ca.f);
        es2.q(0, 2, ca.d);
        es2.q(0, 3, ca.o);
        this.q.e(es2);
    }
    
    public dP() {
    }
}
