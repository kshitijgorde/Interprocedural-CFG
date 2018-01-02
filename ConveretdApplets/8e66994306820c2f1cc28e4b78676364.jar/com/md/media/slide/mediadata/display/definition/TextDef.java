// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display.definition;

import a.b.o.a.b.d;
import java.awt.Font;
import java.awt.Color;
import a.b.p.b;
import a.b.c.a;
import a.b.o.a.a.e;
import a.b.o.a.a.c;
import a.b.o.a.a.h;

public class TextDef implements h
{
    private String a;
    private int b;
    private a.b.o.a.a.c c;
    private e d;
    private boolean e;
    private static String[] z;
    
    public int b() {
        return this.b;
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    public int a() {
        int n = 4;
        if (this.c != null) {
            n += this.c.a();
        }
        return n;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(TextDef.z[6]);
        }
        if (n >= array.length || n < 0) {
            throw new a(TextDef.z[8] + n + TextDef.z[7] + array.length);
        }
        int n2 = 0;
        if (this.c != null) {
            n2 += this.c.a(array, n + n2);
        }
        try {
            this.a(a.b.p.b.a(array, n + n2));
            n2 += 4;
        }
        catch (a.b.p.a a) {
            throw new a(TextDef.z[5] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(TextDef.z[6]);
        }
        if (n >= array.length || n < 0) {
            throw new a(TextDef.z[8] + n + TextDef.z[7] + array.length);
        }
        int n2 = 0;
        if (this.c != null) {
            n2 += this.c.b(array, n + n2);
        }
        try {
            a.b.p.b.a(this.b(), array, n + n2);
            n2 += 4;
        }
        catch (a.b.p.a a) {
            throw new a(TextDef.z[9] + a.toString());
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
    
    public TextDef() {
        this(null);
    }
    
    public TextDef(final String s) {
        this(s, null, null);
    }
    
    public TextDef(final String s, final e e, final a.b.o.a.a.c c) {
        this.a = "";
        this.c = null;
        this.d = null;
        this.e = false;
        this.a(s);
        this.a(e);
        this.a(c);
    }
    
    public a.b.o.a.a.c b() {
        return this.c;
    }
    
    public Color c() {
        try {
            return ((a.b.o.a.b.a)this.c.a()).a();
        }
        catch (a.b.o.a.c c) {
            return null;
        }
        catch (ClassCastException ex) {
            return null;
        }
    }
    
    public e c() {
        return this.d;
    }
    
    public Font d() {
        return this.d.a();
    }
    
    public String a() {
        return this.a;
    }
    
    public void a(a.b.o.a.a.c c) {
        if (c == null) {
            c = new ColorDef();
        }
        this.c = c;
    }
    
    public void a(final String s, final int n, final int n2) {
        final FontDef fontDef = new FontDef();
        if (s != null) {
            fontDef.a(s);
        }
        fontDef.a(n);
        if (n2 > 0) {
            fontDef.b(n2);
        }
        this.a(fontDef);
    }
    
    public void a(final e d) {
        this.d = d;
        if (this.d == null) {
            (this.d = new FontDef()).a(TextDef.z[10]);
            this.d.a(1);
            this.d.b(8);
        }
    }
    
    public void a(String a) {
        if (a == null) {
            a = "";
        }
        this.a = a;
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public boolean e() {
        return this.e;
    }
    
    public String toString() {
        return new String(TextDef.z[2] + this.a() + TextDef.z[4] + this.d + TextDef.z[1] + this.c.toString() + (this.e ? TextDef.z[3] : "") + "}");
    }
    
    public boolean equals(final Object o) {
        try {
            final TextDef textDef = (TextDef)o;
            return this.a().equals(textDef.a()) && this.c().equals(textDef.c()) && this.d().equals(textDef.d());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public a.b.o.a.b.d a() throws a.b.o.a.c {
        final a.b.o.a.b.e e = new a.b.o.a.b.e(this.a(), this.d(), this.c());
        e.a(this.e());
        return e;
    }
    
    public Object clone() {
        try {
            final TextDef textDef = (TextDef)super.clone();
            if (this.a() != null) {
                textDef.a(new String(this.a()));
            }
            if (this.b() != null) {
                textDef.a((a.b.o.a.a.c)this.b().clone());
            }
            if (this.c() != null) {
                textDef.a((e)this.c().clone());
            }
            return textDef;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).d(TextDef.z[0]);
            return null;
        }
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "\u0015dY\u001fL$g\u0001\u0002{aoN\u001f(\"mN\u0005m cM\u000e&".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'A';
                    break;
                }
                case 1: {
                    c2 = '\u0001';
                    break;
                }
                case 2: {
                    c2 = '!';
                    break;
                }
                case 3: {
                    c2 = 'k';
                    break;
                }
                default: {
                    c2 = '\b';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "z!B\u0004d.s\u001bK".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'A';
                    break;
                }
                case 1: {
                    c4 = '\u0001';
                    break;
                }
                case 2: {
                    c4 = '!';
                    break;
                }
                case 3: {
                    c4 = 'k';
                    break;
                }
                default: {
                    c4 = '\b';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0015dY\u001fL$g\u0001\u0010|$yUQ(".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'A';
                    break;
                }
                case 1: {
                    c6 = '\u0001';
                    break;
                }
                case 2: {
                    c6 = '!';
                    break;
                }
                case 3: {
                    c6 = 'k';
                    break;
                }
                default: {
                    c6 = '\b';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "z!T\u0005l$sM\u0002f$e".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'A';
                    break;
                }
                case 1: {
                    c8 = '\u0001';
                    break;
                }
                case 2: {
                    c8 = '!';
                    break;
                }
                case 3: {
                    c8 = 'k';
                    break;
                }
                default: {
                    c8 = '\b';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "z!G\u0004f5;\u0001".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'A';
                    break;
                }
                case 1: {
                    c10 = '\u0001';
                    break;
                }
                case 2: {
                    c10 = '!';
                    break;
                }
                case 3: {
                    c10 = 'k';
                    break;
                }
                default: {
                    c10 = '\b';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u0015iDKo(wD\u0005( sS\nqahRKf.u\u0001\u0007i3fDKm/nT\f`auNKn(mMK|)hRKg#kD\b|{!".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'A';
                    break;
                }
                case 1: {
                    c12 = '\u0001';
                    break;
                }
                case 2: {
                    c12 = '!';
                    break;
                }
                case 3: {
                    c12 = 'k';
                    break;
                }
                default: {
                    c12 = '\b';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\u000ftM\u0007(%`U\n( sS\nqo".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'A';
                    break;
                }
                case 1: {
                    c14 = '\u0001';
                    break;
                }
                case 2: {
                    c14 = '!';
                    break;
                }
                case 3: {
                    c14 = 'k';
                    break;
                }
                default: {
                    c14 = '\b';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "ahRKf.u\u0001\u0002fauI\u000e(3`O\fma1\u0001F(".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'A';
                    break;
                }
                case 1: {
                    c16 = '\u0001';
                    break;
                }
                case 2: {
                    c16 = '!';
                    break;
                }
                case 3: {
                    c16 = 'k';
                    break;
                }
                default: {
                    c16 = '\b';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0000sS\nqahO\u000fm9!N\u001e|anGKj.tO\u000f{{!".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'A';
                    break;
                }
                case 1: {
                    c18 = '\u0001';
                    break;
                }
                case 2: {
                    c18 = '!';
                    break;
                }
                case 3: {
                    c18 = 'k';
                    break;
                }
                default: {
                    c18 = '\b';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\u0015iDKo(wD\u0005( sS\nqahRKf.u\u0001\u0007i3fDKm/nT\f`auNK`.mEK|)d\u0001\bg/uD\u0005|2!N\r(5iH\u0018(.cK\u000ek5;\u0001".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'A';
                    break;
                }
                case 1: {
                    c20 = '\u0001';
                    break;
                }
                case 2: {
                    c20 = '!';
                    break;
                }
                case 3: {
                    c20 = 'k';
                    break;
                }
                default: {
                    c20 = '\b';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u0012dS\u0002n".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'A';
                    break;
                }
                case 1: {
                    c22 = '\u0001';
                    break;
                }
                case 2: {
                    c22 = '!';
                    break;
                }
                case 3: {
                    c22 = 'k';
                    break;
                }
                default: {
                    c22 = '\b';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        TextDef.z = z;
    }
}
