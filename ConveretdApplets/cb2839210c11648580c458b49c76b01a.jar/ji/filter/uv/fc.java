// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.uv;

import ji.util.m;
import ji.res.s;
import ji.io.oe;
import ji.v1event.a6;
import ji.util.e;
import ji.res.aa;
import java.awt.Dimension;
import java.awt.Component;
import ji.res.ay;
import ji.util.i;
import ji.util.d;
import ji.filter.fh;
import ji.document.ad;
import ji.v1event.af;
import ji.annotate.df;
import ji.image.dx;
import ji.io.ac;
import ji.io.h;
import java.util.Hashtable;
import ji.io.p;
import ji.ext.v;
import ji.filter.cj;

public class fc extends cj
{
    private static ac6 a;
    private v b;
    private nz c;
    private p d;
    public static final int e;
    public static final boolean f;
    public static final int g;
    private static final String[] h;
    private static final String[] i;
    private static final String[] j;
    private static final String[] k;
    private static final String[] l;
    private Hashtable m;
    
    public fc(final String a) {
        this.b = new v();
        this.d = null;
        ji.io.h.c(super.a = a, "jiFilterUV 1");
        this.m = new Hashtable();
    }
    
    public final boolean supportsResolutionChange() {
        return true;
    }
    
    public int getResolution() {
        if (this.d != null) {
            return this.d.h(null);
        }
        return fc.e;
    }
    
    public void setResolution(final int n) {
        if (this.d != null) {
            this.d.q(n, null);
        }
    }
    
    public boolean getAutoLimitResolution() {
        if (this.d != null) {
            return this.d.f(null);
        }
        return fc.f;
    }
    
    public void setAutoLimitResolution(final boolean b) {
        if (this.d != null) {
            this.d.l(b, null);
        }
    }
    
    public final String getFilterName() {
        return "jiFilterUV";
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) {
        final int[] array = new int[0];
        try {
            return this.c.getPalette(ac, dx);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return array;
        }
    }
    
    public void a(final String a) {
        super.a = a;
    }
    
    public df a(final ac ac, final df df, final dx dx, final af af, final ad ad, final String s, final int n, final boolean b, final int n2) throws Exception {
        if (this.c != null && ad.bi(29)) {
            if (this.m.containsKey(ac.a())) {
                return null;
            }
            df.a(this.c.getAnnotations(s, dx, ad, n));
            this.m.put(ac.a(), "");
            return df;
        }
        else {
            if (this.c != null) {
                df.a(this.c.getAnnotations(s, dx, ad, n));
                return df;
            }
            return null;
        }
    }
    
    private boolean a(final fh fh) {
        if (fh != null) {
            final String m = fh.m;
            final String c = ji.util.d.c(fh.f, m, fh.n);
            if (c != null) {
                final String lowerCase = c.toLowerCase();
                for (int i = 0; i < fc.l.length; ++i) {
                    if (lowerCase.equals(fc.l[i].toLowerCase())) {
                        return true;
                    }
                }
            }
            if (m != null) {
                final String lowerCase2 = m.toLowerCase();
                for (int j = 0; j < fc.l.length; ++j) {
                    if (lowerCase2.equals(fc.l[j].toLowerCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean g() {
        boolean b = true;
        if (this.c != null) {
            if (this.c.isAborted()) {
                b = false;
            }
            if (this.c.isCancelled()) {
                b = false;
            }
        }
        return b && !ji.util.d.c5() && !ji.util.d.c4();
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (!this.a(fh.o, true)) {
            return null;
        }
        if (ji.util.i.c(256) && this.a(fh)) {
            return null;
        }
        dx processHeader = null;
        int n = 2;
        boolean b = false;
        boolean b2 = false;
        boolean cancelled = false;
        boolean invalidPassword = false;
        boolean b3 = false;
        final long r = fh.b.r();
        if (ji.util.i.c(37)) {
            ji.io.h.d(super.a, "PRO: Universal Viewing Filter test");
        }
        ji.util.d.b9(false);
        while (n > 0 && !ji.util.d.er() && !ji.util.d.ck(super.a)) {
            --n;
            if (this.a(fh.b)) {
                if (ji.util.i.c(37)) {
                    ji.io.h.d(super.a, "PRO: file is valid...");
                }
                try {
                    this.h();
                }
                finally {
                    if (this.c == null && n == 0) {
                        ji.util.d.a(ay.a(), ji.util.d.b(1158, super.a), fh.o, 60, null, fh.g, super.a);
                        return null;
                    }
                }
                try {
                    if (this.c != null) {
                        this.c.resetFlags();
                        this.e(super.a);
                        double n2 = this.getResolution();
                        if (fh.r && fh.j == 6) {
                            b3 = true;
                            fh.r = false;
                        }
                        if (fh.r && fh.j != 6) {
                            Dimension b4 = new Dimension(120, 120);
                            final double n3 = ji.util.d.la;
                            try {
                                b4 = this.d.b(b4, fh.o);
                            }
                            catch (Exception ex) {
                                ji.util.d.a(ex);
                            }
                            n2 = Math.max(ji.util.d.lb, b4.width * b4.height / 300);
                        }
                        processHeader = this.c.processHeader(fh, super.a, n2);
                        if (this.isAborted(fh.d, super.a)) {
                            n = 0;
                            break;
                        }
                        if (processHeader != null) {
                            processHeader.bj = this;
                        }
                        cancelled = this.c.isCancelled();
                        invalidPassword = this.c.isInvalidPassword();
                        final boolean knownNonDisplayable = this.c.isKnownNonDisplayable();
                        final boolean viewingTechnologyLoaded = this.c.isViewingTechnologyLoaded();
                        if (processHeader != null || cancelled || invalidPassword || knownNonDisplayable || viewingTechnologyLoaded) {
                            if (processHeader != null) {
                                processHeader.ah = true;
                            }
                            n = 0;
                            break;
                        }
                    }
                }
                catch (NoClassDefFoundError noClassDefFoundError) {
                    b2 = true;
                    noClassDefFoundError.printStackTrace();
                    fh.b.a(r);
                    ji.io.h.d(super.a, "******** No Universal Viewing class!...");
                }
                catch (Exception ex2) {
                    b2 = true;
                    ex2.printStackTrace();
                }
                if (b2) {
                    this.c = null;
                    ji.io.h.d(super.a, "UV filter: Problem creating Universal Viewing decoder");
                    ji.util.d.a(ay.a(), ji.util.d.b(1158, super.a), fh.o, 60, null, fh.g, super.a);
                    if (this.c == null && n == 0) {
                        return null;
                    }
                }
            }
            else if (ji.util.i.c(37)) {
                ji.io.h.d(super.a, "PRO: file is not valid.");
            }
            if (n > 0) {
                if (!this.g()) {
                    continue;
                }
                try {
                    if (this.c != null) {
                        this.c.removeDecoder(fh.o, super.a);
                    }
                }
                catch (Exception ex3) {}
                if (!aa.b(fh.o, super.a)) {
                    continue;
                }
                b = true;
                fh.b.a(r);
            }
            else {
                if (b) {
                    continue;
                }
                continue;
            }
        }
        if (this.isAborted(fh.d, super.a) && this.g()) {
            if (this.c != null) {
                this.c.close(processHeader);
                this.c.removeDecoder(fh.o, super.a);
            }
            processHeader = null;
        }
        if (processHeader == null) {
            ji.util.e.ag(null);
            if (fh.g != null) {
                fh.g.a(new a6(fh.g, 1, ""));
            }
            if (fh.g != null) {
                fh.g.a(new a6(fh.g, 13, ""));
            }
        }
        if (b3) {
            fh.r = true;
        }
        if (cancelled) {
            if (ji.util.i.c(37)) {
                ji.io.h.d(super.a, "PRO: UV - open cancelled");
            }
            throw new oe(ji.util.d.b(966, super.a));
        }
        if (invalidPassword) {
            if (ji.util.i.c(37)) {
                ji.io.h.d(super.a, "PRO: UV - invalid password");
            }
            throw new oe(ji.util.d.b(967, super.a));
        }
        if (this.isAborted(processHeader, super.a)) {
            if (ji.util.i.c(37)) {
                ji.io.h.d(super.a, "PRO: UV - user aborted open");
            }
            throw new oe(ji.util.d.b(966, super.a));
        }
        if (processHeader != null) {
            processHeader.e = true;
        }
        return processHeader;
    }
    
    private void h() {
        if (this.c == null) {
            try {
                (this.c = (nz)ji.util.d.a2("ji.filter.uv.jiUVDecoder")).setParentId(super.a);
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                noClassDefFoundError.printStackTrace();
                ji.io.h.d(super.a, "******** No Universal Viewing class!...");
                this.c = null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.c = null;
            }
            finally {
                if (this.c == null) {
                    ji.io.h.d(super.a, "UV filter: Problem creating decoder");
                }
            }
        }
    }
    
    private boolean a(final ad ad, final boolean b) {
        synchronized (fc.a) {
            if (fc.a.a) {
                if (!ji.util.e.u(super.a)) {
                    ji.io.h.c(super.a, "Universal Viewing filter disabled because we're an invalid OS");
                    fc.a.b = s.a(752, super.a);
                    if (b) {
                        ji.util.d.a(s.a(1194, super.a), ji.util.d.f(fc.a.b, 70), ad, null, null, super.a, null, 120, true, false);
                    }
                    fc.a.a = false;
                }
                else if (ji.util.e.az() || ji.util.e.t(super.a) || ji.util.e.v(super.a) || ji.util.e.aa(super.a)) {
                    ji.io.h.c(super.a, "Universal Viewing filter disabled because we're an invalid version of Windows");
                    fc.a.b = s.a(752, super.a);
                    if (b) {
                        ji.util.d.a(s.a(1194, super.a), ji.util.d.f(fc.a.b, 70), ad, null, null, super.a, null, 120, true, false);
                    }
                    fc.a.a = false;
                }
                else if (ji.util.e.w(super.a) && !this.b.b(super.a, ad)) {
                    ji.io.h.c(super.a, "Universal Viewing filter disabled because the version of XP must be SP2 or greater.");
                    fc.a.b = s.a(752, super.a);
                    if (b) {
                        ji.util.d.a(s.a(1194, super.a), ji.util.d.f(fc.a.b, 70), ad, null, null, super.a, null, 120, true, false);
                    }
                    fc.a.a = false;
                }
                if (!ji.util.d.m("Universal Viewing module", super.a)) {
                    fc.a.a = false;
                }
            }
            // monitorexit(fc.a)
            return fc.a.a;
        }
    }
    
    private final void e(final String s) {
        try {
            if (this.d == null) {
                this.d = new p(s);
            }
        }
        catch (Exception ex) {}
    }
    
    private boolean a(final ac ac) {
        return true;
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        if (this.c != null) {
            this.c.fillDib(fh, super.a);
        }
    }
    
    public final void clearAbort(final dx dx, final String s) {
        if (this.c != null) {
            this.c.clearAbort();
        }
    }
    
    public final boolean isAborted(final dx dx, final String s) {
        return this.c != null && this.c.isAborted();
    }
    
    public void close(final dx dx, final ad ad) {
        try {
            ji.io.h.c(super.a, "jiFilterUV close called for ".concat(String.valueOf(String.valueOf(this))));
            this.d = null;
            if (dx != null) {
                dx.bj = null;
                if (this.c != null) {
                    this.c.close(dx);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void abort(final dx dx) {
        super.abort(dx);
        try {
            if (this.c != null) {
                this.c.abort(dx);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final boolean a(final String s, final String s2, final ad ad, final boolean b) {
        if (ji.util.i.c(256)) {
            return s != null && s.startsWith("doc");
        }
        if (b) {
            return false;
        }
        if (!ji.util.d.cz(s2)) {
            return a(s, ad);
        }
        return b(s2, ad);
    }
    
    private static boolean a(final String s, final ad ad) {
        for (int i = 0; i < fc.h.length; ++i) {
            if (s.startsWith(fc.h[i])) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean b(final String s, final ad ad) {
        return ji.util.d.a(s, fc.i);
    }
    
    public final boolean isDefinitelySupportedMimeType(final String s, final ad ad) {
        return b(s, ad);
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        if (b) {
            return 0;
        }
        if (!this.a(ad, false)) {
            return 5;
        }
        if (!this.b(super.a)) {
            return 3;
        }
        if (!this.a(ad, false)) {
            return 0;
        }
        this.h();
        if (this.c == null) {
            return 2;
        }
        return this.c.isFileType(ac, ad, super.a, s3, s4, af, s);
    }
    
    public final boolean b(final String s) {
        return ji.util.e.x();
    }
    
    public final boolean d(final String s) {
        return true;
    }
    
    public String c(final String s) {
        return s.a(1194, s);
    }
    
    public String b() {
        synchronized (fc.a) {
            // monitorexit(fc.a)
            return fc.a.b;
        }
    }
    
    public static final void c() {
        try {
            final m m = new m();
            m.a("ji.filter.uv.jiUVDecoder");
            m.b("releaseStaticResource").invoke(null, (Object[])null);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    static {
        fc.a = new ac6(null);
        e = ji.util.d.k9;
        f = ji.util.d.ld;
        g = ji.util.d.le;
        h = new String[] { "doc", "xls", "ppt", "docx", "xlsx", "pptx", "psd", "dwg", "dxf", "ps" };
        i = new String[] { "application/msword", "application/msexcel", "application/vnd.ms-excel", "application/mspowerpoint", "application/vnd.ms-powerpoint", "application/vnd.ms-project", "application/vnd.openxmlformats-officedocuments.wordprocessingml.document", "application/vnd.openxmlformats-officedocuments.spreadsheetml.sheet", "application/vnd.openxmlformats-officedocuments.presentationml.presentation", "application/vnd.adobe.photoshop", "image/vnd.dwg", "image/vnd.dxf", "application/acad", "application/postscript" };
        j = new String[] { "text/plain", "text/csv", "text/tab-separated-values" };
        k = new String[] { "text/html" };
        l = new String[] { "ant", "jp2", "jpg", "jpeg", "djvu", "png", "bmp", "pix", "zip", "tif", "tiff", "pdf", "txt", "text", "htm", "html" };
    }
    
    private static class ac6
    {
        boolean a;
        String b;
        
        private ac6() {
            this.a = true;
            this.b = null;
        }
    }
    
    interface aew
    {
    }
}
