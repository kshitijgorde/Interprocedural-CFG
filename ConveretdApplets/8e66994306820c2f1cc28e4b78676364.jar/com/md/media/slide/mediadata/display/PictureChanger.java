// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.d;
import a.b.o.a.b.c;
import a.b.o.c.h;
import a.b.p.b;
import a.b.c.a;
import a.b.o.a.a.f;
import a.b.o.b.i;

public class PictureChanger extends Picture implements i
{
    private long d;
    private f e;
    private boolean f;
    private boolean g;
    private static String[] z;
    
    public long a() {
        return this.d;
    }
    
    public void b(final long d) {
        this.d = d;
    }
    
    public int a() {
        int n = super.a() + 8;
        if (this.b() != null) {
            n += this.b().a();
        }
        return n;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(PictureChanger.z[3]);
        }
        if (n >= array.length || n < 0) {
            throw new a(PictureChanger.z[2] + n + PictureChanger.z[1] + array.length);
        }
        final int n2 = 0;
        int n3 = n2 + super.a(array, n + n2);
        if (this.b() != null) {
            n3 += this.b().a(array, n + n3);
        }
        try {
            this.b(b.b(array, n + n3));
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(PictureChanger.z[4] + a.toString());
        }
        return n3;
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(PictureChanger.z[3]);
        }
        if (n >= array.length || n < 0) {
            throw new a(PictureChanger.z[2] + n + PictureChanger.z[1] + array.length);
        }
        final int n2 = 0;
        int n3 = n2 + super.b(array, n + n2);
        if (this.b() != null) {
            n3 += this.b().b(array, n + n3);
        }
        try {
            b.a(this.a(), array, n + n3);
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(PictureChanger.z[0] + a.toString());
        }
        return n3;
    }
    
    public PictureChanger() {
        this(null, null);
    }
    
    public PictureChanger(final f f, final f f2) {
        this(f, f2, false, false);
    }
    
    public PictureChanger(final f f, final f f2, final boolean b, final boolean b2) {
        super(f);
        this.e = null;
        this.f = false;
        this.g = false;
        this.b(f2);
        this.a(b);
        this.b(b2);
    }
    
    public void b(final f e) {
        this.e = e;
    }
    
    public f b() {
        return this.e;
    }
    
    public void a(final boolean f) {
        this.f = f;
    }
    
    public boolean a() {
        return this.f;
    }
    
    public void b(final boolean g) {
        this.g = g;
    }
    
    public boolean b() {
        return this.g;
    }
    
    public String toString() {
        return new String(PictureChanger.z[6] + super.d() + PictureChanger.z[7] + this.b() + PictureChanger.z[8] + this.g + PictureChanger.z[5] + this.f + "}");
    }
    
    public h a() throws d {
        try {
            a.b.o.a.b.c c = (a.b.o.a.b.c)super.d().a();
            if (this.b() != null) {
                c = (a.b.o.a.b.c)this.b().a();
            }
            return new a.b.o.c.b(c, c, this.a(), this.b());
        }
        catch (a.b.o.a.c c2) {
            throw new d(PictureChanger.z[9], c2);
        }
        catch (ClassCastException ex) {
            throw new d(PictureChanger.z[10]);
        }
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final PictureChanger pictureChanger = (PictureChanger)o;
            return this.a(this.d(), pictureChanger.d()) && this.a(this.b(), pictureChanger.b());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    private boolean a(final f f, final f f2) {
        if (f == null || f2 == null) {
            return f == f2;
        }
        return f.equals(f2);
    }
    
    public Object clone() {
        final PictureChanger pictureChanger = (PictureChanger)super.clone();
        if (this.b() != null) {
            pictureChanger.b((f)this.b().clone());
        }
        return pictureChanger;
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "jaDRjW\u007fD\u001c-_{S\u0013t\u001e`RRcQ}\u0001\u001elLnDRhPfT\u0015e\u001e}NReQeERyVl\u0001\u0011bP}D\u001cyM)N\u0014-JaH\u0001-QkK\u0017nJ)E\u0007h\u001e}NH-".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '>';
                    break;
                }
                case 1: {
                    c2 = '\t';
                    break;
                }
                case 2: {
                    c2 = '!';
                    break;
                }
                case 3: {
                    c2 = 'r';
                    break;
                }
                default: {
                    c2 = '\r';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u001e`RRcQ}\u0001\u001bc\u001e}I\u0017-LhO\u0015h\u001e9\u0001_-".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '>';
                    break;
                }
                case 1: {
                    c4 = '\t';
                    break;
                }
                case 2: {
                    c4 = '!';
                    break;
                }
                case 3: {
                    c4 = 'r';
                    break;
                }
                default: {
                    c4 = '\r';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u007f{S\u0013t\u001e`O\u0016hF)N\u0007y\u001efGRoQ|O\u0016~\u0004)".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '>';
                    break;
                }
                case 1: {
                    c6 = '\t';
                    break;
                }
                case 2: {
                    c6 = '!';
                    break;
                }
                case 3: {
                    c6 = 'r';
                    break;
                }
                default: {
                    c6 = '\r';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "p|M\u001e-ZhU\u0013-_{S\u0013t\u0010".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '>';
                    break;
                }
                case 1: {
                    c8 = '\t';
                    break;
                }
                case 2: {
                    c8 = '!';
                    break;
                }
                case 3: {
                    c8 = 'r';
                    break;
                }
                default: {
                    c8 = '\r';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "jaDRjW\u007fD\u001c-_{S\u0013t\u001e`RRcQ}\u0001\u001elLnDRhPfT\u0015e\u001e}NRkWeMRyV`RRb\\cD\u0011y\u0004)".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '>';
                    break;
                }
                case 1: {
                    c10 = '\t';
                    break;
                }
                case 2: {
                    c10 = '!';
                    break;
                }
                case 3: {
                    c10 = 'r';
                    break;
                }
                default: {
                    c10 = '\r';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u001edN\u0007~[fW\u0017\u007f\u0013)".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '>';
                    break;
                }
                case 1: {
                    c12 = '\t';
                    break;
                }
                case 2: {
                    c12 = '!';
                    break;
                }
                case 3: {
                    c12 = 'r';
                    break;
                }
                default: {
                    c12 = '\r';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "n`B\u0006xLlb\u001alPnD\u0000-E{D\u0015xRhSH-".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '>';
                    break;
                }
                case 1: {
                    c14 = '\t';
                    break;
                }
                case 2: {
                    c14 = '!';
                    break;
                }
                case 3: {
                    c14 = 'r';
                    break;
                }
                default: {
                    c14 = '\r';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u0005)U\u001djYeD\u00167\u001e".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '>';
                    break;
                }
                case 1: {
                    c16 = '\t';
                    break;
                }
                case 2: {
                    c16 = '!';
                    break;
                }
                case 3: {
                    c16 = 'r';
                    break;
                }
                default: {
                    c16 = '\r';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u001eHB\u0006dQgRH-SfT\u0001h]eH\u0011f\u0013".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '>';
                    break;
                }
                case 1: {
                    c18 = '\t';
                    break;
                }
                case 2: {
                    c18 = '!';
                    break;
                }
                case 3: {
                    c18 = 'r';
                    break;
                }
                default: {
                    c18 = '\r';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "kg@\u0010a[)U\u001d-]fS\u0000h]}M\u000b-J{@\u001c~RhU\u0017-JaDRDShF\u0017I_}@\\".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '>';
                    break;
                }
                case 1: {
                    c20 = '\t';
                    break;
                }
                case 2: {
                    c20 = '!';
                    break;
                }
                case 3: {
                    c20 = 'r';
                    break;
                }
                default: {
                    c20 = '\r';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "m|Q\u0017\u007f\u001ejM\u0013~M)q\u001bnJ|S\u0017#YlU6lJh\t[#J{@\u001c~RhU\u0017%\u0017)R\u001abKeERo[)S\u0017yK{O\u001bcY)h\u001flYle\u0013y_)N\u0010g[jU\u0001!\u001ekT\u0006-Wz\u0001\u001cbJ'".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '>';
                    break;
                }
                case 1: {
                    c22 = '\t';
                    break;
                }
                case 2: {
                    c22 = '!';
                    break;
                }
                case 3: {
                    c22 = 'r';
                    break;
                }
                default: {
                    c22 = '\r';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        PictureChanger.z = z;
    }
}
