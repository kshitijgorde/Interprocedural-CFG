// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata;

import a.b.h.c.d;
import a.b.c.a;
import a.b.a.b.c;
import a.b.o.b.e;
import java.io.Serializable;
import a.b.h.a.b;

public class MediaSet implements b, Serializable
{
    private long a;
    private e b;
    private a.b.a.b.c c;
    private String d;
    private static String[] z;
    
    public long a() {
        return this.a;
    }
    
    public void a(final long a) {
        this.a = a;
    }
    
    public int a() {
        int b = this.b();
        if (this.b() != null) {
            b += this.b().a();
        }
        if (this.a() != null) {
            b += this.a().a();
        }
        return b;
    }
    
    protected int b() {
        return 8;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(MediaSet.z[9]);
        }
        if (n >= array.length || n < 0) {
            throw new a(MediaSet.z[7] + n + MediaSet.z[6] + array.length);
        }
        int n2 = 0;
        if (this.b() != null) {
            n2 += this.b().a(array, n + n2);
        }
        if (this.a() != null) {
            n2 += this.a().a(array, n + n2);
        }
        try {
            this.a(a.b.p.b.b(array, n + n2));
            n2 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(MediaSet.z[10] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(MediaSet.z[9]);
        }
        if (n >= array.length || n < 0) {
            throw new a(MediaSet.z[7] + n + MediaSet.z[6] + array.length);
        }
        int n2 = 0;
        if (this.b() != null) {
            n2 += this.b().b(array, n + n2);
        }
        if (this.a() != null) {
            n2 += this.a().b(array, n + n2);
        }
        try {
            a.b.p.b.a(this.a(), array, n + n2);
            n2 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(MediaSet.z[8] + a.toString());
        }
        return n2;
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
    
    public MediaSet() {
        this.a((e)null);
        this.a((a.b.a.b.c)null);
        this.a((String)null);
    }
    
    public e a() {
        return this.b;
    }
    
    public void a(final e b) {
        this.b = b;
    }
    
    public a.b.a.b.c b() {
        return this.c;
    }
    
    public void a(final a.b.a.b.c c) {
        this.c = c;
    }
    
    public String c() {
        return this.d;
    }
    
    public void a(final String d) {
        this.d = d;
    }
    
    public a.b.h.c.d d() throws a.b.o.d {
        return new a.b.h.c.b(this.a().a(), this.b().a(), this.c());
    }
    
    public Object clone() {
        try {
            final MediaSet set = (MediaSet)super.clone();
            if (this.a() != null) {
                set.a((e)this.a().clone());
            }
            if (this.b() != null) {
                set.a((a.b.a.b.c)this.b().clone());
            }
            return set;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).d(MediaSet.z[5]);
            return null;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(MediaSet.z[3] + this.c() + MediaSet.z[1]);
        sb.append(MediaSet.z[4]);
        if (this.a() == null) {
            sb.append(MediaSet.z[2]);
        }
        else {
            sb.append(this.a().toString());
        }
        sb.append(MediaSet.z[0]);
        if (this.b() == null) {
            sb.append(MediaSet.z[2]);
        }
        else {
            sb.append(this.b().toString());
        }
        sb.append("}");
        return sb.toString();
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = ",\u001a\u0010\u000f|rY\u0001S:".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0017';
                    break;
                }
                case 1: {
                    c2 = ':';
                    break;
                }
                case 2: {
                    c2 = 'u';
                    break;
                }
                case 3: {
                    c2 = 'i';
                    break;
                }
                default: {
                    c2 = '\u001a';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = ",\u001a".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0017';
                    break;
                }
                case 1: {
                    c4 = ':';
                    break;
                }
                case 2: {
                    c4 = 'u';
                    break;
                }
                case 3: {
                    c4 = 'i';
                    break;
                }
                default: {
                    c4 = '\u001a';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "yO\u0019\u0005".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0017';
                    break;
                }
                case 1: {
                    c6 = ':';
                    break;
                }
                case 2: {
                    c6 = 'u';
                    break;
                }
                case 3: {
                    c6 = 'i';
                    break;
                }
                default: {
                    c6 = '\u001a';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "Z_\u0011\u0000{D_\u0001Ia^~OI".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0017';
                    break;
                }
                case 1: {
                    c8 = ':';
                    break;
                }
                case 2: {
                    c8 = 'u';
                    break;
                }
                case 3: {
                    c8 = 'i';
                    break;
                }
                default: {
                    c8 = '\u001a';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "tU\u001b\u001d\u007fyNOI".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0017';
                    break;
                }
                case 1: {
                    c10 = ':';
                    break;
                }
                case 2: {
                    c10 = 'u';
                    break;
                }
                case 3: {
                    c10 = 'i';
                    break;
                }
                default: {
                    c10 = '\u001a';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "Z_\u0011\u0000{D_\u0001Isd\u001a\u001b\u0006n7Y\u0019\u0006tr[\u0017\u0005\u007f9".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0017';
                    break;
                }
                case 1: {
                    c12 = ':';
                    break;
                }
                case 2: {
                    c12 = 'u';
                    break;
                }
                case 3: {
                    c12 = 'i';
                    break;
                }
                default: {
                    c12 = '\u001a';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "7S\u0006ItxNU\u0000t7N\u001d\f:e[\u001b\u000e\u007f7\nUD:".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0017';
                    break;
                }
                case 1: {
                    c14 = ':';
                    break;
                }
                case 2: {
                    c14 = 'u';
                    break;
                }
                case 3: {
                    c14 = 'i';
                    break;
                }
                default: {
                    c14 = '\u001a';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "VH\u0007\bc7S\u001b\r\u007fo\u001a\u001a\u001cn7U\u0013IxxO\u001b\ri-\u001a".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0017';
                    break;
                }
                case 1: {
                    c16 = ':';
                    break;
                }
                case 2: {
                    c16 = 'u';
                    break;
                }
                case 3: {
                    c16 = 'i';
                    break;
                }
                default: {
                    c16 = '\u001a';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "CR\u0010I}~L\u0010\u0007:vH\u0007\bc7S\u0006ItxNU\u0005{e]\u0010I\u007fyU\u0000\u000er7N\u001aIrxV\u0011In\u007f_U\nuyN\u0010\u0007nd\u001a\u001a\u000f:cR\u001c\u001a:xX\u001f\fyc\u001a\u0011\u001c\u007f7N\u001aS:".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0017';
                    break;
                }
                case 1: {
                    c18 = ':';
                    break;
                }
                case 2: {
                    c18 = 'u';
                    break;
                }
                case 3: {
                    c18 = 'i';
                    break;
                }
                default: {
                    c18 = '\u001a';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "YO\u0019\u0005:s[\u0001\b:vH\u0007\bc9".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0017';
                    break;
                }
                case 1: {
                    c20 = ':';
                    break;
                }
                case 2: {
                    c20 = 'u';
                    break;
                }
                case 3: {
                    c20 = 'i';
                    break;
                }
                default: {
                    c20 = '\u001a';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "CR\u0010I}~L\u0010\u0007:vH\u0007\bc7S\u0006ItxNU\u0005{e]\u0010I\u007fyU\u0000\u000er7N\u001aI|~V\u0019In\u007fS\u0006IuuP\u0010\nn-\u001a".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\u0017';
                    break;
                }
                case 1: {
                    c22 = ':';
                    break;
                }
                case 2: {
                    c22 = 'u';
                    break;
                }
                case 3: {
                    c22 = 'i';
                    break;
                }
                default: {
                    c22 = '\u001a';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        MediaSet.z = z;
    }
}
