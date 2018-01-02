import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bq extends b5 implements Runnable, bo
{
    public static boolean kt;
    public static String ks;
    public Thread kr;
    public bl kq;
    public ab kp;
    public as f;
    public boolean ko;
    public boolean kn;
    
    public bq(final boolean b, final boolean b2, final as f) {
        super(f, f);
        (this.f = f).hk(super.c = this);
        f.e4(this);
        this.ko = b;
        this.kn = b;
        super.lq(new ab());
        (this.kp = (ab)super.km).e4(this);
        this.kp.e0(b2);
    }
    
    public bq(final bq bq) {
        this(true, bq.kp.e1(), new as(bq.f));
        super.lf = false;
        bq.kt = bq.kt;
        bq.ks = bq.ks;
    }
    
    public final void cp(final bl kq) {
        this.kq = kq;
    }
    
    public final as jr() {
        return this.f;
    }
    
    public final void cn() {
        if (this.kq != null) {
            this.kq.c3();
        }
    }
    
    public final void jq() {
        super.km.er("Copyright (c) 1998-2000 by Mindbright Technology AB, Stockholm, Sweden");
        if (bq.ks != null) {
            super.km.er(bq.ks);
        }
    }
    
    public final void jp() {
        if (!bq.kt) {
            return;
        }
        if (this.f.hc() != null) {
            super.km.er("MindTerm home: " + this.f.hc());
        }
        if (this.kp.e1()) {
            super.km.er("\tpress <ctrl> + 'D' to enter local command shell");
            if (this.jl()) {
                super.km.er("\t(...you might have to press ENTER also...)");
            }
        }
        if (this.kq != null && this.kq.kg) {
            super.km.er("\tpress <ctrl> + <mouse-" + this.kq.i2() + "> for main-menu");
        }
        super.km.er("");
    }
    
    public final boolean jo() {
        final boolean b = false;
        final int n = (int)((System.currentTimeMillis() - 965157940452L) / 86400000L);
        super.km.er("");
        super.km.er("This is a demo version of MindTerm, it is " + n + " days old.");
        super.km.er("Please go to http://www.mindbright.se/mindterm/");
        super.km.er("\tto check for new versions now and then");
        super.km.er("");
        return b;
    }
    
    public final void jn() {
        super.km.es("Initializing random generator, please wait...");
        ca.mg();
        super.km.er("done");
    }
    
    public final void jm(final String lo, final boolean b, final long n) throws IOException {
        final boolean b2 = this.f.f3() && this.jl();
        this.jn();
        super.km.er("");
        this.jp();
        super.lo = lo;
        super.ld(false);
        if (b2) {
            this.jj();
        }
        if (b) {
            super.lg(n);
        }
        else {
            super.le(n);
        }
        if (b2) {
            this.ji();
        }
    }
    
    public final void run() {
        this.jn();
        if (!this.jo()) {
            for (boolean j5 = true; j5; j5 = this.kp.f9.j5()) {
                boolean b = false;
                boolean b2 = false;
                try {
                    super.km.er("");
                    this.jp();
                    super.ld(true);
                    if (this.jl()) {
                        this.jj();
                    }
                    super.fq.le();
                    if (this.jl()) {
                        this.ji();
                    }
                    if (this.kp.e5()) {
                        this.kp.ep("\n\r\n\rServer died or connection lost");
                    }
                    Thread.sleep(1000L);
                    try {
                        this.f.g_();
                    }
                    catch (IOException ex4) {
                        this.h9("Error saving settings!");
                    }
                }
                catch (b4 b3) {
                    super.km.er("");
                    super.km.er(b3.getMessage());
                    this.f.g3();
                }
                catch (cl cl) {
                    super.km.er("");
                    super.km.er(cl.getMessage());
                    this.f.g3();
                }
                catch (aa aa) {
                    b = true;
                }
                catch (z z) {
                    b2 = true;
                    super.km.er("");
                    super.km.er(z.getMessage());
                }
                catch (UnknownHostException ex) {
                    final String message = ex.getMessage();
                    if (this.f.ce("proxytype").equals("none")) {
                        super.km.er("Unknown host: " + message);
                    }
                    else {
                        super.km.er("Unknown proxy host: " + message);
                    }
                    this.f.g2();
                }
                catch (FileNotFoundException ex2) {
                    super.km.er("File not found: " + ex2.getMessage());
                }
                catch (Exception ex3) {
                    String s = ex3.getMessage();
                    if (s == null || s.trim().length() == 0) {
                        s = ex3.toString();
                    }
                    super.km.er("");
                    super.km.er("Error connecting to " + this.f.ce("server") + ", reason:");
                    super.km.er("-> " + s);
                    if (ca.mn) {
                        System.out.println("If an error occured, please send the below stacktrace to mats@mindbright.se");
                        ex3.printStackTrace();
                    }
                }
                catch (ThreadDeath threadDeath) {
                    if (super.fq != null) {
                        super.fq.l3();
                    }
                    super.fq = null;
                    throw threadDeath;
                }
                this.f.g6();
                super.lf = true;
                this.f.hn = null;
                if (!this.f.hj || super.li) {
                    this.f.g3();
                }
                if (!b2) {
                    if (!this.f.hk) {
                        this.f.g3();
                        this.kn = false;
                    }
                    this.ko = false;
                }
                super.fq = null;
                final h jk = this.jk();
                if (jk != null) {
                    jk.cc(null);
                }
                if (b && this.kp.e1()) {}
            }
            return;
        }
    Label_0011_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(100000L);
                    }
                }
                catch (InterruptedException ex5) {
                    continue Label_0011_Outer;
                }
                continue;
            }
        }
    }
    
    public final boolean jl() {
        return super.km.ev() == null;
    }
    
    public final h jk() {
        final h ev = super.km.ev();
        if (ev != null && ev instanceof h) {
            return ev;
        }
        return null;
    }
    
    public final void jj() {
        (this.kr = new Thread(new bp(super.fq, this.kp))).start();
    }
    
    public final void ji() {
        this.kr.stop();
    }
    
    public final void ew() {
        this.kp.ew();
    }
    
    public final void je(final as as) {
        this.cn();
    }
    
    public final void jh(final b5 b5) {
    }
    
    public final void jg(final b5 b5) {
        this.ko = this.kn;
    }
    
    public final boolean jc() {
        return super.lo != null || this.ko;
    }
    
    public final boolean ja() {
        return bq.kt;
    }
    
    public final String ex(final String s, final String s2) throws IOException {
        return this.kp.ex(s, s2, false);
    }
    
    public final String jb(final String s) throws IOException {
        return this.kp.ex(s, "", true);
    }
    
    public final boolean jd(final String s, final boolean b) {
        boolean jd = false;
        try {
            jd = this.jd(s, true, b);
        }
        catch (IOException ex) {}
        return jd;
    }
    
    public final boolean jd(final String s, final boolean b, final boolean b2) throws IOException {
        boolean ig = false;
        if (this.kq != null && b) {
            ig = this.kq.ig(s, b2);
        }
        else {
            final String ex = this.ex(String.valueOf(s) + (b2 ? " ([yes]/no) " : "(yes/[no]) "), "");
            if (ex.equalsIgnoreCase("yes") || ex.equals("y")) {
                ig = true;
            }
            else if (ex.equals("")) {
                ig = b2;
            }
        }
        return ig;
    }
    
    public final void fb(final b5 b5) {
        this.cn();
        if (bq.kt) {
            super.km.er("Connected to server running " + super.lt);
            if (this.kp.e1()) {
                super.km.er("(command shell escape-sequence is '" + this.kp.f9.j2() + "')");
            }
            super.km.er("");
        }
    }
    
    public final void i9(final b5 b5) {
        this.cn();
        this.ew();
    }
    
    public final void i8(final b5 b5, final boolean b6) {
        this.kp.ez("Login aborted by user");
        this.cn();
        this.ew();
    }
    
    public final void jf(final String s) {
        super.km.er(s);
        super.km.er("");
    }
    
    public final void h9(final String s) {
        if (this.kq != null) {
            if (s.length() < 35) {
                this.kq.ij(s);
            }
            else {
                this.kq.if("MindTerm - Alert", s, 4, 38, true);
            }
        }
        else {
            this.jf(s);
        }
    }
    
    static {
        bq.kt = true;
        bq.ks = null;
    }
}
