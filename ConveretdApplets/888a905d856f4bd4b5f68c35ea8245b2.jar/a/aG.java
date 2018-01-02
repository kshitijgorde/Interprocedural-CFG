// 
// Decompiled by Procyon v0.5.30
// 

package a;

public class aG
{
    public dH q;
    public int q;
    
    public aG() {
    }
    
    public aG(final dH q) {
        this.q = q;
    }
    
    public aG(final dH dh, final int q) {
        this(dh);
        this.q = q;
    }
    
    public void q(final int q) {
        this.q = q;
    }
    
    public void q(final String s) {
        dN.q(s, this.q, this.q);
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
    
    public void q(final String s, final String s2) {
        this.q("/ChangeRoom " + s + ((s2 != null) ? (" " + s2) : ""));
    }
    
    public void e(final String s) {
        this.q("/cn " + s);
    }
    
    public void r(final String s) {
        this.q("/Warn " + s);
    }
    
    public void q() {
        this.q("/Ping");
    }
    
    public void w() {
        this.q("/AllIP");
    }
    
    public void t(final String s) {
        this.q("/KickBots " + s);
    }
    
    public void e() {
        this.q("/MustKick");
    }
    
    public void q(final String s, final boolean b) {
        final dI di;
        (di = new dI(66816, 1)).w = this.q;
        di.q(0, 0, s);
        di.q(0, 0, b);
        this.q.o(di);
    }
    
    public void q(p p) {
        p.r = true;
        this.q.q.e();
        final dI di = new dI(4198513, 1);
        di.w = (p = p).s;
        this.q.o(di);
    }
    
    public void q(final String s, final int n) {
        final dI di;
        (di = new dI(67339, 1)).q(0, 0, -999);
        di.q(0, 0, s);
        di.q(0, 1, n);
        di.w = -1;
        di.q = -1;
        this.q.o(di);
    }
    
    public void w(final String s, final int n) {
        final dI di;
        (di = new dI(67339, 1)).q(0, 0, -999);
        di.q(0, 0, s);
        di.q(0, 0, true);
        di.q(0, 1, n);
        di.w = -1;
        di.q = -1;
        this.q.o(di);
    }
    
    public void r() {
        this.q("/MustBan");
    }
    
    public void t() {
        this.q("/Remove");
    }
    
    public void y() {
        this.q("/GiveKick");
    }
    
    public void r(final int n) {
        this.q("/ChangeStar " + n);
    }
    
    public void t(final int n) {
        this.q("/ChangeIcon " + n);
    }
    
    public void y(final int w) {
        final aO ao;
        if ((ao = (aO)this.q.a.w(w)) == null) {
            return;
        }
        cr cr = null;
        boolean b = false;
        final long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < this.q.d.q; ++i) {
            if ((cr = (cr)this.q.d.q(i)).a.equals(ao.a)) {
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
            final dI di;
            (di = new dI(67074, 1)).q(0, 0, this.q.s);
            di.w = w;
            this.q.o(di);
            return;
        }
        if (cr.f == null) {
            final cr cr2 = cr;
            final long n3;
            final long n2 = (n3 = (long)(Math.random() * 60.0 * 60.0 * 24.0 * 2.0)) / 43200L;
            final long n4 = (n3 - n2 * 12L * 60L * 60L) / 3600L;
            cr2.f = ((n2 > 0L) ? B.q(be.w("%1 days,"), "" + n2) : "") + ((n4 > 0L) ? B.q(be.w("%1 hours,"), "" + n4) : "") + " " + B.q(be.w("%1 minutes"), "" + (n3 - n2 * 12L * 60L * 60L - n4 * 60L * 60L) / 60L);
        }
        final dI di2;
        (di2 = new dI(67073, 1)).q(0, 0, w);
        di2.q(0, 1, -999);
        di2.q(0, 0, cr.a);
        di2.q(0, 1, cr.f);
        di2.q(0, 2, cr.d);
        di2.q(0, 3, cr.i);
        this.q.s(di2);
    }
}
