// 
// Decompiled by Procyon v0.5.30
// 

package ji.zip;

import ji.io.h;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import ji.v1event.a6;
import ji.util.i;
import ji.io.q;
import ji.io.a5;
import ji.util.d;
import ji.awt.c;
import ji.v1event.af;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import ji.io.ac;

public class a4
{
    String[] a;
    String[] b;
    int[] c;
    int[] d;
    int e;
    int f;
    boolean g;
    private String h;
    private static boolean i;
    static String[] j;
    
    public a4(final boolean g) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.h = null;
        this.g = g;
    }
    
    public static final byte[] a(final byte[] array, final ac ac) throws Exception {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] byteArray;
        if (ac != null) {
            final byte[] array2 = new byte[Math.min(array.length, 100240)];
            for (int i = gzipInputStream.read(array2); i > 0; i = gzipInputStream.read(array2)) {
                ac.b(array2, 0, i);
            }
            gzipInputStream.close();
            byteArrayInputStream.close();
            byteArray = new byte[] { 0 };
        }
        else {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(array.length);
            final byte[] array3 = new byte[array.length];
            for (int j = gzipInputStream.read(array3); j > 0; j = gzipInputStream.read(array3)) {
                byteArrayOutputStream.write(array3, 0, j);
            }
            gzipInputStream.close();
            byteArrayInputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }
        return byteArray;
    }
    
    public static final boolean a() {
        return a4.i;
    }
    
    public static final String a(final String s, final boolean b, final Object o, final String s2, final af af, final c c, final boolean b2, final boolean b3) throws Exception {
        boolean al = false;
        String a = null;
        ac ac = null;
        ac ac2 = null;
        a5 a2 = null;
        GZIPInputStream gzipInputStream = null;
        a4.i = false;
        try {
            al = d.al;
            d.al = true;
            ac2 = new ac(s, false, false, 0, b, o, false, s2);
            final int n = (int)ac2.w();
            a2 = new a5(ac2, o);
            a = q.a(o, s2).a(q.a(), b);
            ac = new ac(a, true, false, 0, b, o, true, s2, b3, b, false);
            gzipInputStream = new GZIPInputStream(a2);
            int n2 = 102400;
            if (n2 <= 0) {
                n2 = n;
            }
            final byte[] array = new byte[n2];
            int read;
            for (int i = read = gzipInputStream.read(array); i > 0; i = gzipInputStream.read(array), read += i) {
                ac.b(array, 0, i);
            }
            gzipInputStream.close();
            gzipInputStream = null;
            a4.i = true;
        }
        catch (Exception ex) {
            if (b2 || ji.util.i.c(137)) {
                ex.printStackTrace();
            }
            try {
                if (ac != null) {
                    ac.a(o);
                }
                ac = null;
            }
            catch (Exception ex2) {}
            try {
                if (a != null) {
                    ji.io.ac.c(a, s2);
                }
            }
            catch (Exception ex3) {}
            a = s;
        }
        finally {
            try {
                if (gzipInputStream != null) {
                    gzipInputStream.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex5) {}
            try {
                if (a2 != null) {
                    a2.close();
                    a2.a();
                }
            }
            catch (Exception ex6) {}
            try {
                if (ac2 != null) {
                    ac2.a(o);
                }
            }
            catch (Exception ex7) {}
            if (af != null) {
                af.a(new a6(o, 4, "0"));
            }
            if (c != null) {
                a(new a6(o, 4, "0"), c);
            }
            d.al = al;
        }
        return a;
    }
    
    private static final void a(final a6 a6, final c c) {
        try {
            if (c != null) {
                for (int b = c.b(), i = 0; i < b; ++i) {
                    if (c.b(i) != null) {
                        ((af)c.b(i)).a(a6);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final byte[] a(final byte[] array) throws Exception {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final byte[] array2 = new byte[1024];
        int read;
        while ((read = byteArrayInputStream.read(array2)) >= 0) {
            gzipOutputStream.write(array2, 0, read);
        }
        byteArrayInputStream.close();
        gzipOutputStream.close();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }
    
    private static int a(final ac ac) throws Exception {
        final byte[] array = new byte[4];
        ac.a(array);
        return (array[3] & 0xFF) << 24 | (array[2] & 0xFF) << 16 | (array[1] & 0xFF) << 8 | (array[0] & 0xFF);
    }
    
    private static int b(final ac ac) throws Exception {
        final byte[] array = new byte[2];
        ac.a(array);
        return (array[1] & 0xFF) << 8 | (array[0] & 0xFF);
    }
    
    private static final int c(final ac ac) throws Exception {
        final int a = a(ac);
        if (a == 67324752) {
            return 0;
        }
        if (a == 134695760) {
            return 1;
        }
        if (a == 33639248) {
            return 2;
        }
        if (a == 101010256) {
            return 3;
        }
        return -1;
    }
    
    private static final su d(final ac ac) throws Exception {
        final su su = new su();
        su.a = b(ac);
        su.b = b(ac);
        su.c = b(ac);
        su.d = b(ac);
        su.e = a(ac);
        su.f = a(ac);
        su.g = b(ac);
        if (su.g > 0) {
            final byte[] array = new byte[su.g];
            ac.a(array);
            su.h = new String(array);
        }
        return su;
    }
    
    private static final st e(final ac ac) throws Exception {
        final st st = new st();
        st.a = b(ac);
        st.b = b(ac);
        st.c = b(ac);
        st.d = b(ac);
        st.e = b(ac);
        st.f = b(ac);
        st.g = a(ac);
        st.h = a(ac);
        st.i = a(ac);
        st.j = b(ac);
        st.k = b(ac);
        st.l = b(ac);
        st.m = b(ac);
        st.n = b(ac);
        st.o = a(ac);
        st.p = a(ac);
        if (st.j > 0) {
            final byte[] array = new byte[st.j];
            ac.a(array);
            st.q = new String(array);
        }
        if (st.k > 0) {
            ac.a(st.r = new byte[st.k]);
        }
        if (st.l > 0) {
            final byte[] array2 = new byte[st.l];
            ac.a(array2);
            st.s = new String(array2);
        }
        return st;
    }
    
    private static final sr f(final ac ac) throws Exception {
        final sr sr = new sr();
        sr.a = (int)ac.r();
        sr.b = b(ac);
        sr.c = b(ac);
        sr.d = b(ac);
        sr.e = b(ac);
        sr.f = b(ac);
        sr.g = a(ac);
        sr.h = a(ac);
        sr.i = a(ac);
        sr.j = b(ac);
        sr.k = b(ac);
        if (sr.j > 0) {
            final byte[] array = new byte[sr.j];
            ac.a(array);
            sr.l = new String(array);
        }
        if (sr.k > 0) {
            ac.a(sr.m = new byte[sr.k]);
        }
        sr.n = (int)ac.r();
        return sr;
    }
    
    private static final ss g(final ac ac) throws Exception {
        final ss ss = new ss();
        ss.a = (int)ac.r();
        ss.b = a(ac);
        ss.c = a(ac);
        ss.d = a(ac);
        return ss;
    }
    
    private static final sr[] b(final String s, final Object o, final String s2) {
        ac ac = null;
        try {
            ac = new ac(s, false, false, 0, false, o, false, s2);
            final c c = new c("jiZipFileList");
            while (!ac.g()) {
                switch (c(ac)) {
                    case 1: {
                        final sr f = f(ac);
                        if (f != null) {
                            c.c(f);
                            ac.a(f.h);
                            continue;
                        }
                        continue;
                    }
                    case 2: {
                        g(ac);
                        continue;
                    }
                    case 3: {
                        e(ac);
                        continue;
                    }
                    case 4: {
                        d(ac);
                        continue;
                    }
                    case 0: {
                        ac.a(ac.w());
                        continue;
                    }
                }
            }
            final sr[] array = new sr[c.b()];
            for (int i = 0; i < c.b(); ++i) {
                array[i] = (sr)c.b(i);
            }
            c.c();
            return array;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex2) {}
        }
        return null;
    }
    
    private static final byte[] a(final String s, final Object o, final String s2, final int n, final int n2) throws Exception {
        boolean al = false;
        byte[] array = null;
        ac ac = null;
        try {
            al = d.al;
            d.al = true;
            ac = new ac(s, false, false, 0, false, o, false, s2);
            ac.a((long)n);
            array = new byte[n2];
            ac.a(array);
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex) {}
            d.al = al;
        }
        return array;
    }
    
    private static final byte[] a(final ac ac, final int n, final int n2) throws Exception {
        ac.a((long)n);
        final byte[] array = new byte[n2];
        ac.a(array);
        return array;
    }
    
    private static final byte[] a(byte[] a, final int n, final int n2, final int n3, final ac ac) throws Exception {
        if (n == 0) {
            if (ac == null) {
                return a;
            }
            ac.b(a);
        }
        else if (a != null) {
            final byte[] array = new byte[18 + a.length];
            array[0] = 31;
            array[1] = -117;
            array[2] = (byte)n;
            array[4] = (array[3] = 0);
            array[6] = (array[5] = 0);
            array[8] = (array[7] = 0);
            array[9] = 27;
            System.arraycopy(a, 0, array, 10, a.length);
            int n4 = array.length - 8;
            array[n4++] = (byte)(n2 & 0xFF);
            array[n4++] = (byte)((n2 & 0xFF00) >> 8);
            array[n4++] = (byte)((n2 & 0xFF0000) >> 16);
            array[n4++] = (byte)((n2 & 0xFF000000) >> 24);
            array[n4++] = (byte)(n3 & 0xFF);
            array[n4++] = (byte)((n3 & 0xFF00) >> 8);
            array[n4++] = (byte)((n3 & 0xFF0000) >> 16);
            array[n4++] = (byte)((n3 & 0xFF000000) >> 24);
            a = a(array, ac);
        }
        return a;
    }
    
    public static final String[] b() {
        return a4.j;
    }
    
    public static final String a(final String s, final Object o, final String s2, final boolean b, final String s3, final boolean b2, final boolean b3) {
        boolean al = false;
        String s4 = null;
        try {
            al = d.al;
            d.al = true;
            final sr[] b4 = b(s, o, s2);
            if (b4 != null) {
                final boolean b5 = false;
                int n = 0;
                a4.j = new String[b4.length];
                while (!b5 && n < b4.length) {
                    final String l = b4[n].l;
                    if (!d.by(l)) {
                        final String bc = d.bc(l.toLowerCase());
                        if (!bc.endsWith("\\") && !bc.endsWith("/")) {
                            final byte[] a = a(s, o, s2, b4[n].n, b4[n].h);
                            if (a != null) {
                                final byte[] a2 = a(a, b4[n].d, b4[n].g, b4[n].i, null);
                                final q a3 = q.a(o, s2);
                                String s5;
                                if (b2) {
                                    String b6 = null;
                                    if (b3) {
                                        b6 = q.b();
                                    }
                                    s5 = a3.a(q.a(), false, d.cq(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("/").append(bc)))), b6);
                                }
                                else {
                                    final String n2 = a3.n();
                                    s5 = d.b(n2, d.bh(n2), d.bh(b4[n].l));
                                }
                                if (s4 == null) {
                                    s4 = s5;
                                }
                                final ac ac = new ac(s5, true, false, 0, false, o, s2);
                                ac.b(a2);
                                ac.a(o);
                                a4.j[n] = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(bc))).append("<:>").append(s5)));
                            }
                            if (!b) {
                                break;
                            }
                        }
                    }
                    ++n;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            d.al = al;
        }
        return s4;
    }
    
    public static final String[] a(final String s, final Object o, final String s2) {
        String[] array = null;
        final sr[] b = b(s, o, s2);
        if (b != null) {
            array = new String[b.length];
            for (int i = 0; i < b.length; ++i) {
                array[i] = b[i].l;
            }
        }
        return array;
    }
    
    public static final boolean a(final String s, final String s2, final String s3, final boolean b, final Object o, final String s4, final boolean b2, final boolean b3, final af af) {
        return a(s, s2, s3, b, o, s4, b2, b3, af, false);
    }
    
    public static final boolean a(final String s, final String s2, final String s3, final boolean b, final Object o, final String s4, final boolean b2, final boolean b3, final af af, final boolean b4) {
        return a(s, s2, s3, b, o, s4, b2, b3, af, b4, false);
    }
    
    public static final boolean a(final String s, String s2, final String s3, final boolean b, final Object o, final String s4, final boolean b2, final boolean b3, final af af, final boolean b4, final boolean b5) {
        boolean b6 = true;
        boolean al = false;
        ac ac = null;
        ac ac2 = null;
        try {
            al = d.al;
            d.al = true;
            final sr[] b7 = b(s, o, s4);
            if (b7 != null) {
                if (b7.length == 0) {
                    b6 = false;
                }
                else {
                    s2 = d.b(s2, "\\", "/");
                    if (!s2.endsWith("/")) {
                        s2 = String.valueOf(String.valueOf(s2)).concat("/");
                    }
                    final boolean b8 = false;
                    int n = 0;
                    ac = new ac(s, false, false, 0, false, o, false, s4);
                    if (b2) {
                        try {
                            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("/").append("vlocal.v1")));
                            if (b) {
                                ac2 = new ac(value, false, false, 0, false, o, false, s4);
                                final byte[] array = new byte[(int)ac2.w()];
                                ac2.a(array);
                                ac2.a(o);
                                ac2 = new ac(value, true, false, 0, false, o, false, s4);
                                ac2.b(array);
                            }
                        }
                        catch (IOException ex3) {}
                    }
                    int n3;
                    final int n2 = n3 = b7.length / 20;
                    while (!b8 && n < b7.length) {
                        final String l = b7[n].l;
                        if (!d.by(l)) {
                            boolean b9 = true;
                            final String concat = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(d.bc(l))));
                            s2 = d.b(s2, "//", "/");
                            if (b3) {
                                if (concat.toLowerCase().indexOf("meta-inf") >= 0) {
                                    b9 = false;
                                }
                                if (concat.toLowerCase().indexOf("package cache") >= 0) {
                                    b9 = false;
                                }
                            }
                            if (b5) {
                                if (b7[n].l.endsWith("/")) {
                                    b9 = false;
                                }
                                else if (new File(b7[n].l).getParent() != null) {
                                    b9 = false;
                                }
                            }
                            if (b9) {
                                ji.io.ac.a(d.i(concat, s4), true, ac2, s4);
                                if (b7[n].h == 0 && b7[n].l.endsWith("/")) {
                                    ji.io.ac.a(concat, true, ac2, s4);
                                }
                                else {
                                    final byte[] a = a(ac, b7[n].n, b7[n].h);
                                    if (a != null) {
                                        final ac ac3 = new ac(concat, true, false, 0, false, o, true, s4);
                                        a(a, b7[n].d, b7[n].g, b7[n].i, ac3);
                                        ac3.a(o);
                                        if (b4) {
                                            try {
                                                final long n4 = b7[n].e | b7[n].f << 16;
                                                ji.io.ac.a(concat, new Date((int)((n4 >> 25 & 0x7F) + 80), (int)((n4 >> 21 & 0xF) - 1), (int)(n4 >> 16 & 0x1F), (int)(n4 >> 11 & 0x1F), (int)(n4 >> 5 & 0x3F), (int)(n4 << 1 & 0x3E)).getTime());
                                            }
                                            catch (Exception ex) {
                                                d.a(ex);
                                            }
                                        }
                                    }
                                }
                                if (ac2 != null) {
                                    ac2.b(String.valueOf(String.valueOf(concat)).concat("\r\n").getBytes());
                                }
                            }
                        }
                        --n3;
                        ++n;
                        if (n3 <= 0) {
                            n3 = n2;
                            if (af == null) {
                                continue;
                            }
                            af.a(new a6(af, 4, "".concat(String.valueOf(String.valueOf(100 * n / b7.length)))));
                        }
                    }
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            b6 = false;
            h.d(s4, "Install failed source: ".concat(String.valueOf(String.valueOf(s))));
            h.d(s4, "Install failed destination: ".concat(String.valueOf(String.valueOf(s2))));
        }
        finally {
            if (ac != null) {
                try {
                    ac.a(o);
                }
                catch (Exception ex4) {}
            }
            if (ac2 != null) {
                try {
                    ac2.a(o);
                }
                catch (Exception ex5) {}
            }
            d.al = al;
        }
        return b6;
    }
    
    static {
        a4.i = false;
        a4.j = null;
    }
    
    static class ss
    {
        int a;
        int b;
        int c;
        int d;
    }
    
    static class su
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        String h;
    }
    
    static class st
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        int l;
        int m;
        int n;
        int o;
        int p;
        String q;
        byte[] r;
        String s;
    }
    
    static class sr
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        String l;
        byte[] m;
        int n;
    }
}
