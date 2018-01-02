// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.util.c3;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import ji.ext.jiQuickStart;
import ji.util.b6;
import java.io.File;
import ji.io.q;
import ji.font.j;
import ji.v1event.ao;
import ji.net.a0;
import java.net.HttpURLConnection;
import java.io.FileNotFoundException;
import java.net.URLConnection;
import ji.sec.az;
import ji.sec.u;
import java.io.InputStream;
import java.net.URL;
import ji.util.e;
import ji.io.ac;
import ji.io.h;
import ji.util.i;
import ji.util.y;
import ji.v1event.af;
import java.awt.Component;
import ji.util.d;
import ji.sec.aw;
import java.util.Hashtable;
import ji.awt.bb;
import ji.awt.c;

public class z
{
    private static final Object a;
    private static final Object b;
    private static final Object c;
    private static final Object d;
    public static boolean e;
    private static boolean f;
    private static boolean g;
    private String h;
    public static boolean i;
    private static String[] j;
    private static boolean k;
    public static c l;
    public static c m;
    public static c n;
    public static c o;
    public static c p;
    public static c q;
    public static bb r;
    private static boolean s;
    public static c t;
    public static c u;
    public static c v;
    public static long w;
    private static Hashtable x;
    private static Exception y;
    private static aw z;
    
    public z() {
        this.h = null;
    }
    
    public static final boolean a() {
        return ji.res.z.i;
    }
    
    public static final void a(final boolean i) {
        ji.res.z.i = i;
    }
    
    private static final String e(final String s) {
        return ji.util.d.b(s, ".gif", ".dat");
    }
    
    public static final void b(final boolean f) {
        ji.res.z.f = f;
    }
    
    public static final boolean b() {
        return ji.res.z.f && !ji.res.z.e;
    }
    
    public static final String a(final String s) {
        return c(s);
    }
    
    public static final byte[] a(final Component component, final String s, final String s2, final af af, final Object o, final String s3, final y y) throws Exception {
        return a(component, s, s2, af, o, s3, false, y);
    }
    
    public static final byte[] a(final String s, final String s2, final int n, final af af, final Object o, final String s3, final y y) {
        return a(s, s2, n, af, o, s3, false, null, null, y, false);
    }
    
    public static final byte[] a(final String s, final String s2, final int n, final af af, final Object o, final String s3, final boolean b, final y y, final boolean b2) {
        return a(s, s2, n, af, o, s3, b, null, null, y, b2);
    }
    
    public static final byte[] a(final String s, final String s2, final int n, final af af, final Object o, final String s3, final boolean b, final String s4, final String s5, final y y) {
        return a(s, s2, n, af, o, s3, b, s4, s5, y, false);
    }
    
    public static final byte[] a(final String s, final String s2, final int n, final af af, final Object o, final String s3, final boolean b, final String s4, final String s5, final y y, final boolean b2) {
        return a(s, s2, n, af, o, s3, b, s4, s5, true, y, b2);
    }
    
    public static final String a(final Component component, final String s, final String s2, final af af, final String s3, final Object o) throws Exception {
        if (s == null && s2 == null) {
            return null;
        }
        if (ji.util.d.f.a((Object)s)) {
            return null;
        }
        return a(ji.util.d.h(s, s3), af, s3, true, true, o);
    }
    
    public static final byte[] a(final String s, final String s2, int n, final af af, final Object o, final String s3, final boolean b, final String s4, String b2, final boolean b3, final y y, final boolean b4) {
        URLConnection a = null;
        InputStream inputStream = null;
        int n2 = 1;
        boolean c = ji.util.i.c(251);
        int n3 = 0;
        byte[] array = null;
        ji.util.d.fy = null;
        ji.util.d.f0 = false;
        String value = s;
        int n4 = 0;
        ji.util.d.v = false;
        try {
            if (ji.util.d.eg() && s2 != null) {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(value)));
            }
        }
        catch (Exception ex4) {}
        try {
            if (ji.util.d.dr()) {
                h.d(s3, "FILE: Load ".concat(String.valueOf(String.valueOf(s))));
            }
            while (n2 != 0 && n4 < 1) {
                ++n4;
                final boolean b5 = false;
                if (ji.util.d.eg()) {
                    String a2 = null;
                    if (!b4) {
                        a2 = a(value, af, s3, ji.util.d.eg(), false, o);
                    }
                    if (a2 != null) {
                        if (!b3) {
                            return new byte[] { 0 };
                        }
                        array = ji.util.d.b(a2, af, s3);
                    }
                    if (array == null) {
                        try {
                            final String a3 = a(value, o, s3, af);
                            if (!ji.util.d.by(a3)) {
                                array = ji.util.d.b(a3, af, s3);
                                if (!ji.util.d.by(s4)) {
                                    final ac ac = new ac(s4, true, false, 0, false, o, false, s3);
                                    ac.b(array);
                                    ac.a(o);
                                    ji.io.ac.c(a3, s3);
                                }
                            }
                        }
                        catch (Exception ex5) {}
                    }
                    if (array != null) {
                        n2 = 0;
                    }
                    else {
                        final Object o2 = null;
                        final URL url = new URL(ji.util.e.am(), value);
                        try {
                            if (url != null) {
                                final String bh = ji.util.d.bh(url.toString());
                                if (bh != null && bh.toLowerCase().equals("txt")) {
                                    c = false;
                                }
                            }
                        }
                        catch (Exception ex6) {}
                        try {
                            a = ji.util.d.a(url, ji.util.i.c(222), s3);
                            if (a != null) {
                                n3 = ji.util.d.b(a, s3);
                                if (b2 != null && ji.util.i.c(27) && ji.util.d.ag > 0) {
                                    double n5 = 0.0;
                                    try {
                                        n5 = n3 / ji.util.d.ag;
                                    }
                                    catch (Exception ex7) {}
                                    if (n5 >= ji.util.d.n && n5 >= 0.1) {
                                        final int n6 = (int)Math.round(n5 + 0.5);
                                        String s5;
                                        if (n6 <= 1) {
                                            s5 = "1 ".concat(String.valueOf(String.valueOf(s.a(663, s3))));
                                        }
                                        else if (n6 < 60) {
                                            s5 = String.valueOf(String.valueOf(new StringBuffer("").append(n6).append(" ").append(s.a(664, s3))));
                                        }
                                        else if (n6 == 60) {
                                            s5 = "1 ".concat(String.valueOf(String.valueOf(s.a(776, s3))));
                                        }
                                        else {
                                            final int n7 = (n6 + 15) / 60;
                                            String s6;
                                            if (n7 <= 1) {
                                                s6 = s.a(776, s3);
                                            }
                                            else {
                                                s6 = s.a(777, s3);
                                            }
                                            s5 = String.valueOf(String.valueOf(new StringBuffer("").append(n7).append(" ").append(s6)));
                                        }
                                        b2 = ji.util.d.b(b2, "<seconds>", s5);
                                        try {
                                            h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, prompting user with: ").append(ji.util.d.b(b2, "\n", " ")).append("..."))));
                                        }
                                        catch (Exception ex8) {}
                                        final boolean a4 = ji.util.d.a(s.a(725, s3), b2, (Component)o, null, null, s3);
                                        if (a4) {
                                            h.d(s3, "Auto-Update, user response: Yes...");
                                        }
                                        else {
                                            h.d(s3, "Auto-Update, user response: No!...");
                                        }
                                        if (!a4) {
                                            n2 = 0;
                                            ji.util.d.v = true;
                                            h.d(s3, "User Aborted download of ".concat(String.valueOf(String.valueOf(url))));
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                        catch (Exception ex) {
                            if (c) {
                                if (url != null) {
                                    h.d(s3, url.toString());
                                }
                                ex.printStackTrace();
                            }
                            a = null;
                            inputStream = null;
                            ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(url).append("' not found (").append(ex).append(")")));
                            ji.util.d.f0 = true;
                        }
                        if (n3 <= 0) {
                            if (c && ji.util.d.e4 < 5) {
                                ++ji.util.d.e4;
                                if (a != null) {
                                    h.d(s3, a.toString());
                                }
                                else {
                                    h.d(s3, "Connection = null");
                                }
                                if (a != null) {
                                    h.d(s3, "ContentLength = ".concat(String.valueOf(String.valueOf(ji.util.d.b(a, s3)))));
                                }
                                h.d(s3, "zero length (files does not exist at this location): ".concat(String.valueOf(String.valueOf(url))));
                            }
                            ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(url).append("' not found")));
                            ji.util.d.f0 = true;
                        }
                        if (ji.util.d.fy == null) {
                            try {
                                array = a(n3, a, null, s2, af, url.toString(), s4, o, s3, b3);
                            }
                            catch (Exception ex2) {
                                if (c) {
                                    if (url != null) {
                                        h.d(s3, url.toString());
                                    }
                                    ex2.printStackTrace();
                                }
                                ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(value, s3)).append("' failed #1 (").append(ex2).append(")")));
                            }
                        }
                        if (array == null || o2 != null) {
                            continue;
                        }
                        a(array, s4, value, af, s3, false);
                    }
                }
                else {
                    String s7 = value;
                    if (s2 != null) {
                        if (ji.util.d.bj(s2)) {
                            s7 = new URL(new URL(s2), s7).toString();
                        }
                        else if (!ji.util.d.bj(s7)) {
                            final String bu = ji.util.d.bu(s3);
                            String s8 = ji.util.d.a(s2, s7, s3);
                            if (!s8.endsWith(bu)) {
                                s8 = String.valueOf(String.valueOf(s8)).concat(String.valueOf(String.valueOf(bu)));
                            }
                            s7 = String.valueOf(String.valueOf(s8)).concat(String.valueOf(String.valueOf(s7)));
                        }
                    }
                    String a5 = a(s7, af, s3, ji.util.d.eg(), false, o);
                    if (a5 != null) {
                        if (!b3) {
                            return new byte[] { 0 };
                        }
                        array = ji.util.d.b(a5, af, s3);
                    }
                    if (array == null) {
                        a5 = null;
                        if (ji.util.d.bj(s7)) {
                            final URLConnection a6 = ji.util.d.a(new URL(s7), true, s3);
                            n3 = ji.util.d.b(a6, s3);
                            inputStream = a6.getInputStream();
                        }
                        else {
                            final u u = new u(s7, s3);
                            ji.util.d.g8 = u.f();
                            if (u != null) {
                                if (u.c()) {
                                    inputStream = new az(s7, s3);
                                    if (inputStream != null) {
                                        n3 = (int)u.g();
                                    }
                                }
                                else {
                                    ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(s7, s3)).append("' not found.")));
                                    ji.util.d.f0 = true;
                                }
                            }
                        }
                        if (ji.util.d.f0) {
                            final u u2 = new u(ji.util.d.o(s7, s3), s3);
                            if (u2 != null) {
                                if (u2.c()) {
                                    inputStream = new az(ji.util.d.o(s7, s3), s3);
                                    if (inputStream != null) {
                                        n3 = (int)u2.g();
                                    }
                                }
                                else {
                                    ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(s7, s3)).append("' not found.")));
                                    ji.util.d.f0 = true;
                                }
                            }
                        }
                        if (inputStream != null) {
                            array = a(n3, null, inputStream, s2, af, null, s4, o, s3, b3);
                        }
                        if (array != null) {
                            ji.util.d.fy = null;
                        }
                    }
                    if (!b5) {
                        n2 = 0;
                        if (array == null || a5 != null) {
                            continue;
                        }
                        a(array, s4, s7, af, s3, false);
                    }
                    else {
                        if (--n > 0) {
                            try {
                                try {
                                    if (a != null) {
                                        a = null;
                                    }
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        }
                                        catch (Exception ex9) {}
                                        inputStream = null;
                                        continue;
                                    }
                                    continue;
                                }
                                catch (Exception ex10) {}
                            }
                            catch (Exception ex11) {
                                continue;
                            }
                        }
                        n2 = 0;
                    }
                }
            }
        }
        catch (Exception ex3) {
            if (c) {
                h.d(s3, ji.util.d.o(value, s3));
                ex3.printStackTrace();
            }
            ji.util.d.b(ex3);
            if (ex3 instanceof FileNotFoundException) {
                ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(value, s3)).append("' not found (").append(ex3).append(")")));
                ji.util.d.f0 = true;
            }
            else {
                ji.util.d.fy = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(value, s3)).append("' failed #2 (").append(ex3).append(")")));
            }
        }
        finally {
            try {
                if (a != null) {}
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex12) {}
                }
            }
            catch (Exception ex13) {}
        }
        if (ji.util.d.dr()) {
            h.d(s3, "FILE: Load Complete ".concat(String.valueOf(String.valueOf(s))));
        }
        return array;
    }
    
    private static boolean a(final int n) {
        return n >= 400 && n <= 599;
    }
    
    public static final byte[] a(final String s, final String s2, final af af, final Object o, final String s3, final String s4, String b, final boolean b2) throws FileNotFoundException {
        URLConnection a = null;
        InputStream inputStream = null;
        boolean c = ji.util.i.c(251);
        int n = 0;
        byte[] array = null;
        String s5 = null;
        int n2 = 0;
        String value = s;
        ji.util.d.v = false;
        try {
            if (ji.util.d.eg() && s2 != null) {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append(value)));
            }
        }
        catch (Exception ex5) {}
        try {
            if (ji.util.d.dr()) {
                h.d(s3, "FILE: Load ".concat(String.valueOf(String.valueOf(s))));
            }
            final boolean b3 = false;
            int responseCode = -1;
            s5 = null;
            if (ji.util.d.eg()) {
                final String a2 = a(value, af, s3, ji.util.d.eg(), false, o);
                if (a2 != null) {
                    if (!b2) {
                        return new byte[] { 0 };
                    }
                    array = ji.util.d.b(a2, af, s3);
                }
                if (array == null) {
                    try {
                        final String a3 = a(value, o, s3, af);
                        if (!ji.util.d.by(a3)) {
                            array = ji.util.d.b(a3, af, s3);
                            if (!ji.util.d.by(s4)) {
                                final ac ac = new ac(s4, true, false, 0, false, o, false, s3);
                                ac.b(array);
                                ac.a(o);
                                ji.io.ac.c(a3, s3);
                            }
                        }
                    }
                    catch (Exception ex6) {}
                }
                if (array == null) {
                    final Object o2 = null;
                    final URL url = new URL(ji.util.e.am(), value);
                    try {
                        if (url != null) {
                            final String bh = ji.util.d.bh(url.toString());
                            if (bh != null && bh.toLowerCase().equals("txt")) {
                                c = false;
                            }
                        }
                    }
                    catch (Exception ex7) {}
                    try {
                        a = ji.util.d.a(url, ji.util.i.c(222), s3);
                        if (a != null) {
                            responseCode = -1;
                            if (!ji.util.d.em() && a instanceof HttpURLConnection) {
                                final HttpURLConnection httpURLConnection = (HttpURLConnection)a;
                                responseCode = httpURLConnection.getResponseCode();
                                if (a(responseCode)) {
                                    s5 = String.valueOf(String.valueOf(new StringBuffer("'").append(url).append("' ").append(responseCode).append(": ").append(httpURLConnection.getResponseMessage())));
                                }
                            }
                            if (s5 == null) {
                                n = ji.util.d.b(a, s3);
                                if (b != null && ji.util.i.c(27) && ji.util.d.ag > 0) {
                                    double n3 = 0.0;
                                    try {
                                        n3 = n / ji.util.d.ag;
                                    }
                                    catch (Exception ex8) {}
                                    if (n3 >= ji.util.d.n && n3 >= 0.1) {
                                        final int n4 = (int)Math.round(n3 + 0.5);
                                        String s6;
                                        if (n4 <= 1) {
                                            s6 = "1 ".concat(String.valueOf(String.valueOf(s.a(663, s3))));
                                        }
                                        else if (n4 < 60) {
                                            s6 = String.valueOf(String.valueOf(new StringBuffer("").append(n4).append(" ").append(s.a(664, s3))));
                                        }
                                        else if (n4 == 60) {
                                            s6 = "1 ".concat(String.valueOf(String.valueOf(s.a(776, s3))));
                                        }
                                        else {
                                            final int n5 = (n4 + 15) / 60;
                                            String s7;
                                            if (n5 <= 1) {
                                                s7 = s.a(776, s3);
                                            }
                                            else {
                                                s7 = s.a(777, s3);
                                            }
                                            s6 = String.valueOf(String.valueOf(new StringBuffer("").append(n5).append(" ").append(s7)));
                                        }
                                        b = ji.util.d.b(b, "<seconds>", s6);
                                        try {
                                            h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Auto-Update, prompting user with: ").append(ji.util.d.b(b, "\n", " ")).append("..."))));
                                        }
                                        catch (Exception ex9) {}
                                        final boolean a4 = ji.util.d.a(s.a(725, s3), b, (Component)o, null, null, s3);
                                        if (a4) {
                                            h.d(s3, "Auto-Update, user response: Yes...");
                                        }
                                        else {
                                            h.d(s3, "Auto-Update, user response: No!...");
                                        }
                                        if (!a4) {
                                            ji.util.d.v = true;
                                            h.d(s3, "User Aborted download of ".concat(String.valueOf(String.valueOf(url))));
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        if (c) {
                            if (url != null) {
                                h.d(s3, url.toString());
                            }
                            ex.printStackTrace();
                        }
                        a = null;
                        inputStream = null;
                        s5 = String.valueOf(String.valueOf(new StringBuffer("'").append(url).append("' ").append(s.a(260, s3)).append(" (").append(ex).append(")")));
                        n2 = 1;
                    }
                    if (a(responseCode)) {
                        if (c && ji.util.d.e4 < 5) {
                            ++ji.util.d.e4;
                            if (a != null) {
                                h.d(s3, a.toString());
                            }
                            else {
                                h.d(s3, "Connection = null");
                            }
                            if (a != null) {
                                h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Http Response = ").append(responseCode).append(", ").append(s5))));
                            }
                        }
                        n2 = 1;
                    }
                    else if (n <= 0) {
                        if (c && ji.util.d.e4 < 5) {
                            ++ji.util.d.e4;
                            if (a != null) {
                                h.d(s3, a.toString());
                            }
                            else {
                                h.d(s3, "Connection = null");
                            }
                            if (a != null) {
                                h.d(s3, "ContentLength = ".concat(String.valueOf(String.valueOf(ji.util.d.b(a, s3)))));
                            }
                            h.d(s3, "zero length (files does not exist at this location): ".concat(String.valueOf(String.valueOf(url))));
                        }
                        s5 = String.valueOf(String.valueOf(new StringBuffer("'").append(url).append("' ").append(s.a(260, s3))));
                        n2 = 1;
                    }
                    if (s5 == null) {
                        try {
                            array = a(n, a, null, s2, af, url.toString(), s4, o, s3, b2);
                        }
                        catch (Exception ex2) {
                            if (c) {
                                if (url != null) {
                                    h.d(s3, url.toString());
                                }
                                ex2.printStackTrace();
                            }
                            s5 = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(value, s3)).append("' failed #1 (").append(ex2).append(")")));
                        }
                    }
                    if (array != null && o2 == null) {
                        a(array, s4, value, af, s3, false);
                    }
                }
            }
            else {
                String s8 = value;
                if (s2 != null) {
                    if (ji.util.d.bj(s2)) {
                        s8 = new URL(new URL(s2), s8).toString();
                    }
                    else if (!ji.util.d.bj(s8)) {
                        final String bu = ji.util.d.bu(s3);
                        String s9 = ji.util.d.a(s2, s8, s3);
                        if (!s9.endsWith(bu)) {
                            s9 = String.valueOf(String.valueOf(s9)).concat(String.valueOf(String.valueOf(bu)));
                        }
                        s8 = String.valueOf(String.valueOf(s9)).concat(String.valueOf(String.valueOf(s8)));
                    }
                }
                String a5 = a(s8, af, s3, ji.util.d.eg(), false, o);
                if (a5 != null) {
                    if (!b2) {
                        return new byte[] { 0 };
                    }
                    array = ji.util.d.b(a5, af, s3);
                }
                if (array == null) {
                    a5 = null;
                    if (ji.util.d.bj(s8)) {
                        final URLConnection a6 = ji.util.d.a(new URL(s8), true, s3);
                        if (!ji.util.d.em() && a6 instanceof HttpURLConnection) {
                            final HttpURLConnection httpURLConnection2 = (HttpURLConnection)a6;
                            if (a(httpURLConnection2.getResponseCode())) {
                                s5 = httpURLConnection2.getResponseMessage();
                                n2 = 1;
                            }
                        }
                        if (s5 == null) {
                            n = ji.util.d.b(a6, s3);
                            inputStream = a6.getInputStream();
                        }
                    }
                    else {
                        final u u = new u(s8, s3);
                        ji.util.d.g8 = u.f();
                        if (u != null) {
                            if (u.c()) {
                                inputStream = new az(s8, s3);
                                if (inputStream != null) {
                                    n = (int)u.g();
                                }
                            }
                            else {
                                s5 = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(s8, s3)).append("' ").append(s.a(260, s3))));
                                n2 = 1;
                            }
                        }
                    }
                    if (n2 != 0) {
                        final u u2 = new u(ji.util.d.o(s8, s3), s3);
                        if (u2 != null) {
                            if (u2.c()) {
                                inputStream = new az(ji.util.d.o(s8, s3), s3);
                                if (inputStream != null) {
                                    n = (int)u2.g();
                                }
                            }
                            else {
                                if (s5 == null) {
                                    s5 = String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(s8, s3)).append("' ").append(s.a(260, s3))));
                                }
                                n2 = 1;
                            }
                        }
                    }
                    if (inputStream != null) {
                        array = a(n, null, inputStream, s2, af, null, s4, o, s3, b2);
                    }
                    if (array != null) {
                        s5 = null;
                    }
                }
                if (!b3 && array != null && a5 == null) {
                    a(array, s4, s8, af, s3, false);
                }
            }
        }
        catch (FileNotFoundException ex3) {
            if (c) {
                h.d(s3, ji.util.d.o(value, s3));
            }
            ji.util.d.a(ex3);
            if (n2 != 0) {
                throw ex3;
            }
            throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(value, s3)).append("' ").append(s.a(260, s3)).append(" (").append(ex3).append(")"))));
        }
        catch (Exception ex4) {
            if (c) {
                h.d(s3, ji.util.d.o(value, s3));
                ex4.printStackTrace();
            }
            ji.util.d.b(ex4);
            throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer("'").append(ji.util.d.o(value, s3)).append("' failed #2 (").append(ex4).append(")"))));
        }
        finally {
            try {
                if (a != null) {}
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex10) {}
                }
            }
            catch (Exception ex11) {}
        }
        if (s5 != null) {
            throw new FileNotFoundException(s5);
        }
        if (ji.util.d.dr()) {
            h.d(s3, "FILE: Load Complete ".concat(String.valueOf(String.valueOf(s))));
        }
        return array;
    }
    
    public static final String a(final URL url, final String s, final Object o, final String s2, final af af) {
        String s3 = null;
        a0 a0 = null;
        String b = null;
        try {
            a0 = new a0(o, s2);
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s2)))).append("/").append(ji.util.d.h(s, s2))));
            b = a0.b(url, true, true, "", o, af, null);
            if (!ji.util.d.by(b) && !a0.h()) {
                if (ji.util.d.cs()) {
                    h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Downloaded: ").append(url).append("..."))));
                }
                ac.a(b, value, o, s2, false);
                s3 = value;
            }
            else {
                h.d(s2, "ERROR: Unable to find font file: ".concat(String.valueOf(String.valueOf(url))));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (a0 != null) {
                if (b != null) {
                    a0.a(url, o, s2);
                }
                a0.a();
            }
        }
        return s3;
    }
    
    public static final String a(final String s, final af af, final String s2, final boolean b, final boolean b2, final Object o) {
        if (ji.util.i.c(36)) {
            h.d(s2, "Util: getRepFile synchronizing");
        }
        synchronized (ji.res.z.d) {
            if (ji.util.i.c(36)) {
                h.d(s2, "Util: getRepFile synchronized");
            }
            final String b3 = b(s, af, s2, b, b2, o);
            if (ji.util.i.c(36)) {
                h.d(s2, "Util: getRepFile end synchronized");
            }
            // monitorexit(z.d)
            return b3;
        }
    }
    
    public static final String a(final String s, final String s2, final String s3) {
        if (b() && a(s3) != null) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s3)))).append("/").append(s2).append("/").append(s)));
        }
        return null;
    }
    
    private static final String b(String e, final af af, final String s, final boolean b, final boolean b2, final Object o) {
        String s2 = null;
        if (b() && b) {
            try {
                e = e(e);
                final String h = ji.util.d.h(e, s);
                if (!b2 && !c(h, s)) {
                    return null;
                }
                final String c = ji.util.d.c(o, s);
                if (c != null) {
                    ji.util.d.g7 = c;
                }
                if (c != null && aa.a()) {
                    if (ji.res.z.g) {
                        c(s);
                        ji.res.z.g = false;
                    }
                    final String h2 = ji.util.d.h(e, s);
                    if (!ji.util.d.b(h2, false, s)) {
                        final String bh = ji.util.d.bh(e);
                        int n = b2 ? 1 : 0;
                        if (n == 0) {
                            if (bh.toLowerCase().startsWith("v1")) {
                                n = 1;
                            }
                            else if (ji.font.j.i(e)) {
                                n = 1;
                            }
                            else if (h2.toLowerCase().startsWith("locals")) {
                                n = 1;
                            }
                            else if (h2.toLowerCase().startsWith("fonts.txt")) {
                                n = 1;
                            }
                        }
                        if (n != 0) {
                            a(af, s);
                            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s)))).append("/").append(ji.util.d.h(e, s))));
                            if (ac.d(value, s)) {
                                if (a()) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Rep: ").append(value).append(" is in repository"))));
                                }
                                s2 = value;
                            }
                            else if (a()) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Rep: ").append(value).append(" is NOT in repository"))));
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                s2 = null;
                ex.printStackTrace();
            }
        }
        return s2;
    }
    
    public static boolean a(final Object o, final String s) {
        if (!ji.util.d.hc) {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s)))).append("/").append(aa.b()).append(".").append("v1")));
            boolean b = false;
            final String value2 = String.valueOf(String.valueOf(new StringBuffer("").append(aa.c()).append(aa.b())));
            if (!ac.d(value, s)) {
                b = true;
            }
            else {
                try {
                    final ac ac = new ac(value, false, false, 0, false, o, s);
                    final byte[] array = new byte[(int)ac.v()];
                    ac.a(array);
                    ac.a(o);
                    if (!new String(array).equals(value2)) {
                        b = true;
                    }
                }
                catch (Exception ex) {
                    b = true;
                }
            }
            if (b) {
                if (ji.util.d.c3 == null) {
                    if (a()) {
                        h.d(s, String.valueOf(String.valueOf(new StringBuffer("Rep: Clear repository ").append(aa.c()).append(" for ").append(aa.b()))));
                    }
                    a(a(s), s);
                }
                try {
                    final ac ac2 = new ac(value, true, false, 0, false, o, s);
                    ac2.b(value2.getBytes());
                    ac2.a(o);
                }
                catch (Exception ex2) {}
            }
            else if (a()) {
                h.d(s, String.valueOf(String.valueOf(new StringBuffer("Rep: Repository ").append(aa.c()).append(" OK for ").append(aa.b()))));
            }
            if (ji.util.i.c(233)) {
                try {
                    ji.res.z.z = ji.io.q.a(a(s), s, value2);
                }
                catch (Exception ex3) {}
            }
            if (ji.util.i.c(233)) {
                final String b2 = ji.io.q.b(b(s), "rep", s);
                if (b2 != null) {
                    b(b2, s);
                }
            }
            ji.util.d.hc = true;
            return b;
        }
        return false;
    }
    
    public static final String b(final String s) {
        synchronized (ji.res.z.x) {
            String s2 = null;
            if (s != null) {
                final String[] array = ji.res.z.x.get(s);
                if (array != null && array.length > 0) {
                    s2 = array[1];
                }
            }
            // monitorexit(z.x)
            return s2;
        }
    }
    
    public static final String c(final String s) {
        synchronized (ji.res.z.x) {
            String s2 = null;
            if (s != null) {
                final String[] array = ji.res.z.x.get(s);
                if (array != null && array.length > 0) {
                    s2 = array[0];
                }
            }
            if (!ji.util.d.by(s2)) {
                // monitorexit(z.x)
                return s2;
            }
            try {
                final q a = ji.io.q.a((Object)null, s);
                String i = null;
                String s4;
                if (ji.util.i.c(233)) {
                    s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a.i()))).append(File.separator).append("cache")));
                    i = a.i();
                }
                else {
                    s4 = ji.util.d.c((Object)null, s);
                }
                String s5;
                String s7;
                if (ji.util.d.jh != null) {
                    s5 = "rep";
                    final String jh = ji.util.d.jh;
                    String s6 = "viewone";
                    if (ji.util.d.ei()) {
                        s6 = "viewone .NET";
                    }
                    final String c = s.c(s6);
                    if (jh.endsWith("/") || jh.endsWith("\\")) {
                        s7 = String.valueOf(String.valueOf(jh)).concat(String.valueOf(String.valueOf(c)));
                    }
                    else {
                        s7 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(jh))).append("/").append(c)));
                    }
                }
                else if (ji.util.d.cg(s)) {
                    s5 = "../rep";
                    s7 = s4;
                }
                else {
                    s5 = "rep";
                    s7 = s4;
                }
                if (s7 != null) {
                    if (s7.endsWith("/") || s7.endsWith("\\")) {
                        s2 = String.valueOf(String.valueOf(s7)).concat(String.valueOf(String.valueOf(s5)));
                    }
                    else {
                        s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s7))).append("/").append(s5)));
                    }
                    final int index = s2.indexOf("/..");
                    if (index >= 0) {
                        final int max = Math.max(s2.substring(0, index).lastIndexOf("/"), s2.substring(0, index).lastIndexOf("\\"));
                        if (max >= 0) {
                            s2 = String.valueOf(String.valueOf(s2.substring(0, max))).concat(String.valueOf(String.valueOf(s2.substring(index + 3))));
                        }
                    }
                    if (ji.util.i.c(233) && aa.e() != null) {
                        s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(File.separator).append(ji.util.d.bf(aa.e()))));
                    }
                    ac.e(s2, s);
                }
                if (s != null && s2 != null) {
                    ji.res.z.x.put(s, new String[] { s2, i });
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            // monitorexit(z.x)
            return s2;
        }
    }
    
    public static final void a(final String s, final boolean b, final String s2) {
        if (ji.util.i.c(36)) {
            h.d(s2, "Util: removeRepFile synchronizing");
        }
        synchronized (ji.res.z.d) {
            if (ji.util.i.c(36)) {
                h.d(s2, "Util: removeRepFile synchronized");
            }
            b(s, b, s2);
            if (ji.util.i.c(36)) {
                h.d(s2, "Util: removeRepFile end synchronized");
            }
        }
        // monitorexit(z.d)
    }
    
    private static final void b(String e, final boolean b, final String s) {
        if (b()) {
            try {
                e = e(e);
                final String h = ji.util.d.h(e, s);
                if (!b && !c(h, s)) {
                    return;
                }
                if (ji.util.d.c((Object)null, s) != null && a(s) != null && aa.a()) {
                    final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s)))).append("/").append(h)));
                    final String d = d(value, s);
                    final String b2 = ji.util.d.b(value, ji.util.d.bh(value), "v1");
                    try {
                        ac.c(value, s);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        ac.c(d, s);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    try {
                        ac.c(b2, s);
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
            catch (Exception ex4) {
                ex4.printStackTrace();
            }
        }
    }
    
    private static boolean c(final String s, final String s2) {
        final String lowerCase = s.toLowerCase();
        return !lowerCase.startsWith(ji.util.d.cu(s2).toLowerCase()) && !lowerCase.startsWith("trial.") && !lowerCase.startsWith("external.") && !lowerCase.endsWith(".jar");
    }
    
    private static final void a(final byte[] array, final String s, final String s2, final af af, final String s3, final boolean b) {
        if (ji.util.i.c(36)) {
            h.d(s3, "Util: addRepFile synchronizing");
        }
        synchronized (ji.res.z.d) {
            if (ji.util.i.c(36)) {
                h.d(s3, "Util: addRepFile synchronized");
            }
            b(array, s, s2, af, s3, b);
            if (ji.util.i.c(36)) {
                h.d(s3, "Util: addRepFile end synchronized");
            }
        }
        // monitorexit(z.d)
    }
    
    private static final void b(final byte[] array, final String s, String e, final af af, final String s2, final boolean b) {
        try {
            if (array != null && e != null && a(s2) != null && aa.a() && b() && s == null) {
                e = e(e);
                final String h = ji.util.d.h(e, s2);
                if (c(h, s2) || b) {
                    boolean b2 = false;
                    if (b) {
                        b2 = true;
                    }
                    else if (ji.util.d.bh(e).toLowerCase().startsWith("v1")) {
                        b2 = true;
                    }
                    else if (h.toLowerCase().startsWith("locals")) {
                        b2 = true;
                    }
                    if (b2) {
                        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a(s2)))).append("/").append(ji.util.d.h(e, s2))));
                        if (a()) {
                            ji.io.h.d(s2, "Rep: Add ".concat(String.valueOf(String.valueOf(value))));
                        }
                        final ac ac = new ac(value, true, false, 0, false, af, s2);
                        ac.b(array);
                        ac.a(af);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final byte[] a(final Component component, final String s, final String s2, final af af, Object o, final String s3, boolean b, final y y) throws Exception {
        byte[] array = null;
        String h = null;
        boolean b2 = true;
        if (o == null) {
            o = component;
            if (o == null) {
                ji.io.h.d(s3, "Error locating parent (null) in data file load");
            }
        }
        if (!b) {
            b = true;
            s.toLowerCase();
            if (s.startsWith("lic.")) {
                b = false;
            }
            else if (s.startsWith("trial.")) {
                b = false;
            }
            else if (aa.d(s)) {
                b = false;
            }
        }
        if (s == null && s2 == null) {
            return null;
        }
        if (ji.util.d.f.a((Object)s)) {
            return null;
        }
        if (b) {
            h = ji.util.d.h(s, s3);
            String a = null;
            if (b() && ji.res.z.k) {
                a = a(h, af, s3, true, true, o);
            }
            boolean b3 = false;
            if (a != null) {
                try {
                    final ac ac = new ac(a, false, false, 0, false, af, s3);
                    array = new byte[(int)ac.v()];
                    ac.a(array);
                    ac.a(af);
                    final String d = d(h, s3);
                    try {
                        final ac ac2 = new ac(d, false, false, 0, false, af, s3);
                        y.a = ac2.p();
                        y.b = ac2.p();
                        if (ac2.p() > 0) {
                            y.c = true;
                        }
                        else {
                            if (!ji.util.d.by(s)) {
                                if (s.toLowerCase().indexOf(ji.util.d.at.toLowerCase()) >= 0) {
                                    b3 = true;
                                }
                                else if (s.toLowerCase().indexOf(ji.util.d.au.toLowerCase()) >= 0) {
                                    b3 = true;
                                }
                                else if (s.toLowerCase().indexOf(ji.util.d.av.toLowerCase()) >= 0) {
                                    b3 = true;
                                }
                                else if (s.toLowerCase().indexOf(ji.util.d.aw.toLowerCase()) >= 0) {
                                    b3 = true;
                                }
                            }
                            if (!b3) {
                                y.c = false;
                            }
                        }
                        ac2.a(component);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (!b3) {
                        b2 = false;
                    }
                    else {
                        array = null;
                        ji.io.h.d(s3, String.valueOf(String.valueOf(d)).concat(" appears to be corrupt so regenerating file..."));
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        boolean b4 = false;
        if (array == null) {
            y.c = false;
            if (s2 != null) {
                final byte[] a2 = a(s2, ji.util.e.an(), 3, af, o, s3, b, null, null, y);
                if (a2 == null) {
                    array = a(s, ji.util.e.an(), 3, af, o, s3, b, null, null, y);
                }
                else {
                    y.c = true;
                    try {
                        array = ji.util.d.b(a2, s2, ji.util.d.t, y, s3);
                    }
                    catch (b6 b6) {
                        b4 = true;
                    }
                }
            }
            else {
                array = a(s, ji.util.e.an(), 3, af, o, s3, b, null, null, y);
            }
            String s4 = ji.util.d.es();
            if (ji.res.z.y != null) {
                s4 = ji.res.z.y.toString();
            }
            if (b4) {
                array = null;
            }
            if (array == null) {
                if (!ji.util.d.fs) {
                    boolean b5 = false;
                    if (b4) {
                        if (s2 != null) {
                            s4 = String.valueOf(String.valueOf(s2)).concat(" is corrupt, please re-install the viewer on your server.");
                        }
                        else {
                            s4 = String.valueOf(String.valueOf(s)).concat(" is corrupt, please re-install the viewer on your server.");
                        }
                        b5 = true;
                    }
                    else {
                        if (s4 == null) {
                            if (s2 != null) {
                                s4 = String.valueOf(String.valueOf(s2)).concat(" not found");
                            }
                            else {
                                s4 = String.valueOf(String.valueOf(s)).concat(" not found");
                            }
                        }
                        else if (s2 != null) {
                            s4 = ji.util.d.b(s4, s, s2);
                        }
                        s4 = String.valueOf(String.valueOf(s4)).concat("\n\nPlease ensure the above file exists before using this version of viewONE");
                    }
                    ji.util.d.f.c(s);
                    ji.util.d.a(s4, (af)null, s3);
                    ji.util.d.fs = true;
                    if (b5) {
                        if (ji.util.d.by(a(s3))) {
                            c(s3);
                        }
                        a(a(s3), s3);
                    }
                }
                if (s4 != null) {
                    throw new Exception(s4);
                }
                if (s2 != null) {
                    throw new Exception(String.valueOf(String.valueOf(s2)).concat(" not found"));
                }
                throw new Exception(String.valueOf(String.valueOf(s)).concat(" not found"));
            }
            else if (array != null && b && h != null && b2 && b()) {
                a(array, null, h, af, s3, true);
                final String d2 = d(h, s3);
                if (d2 != null) {
                    try {
                        final ac ac3 = new ac(d2, true, false, 0, false, component, s3);
                        ac3.b(y.a);
                        ac3.b(y.b);
                        if (y.c) {
                            ac3.b(1);
                        }
                        else {
                            ac3.b(0);
                        }
                        ac3.a(component);
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
            }
        }
        return array;
    }
    
    private static final String d(final String s, final String s2) {
        if (ji.util.i.c(36)) {
            h.d(s2, "Util: getRepIndex synchronizing");
        }
        synchronized (ji.res.z.c) {
            if (ji.util.i.c(36)) {
                h.d(s2, "Util: getRepIndex synchronized");
            }
            final String e = e(s, s2);
            if (ji.util.i.c(36)) {
                h.d(s2, "Util: getRepIndex end synchronized");
            }
            // monitorexit(z.c)
            return e;
        }
    }
    
    private static final String e(final String s, final String s2) {
        final String a = a(s2);
        if (a == null) {
            return null;
        }
        final String h = ji.util.d.h(s, s2);
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append("/").append(h.substring(0, h.lastIndexOf("."))).append(".idx")));
    }
    
    public static String a(final String s, final Object o, final String s2, final af af) throws Exception {
        String s3 = null;
        if (ji.util.d.dr()) {
            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("CustomResource: loading: ").append(s).append("..."))));
        }
        a0 a0 = null;
        try {
            if (s.toLowerCase().indexOf(".v1") >= 0) {
                return null;
            }
            if (s.toLowerCase().indexOf(".txt") >= 0) {
                return null;
            }
            a0 = new a0(o, s2);
            final URL url = new URL(ji.util.e.am(), s);
            if (ji.util.d.dr()) {
                h.d(s2, "CustomResource step1...");
            }
            s3 = a0.a(url);
            if (s3 != null && !ac.d(s3, s2)) {
                if (ji.util.d.dr()) {
                    h.d(s2, "CustomResource step1A...");
                }
                a0.a(url, o, s2);
                s3 = null;
            }
            if (ji.util.d.dr()) {
                h.d(s2, "CustomResource step2...");
            }
            if (!ji.util.d.b(ji.res.z.j) && ji.util.d.by(s3)) {
                if (ji.util.d.dr()) {
                    h.d(s2, "CustomResource step3...");
                }
                for (int i = 0; i < ji.res.z.j.length; ++i) {
                    try {
                        final URL url2 = new URL(ji.res.z.j[i]);
                        if (ji.util.d.dr()) {
                            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("CustomResource step3a...(").append(url2).append(")"))));
                        }
                        final String b = a0.b(url2, false, false, "", o, af, null);
                        if (ji.util.d.dr()) {
                            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("CustomResource step3b...(").append(b).append(")"))));
                        }
                    }
                    catch (Exception y) {
                        y.printStackTrace();
                        if (!(y instanceof NullPointerException)) {
                            ji.res.z.y = y;
                            ji.util.d.fy = y.toString();
                            throw y;
                        }
                        h.d(s2, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" is not contained in ").append(ji.res.z.j[i]).append("..."))));
                    }
                }
                if (ji.util.d.dr()) {
                    h.d(s2, "CustomResource step4...");
                }
                s3 = a0.a(url);
                if (ji.util.d.dr()) {
                    h.d(s2, "CustomResource step4...");
                }
            }
            if (ji.util.d.dr()) {
                h.d(s2, "CustomResource step5...");
            }
            if (s3 != null && !ji.util.d.by(s3) && !ac.d(s3, s2)) {
                if (ji.util.d.dr()) {
                    h.d(s2, "CustomResource step6...");
                }
                a0.a(url, o, s2);
                s3 = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (a0 != null) {
                a0.a();
            }
        }
        if (ji.util.d.dr()) {
            h.d(s2, String.valueOf(String.valueOf(new StringBuffer("CustomResource step7... ").append(s3).append(" (").append(ac.a(s3, s2)).append(" bytes)"))));
        }
        return s3;
    }
    
    private static final byte[] a(final int n, final URLConnection urlConnection, final InputStream inputStream, final String s, final af af, final String s2, final String s3, final Object o, final String s4, final boolean b) throws Exception {
        byte[] array = null;
        if (ji.util.d.dr()) {
            h.d(s4, String.valueOf(String.valueOf(new StringBuffer("DataResource: Retrieving ").append(s2).append("..."))));
        }
        if (ji.util.d.eg() && urlConnection != null) {
            if (ji.util.i.c(273)) {
                array = ji.util.d.a(urlConnection, af, n, s2, s3, o, s4, b);
            }
            else {
                array = ji.util.d.a(urlConnection, af, -1, s2, s3, o, s4, b);
            }
        }
        else if (inputStream != null) {
            array = ji.util.d.a(inputStream, af, o, s4, s3, b);
        }
        return array;
    }
    
    public static final void a(final String s, final String s2) {
        try {
            if (a()) {
                h.d(s2, "Rep: Clear ".concat(String.valueOf(String.valueOf(s))));
            }
            try {
                if (jiQuickStart.d(s2) && !jiQuickStart.i(s2) && jiQuickStart.e(s2)) {
                    if (ji.util.i.c(105)) {
                        h.d(s2, "ViewONEStandard: Shutting down QS before clearing repository...");
                    }
                    jiQuickStart.a(s2, true);
                }
            }
            catch (Exception ex2) {}
            ji.util.d.a(s, s2, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void b(final String s, final String s2) {
        if (s != null) {
            try {
                a(s, ac.h(s, s2), s2);
            }
            catch (Exception ex) {}
        }
    }
    
    private static final void a(final String s, final String[] array, final String s2) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                try {
                    final String b = ji.io.q.b(s, array[i], s2);
                    if (ac.f(b, s2)) {
                        try {
                            if (!ji.io.q.b(b, s2)) {
                                final String[] h = ac.h(b, s2);
                                if (h != null) {
                                    a(b, h, s2);
                                }
                                ac.a(b, s2, false, false);
                            }
                        }
                        catch (Exception ex) {}
                    }
                    else if (!ji.io.q.b(s, s2)) {
                        ac.a(b, s2, false, false);
                    }
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public static final void a(final String[] array) {
        try {
            if (array != null) {
                ji.res.z.j = new String[array.length];
                for (int i = 0; i < array.length; ++i) {
                    ji.res.z.j[i] = array[i];
                }
            }
            else {
                ji.res.z.j = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final Image a(final int n, final int n2, final int n3, final int n4, final Component component, final String s, final String s2, final af af, final Color color, final String s3) throws Exception {
        return a(n, n2, n3, n4, component, s, s2, af, color, s3, false);
    }
    
    public static final Image a(final int n, final int n2, final int n3, final int n4, final Component component, final String s, final String s2, final af af, final Color color, final String s3, final boolean b) throws Exception {
        if (ji.res.z.s) {
            if (ji.util.i.c(36)) {
                h.d(s3, "Util: loadImageRes2 synchronizing");
            }
            synchronized (ji.res.z.b) {
                if (ji.util.i.c(36)) {
                    h.d(s3, "Util: loadImageRes2 synchronized");
                }
                final Image b2 = b(n, n2, n3, n4, component, s, s2, af, color, s3, b);
                if (ji.util.i.c(36)) {
                    h.d(s3, "Util: loadImageRes2 end synchronized");
                }
                // monitorexit(z.b)
                return b2;
            }
        }
        return b(n, n2, n3, n4, component, s, s2, af, color, s3, b);
    }
    
    private static final Image b(final int n, final int n2, final int n3, final int n4, final Component component, final String s, final String s2, final af af, final Color color, final String s3, final boolean b) throws Exception {
        if (ji.util.i.c(100)) {
            return d(n, n2, n3, n4, component, s, s2, af, color, s3, b);
        }
        return c(n, n2, n3, n4, component, s, s2, af, color, s3, b);
    }
    
    private static final Image c(final int n, final int n2, int n3, int n4, final Component component, final String s, final String s2, final af af, final Color color, final String s3, final boolean b) throws Exception {
        Image image = null;
        try {
            String value = "0";
            if (color != null) {
                value = String.valueOf(String.valueOf(new StringBuffer("").append(color.getRed()).append(color.getGreen()).append(color.getBlue())));
            }
            if (n3 <= 0) {
                n3 = 22;
            }
            if (n4 <= 0) {
                n4 = 19;
            }
            String.valueOf(String.valueOf(new StringBuffer("").append(n).append("#").append(n2).append("#").append(n3).append("#").append(n4).append("#").append(s).append("#").append(value)));
            final String s4 = "dim";
            int[] array = null;
            if (array == null) {
                final String concat = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(value)));
                int[] array2 = (int[])ji.res.z.n.d(concat);
                Dimension dimension2;
                if (array2 == null) {
                    int[] array3 = (int[])ji.res.z.l.d(s);
                    Dimension dimension;
                    if (array3 == null) {
                        if (ji.util.d.eg() && !ji.util.d.ep()) {
                            ji.util.d.a(ji.util.e.v, String.valueOf(String.valueOf(s.a(7, s3))).concat("..."));
                        }
                        final Image b2 = b(component, s, s2, af, s3);
                        ji.util.d.a(component, b2);
                        dimension = new Dimension(b2.getWidth(null), b2.getHeight(null));
                        array3 = new int[dimension.width * dimension.height];
                        try {
                            final c3 a = ji.util.d.a(b2, s3, component);
                            ji.util.d.a(component, s3, b2, a, 0, 0, dimension.width, dimension.height, dimension.width, array3);
                            ji.util.d.a(b2, a);
                        }
                        catch (Exception ex3) {}
                        ji.res.z.l.a(s, array3);
                        ji.res.z.l.a(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s4))), dimension);
                        try {
                            ji.res.z.m.a(s);
                            ji.res.z.m.a(s, new Long(System.currentTimeMillis()));
                        }
                        catch (Exception ex4) {}
                    }
                    else {
                        dimension = (Dimension)ji.res.z.l.d(String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(s4))));
                        try {
                            ji.res.z.m.a(s);
                            ji.res.z.m.a(s, new Long(System.currentTimeMillis()));
                        }
                        catch (Exception ex5) {}
                    }
                    if (ji.util.d.eg() && !ji.util.d.ep()) {
                        ji.util.d.a(ji.util.e.v, String.valueOf(String.valueOf(s.a(7, s3))).concat("..."));
                    }
                    final SystemColor controlShadow = SystemColor.controlShadow;
                    if (ji.util.e.g != null) {
                        ji.util.d.k(ji.util.e.g);
                    }
                    Color color2 = null;
                    try {
                        if (color != null) {
                            color2 = color;
                        }
                        else {
                            color2 = ji.util.e.ao();
                        }
                        if (color2 == null) {
                            color2 = component.getBackground();
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    final Color color3 = new Color(color2.getRGB() & 0xFFFFFF);
                    int n5 = 0;
                    if (color != null) {
                        n5 = -16777216;
                    }
                    if (ji.util.i.c(7) && ji.util.e.t() && ji.util.d.ar()) {
                        ji.util.d.a(array3, dimension.width, dimension.height, ji.util.d.h, ji.util.e.a0(), ji.util.e.a3(), n5, n4);
                    }
                    else {
                        ji.util.d.a(array3, dimension.width, dimension.height, ji.util.d.h, color3, n5);
                    }
                    try {
                        ji.res.z.n.a(concat, array3);
                        ji.res.z.n.a(String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(s4))), dimension);
                    }
                    catch (Exception ex6) {}
                    try {
                        ji.res.z.o.a(concat);
                        ji.res.z.o.a(concat, new Long(System.currentTimeMillis()));
                    }
                    catch (Exception ex7) {}
                    array2 = array3;
                    dimension2 = dimension;
                }
                else {
                    dimension2 = (Dimension)ji.res.z.n.d(String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(s4))));
                    try {
                        ji.res.z.o.a(concat);
                        ji.res.z.o.a(concat, new Long(System.currentTimeMillis()));
                    }
                    catch (Exception ex8) {}
                }
                if (array2 != null) {
                    array = new int[n3 * n4];
                    try {
                        int n6 = 19;
                        int n7 = 22;
                        int n8 = 1;
                        if (b) {
                            n6 = n4;
                            n7 = n3;
                            n8 = 0;
                        }
                        for (int i = 0; i < n4; ++i) {
                            System.arraycopy(array2, n2 * n6 * dimension2.width + i * dimension2.width + (n2 + n8) * dimension2.width + (n * n7 + n + n8), array, i * n3, n3);
                        }
                    }
                    catch (Exception ex9) {}
                }
            }
            if (array != null) {
                try {
                    image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n3, n4, ColorModel.getRGBdefault(), array, 0, n3));
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        catch (Exception ex10) {}
        f(s3);
        return image;
    }
    
    private static final Image d(final int n, final int n2, int n3, int n4, final Component component, final String s, final String s2, final af af, final Color color, final String s3, final boolean b) throws Exception {
        Image image = null;
        try {
            if (n3 <= 0) {
                n3 = 22;
            }
            if (n4 <= 0) {
                n4 = 19;
            }
            s.toLowerCase();
            int[] array = (int[])ji.res.z.t.d(s);
            final Integer n5 = (Integer)ji.res.z.u.d(s);
            final Integer n6 = (Integer)ji.res.z.v.d(s);
            int n7 = 0;
            if (n5 != null) {
                n7 = n5;
            }
            if (n6 != null) {
                n6;
            }
            if (array == null) {
                String.valueOf(String.valueOf(s.a(7, s3))).concat("...");
                final y y = new y();
                final byte[] a = a(component, s, s2, af, null, s3, true, y);
                n7 = y.a;
                final int b2 = y.b;
                ji.util.d.es();
                if (a != null) {
                    array = new int[a.length * 4];
                    for (int i = 0; i < a.length; i += 4) {
                        final int n8 = i / 4;
                        try {
                            array[n8] = ((a[i] & 0xFF) << 24) + ((a[i + 1] & 0xFF) << 16) + ((a[i + 2] & 0xFF) << 8) + (a[i + 3] & 0xFF);
                        }
                        catch (Exception ex4) {}
                    }
                    Color color2 = null;
                    try {
                        if (color != null) {
                            color2 = color;
                        }
                        else {
                            color2 = ji.util.e.ao();
                        }
                        if (color2 == null) {
                            color2 = component.getBackground();
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (color2 != null) {
                        final Color color3 = new Color(color2.getRGB() & 0xFFFFFF);
                        int n9 = 0;
                        if (color != null) {
                            n9 = -16777216;
                        }
                        try {
                            ji.util.d.a(array, ji.util.d.h, color3, n9);
                        }
                        catch (Exception ex5) {}
                    }
                    ji.res.z.t.a(s, array);
                    ji.res.z.u.a(s, new Integer(n7));
                    ji.res.z.v.a(s, new Integer(b2));
                }
            }
            if (array != null) {
                ji.res.z.w = System.currentTimeMillis();
                final int[] array2 = new int[n3 * n4];
                try {
                    int n10 = 19;
                    int n11 = 22;
                    int n12 = 1;
                    if (b) {
                        n10 = n4;
                        n11 = n3;
                        n12 = 0;
                    }
                    for (int j = 0; j < n4; ++j) {
                        System.arraycopy(array, n2 * n10 * n7 + j * n7 + (n2 + n12) * n7 + (n * n11 + n + n12), array2, j * n3, n3);
                    }
                }
                catch (Exception ex6) {}
                if (array2 != null) {
                    try {
                        image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n3, n4, ColorModel.getRGBdefault(), array2, 0, n3));
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                f(s3);
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return image;
    }
    
    public static final void c() {
        try {
            if (ji.res.z.l != null) {
                ji.res.z.l.c();
            }
            if (ji.res.z.m != null) {
                ji.res.z.m.c();
            }
            if (ji.res.z.n != null) {
                ji.res.z.n.c();
            }
            if (ji.res.z.o != null) {
                ji.res.z.o.c();
            }
            if (ji.res.z.p != null) {
                ji.res.z.p.c();
            }
            if (ji.res.z.q != null) {
                ji.res.z.q.c();
            }
        }
        catch (Exception ex) {}
    }
    
    public static final Image a(final Component component, final String s, final String s2, final af af, final String s3) throws Exception {
        final boolean b = ji.res.z.f && !ji.res.z.e;
        ji.res.z.f = false;
        Image a = null;
        try {
            a(s, component, s3, af);
            a = a(component, s, s2, af, null, s3);
        }
        finally {
            ji.res.z.f = (b && !ji.res.z.e);
        }
        return a;
    }
    
    public static final Image b(final Component component, final String s, final String s2, final af af, final String s3) throws Exception {
        return a(component, s, s2, af, null, s3);
    }
    
    public static final Image a(final Component component, final String s, final String s2, final af af, final Color color, final String s3) throws Exception {
        if (ji.res.z.s) {
            if (ji.util.i.c(36)) {
                h.d(s3, "Util: loadImageRes1 synchronizing");
            }
            synchronized (ji.res.z.a) {
                if (ji.util.i.c(36)) {
                    h.d(s3, "Util: loadImageRes1 synchronized");
                }
                final Image b = b(component, s, s2, af, color, s3);
                if (ji.util.i.c(36)) {
                    h.d(s3, "Util: loadImageRes1 end synchronized");
                }
                // monitorexit(z.a)
                return b;
            }
        }
        return b(component, s, s2, af, color, s3);
    }
    
    private static final Image b(final Component component, final String s, final String s2, final af af, final Color color, final String s3) throws Exception {
        Image image = null;
        if (s == null && s2 == null) {
            return null;
        }
        int i = 1;
        int n = 3;
        while (i != 0) {
            i = 0;
            final y y = new y();
            byte[] array = a(component, s, s2, af, null, s3, y);
            final String es = ji.util.d.es();
            if (array != null) {
                try {
                    if (y.c) {
                        try {
                            int n2 = y.a;
                            int n3 = y.b;
                            if (n2 == 0 || n2 == 0) {
                                ji.res.z.k = false;
                                try {
                                    array = a(component, s, s2, af, null, s3, y);
                                    n2 = y.a;
                                    n3 = y.b;
                                }
                                finally {
                                    ji.res.z.k = true;
                                }
                            }
                            final int[] array2 = new int[n2 * n3];
                            boolean b = false;
                            for (int j = 0; j < array.length; j += 4) {
                                final int n4 = j / 4;
                                try {
                                    array2[n4] = ((array[j] & 0xFF) << 24) + ((array[j + 1] & 0xFF) << 16) + ((array[j + 2] & 0xFF) << 8) + (array[j + 3] & 0xFF);
                                    if (array2[n4] != 0) {
                                        b = true;
                                    }
                                }
                                catch (Exception ex3) {}
                            }
                            if (!b && n > 0) {
                                h.d(s3, "Resetting repository file: ".concat(String.valueOf(String.valueOf(s2))));
                                --n;
                                a(s, false, s3);
                                i = 1;
                                continue;
                            }
                            try {
                                if (color != null) {
                                    ji.util.d.a(array2, n2, n3, ji.util.d.h, color, -16777216);
                                }
                            }
                            catch (Exception ex4) {}
                            image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(n2, n3, ColorModel.getRGBdefault(), array2, 0, n2));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        image = Toolkit.getDefaultToolkit().createImage(array);
                        Color color2 = null;
                        try {
                            if (color != null) {
                                color2 = color;
                            }
                            else {
                                color2 = ji.util.e.ao();
                            }
                            if (color2 == null) {
                                color2 = component.getBackground();
                            }
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        if (color2 != null) {
                            final Color color3 = new Color(color2.getRGB() & 0xFFFFFF);
                            int n5 = 0;
                            if (color != null) {
                                n5 = -16777216;
                            }
                            ji.util.d.a(component, image);
                            final int width = image.getWidth(null);
                            final int height = image.getHeight(null);
                            if (width > 0 && height > 0) {
                                final int[] array3 = new int[width * height];
                                final c3 a = ji.util.d.a(image, s3, component);
                                ji.util.d.a(component, s3, image, a, 0, 0, width, height, width, array3);
                                ji.util.d.a(image, a);
                                boolean a2 = false;
                                try {
                                    a2 = ji.util.d.a(array3, width, height, ji.util.d.h, color3, n5);
                                }
                                catch (Exception ex5) {}
                                if (a2) {
                                    image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, ColorModel.getRGBdefault(), array3, 0, width));
                                }
                            }
                        }
                    }
                    if (image == null) {
                        continue;
                    }
                    ji.util.d.a(component, image);
                }
                catch (Exception ex6) {}
            }
            else {
                ji.util.d.a(s2, s, es, s3);
            }
        }
        return image;
    }
    
    private static final void f(final String s) {
        try {
            if (!ji.util.d.c5() && !ji.util.d.c3() && !ji.util.d.c4() && ji.res.z.r == null) {
                (ji.res.z.r = new bb(s, new c4(s))).setPriority(1);
                ji.res.z.r.start();
            }
        }
        catch (Exception ex) {}
    }
    
    public static final Dimension d() {
        return new Dimension(22, 19);
    }
    
    public static boolean d(final String s) {
        boolean b = false;
        if (b()) {
            b = true;
            final String[] array = { e(ji.util.d.at), e(ji.util.d.au), e(ji.util.d.av) };
            final File file = new File(a(s));
            File file2;
            for (int n = 0; n < array.length && b; b = (b && file2.exists()), ++n) {
                file2 = new File(file, array[n]);
                if (ji.util.i.c(83)) {
                    h.d(s, "checking for cached file ".concat(String.valueOf(String.valueOf(file2.getAbsolutePath()))));
                }
            }
        }
        else if (ji.util.i.c(83)) {
            h.d(s, "checking fo cached file: repository not enabled");
        }
        return b;
    }
    
    static {
        a = new Object();
        b = new Object();
        c = new Object();
        d = new Object();
        ji.res.z.e = false;
        ji.res.z.f = true;
        ji.res.z.g = true;
        ji.res.z.i = false;
        ji.res.z.j = null;
        ji.res.z.k = true;
        ji.res.z.l = new c("jiUtilResourceSources");
        ji.res.z.m = new c("jiUtilResourceSourcesAccess");
        ji.res.z.n = new c("jiUtilResourceSourcesColoured");
        ji.res.z.o = new c("jiUtilResourceSourcesColouredAccess");
        ji.res.z.p = new c("jiUtilResourceProcessed1");
        ji.res.z.q = new c("jiUtilResourceProcessed2");
        ji.res.z.r = null;
        ji.res.z.s = false;
        ji.res.z.t = new c("jiRep Res Indexes1", 10);
        ji.res.z.u = new c("jiRep Res Indexes2", 10);
        ji.res.z.v = new c("jiRep Res Indexes3", 10);
        ji.res.z.w = 0L;
        ji.res.z.x = new Hashtable();
        ji.res.z.y = null;
        ji.res.z.z = null;
    }
}
