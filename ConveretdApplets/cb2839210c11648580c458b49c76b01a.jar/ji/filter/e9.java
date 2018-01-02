// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.v1event.af;
import ji.image.ds;
import ji.document.ad;
import ji.util.e;
import ji.v1event.a6;
import ji.util.d;
import ji.io.q;
import ji.io.ac;
import java.util.Hashtable;
import ji.image.dx;
import ji.io.hp;
import ji.decode.gc;

public class e9 extends cj
{
    private gc a;
    private boolean b;
    private hp c;
    private String d;
    
    public final String getFilterName() {
        return "jiFilterFilenet";
    }
    
    public void a(final String d) {
        this.d = d;
    }
    
    public e9() {
        this.a = null;
        this.b = true;
        this.c = null;
        this.d = null;
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        this.b = true;
        this.c = new hp(fh.b);
        try {
            return a(this.c);
        }
        finally {
            if (this.c != null) {
                this.c.a();
                this.c = null;
            }
        }
    }
    
    private static final dx a(final hp hp) throws Exception {
        final dx dx = new dx();
        dx.l = "Filenet";
        final oz oz = new oz();
        hp.a(0L);
        oz.a = hp.c();
        oz.b = hp.c();
        oz.d = hp.d();
        dx.m = hp.d();
        dx.n = hp.d();
        dx.u = 1;
        dx.i = hp.g();
        if (dx.m <= 50 || dx.m > 20000 || dx.n <= 0 || oz.d <= 0 || oz.d > 500) {
            return null;
        }
        if (oz.b == 2) {
            return null;
        }
        hp.a((long)oz.c);
        oz.e = new o0[oz.d];
        for (int i = 0; i < oz.d; ++i) {
            oz.e[i] = new o0();
            oz.e[i].c = hp.c();
            oz.e[i].b = hp.c();
            oz.e[i].a = hp.d();
        }
        (dx.bk = new Hashtable(5)).put("Format", "".concat(String.valueOf(String.valueOf(oz.a))));
        dx.bk.put("Config", "".concat(String.valueOf(String.valueOf(oz.b))));
        dx.bk.put("NumBands", "".concat(String.valueOf(String.valueOf(oz.d))));
        dx.bk.put("Width", "".concat(String.valueOf(String.valueOf(dx.m))));
        dx.bk.put("Height", "".concat(String.valueOf(String.valueOf(dx.n))));
        dx.an = 0;
        dx.ax = oz.a();
        dx.ar = true;
        dx.at = 0;
        switch (oz.b & 0x70) {
            case 64: {
                dx.ac = 100.0;
                dx.ad = 100.0;
                break;
            }
            case 0: {
                dx.ac = 200.0;
                dx.ad = 200.0;
                break;
            }
            case 16: {
                dx.ac = 200.0;
                dx.ad = 200.0;
                break;
            }
            case 32: {
                dx.ac = 300.0;
                dx.ad = 300.0;
                break;
            }
            case 48: {
                dx.ac = 400.0;
                dx.ad = 400.0;
                break;
            }
        }
        return dx;
    }
    
    public final boolean e() {
        return false;
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        super.c = true;
        try {
            super.b = false;
            final q a = q.a(fh.o, this.d);
            if (!this.b) {
                throw new Exception(ji.util.d.b(335, this.d));
            }
            int n = 0;
            byte[] array = null;
            final a6 a2 = new a6(this, 4, "");
            this.c = new hp(fh.b, fh.d.an);
            super.b = false;
            fh.c.b(1, true, fh.o);
            final oz oz = (oz)fh.d.ax;
            try {
                for (int n2 = 0; n2 < oz.d && !super.b; ++n2) {
                    int n3 = oz.c + oz.d * 4;
                    for (int i = 0; i < n2; ++i) {
                        n3 += oz.e[i].a;
                    }
                    switch (oz.e[n2].c) {
                        case 1: {
                            if (array == null) {
                                array = new byte[oz.e[n2].a];
                            }
                            else if (array.length < oz.e[n2].a) {
                                array = new byte[oz.e[n2].a];
                            }
                            this.c.a((long)n3);
                            this.c.a(array, 0, oz.e[n2].a);
                            this.a(array, fh.d.m, oz.e[n2].b, n, fh.c);
                            n += oz.e[n2].b;
                            if (fh.g != null) {
                                a2.a("".concat(String.valueOf(String.valueOf(100 * n / fh.d.n))));
                                fh.g.a(a2);
                                break;
                            }
                            break;
                        }
                        case 2:
                        case 4:
                        case 5:
                        case 6: {
                            if (this.a == null) {
                                (this.a = new gc(this.d)).a(fh.g, fh.d, fh.c, this.c, a, fh.o);
                            }
                            n += this.a.a(oz.e[n2].b, n3, oz.e[n2].a, n);
                        }
                    }
                }
            }
            finally {
                if (this.a != null) {
                    this.a.b();
                }
                this.a = null;
                try {
                    this.c.a();
                    this.c = null;
                }
                catch (Exception ex) {}
            }
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (e.ai()) {
                super.b = true;
                if (this.a != null) {
                    this.a.a();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void close(final dx dx, final ad ad) {
        this.b = false;
    }
    
    public final void a(final byte[] array, final int n, final int n2, final int n3, final ds ds) throws Exception {
        final byte[] array2 = new byte[1 + n / 8];
        final short[] array3 = new short[n];
        ds.am();
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n / 8; ++j) {
                array2[j] = array[j + i * n / 8];
            }
            ds.a(array3, ji.util.d.a(0, n, array2, 0, array3));
        }
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        hp hp = null;
        try {
            hp = new hp(ac);
            hp.a(0L);
            return (a(hp) != null) ? 1 : 0;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return 0;
        }
        finally {
            if (hp != null) {
                hp.a();
            }
        }
    }
    
    public String c(final String s) {
        return "FileNet";
    }
}
