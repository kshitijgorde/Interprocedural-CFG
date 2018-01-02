// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import java.io.OutputStream;
import ji.document.gm;
import ji.v1event.af;
import ji.annotate.df;
import ji.io.ac;
import ji.io.h;
import ji.util.i;
import ji.document.ap;
import ji.document.ad;
import ji.document.es;
import ji.image.dx;

public abstract class cj implements ck
{
    protected String a;
    protected boolean b;
    protected boolean c;
    static final Object d;
    
    public abstract String getFilterName();
    
    public abstract dx loadImageHeaderInternal(final fh p0) throws Exception;
    
    public boolean supportsResolutionChange() {
        return false;
    }
    
    public boolean a() {
        return false;
    }
    
    public void a(final es es, final ad ad, final ap ap, final dx dx, final String s, final int n) throws Exception {
    }
    
    public int getResolution() {
        return 0;
    }
    
    public void setResolution(final int n) {
    }
    
    public boolean getAutoLimitResolution() {
        return false;
    }
    
    public void setAutoLimitResolution(final boolean b) {
    }
    
    public final dx a(final fh fh, final boolean b) throws Exception {
        dx dx = null;
        Label_0090: {
            if (!b) {
                if (i.c(36)) {
                    h.d(fh.u, "AbsFilter: loadHeader synchronizing");
                }
                synchronized (cj.d) {
                    if (i.c(36)) {
                        h.d(fh.u, "AbsFilter: loadHeader synchronized");
                    }
                    dx = this.a(fh);
                    if (i.c(36)) {
                        h.d(fh.u, "AbsFilter: loadHeader end synchronized");
                    }
                    // monitorexit(cj.d)
                    break Label_0090;
                }
            }
            dx = this.a(fh);
        }
        if (dx != null) {
            fh.o.b(dx.cy + dx.cz + dx.c0 + dx.c1, dx.cy + dx.c0, dx.cz + dx.c1);
        }
        return dx;
    }
    
    private final dx a(final fh fh) throws Exception {
        final dx loadImageHeaderInternal = this.loadImageHeaderInternal(fh);
        if (loadImageHeaderInternal != null) {
            loadImageHeaderInternal.al = loadImageHeaderInternal.am;
        }
        return loadImageHeaderInternal;
    }
    
    public abstract void fillDibInternal(final fh p0) throws Exception;
    
    public final void b(final fh fh, final boolean b) throws Exception {
        if (i.c(36)) {
            h.d(fh.u, "AbsFilter: filldib synchronizing: ".concat(String.valueOf(String.valueOf(this))));
        }
        if (!b) {
            synchronized (cj.d) {
                if (i.c(36)) {
                    h.d(fh.u, "AbsFilter: filldib synchronized");
                }
                this.fillDibInternal(fh);
                if (fh.c != null) {
                    fh.c.a(fh.o, fh.d, fh.p);
                }
                if (i.c(36)) {
                    h.d(fh.u, "AbsFilter: filldib end synchronized");
                }
                // monitorexit(cj.d)
                return;
            }
        }
        this.fillDibInternal(fh);
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public abstract void close(final dx p0, final ad p1);
    
    public void abort(final dx dx) {
        this.b = true;
    }
    
    public abstract boolean isAborted(final dx p0, final String p1);
    
    public abstract void clearAbort(final dx p0, final String p1);
    
    public boolean d() {
        return this.c;
    }
    
    public boolean e() {
        return false;
    }
    
    public df a(final ac ac, final df df, final dx dx, final af af, final ad ad, final String s, final int n, final boolean b, final int n2) throws Exception {
        return df;
    }
    
    public boolean f() {
        return false;
    }
    
    public abstract int[] getPalette(final ac p0, final dx p1, final String p2) throws Exception;
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        return 2;
    }
    
    public boolean isDefinitelySupportedMimeType(final String s, final ad ad) {
        return false;
    }
    
    public boolean d(final String s) {
        return true;
    }
    
    public boolean b(final String s) {
        return true;
    }
    
    public String c(final String s) {
        return this.getFilterName();
    }
    
    public String b() {
        return null;
    }
    
    public boolean a(final ad ad) {
        return true;
    }
    
    public void a(final dx dx, final dx dx2) {
    }
    
    public boolean isAttachmentAvailable() {
        return false;
    }
    
    public gm[] getAttachmentData() throws Exception {
        return null;
    }
    
    public void streamAttachment(final gm gm, final OutputStream outputStream) throws Exception {
    }
    
    static {
        d = new Object();
    }
}
