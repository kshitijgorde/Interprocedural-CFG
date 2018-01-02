// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.document.ad;
import ji.util.e;
import ji.util.c3;
import ji.v1event.a6;
import ji.io.ac;
import java.util.Hashtable;
import java.awt.image.ImageObserver;
import ji.util.d;
import java.awt.Frame;
import java.awt.Component;
import ji.v1event.af;
import ji.image.dx;
import java.awt.Image;

public class fx extends cj
{
    private Image a;
    private String b;
    
    public void a(final String b) {
        this.b = b;
    }
    
    public fx() {
        this.a = null;
        this.b = null;
    }
    
    public final String getFilterName() {
        return "jiFilterImport";
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        return null;
    }
    
    public final dx a(final Image a, final String s, final af af, final Component component) throws Exception {
        this.a = a;
        final dx dx = new dx();
        dx.ay = true;
        try {
            ji.util.d.a(new Frame(), a);
            dx.m = a.getWidth(null);
            dx.n = a.getHeight(null);
            dx.u = 1;
            dx.i = dx.m * dx.n * 4;
        }
        catch (Exception ex) {
            if (a != null) {
                a.flush();
            }
            return null;
        }
        if (dx.m <= 0 || dx.n <= 0) {
            this.a = null;
            return null;
        }
        (dx.bk = new Hashtable(5)).put("Width", "".concat(String.valueOf(String.valueOf(dx.m))));
        dx.bk.put("Height", "".concat(String.valueOf(String.valueOf(dx.n))));
        dx.z = 8;
        dx.aa = 3;
        dx.l = "JavaImage";
        dx.am = dx.z * dx.aa;
        dx.an = 0;
        dx.ar = false;
        dx.at = 0;
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
            fh.c.b(4, false, fh.o);
            try {
                int min = Math.min(ji.util.d.ar / 4 / fh.d.m, fh.d.n);
                if (min < 1) {
                    min = 1;
                }
                final int n = min * fh.d.m;
                int n2 = fh.d.n;
                int n3 = 0;
                final a6 a6 = new a6(this, 4, "");
                final int[] array = new int[n];
                while (n2 > 0 && !super.b) {
                    final c3 a7 = ji.util.d.a(this.a, this.b, fh.o);
                    ji.util.d.a(fh.o, this.b, this.a, a7, 0, n3, fh.d.m, min, fh.d.m, array);
                    ji.util.d.a(this.a, a7);
                    fh.c.a(array, min * fh.d.m, fh.o, n3, n3 + min - 1, true);
                    n3 += min;
                    n2 -= min;
                    if (n2 < min) {
                        min = n2;
                    }
                    if (fh.g != null) {
                        a6.a("".concat(String.valueOf(String.valueOf(100 * n3 / fh.d.n))));
                        fh.g.a(a6);
                    }
                }
                fh.c.e(fh.o);
            }
            finally {}
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
            }
        }
        catch (Exception ex) {}
    }
    
    public final void close(final dx dx, final ad ad) {
        if (this.a != null) {
            this.a.flush();
            this.a = null;
        }
    }
}
