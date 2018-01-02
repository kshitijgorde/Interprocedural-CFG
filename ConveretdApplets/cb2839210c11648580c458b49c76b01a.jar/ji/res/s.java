// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.util.i;
import ji.util.t;
import ji.util.f3;
import java.util.Locale;
import ji.v1event.af;
import ji.util.e;
import ji.io.h;
import ji.util.d;
import ji.awt.c;

public class s
{
    public static String a;
    public static String b;
    private static String c;
    private static String d;
    private static Object e;
    private static ab f;
    private static c g;
    public static String h;
    public static boolean i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    public static boolean n;
    public static boolean o;
    public static boolean p;
    public static boolean q;
    private static boolean r;
    private static String s;
    
    public static final String a() {
        if (ji.res.s.f != null) {
            return ji.res.s.f.a();
        }
        return "UNKNOWN";
    }
    
    public static final String b() {
        if (ji.res.s.h == null) {
            return "NONE";
        }
        return ji.res.s.h;
    }
    
    public static final String c() {
        if (ji.res.s.a == null) {
            return "NONE";
        }
        return ji.res.s.a;
    }
    
    public static final String a(final String s, final String s2) {
        return a(s, s2, s.f);
    }
    
    public static final String a(final String s, final String s2, ab f) {
        synchronized (s.e) {
            if (f == null) {
                f = s.f;
            }
            try {
                if (s.g != null) {
                    if (ji.util.d.c6) {
                        if (s != null) {
                            if (ji.util.d.c(s, -1) < 0) {
                                ji.io.h.d(s2, "*** ERROR *** ");
                                ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("*** Upgrade issue: User trying to replace text for message with key '").append(s).append("', but keys are now numeric!"))));
                                ji.io.h.d(s2, "*** Advise user of the correct new numeric key they must use - see conversion.log output from localization tool.");
                            }
                            else {
                                final String s3 = (String)s.g.d(s.toLowerCase());
                                if (s3 != null) {
                                    // monitorexit(s.e)
                                    return s3;
                                }
                            }
                        }
                    }
                    else {
                        final String s5 = (String)s.g.d(s.toLowerCase());
                        if (s5 != null) {
                            // monitorexit(s.e)
                            return s5;
                        }
                    }
                }
                if (f == null) {
                    s.a = n(s2);
                    if (s.i) {
                        s.a = "en";
                    }
                    f = new ab(s2, "lanMessages");
                    s.h = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.a))).append(".").append("v1")));
                    String a = null;
                    if (ji.util.d.c3 == null) {
                        a = f.a(s.h, String.valueOf(String.valueOf(s.a)).concat(".txt"), ji.util.e.an(), true, null, null, true, 100);
                    }
                    else {
                        f.a(ji.util.d.c6);
                        f.a(null, ji.util.d.c3, ji.util.d.bn(s2));
                    }
                    if (a == null && f.g() < 64) {
                        a = "Invalid message file format #3!";
                        ji.io.h.d(s2, a);
                        ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Number of messages = ").append(f.g()).append(", cachedMessageInfo=").append(ji.util.d.c3 != null))));
                        try {
                            if (!ji.util.d.by(z.a(s2))) {
                                ji.io.h.d(s2, "Corrupt message file!");
                                ji.io.h.d(s2, "Clearing repository as a precaution.");
                                z.a(z.a(s2), s2);
                            }
                        }
                        catch (Exception ex3) {}
                    }
                    s.f = f;
                    if (a != null && !ji.util.d.dw()) {
                        ji.io.h.d(s2, a);
                        String fx = String.valueOf(String.valueOf(a)).concat("\n\nPlease ensure the above file exists before using this version of viewONE");
                        if (ji.util.d.mm) {
                            fx = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(fx)).concat("\nPlease note, the cause of this problem can be:\n\n"))).concat("1) The file is not present on the server.\n"))).concat("2) The file on the server is corrupt.\n"))).concat("3) The mime-type settings for your web server does not include '*.v1' files\n"))).concat("(If you need to add '*.v1' as a mime type to your server, please ensure that it is set to 'application/octet-stream').");
                        }
                        ji.util.d.a(ji.util.d.fx = fx, (af)null, s2);
                    }
                    if (!ji.util.d.by(aa.a(s2))) {}
                }
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
                // monitorexit(s.e)
                return "";
            }
        }
        // monitorexit(s.e)
        try {
            if (f != null) {
                return c(f.a(s));
            }
            if (ji.util.d.dw()) {
                return c(s);
            }
            return "";
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
            return "";
        }
    }
    
    public static final String a(final int n, final String s, final Object[] array) {
        String s2 = a(n, s);
        if (s2 != null) {
            for (int i = 0; i < 999; ++i) {
                final String value = String.valueOf(String.valueOf(new StringBuffer("<").append(i).append(">")));
                int n2 = s2.indexOf(value);
                if (n2 == -1) {
                    break;
                }
                while (n2 != -1 && i < array.length) {
                    final StringBuffer sb = new StringBuffer(s2.substring(0, n2));
                    sb.append(array[i]);
                    final int n3 = n2 + value.length();
                    if (s2.length() >= n3) {
                        sb.append(s2.substring(n3));
                    }
                    s2 = sb.toString();
                    n2 = s2.indexOf(value);
                }
            }
        }
        return s2;
    }
    
    public static final String a(final int n, final String s) {
        return a(n, s, s.f);
    }
    
    public static final String a(final int n, final String s, ab f) {
        synchronized (s.e) {
            if (!ji.util.d.c6) {
                ji.io.h.d(s, "Localization error #7!");
                // monitorexit(s.e)
                return "Localization error #7";
            }
            if (f == null) {
                f = s.f;
            }
            try {
                if (s.g != null) {
                    final String s3 = (String)s.g.d("".concat(String.valueOf(String.valueOf(n))));
                    if (s3 != null) {
                        // monitorexit(s.e)
                        return s3;
                    }
                }
                if (f == null) {
                    s.a = n(s);
                    if (s.i) {
                        ji.io.h.d(s, "*** Detected old version on server");
                        s.a = "en";
                    }
                    f = new ab(s, "lanMessages");
                    s.h = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.a))).append(".").append("v1")));
                    String a = null;
                    if (ji.util.d.c3 == null) {
                        a = f.a(s.h, String.valueOf(String.valueOf(s.a)).concat(".txt"), ji.util.e.an(), true, null, null, true, 100);
                    }
                    else {
                        f.a(ji.util.d.c6);
                        f.a(null, ji.util.d.c3, ji.util.d.bn(s));
                    }
                    if (a == null && f.g() < 64) {
                        a = "Invalid message file format #3!";
                        ji.io.h.d(s, a);
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Number of messages = ").append(f.g()).append(", cachedMessageInfo=").append(ji.util.d.c3 != null))));
                        try {
                            if (!ji.util.d.by(z.a(s))) {
                                ji.io.h.d(s, "Corrupt message file!");
                                ji.io.h.d(s, "Clearing repository as a precaution.");
                                z.a(z.a(s), s);
                            }
                        }
                        catch (Exception ex3) {}
                    }
                    s.f = f;
                    if (a != null && !ji.util.d.dw()) {
                        ji.io.h.d(s, a);
                        String fx = String.valueOf(String.valueOf(a)).concat("\n\nPlease ensure the above file exists before using this version of viewONE");
                        if (ji.util.d.mm) {
                            fx = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(fx)).concat("\nPlease note, the cause of this problem can be:\n\n"))).concat("1) The file is not present on the server.\n"))).concat("2) The file on the server is corrupt.\n"))).concat("3) The mime-type settings for your web server does not include '*.v1' files\n"))).concat("(If you need to add '*.v1' as a mime type to your server, please ensure that it is set to 'application/octet-stream').");
                        }
                        ji.util.d.a(ji.util.d.fx = fx, (af)null, s);
                    }
                    if (!ji.util.d.by(aa.a(s))) {}
                }
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
                // monitorexit(s.e)
                return "";
            }
        }
        // monitorexit(s.e)
        try {
            if (f != null) {
                return c(f.a(n));
            }
            if (ji.util.d.dw()) {
                return c("".concat(String.valueOf(String.valueOf(n))));
            }
            return "";
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
            return "";
        }
    }
    
    public static final void d() {
        if (ji.res.s.f != null) {
            ji.res.s.f.h();
        }
    }
    
    public static final String a(String s) {
        if (s.r && ji.util.d.eg()) {
            final char[] charArray = s.toCharArray();
            boolean b = false;
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == '\u00fd') {
                    charArray[i] = 'y';
                    b = true;
                }
                else if (charArray[i] == '\u00fe') {
                    charArray[i] = 'p';
                    b = true;
                }
                else if (charArray[i] == '\u00f0') {
                    charArray[i] = 'o';
                    b = true;
                }
            }
            if (b) {
                s = new String(charArray);
            }
        }
        return s;
    }
    
    public static final String b(final String s) {
        String s2 = s;
        if (Locale.getDefault().getLanguage().equals(Locale.JAPANESE.getLanguage())) {
            final char[] array = { '\uff10' };
            final int a = f3.a(array, 0);
            final String s3 = new String(array);
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (Character.isDigit(charArray[i])) {
                    charArray[i] = f3.a(Character.getNumericValue(charArray[i]) + a)[0];
                }
            }
            s2 = new String(charArray);
        }
        return s2;
    }
    
    public static final String c(final String s) {
        String s2 = a(t.a(t.b(s, "viewONE", "ViewONE"), "<quickstart>", "QuickStart"));
        if (!s.n) {
            return s2;
        }
        if (k()) {
            if ((j() || i()) && s.p) {
                if (!ji.util.d.by(s.k)) {
                    s2 = ji.util.d.b(s2, "viewone", s.k);
                }
                if (!ji.util.d.by(s.l)) {
                    s2 = ji.util.d.b(ji.util.d.b(s2, "http://www.daeja.com", s.l), "www.daeja.com", s.l);
                }
                if (!ji.util.d.by(s.m) || i()) {
                    s2 = ji.util.d.b(s2, "support@daeja.com", s.m);
                }
                if (!ji.util.d.by(s.m)) {
                    s2 = ji.util.d.b(s2, "upgrade@daeja.com", s.m);
                }
                if (!ji.util.d.by(s.j)) {
                    s2 = ji.util.d.b(ji.util.d.b(s2, "daeja image systems", s.j), "daeja", s.j);
                }
            }
            return s2;
        }
        return s2;
    }
    
    public static final String d(final String s) {
        return s.b;
    }
    
    public static final void e(final String s) {
        if (s != null) {
            s.a = s;
            s.q = true;
            try {
                if (!x.b().equals("")) {
                    s.b = s;
                }
                else {
                    s.b = null;
                }
            }
            catch (Exception ex) {}
        }
        o();
    }
    
    public static final void f(String bc) {
        bc = ji.util.d.bc(bc);
        if (!ji.util.d.by(bc) && !bc.equals("-")) {
            ji.res.s.k = bc;
        }
    }
    
    public static final String e() {
        return ji.res.s.k;
    }
    
    public static final void g(String bc) {
        bc = ji.util.d.bc(bc);
        if (!ji.util.d.by(bc) && !bc.equals("-")) {
            ji.res.s.j = bc;
        }
    }
    
    public static final String f() {
        return ji.res.s.j;
    }
    
    public static final void h(String bc) {
        bc = ji.util.d.bc(bc);
        if (!ji.util.d.by(bc) && !bc.equals("-")) {
            ji.res.s.l = bc;
        }
    }
    
    public static final String g() {
        return ji.res.s.l;
    }
    
    public static final String h() {
        return ji.res.s.m;
    }
    
    public static final String a(final String s, final boolean b) {
        if (!b) {
            s.p = false;
            final String a = a(9, s);
            s.p = true;
            return a;
        }
        return a(9, s);
    }
    
    public static final String b(final String s, final boolean b) {
        if (!b) {
            s.p = false;
            final String a = a(10, s);
            s.p = true;
            return a;
        }
        return a(10, s);
    }
    
    public static final void a(final boolean o) {
        ji.res.s.o = o;
    }
    
    public static final boolean i() {
        return k() && ji.res.s.o;
    }
    
    public static final boolean j() {
        return !ji.util.d.by() || ji.util.d.jk;
    }
    
    public static final boolean k() {
        return !ji.util.e.u() || true;
    }
    
    public static boolean i(final String s) {
        return k(s) && ji.util.i.c(93);
    }
    
    public static boolean j(final String s) {
        boolean b = false;
        try {
            final String o = o(s);
            if (o != null && ((ji.util.i.c(140) && c(o, "iw")) || c(o, "ar"))) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private static String o(final String s) {
        String s2 = s.a;
        if (!s.q) {
            try {
                s2 = x.b();
            }
            catch (Exception ex) {}
        }
        else {
            final String d = d(s);
            if (d != null) {
                s2 = d;
            }
        }
        return s2;
    }
    
    public static boolean k(final String s) {
        boolean b = false;
        try {
            final String o = o(s);
            if (o != null && (c(o, "ar") || c(o, "ja") || c(o, "ru") || c(o, "ko") || c(o, "zh") || c(o, "th") || c(o, "iw"))) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private static boolean c(final String s, final String s2) {
        try {
            if (s.toLowerCase().equals(s2.toLowerCase()) || s.toLowerCase().indexOf(String.valueOf(String.valueOf(s2.toLowerCase())).concat("_")) >= 0) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static final String l() {
        if (ji.res.s.s != null) {
            return ji.res.s.s.toLowerCase().substring(0, 2);
        }
        if (ji.res.s.c != null) {
            return ji.res.s.c.toLowerCase().substring(0, 2);
        }
        return "us";
    }
    
    public static final void l(String bc) {
        bc = ji.util.d.bc(bc);
        if (!ji.util.d.by(bc) && !bc.equals("-")) {
            ji.res.s.m = bc;
        }
    }
    
    public static final void b(String s, final String s2) {
        try {
            if (s.g == null) {
                s.g = new c("jiUtilTextReplacement");
            }
            if (s.toLowerCase().equals("helpannfix")) {
                s = "450";
            }
            s.g.a(s.toLowerCase(), s2);
        }
        catch (Exception ex) {}
    }
    
    public static final void m() {
        try {
            if (ji.res.s.g != null) {
                ji.res.s.g.c();
                ji.res.s.g = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean n() {
        return ji.res.s.r;
    }
    
    public static final String m(final String s) {
        if (!s.q) {
            try {
                s.a = x.b();
            }
            catch (Exception ex) {}
        }
        return s.a;
    }
    
    public static final String n(final String s) {
        if (!s.q) {
            try {
                s.a = x.b();
            }
            catch (Exception ex) {}
        }
        boolean b = true;
        s.d = s.a;
        if (s.d != null && s.d.toLowerCase().equals("tr")) {
            s.r = true;
        }
        try {
            s.c = x.a();
        }
        catch (Exception ex2) {
            s.c = "us";
        }
        if (ji.util.d.ba(s) == null && !ji.util.d.gg) {
            String f = null;
            if (!s.q) {
                f = ji.util.d.f(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.a))).append("_").append(s.c))), s);
            }
            if (f == null) {
                s.a = ji.util.d.f(s.a, s);
            }
            else {
                s.a = f;
            }
            if (s.a == null) {
                b = false;
            }
        }
        else {
            b = false;
        }
        if (!b) {
            s.a = "en";
            ji.util.d.bw("Cp1252");
        }
        return s.a;
    }
    
    public static void o() {
        try {
            if (ji.res.s.f != null) {
                ji.res.s.f.f();
                ji.res.s.f = null;
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        ji.res.s.a = "win_en";
        ji.res.s.b = null;
        ji.res.s.c = null;
        ji.res.s.d = "en";
        ji.res.s.e = new Object();
        ji.res.s.f = null;
        ji.res.s.g = null;
        ji.res.s.h = null;
        ji.res.s.i = false;
        ji.res.s.j = null;
        ji.res.s.k = null;
        ji.res.s.l = null;
        ji.res.s.m = null;
        ji.res.s.n = true;
        ji.res.s.o = false;
        ji.res.s.p = true;
        ji.res.s.q = false;
        ji.res.s.r = false;
        ji.res.s.s = null;
    }
}
