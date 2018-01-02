// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Observable;
import java.io.IOException;
import java.util.Observer;

public class Server implements Observer
{
    private static final String tt = "connection is closed";
    public static final String ut = "2.1.0";
    public static final String vt = "2.1.0p";
    public static final int ot = 1;
    public static final int pt = 2;
    public static final int qt = 3;
    private IClient wt;
    private by uc;
    private boolean hf;
    
    public Server(final IClient wt, final String s, final int n) throws IOException {
        this.wt = wt;
        (this.uc = new by(s, n)).addObserver(this);
        this.uc.yi(5);
        this.uc.ui(5);
        this.hf = true;
    }
    
    private void sh() throws IOException {
        if (!this.hf) {
            throw new IOException("connection is closed");
        }
    }
    
    public boolean zb() {
        return this.hf;
    }
    
    public void c() throws IOException {
        this.uc.deleteObserver(this);
        this.uc.c();
        this.hf = false;
    }
    
    public void g(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11) throws IOException {
        this.sh();
        this.uc.t(new Access(s, this.vh(s2), s3, s4, s5, s6, s7, s8, s9, s10, s11));
    }
    
    public void g(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final boolean b, final boolean b2, final boolean b3, final String s12, final String s13, final String s14, final boolean b4, final String s15) throws IOException {
        this.sh();
        this.uc.t(new ba(s, this.vh(s2), s3, s4, s5, s6, s7, s8, s9, s10, s11, b, b2, b3, s12, s13, s14, b4, s15));
    }
    
    public void j() throws IOException {
        this.sh();
        this.uc.t(new be());
    }
    
    public void f(final String s) throws IOException {
        this.sh();
        this.uc.t(new bg(s));
    }
    
    public void ph(final String[] array) throws IOException {
        this.sh();
        this.uc.t(new dj(array));
    }
    
    public void h(final String s, final String s2, final String s3) throws IOException {
        this.sh();
        this.uc.t(new au(s, s2, s3));
    }
    
    public void k(final String s, final String s2) throws IOException {
        this.sh();
        this.uc.t(new aw(s, s2));
    }
    
    public void d(final String s, final String s2, final String s3) throws IOException {
        this.sh();
        this.uc.t(new at(s, s2, s3));
    }
    
    public void i(final int n, final String s) throws IOException {
        this.sh();
        this.uc.t(new av(n, s));
    }
    
    public void a(final String s, final String s2, final String s3) throws IOException {
        this.sh();
        this.uc.t(new as(s, s2, s3));
    }
    
    public void a(final int n, final String s, final String s2) throws IOException {
        this.sh();
        this.uc.t(new as(n, s, s2));
    }
    
    public void a(final String s) throws IOException {
        this.sh();
        this.uc.t(new as(s));
    }
    
    public void b(final String s, final String s2, final String s3, final String s4) throws IOException {
        this.sh();
        this.uc.t(new dg(s, s2, s3, s4));
    }
    
    public void e(final String s, final String s2, final String s3) throws IOException {
        this.sh();
        this.uc.t(new ar(s, s2, s3));
    }
    
    public void ue(final String s, final String s2, final String s3, final int n) throws IOException {
        this.sh();
        this.uc.t(new ay(s, s2, s3, n));
    }
    
    public void update(final Observable observable, final Object o) {
        final by by = (by)observable;
        if (o instanceof ba) {
            final ba ba = (ba)o;
            this.wt.g(this.rh(ba.tb()), ba.kd(), ba.rb());
            return;
        }
        if (o instanceof Access) {
            final Access access = (Access)o;
            this.wt.g(this.rh(access.tb()), access.kd(), "");
            return;
        }
        if (o instanceof be) {
            this.wt.j(((be)o).kd());
            return;
        }
        if (o instanceof bg) {
            final bg bg = (bg)o;
            this.wt.f(this.th(bg.tb()), bg.nd(), bg.qb());
            return;
        }
        if (o instanceof au) {
            final au au = (au)o;
            switch (au.o()) {
                case 4: {
                    this.wt.h(this.uh(au.tb()), au.ld(), this.qh(au.wb()), au.sb(), au.qb());
                }
                case 2: {
                    this.wt.h(au.ld(), au.n(), au.rb(), au.l(), Boolean.valueOf(au.vb()), Boolean.valueOf(au.ub()));
                }
            }
        }
        else {
            if (o instanceof aw) {
                final aw aw = (aw)o;
                this.wt.k(aw.ld(), aw.n());
                return;
            }
            if (o instanceof at) {
                final at at = (at)o;
                if (at.o() == 2) {
                    this.wt.d(at.hc(), at.qc(), at.nc(), at.oc());
                    return;
                }
                this.wt.d(at.hc(), at.pc(), at.mc(), at.rc());
            }
            else {
                if (o instanceof av) {
                    final av av = (av)o;
                    this.wt.i(av.hc(), av.qc());
                    return;
                }
                if (o instanceof as) {
                    final as as = (as)o;
                    final int hc = as.hc();
                    final String ld = as.ld();
                    final String n = as.n();
                    final String lc = as.lc();
                    if (n.length() == 0) {
                        this.wt.a(lc);
                        return;
                    }
                    if (hc == 0) {
                        this.wt.a(ld, n, lc);
                        return;
                    }
                    this.wt.a(hc, n, lc);
                }
                else {
                    if (o instanceof dg) {
                        final dg dg = (dg)o;
                        this.wt.b(dg.ld(), dg.qc(), dg.lc());
                        return;
                    }
                    if (o instanceof ar) {
                        final ar ar = (ar)o;
                        this.wt.e(ar.ld(), ar.qc());
                        return;
                    }
                    if (o instanceof bb) {
                        final bb bb = (bb)o;
                        bb.mh();
                        try {
                            by.t(bb);
                            return;
                        }
                        catch (IOException ex) {
                            return;
                        }
                    }
                    if (o == null) {
                        by.deleteObserver(this);
                        this.hf = false;
                        this.wt.c();
                    }
                }
            }
        }
    }
    
    private String vh(final String s) {
        if (s.equals("2.1.0")) {
            return "2.1.0";
        }
        if (s.equals("2.1.0p")) {
            return "2.1.0p";
        }
        throw new IllegalArgumentException("unknown applet version " + s);
    }
    
    private int rh(final int n) {
        switch (n) {
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 4;
            }
            case 5: {
                return 5;
            }
            default: {
                throw new IllegalArgumentException("unknown access result " + n);
            }
        }
    }
    
    private int uh(final int n) {
        switch (n) {
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 4;
            }
            case 5: {
                return 5;
            }
            default: {
                throw new IllegalArgumentException("unknown enter room result " + n);
            }
        }
    }
    
    private int qh(final int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            default: {
                throw new IllegalArgumentException("unknown room type " + n);
            }
        }
    }
    
    private int th(final int n) {
        switch (n) {
            case 1: {
                return 1;
            }
            case 2: {
                return 5;
            }
            default: {
                throw new IllegalArgumentException("unknown user list result " + n);
            }
        }
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        this.c();
    }
}
