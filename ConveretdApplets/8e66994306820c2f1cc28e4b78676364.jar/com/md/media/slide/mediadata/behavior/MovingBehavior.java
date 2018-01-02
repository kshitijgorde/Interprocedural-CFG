// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.behavior;

import a.b.c.a;
import a.b.a.b.b;

public class MovingBehavior implements b
{
    public long a;
    public int b;
    public int c;
    private int d;
    private int e;
    private static String[] z;
    
    public long a() {
        return this.a;
    }
    
    public void a(final long a) {
        this.a = a;
    }
    
    public int b() {
        return this.b;
    }
    
    public void c(final int b) {
        this.b = b;
    }
    
    public int c() {
        return this.c;
    }
    
    public void d(final int c) {
        this.c = c;
    }
    
    public int a() {
        return 16;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(MovingBehavior.z[0]);
        }
        if (n >= array.length || n < 0) {
            throw new a(MovingBehavior.z[2] + n + MovingBehavior.z[1] + array.length);
        }
        int n2 = 0;
        try {
            this.a(a.b.p.b.b(array, n + n2));
            n2 += 8;
            this.c(a.b.p.b.a(array, n + n2));
            n2 += 4;
            this.d(a.b.p.b.a(array, n + n2));
            n2 += 4;
        }
        catch (a.b.p.a a) {
            throw new a(MovingBehavior.z[3] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(MovingBehavior.z[0]);
        }
        if (n >= array.length || n < 0) {
            throw new a(MovingBehavior.z[2] + n + MovingBehavior.z[1] + array.length);
        }
        final int n2 = 0;
        int n5;
        try {
            final int n3 = n2 + a.b.p.b.a(this.a(), array, n + n2);
            final int n4 = n3 + a.b.p.b.a(this.b(), array, n + n3);
            n5 = n4 + a.b.p.b.a(this.c(), array, n + n4);
        }
        catch (a.b.p.a a) {
            throw new a(MovingBehavior.z[6] + a.toString());
        }
        return n5;
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
    
    public MovingBehavior() {
        this(4, 0);
    }
    
    public MovingBehavior(final int n, final int n2) {
        this.a(n);
        this.b(n2);
    }
    
    public a.b.a.a.a a() {
        return new a.b.a.a.b(this.d, this.e);
    }
    
    public int d() {
        return this.d;
    }
    
    public void a(final int d) {
        this.d = d;
    }
    
    public int e() {
        return this.e;
    }
    
    public void b(final int e) {
        this.e = e;
    }
    
    public boolean equals(final Object o) {
        try {
            final a.b.a.a.b b = (a.b.a.a.b)o;
            return this.d() == b.a() && this.e() == b.b();
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        return new String(MovingBehavior.z[4] + this.e() + MovingBehavior.z[5] + this.d() + "}");
    }
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).d(MovingBehavior.z[7]);
            return null;
        }
    }
    
    static {
        final String[] z = new String[8];
        final int n = 0;
        final char[] charArray = ":w_\u0007\u001b\u0010cG\n\u001b\u0015pA\nBZ".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 't';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = '3';
                    break;
                }
                case 3: {
                    c2 = 'k';
                    break;
                }
                default: {
                    c2 = ';';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "Tk@KU\u001bv\u0013\u0002UTv[\u000e\u001b\u0006c]\f^T2\u0013F\u001b".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 't';
                    break;
                }
                case 1: {
                    c4 = '\u0002';
                    break;
                }
                case 2: {
                    c4 = '3';
                    break;
                }
                case 3: {
                    c4 = 'k';
                    break;
                }
                default: {
                    c4 = ';';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "5pA\nBTk]\u000f^\f\"\\\u001eOTmUKY\u001bw]\u000fHN\"".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 't';
                    break;
                }
                case 1: {
                    c6 = '\u0002';
                    break;
                }
                case 2: {
                    c6 = '3';
                    break;
                }
                case 3: {
                    c6 = 'k';
                    break;
                }
                default: {
                    c6 = ';';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = " jVK\\\u001dtV\u0005\u001b\u0015pA\nBTk@KU\u001bv\u0013\u0007Z\u0006eVK^\u001amF\fSTv\\K]\u001dn_KO\u001ck@KT\u0016hV\bON\"".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 't';
                    break;
                }
                case 1: {
                    c8 = '\u0002';
                    break;
                }
                case 2: {
                    c8 = '3';
                    break;
                }
                case 3: {
                    c8 = 'k';
                    break;
                }
                default: {
                    c8 = ';';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "9mE\u0002U\u0013@V\u0003Z\u0002k\\\u0019\u001b\u000ffZ\u0019^\u0017vZ\u0004UN\"".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 't';
                    break;
                }
                case 1: {
                    c10 = '\u0002';
                    break;
                }
                case 2: {
                    c10 = '3';
                    break;
                }
                case 3: {
                    c10 = 'k';
                    break;
                }
                default: {
                    c10 = ';';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "O\"@\u001b^\u0011f\tK".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 't';
                    break;
                }
                case 1: {
                    c12 = '\u0002';
                    break;
                }
                case 2: {
                    c12 = '3';
                    break;
                }
                case 3: {
                    c12 = 'k';
                    break;
                }
                default: {
                    c12 = ';';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = " jVK\\\u001dtV\u0005\u001b\u0015pA\nBTk@KU\u001bv\u0013\u0007Z\u0006eVK^\u001amF\fSTv\\KS\u001bnWKO\u001cg\u0013\bT\u001avV\u0005O\u0007\"\\\r\u001b\u0000jZ\u0018\u001b\u001b`Y\u000eX\u00008\u0013".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 't';
                    break;
                }
                case 1: {
                    c14 = '\u0002';
                    break;
                }
                case 2: {
                    c14 = '3';
                    break;
                }
                case 3: {
                    c14 = 'k';
                    break;
                }
                default: {
                    c14 = ';';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "9mE\u0002U\u0013@V\u0003Z\u0002k\\\u0019\u001b\u001dq\u0013\u0005T\u0000\"P\u0007T\u001agR\tW\u0011,".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 't';
                    break;
                }
                case 1: {
                    c16 = '\u0002';
                    break;
                }
                case 2: {
                    c16 = '3';
                    break;
                }
                case 3: {
                    c16 = 'k';
                    break;
                }
                default: {
                    c16 = ';';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        MovingBehavior.z = z;
    }
}
