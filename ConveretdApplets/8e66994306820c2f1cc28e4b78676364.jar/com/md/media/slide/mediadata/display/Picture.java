// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.d;
import a.b.o.a.b.c;
import a.b.o.c.h;
import a.b.c.a;
import a.b.o.a.a.f;
import a.b.o.b.b;

public class Picture implements b
{
    private byte[] a;
    private long b;
    private f c;
    private static String[] z;
    
    public byte[] b() {
        return this.a;
    }
    
    public void a(final byte[] a) {
        this.a = a;
    }
    
    public long c() {
        return this.b;
    }
    
    public void a(final long b) {
        this.b = b;
    }
    
    public int a() {
        int n = 24;
        if (this.d() != null) {
            n += this.d().a();
        }
        return n;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(Picture.z[1]);
        }
        if (n >= array.length || n < 0) {
            throw new a(Picture.z[2] + n + Picture.z[3] + array.length);
        }
        int n2 = 0;
        if (this.d() != null) {
            n2 += this.d().a(array, n + n2);
        }
        try {
            this.a(a.b.p.b.a(array, n + n2, 16));
            n2 += 16;
            this.a(a.b.p.b.b(array, n + n2));
            n2 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(Picture.z[4] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(Picture.z[1]);
        }
        if (n >= array.length || n < 0) {
            throw new a(Picture.z[2] + n + Picture.z[3] + array.length);
        }
        int n2 = 0;
        if (this.d() != null) {
            n2 += this.d().b(array, n + n2);
        }
        int n3;
        try {
            final byte[] b = this.b();
            System.arraycopy(b, 0, array, n + n2, b.length);
            n3 = n2 + b.length;
        }
        catch (Exception ex) {
            throw new a(Picture.z[0] + ex.toString());
        }
        try {
            a.b.p.b.a(this.c(), array, n + n3);
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(Picture.z[0] + a.toString());
        }
        return n3;
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
    
    public Picture() {
        this(null);
    }
    
    public Picture(final f f) {
        this.c = null;
        this.a(f);
    }
    
    public void a(final f c) {
        this.c = c;
    }
    
    public f d() {
        return this.c;
    }
    
    public h a() throws d {
        a.b.o.c.a a;
        if (this.d() == null) {
            a = new a.b.o.c.a();
        }
        else {
            try {
                a = new a.b.o.c.a((a.b.o.a.b.c)this.d().a());
            }
            catch (a.b.o.a.c c) {
                throw new d(Picture.z[5], c);
            }
            catch (ClassCastException ex) {
                throw new d(Picture.z[6], ex);
            }
        }
        return a;
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final Picture picture = (Picture)o;
            return (this.d() == null && picture.d() == null) || this.d().equals(picture.d());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(Picture.z[8]);
        if (this.d() == null) {
            sb.append(Picture.z[9]);
        }
        else {
            sb.append(this.d().toString());
        }
        sb.append(")");
        return sb.toString();
    }
    
    public Object clone() {
        try {
            final Picture picture = (Picture)super.clone();
            final byte[] b = this.b();
            if (b != null) {
                final byte[] array = new byte[b.length];
                System.arraycopy(b, 0, array, 0, b.length);
                picture.a(array);
            }
            if (this.d() != null) {
                picture.a((f)this.d().clone());
            }
            return picture;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).d(Picture.z[7]);
            return null;
        }
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "\u007fW]=\u0017BI]sPJMJ|\t\u000bVK=\u001eDK\u0018q\u0011YX]=\u0015EPMz\u0018\u000bKW=\u0018DS\\=\u0004CZ\u0018~\u001fEK]s\u0004X\u001fW{P_WQnPD]Rx\u0013_\u001f\\h\u0015\u000bKW'P".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '+';
                    break;
                }
                case 1: {
                    c2 = '?';
                    break;
                }
                case 2: {
                    c2 = '8';
                    break;
                }
                case 3: {
                    c2 = '\u001d';
                    break;
                }
                default: {
                    c2 = 'p';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "eJTqPO^L|PJMJ|\t\u0005".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '+';
                    break;
                }
                case 1: {
                    c4 = '?';
                    break;
                }
                case 2: {
                    c4 = '8';
                    break;
                }
                case 3: {
                    c4 = '\u001d';
                    break;
                }
                default: {
                    c4 = 'p';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "jMJ|\t\u000bVVy\u0015S\u001fWh\u0004\u000bP^=\u0012DJVy\u0003\u0011\u001f".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '+';
                    break;
                }
                case 1: {
                    c6 = '?';
                    break;
                }
                case 2: {
                    c6 = '8';
                    break;
                }
                case 3: {
                    c6 = '\u001d';
                    break;
                }
                default: {
                    c6 = 'p';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u000bVK=\u001eDK\u0018t\u001e\u000bKPxPY^Vz\u0015\u000b\u000f\u00180P".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '+';
                    break;
                }
                case 1: {
                    c8 = '?';
                    break;
                }
                case 2: {
                    c8 = '8';
                    break;
                }
                case 3: {
                    c8 = '\u001d';
                    break;
                }
                default: {
                    c8 = 'p';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u007fW]=\u0017BI]sPJMJ|\t\u000bVK=\u001eDK\u0018q\u0011YX]=\u0015EPMz\u0018\u000bKW=\u0016BST=\u0004CVK=\u001fIU]~\u0004\u0011\u001f".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '+';
                    break;
                }
                case 1: {
                    c10 = '?';
                    break;
                }
                case 2: {
                    c10 = '8';
                    break;
                }
                case 3: {
                    c10 = '\u001d';
                    break;
                }
                default: {
                    c10 = 'p';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "~QY\u007f\u001cN\u001fLrPHM]|\u0004N\u001fqp\u0011LZ||\u0004J\u0011".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '+';
                    break;
                }
                case 1: {
                    c12 = '?';
                    break;
                }
                case 2: {
                    c12 = '8';
                    break;
                }
                case 3: {
                    c12 = '\u001d';
                    break;
                }
                default: {
                    c12 = 'p';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "|MWs\u0017\u000bKAm\u0015\u000bX]s\u0015Y^Lx\u0014\u000bYWoPbRYz\u0015o^L|^".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '+';
                    break;
                }
                case 1: {
                    c14 = '?';
                    break;
                }
                case 2: {
                    c14 = '8';
                    break;
                }
                case 3: {
                    c14 = '\u001d';
                    break;
                }
                default: {
                    c14 = 'p';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "{V[i\u0005YZ\u0018t\u0003\u000bQWiPHSWs\u0015J]Tx^".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '+';
                    break;
                }
                case 1: {
                    c16 = '?';
                    break;
                }
                case 2: {
                    c16 = '8';
                    break;
                }
                case 3: {
                    c16 = '\u001d';
                    break;
                }
                default: {
                    c16 = 'p';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "{V[i\u0005YZ\u0018f".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '+';
                    break;
                }
                case 1: {
                    c18 = '?';
                    break;
                }
                case 2: {
                    c18 = '8';
                    break;
                }
                case 3: {
                    c18 = '\u001d';
                    break;
                }
                default: {
                    c18 = 'p';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "EJTq".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '+';
                    break;
                }
                case 1: {
                    c20 = '?';
                    break;
                }
                case 2: {
                    c20 = '8';
                    break;
                }
                case 3: {
                    c20 = '\u001d';
                    break;
                }
                default: {
                    c20 = 'p';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        Picture.z = z;
    }
}
