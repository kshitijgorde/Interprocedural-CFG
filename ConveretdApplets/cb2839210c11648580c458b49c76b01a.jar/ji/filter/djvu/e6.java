// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.djvu;

import ji.res.s;
import ji.v1event.af;
import ji.io.ac;
import ji.document.ad;
import ji.util.e;
import ji.io.h;
import ji.util.i;
import java.awt.Component;
import ji.util.d;
import ji.image.dx;
import ji.filter.fh;
import ji.ext.fo;
import ji.filter.cj;

public class e6 extends cj
{
    private static gy a;
    private static gx b;
    private fo c;
    
    public e6() {
        this.c = null;
    }
    
    public final String getFilterName() {
        return "jiFilterDJV";
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        try {
            if (!ji.util.d.m("DJVU Module", super.a)) {
                return null;
            }
            fh.b.a(0L);
            final byte[] array = new byte[4];
            fh.b.a(array);
            if (new String(array).toLowerCase().equals("at&t")) {
                if (e6.a == null) {
                    if (this.c == null) {
                        this.c = new fo(super.a);
                    }
                    e6.b = (gx)this.c.a("ji.filter.djvu.jiJniDjVuLoader", null, null, 760, "JNIDjVu.dll", "JNIDjVu.dll", fh.o, fh.g, super.a, true, null);
                    e6.a = (gy)this.c.a("ji.filter.djvu.jiFilterDJVLib", "jiFDJVl.class", "jiFDJV.class", 760, "jiFDJV.dll", "jiFDJV.dll", fh.o, fh.g, super.a, false, new Object[] { e6.b.getLibName(super.a) });
                    if (!e6.a.isLoaded()) {
                        e6.a = null;
                        if (i.c(5)) {
                            h.d(super.a, "Failed to load jiFDJV.dll");
                        }
                        return null;
                    }
                }
                final dx loadImageHeaderInternal = e6.a.loadImageHeaderInternal(fh);
                if (loadImageHeaderInternal != null) {
                    loadImageHeaderInternal.e = true;
                }
                return loadImageHeaderInternal;
            }
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void abort(final dx dx) {
        if (e.ai() && e6.a != null) {
            e6.a.abort();
        }
    }
    
    public final void clearAbort(final dx dx, final String s) {
        if (e6.a != null) {
            e6.a.clearAbort();
        }
    }
    
    public final boolean isAborted(final dx dx, final String s) {
        return e6.a != null && e6.a.isAborted();
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        if (e6.a != null) {
            e6.a.fillDibInternal(fh);
        }
    }
    
    public void close(final dx dx, final ad ad) {
        if (e6.a != null) {
            e6.a.close(dx);
        }
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        try {
            ac.a(0L);
            final byte[] array = new byte[4];
            ac.a(array);
            return new String(array).toLowerCase().equals("at&t") ? 1 : 0;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return 0;
        }
    }
    
    public final boolean b(final String s) {
        return e.v();
    }
    
    public final boolean d(final String s) {
        return i.c(12);
    }
    
    public String c(final String s) {
        return s.a(760, s);
    }
}
