// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.c.f;
import a.b.o.a.b.e;
import a.b.o.a.a.c;
import a.b.o.a.a.h;
import a.b.p.b;
import a.b.c.a;
import a.b.o.b.d;

public class TextLink extends TextChanger implements d
{
    private long h;
    private String i;
    private transient boolean j;
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
            throw new a(TextLink.z[2]);
        }
        if (n >= array.length || n < 0) {
            throw new a(TextLink.z[4] + n + TextLink.z[5] + array.length);
        }
        final int n2 = 0;
        int n3 = n2 + super.a(array, n + n2);
        try {
            this.c(b.b(array, n + n3));
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(TextLink.z[11] + a.toString());
        }
        return n3;
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(TextLink.z[2]);
        }
        if (n >= array.length || n < 0) {
            throw new a(TextLink.z[4] + n + TextLink.z[5] + array.length);
        }
        final int n2 = 0;
        int n3 = n2 + super.b(array, n + n2);
        try {
            b.a(this.b(), array, n + n3);
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(TextLink.z[3] + a.toString());
        }
        return n3;
    }
    
    public TextLink() {
        this(null, (String)null);
    }
    
    public TextLink(final a.b.o.a.a.h h, final String s) {
        this.i = null;
        this.j = false;
        super.a(h);
        if (h != null) {
            try {
                final a.b.o.a.a.h a = a.b.g.b.a().b().a(h.a());
                final a.b.o.a.a.a.a a2 = new a.b.o.a.a.a.a();
                a2.b(TextLink.z[6]);
                a.a(a2);
                a.a(this.j);
                a.a(h.c());
                super.b(a);
            }
            catch (a.b.g.a a3) {
                a3.printStackTrace();
            }
        }
        super.a(true);
        super.b(false);
        this.a(s);
    }
    
    public void a(final a.b.o.a.a.h h) {
        if (h != null) {
            h.a(this.j);
            super.a(h);
        }
    }
    
    public void b(final a.b.o.a.a.h h) {
        if (h != null) {
            h.a(this.j);
            super.b(h);
        }
    }
    
    public void a(final String i) {
        this.i = i;
    }
    
    public String a() {
        return this.i;
    }
    
    public void c(final boolean j) {
        this.j = j;
    }
    
    public a.b.o.c.h a() throws a.b.o.d {
        try {
            if (super.b() == null) {
                return new a.b.o.c.f((a.b.o.a.b.e)super.a().a(), this.a());
            }
            return new a.b.o.c.f((a.b.o.a.b.e)super.a().a(), (a.b.o.a.b.e)super.b().a(), this.a());
        }
        catch (a.b.o.a.c c) {
            throw new a.b.o.d(TextLink.z[0], c);
        }
        catch (ClassCastException ex) {
            throw new a.b.o.d(TextLink.z[1]);
        }
    }
    
    public String toString() {
        return new String(TextLink.z[9] + super.a() + TextLink.z[10] + super.b() + TextLink.z[8] + this.a() + TextLink.z[7]);
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final TextLink textLink = (TextLink)o;
            return super.equals(textLink) && ((this.a() == null && textLink.a() == null) || this.a().equals(textLink.a()));
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public Object clone() {
        final TextLink textLink = (TextLink)super.clone();
        if (this.a() != null) {
            textLink.a(new String(this.a()));
        }
        return textLink;
    }
    
    static {
        final String[] z = new String[12];
        final int n = 0;
        final char[] charArray = "Rk]mab%H`-djN}hdqPv-sw]a~kdHj-smY/Djd[jIfq]!".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = '\u0005';
                    break;
                }
                case 2: {
                    c2 = '<';
                    break;
                }
                case 3: {
                    c2 = '\u000f';
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
        final char[] charArray2 = "TpLj\u007f'fPn~t%h}livZj\u007ffgPj^pdLF`fbY!jbqxnyf-\u0015!yudR|afqY'$'vT`xka\u001cmh'wY{xukUaj'LQnjbA]{l'j^ehdqO#-epH/dt%R`y)".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0007';
                    break;
                }
                case 1: {
                    c4 = '\u0005';
                    break;
                }
                case 2: {
                    c4 = '<';
                    break;
                }
                case 3: {
                    c4 = '\u000f';
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
        final char[] charArray3 = "IpPc-cdHn-fwNnt)".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0007';
                    break;
                }
                case 1: {
                    c6 = '\u0005';
                    break;
                }
                case 2: {
                    c6 = '<';
                    break;
                }
                case 3: {
                    c6 = '\u000f';
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
        final char[] charArray4 = "SmY/jnsYa-fwNnt'lO/chq\u001cclubY/hijIhe'qS/ehiX/yo`\u001clbiqYayt%Si-smU|-hgVjns%Xzh'qS5-".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0007';
                    break;
                }
                case 1: {
                    c8 = '\u0005';
                    break;
                }
                case 2: {
                    c8 = '<';
                    break;
                }
                case 3: {
                    c8 = '\u000f';
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
        final char[] charArray5 = "FwNnt'lRkh\u007f%Szy'jZ/ohpRk~=%".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0007';
                    break;
                }
                case 1: {
                    c10 = '\u0005';
                    break;
                }
                case 2: {
                    c10 = '<';
                    break;
                }
                case 3: {
                    c10 = '\u000f';
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
        final char[] charArray6 = "'lO/chq\u001cfc'qTj-udRhh'5\u001c\"-".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0007';
                    break;
                }
                case 1: {
                    c12 = '\u0005';
                    break;
                }
                case 2: {
                    c12 = '<';
                    break;
                }
                case 3: {
                    c12 = '\u000f';
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
        final char[] charArray7 = "7}xL=F4\b".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0007';
                    break;
                }
                case 1: {
                    c14 = '\u0005';
                    break;
                }
                case 2: {
                    c14 = '<';
                    break;
                }
                case 3: {
                    c14 = '\u000f';
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
        final char[] charArray8 = "'x".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0007';
                    break;
                }
                case 1: {
                    c16 = '\u0005';
                    break;
                }
                case 2: {
                    c16 = '<';
                    break;
                }
                case 3: {
                    c16 = '\u000f';
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
        final char[] charArray9 = "<%I}a=%".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0007';
                    break;
                }
                case 1: {
                    c18 = '\u0005';
                    break;
                }
                case 2: {
                    c18 = '<';
                    break;
                }
                case 3: {
                    c18 = '\u000f';
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
        final char[] charArray10 = "KlRdleiY[h\u007fqpfyb%G}h`pPn\u007f=%".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0007';
                    break;
                }
                case 1: {
                    c20 = '\u0005';
                    break;
                }
                case 2: {
                    c20 = '<';
                    break;
                }
                case 3: {
                    c20 = '\u000f';
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
        final char[] charArray11 = "<%H`j`iY5-".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\u0007';
                    break;
                }
                case 1: {
                    c22 = '\u0005';
                    break;
                }
                case 2: {
                    c22 = '<';
                    break;
                }
                case 3: {
                    c22 = '\u000f';
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
        final int n34 = 11;
        final char[] charArray12 = "SmY/jnsYa-fwNnt'lO/chq\u001cclubY/hijIhe'qS/kniP/yolO/beoYly=%".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '\u0007';
                    break;
                }
                case 1: {
                    c24 = '\u0005';
                    break;
                }
                case 2: {
                    c24 = '<';
                    break;
                }
                case 3: {
                    c24 = '\u000f';
                    break;
                }
                default: {
                    c24 = '\r';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        TextLink.z = z;
    }
}
