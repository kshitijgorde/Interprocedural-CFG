// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.d;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class a
{
    static String a;
    static String b;
    static String c;
    static String d;
    static String e;
    static String f;
    static String g;
    static String h;
    static String i;
    static String j;
    static String k;
    static String l;
    static String m;
    static String n;
    static String o;
    static String p;
    static String q;
    static String r;
    static String s;
    static String t;
    static String u;
    static String v;
    static String w;
    static String x;
    static String y;
    static String z;
    static String A;
    static String B;
    static String C;
    static String D;
    static String E;
    static String F;
    static String G;
    static String H;
    static String I;
    static String J;
    int K;
    int L;
    byte[] M;
    Vector N;
    Hashtable O;
    a P;
    private byte[] Q;
    i R;
    private static String[] S;
    
    public a(final byte[] array) {
        this(-1, array);
    }
    
    public a(final String s) {
        this(new byte[] { (byte)s.charAt(0), (byte)s.charAt(1), (byte)s.charAt(2), (byte)s.charAt(3) });
    }
    
    public a(final int k, final byte[] m) {
        this.K = -1;
        this.L = 0;
        this.M = null;
        this.N = new Vector();
        this.O = new Hashtable();
        this.P = null;
        this.Q = new byte[4];
        this.K = k;
        this.M = m;
        this.b();
    }
    
    public void a() {
        final boolean u = com.easypano.tourweaver.d.i.u;
        final Enumeration<a> elements = this.N.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a();
            if (u) {
                return;
            }
            if (u) {
                break;
            }
        }
        this.P = null;
        this.O = null;
        this.N.removeAllElements();
        this.M = null;
        this.R = null;
    }
    
    private void b() {
        final boolean u = com.easypano.tourweaver.d.i.u;
        final String s = new String(this.M);
        boolean b2 = false;
        Label_0075: {
            Label_0068: {
                if (!u) {
                    Label_0058: {
                        if (!s.equals(com.easypano.tourweaver.d.a.d)) {
                            final boolean b = b2 = s.equals(com.easypano.tourweaver.d.a.k);
                            if (!u) {
                                if (b) {
                                    break Label_0058;
                                }
                                final boolean equals;
                                b2 = (equals = s.equals(com.easypano.tourweaver.d.a.w));
                            }
                            if (u) {
                                break Label_0075;
                            }
                            if (!b) {
                                break Label_0068;
                            }
                        }
                    }
                    this.L = 16;
                }
                if (!u) {
                    return;
                }
            }
            b2 = s.equals(com.easypano.tourweaver.d.a.p);
        }
        if (b2) {
            this.L = 4;
        }
    }
    
    public a c() {
        return this.P;
    }
    
    public synchronized void a(final a a) {
        this.N.addElement(a);
        a.P = this;
    }
    
    public Enumeration d() {
        return this.N.elements();
    }
    
    public void b(final a a) {
        this.N.removeElement(a);
    }
    
    public Hashtable e() {
        return this.O;
    }
    
    public static byte[] a(final int n) {
        return new byte[] { (byte)(0xFF & n), (byte)(0xFF & n >>> 8), (byte)(0xFF & n >>> 16), (byte)(0xFF & n >>> 24) };
    }
    
    public static int a(final byte[] array) {
        return (array[0] & 0xFF) | (array[1] << 8 & 0xFF00) | (array[2] << 16 & 0xFF0000) | array[3] << 24;
    }
    
    public void a(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        a a = this;
        if (!u) {
            if (this.M == null) {
                throw new NullPointerException(com.easypano.tourweaver.d.a.S[0]);
            }
            a = this;
        }
        final int k = a.K;
        Label_0073: {
            if (!u) {
                if (k != -1) {
                    break Label_0073;
                }
                a(inputStream, this.Q);
            }
            if (k != 0) {
                this.K = a(this.Q);
                this.R.a(4);
            }
        }
        final String s = new String(this.M);
        int n6;
        int equals;
        int n5;
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = (n5 = (equals = (n6 = (s.equals(com.easypano.tourweaver.d.a.d) ? 1 : 0))))));
        Label_0231: {
            Label_0230: {
                if (!u) {
                    if (n != 0) {
                        this.b(inputStream);
                        if (!u) {
                            break Label_0230;
                        }
                    }
                    n3 = (n2 = (n4 = (n5 = (equals = (n6 = (s.equals(com.easypano.tourweaver.d.a.k) ? 1 : 0))))));
                }
                if (!u) {
                    if (n2 != 0) {
                        this.c(inputStream);
                        if (!u) {
                            break Label_0230;
                        }
                    }
                    n4 = (n3 = (n5 = (equals = (n6 = (s.equals(com.easypano.tourweaver.d.a.p) ? 1 : 0)))));
                }
                if (!u) {
                    if (n3 != 0) {
                        this.d(inputStream);
                        if (!u) {
                            break Label_0230;
                        }
                    }
                    n5 = (n4 = (equals = (n6 = (s.equals(com.easypano.tourweaver.d.a.w) ? 1 : 0))));
                }
                if (!u) {
                    if (n4 != 0) {
                        this.e(inputStream);
                        if (!u) {
                            break Label_0230;
                        }
                    }
                    equals = (n5 = (n6 = (s.equals(com.easypano.tourweaver.d.a.y) ? 1 : 0)));
                }
                if (!u) {
                    if (n5 != 0) {
                        this.f(inputStream);
                        if (!u) {
                            break Label_0230;
                        }
                    }
                    n6 = (equals = (s.equals(com.easypano.tourweaver.d.a.D) ? 1 : 0));
                }
                if (u) {
                    break Label_0231;
                }
                if (equals != 0) {
                    this.g(inputStream);
                }
            }
            n6 = 0;
        }
        int i = n6;
        while (i < this.L) {
            a(inputStream, this.Q);
            this.R.a(4);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    public a a(final String s) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        a a = this;
        a p = null;
        while (!new String(a.M).equals(s)) {
            p = a.P;
            if (u) {
                return p;
            }
            a = p;
            if (u) {
                break;
            }
        }
        return p;
    }
    
    private void b(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int n4;
        int a;
        int n3;
        int n2;
        final int n = n2 = (n3 = (a = (n4 = a(inputStream, this.Q))));
        if (!u) {
            if (n != 0) {
                this.O.put(com.easypano.tourweaver.d.a.h, new Integer(a(this.Q)));
            }
            final int n5;
            n2 = (n5 = (n3 = (a = (n4 = a(inputStream, this.Q)))));
        }
        if (!u) {
            if (n != 0) {
                this.O.put(com.easypano.tourweaver.d.a.i, new Integer(a(this.Q)));
            }
            n3 = (n2 = (a = (n4 = a(inputStream, this.Q))));
        }
        if (!u) {
            if (n2 != 0) {
                this.O.put(com.easypano.tourweaver.d.a.f, new Integer(a(this.Q)));
            }
            a = (n3 = (n4 = a(inputStream, this.Q)));
        }
        if (!u) {
            if (n3 != 0) {
                this.O.put(com.easypano.tourweaver.d.a.g, new Integer(a(this.Q)));
            }
            n4 = (a = a(inputStream, this.Q));
        }
        if (!u) {
            if (a != 0) {
                this.O.put(com.easypano.tourweaver.d.a.e, new Integer(a(this.Q)));
            }
            n4 = this.O.get(com.easypano.tourweaver.d.a.h);
        }
        this.R.a(null, com.easypano.tourweaver.d.i.a | com.easypano.tourweaver.d.i.b, 0, 0, 0, 0, 0, n4, this.O.get(com.easypano.tourweaver.d.a.i), this.O.get(com.easypano.tourweaver.d.a.e), this.O.get(com.easypano.tourweaver.d.a.f));
        this.R.a(20);
    }
    
    private void c(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int intValue = 0;
        int intValue2 = 0;
        int n = 0;
        int n3;
        int a;
        final int n2 = a = (n3 = a(inputStream, this.Q));
        if (!u) {
            if (n2 != 0) {
                intValue = new Integer(a(this.Q));
                this.O.put(com.easypano.tourweaver.d.a.l, new Integer(intValue));
            }
            final int n4;
            a = (n4 = (n3 = a(inputStream, this.Q)));
        }
        if (!u) {
            if (n2 != 0) {
                intValue2 = new Integer(a(this.Q));
                this.O.put(com.easypano.tourweaver.d.a.n, new Integer(intValue2));
            }
            n3 = (a = a(inputStream, this.Q));
        }
        Label_0173: {
            if (!u) {
                if (a == 0) {
                    break Label_0173;
                }
                n3 = new Integer(a(this.Q));
            }
            n = n3;
            this.O.put(com.easypano.tourweaver.d.a.o, new Integer(n));
        }
        this.R.a(null, com.easypano.tourweaver.d.i.e, 0, 0, intValue, intValue2, n, 0, 0, 0, 0);
        this.R.a(12);
    }
    
    private void d(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int n3;
        int a;
        int n2;
        final int n = n2 = (a = (n3 = a(inputStream, this.Q)));
        if (!u) {
            if (n != 0) {
                this.O.put(com.easypano.tourweaver.d.a.q, new Integer(a(this.Q)));
            }
            final int n4;
            n2 = (n4 = (a = (n3 = a(inputStream, this.Q))));
        }
        if (!u) {
            if (n != 0) {
                this.O.put(com.easypano.tourweaver.d.a.r, new Integer(a(this.Q)));
            }
            a = (n2 = (n3 = a(inputStream, this.Q)));
        }
        if (!u) {
            if (n2 != 0) {
                this.O.put(com.easypano.tourweaver.d.a.s, new Integer(a(this.Q)));
            }
            n3 = (a = a(inputStream, this.Q));
        }
        Label_0203: {
            if (!u) {
                if (a != 0) {
                    this.O.put(com.easypano.tourweaver.d.a.t, new Integer(a(this.Q)));
                }
                if (u) {
                    break Label_0203;
                }
                n3 = a(inputStream, this.Q);
            }
            if (n3 != 0) {
                this.O.put(com.easypano.tourweaver.d.a.u, new Integer(a(this.Q)));
            }
        }
        this.R.a(20);
    }
    
    private void e(final InputStream inputStream) throws IOException {
        if (!com.easypano.tourweaver.d.i.u) {
            if (a(inputStream, this.Q) != 0) {
                this.O.put(com.easypano.tourweaver.d.a.x, new Integer(a(this.Q)));
            }
        }
        this.R.a(4);
    }
    
    private void f(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int n2;
        final int n = n2 = a(inputStream, this.Q);
        if (!u) {
            if (n != 0) {
                this.O.put(com.easypano.tourweaver.d.a.z, new Integer(a(this.Q)));
            }
            final int a;
            n2 = (a = a(inputStream, this.Q));
        }
        Label_0123: {
            if (!u) {
                if (n != 0) {
                    this.O.put(com.easypano.tourweaver.d.a.A, new Integer(a(this.Q)));
                }
                if (u) {
                    break Label_0123;
                }
                n2 = a(inputStream, this.Q);
            }
            if (n2 != 0) {
                this.O.put(com.easypano.tourweaver.d.a.B, new Integer(a(this.Q)));
            }
        }
        this.R.a(12);
    }
    
    private void g(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int a = -1;
        int a2 = 0;
        int a3 = 0;
        if (a(inputStream, this.Q) != 0) {}
        int n3;
        int a4;
        int n2;
        final int n = n2 = (a4 = (n3 = a(inputStream, this.Q)));
        if (!u) {
            if (n != 0) {
                a2 = a(this.Q);
            }
            final int n4;
            n2 = (n4 = (a4 = (n3 = a(inputStream, this.Q))));
        }
        if (!u) {
            if (n != 0) {
                a3 = a(this.Q);
            }
            a4 = (n2 = (n3 = a(inputStream, this.Q)));
        }
        if (!u) {
            if (n2 != 0) {
                a = a(this.Q);
            }
            n3 = (a4 = a(inputStream, this.Q));
        }
        if (!u) {
            if (a4 != 0) {
                this.O.put(com.easypano.tourweaver.d.a.H, new Integer(a(this.Q)));
            }
            n3 = 0;
        }
        int i = n3;
        while (true) {
            while (i < 4) {
                final int n5 = a(inputStream, this.Q);
                if (u) {
                    final byte[] array = new byte[n5];
                    final int a5 = a(inputStream, array);
                    if (!u) {
                        if (a5 != 0) {
                            this.O.put(com.easypano.tourweaver.d.a.I, array);
                        }
                        this.R.a(36 + array.length);
                    }
                    int intValue = a5;
                    int intValue2 = -1;
                    int j = 1;
                    while (true) {
                        while (j <= this.R.g()) {
                            final a a6 = this.R.r.get(com.easypano.tourweaver.d.a.p + new Integer(j).toString());
                            final int intValue3 = a6.e().get(com.easypano.tourweaver.d.a.u);
                            if (!u) {
                                int n8;
                                int n7;
                                final int n6 = n7 = (n8 = intValue3);
                                int n11;
                                int n10;
                                final int n9 = n10 = (n11 = a);
                                if (u) {
                                    if (!u) {
                                        if (n6 == n9) {
                                            System.out.println(com.easypano.tourweaver.d.a.S[1]);
                                            if (!u) {
                                                return;
                                            }
                                        }
                                        n8 = (n7 = intValue);
                                        n11 = (n10 = -1);
                                    }
                                    Label_0453: {
                                        if (!u) {
                                            if (n7 != n10) {
                                                break Label_0453;
                                            }
                                            n8 = intValue2;
                                            n11 = -1;
                                        }
                                        if (n8 == n11) {
                                            this.R.a(array, com.easypano.tourweaver.d.i.c, intValue, intValue2, 0, 0, 0, a2, a3, 0, 0);
                                            if (!u) {
                                                return;
                                            }
                                        }
                                    }
                                    this.R.a(array, com.easypano.tourweaver.d.i.d, intValue, intValue2, 0, 0, 0, a2, a3, 0, 0);
                                    return;
                                }
                                if (n6 == n9) {
                                    intValue = (int)a6.e().get(com.easypano.tourweaver.d.a.q);
                                    intValue2 = (int)a6.e().get(com.easypano.tourweaver.d.a.r);
                                    if (!u) {
                                        break;
                                    }
                                }
                                ++j;
                            }
                            if (u) {
                                break;
                            }
                        }
                        int n8;
                        final int n12;
                        int n7 = n12 = (n8 = a);
                        int n11;
                        final int n13;
                        int n10 = n13 = (n11 = -1);
                        continue;
                    }
                }
                ++i;
                if (u) {
                    break;
                }
            }
            final int n5 = this.O.get(com.easypano.tourweaver.d.a.H);
            continue;
        }
    }
    
    public static synchronized int a(final InputStream inputStream, final byte[] array) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int n = 0;
        while (inputStream != null && array != null) {
            final int n2 = n;
            if (u || n2 >= array.length) {
                return n2;
            }
            final int read = inputStream.read(array, n, array.length - n);
            final int n3 = -1;
            if (!u && (read == n3 && !u)) {
                goto Label_0063;
            }
            n = read + n3;
            if (u) {
                goto Label_0063;
            }
        }
        goto Label_0063;
    }
    
    public void a(final i r) {
        this.R = r;
    }
    
    static {
        com.easypano.tourweaver.d.a.S = new String[] { z(z("l&%\u0016'%bu\u001drt3")), z(z("o-:\u001d`86;\u0007'y+:\u001e',lc")) };
        com.easypano.tourweaver.d.a.a = z(z("j0:\u0007"));
        com.easypano.tourweaver.d.a.b = z(z("q13\u001c"));
        com.easypano.tourweaver.d.a.c = z(z("k<0\u001d"));
        com.easypano.tourweaver.d.a.d = z(z("k<=\u0017"));
        com.easypano.tourweaver.d.a.e = z(z("j87"));
        com.easypano.tourweaver.d.a.f = z(z("u01\u0016"));
        com.easypano.tourweaver.d.a.g = z(z("q24\u0014bQ11\u0016\u007f"));
        com.easypano.tourweaver.d.a.h = z(z("o61\u0007o"));
        com.easypano.tourweaver.d.a.i = z(z("p:<\u0014ol"));
        com.easypano.tourweaver.d.a.j = z(z("l69\u0016"));
        com.easypano.tourweaver.d.a.k = z(z("l6=\u0017"));
        com.easypano.tourweaver.d.a.l = z(z("v*8"));
        com.easypano.tourweaver.d.a.m = z(z("u01\u0016"));
        com.easypano.tourweaver.d.a.n = z(z("o1 \u001e"));
        com.easypano.tourweaver.d.a.o = z(z("p1 \u001e"));
        com.easypano.tourweaver.d.a.p = z(z("l69\u0003"));
        com.easypano.tourweaver.d.a.q = "x";
        com.easypano.tourweaver.d.a.r = "y";
        com.easypano.tourweaver.d.a.s = z(z("o61\u0007o"));
        com.easypano.tourweaver.d.a.t = z(z("p:<\u0014ol"));
        com.easypano.tourweaver.d.a.u = z(z("q24\u0016`Q11\u0016\u007f"));
        com.easypano.tourweaver.d.a.v = z(z("q11\u0007"));
        com.easypano.tourweaver.d.a.w = z(z("q+=\u0017"));
        com.easypano.tourweaver.d.a.x = z(z("v*8"));
        com.easypano.tourweaver.d.a.y = z(z("q11\u000b"));
        com.easypano.tourweaver.d.a.z = z(z("q11\u0016\u007f"));
        com.easypano.tourweaver.d.a.A = z(z("|>!\u0012H~9&\u0016s"));
        com.easypano.tourweaver.d.a.B = z(z("|>!\u0012Tq%0"));
        com.easypano.tourweaver.d.a.C = z(z("l;4\u0007"));
        com.easypano.tourweaver.d.a.D = z(z("q24\u0014"));
        com.easypano.tourweaver.d.a.E = z(z("~0'\u001efl"));
        com.easypano.tourweaver.d.a.F = z(z("o61\u0007o"));
        com.easypano.tourweaver.d.a.G = z(z("p:<\u0014ol"));
        com.easypano.tourweaver.d.a.H = z(z("|>!\u0012Tq%0"));
        com.easypano.tourweaver.d.a.I = z(z("q24\u0014b\\>!\u0012"));
        com.easypano.tourweaver.d.a.J = z(z("q11\u0016\u007f"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= '\u0007';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0018';
                            break;
                        }
                        case 1: {
                            c2 = '_';
                            break;
                        }
                        case 2: {
                            c2 = 'U';
                            break;
                        }
                        case 3: {
                            c2 = 's';
                            break;
                        }
                        default: {
                            c2 = '\u0007';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
