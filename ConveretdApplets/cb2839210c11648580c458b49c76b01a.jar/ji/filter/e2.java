// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.v1event.af;
import ji.document.ad;
import ji.util.e;
import ji.io.ac;
import ji.jpeg.os;
import java.io.EOFException;
import java.awt.Component;
import ji.decode.ou;
import ji.util.i;
import java.util.Hashtable;
import ji.io.h;
import ji.util.d;
import ji.image.dx;
import ji.jpeg.or;

public class e2 extends cj
{
    private String a;
    Exception b;
    private String[] c;
    or d;
    boolean e;
    boolean f;
    
    public final String getFilterName() {
        return "jiFilterJPGFast";
    }
    
    public e2() {
        this.c = new String[] { "doc", "htm", "html", "image", "jp2", "djvu", "png", "bmp", "pix", "db", "zip", "xls", "txt", "text", "pdf" };
        this.e = false;
        this.f = false;
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public void b(final boolean f) {
        this.f = f;
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        try {
            if (ji.util.d.dv()) {
                h.e(this.a, "Analysing FAST-JPEG...");
            }
            final String m = fh.m;
            String s = ji.util.d.c(fh.f, m, fh.n);
            if (s != null) {
                s = s.toLowerCase();
                for (int i = 0; i < this.c.length; ++i) {
                    if (s.equals(this.c[i].toLowerCase())) {
                        return null;
                    }
                }
            }
            if (m != null) {
                final String lowerCase = m.toLowerCase();
                for (int j = 0; j < this.c.length; ++j) {
                    if (lowerCase.equals(this.c[j].toLowerCase())) {
                        return null;
                    }
                }
            }
            try {
                if (this.isFileType(fh.b, fh.n, s, fh.o, false, fh.e, fh.f, fh.g, false) == 0) {
                    return null;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.d = new or(fh.b, this.e);
            final dx dx = new dx();
            dx.bk = new Hashtable(5);
            dx.f = fh.e;
            dx.h = fh.f;
            dx.u = 1;
            dx.v = 1;
            dx.i = fh.b.v();
            dx.ay = false;
            dx.an = 0;
            dx.ar = false;
            dx.at = 0;
            dx.a = 2;
            fh.b.a(0L);
            try {
                fh.l = 0;
                return this.d.a(fh.b, dx, fh);
            }
            catch (Exception b) {
                if (i.c(5)) {
                    b.printStackTrace();
                }
                this.b = b;
                fh.b.a(0L);
                return ou.a(fh.b, dx, this.a, true);
            }
        }
        finally {
            if (ji.util.d.dv()) {
                h.e(this.a, "Analysed FAST-JPEG.");
            }
        }
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        super.c = true;
        try {
            super.b = false;
            try {
                if (ji.util.d.dv()) {
                    h.e(this.a, "Decompressing FAST-JPEG...");
                }
                if (this.b != null) {
                    if (ji.util.d.dv()) {
                        h.e(this.a, "Decompressing FAST-JPEG2...");
                    }
                    fh.d.bk.put("Failover", this.b.getMessage());
                    final e3 e3 = new e3();
                    e3.a(this.a);
                    e3.fillDibInternal(fh);
                    if (ji.util.d.dv()) {
                        h.e(this.a, "Decompressing FAST-JPEG2A...");
                    }
                }
                else {
                    if (ji.util.d.dv()) {
                        h.e(this.a, "Decompressing FAST-JPEG3...");
                    }
                    fh.b.a(0L);
                    fh.c.b(4, false, fh.o);
                    try {
                        this.d.a(fh.b, fh.c, fh.d, fh.g, fh.o, fh);
                    }
                    finally {
                        if (!this.f) {
                            fh.c.e(fh.o);
                        }
                    }
                    if (ji.util.d.dv()) {
                        h.e(this.a, "Decompressing FAST-JPEG3A...");
                    }
                }
            }
            catch (Exception ex) {
                if (ex instanceof EOFException) {
                    throw ex;
                }
                if (ex instanceof ArrayIndexOutOfBoundsException) {
                    throw ex;
                }
                if (ex instanceof os) {
                    throw ex;
                }
            }
            finally {
                if (ji.util.d.dv()) {
                    h.e(this.a, "Decompressed FAST-JPEG.");
                }
            }
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public void abort(final dx dx) {
        if (ji.util.e.ai()) {
            super.b = true;
            if (this.d != null) {
                this.d.a();
            }
        }
    }
    
    public void close(final dx dx, final ad ad) {
    }
    
    public boolean e() {
        return false;
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        try {
            ac.a(0L);
            final int j = ac.j();
            final int i = ac.j();
            return ((j == 255 && i == 216) || false) ? 1 : 0;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return 2;
        }
    }
    
    public final boolean d(final String s) {
        return ji.util.d.a6();
    }
    
    public String c(final String s) {
        return this.getFilterName();
    }
}
