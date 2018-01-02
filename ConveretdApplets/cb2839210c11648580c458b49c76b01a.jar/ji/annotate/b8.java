// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import java.awt.Rectangle;
import ji.util.eu;
import java.util.Vector;
import ji.io.q;
import ji.zip.a4;
import java.util.Enumeration;
import ji.awt.ax;
import ji.image.dx;
import java.io.FileNotFoundException;
import java.awt.Component;
import ji.io.ac;
import ji.v1event.ao;
import ji.util.i;
import ji.net.a0;
import ji.util.e;
import ji.io.h;
import ji.awt.c;
import ji.document.ad;
import ji.v1event.af;
import java.net.URL;
import ji.util.d;
import java.util.Hashtable;

public final class b8
{
    private static String a;
    private static String b;
    private static dy c;
    private static Object d;
    private static boolean e;
    private static Hashtable f;
    
    public static final String a() {
        return b8.a;
    }
    
    public static final String b() {
        return b8.b;
    }
    
    private static final void a(final String s, final int n, final boolean b, final String s2) {
        try {
            if (b8.c == null) {
                b8.c = new dy(s, n, b, s2);
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    private static final void c() {
        try {
            if (b8.c != null) {
                b8.c.v();
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean a(final URL url, final boolean b, final boolean b2, final int n, final boolean b3, final String s, final boolean b4, final af af, final ad ad, final String s2, final int n2, final boolean b5, final String s3, final String s4, final c c, final df df, final boolean b6, final boolean b7, final c c2, final boolean b8, final boolean b9) throws Exception {
        a0 a0 = null;
        b8.a = null;
        b8.b = null;
        final URL url2 = (URL)a(url, n, ji.util.d.a(ad));
        if (ji.util.d.cs()) {
            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Loading annotations file(URL) :  <").append(url2).append(">"))));
        }
        if (url != null) {
            try {
                final boolean b10 = ji.util.d.a(ad) && c2 != null && !b8;
                ji.util.e.a(false, null);
                ji.util.e.ag(null);
                b8.a = ji.util.d.h(url.toString(), s2);
                a0 = new a0(ad, s2);
                a0.b(af);
                final String a2 = a0.a(url2, true, b3, s, null, null, false, ad, af, i.c(120), ad, s4, false);
                if (a2 != null) {
                    if (ji.util.d.cs()) {
                        h.d(s2, "#3 Annotations file cached to :  ".concat(String.valueOf(String.valueOf(ji.util.d.cd(a2)))));
                    }
                    final boolean a3 = a(a2, b, b2, c, df, s4, af, ad, s2, n2, b5, s3, b6, b7, ad.aa(), b10, false);
                    Label_0289: {
                        if (b10) {
                            synchronized (b8.d) {
                                a(n, s2, ad, b);
                                // monitorexit(b8.d)
                                break Label_0289;
                            }
                            throw new Exception("Failed to retrieve annotations");
                        }
                    }
                    if (ji.util.d.cs()) {
                        h.d(s2, String.valueOf(String.valueOf(new StringBuffer("#3 Annotations parsed:  ").append(a3).append(", count = ").append(df.d()))));
                    }
                    return a3;
                }
                throw new Exception("Failed to retrieve annotations");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (!ad.a(ex) || b9) {
                    throw ex;
                }
                return false;
            }
            finally {
                if (a0 != null) {
                    if (af != null) {
                        a0.a(af);
                    }
                    a0.a();
                }
            }
        }
        return true;
    }
    
    public static final boolean a(final String s) {
        synchronized (b8.f) {
            // monitorexit(b8.f)
            return b8.f.get(s) != null;
        }
    }
    
    public static final void a(final String s, final boolean b) {
        synchronized (b8.f) {
            Label_0041: {
                try {
                    if (!b) {
                        b8.f.remove(s);
                        break Label_0041;
                    }
                    if (!a(s)) {
                        b8.f.put(s, s);
                    }
                    break Label_0041;
                }
                catch (Exception ex) {
                }
                // monitorexit(b8.f)
            }
        }
    }
    
    private static final void a(final String s, final int n, final ad ad, final String s2) {
        try {
            if (ac.d(s, s2)) {
                final int n2 = (int)ac.a(s, s2);
                if (n2 >= 0 && n2 < n) {
                    final ac ac = new ac(s, false, false, 0, ad, s2);
                    final byte[] array = new byte[n2];
                    ac.a(array);
                    ac.a(ad);
                    final ac ac2 = new ac(s, true, false, 0, ad, s2);
                    ac2.b(array);
                    ac2.b(new byte[n - n2 + 1]);
                    ac2.a(ad);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void a(final int n, final String s, final ad ad, final boolean b) {
        final String a = a(s, a(ad, b, s), ad);
        try {
            final int j8 = ad.j8();
            if (!a(a)) {
                ac.c(a, s);
                final ac ac = new ac(a, true, false, 0, ad, s);
                ac.b(new byte[Math.max(j8, n)]);
                ac.a(ad);
            }
            else {
                a(a, j8, ad, s);
            }
            final ac ac2 = new ac(a, true, false, 0, ad, s);
            ac2.a((long)(n - 1));
            ac2.a((byte)1);
            ac2.a(ad);
            a(a, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void b(final int n, final String s, final ad ad, final boolean b) {
        final String a = a(s, a(ad, b, s), ad);
        try {
            ad.j8();
            if (a(a) && ac.d(a, s)) {
                final ac ac = new ac(a, true, false, 0, ad, s);
                ac.a((long)(n - 1));
                ac.a((byte)0);
                ac.a(ad);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final boolean c(final int n, final String s, final ad ad, final boolean b) {
        boolean b2 = false;
        final String a = a(s, a(ad, b, s), ad);
        if (ac.d(a, s)) {
            try {
                if (ac.a(a, s) >= n) {
                    final ac ac = new ac(a, false, true, 102400, ad, s);
                    ac.a((long)(n - 1));
                    if (ac.i() != 0) {
                        b2 = true;
                    }
                    ac.a(ad);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return b2;
    }
    
    public static final boolean a(final String s, final boolean b, final boolean b2, final int n, final boolean b3, final String s2, final boolean b4, final af af, final ad ad, final String s3, final int n2, final boolean b5, final String s4, final String s5, final c c, final df df, final boolean b6, final boolean b7, final c c2, final boolean b8, final boolean b9, final boolean b10) throws Exception {
        b8.a = null;
        b8.b = null;
        if (ji.util.d.cs()) {
            h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Loading annotations file(local) :  <").append(s).append(">"))));
        }
        final boolean b11 = false;
        if (s != null) {
            try {
                if (ji.util.d.cs()) {
                    h.d(s3, "#3a Annotations file cached to :  ".concat(String.valueOf(String.valueOf(ji.util.d.cd(s)))));
                }
                final boolean a = a(s, b, b2, c, df, s5, af, ad, s3, n2, b5, s4, b6, b7, ad.aa(), b11, b10);
                if (b11) {
                    synchronized (b8.d) {
                        a(n, s3, ad, b);
                    }
                    // monitorexit(b8.d)
                }
                if (ji.util.d.cs()) {
                    h.d(s3, String.valueOf(String.valueOf(new StringBuffer("#3b Annotations parsed:  ").append(a).append(", count = ").append(df.d()))));
                }
                return a;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (!ad.a(ex) || b9) {
                    throw ex;
                }
                return false;
            }
        }
        return true;
    }
    
    public static Object a(final Object o, final int n, final boolean b) {
        try {
            if (n > 0 && b && o != null) {
                final String string = o.toString();
                if (ji.util.d.bj(string)) {
                    final int index = string.toLowerCase().indexOf("&page=");
                    String s;
                    if (index > 0) {
                        final int index2 = string.toLowerCase().indexOf("&", index + 1);
                        if (index2 > 0) {
                            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(string.substring(0, index)))).append("&page=").append(n).append(string.substring(index2))));
                        }
                        else {
                            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(string.substring(0, index)))).append("&page=").append(n)));
                        }
                    }
                    else {
                        s = String.valueOf(String.valueOf(string)).concat(String.valueOf(String.valueOf("&page=".concat(String.valueOf(String.valueOf(n))))));
                    }
                    return new URL(s);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return o;
    }
    
    private static final Object a(final ad ad, final boolean b, final String s) {
        if (b) {
            return ad.gi();
        }
        return ad.jf();
    }
    
    public static final boolean a(final c c, final Object o, int n, int n2, final df df, final boolean b, final String s, final int n3, final ad ad, final boolean b2, final boolean b3, final boolean b4) throws Exception {
        boolean b5 = false;
        if (n2 == 0) {
            n2 = 1;
        }
        if (n == 0) {
            n = 1;
        }
        if (n2 == -1) {
            n2 = 1;
        }
        if (n == -1) {
            n = 1;
        }
        if (i.c(145)) {
            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Download annotations for page ").append(n).append("? (url = ").append(o).append(")"))));
        }
        if (ji.util.d.a(ad) && !b) {
            final Object a = a(ad, b4, s);
            if (a == null) {
                return false;
            }
            String string = null;
            if (o != null) {
                string = o.toString();
            }
            if (df != null && c != null) {
                if (i.c(145)) {
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("Download annotations for page ").append(n).append("?"))));
                }
                if (n2 != n && b3) {
                    if (i.c(145)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("First swap annotations for page ").append(n).append("?"))));
                    }
                    a(n, a, df, s, b2, ad, ad, b);
                }
                if (df.b(n, false)) {
                    if (i.c(145)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("No need to download because we already have some annotations on page ").append(n).append("..."))));
                    }
                    return false;
                }
                boolean b6 = true;
                boolean b7 = false;
                final boolean a2 = a(n, s, ji.util.d.c1(s), ad, a, ad);
                if (a2) {
                    if (i.c(145)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("We have annotations already cached for page ").append(n).append("..."))));
                    }
                    if (a(n, a, df, s, ad, ad)) {
                        b5 = true;
                        b6 = false;
                    }
                    else if (a != null) {
                        a(df, n, b, ad, s, b4);
                        b7 = true;
                    }
                }
                if (b6) {
                    if (string != null) {
                        if (!a2 || !a(n, b, ad, s, b4) || b7) {
                            if (i.c(145)) {
                                h.d(s, String.valueOf(String.valueOf(new StringBuffer("We need to download annotations for page ").append(n).append("..."))));
                            }
                            try {
                                final URL url = new URL(string);
                                final df df2 = new df(s);
                                if (ad.a(url, false, false, n, false, df2, false, true)) {
                                    df.a(df2);
                                    a(n, df2, a, s, b2, ad, ad, b);
                                    b5 = true;
                                }
                                synchronized (b8.d) {
                                    a(n, s, ad, b4);
                                }
                                // monitorexit(b8.d)
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                                try {
                                    if (!ad.a(ex)) {
                                        ji.util.d.a(ji.util.d.b(479, s), String.valueOf(String.valueOf(new StringBuffer("\n ----- ").append(ji.util.d.b(260, s)).append(" -----").append("\n(").append(ex).append(")").append("\n").append(string))), ad, 60, null, null, s);
                                    }
                                }
                                catch (Exception ex2) {}
                                if (!ad.a(ex) && n > 1) {
                                    throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("").append(ex).append(" \n(").append(string).append(")"))));
                                }
                                return b5;
                            }
                        }
                        if (i.c(145)) {
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Already downloaded annotations for page ").append(n).append("."))));
                        }
                    }
                    else if (i.c(145)) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("Not downloading annotations for page ").append(n).append(" because no URL specified."))));
                    }
                }
            }
        }
        else if (i.c(145)) {
            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Not downloading annotations for page ").append(n).append("."))));
        }
        return b5;
    }
    
    public static final boolean a(final int n, final boolean b, final ad ad, final String s, final boolean b2) {
        try {
            if (ji.util.d.a(ad) && !b) {
                synchronized (b8.d) {
                    // monitorexit(b8.d)
                    return c(n, s, ad, b2);
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public static final void a(final df df, final int n, final boolean b, final ad ad, final String s, final boolean b2) {
        try {
            if (ji.util.d.a(ad) && !b) {
                synchronized (b8.d) {
                    b(n, s, ad, b2);
                }
                // monitorexit(b8.d)
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean a(final String s, final boolean b, final boolean b2, final c c, final df df, final String s2, final af af, final ad ad, final String s3, final int n, final boolean b3, final String s4, final boolean b4, final boolean b5, final boolean b6, final boolean b7, final boolean b8) throws Exception {
        boolean b9 = false;
        df df2 = null;
        try {
            if (!ji.util.d.by(s)) {
                if (ac.d(s, s3)) {
                    a(s3, n, b3, s4);
                    b8.c.a(c);
                    if (i.c(94) && !ji.util.d.a(ad)) {
                        ad.d(ji.util.d.b(1099, s3));
                    }
                    if (b2) {
                        df2 = b8.c.a(s, ad, af, s2, b4, b5, b8, true, false);
                        if (df2 != null) {
                            df2.c(true);
                            df.a(df2.b(ad));
                        }
                    }
                    else if (b) {
                        df2 = b8.c.a(s, ad, af, s2, b4, b5, b8, true, false);
                        if (df2 != null) {
                            df.a(df2.b(ad));
                        }
                    }
                    else {
                        df2 = b8.c.a(s, ad, af, s2, b4, b5, b8, true, false);
                        if (df2 != null) {
                            df.a(df2.b(ad));
                        }
                    }
                    b8.b = ad.gh();
                    if (ji.util.d.by(b8.b)) {
                        b9 = true;
                    }
                    else {
                        h.d(s3, "Closing document due to annotations error: ".concat(String.valueOf(String.valueOf(b8.b))));
                        b9 = false;
                    }
                }
                else if (!ad.hb() && b8.e) {
                    ji.util.d.a(ji.util.d.b(479, s3), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("\n ----- ").append(ji.util.d.b(260, s3)).append(" -----"))), ad, 60, null, af, s3);
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cs()) {
                ex.printStackTrace();
            }
            if (b7) {
                throw ex;
            }
            if (ji.util.d.cs()) {
                ji.util.d.a(ji.util.d.b(479, s3), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("\n").append(ex.toString()))), ad, 60, null, af, s3);
            }
        }
        finally {
            try {
                if (df2 != null) {
                    df2.j();
                    df2.m();
                }
            }
            catch (Exception ex2) {}
            c();
        }
        return b9;
    }
    
    public static final int a(final dg dg, final dx dx, final double n, final boolean b) {
        return a(dg.a(dx), dg, dx, n, b);
    }
    
    public static final int a(final int n, final dg dg, final dx dx, final double n2, final boolean b) {
        return dg.d(n2);
    }
    
    public static final void a(final int n, final Object o, final df df, final String s, final boolean b, final Component component, final ad ad, final boolean b2) {
        if (ji.util.d.a(ad)) {
            final boolean a = df.a();
            try {
                final ax g = df.g();
                final df df2 = new df(s);
                df2.a(true);
                df.a(true);
                final c c = new c("annotCache1");
                final c c2 = new c("annotCache2");
                final Hashtable hashtable = new Hashtable<String, String>();
                while (g.a()) {
                    final String b3 = g.b();
                    final dg b4 = df.b("".concat(String.valueOf(String.valueOf(b3))));
                    if (b4.i(n) != n && !b4.aw()) {
                        try {
                            final String concat = "0000000000000000".concat(String.valueOf(String.valueOf(99999 - b4.ce())));
                            hashtable.put(concat.substring(concat.length() - 16), b3);
                            final String concat2 = "".concat(String.valueOf(String.valueOf(b4.i(n))));
                            if (c2.d(concat2) != null) {
                                continue;
                            }
                            c2.a(concat2, new Integer(b4.i(n)));
                        }
                        catch (Exception ex) {}
                    }
                }
                try {
                    if (hashtable.size() > 0) {
                        hashtable.size();
                        final Enumeration<String> keys = hashtable.keys();
                        while (keys.hasMoreElements()) {
                            final String s2 = hashtable.get(keys.nextElement());
                            c.c(s2);
                            df2.a(df.b("".concat(String.valueOf(String.valueOf(s2)))));
                        }
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (c.b() > 0) {
                        if (i.c(145)) {
                            h.d(s, String.valueOf(String.valueOf(new StringBuffer("Need to swap out ").append(c.b()).append(" annotations ..."))));
                        }
                        while (c.b() > 0) {
                            final Object b5 = c.b(0);
                            c.d(0);
                            df.f("".concat(String.valueOf(String.valueOf(b5))));
                        }
                        final ee ee = new ee(s);
                        ee.a(0, s);
                        while (c2.b() > 0) {
                            final int intValue = (int)c2.b(0);
                            c2.d(0);
                            final df a2 = df2.a(intValue, s);
                            if (i.c(145)) {
                                h.d(s, String.valueOf(String.valueOf(new StringBuffer("Swapping out ").append(a2.d()).append(" annotation(s) on page ").append(intValue).append("..."))));
                            }
                            a(intValue, a2, o, s, b, component, ad, ee, b2);
                        }
                    }
                }
                catch (Exception ex3) {}
                try {
                    df2.m();
                }
                catch (Exception ex4) {}
                try {
                    c.c();
                }
                catch (Exception ex5) {}
                try {
                    c2.c();
                }
                catch (Exception ex6) {}
            }
            catch (Exception ex7) {}
            finally {
                df.a(a);
            }
        }
    }
    
    public static final void a(final int n, final df df, final Object o, final String s, final boolean b, final Component component, final ad ad, final boolean b2) throws Exception {
        if (df != null && ji.util.d.a(ad) && !b2) {
            final ee ee = new ee(s);
            ee.a(0, s);
            a(n, df, o, s, b, component, ad, ee, b2);
        }
    }
    
    private static final void a(final int n, final df df, final Object o, final String s, final boolean b, final Component component, final ad ad, final ee ee, final boolean b2) throws Exception {
        if (df != null && ji.util.d.a(ad) && !b2) {
            final boolean a = df.a();
            try {
                df.a(true);
                if (!df.b(n)) {
                    byte[] array = ee.a(s, df, component, ad, false, null, "UTF8", false, false, 0, b, null, true, true);
                    if (array != null) {
                        array = a4.a(array);
                    }
                    final String a2 = a(s, ji.util.d.c1(s), o, n, component, true);
                    final ac ac = new ac(a2, true, false, 0, false, component, true, s);
                    ac.b(array);
                    ac.a(component);
                    a(a2, true);
                }
            }
            finally {
                df.a(a);
            }
        }
    }
    
    public static final void a(final int n, final Object o, final String s, final boolean b, final Component component, final ad ad, final byte[] array) throws Exception {
        if (array != null && ji.util.d.a(ad)) {
            final String a = a(s, ji.util.d.c1(s), o, n, component, true);
            final ac ac = new ac(a, true, false, 0, false, component, true, s);
            ac.b(array);
            ac.a(component);
            a(a, true);
        }
    }
    
    public static final boolean a(final int n, final Object o, final df df, final String s, final Component component, final ad ad) {
        boolean b = false;
        try {
            if (a(n, s, ji.util.d.c1(s), component, o, ad)) {
                if (i.c(145)) {
                    h.d(s, String.valueOf(String.valueOf(new StringBuffer("Loading cached annotations for page ").append(n).append("..."))));
                }
                final String a = a(s, ji.util.d.c1(s), o, n, component, true);
                final df df2 = new df(s);
                try {
                    b8.e = false;
                    if (ad.a(a, false, false, n, false, df2, false, true, true)) {
                        df.a(df2.b(ad));
                        df2.m();
                        b = true;
                    }
                }
                catch (Exception ex2) {}
                finally {
                    b8.e = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            h.d(s, "Annotation reload required");
        }
        return b;
    }
    
    private static final int a(final Object o) {
        char c = '\0';
        final char[] charArray = "".concat(String.valueOf(String.valueOf(o))).toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            c += (char)(charArray[i] & '\u00ff');
        }
        return c;
    }
    
    private static final String a(final String s, final Object o, final ad ad) {
        return a(s, ji.util.d.c1(s), String.valueOf(String.valueOf(o)).concat("list"), 0, ad, false);
    }
    
    private static final String a(final String s, final String s2, final Object o, final int n, final Component component, final boolean b) {
        final q a = q.a(component, s);
        String s3 = a.f();
        if (!s3.endsWith("\\") && !s3.endsWith("/")) {
            s3 = String.valueOf(String.valueOf(s3)).concat("/");
        }
        char c = '\0';
        final char[] charArray = String.valueOf(String.valueOf(new StringBuffer("").append(s).append(o))).toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            c += (char)(charArray[i] & '\u00ff');
        }
        String s4;
        if (b) {
            s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(a.g()).append("alckv1").append(s2).append("D").append(a(o)).append("D").append(n).append(".tmp")));
        }
        else {
            s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("alckv1").append(s2).append("D").append(a(o)).append("D").append(n).append(".tmp")));
        }
        return s4;
    }
    
    public static final boolean a(final int n, final String s, final String s2, final Component component, final Object o, final ad ad) {
        final boolean b = false;
        if (ji.util.d.a(ad)) {
            try {
                final String a = a(s, s2, o, n, component, true);
                if (a(a)) {
                    return ac.d(a, s);
                }
                if (ac.d(a, s)) {
                    return true;
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public static final boolean a(final ad ad, final Object o, final String s) {
        try {
            if (ji.util.d.a(ad) && s != null && ad != null && s.indexOf("alckv1") >= 0 && ad.a0(o.toString())) {
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static final void a(final String s, final ad ad, final Object o) {
        try {
            if (!ad.a0(o.toString())) {
                final String a = a(s, o, ad);
                if (ac.d(a, s)) {
                    final ac ac = new ac(a, false, false, 0, ad, s);
                    final byte[] array = new byte[(int)ac.v()];
                    ac.a(array);
                    ac.a(ad);
                    a(a, false);
                    ji.io.ac.c(a, s);
                    if (array != null) {
                        for (int i = 0; i < array.length; ++i) {
                            try {
                                final String a2 = a(s, ji.util.d.c1(s), o, i + 1, ad, true);
                                ji.io.ac.c(a2, s);
                                a(a2, false);
                            }
                            catch (Exception ex) {}
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public static final dg a(final df df, final long n) {
        try {
            for (int d = df.d(), i = 0; i < d; ++i) {
                final dg f = df.f(i);
                if (f.e(1) && f.bi() == n) {
                    return f;
                }
            }
            return null;
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static final dg a(final df df, final long n, final int n2) {
        try {
            for (int d = df.d(), i = 0; i < d; ++i) {
                final dg f = df.f(i);
                if (f.e(2) && f.bi() == n && f.i(0) == n2) {
                    return f;
                }
            }
            return null;
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static final df a(final String s, final dx dx, final int n, final Vector vector, final int n2, final ad ad, final int n3) {
        final df df = new df(s);
        try {
            if (vector != null) {
                final int size = vector.size();
                if (size > 0) {
                    final dg dg = new dg("searchmarkerpage".concat(String.valueOf(String.valueOf(n))), ji.util.d.cm(), 0, s, n2, ad.hc());
                    dg.a(ad);
                    dg.a(16, new String[0]);
                    dg.a(true, 2);
                    dg.d(0);
                    dg.j(n);
                    dg.d((long)n3);
                    df.a(dg);
                    for (int i = 0; i < size; ++i) {
                        final eu eu = vector.elementAt(i);
                        for (int j = 0; j < eu.b.size(); ++j) {
                            final Rectangle rectangle = eu.b.elementAt(j);
                            final dg dg2 = new dg(String.valueOf(String.valueOf(new StringBuffer("findResult").append(eu.a).append("-").append(i + 1).append("-").append(j))), ji.util.d.cm(), 0, s, n2, ad.hc());
                            dg2.a(ad);
                            dg2.a(13, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
                            dg2.au(true);
                            dg2.a(true, 1);
                            dg2.a(eu.e, eu.f);
                            dg2.ba(true);
                            dg2.d(0);
                            dg2.j(eu.a);
                            dg2.aj(true);
                            dg2.am(false);
                            dg2.ag(false);
                            dg2.d(ad.k5());
                            dg2.d(eu.c);
                            dg2.c(ad.k6());
                            dg2.m(eu.a(s));
                            df.a(dg2);
                        }
                        eu.g = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return df;
    }
    
    static {
        b8.a = null;
        b8.b = null;
        b8.c = null;
        b8.d = new Object();
        b8.e = true;
        b8.f = new Hashtable();
    }
}
