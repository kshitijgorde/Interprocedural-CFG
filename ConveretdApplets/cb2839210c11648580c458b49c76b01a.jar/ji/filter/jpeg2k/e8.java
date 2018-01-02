// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.jpeg2k;

import ji.res.s;
import ji.v1event.af;
import ji.document.ad;
import ji.filter.jpeg2k.tile.oy;
import ji.io.fb;
import ji.io.ac;
import ji.util.e;
import java.awt.Component;
import ji.util.cn;
import ji.res.aa;
import ji.io.h;
import ji.util.i;
import ji.util.d;
import ji.image.dx;
import ji.filter.fh;
import ji.filter.jpeg2k.tile.k5;
import ji.filter.cj;

public final class e8 extends cj
{
    private boolean a;
    private ox b;
    private k5 c;
    private static boolean d;
    private static boolean e;
    
    public final String getFilterName() {
        return "jiFilterJP2";
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (e8.d) {
            return null;
        }
        dx a = null;
        ji.util.d.b9(false);
        int n = 2;
        boolean b = false;
        if (i.c(37)) {
            h.d(super.a, "PRO: JP2 Filter...");
        }
        if (!a(fh.b, fh.u)) {
            if (i.c(37)) {
                h.d(super.a, "PRO: JP2 Not a valid JP2 file.");
            }
            return null;
        }
        while (n > 0 && !ji.util.d.er()) {
            try {
                --n;
                if (aa.b(fh.o, super.a)) {
                    if (i.c(37)) {
                        h.d(super.a, "PRO: Is installed locally...");
                    }
                    ji.util.d.b9(false);
                    cn.c(b, fh.o, super.a, fh.g);
                    e8.d = ji.util.d.er();
                    if (e8.d) {
                        n = 0;
                        return null;
                    }
                }
                else if (i.c(37)) {
                    h.d(super.a, "PRO: Is not installed locally.");
                }
                final String a2 = aa.a("jj2000.j2k.decoder.jiJpeg2000v2", cn.e(), "jj2000.j2k.decoder.jiJpeg2000Decoder", ji.util.e.u(), !e8.e, fh.o, super.a);
                if (a2 != null) {
                    h.d(super.a, a2);
                    e8.e = true;
                    return null;
                }
                final String a3 = aa.a("daeja1.png.jiPngv2", cn.c(), "daeja1.png.jiPngDecoder", ji.util.e.u(), !e8.e, fh.o, super.a);
                if (a3 != null) {
                    h.d(super.a, a3);
                    e8.e = true;
                    return null;
                }
                try {
                    this.b = new ox(ji.util.d.a2("jj2000.j2k.decoder.jiJpeg2000Decoder"));
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    h.d(super.a, "******** ".concat(String.valueOf(String.valueOf(noClassDefFoundError))));
                    b = true;
                    noClassDefFoundError.printStackTrace();
                    h.d(super.a, "******** No JPEG2000 class!...");
                    this.b = null;
                }
                catch (Exception ex) {
                    h.d(super.a, "******** ".concat(String.valueOf(String.valueOf(ex))));
                    h.d(super.a, "******** Failed to find JPEG2000 decoder");
                    b = true;
                    this.b = null;
                }
                if (this.b == null) {
                    continue;
                }
                a = this.b.a(new ac(fh.f, false, false, 0, fh.o, super.a), fh, (int)fh.b.v(), fh.o);
                if (a != null) {
                    n = 0;
                    continue;
                }
                continue;
            }
            catch (fb fb) {
                throw fb;
            }
            catch (NoClassDefFoundError noClassDefFoundError2) {
                b = true;
                noClassDefFoundError2.printStackTrace();
                h.d(super.a, "******** No JPEG2000 class!...");
                continue;
            }
            catch (Exception ex2) {
                if (aa.b(fh.o, super.a)) {
                    b = true;
                    h.d(super.a, "******** Rebuilding JPEG2000 decoder...");
                    continue;
                }
                n = 0;
                continue;
            }
            break;
        }
        return a;
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public void clearAbort(final dx dx, final String s) {
        this.a = false;
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        this.c = new oy(fh.o, "jiFilterJP2", fh.d.am);
        this.b.a(this.c, fh);
        if (this.a) {
            return;
        }
        switch (fh.d.am) {
            case 1: {
                fh.c.b(1, false, fh.o);
                break;
            }
            case 4: {
                fh.c.b(2, false, fh.o);
                break;
            }
            case 8: {
                fh.c.b(3, false, fh.o);
                break;
            }
            case 24: {
                fh.c.b(4, false, fh.o);
                break;
            }
            default: {
                fh.c.b(4, false, fh.o);
                break;
            }
        }
        this.c.a(fh.c, fh.d.m);
        this.c.c();
        this.c = null;
        fh.c.e(fh.o);
    }
    
    public void abort(final dx dx) {
        try {
            this.a = true;
            if (this.b != null) {
                this.b.a();
            }
            if (this.c != null) {
                this.c.b();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean isAborted(final dx dx, final String s) {
        return this.a;
    }
    
    public void close(final dx dx, final ad ad) {
        this.c();
    }
    
    private static boolean a(final ac ac, final String s) {
        boolean b = true;
        try {
            ac.a(0L);
            if (ac.p() != 12 || ac.p() != 1783636000 || ac.p() != 218793738) {
                ac.a(0L);
                if (ac.l() != -177) {
                    ac.a(0L);
                    b = false;
                }
            }
        }
        catch (Exception ex) {
            h.a(s, ex);
            b = false;
        }
        finally {
            try {
                ac.a(0L);
            }
            catch (Exception ex2) {
                h.a(s, ex2);
            }
        }
        return b;
    }
    
    public final void c() {
        if (this.b != null) {
            this.b.b();
        }
        super.a = null;
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        if (a(ac, null)) {
            return 1;
        }
        return 0;
    }
    
    public final boolean b(final String s) {
        return ji.util.d.ap();
    }
    
    public String c(final String s) {
        return s.a(947, s);
    }
    
    static {
        e8.d = false;
        e8.e = false;
    }
}
