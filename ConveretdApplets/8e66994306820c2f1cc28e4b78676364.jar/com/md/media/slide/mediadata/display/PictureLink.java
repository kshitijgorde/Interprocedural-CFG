// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.d;
import a.b.o.c.h;
import a.b.o.a.a.f;
import a.b.p.b;
import a.b.c.a;
import a.b.o.b.c;

public class PictureLink extends PictureChanger implements c
{
    private long h;
    private String i;
    private boolean j;
    private static String[] z;
    
    public long b() {
        return this.h;
    }
    
    public void c(final long h) {
        this.h = h;
    }
    
    public int a() {
        return super.a() + 8;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(PictureLink.z[5]);
        }
        if (n >= array.length || n < 0) {
            throw new a(PictureLink.z[6] + n + PictureLink.z[7] + array.length);
        }
        final int n2 = 0;
        int n3 = n2 + super.a(array, n + n2);
        try {
            this.c(b.b(array, n + n3));
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(PictureLink.z[8] + a.toString());
        }
        return n3;
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(PictureLink.z[5]);
        }
        if (n >= array.length || n < 0) {
            throw new a(PictureLink.z[6] + n + PictureLink.z[7] + array.length);
        }
        final int n2 = 0;
        int n3 = n2 + super.b(array, n + n2);
        try {
            b.a(this.b(), array, n + n3);
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(PictureLink.z[11] + a.toString());
        }
        return n3;
    }
    
    public PictureLink() {
        this(null, (String)null);
    }
    
    public PictureLink(final a.b.o.a.a.f f, final String s) {
        this(f, null, s);
    }
    
    public PictureLink(final a.b.o.a.a.f f, final a.b.o.a.a.f f2, final String s) {
        super(f, f2, true, false);
        this.i = null;
        this.j = false;
        this.a(s);
    }
    
    public String a() {
        return this.i;
    }
    
    public void a(final String i) {
        this.i = i;
    }
    
    public h a() throws d {
        a.b.o.a.b.c c = null;
        a.b.o.a.b.c c2 = null;
        try {
            if (super.d() != null) {
                c = (a.b.o.a.b.c)super.d().a();
            }
            if (super.b() != null) {
                c2 = (a.b.o.a.b.c)super.b().a();
            }
        }
        catch (a.b.o.a.c c3) {
            throw new d(PictureLink.z[10], c3);
        }
        catch (ClassCastException ex) {
            throw new d(PictureLink.z[9]);
        }
        a.b.o.c.c c4;
        if (c2 != null) {
            c4 = new a.b.o.c.c(c, c2, this.a());
        }
        else if (c != null) {
            c4 = new a.b.o.c.c(c, this.a());
        }
        else {
            c4 = new a.b.o.c.c();
            c4.a(this.a());
        }
        return c4;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final PictureLink pictureLink = (PictureLink)o;
            return super.equals(pictureLink) && ((this.a() == null && pictureLink.a() == null) || this.a().equals(pictureLink.a()));
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(PictureLink.z[4]);
        sb.append(PictureLink.z[0] + super.d() + PictureLink.z[3]);
        if (super.b() != null) {
            sb.append(PictureLink.z[2] + super.b().toString() + PictureLink.z[3]);
        }
        sb.append(PictureLink.z[1] + this.a());
        sb.append("}");
        return sb.toString();
    }
    
    public Object clone() {
        final PictureLink pictureLink = (PictureLink)super.clone();
        if (this.a() != null) {
            pictureLink.a(new String(this.a()));
        }
        return pictureLink;
    }
    
    static {
        final String[] z = new String[12];
        final int n = 0;
        final char[] charArray = "?~\u0005\u0000Y,i+\u0018T*~XU".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'M';
                    break;
                }
                case 1: {
                    c2 = '\u001b';
                    break;
                }
                case 2: {
                    c2 = 'b';
                    break;
                }
                case 3: {
                    c2 = 'u';
                    break;
                }
                default: {
                    c2 = '5';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "!t\u0001\u0014A$t\fO\u0015".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'M';
                    break;
                }
                case 1: {
                    c4 = '\u001b';
                    break;
                }
                case 2: {
                    c4 = 'b';
                    break;
                }
                case 3: {
                    c4 = 'u';
                    break;
                }
                default: {
                    c4 = '5';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "9t\u0005\u0012Y(\u007f+\u0018T*~XU".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'M';
                    break;
                }
                case 1: {
                    c6 = '\u001b';
                    break;
                }
                case 2: {
                    c6 = 'b';
                    break;
                }
                case 3: {
                    c6 = 'u';
                    break;
                }
                default: {
                    c6 = '5';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "v;".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'M';
                    break;
                }
                case 1: {
                    c8 = '\u001b';
                    break;
                }
                case 2: {
                    c8 = 'b';
                    break;
                }
                case 3: {
                    c8 = 'u';
                    break;
                }
                default: {
                    c8 = '5';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u0001r\f\u001eT/w\u0007<X,|\u00079\\9~B\u000e".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'M';
                    break;
                }
                case 1: {
                    c10 = '\u001b';
                    break;
                }
                case 2: {
                    c10 = 'b';
                    break;
                }
                case 3: {
                    c10 = 'u';
                    break;
                }
                default: {
                    c10 = '5';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u0003n\u000e\u0019\u0015)z\u0016\u0014\u0015,i\u0010\u0014Lc".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'M';
                    break;
                }
                case 1: {
                    c12 = '\u001b';
                    break;
                }
                case 2: {
                    c12 = 'b';
                    break;
                }
                case 3: {
                    c12 = 'u';
                    break;
                }
                default: {
                    c12 = '5';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\fi\u0010\u0014Lmr\f\u0011P5;\r\u0000Amt\u0004UW\"n\f\u0011Fw;".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'M';
                    break;
                }
                case 1: {
                    c14 = '\u001b';
                    break;
                }
                case 2: {
                    c14 = 'b';
                    break;
                }
                case 3: {
                    c14 = 'u';
                    break;
                }
                default: {
                    c14 = '5';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "mr\u0011U[\"oB\u001c[mo\n\u0010\u0015?z\f\u0012Pm+BX\u0015".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'M';
                    break;
                }
                case 1: {
                    c16 = '\u001b';
                    break;
                }
                case 2: {
                    c16 = 'b';
                    break;
                }
                case 3: {
                    c16 = 'u';
                    break;
                }
                default: {
                    c16 = '5';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0019s\u0007UR$m\u0007\u001b\u0015,i\u0010\u0014Lmr\u0011U[\"oB\u0019T?|\u0007UP#t\u0017\u0012]mo\rUS$w\u000eUA%r\u0011UZ/q\u0007\u0016Aw;".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'M';
                    break;
                }
                case 1: {
                    c18 = '\u001b';
                    break;
                }
                case 2: {
                    c18 = 'b';
                    break;
                }
                case 3: {
                    c18 = 'u';
                    break;
                }
                default: {
                    c18 = '5';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\u001en\u0012\u0010Gmx\u000e\u0014F>;2\u001cV9n\u0010\u0010v%z\f\u0012P?5\u0005\u0010A\tz\u0016\u0014\u001dd5\u0016\u0007T#h\u000e\u0014A(3KUF%t\u0017\u0019Qmy\u0007UG(o\u0017\u0007[$u\u0005U| z\u0005\u0010q,o\u0003UZ/q\u0007\u0016A>7B\u0017@9;\u000b\u0006\u0015#t\u0016[".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'M';
                    break;
                }
                case 1: {
                    c20 = '\u001b';
                    break;
                }
                case 2: {
                    c20 = 'b';
                    break;
                }
                case 3: {
                    c20 = 'u';
                    break;
                }
                default: {
                    c20 = '5';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u0018u\u0003\u0017Y(;\u0016\u001a\u0015.t\u0010\u0007P.o\u000e\f\u00159i\u0003\u001bF!z\u0016\u0010\u00159s\u0007U| z\u0005\u0010q,o\u0003[".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'M';
                    break;
                }
                case 1: {
                    c22 = '\u001b';
                    break;
                }
                case 2: {
                    c22 = 'b';
                    break;
                }
                case 3: {
                    c22 = 'u';
                    break;
                }
                default: {
                    c22 = '5';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "\u0019s\u0007UR$m\u0007\u001b\u0015,i\u0010\u0014Lmr\u0011U[\"oB\u0019T?|\u0007UP#t\u0017\u0012]mo\rU]\"w\u0006UA%~B\u0016Z#o\u0007\u001bA>;\r\u0013\u00159s\u000b\u0006\u0015\"y\b\u0010V9;\u0006\u0000Pmo\rO\u0015".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'M';
                    break;
                }
                case 1: {
                    c24 = '\u001b';
                    break;
                }
                case 2: {
                    c24 = 'b';
                    break;
                }
                case 3: {
                    c24 = 'u';
                    break;
                }
                default: {
                    c24 = '5';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        PictureLink.z = z;
    }
}
