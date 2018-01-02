// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.util.gq;
import java.util.Enumeration;
import java.util.StringTokenizer;
import ji.util.d;
import java.util.Hashtable;

public class bd
{
    public static int a;
    public static int b;
    public static int c;
    private boolean[] d;
    private static String[] e;
    private static boolean[] f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    public static final int k;
    private int[] l;
    private static String[] m;
    private static int[] n;
    private String[] o;
    private static String[] p;
    private static String[] q;
    public String[][] r;
    private static String[] s;
    private static String[][] t;
    private static ac4 u;
    private static Hashtable v;
    
    public bd(final String s) {
        this.d = new boolean[46];
        this.l = new int[7];
        this.o = new String[10];
        this.r = new String[2][];
        f();
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i] = bd.f[i];
        }
        for (int j = 0; j < this.o.length; ++j) {
            this.o[j] = bd.q[j];
        }
        for (int k = 0; k < this.l.length; ++k) {
            this.l[k] = bd.n[k];
        }
        for (int l = 0; l < this.r.length; ++l) {
            this.r[l] = bd.t[l];
        }
        if (bd.v != null) {
            synchronized (bd.v) {
                bd.v.put(s, this);
            }
            // monitorexit(bd.v)
        }
    }
    
    public static bd a(final String s) {
        if (bd.v != null) {
            synchronized (bd.v) {
                // monitorexit(bd.v)
                return bd.v.get(s);
            }
        }
        return null;
    }
    
    private static final void e() {
        if (bd.f == null) {
            bd.f = new boolean[46];
        }
        if (bd.n == null) {
            bd.n = new int[7];
        }
        if (bd.q == null) {
            bd.q = new String[10];
        }
        bd.f[2] = true;
        bd.f[5] = false;
        bd.f[11] = false;
        bd.f[16] = true;
        bd.f[22] = true;
        bd.f[23] = false;
        bd.f[24] = true;
        bd.f[25] = true;
        bd.f[26] = true;
        bd.f[37] = true;
        bd.f[38] = true;
        bd.f[32] = true;
        bd.f[34] = true;
        bd.f[35] = true;
        bd.q[0] = "";
        bd.q[2] = "";
        bd.q[3] = "image";
        bd.q[4] = "152,192,217";
        bd.q[7] = "black";
        bd.n[4] = 5;
        bd.n[5] = 5;
        bd.n[6] = bd.k;
        bd.f[27] = true;
        bd.f[28] = true;
        bd.f[29] = false;
        bd.q[6] = "_blank";
        bd.f[30] = true;
        bd.f[31] = false;
        bd.f[33] = true;
        bd.f[40] = false;
        bd.n[3] = 40000;
        bd.q[9] = "thumbsleft,thumbsright";
    }
    
    private static final void f() {
        synchronized (bd.u) {
            if (!bd.u.a) {
                g();
                e();
                bd.u.a = true;
            }
        }
        // monitorexit(bd.u)
    }
    
    private static final void g() {
        if (bd.e == null) {
            bd.e = new String[46];
        }
        a(bd.e, 0, "tiffSaveColor");
        a(bd.e, 1, "multipleSaveFormats");
        a(bd.e, 2, "convertToTIFFOnSave");
        a(bd.e, 3, "burnPDFToPDF");
        a(bd.e, 4, "waitForSignalRender");
        a(bd.e, 5, "allowTextFilterDisplayAnyFile");
        a(bd.e, 6, "filenetAnnotBoundsCheck");
        a(bd.e, 6, "annotBoundsCheck");
        a(bd.e, bd.a, "filenetAnnotBoundsCheckAllPages");
        a(bd.e, bd.a, "annotBoundsCheckAllPages");
        a(bd.e, 8, "PDFToPDFBurnPageFix");
        a(bd.e, bd.b, "textFilterWrapping");
        a(bd.e, bd.c, "textFilterPageBreaks");
        a(bd.e, 11, "saveAnnotationsIgnoreFileDisplayErrors");
        a(bd.e, 12, "annotationGZipGet");
        a(bd.e, 13, "annotationGZipLocal");
        a(bd.e, 14, "annotationStream");
        a(bd.e, 15, "compressedPDFtoPDFBurnSupport");
        a(bd.e, 16, "PDFtoPDFBurnClearFiles");
        a(bd.e, 17, "annotationGZip");
        a(bd.e, 18, "thumbDblClickSelect");
        a(bd.e, 19, "annotationSaveNoteId");
        a(bd.e, 20, "allowSaveBurnableAnnotations");
        a(bd.e, 21, "burnAllAnnotations");
        a(bd.e, 22, "thumbsTextModeAllowed");
        a(bd.e, 23, "saveTIFFLZWMemoryImprovement");
        a(bd.e, 30, "allowSaveAttachments");
        a(bd.e, 31, "allowExternalImages");
        a(bd.e, 28, "pdf7RenderFix2");
        a(bd.e, 27, "pdf7RenderFix1");
        a(bd.e, 37, "bulkJSQueue");
        a(bd.e, 38, "preventStartAnnotationRender");
        a(bd.e, 24, "filenetCOLDUsePageBackgroundImage");
        a(bd.e, 25, "filenetCSAdminPermissionsOnChange");
        a(bd.e, 26, "annotationTextClip");
        a(bd.e, 41, "keepActiveAnnotOnResize");
        a(bd.e, 29, "showEMLAttachments");
        a(bd.e, 33, "blockOfficeModule");
        a(bd.e, 32, "ignoreTextFilterMimetypes");
        a(bd.e, 34, "searchResultsAlwaysVisible");
        a(bd.e, 35, "searchResultsVisibleOnThumbs");
        a(bd.e, 36, "pdfRenderRGB");
        a(bd.e, 39, "nwJSsetRedactionIsSemiTransparent");
        a(bd.e, 40, "newAnnotationStampMethod");
        a(bd.e, 42, "cookieHandler");
        a(bd.e, 43, "separateThumbsNetCache");
        a(bd.e, 44, "disableIORedirectPDF");
        a(bd.e, 45, "tiffConsolidateStripsG31D");
        if (bd.m == null) {
            bd.m = new String[7];
        }
        a(bd.m, 0, "textFilterPageHeight");
        a(bd.m, 1, "textFilterPageWidth");
        a(bd.m, 2, "pdfDibMemoryBuffer");
        a(bd.m, 3, "htmlDecoderMax");
        a(bd.m, 4, "findResultHighlightWidth");
        a(bd.m, 5, "findResultHistoryListSize");
        a(bd.m, 6, "pdfDllRenderMode");
        if (bd.p == null) {
            bd.p = new String[10];
        }
        a(bd.p, 0, "textfilterMimetypes");
        a(bd.p, 1, "textfilterLocalFileExtensions");
        a(bd.p, 2, "thumbDblClick");
        a(bd.p, 5, "attachmentBaseURL");
        a(bd.p, 6, "attachmentTarget");
        a(bd.p, 3, "defaultThumbsDisplayMode");
        a(bd.p, 4, "findResultColor");
        a(bd.p, 7, "findResultHighlightColor");
        a(bd.p, 8, "userLogoutRedirectMatch");
        a(bd.p, 9, "displaySearchBoxThumbnailView");
        if (bd.s == null) {
            bd.s = new String[2];
        }
        a(bd.s, 0, "attachmentBaseURLPage");
        a(bd.s, 1, "attachmentID");
        for (int i = 0; i < bd.e.length; ++i) {
            if (bd.e[i] == null) {
                throw new NullPointerException("index ".concat(String.valueOf(String.valueOf(i))));
            }
        }
        for (int j = 0; j < bd.m.length; ++j) {
            if (bd.m[j] == null) {
                throw new NullPointerException("index ".concat(String.valueOf(String.valueOf(j))));
            }
        }
        for (int k = 0; k < bd.p.length; ++k) {
            if (bd.p[k] == null) {
                throw new NullPointerException("index ".concat(String.valueOf(String.valueOf(k))));
            }
        }
        for (int l = 0; l < bd.s.length; ++l) {
            if (bd.s[l] == null) {
                throw new NullPointerException("index ".concat(String.valueOf(String.valueOf(l))));
            }
        }
    }
    
    private static void a(final String[] array, final int n, final String s) {
        if (d.by(array[n])) {
            array[n] = s.toLowerCase();
        }
        else {
            array[n] = String.valueOf(String.valueOf(array[n])).concat(String.valueOf(String.valueOf(",".concat(String.valueOf(String.valueOf(s.toLowerCase()))))));
        }
    }
    
    protected final void a(final bd bd) {
        for (int i = 0; i < this.d.length; ++i) {
            this.a(i, bd.a(i));
        }
        for (int j = 0; j < this.l.length; ++j) {
            this.a(j, bd.c(j));
        }
        for (int k = 0; k < this.o.length; ++k) {
            this.a(k, bd.b(k));
        }
        for (int l = 0; l < this.r.length; ++l) {
            final String[] d = bd.d(l);
            if (d == null) {
                this.a(l, (String[])null);
            }
            else {
                final String[] array = new String[d.length];
                System.arraycopy(d, 0, array, 0, d.length);
                this.a(l, array);
            }
        }
    }
    
    public final void a(final int n, final boolean b) {
        try {
            if (n < 46) {
                this.d[n] = b;
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean a(final int n) {
        return this.d[n];
    }
    
    public static final boolean a(final String s, final int n) {
        final ad x = d.x(s);
        return x != null && x.bi(n);
    }
    
    public final void a() {
        this.d = null;
        this.o = null;
        this.l = null;
    }
    
    public static final void b() {
        synchronized (bd.u) {
            bd.f = null;
            bd.q = null;
            bd.n = null;
            bd.e = null;
            bd.p = null;
            bd.m = null;
            bd.u.a = false;
        }
        // monitorexit(bd.u)
        synchronized (bd.v) {
            bd.v.clear();
        }
        // monitorexit(bd.v)
    }
    
    public final void a(final int n, final String s) {
        try {
            if (n < 10) {
                this.o[n] = new String(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final String b(final int n) {
        try {
            if (n < 10) {
                return this.o[n];
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public final void a(final int n, final int n2) {
        try {
            if (n < 7) {
                this.l[n] = n2;
            }
        }
        catch (Exception ex) {}
    }
    
    public final int c(final int n) {
        try {
            if (n < 7) {
                return this.l[n];
            }
        }
        catch (Exception ex) {}
        return 0;
    }
    
    public final void a(final int n, final String[] array) {
        try {
            if (n < 2) {
                this.r[n] = array;
            }
        }
        catch (Exception ex) {}
    }
    
    public final String[] d(final int n) {
        try {
            if (n < 2) {
                return this.r[n];
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private static final String e(final int n) {
        f();
        if (n < 46 && bd.e[n] != null) {
            return bd.e[n];
        }
        throw new RuntimeException("Property Boolean Value has no name: ".concat(String.valueOf(String.valueOf(n))));
    }
    
    private static final String f(final int n) {
        f();
        if (n < 10 && bd.p[n] != null) {
            return bd.p[n];
        }
        throw new RuntimeException("Property String Value has no name: ".concat(String.valueOf(String.valueOf(n))));
    }
    
    private static final String g(final int n) {
        f();
        if (n < 2 && bd.s[n] != null) {
            return bd.s[n];
        }
        throw new RuntimeException("Property String [] Value has no name: ".concat(String.valueOf(String.valueOf(n))));
    }
    
    private static final String h(final int n) {
        f();
        if (n < 7 && bd.m[n] != null) {
            return bd.m[n];
        }
        throw new RuntimeException("Property String Value has no name: ".concat(String.valueOf(String.valueOf(n))));
    }
    
    public static final int b(String lowerCase) {
        if (!d.by(lowerCase)) {
            f();
            lowerCase = lowerCase.toLowerCase();
            for (int i = 0; i < bd.p.length; ++i) {
                if (a(bd.p[i], lowerCase)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static final int c(String lowerCase) {
        if (!d.by(lowerCase)) {
            f();
            lowerCase = lowerCase.toLowerCase();
            for (int i = 0; i < bd.e.length; ++i) {
                if (a(bd.e[i], lowerCase)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static final int d(String lowerCase) {
        if (!d.by(lowerCase)) {
            f();
            lowerCase = lowerCase.toLowerCase();
            for (int i = 0; i < bd.m.length; ++i) {
                if (a(bd.m[i], lowerCase)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static final int e(String lowerCase) {
        if (!d.by(lowerCase)) {
            f();
            lowerCase = lowerCase.toLowerCase();
            for (int i = 0; i < bd.s.length; ++i) {
                if (a(bd.s[i], lowerCase)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private static final boolean a(final String s, final String s2) {
        boolean equals = false;
        if (!d.by(s)) {
            if (s.indexOf(44) > -1) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (!d.by(nextToken) && nextToken.equals(s2)) {
                        equals = true;
                        break;
                    }
                }
            }
            else {
                equals = s.equals(s2);
            }
        }
        return equals;
    }
    
    public static final boolean f(final String s) {
        return c(s) != -1 || b(s) != -1 || d(s) != -1 || e(s) != -1;
    }
    
    public final void a(final Hashtable hashtable) {
        f();
        final Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String[] value = hashtable.get(s);
            if (value == null) {
                continue;
            }
            final int c = c(s);
            if (c != -1) {
                this.a(c, ji.util.d.b(value.toString(), false));
            }
            else {
                final int b = b(s);
                if (b != -1) {
                    this.a(b, value.toString());
                }
                else {
                    final int d = d(s);
                    if (d != -1 && ji.util.d.bb(value.toString())) {
                        this.a(d, Integer.parseInt(value.toString()));
                    }
                    else {
                        final int e = e(s);
                        if (e == -1 || !(value instanceof String[])) {
                            continue;
                        }
                        this.a(e, value);
                    }
                }
            }
        }
    }
    
    private final int i(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < this.d.length; ++i) {
            if (this.d[i] != bd.f[i]) {
                return i;
            }
        }
        return -1;
    }
    
    private final int j(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < this.o.length; ++i) {
            if (bd.q[i] == null) {
                if (this.o[i] != null) {
                    return i;
                }
            }
            else {
                if (this.o[i] == null) {
                    return i;
                }
                if (!this.o[i].equals(bd.q[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private final int k(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < this.r.length; ++i) {
            if (bd.t[i] == null) {
                if (this.r[i] != null) {
                    return i;
                }
            }
            else {
                if (this.r[i] == null) {
                    return i;
                }
                if (!gq.a(this.r[i], bd.t[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    private final int l(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < this.l.length; ++i) {
            if (this.l[i] != bd.n[i]) {
                return i;
            }
        }
        return -1;
    }
    
    private String g(String s) {
        if (s.indexOf("filenet") > -1 && !ji.util.d.bf()) {
            s = ji.util.d.b(s, "filenet", "*oem1*");
        }
        if (s.indexOf("iconect") > -1 && !ji.util.d.av()) {
            s = ji.util.d.b(s, "iconect", "*oem2*");
        }
        return s;
    }
    
    public final StringBuffer c() {
        synchronized (bd.u) {
            final StringBuffer sb = new StringBuffer();
            try {
                int i = -1;
                while ((i = this.i(i)) != -1) {
                    sb.append(String.valueOf(String.valueOf(new StringBuffer("dc: ").append(this.g(e(i))).append(" = ").append(this.a(i)).append("\n"))));
                }
                int l = -1;
                while ((l = this.l(l)) != -1) {
                    sb.append(String.valueOf(String.valueOf(new StringBuffer("dc: ").append(this.g(h(l))).append(" = ").append(this.c(l)).append("\n"))));
                }
                int j = -1;
                while ((j = this.j(j)) != -1) {
                    sb.append(String.valueOf(String.valueOf(new StringBuffer("dc: ").append(this.g(f(j))).append(" = ").append(this.b(j)).append("\n"))));
                }
                int k = -1;
                while ((k = this.k(k)) != -1) {
                    sb.append(String.valueOf(String.valueOf(new StringBuffer("dc: ").append(this.g(g(k))).append(" = ").append(gq.a(this.d(k))).append("\n"))));
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            // monitorexit(bd.u)
            return sb;
        }
    }
    
    final String d() {
        final String b = this.b(2);
        if (ji.util.d.by(b)) {
            return this.a(18) ? "Select" : "FullPage";
        }
        final String lowerCase = b.toLowerCase();
        if (lowerCase.equals("FullPage".toLowerCase())) {
            return "FullPage";
        }
        if (lowerCase.equals("Select".toLowerCase())) {
            return "Select";
        }
        if (lowerCase.equalsIgnoreCase("NewWindow".toLowerCase())) {
            return "NewWindow";
        }
        return "FullPage";
    }
    
    static {
        bd.a = 7;
        bd.b = 9;
        bd.c = 10;
        bd.e = new String[46];
        bd.f = new boolean[46];
        bd.g = 0;
        bd.h = 1;
        bd.i = 2;
        bd.j = 3;
        k = bd.j;
        bd.m = new String[7];
        bd.n = new int[7];
        bd.p = new String[10];
        bd.q = new String[10];
        bd.s = new String[2];
        bd.t = new String[2][];
        bd.u = new ac4(null);
        bd.v = new Hashtable();
    }
    
    private static class ac4
    {
        boolean a;
    }
    
    interface aeu
    {
    }
}
