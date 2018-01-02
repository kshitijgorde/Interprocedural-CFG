// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.pdf;

import ji.util.m;
import ji.io.of;
import ji.io.oe;
import ji.v1event.a6;
import ji.util.fn;
import ji.io.fb;
import java.awt.Dimension;
import ji.util.cn;
import ji.res.aa;
import ji.filter.fh;
import ji.res.s;
import ji.annotate.df;
import ji.io.ac;
import ji.image.dx;
import ji.document.ap;
import ji.document.es;
import java.awt.Component;
import ji.util.e;
import ji.res.ay;
import ji.util.i;
import ji.v1event.ak;
import ji.v1event.a2;
import ji.v1event.af;
import ji.util.d;
import ji.document.ad;
import ji.io.h;
import ji.io.p;
import java.util.Vector;
import ji.filter.cj;

public class ci extends cj
{
    private Vector a;
    private static boolean b;
    private static ac5 c;
    private static boolean d;
    private final String e = "thumbnail";
    private static int f;
    private p g;
    
    public static final void a(final String s, final int n) {
        if (ci.f != 0) {
            String concat = null;
            switch (ci.f) {
                case 7: {
                    concat = "7.0.7";
                    break;
                }
                case 8: {
                    concat = "8.1.0";
                    break;
                }
                default: {
                    concat = "Undefined, ".concat(String.valueOf(String.valueOf(ci.f)));
                    break;
                }
            }
            h.c(s, "PDF Library Version has already been defined as ".concat(String.valueOf(String.valueOf(concat))));
        }
        else {
            Object o = null;
            switch (n) {
                case 7: {
                    o = "7.0.7";
                    ci.f = n;
                    break;
                }
                case 8: {
                    o = "8.1.0";
                    break;
                }
            }
            if (o != null) {
                ci.f = n;
                h.c(s, "PDF Library Version has been set as ".concat(String.valueOf(String.valueOf(o))));
            }
            else {
                h.c(s, "PDF Library Version has not been set as version is invalid: ".concat(String.valueOf(String.valueOf(n))));
            }
        }
    }
    
    private final oc a(final int n, final String s, final String s2, final boolean b) {
        return this.a(n, s, s2, null, null, false, b);
    }
    
    private final oc a(final int n, final String s, final String s2, String concat, final ad ad, final boolean b, final boolean b2) {
        try {
            oc a = null;
            if (b2) {
                if (concat == null) {
                    concat = "thumbnail";
                }
                else {
                    concat = String.valueOf(String.valueOf(concat)).concat("thumbnail");
                }
            }
            if (this.a != null) {
                synchronized (this.a) {
                    for (int i = 0; i < this.a.size(); ++i) {
                        final oc oc = this.a.elementAt(i);
                        if (!ji.util.d.by(oc.c()) && !ji.util.d.by(s) && s.equals(oc.c()) && oc.d.equals(s2)) {
                            if (oc.j == null && concat == null) {
                                a = oc;
                                break;
                            }
                            if (oc.j != null && concat != null && oc.j.equals(concat)) {
                                a = oc;
                                break;
                            }
                        }
                    }
                }
                // monitorexit(this.a)
            }
            if (a == null && concat != null && ad != null && b) {
                a = this.a(s, ad, s2, concat, null, b2);
            }
            return a;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private final oc a(final int n, final String s, final String s2, final ad ad) {
        try {
            oc oc = null;
            if (this.a != null) {
                synchronized (this.a) {
                    for (int i = 0; i < this.a.size(); ++i) {
                        final oc oc2 = this.a.elementAt(i);
                        if (!ji.util.d.by(oc2.c()) && !ji.util.d.by(s) && s.equals(oc2.c()) && oc2.d.equals(s2)) {
                            oc = oc2;
                            break;
                        }
                    }
                }
                // monitorexit(this.a)
            }
            return oc;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private final void a(final int n, final Object o) {
        try {
            if (this.a != null) {
                synchronized (this.a) {
                    if (this.a.contains(o)) {
                        this.a.removeElement(o);
                    }
                }
                // monitorexit(this.a)
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final oc a(final String s, final ad ad, final String s2, final af af, final boolean b) {
        return this.a(s, ad, s2, null, af, b);
    }
    
    private final oc a(final String s, final ad ad, final String d, String concat, final af af, final boolean b) {
        boolean b2 = false;
        final oc oc = new oc();
        try {
            oc.e = (ha)d.a2("daeja2.pdf.jiPdfDecoder");
            ci.b = true;
            oc.e.setParent(d, ad);
            oc.e.setPDFLibraryVersion(ci.f);
            oc.a(s);
            oc.d = d;
            if (b) {
                if (concat == null) {
                    concat = "thumbnail";
                }
                else {
                    concat = String.valueOf(String.valueOf(concat)).concat("thumbnail");
                }
            }
            oc.j = concat;
            (oc.f = new a2("pdfFilter".concat(String.valueOf(String.valueOf(oc.a()))), ad.iu())).b(oc);
            synchronized (this.a) {
                this.a.addElement(oc);
            }
            // monitorexit(this.a)
            if (i.c(5)) {
                h.d(d, String.valueOf(String.valueOf(new StringBuffer("Added new PDF Filter ").append(oc.d).append(", ").append(oc.f.a()))));
            }
            return oc;
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            b2 = true;
            noClassDefFoundError.printStackTrace();
            h.d(d, "******** No PDF class!...");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (oc.e == null) {
                h.d(d, "PDF filter: Failed to find copy of pdf decoder! (daeja2.jar)");
            }
        }
        if (b2) {
            if (oc != null) {
                oc.d();
            }
            h.d(d, "PDF filter: Failed to find copy of pdf decoder! (daeja2.jar)");
            try {
                d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.j(d)))).append("daeja2.jar").append("\n ----- ").append(d.b(260, d)).append(" -----"))), ad, 60, null, af, d);
            }
            catch (Exception ex2) {}
        }
        return null;
    }
    
    public boolean getAutoLimitResolution() {
        if (this.g != null) {
            return this.g.j(ji.util.d.ld, null);
        }
        return ji.util.d.ld;
    }
    
    public int getResolution() {
        if (this.g != null) {
            return this.g.p(ji.util.d.k9, null);
        }
        return ji.util.d.k9;
    }
    
    public void setAutoLimitResolution(final boolean b) {
        if (this.g != null) {
            this.g.i(b, null);
        }
    }
    
    public void setResolution(final int n) {
        if (this.g != null) {
            this.g.o(n, null);
        }
    }
    
    public boolean supportsResolutionChange() {
        return true;
    }
    
    public boolean a() {
        return true;
    }
    
    public void a(final es es, final ad ad, final ap n, final dx g, final String d, final int o) throws Exception {
        oc a = null;
        if (this.a != null) {
            synchronized (this.a) {
                a = this.a(4, g.h, d, "search", ad, true, g.a0);
            }
            // monitorexit(this.a)
        }
        if (a != null) {
            final od od = new od(6);
            od.d = d;
            od.m = (es)es.d();
            od.n = n;
            od.g = g;
            od.o = o;
            a.a(od);
            a.b();
            return;
        }
        throw new Exception("Unable to obtain PDF decoder object");
    }
    
    public ci() {
        this.a = new Vector();
        this.g = null;
    }
    
    public final String getFilterName() {
        return "jiFilterPDF";
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) {
        final int[] array = new int[0];
        try {
            oc a = null;
            if (this.a != null) {
                synchronized (this.a) {
                    a = this.a(1, dx.h, s, dx.a0);
                }
                // monitorexit(this.a)
            }
            if (a != null) {
                return a.e.getPalette(ac, dx);
            }
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return array;
        }
    }
    
    public void a(final String a) {
        super.a = a;
    }
    
    public df a(final ac ac, final df df, final dx g, final af af, final ad h, final String d, final int f, final boolean b, final int n) throws Exception {
        oc a = null;
        if (this.a != null) {
            synchronized (this.a) {
                a = this.a(2, g.h, d, g.a0);
            }
            // monitorexit(this.a)
        }
        if (a != null) {
            final od od = new od(3);
            od.d = d;
            od.g = g;
            od.h = h;
            od.f = f;
            final df df2 = (df)a.a(od);
            a.b();
            if (df2 != null) {
                df.a(df2);
                df2.m();
            }
            return df;
        }
        return null;
    }
    
    private boolean a(final ad ad, final boolean b) {
        synchronized (ci.c) {
            if (ci.c.a) {
                if (!ji.util.e.u(super.a)) {
                    h.c(super.a, "PDF Viewing filter disabled because we're an invalid OS");
                    ci.c.b = s.a(751, super.a);
                    if (b) {
                        ji.util.d.a(s.a(724, super.a), ji.util.d.f(ci.c.b, 70), ad, null, null, super.a, null, 120, true, false);
                    }
                    ci.c.a = false;
                }
                else if (ji.util.e.az() || ji.util.e.t(super.a) || ji.util.e.v(super.a) || ji.util.e.aa(super.a)) {
                    h.c(super.a, "PDF filter disabled because we're an invalid version of Windows");
                    ci.c.b = s.a(750, super.a);
                    if (b) {
                        ji.util.d.a(s.a(724, super.a), ji.util.d.f(ci.c.b, 70), ad, null, null, super.a, null, 120, true, false);
                    }
                    ci.c.a = false;
                }
                if (!ji.util.d.m("PDF module", super.a)) {
                    ci.c.a = false;
                }
            }
            // monitorexit(ci.c)
            return ci.c.a;
        }
    }
    
    public dx loadImageHeaderInternal(final fh c) throws Exception {
        if (!this.a(c.o, true)) {
            return null;
        }
        dx dx = null;
        int n = 2;
        boolean b = false;
        boolean b2 = false;
        boolean cancelled = false;
        boolean invalidPassword = false;
        String moduleError = null;
        int moduleErrorType = 0;
        boolean b3 = false;
        final String s = null;
        final long r = c.b.r();
        if (i.c(37)) {
            h.d(super.a, "PRO: PDF Filter test");
        }
        ji.util.d.b9(false);
        if (!a(c.b)) {
            if (i.c(37)) {
                h.d(super.a, "PRO: file is not PDF.");
            }
            return null;
        }
        while (n > 0 && !ji.util.d.er()) {
            --n;
            if (i.c(37)) {
                h.d(super.a, "PRO: file is PDF...");
            }
            if (aa.b(c.o, super.a)) {
                if (i.c(37)) {
                    h.d(super.a, "PDF: about to install PDF Library.");
                }
                cn.b(false, c.o, super.a, c.g);
                if (ji.util.d.er()) {
                    if (i.c(37)) {
                        h.d(super.a, "PDF: User aborted download");
                    }
                    return null;
                }
            }
            final String a = aa.a("daeja2.pdf.jiPDFv2", cn.d(), "com.datalogics.dli.JNIUtil", ji.util.e.u(), !ci.d, c.o, super.a);
            if (a != null) {
                h.d(super.a, a);
                ci.d = true;
                return null;
            }
            oc oc = null;
            if (this.a != null) {
                synchronized (this.a) {
                    oc = this.a(3, c.f, super.a, c.r);
                }
                // monitorexit(this.a)
            }
            if (oc == null) {
                oc = this.a(c.f, c.o, super.a, c.g, c.r);
                if (oc == null) {
                    c.b.a(r);
                    if (n == 0) {
                        ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.j(super.a)))).append("daeja2.jar").append("\n ----- ").append(ji.util.d.b(260, super.a)).append(" -----"))), c.o, 60, null, c.g, super.a);
                        return null;
                    }
                }
            }
            if (oc != null) {
                oc.a(c.f);
            }
            ha e = oc.e;
            try {
                if (e != null) {
                    e.resetFlags();
                    this.e(super.a);
                    double e2;
                    final double ai = e2 = this.g.p(ji.util.d.k9, c.o);
                    if (c.r && c.j == 6) {
                        b3 = true;
                        c.r = false;
                    }
                    if (c.r && c.j != 6) {
                        Dimension b4 = new Dimension(120, 120);
                        final double n2 = ji.util.d.la;
                        try {
                            b4 = this.g.b(b4, c.o);
                        }
                        catch (Exception ex2) {}
                        e2 = Math.max(ji.util.d.lb, b4.width * b4.height / 400);
                        if (i.c(5)) {
                            h.d(super.a, String.valueOf(String.valueOf(new StringBuffer("PDF: Thumbnail Resolution: ").append(e2).append(", thumbSize: ").append(b4))));
                        }
                    }
                    if (this.a != null) {
                        synchronized (this.a) {
                            final od od = new od(1);
                            od.d = super.a;
                            od.c = c;
                            od.e = e2;
                            dx = (dx)oc.a(od);
                            oc.b();
                        }
                        // monitorexit(this.a)
                    }
                    cancelled = e.isCancelled();
                    invalidPassword = e.isInvalidPassword();
                    moduleErrorType = e.getModuleErrorType();
                    moduleError = e.getModuleError();
                    if (dx != null || cancelled || invalidPassword || moduleErrorType != 0) {
                        if (dx != null) {
                            dx.ah = true;
                            dx.ai = ai;
                        }
                        n = 0;
                        break;
                    }
                    if (dx == null) {
                        h.d(super.a, "PDF header load failed.");
                    }
                }
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                b2 = true;
                noClassDefFoundError.printStackTrace();
                c.b.a(r);
                h.d(super.a, "******** No PDF class!...");
            }
            catch (fn fn) {
                if (fn.a() instanceof fb) {
                    throw (fb)fn.a();
                }
                b2 = true;
                fn.printStackTrace();
            }
            catch (Exception ex) {
                b2 = true;
                ex.printStackTrace();
            }
            if (b2) {
                e = null;
                h.d(super.a, "PDF filter: Failed to find copy of pdf decoder! (daeja2.jar)");
                ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.j(super.a)))).append(", daeja2.jar").append("\n ----- ").append(ji.util.d.b(260, super.a)).append(" -----"))), c.o, 60, null, c.g, super.a);
                if (n == 0) {
                    return null;
                }
            }
            if (s != null) {
                e = null;
                h.d(super.a, s);
                ji.util.d.a(ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.j(super.a)))).append(", ").append(s))), c.o, 60, null, c.g, super.a);
                if (n == 0) {
                    return null;
                }
            }
            if (n > 0) {
                try {
                    if (e != null && e.isForceRemoval()) {
                        e.removeDecoder(c.o, super.a);
                    }
                }
                catch (Exception ex3) {}
                if (!aa.b(c.o, super.a)) {
                    continue;
                }
                b = true;
                c.b.a(r);
            }
            else {
                if (b) {
                    continue;
                }
                continue;
            }
        }
        if (ji.util.d.er()) {
            dx = null;
        }
        if (dx == null) {
            ji.util.e.ag(null);
            if (c.g != null) {
                c.g.a(new a6(c.g, 1, ""));
            }
            if (c.g != null) {
                c.g.a(new a6(c.g, 13, ""));
            }
        }
        if (b3) {
            c.r = true;
        }
        if (cancelled) {
            if (i.c(37)) {
                h.d(super.a, "PRO: PDF - open cancelled");
            }
            throw new oe(ji.res.s.a(966, super.a));
        }
        if (invalidPassword) {
            if (i.c(37)) {
                h.d(super.a, "PRO: PDF - invalid password");
            }
            throw new oe(ji.res.s.a(967, super.a));
        }
        if (moduleErrorType != 0) {
            switch (moduleErrorType) {
                case 1: {
                    throw new of(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.s.a(1287, super.a)))).append(": ").append(moduleError))));
                }
                case 2: {
                    throw new fb(moduleError);
                }
            }
        }
        return dx;
    }
    
    private final void e(final String s) {
        try {
            if (this.g == null) {
                this.g = new p(s);
            }
        }
        catch (Exception ex) {}
    }
    
    private static boolean a(final ac ac) {
        try {
            boolean startsWith = false;
            ac.a(0L);
            if (!i.c(264)) {
                final byte[] array = new byte[(ac.w() > 1024) ? 1024 : ((int)ac.w())];
                if (new String(array, 0, ac.a(array)).indexOf("%PDF-") != -1) {
                    final long w = ac.w();
                    final int n = (int)Math.min(w, 1024L);
                    if (n > 10) {
                        ac.a(w - n);
                        startsWith = (new String(array, 0, ac.a(array)).indexOf("%%EOF") != -1);
                    }
                }
            }
            else {
                final byte[] array2 = new byte[15];
                ac.a(array2);
                final String s = new String(array2);
                if (!startsWith) {
                    startsWith = s.startsWith("%PDF-");
                }
            }
            return startsWith;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    public void fillDibInternal(final fh c) throws Exception {
        oc a = null;
        if (this.a != null) {
            synchronized (this.a) {
                a = this.a(4, c.d.h, c.u, c.r);
            }
            // monitorexit(this.a)
        }
        if (a != null) {
            final od od = new od(2);
            od.d = super.a;
            od.c = c;
            a.a(od);
            a.b();
            return;
        }
        throw new Exception("Unable to obtain PDF decoder object");
    }
    
    public final void clearAbort(final dx dx, final String s) {
        final oc a = this.a(5, dx.h, s, dx.a0);
        if (a != null) {
            a.e.clearAbort();
        }
    }
    
    public final boolean isAborted(final dx dx, final String s) {
        final oc a = this.a(6, dx.h, s, dx.a0);
        return a != null && a.e.isAborted();
    }
    
    public void close(final dx g, final ad h) {
        oc oc = null;
        if (this.a != null) {
            synchronized (this.a) {
                if (g != null) {
                    oc = this.a(7, g.h, h.iu(), h);
                }
            }
            // monitorexit(this.a)
        }
        while (oc != null) {
            if (h != null && g != null && g.h != null && oc != null) {
                if (i.c(5)) {
                    h.d(super.a, String.valueOf(String.valueOf(new StringBuffer("Close PDF filter ").append(oc.d).append(", ").append(oc.f.a()))));
                }
                final od od = new od(4);
                od.d = super.a;
                od.g = g;
                od.h = h;
                oc.a(od);
                oc.b();
                try {
                    this.g = null;
                    if (oc != null) {
                        oc.e.cleanUp();
                        oc.d();
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.a(7, oc);
            }
            if (this.a != null) {
                synchronized (this.a) {
                    if (g != null) {
                        oc = this.a(7, g.h, h.iu(), h);
                    }
                    // monitorexit(this.a)
                    continue;
                }
                break;
            }
        }
    }
    
    public void abort(final dx dx) {
        super.abort(dx);
        try {
            if (dx != null) {
                final oc a = this.a(8, dx.h, super.a, dx.a0);
                if (a != null) {
                    a.e.abort();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        if (a(ac)) {
            return this.a(ad, false) ? 1 : 4;
        }
        return 0;
    }
    
    public final boolean b(final String s) {
        return ji.util.e.w();
    }
    
    public String c(final String s) {
        return s.a(724, s);
    }
    
    public final String b() {
        synchronized (ci.c) {
            // monitorexit(ci.c)
            return ci.c.b;
        }
    }
    
    public static final void c() {
        if (!ci.b) {
            return;
        }
        try {
            final m m = new m();
            m.a("daeja2.pdf.jiPdfDecoder");
            m.b("releaseStaticResource").invoke(null, (Object[])null);
            ci.b = false;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    static {
        ci.b = false;
        ci.c = new ac5(null);
        ci.d = false;
        ci.f = 0;
    }
    
    private static class ac5
    {
        boolean a;
        String b;
        
        private ac5() {
            this.a = true;
            this.b = null;
        }
    }
    
    interface aev
    {
    }
}
