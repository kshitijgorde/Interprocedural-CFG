import java.awt.RenderingHints;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImageOp;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class null
{
    private static final int _b = 1;
    private static final int ab = -1;
    private boolean ya;
    private String[] bb;
    private String[] cb;
    private String[] qa;
    private String[] ra;
    private String[] sa;
    private int db;
    private int eb;
    private int fb;
    private int gb;
    private int hb;
    private long ib;
    private ImageObserver xa;
    private int jb;
    private BufferedImage kb;
    private BufferedImage lb;
    private BufferedImage mb;
    private new nb;
    private boolean ob;
    private boolean pb;
    private boolean qb;
    private Thread rb;
    private boolean sb;
    private boolean tb;
    private String _;
    private int a;
    private static String T = "";
    private static String U = "\u572d";
    private static String V = "\u5754\u5770\u577c\u577a\u5778\u573d\u5775\u577c\u576e\u573d\u577f\u5778\u5778\u5773\u573d\u5779\u576f\u577c\u576a\u5778\u5779";
    private static String W = "\u5754\u5770\u577c\u577a\u5778\u573d\u5775\u577c\u576e\u573d\u5773\u5772\u5769\u573d\u5764\u5778\u5769\u573d\u577f\u5778\u5778\u5773\u573d\u577e\u576f\u5778\u577c\u5769\u5778\u5779\u573c";
    private static String ba = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5770\u5774\u5773\u5768\u5769\u5778\u5770\u577c\u576f\u5776\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String ca = "\u5731\u573d\u5764\u5727\u573d";
    private static String da = "\u5750\u5754\u5753\u5748\u5749\u5758\u5742\u5750\u575c\u574f\u5756";
    private static String ea = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5770\u5774\u5773\u5768\u5769\u5778\u5773\u5768\u5770\u577f\u5778\u576f\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String ta = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5779\u5778\u577a\u576f\u5778\u5778\u5770\u577c\u576f\u5776\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String ua = "\u5759\u5758\u575a\u574f\u5758\u5758\u5742\u5750\u575c\u574f\u5756";
    private static String Sa = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5779\u5778\u577a\u576f\u5778\u5778\u573d\u576b\u577c\u5771\u5768\u5778\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String Ta = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5779\u577c\u5764\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String Ua = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5770\u5772\u5773\u5769\u5775\u573d\u576b\u577c\u5771\u5768\u5778\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String Va = "\u5759\u576f\u577c\u576a\u5774\u5773\u577a\u573d\u5764\u5778\u577c\u576f\u573d\u576b\u577c\u5771\u5768\u5778\u573d\u5769\u5772\u573d\u5765\u5727\u573d";
    private static String Wa = "\u573d\u5730\u573d";
    
    public null(final boolean ya, final new nb, final int n, final int n2, final int n3, final int n4, final int n5, final ImageObserver xa) {
        this.bb = null;
        this.cb = null;
        this.qa = null;
        this.ra = null;
        this.sa = null;
        this.db = 0;
        this.eb = 0;
        this.fb = 0;
        this.gb = 0;
        this.hb = 0;
        this.ib = 0L;
        this.jb = 1;
        this.kb = null;
        this.lb = null;
        this.mb = null;
        this.ob = false;
        this.pb = false;
        this.qb = false;
        this.rb = null;
        this.sb = true;
        this.tb = true;
        this._ = null.T;
        this.a = -1;
        this.ya = ya;
        this.nb = nb;
        this.xa = xa;
        this.ib = n5 * 1000;
        this.rb = this._();
        this.a(n3, n4, n, n2);
        this.rb.start();
    }
    
    public void i(final String _) {
        if (!_.equals(this._)) {
            this.a();
            this.sb = true;
            this.qa = this.a(_.toCharArray());
            this._ = _;
        }
    }
    
    public void j(final String s) {
        if (this.sb) {
            this.a();
            this.ra = this.a(s.toCharArray());
        }
    }
    
    public void k(final String s) {
        if (this.sb) {
            this.a();
            this.sa = this.a(s.toCharArray());
        }
    }
    
    public void h(final int a) {
        if (a != this.a) {
            this.b();
            this.tb = true;
            if (a < 10) {
                this.cb = this.a(new String(null.U.concat(String.valueOf(String.valueOf(a)))).toCharArray());
            }
            else {
                this.cb = this.a(new String(null.T.concat(String.valueOf(String.valueOf(a)))).toCharArray());
            }
            this.a = a;
        }
    }
    
    public void b(final int n) {
        if (this.tb) {
            this.b();
            this.bb = this.a(new String(null.T.concat(String.valueOf(String.valueOf(n)))).toCharArray());
        }
    }
    
    public void _(final Graphics2D graphics2D) {
        if (this.mb != null) {
            graphics2D.drawImage(this.mb, null, this.fb, this.gb);
            this.b(null.V);
        }
        else {
            this.b(null.W);
        }
    }
    
    public synchronized void _() {
        this.m();
        this.qb = true;
        if (this.jb == 1 && this.tb) {
            this.ob = true;
            this.n();
            this.tb = false;
            this.ob = false;
        }
        else if (this.jb == 1 && !this.tb) {
            this.mb = this.kb;
        }
        else if (this.jb == -1 && this.sb) {
            this.pb = true;
            this.c();
            this.sb = false;
            this.pb = false;
        }
        else if (this.jb == -1 && !this.sb) {
            this.mb = this.lb;
        }
        this.qb = false;
    }
    
    private BufferedImage b(final int n, final int n2) {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(n, n2, 2);
    }
    
    private String[] a(final char[] array) {
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new String(null.T.concat(String.valueOf(String.valueOf(array[i]))));
        }
        return array2;
    }
    
    private void n() {
        final BufferedImage b = this.b(this.hb, this.nb.f());
        final Graphics2D graphics = b.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int eb = this.eb;
        for (int i = this.cb.length - 1; i > -1; --i) {
            if (i == this.cb.length - 1) {
                this.b(String.valueOf(String.valueOf(new StringBuffer(null.ba).append(eb).append(null.ca).append(0))));
                graphics.drawImage(this.nb.a(null.da), eb, 0, this.xa);
                eb -= this.nb.t();
            }
            final int n = eb - this.nb.u();
            this.b(String.valueOf(String.valueOf(new StringBuffer(null.ea).append(n).append(null.ca).append(0))));
            graphics.drawImage(this.nb.a(this.cb[i]), n, 0, this.xa);
            eb = n - this.nb.t();
        }
        int db = this.db;
        for (int j = this.bb.length - 1; j > -1; --j) {
            if (j == this.bb.length - 1) {
                this.b(String.valueOf(String.valueOf(new StringBuffer(null.ta).append(db).append(null.ca).append(0))));
                graphics.drawImage(this.nb.a(null.ua), db, 0, this.xa);
                db -= this.nb.t();
            }
            final int n2 = db - this.nb.u();
            this.b(String.valueOf(String.valueOf(new StringBuffer(null.Sa).append(n2).append(null.ca).append(0))));
            graphics.drawImage(this.nb.a(this.bb[j]), n2, 0, this.xa);
            db = n2 - this.nb.t();
        }
        this.kb = b;
        this.mb = b;
    }
    
    private void c() {
        final BufferedImage b = this.b(this.hb, this.nb.f());
        final Graphics2D graphics = b.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int n = 0;
        for (int i = 0; i < this.qa.length; ++i) {
            n += this.nb.u() + this.nb.t();
        }
        int n2 = n + (this.nb.u() - this.nb.t());
        for (int j = 0; j < this.ra.length; ++j) {
            n2 += this.nb.u() + this.nb.t();
        }
        int n3 = n2 + (this.nb.u() - this.nb.t());
        for (int k = 0; k < this.sa.length; ++k) {
            n3 += this.nb.u() + this.nb.t();
        }
        int n4 = this.hb / 2 - (n3 - this.nb.t()) / 2;
        for (int l = 0; l < this.qa.length; ++l) {
            this.b(String.valueOf(String.valueOf(new StringBuffer(null.Ta).append(n4).append(null.ca).append(0))));
            graphics.drawImage(this.nb.a(this.qa[l]), n4, 0, this.xa);
            n4 += this.nb.u() + this.nb.t();
        }
        int n5 = n4 + (this.nb.u() - this.nb.t());
        for (int n6 = 0; n6 < this.ra.length; ++n6) {
            this.b(String.valueOf(String.valueOf(new StringBuffer(null.Ua).append(n5).append(null.ca).append(0))));
            graphics.drawImage(this.nb.a(this.ra[n6]), n5, 0, this.xa);
            n5 += this.nb.u() + this.nb.t();
        }
        int n7 = n5 + (this.nb.u() - this.nb.t());
        for (int n8 = 0; n8 < this.sa.length; ++n8) {
            this.b(String.valueOf(String.valueOf(new StringBuffer(null.Va).append(n7).append(null.ca).append(0))));
            graphics.drawImage(this.nb.a(this.sa[n8]), n7, 0, this.xa);
            n7 += this.nb.u() + this.nb.t();
        }
        this.lb = b;
        this.mb = b;
    }
    
    private void b() {
        while (this.ob) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void a() {
        while (this.pb) {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void m() {
        while (true) {
            if (this.cb != null && this.bb != null && this.qa != null && this.ra != null) {
                if (this.sa != null) {
                    break;
                }
            }
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4) {
        this.hb = 9 * this.nb.u() + 4 * this.nb.t();
        final int n5 = n3 + n / 2;
        final int n6 = n4 + n2 / 2;
        this.fb = n5 - this.hb / 2;
        this.gb = n6 - this.nb.f() / 2;
        this.db = 3 * this.nb.u() + 3 * this.nb.t();
        this.eb = 7 * this.nb.u() + 6 * this.nb.t();
        final int n7 = 8 * this.nb.u() + 6 * this.nb.t();
        this.db += this.hb - n7;
        this.eb += this.hb - n7;
    }
    
    private Thread _() {
        return new Thread(new package(this));
    }
    
    private void b(final String s) {
        if (this.ya) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i()))).append(null.Wa).append(s))));
        }
    }
    
    private String i() {
        return this.getClass().getName();
    }
    
    static void _(final null null, final String s) {
        null.b(s);
    }
    
    static long _(final null null) {
        return null.ib;
    }
    
    static boolean b(final null null) {
        return null.qb;
    }
    
    static int _(final null null, final int n) {
        return null.jb *= n;
    }
    
    static int a(final null null) {
        return null.jb;
    }
    
    static {
        null.T = l(null.T);
        null.U = l(null.U);
        null.V = l(null.V);
        null.W = l(null.W);
        null.ba = l(null.ba);
        null.ca = l(null.ca);
        null.da = l(null.da);
        null.ea = l(null.ea);
        null.ta = l(null.ta);
        null.ua = l(null.ua);
        null.Sa = l(null.Sa);
        null.Ta = l(null.Ta);
        null.Ua = l(null.Ua);
        null.Va = l(null.Va);
        null.Wa = l(null.Wa);
    }
    
    private static String l(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x1571D);
        }
        return new String(array);
    }
}
