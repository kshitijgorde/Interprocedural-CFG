// 
// Decompiled by Procyon v0.5.30
// 

package ji.font;

import java.net.URL;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import ji.io.ac;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import ji.image.dw;
import ji.document.ad;
import java.util.Enumeration;
import java.util.Hashtable;
import ji.res.s;
import ji.util.e;
import ji.v1event.a6;
import ji.io.h;
import java.util.StringTokenizer;
import ji.res.z;
import ji.util.y;
import ji.v1event.af;
import java.awt.Component;
import ji.util.d;
import ji.util.i;
import java.awt.Font;
import ji.util.m;
import java.awt.Image;

public final class j
{
    static Object a;
    static Image b;
    static int c;
    static int d;
    static boolean e;
    private static int f;
    private static Object g;
    private static m h;
    public static String i;
    private static String j;
    private static String k;
    private static String l;
    public static boolean m;
    public static boolean n;
    public static boolean o;
    public static int p;
    public static int q;
    public static int r;
    public static int s;
    public static int t;
    public static int u;
    public static boolean v;
    private static int w;
    private static int x;
    private static int y;
    private static String z;
    private static String aa;
    private static int ab;
    private static String[] ac;
    private static ct[] ad;
    static vd ae;
    public static int af;
    private static int ag;
    private static boolean ah;
    private static double ai;
    private static String[] aj;
    private static String[] ak;
    private static char[][] al;
    private static int[] am;
    private static int an;
    private static int ao;
    private static int ap;
    private static String aq;
    private static String ar;
    private static String as;
    private static Object at;
    private static String au;
    private static boolean av;
    private static int aw;
    private static String ax;
    private static int ay;
    private static int az;
    private static boolean a0;
    private static byte[] a1;
    
    public static ct a() {
        return new ct(ji.font.j.k, ji.font.j.q, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static ct a(final Font font) {
        return a(font, ji.font.j.i, true);
    }
    
    public static ct a(final Font i, final String s, final boolean b) {
        final ct ct = new ct(s, ji.font.j.r, true, false, false);
        if (i.c(247) && b) {
            final boolean b2 = false | true;
            if (i != null) {
                ct.i = i;
            }
            else {
                ct.i = new Font(s, b2 ? 1 : 0, ji.font.j.r);
            }
        }
        return ct;
    }
    
    public static ct b() {
        return new ct(ji.font.j.i, ji.font.j.r, true, false, false);
    }
    
    public static ct c() {
        return new ct(ji.font.j.k, ji.font.j.q, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static ct d() {
        return new ct(ji.font.j.l, ji.font.j.q, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static ct e() {
        return new ct(ji.font.j.k, ji.font.j.q, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static ct f() {
        return new ct(ji.font.j.k, ji.font.j.q, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static final void a(final String s) {
        if (!ji.util.d.by(s)) {
            ji.font.j.i = s.toLowerCase();
            ji.font.j.k = s.toLowerCase();
        }
    }
    
    public static final String g() {
        return ji.font.j.j;
    }
    
    public static final void a(final int r) {
        b(ji.font.j.r = r);
    }
    
    public static final void b(final String j) {
        if (!ji.util.d.by(j)) {
            j.j = j;
        }
    }
    
    public static final void c(final String j) {
        if (!ji.util.d.by(j)) {
            j.j = j;
            j.i = j.toLowerCase();
            j.k = j.toLowerCase();
        }
    }
    
    public static final ct h() {
        return a();
    }
    
    public static final void b(final int n) {
        ji.font.j.v = true;
        ji.font.j.w = n;
        ji.font.j.x = n;
    }
    
    public static final void c(final int y) {
        ji.font.j.y = y;
    }
    
    public static final int i() {
        return ji.font.j.y;
    }
    
    public static final ct j() {
        return a((Font)null);
    }
    
    public static final ct b(final Font font) {
        return a(font);
    }
    
    public static final String k() {
        return ji.font.j.au;
    }
    
    public static final boolean l() {
        return ji.font.j.av;
    }
    
    public static final int m() {
        return ji.font.j.aw;
    }
    
    public static final int n() {
        return ji.font.j.ay;
    }
    
    public static final void d(final int az) {
        ji.font.j.az = az;
    }
    
    public static final int o() {
        return ji.font.j.az;
    }
    
    public static final String p() {
        return ji.font.j.ax;
    }
    
    public static final void d(final String au) {
        ji.font.j.au = au;
    }
    
    public static final void a(final boolean av) {
        ji.font.j.av = av;
    }
    
    public static final void e(final int aw) {
        ji.font.j.aw = aw;
    }
    
    public static final void e(final String ax) {
        ji.font.j.ax = ax;
    }
    
    public static final void f(final int ay) {
        ji.font.j.ay = ay;
    }
    
    public static final String q() {
        return ji.font.j.aa;
    }
    
    public static final String r() {
        return ji.font.j.z;
    }
    
    public static final int s() {
        return ji.font.j.ab;
    }
    
    public static final d1 a(final Component component, final af af, final String s, final ct ct, final double n) throws Exception {
        return a(component, ct, af, ji.util.i.c(247), s, n, true);
    }
    
    public static final d1 a(final Component component, final af af, final String s, final ct ct, final double n, final boolean b) throws Exception {
        return a(component, ct, af, ji.util.i.c(247) && b, s, n, b);
    }
    
    private static final String[] b(final Component component, final af af, final String s) throws Exception {
        final byte[] a = ji.res.z.a(component, "ftlist.txt", ji.util.d.b("ftlist.txt", ji.util.d.bh("ftlist.txt"), "v1"), af, null, s, new y());
        String[] array = null;
        if (a != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(a), "\r\n");
            int i = stringTokenizer.countTokens();
            if (i < 1) {
                ji.io.h.d(s, "No fonts!");
            }
            else {
                array = new String[i];
                int n = 0;
                while (i > 0) {
                    array[n++] = stringTokenizer.nextToken();
                    --i;
                }
            }
        }
        return array;
    }
    
    private static final synchronized void c(final Component component, final af af, final String s) throws Exception {
        try {
            if (ji.font.j.ac == null) {
                ji.font.j.ac = b(component, af, s);
                if (ji.font.j.ac != null) {
                    ji.font.j.ad = new ct[ji.font.j.ac.length];
                    for (int i = 0; i < ji.font.j.ac.length; ++i) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(ji.font.j.ac[i], ":");
                        final int countTokens = stringTokenizer.countTokens();
                        final String nextToken = stringTokenizer.nextToken();
                        final String nextToken2 = stringTokenizer.nextToken();
                        final String nextToken3 = stringTokenizer.nextToken();
                        ji.font.j.ac[i] = nextToken;
                        ji.font.j.ad[i] = new ct();
                        ji.font.j.ad[i].a = ji.util.d.c(nextToken2, 1);
                        ji.font.j.ad[i].c = nextToken3;
                        ji.font.j.ad[i].b = 84;
                        if (countTokens > 3) {
                            ji.font.j.ad[i].b = ji.util.d.c(stringTokenizer.nextToken(), 1);
                        }
                        if (countTokens > 4) {
                            ji.font.j.ad[i].d = ji.util.d.b(stringTokenizer.nextToken(), false);
                        }
                        if (countTokens > 5) {
                            ji.font.j.ad[i].e = ji.util.d.b(stringTokenizer.nextToken(), false);
                        }
                        if (countTokens > 6) {
                            ji.font.j.ad[i].f = ji.util.d.b(stringTokenizer.nextToken(), true);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final synchronized int a(final Component component, final af af, final String s, final ct ct) {
        int n = -1;
        String lowerCase = ct.c.toLowerCase();
        if (lowerCase.toLowerCase().startsWith("system")) {
            lowerCase = "arial";
        }
        Label_0749: {
            try {
                c(component, af, s);
                for (int i = 0; i < ji.font.j.ad.length; ++i) {
                    if (ji.font.j.ad[i].c.toLowerCase().equals(lowerCase) && ji.font.j.ad[i].b == ct.b && ji.font.j.ad[i].d == ct.d && ji.font.j.ad[i].e == ct.e && ji.font.j.ad[i].f == ct.f) {
                        n = i;
                        break;
                    }
                }
                if (n < 0) {
                    for (int j = 0; j < ji.font.j.ad.length; ++j) {
                        if (ji.font.j.ad[j].c.toLowerCase().equals(lowerCase) && ji.font.j.ad[j].b == ct.b) {
                            n = j;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int k = ji.font.j.ad.length - 1; k >= 0; --k) {
                        if (ji.font.j.ad[k].c.toLowerCase().equals(lowerCase) && ji.font.j.ad[k].b > ct.b && ji.font.j.ad[k].d == ct.d && ji.font.j.ad[k].e == ct.e && ji.font.j.ad[k].f == ct.f) {
                            n = k;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int l = ji.font.j.ad.length - 1; l >= 0; --l) {
                        if (ji.font.j.ad[l].c.toLowerCase().equals(lowerCase) && ji.font.j.ad[l].b > ct.b) {
                            n = l;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int n2 = 0; n2 < ji.font.j.ad.length; ++n2) {
                        if (ji.font.j.ad[n2].c.toLowerCase().equals(lowerCase)) {
                            n = n2;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int n3 = 0; n3 < ji.font.j.ad.length; ++n3) {
                        if (ji.font.j.ad[n3].c.toLowerCase().startsWith(lowerCase) && ji.font.j.ad[n3].b == ct.b) {
                            n = n3;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int n4 = ji.font.j.ad.length - 1; n4 >= 0; --n4) {
                        if (ji.font.j.ad[n4].c.toLowerCase().startsWith(lowerCase) && ji.font.j.ad[n4].b > ct.b) {
                            n = n4;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int n5 = 0; n5 < ji.font.j.ad.length; ++n5) {
                        if (ji.font.j.ad[n5].c.toLowerCase().startsWith(lowerCase)) {
                            n = n5;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    for (int n6 = ji.font.j.ad.length - 1; n6 >= 0; --n6) {
                        if (lowerCase.startsWith(ji.font.j.ad[n6].c.toLowerCase()) && ji.font.j.ad[n6].b > ct.b) {
                            n = n6;
                            break;
                        }
                    }
                }
                if (n < 0) {
                    Block_45: {
                        for (int n7 = 0; n7 < ji.font.j.ad.length; ++n7) {
                            if (lowerCase.startsWith(ji.font.j.ad[n7].c.toLowerCase())) {
                                break Block_45;
                            }
                        }
                        break Label_0749;
                    }
                    int n7 = 0;
                    n = n7;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (n < 0) {
            n = 0;
        }
        return n;
    }
    
    public static final synchronized ct g(final int n) {
        return ji.font.j.ad[n];
    }
    
    private static final String k(final String s) {
        final int index = s.indexOf("_");
        String substring;
        if (index >= 0) {
            substring = s.substring(index + 1);
        }
        else {
            substring = s;
        }
        return substring;
    }
    
    private static final synchronized String d(final Component component, final af af, final String s) throws Exception {
        c(component, af, s);
        if (ji.font.j.ac != null) {
            try {
                return ji.font.j.ac[ji.font.j.af];
            }
            catch (Exception ex) {
                return "font1.txt";
            }
        }
        return "font1.txt";
    }
    
    private static final synchronized d1 a(final Component component, final String s, final String s2, final af af, boolean b, final String s3, final ct ct, double n, final int n2, final boolean b2) throws Exception {
        String value = null;
        ji.font.j.aa = null;
        ji.font.j.z = null;
        ji.font.j.ab = -1;
        if (ct.b()) {
            b = true;
        }
        String concat;
        if (b) {
            concat = "Dynamic".concat(String.valueOf(String.valueOf(s)));
        }
        else {
            concat = s;
        }
        final String concat2 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(ct)));
        d1 a = a(s3, concat2);
        if (a != null) {
            a.n();
            ji.font.j.aa = a.c();
            ji.font.j.z = a.d();
            ji.font.j.ab = a.e();
        }
        else {
            try {
                if (af != null && (!ji.util.i.c(247) || !b2)) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(710, s3)))).append(" ").append(ji.util.d.b(756, s3)).append("\n").append(ji.util.d.b(755, s3)).append("...")));
                    af.a(new a6(component, 1, value));
                }
                ji.util.e.a(true, 756, af, component);
                if (concat == null && s2 == null) {
                    return null;
                }
                ct ct2 = null;
                if (!ct.b() && ji.font.j.ad != null) {
                    ct2 = ji.font.j.ad[b(component, s, af, s3)];
                }
                if (ct2 != null) {
                    n = ct.b / ct2.b;
                }
                if (b) {
                    a = new d1(component, concat, ji.util.d.ev(), null, ct.b, n, ct.c, ct.b, s3, ct);
                    ji.font.j.aa = a.c();
                    ji.font.j.z = a.d();
                    ji.font.j.ab = a.e();
                    a.n();
                    a(s3, concat2, a);
                }
                else {
                    final byte[] a2 = ji.res.z.a(component, concat, s2, af, null, s3, new y());
                    final String es = ji.util.d.es();
                    if (a2 != null) {
                        try {
                            a = new d1(component, concat, ji.util.d.ev(), a2, n, ct.c, ct.b, s3, ct);
                            ji.font.j.aa = concat;
                            ji.font.j.z = ji.font.j.ad[n2].c;
                            ji.font.j.ab = ji.font.j.ad[n2].b;
                            a.a(ji.font.j.z);
                            a.a(ji.font.j.ab);
                            a.n();
                            a(s3, concat2, a);
                        }
                        catch (Exception ex) {}
                    }
                    else {
                        ji.util.d.a(s2, concat, es, s3);
                    }
                }
            }
            finally {
                if (value != null && af != null && (!ji.util.i.c(247) || !b2)) {
                    af.a(new a6(component, 1, ""));
                }
                ji.util.e.a(false, 0, af, component);
            }
        }
        return a;
    }
    
    public static final synchronized String a(final Component component, final af af, final String s) throws Exception {
        String concat = "";
        c(component, af, s);
        for (int i = 0; i < ji.font.j.ad.length; ++i) {
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.font.j.ad[i].c))).append(", ").append(ji.font.j.ad[i].a))));
            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("\n").append(ji.font.j.ad[i].c).append(", ").append(ji.font.j.ad[i].a))))));
        }
        return concat;
    }
    
    public static final void a(final double ai) {
        ji.font.j.ai = ai;
    }
    
    public static final double t() {
        return ji.font.j.ai;
    }
    
    public static final d1 a(final Component component, final String s, final af af, final boolean b, final String s2, final ct ct, final double n, final int n2) throws Exception {
        return a(component, s, ji.util.d.b(s, ji.util.d.bh(s), "v1"), af, b, s2, ct, n, n2, true);
    }
    
    public static final void f(final String s) {
        a(s, true);
    }
    
    public static final void a(final String s, final boolean b) {
        if ((!ji.util.i.c(247) || !b) && s.i(s)) {
            synchronized (ji.font.j.ae) {
                final Object a = ji.font.j.ae.a(s);
                if (a != null && a instanceof Hashtable) {
                    final Enumeration<d1> elements = ((Hashtable<K, d1>)a).elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().k();
                    }
                }
            }
            // monitorexit(j.ae)
        }
    }
    
    private static final String a(final String s, final boolean b, final String s2) {
        int n = s.lastIndexOf("/");
        if (n < 0) {
            n = s.lastIndexOf("\\");
        }
        String s3;
        if (n >= 0) {
            final String substring = s.substring(0, n);
            final String substring2 = s.substring(n + 1);
            if (b) {
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(substring))).append(k(s2)).append("_").append(substring2)));
            }
            else {
                s3 = String.valueOf(String.valueOf(substring)).concat(String.valueOf(String.valueOf(substring2)));
            }
        }
        else if (b) {
            s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(k(s2)))).append("_").append(s)));
        }
        else {
            s3 = s;
        }
        return s3;
    }
    
    public static final synchronized int a(final Component component, final ct ct, final af af, final String s) throws Exception {
        c(component, af, s);
        a(component, af, s, ct);
        return ji.font.j.ad[a(component, af, s, ct)].a;
    }
    
    public static final int u() {
        return ji.font.j.ag;
    }
    
    public static final synchronized String b(final Component component, final ct ct, final af af, final String s) throws Exception {
        c(component, af, s);
        final int a = a(component, af, s, ct);
        if (ji.font.j.ac != null) {
            try {
                ji.font.j.ag = a;
                return a(ji.font.j.ac[a], ji.font.j.ah, s.a);
            }
            catch (Exception ex) {
                ji.font.j.ag = ji.font.j.af;
                return a(d(component, af, s), ji.font.j.ah, s.a);
            }
        }
        ji.font.j.ag = ji.font.j.af;
        return a(d(component, af, s), ji.font.j.ah, s.a);
    }
    
    private static final synchronized int b(final Component component, final String s, final af af, final String s2) throws Exception {
        c(component, af, s2);
        int n = 0;
        if (ji.font.j.ac != null) {
            for (int i = 0; i < ji.font.j.ac.length; ++i) {
                if (a(ji.font.j.ac[i], ji.font.j.ah, s.a).toLowerCase().equals(s)) {
                    n = i;
                    break;
                }
            }
        }
        return n;
    }
    
    private static final synchronized d1 a(final Component component, ct ct, final af af, final boolean b, final String s, final double n, final boolean b2) throws Exception {
        d1 a = null;
        if (ct == null) {
            ct = j();
        }
        else if (ji.util.d.by(ct.c)) {
            ct = j();
        }
        if (!ji.util.i.c(247) || !b2) {
            c(component, af, s);
        }
        ji.font.j.aa = null;
        ji.font.j.z = null;
        ji.font.j.ab = -1;
        if (ji.font.j.ac == null) {
            if (!ji.util.i.c(247) || !b2) {
                return a;
            }
        }
        try {
            String b3 = null;
            if (!ji.util.i.c(247) || !b2) {
                b3 = b(component, ct, af, s);
            }
            a = a(component, b3, af, b, s, ct, n, ji.font.j.ag);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return a;
    }
    
    public static final int h(final int n) {
        if (n == 36) {
            return n;
        }
        return 72;
    }
    
    public static final ct a(final String s, final int n) {
        return new ct(s, n, true, false, false);
    }
    
    public static final ct a(final String s, final int n, final Font font) {
        return new ct(s, n, true, false, false, font);
    }
    
    public static final ct g(final String s) {
        return new ct(s, ji.font.j.q, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static final ct v() {
        return new ct(ji.font.j.k, ji.font.j.t, ji.font.j.m, ji.font.j.n, ji.font.j.o);
    }
    
    public static final ct w() {
        return c();
    }
    
    public static final ct x() {
        return d();
    }
    
    public static final ct y() {
        return f();
    }
    
    public static final int z() {
        if (ji.font.j.v) {
            return ji.font.j.w;
        }
        if (ji.util.d.bf()) {
            return ji.font.j.s;
        }
        return ji.font.j.t;
    }
    
    public static final int aa() {
        if (ji.font.j.v) {
            return ji.font.j.x;
        }
        if (ji.util.d.bf()) {
            return ji.font.j.s;
        }
        return ji.font.j.u;
    }
    
    public static final boolean a(final ct ct) {
        return ct.a(a((Font)null));
    }
    
    public static final ct a(final ad ad, final af af, final String s, final String s2) {
        return a(ad, af, s, s2, true);
    }
    
    public static final ct a(final ad ad, final af af, final String s, final String s2, final boolean b) {
        if (ji.util.i.c(247) && b) {
            return g(s2);
        }
        return g(a(ad, af, s, g(s2)));
    }
    
    public static final ct b(final boolean b) {
        if (ji.util.d.bf()) {
            return e();
        }
        if (b) {
            return b();
        }
        return f();
    }
    
    public static final int c(final boolean b) {
        if (b) {
            return aa();
        }
        return z();
    }
    
    public static final void a(final String s, final Component component, final String s2, final int n, final int n2, final int n3, final Font font, final boolean b, final boolean b2, final int[] array, final int n4, final int n5, final int n6, final dw dw, final int n7, final af af) {
        if (ji.util.e.av()) {
            b(s, component, s2, n, n2, n3, font, b, b2, array, n4, n5, n6, dw, n7, af);
        }
        else {
            a(s, component, s2, n, n2, n3, font, b, b2, array, dw, n7);
        }
    }
    
    public static final void ab() {
        if (ji.util.e.av()) {
            ae();
        }
        else {
            af();
        }
    }
    
    public static final void b(final String s, final boolean b) {
        try {
            if (ji.font.j.ae != null) {
                synchronized (ji.font.j.ae) {
                    if (ji.font.j.ae.b(s)) {
                        final Object a = ji.font.j.ae.a(s);
                        if (a instanceof Hashtable) {
                            final Hashtable<K, d1> hashtable = (Hashtable<K, d1>)a;
                            final Enumeration<d1> elements = hashtable.elements();
                            while (elements.hasMoreElements()) {
                                final d1 d1 = elements.nextElement();
                                d1.j();
                                if (b) {
                                    d1.m();
                                }
                            }
                            if (b) {
                                hashtable.clear();
                            }
                        }
                        if (b) {
                            ji.font.j.ae.remove(s);
                        }
                    }
                }
                // monitorexit(j.ae)
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        ab();
    }
    
    private static final void ad() throws Exception {
        if (ji.font.j.h == null) {
            ji.font.j.g = ji.util.d.a2("ji.font.jiJava2FontUtils");
            ji.font.j.h = new m(ji.font.j.g);
        }
    }
    
    private static final void ae() {
        try {
            if (ji.font.j.h != null) {
                ji.font.j.h.c("releaseTempMemory");
                ji.font.j.h.b();
                ji.font.j.g = null;
                ji.font.j.h = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static final void af() {
        if (ji.font.j.b != null) {
            ji.font.j.b = null;
            ji.font.j.c = 0;
            ji.font.j.d = 0;
        }
    }
    
    private static final void b(final String s, final Component component, final String s2, final int n, final int n2, final int n3, final Font font, final boolean b, final boolean b2, final int[] array, final int n4, final int n5, final int n6, final dw dw, final int n7, final af af) {
        try {
            ad();
            final Object[] array2 = new Object[16];
            int n8 = 0;
            array2[n8++] = s;
            array2[n8++] = component;
            array2[n8++] = s2;
            array2[n8++] = new Integer(n);
            array2[n8++] = new Integer(n2);
            array2[n8++] = new Integer(n3);
            array2[n8++] = font;
            array2[n8++] = new Boolean(b);
            array2[n8++] = new Boolean(b2);
            array2[n8++] = array;
            array2[n8++] = new Integer(n4);
            array2[n8++] = new Integer(n5);
            array2[n8++] = new Integer(n6);
            array2[n8++] = dw;
            array2[n8++] = new Integer(n7);
            array2[n8++] = af;
            ji.font.j.h.a("drawString", array2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static final void a(final String s, final Component component, final String s2, final int c, final int d, final int n, final Font font, final boolean b, final boolean b2, final int[] array, final dw dw, final int n2) {
        try {
            boolean b3 = false;
            if (ji.font.j.b == null) {
                b3 = true;
            }
            else {
                if (ji.font.j.c < c) {
                    b3 = true;
                }
                if (ji.font.j.d < d) {
                    b3 = true;
                }
            }
            if (b3) {
                if (ji.font.j.b != null) {
                    ji.font.j.b.flush();
                    ji.font.j.b = null;
                }
                ji.font.j.b = component.createImage(c, d);
                if (!d.em() && ji.font.j.b == null && !component.isDisplayable()) {
                    ji.font.j.b = a(c, d, 1);
                }
                ji.font.j.c = c;
                ji.font.j.d = d;
            }
            final Graphics graphics = ji.font.j.b.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, c, d);
            graphics.setColor(Color.black);
            graphics.setFont(font);
            graphics.drawString(s2, 0, 80 * n / 100 - 2);
            d.a(component, ji.font.j.b);
            new PixelGrabber(ji.font.j.b, 0, 0, c, d, array, 0, c).grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Image a(final int n, final int n2, final int n3) {
        if (ji.util.e.av()) {
            return b(n, n2, n3);
        }
        return null;
    }
    
    private static Image b(final int n, final int n2, final int n3) {
        Image image = null;
        try {
            ad();
            final Object a = ji.font.j.h.a("getBufferedImage", new Integer(n), new Integer(n2), new Integer(n3));
            if (a != null) {
                image = (Image)a;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return image;
    }
    
    public static String[] a(final Component component) {
        if (ji.util.e.av()) {
            return ag();
        }
        return b(component);
    }
    
    private static String[] ag() {
        String[] array = null;
        try {
            ad();
            final Object c = ji.font.j.h.c("getFontFamilyNames");
            if (c != null) {
                array = (String[])c;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }
    
    public static final boolean ac() {
        return ji.font.j.e;
    }
    
    public static final String a(final String s, String s2, final Component component) {
        ji.font.j.e = false;
        String s3;
        s2 = (s3 = ji.util.d.bc(s2));
        final int n = 3 * (2 * s2.length() / 4);
        final int n2 = 1 * (2 * s2.length() / 3);
        if (ji.util.d.cs()) {
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Matching font '").append(s2).append("'..."))));
        }
        synchronized (ji.font.j.at) {
            if (ji.font.j.aq != null && ji.font.j.aq.equals(s2.toLowerCase())) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Using previous match '").append(ji.font.j.ar).append("'..."))));
                }
                ji.font.j.e = true;
                // monitorexit(j.at)
                return ji.font.j.ar;
            }
            c(component);
            if (ji.font.j.aj != null) {
                String ar2 = b(s2, n);
                if (ar2 == null) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "FONT: Match not found, looking for alternative name...");
                    }
                    String s4 = l(s2);
                    if (s4 == null) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(s, "FONT: Alternative name not found, checking for multiple words..");
                        }
                        if (s2.indexOf(32) >= 0) {
                            final String bc = ji.util.d.bc(s2.substring(0, s2.indexOf(32)));
                            if (ji.util.d.cs()) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Found multiple words, trying first word '").append(bc).append("'..."))));
                            }
                            ar2 = b(bc, n2);
                            if (ar2 == null) {
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(s, "FONT: Match not found on first word, looking for alternative name for first word...");
                                }
                                s4 = d(bc, true);
                                if (s4 != null) {
                                    if (ji.util.d.cs()) {
                                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Alternative name '").append(s4).append("' found, trying for match on this..."))));
                                    }
                                    ar2 = b(s4, n2);
                                }
                                else if (ji.util.d.cs()) {
                                    ji.io.h.d(s, "FONT: Alternative name not found...");
                                }
                            }
                            else if (ji.util.d.cs()) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Match '").append(ar2).append("' found on first word..."))));
                            }
                        }
                        if (s4 == null && ar2 == null) {
                            if (ji.util.d.cs()) {
                                ji.io.h.d(s, "FONT: Looking for alternative name using looser criteria...");
                            }
                            s4 = d(s2, true);
                            if (s4 != null) {
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Found alternative '").append(s4).append("'..."))));
                                }
                            }
                            else if (ji.util.d.cs()) {
                                ji.io.h.d(s, "FONT: Alternative still not found...");
                            }
                        }
                    }
                    if (s4 != null && ar2 == null) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Looking for match on first alternative '").append(ji.font.j.as).append("'..."))));
                        }
                        ar2 = b(ji.font.j.as, n);
                        if (ar2 == null) {
                            if (ji.util.d.cs()) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Not found match, looking for match on second alternative '").append(s4).append("'..."))));
                            }
                            ar2 = b(s4, n);
                            if (ar2 == null) {
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Not Found match '").append(ar2).append("' for '").append(s4).append("'..."))));
                                }
                            }
                            else if (ji.util.d.cs()) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Found match '").append(ar2).append("' for second alternative..."))));
                            }
                        }
                        else if (ji.util.d.cs()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Found match '").append(ar2).append("' for '").append(ji.font.j.as).append("'..."))));
                        }
                    }
                    if (ar2 == null) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Not found match, so looking for looser match on '").append(s2).append("'..."))));
                        }
                        ar2 = b(s2, n2);
                        if (ar2 != null) {
                            if (ji.util.d.cs()) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Found match '").append(ar2).append("' for '").append(s2).append("'..."))));
                            }
                        }
                        else if (ji.util.d.cs()) {
                            ji.io.h.d(s, "FONT: Not found match...");
                        }
                    }
                }
                else if (ji.util.d.cs()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Found match '").append(ar2).append("'..."))));
                }
                if (ar2 != null) {
                    ji.font.j.ar = ar2;
                    s3 = ji.font.j.ar;
                    ji.font.j.aq = s2.toLowerCase();
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Matched '").append(s3).append("' for '").append(s2).append("'...continuing now..."))));
                    }
                }
                else {
                    final String g = g();
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Looking for match on default '").append(g).append("'..."))));
                    }
                    final String b = b(g, n);
                    if (b != null) {
                        s3 = b;
                        if (ji.util.d.cs()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Matched '").append(s3).append("' for default '").append(g).append("'...continuing now..."))));
                        }
                    }
                    else if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FONT: Failed to match '").append(s2).append("' so leaving as-is..."))));
                    }
                }
            }
            else {
                ji.io.h.d(s, "FONT: ** Error, empty font list...!...");
            }
        }
        // monitorexit(j.at)
        return s3;
    }
    
    private static void c(final Component component) {
        if (ji.font.j.aj == null) {
            ji.font.j.aj = a(component);
            if (ji.font.j.aj != null) {
                ji.font.j.an = 0;
                ji.font.j.ao = ji.font.j.aj.length;
                ji.font.j.al = new char[ji.font.j.ao][0];
                ji.font.j.am = new int[ji.font.j.ao];
                ji.font.j.ak = new String[ji.font.j.ao];
                for (int i = 0; i < ji.font.j.ao; ++i) {
                    ji.font.j.an = Math.max(ji.font.j.an, 1 + 2 * ji.font.j.aj[i].length());
                    ji.font.j.ak[i] = ji.font.j.aj[i].toLowerCase();
                    ji.font.j.al[i] = ji.font.j.ak[i].toCharArray();
                    ji.font.j.am[i] = ji.font.j.aj[i].length() * 2;
                }
            }
        }
    }
    
    private static String l(final String s) {
        final String lowerCase = s.toLowerCase();
        for (int i = 0; i < r8.a.length; i += 2) {
            if (r8.a[i + 1].equals(lowerCase)) {
                ji.font.j.as = r8.a[i + 1];
                return r8.a[i];
            }
        }
        return null;
    }
    
    private static String b(final String s, final int n) {
        final String lowerCase = s.toLowerCase();
        final char[] charArray = lowerCase.toCharArray();
        ji.font.j.ap = -1;
        ji.font.j.ap = -1;
        int n2 = -1;
        final int n3 = s.length() * 2;
        for (int i = 0; i < ji.font.j.ao; ++i) {
            if (ji.font.j.ak[i].equals(lowerCase)) {
                ji.font.j.e = true;
                ji.font.j.ap = ji.font.j.an;
                return ji.font.j.aj[i];
            }
            int ap = 0;
            final char[] array = ji.font.j.al[i];
            final int min = Math.min(charArray.length, array.length);
            int n4 = -1;
            for (int j = 0; j < min; ++j) {
                final char c = charArray[j];
                if (c == array[j]) {
                    ap += 2;
                }
                else {
                    if (n4 < 0) {
                        n4 = j;
                    }
                    for (int k = n4; k < array.length; ++k) {
                        if (c == array[k]) {
                            ++ap;
                            n4 = k + 1;
                            break;
                        }
                    }
                }
            }
            if (ji.font.j.ap < ap) {
                ji.font.j.ap = ap;
                n2 = i;
            }
        }
        final int n5 = 3 * (2 * s.length() / 4);
        if (n > 0 && ji.font.j.ap < n) {
            return null;
        }
        if (n2 >= 0) {
            return ji.font.j.aj[n2];
        }
        return null;
    }
    
    private static String d(final String s, final boolean b) {
        final char[] charArray = s.toLowerCase().toCharArray();
        ji.font.j.ap = -1;
        ji.font.j.ap = -1;
        int n = -1;
        final int length = s.length();
        final int n2 = length * 2;
        for (int length2 = r8.a.length, i = 0; i < length2; i += 2) {
            int ap = 0;
            final char[] charArray2 = r8.a[i + 1].toCharArray();
            final int min = Math.min(charArray.length, charArray2.length);
            int n3 = -1;
            for (int j = 0; j < min; ++j) {
                final char c = charArray[j];
                if (c == charArray2[j]) {
                    ap += 2;
                }
                else {
                    if (n3 < 0) {
                        n3 = j;
                    }
                    for (int k = n3; k < charArray2.length; ++k) {
                        if (c == charArray2[k]) {
                            ++ap;
                            n3 = k + 1;
                            break;
                        }
                    }
                }
            }
            if (ap == n2 && length == charArray2.length) {
                ji.font.j.ap = ji.font.j.an;
                ji.font.j.as = r8.a[i + 1];
                return r8.a[i];
            }
            if (ji.font.j.ap < ap) {
                ji.font.j.ap = ap;
                n = i;
            }
        }
        final int n4 = 3 * (2 * s.length() / 4);
        if (b) {
            if (ji.font.j.ap < n4) {
                return null;
            }
        }
        else if (ji.font.j.ap < s.length()) {
            return null;
        }
        if (n >= 0) {
            ji.font.j.as = r8.a[n + 1];
            return r8.a[n];
        }
        return null;
    }
    
    public static String[] b(final Component component) {
        try {
            final String[] fontList = component.getToolkit().getFontList();
            final Vector vector = new Vector<String>(fontList.length);
            for (int i = 0; i < fontList.length; ++i) {
                final String s = fontList[i];
                if (!s.toLowerCase().startsWith("dialog")) {
                    vector.addElement(s);
                }
            }
            final int size = vector.size();
            final String[] array = new String[size];
            for (int j = 0; j < size; ++j) {
                array[j] = vector.elementAt(j);
            }
            return array;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static int a(final Component component, final Font font, final String s) {
        if (ji.util.e.av()) {
            return b(component, font, s);
        }
        return c(component, font, s);
    }
    
    private static int b(final Component component, final Font font, final String s) {
        int intValue = 2;
        try {
            ad();
            final Object a = ji.font.j.h.a("getActualFontWidth", component, font, s);
            if (a != null) {
                intValue = (int)a;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return intValue;
    }
    
    public static Font a(final String s, final int n, final int n2, final Object o, final String s2) {
        Font font = null;
        try {
            ad();
            final Object a = ji.font.j.h.a("createFontFromTTF", s, new Integer(n), new Integer(n2), o, s2);
            if (a != null) {
                font = (Font)a;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return font;
    }
    
    private static int c(final Component component, final Font font, final String s) {
        final Graphics create = component.getGraphics().create();
        create.setFont(font);
        return create.getFontMetrics().stringWidth(s);
    }
    
    private static final void a(final String s, final String s2, final d1 d1) {
        synchronized (ji.font.j.ae) {
            Hashtable<String, d1> hashtable = null;
            if (ji.font.j.ae.b(s)) {
                hashtable = (Hashtable<String, d1>)ji.font.j.ae.a(s);
            }
            if (hashtable == null) {
                hashtable = new Hashtable<String, d1>();
                ji.font.j.ae.a(s, hashtable);
            }
            hashtable.put(s2, d1);
        }
        // monitorexit(j.ae)
    }
    
    private static final d1 a(final String s, final String s2) {
        synchronized (ji.font.j.ae) {
            Hashtable<K, d1> hashtable = null;
            if (ji.font.j.ae.b(s)) {
                hashtable = (Hashtable<K, d1>)ji.font.j.ae.a(s);
            }
            if (hashtable == null) {
                // monitorexit(j.ae)
                return null;
            }
            // monitorexit(j.ae)
            return hashtable.get(s2);
        }
    }
    
    public static final void c(final String s, final boolean b) {
        try {
            b(s, b);
        }
        catch (Exception ex) {}
    }
    
    public static final String[] h(final String s) {
        String s2;
        if (ji.util.d.eg()) {
            s2 = ji.res.z.c(s);
        }
        else {
            s2 = ji.util.e.an();
        }
        String[] h = ji.io.ac.h(s2, s);
        if (h != null) {
            final int length = h.length;
            final Vector vector = new Vector<String>(length);
            for (int i = 0; i < length; ++i) {
                if (i(h[i])) {
                    vector.addElement(h[i]);
                }
            }
            final int size = vector.size();
            if (size > 0) {
                h = new String[size];
                for (int j = 0; j < size; ++j) {
                    h[j] = vector.elementAt(j);
                }
            }
            else {
                h = null;
            }
            while (vector.size() > 0) {
                vector.removeElementAt(0);
            }
        }
        return h;
    }
    
    public static final boolean i(final String s) {
        return ji.util.d.bh(s).toLowerCase().equals("ttf");
    }
    
    public static byte[] j(final String s) {
        try {
            if (!ji.font.j.a0) {
                final y y = new y();
                if (ji.font.j.a1 == null) {
                    ji.font.j.a1 = ji.res.z.a("fonts.txt", ji.util.e.an(), 3, null, null, s, y);
                }
                if (ji.font.j.a1 == null) {
                    ji.font.j.a0 = true;
                }
            }
        }
        catch (Exception ex) {
            ji.font.j.a0 = true;
            ex.printStackTrace();
            return null;
        }
        return ji.font.j.a1;
    }
    
    public static final void a(final af af, final Component component, final String s) {
        if (ji.util.d.eg()) {
            final byte[] j = j(s);
            try {
                if (j != null) {
                    final String s2 = new String(j);
                    final Vector vector = new Vector<String>();
                    if (s2.length() > 0) {
                        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(j);
                        final InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
                        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        for (String s3 = ji.util.d.c(bufferedReader.readLine(), true); s3 != null; s3 = ji.util.d.c(bufferedReader.readLine(), true)) {
                            if (s3.length() > 0) {
                                vector.addElement(s3);
                            }
                        }
                        bufferedReader.close();
                        inputStreamReader.close();
                        byteArrayInputStream.close();
                    }
                    if (vector.size() > 0) {
                        final String concat = String.valueOf(String.valueOf(ji.util.d.b(1285, s))).concat("...");
                        while (vector.size() > 0) {
                            final String s4 = vector.elementAt(0);
                            vector.removeElementAt(0);
                            if (ji.res.z.a(component, s4, null, af, s, component) == null) {
                                if (ji.util.i.c(253)) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FontDialog: '").append(s4).append("' not in repository so downloading..."))));
                                }
                                ji.util.e.ag(concat);
                                if (af != null) {
                                    af.a(new a6(component, 9, concat));
                                }
                                if (ji.res.z.a(new URL(ji.util.e.am(), s4), s4, component, s, af) == null) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FontDialog: Unable to download font '").append(s4).append("'"))));
                                }
                                else {
                                    if (!ji.util.i.c(253)) {
                                        continue;
                                    }
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("FontDialog: Downloaded '").append(s4).append("'."))));
                                }
                            }
                        }
                    }
                    else {
                        ji.io.h.d(s, "FontDialog: fonts.txt not found, empty or does not contain valid font filenames");
                    }
                    if (af != null) {
                        af.a(new a6(component, 10, ""));
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String a(final Component component, final String s, final af af, final String s2) throws Exception {
        String s3 = ji.res.z.a(component, s, null, af, s2, component);
        if (s3 == null) {
            if (ji.util.d.eg()) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("TTF file specified: '").append(s).append("'...downloading..."))));
                }
                s3 = ji.res.z.a(new URL(ji.util.e.am(), s), s, component, s2, af);
            }
            else {
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.e.an()))).append("/").append(s)));
            }
        }
        return s3;
    }
    
    public static byte[] a(final int n, final int n2, final int[] array, final int n3) {
        final byte[] array2 = new byte[n * n2];
        for (int i = 0; i < n2; ++i) {
            final int n4 = i * n;
            for (int j = 0; j < n; ++j) {
                if (array[j + n4] == ji.font.j.f) {
                    array2[j + n4] = 0;
                }
                else if (array[j + n4] == n3) {
                    array2[j + n4] = -56;
                }
                else {
                    array2[j + n4] = 1;
                }
            }
        }
        return array2;
    }
    
    static {
        ji.font.j.a = new Object();
        ji.font.j.b = null;
        ji.font.j.c = 0;
        ji.font.j.d = 0;
        ji.font.j.e = false;
        ji.font.j.f = Color.white.getRGB();
        ji.font.j.g = null;
        ji.font.j.h = null;
        ji.font.j.i = "courier";
        ji.font.j.j = "Arial";
        ji.font.j.k = "arial";
        ji.font.j.l = "arial black";
        ji.font.j.m = true;
        ji.font.j.n = false;
        ji.font.j.o = false;
        ji.font.j.p = 84;
        ji.font.j.q = 84;
        ji.font.j.r = 48;
        ji.font.j.s = 12;
        ji.font.j.t = 28;
        ji.font.j.u = 48;
        ji.font.j.v = false;
        ji.font.j.w = -1;
        ji.font.j.x = -1;
        ji.font.j.y = 0;
        ji.font.j.z = null;
        ji.font.j.aa = null;
        ji.font.j.ab = -1;
        ji.font.j.ac = null;
        ji.font.j.ad = null;
        ji.font.j.ae = new vd((aet)null);
        ji.font.j.af = 0;
        ji.font.j.ag = ji.font.j.af;
        ji.font.j.ah = false;
        ji.font.j.ai = 0.0;
        ji.font.j.aj = null;
        ji.font.j.ak = null;
        ji.font.j.al = null;
        ji.font.j.am = null;
        ji.font.j.an = 0;
        ji.font.j.ao = 0;
        ji.font.j.ap = -1;
        ji.font.j.aq = null;
        ji.font.j.ar = null;
        ji.font.j.as = null;
        ji.font.j.at = new Object();
        ji.font.j.au = ji.font.j.k;
        ji.font.j.av = false;
        ji.font.j.aw = 10;
        ji.font.j.ax = ji.font.j.k;
        ji.font.j.ay = 10;
        ji.font.j.az = -1;
        ji.font.j.a0 = false;
        ji.font.j.a1 = null;
    }
    
    private static class vd extends Hashtable
    {
        public synchronized Object a(final String s) {
            String a = ji.document.ad.a(s);
            if (a == null) {
                a = s;
            }
            return super.get(a);
        }
        
        public synchronized Object a(final String s, final Object o) {
            String a = ji.document.ad.a(s);
            if (a == null) {
                a = s;
            }
            return super.put(a, o);
        }
        
        public synchronized boolean b(final String s) {
            String a = ji.document.ad.a(s);
            if (a == null) {
                a = s;
            }
            return super.containsKey(a);
        }
    }
    
    interface aet
    {
    }
}
