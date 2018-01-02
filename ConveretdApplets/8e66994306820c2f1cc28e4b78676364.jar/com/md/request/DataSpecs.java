// 
// Decompiled by Procyon v0.5.30
// 

package com.md.request;

import a.b.p.b;
import a.a.e.a;
import java.util.Vector;
import a.b.i.g;

public class DataSpecs extends g
{
    private int d;
    private long e;
    private int f;
    private long g;
    private Vector h;
    private static String[] z;
    
    public DataSpecs(final String s, final String s2, final int n) {
        super(s, s2, n);
        this.d = -1;
        this.e = -1L;
        this.f = -1;
        this.g = -1L;
        this.h = null;
        this.h = new Vector();
    }
    
    public void b(final int d) {
        this.d = d;
    }
    
    public int b() {
        return this.d;
    }
    
    public void a(final long e) {
        this.e = e;
    }
    
    public long c() {
        return this.e;
    }
    
    public void c(final int f) {
        this.f = f;
    }
    
    public int d() {
        return this.f;
    }
    
    public void b(final long g) {
        this.g = g;
    }
    
    public long e() {
        return this.g;
    }
    
    public void a(final byte[] array) {
        if (array == null) {
            return;
        }
        this.h.addElement(array);
    }
    
    public byte[] f() {
        int n = 0;
        for (int i = 0; i < this.h.size(); ++i) {
            n += ((byte[])this.h.elementAt(i)).length;
        }
        final byte[] array = new byte[n];
        int n2 = 0;
        for (int j = 0; j < this.h.size(); ++j) {
            final byte[] array2 = this.h.elementAt(j);
            for (int k = 0; k < array2.length; ++k) {
                array[n2] = array2[k];
                ++n2;
            }
        }
        return array;
    }
    
    public int a() {
        return 56;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null || array.length - n != this.a()) {
            throw new a(DataSpecs.z[10] + this.a() + DataSpecs.z[9] + ((array == null) ? "0" : ("" + (array.length - n))) + DataSpecs.z[1]);
        }
        int n2 = n;
        try {
            this.b(a.b.p.b.a(array, n2));
            n2 += 4;
            this.a(a.b.p.b.b(array, n2));
            n2 += 8;
            this.c(a.b.p.b.a(array, n2));
            n2 += 4;
            this.b(a.b.p.b.b(array, n2));
            n2 += 8;
            this.a(a.b.p.b.a(array, n2, 16));
            n2 += 16;
            this.a(a.b.p.b.a(array, n2, 16));
            n2 += 16;
        }
        catch (a.b.p.a a) {
            throw new a(DataSpecs.z[7], a);
        }
        if (n2 - n != this.a()) {
            throw new a(DataSpecs.z[6] + this.a() + DataSpecs.z[8] + (n2 - n) + DataSpecs.z[0]);
        }
        return n2 - n;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null || array.length - n < this.a()) {
            throw new a(DataSpecs.z[2] + this.a() + DataSpecs.z[3] + ((array == null) ? "0" : ("" + (array.length - n))) + DataSpecs.z[1]);
        }
        int n2 = n;
        try {
            a.b.p.b.a(this.b(), array, n2);
            n2 += 4;
            a.b.p.b.a(this.c(), array, n2);
            n2 += 8;
            a.b.p.b.a(this.d(), array, n2);
            n2 += 4;
            a.b.p.b.a(this.e(), array, n2);
            n2 += 8;
            final byte[] f = this.f();
            System.arraycopy(f, 0, array, n2, f.length);
            n2 += 32;
        }
        catch (a.b.p.a a) {
            throw new a(DataSpecs.z[4], a);
        }
        if (n2 - n != this.a()) {
            throw new a(DataSpecs.z[6] + this.a() + DataSpecs.z[5] + (n2 - n) + DataSpecs.z[0]);
        }
        return n2 - n;
    }
    
    public byte[] a() throws a {
        final int a = this.a();
        if (a == 0) {
            return new byte[0];
        }
        byte[] array = new byte[a];
        final int b = this.b(array, 0);
        if (b < array.length) {
            final byte[] array2 = new byte[b];
            System.arraycopy(array, 0, array2, 0, b);
            array = array2;
        }
        return array;
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "2i".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u001b';
                    break;
                }
                case 1: {
                    c2 = 'G';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = 'd';
                    break;
                }
                default: {
                    c2 = '0';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = ";0p\u0017\u0010i\"r\u0001Ym\"uJ".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u001b';
                    break;
                }
                case 1: {
                    c4 = 'G';
                    break;
                }
                case 2: {
                    c4 = '\u0011';
                    break;
                }
                case 3: {
                    c4 = 'd';
                    break;
                }
                default: {
                    c4 = '0';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "O/tDxr y\bQu#t\u0016b~6d\u0001Co\u000e\u007f\u0002_i*p\u0010Yt)1\u0001Hk\"r\u0010C;3~DB~3d\u0016^;".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u001b';
                    break;
                }
                case 1: {
                    c6 = 'G';
                    break;
                }
                case 2: {
                    c6 = '\u0011';
                    break;
                }
                case 3: {
                    c6 = 'd';
                    break;
                }
                default: {
                    c6 = '0';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = ";#p\u0010Q g~\n\\bgt\n_n yDBt(|DVt51".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u001b';
                    break;
                }
                case 1: {
                    c8 = 'G';
                    break;
                }
                case 2: {
                    c8 = '\u0011';
                    break;
                }
                case 3: {
                    c8 = 'd';
                    break;
                }
                default: {
                    c8 = '0';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "X&\u007f\n_ogc\u0001Di.t\u0012U;#p\u0010Q;!c\u000b];5t\u0015E~4eD@z5p\tUo\"c\u0017\u001e".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u001b';
                    break;
                }
                case 1: {
                    c10 = 'G';
                    break;
                }
                case 2: {
                    c10 = '\u0011';
                    break;
                }
                case 3: {
                    c10 = 'd';
                    break;
                }
                default: {
                    c10 = '0';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "2gu\u000bUhg\u007f\u000bD;\"`\u0011Qwgc\u0001Di.t\u0012U\u007fgu\u0005Dzg9".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u001b';
                    break;
                }
                case 1: {
                    c12 = 'G';
                    break;
                }
                case 2: {
                    c12 = '\u0011';
                    break;
                }
                case 3: {
                    c12 = 'd';
                    break;
                }
                default: {
                    c12 = '0';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "R)e\u0001Bu&}Dui5~\u0016\u0010o(1,Y|/}\u0005^\u007f\"c6Uj2t\u0017DR)w\u000bBv&e\r_u}1\u0007Qk&r\rDbg9".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u001b';
                    break;
                }
                case 1: {
                    c14 = 'G';
                    break;
                }
                case 2: {
                    c14 = '\u0011';
                    break;
                }
                case 3: {
                    c14 = 'd';
                    break;
                }
                default: {
                    c14 = '0';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "X&\u007f\n_ogb\u0010_i\"1\u0000Qo&1\r^o(1\u0016Uj2t\u0017D;7p\u0016Qv\"e\u0001Bhi".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u001b';
                    break;
                }
                case 1: {
                    c16 = 'G';
                    break;
                }
                case 2: {
                    c16 = '\u0011';
                    break;
                }
                case 3: {
                    c16 = 'd';
                    break;
                }
                default: {
                    c16 = '0';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "2gu\u000bUhg\u007f\u000bD;\"`\u0011Qwgw\r\\w\"uDTz3pD\u0018".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u001b';
                    break;
                }
                case 1: {
                    c18 = 'G';
                    break;
                }
                case 2: {
                    c18 = '\u0011';
                    break;
                }
                case 3: {
                    c18 = 'd';
                    break;
                }
                default: {
                    c18 = '0';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = ";#p\u0010Q;3~DUv%t\u0000\u000b;(\u007f\bI;".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u001b';
                    break;
                }
                case 1: {
                    c20 = 'G';
                    break;
                }
                case 2: {
                    c20 = '\u0011';
                    break;
                }
                case 3: {
                    c20 = 'd';
                    break;
                }
                default: {
                    c20 = '0';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "O/tDxr y\bQu#t\u0016b~6d\u0001Co\u000e\u007f\u0002_i*p\u0010Yt)1\u0001Hk\"r\u0010C;".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\u001b';
                    break;
                }
                case 1: {
                    c22 = 'G';
                    break;
                }
                case 2: {
                    c22 = '\u0011';
                    break;
                }
                case 3: {
                    c22 = 'd';
                    break;
                }
                default: {
                    c22 = '0';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        DataSpecs.z = z;
    }
}
