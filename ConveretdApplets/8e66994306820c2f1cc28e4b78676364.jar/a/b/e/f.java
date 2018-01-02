// 
// Decompiled by Procyon v0.5.30
// 

package a.b.e;

import a.b.i.k;
import org.a.a.a.a;
import a.b.g.b;
import a.b.k.h;
import a.b.i.i;

public class f extends e
{
    private static org.a.c.f i;
    private i j;
    private h k;
    private String l;
    private org.a.a.e m;
    private String n;
    private int o;
    private static String[] z;
    
    public f() {
        this(f.z[4]);
    }
    
    protected f(final String n) {
        this.o = 2;
        this.n = n;
        if (f.i == null) {
            f.i = a.b.g.b.a().d().a(this.getClass().getName());
        }
    }
    
    public void a(final g g) throws c {
        try {
            final a.b.i.h a = g.a();
            if (!(a instanceof i)) {
                f.i.c(f.z[1]);
                throw new c(f.z[3]);
            }
            this.a((i)a);
            this.a(g.b());
            this.a(g.f());
            this.m = this.b(g);
            this.l = g.b();
            final org.a.a.c[] g2 = g.g();
            for (int n = 0; g2 != null && n < g2.length; ++n) {
                this.m.a(g2[n]);
            }
            this.m.a(g.d());
            this.m.a((long)g.e());
        }
        catch (d d) {
            f.i.c(f.z[0] + d);
            throw new c(f.z[2], d);
        }
    }
    
    protected org.a.a.e b(final g g) throws c {
        try {
            return new org.a.a.a.a(g.a(), g.f(), g.c(), this.n, a.b.g.b.a().c().a());
        }
        catch (d d) {
            f.i.b(f.z[13] + d.toString());
            throw new c(f.z[13] + d.toString(), d);
        }
    }
    
    protected a.b.h.c.d[] b(final k[] array) throws c {
        try {
            final byte[] a = this.a().a(array);
            if (a == null) {
                throw new c(f.z[12]);
            }
            int n = 0;
            int n2 = 0;
            final org.a.a.b[] array2 = new org.a.a.b[this.o];
            byte[] a2 = null;
            while (n2 == 0 && n < this.o) {
                try {
                    a2 = this.m.a(this.l, a);
                    n2 = 1;
                }
                catch (org.a.a.b b) {
                    array2[n] = b;
                    ++n;
                    f.i.d(f.z[6] + n + f.z[8], b);
                }
            }
            if (a2 == null) {
                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < n; ++i) {
                    sb.append(f.z[7]);
                    sb.append(i + 1);
                    sb.append(f.z[10]);
                    sb.append(array2[i]);
                }
                f.i.c(f.z[5]);
                throw new c(f.z[9] + sb.toString());
            }
            return this.b().a(a2);
        }
        catch (NullPointerException ex) {
            f.i.d(f.z[11], ex);
            throw new c(ex);
        }
        catch (ClassCastException ex2) {
            f.i.d(f.z[11], ex2);
            throw new c(ex2);
        }
        catch (a.b.i.a a3) {
            f.i.d(f.z[11], a3);
            throw new c(a3);
        }
        catch (a.b.k.a a4) {
            f.i.d(f.z[11], a4);
            throw new c(a4);
        }
    }
    
    protected i a() {
        return this.j;
    }
    
    protected void a(final i j) throws c {
        this.j = j;
    }
    
    protected h b() {
        return this.k;
    }
    
    protected void a(final h k) throws c {
        this.k = k;
    }
    
    static {
        final String[] z = new String[14];
        final int n = 0;
        final char[] charArray = "d[P|\u0016T\u0015EqZRZ_x\u0013V@C{ZF\\EvZE]T>\u001dXCTpZATC\u007f\u0017TATl\t\u000b\u0015".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '1';
                    break;
                }
                case 1: {
                    c2 = '5';
                    break;
                }
                case 2: {
                    c2 = '1';
                    break;
                }
                case 3: {
                    c2 = '\u001e';
                    break;
                }
                default: {
                    c2 = 'z';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "x[\u0011j\u0012T\u0015Vw\fT[\u0011n\u001bCT\\{\u000eTGB2ZE]T>(TDD{\tErTp\u001fCTEq\b\u0011BPmZ^S\u0011j\u0012T\u0015Xp\u0019^GC{\u0019E\u0015Eg\nT\u001b".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '1';
                    break;
                }
                case 1: {
                    c4 = '5';
                    break;
                }
                case 2: {
                    c4 = '1';
                    break;
                }
                case 3: {
                    c4 = '\u001e';
                    break;
                }
                default: {
                    c4 = 'z';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "d[P|\u0016T\u0015EqZRZ_x\u0013V@C{ZF\\EvZE]T>\u001dXCTpZATC\u007f\u0017TATl\t\u001f".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '1';
                    break;
                }
                case 1: {
                    c6 = '5';
                    break;
                }
                case 2: {
                    c6 = '1';
                    break;
                }
                case 3: {
                    c6 = '\u001e';
                    break;
                }
                default: {
                    c6 = 'z';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "e]T>\u001dXCTpZcP@k\u001fBAv{\u0014TGPj\u0015C\u0015Eg\nT\u0015XmZ_ZE>\tDEAq\bEPU0".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '1';
                    break;
                }
                case 1: {
                    c8 = '5';
                    break;
                }
                case 2: {
                    c8 = '1';
                    break;
                }
                case 3: {
                    c8 = '\u001e';
                    break;
                }
                default: {
                    c8 = 'z';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "azbJ".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '1';
                    break;
                }
                case 1: {
                    c10 = '5';
                    break;
                }
                case 2: {
                    c10 = '1';
                    break;
                }
                case 3: {
                    c10 = '\u001e';
                    break;
                }
                default: {
                    c10 = 'z';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "d[P|\u0016T\u0015EqZVP_{\bPAT>>XFAr\u001bHfTjZU@T>\u000e^\u0015P|\u0015GP\u0011P\u001fEpI}\u001fAAXq\u0014B\u001b".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '1';
                    break;
                }
                case 1: {
                    c12 = '5';
                    break;
                }
                case 2: {
                    c12 = '1';
                    break;
                }
                case 3: {
                    c12 = '\u001e';
                    break;
                }
                default: {
                    c12 = 'z';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "cP@k\u001fBA\u0011\u007f\u000eEP\\n\u000e\u0011".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '1';
                    break;
                }
                case 1: {
                    c14 = '5';
                    break;
                }
                case 2: {
                    c14 = '1';
                    break;
                }
                case 3: {
                    c14 = '\u001e';
                    break;
                }
                default: {
                    c14 = 'z';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u0011\u0015\u0019".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '1';
                    break;
                }
                case 1: {
                    c16 = '5';
                    break;
                }
                case 2: {
                    c16 = '1';
                    break;
                }
                case 3: {
                    c16 = '\u001e';
                    break;
                }
                default: {
                    c16 = 'z';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0011S^lZuTE\u007f)TA\u0011x\u001bXYTzT".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '1';
                    break;
                }
                case 1: {
                    c18 = '5';
                    break;
                }
                case 2: {
                    c18 = '1';
                    break;
                }
                case 3: {
                    c18 = '\u001e';
                    break;
                }
                default: {
                    c18 = 'z';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "d[P|\u0016T\u0015EqZVP_{\bPAT>>XFAr\u001bHfTjZU@T>\u000e^\u0015\u007f{\u000etMR{\nE\\^p\t\u000b".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '1';
                    break;
                }
                case 1: {
                    c20 = '5';
                    break;
                }
                case 2: {
                    c20 = '1';
                    break;
                }
                case 3: {
                    c20 = '\u001e';
                    break;
                }
                default: {
                    c20 = 'z';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u0018\u0015".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '1';
                    break;
                }
                case 1: {
                    c22 = '5';
                    break;
                }
                case 2: {
                    c22 = '1';
                    break;
                }
                case 3: {
                    c22 = '\u001e';
                    break;
                }
                default: {
                    c22 = 'z';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "tGCq\b\u0011VC{\u001bE\\_yZuTE\u007f)TA\u000b".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '1';
                    break;
                }
                case 1: {
                    c24 = '5';
                    break;
                }
                case 2: {
                    c24 = '1';
                    break;
                }
                case 3: {
                    c24 = '\u001e';
                    break;
                }
                default: {
                    c24 = 'z';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "d[P|\u0016T\u0015EqZVP_{\bPAT>\bTDD{\tE\u0015U\u007f\u000eP\u0015Sg\u000eTF".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '1';
                    break;
                }
                case 1: {
                    c26 = '5';
                    break;
                }
                case 2: {
                    c26 = '1';
                    break;
                }
                case 3: {
                    c26 = '\u001e';
                    break;
                }
                default: {
                    c26 = 'z';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "d[P|\u0016T\u0015EqZRGT\u007f\u000eT\u0015P>4TAc{\u000bDPBjZ^W[{\u0019E\u0015Uk\u001f\u0011A^>\u001fCG^l@\u0011".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = '1';
                    break;
                }
                case 1: {
                    c28 = '5';
                    break;
                }
                case 2: {
                    c28 = '1';
                    break;
                }
                case 3: {
                    c28 = '\u001e';
                    break;
                }
                default: {
                    c28 = 'z';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        f.z = z;
    }
}
