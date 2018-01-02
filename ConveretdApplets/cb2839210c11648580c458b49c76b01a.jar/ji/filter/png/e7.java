// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.png;

import ji.res.s;
import java.io.EOFException;
import ji.v1event.af;
import ji.document.ad;
import ji.io.a5;
import ji.res.ay;
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
import ji.filter.cj;

public class e7 extends cj
{
    private ow a;
    private String b;
    private static boolean c;
    private static boolean d;
    
    public e7() {
        this.b = null;
    }
    
    public final String getFilterName() {
        return "jiFilterPng";
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (e7.c) {
            return null;
        }
        dx a = null;
        int n = 2;
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        final long r = fh.b.r();
        ji.util.d.b9(false);
        if (!a(fh.b)) {
            if (i.c(37)) {
                h.d(this.b, "PRO: file is not PNG.");
            }
        }
        else {
            while (n > 0 && !ji.util.d.er()) {
                try {
                    --n;
                    if (aa.b(fh.o, this.b)) {
                        ji.util.d.b9(false);
                        cn.a(b, fh.o, this.b, fh.g);
                        e7.c = ji.util.d.er();
                        if (e7.c) {
                            n = 0;
                            return null;
                        }
                    }
                    final String a2 = aa.a("daeja1.png.jiPngv2", cn.c(), "daeja1.png.jiPngDecoder", e.u(), !e7.d, fh.o, this.b);
                    if (a2 != null) {
                        h.d(this.b, a2);
                        e7.d = true;
                        n = 0;
                    }
                    else {
                        try {
                            this.a = new ow(ji.util.d.a2("daeja1.png.jiPngDecoder"));
                            b2 = true;
                        }
                        catch (NoClassDefFoundError noClassDefFoundError) {
                            b = true;
                            fh.b.a(r);
                            b3 = true;
                            noClassDefFoundError.printStackTrace();
                            h.d(this.b, "******** No PNG class!...");
                            this.a = null;
                        }
                        catch (Exception ex) {
                            h.d(this.b, "******** Failed to find png decoder");
                            this.a = null;
                        }
                    }
                    if (this.a != null) {
                        a = this.a.a(fh.o, fh.g, fh.b, ac.a(fh.f, this.b), this.b);
                        a.d = false;
                        n = 0;
                        if (b2 && b) {
                            h.d(this.b, "******** Successfully rebuilt PNG decoder.");
                        }
                    }
                }
                catch (NoClassDefFoundError noClassDefFoundError2) {
                    b = true;
                    fh.b.a(r);
                    b3 = true;
                    noClassDefFoundError2.printStackTrace();
                    h.d(this.b, "******** No PNG class!...");
                }
                catch (fb fb) {
                    throw fb;
                }
                catch (Exception ex2) {
                    if (aa.b(fh.o, this.b)) {
                        b = true;
                        fh.b.a(r);
                        h.d(this.b, "******** Rebuilding PNG decoder...");
                    }
                    else {
                        n = 0;
                    }
                }
                if (b3) {
                    this.a = null;
                    h.d(this.b, "PNG filter: Failed to find copy of png decoder! (daeja1.jar)");
                    ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(e.am()))).append("daeja1.jar").append("\n ----- ").append(ji.util.d.b(260, this.b)).append(" -----"))), fh.o, 60, null, fh.g, this.b);
                    if (this.a == null && n == 0) {
                        return null;
                    }
                    continue;
                }
            }
        }
        if (ji.util.d.er()) {
            a = null;
        }
        return a;
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return false;
    }
    
    public void clearAbort(final dx dx, final String s) {
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        if (this.a != null) {
            return this.a.a(ac, dx, s);
        }
        return null;
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        final a5 a5 = new a5(fh.b, fh.o);
        if (this.a != null) {
            this.a.a(fh, a5);
        }
    }
    
    private static boolean a(final ac ac) throws Exception {
        ac.a(0L);
        return ac.f() == 137 && ac.f() == 80 && ac.f() == 78 && ac.f() == 71 && ac.f() == 13 && ac.f() == 10 && ac.f() == 26 && ac.f() == 10;
    }
    
    public void close(final dx dx, final ad ad) {
        try {
            if (this.a != null) {
                this.a.a();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        try {
            return a(ac) ? 1 : 0;
        }
        catch (EOFException ex) {
            ji.util.d.a(ex);
            return 0;
        }
        catch (Exception ex2) {
            ji.util.d.a(ex2);
            return 2;
        }
    }
    
    public final boolean b(final String s) {
        return ji.util.d.aa();
    }
    
    public final boolean d(final String s) {
        return i.c(21);
    }
    
    public String c(final String s) {
        return s.a(768, s);
    }
    
    static {
        e7.c = false;
        e7.d = false;
    }
}
