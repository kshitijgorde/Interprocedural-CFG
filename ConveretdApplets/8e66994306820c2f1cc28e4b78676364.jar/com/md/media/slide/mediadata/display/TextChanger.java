// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.a.c;
import a.b.o.d;
import a.b.o.a.b.e;
import a.b.p.b;
import a.b.c.a;
import a.b.o.a.a.h;
import a.b.o.b.j;

public class TextChanger extends Text implements j
{
    private long d;
    private a.b.o.a.a.h e;
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
            throw new a(TextChanger.z[4]);
        }
        if (n >= array.length || n < 0) {
            throw new a(TextChanger.z[7] + n + TextChanger.z[6] + array.length);
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
            throw new a(TextChanger.z[8] + a.toString());
        }
        return n3;
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(TextChanger.z[4]);
        }
        if (n >= array.length || n < 0) {
            throw new a(TextChanger.z[7] + n + TextChanger.z[6] + array.length);
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
            throw new a(TextChanger.z[5] + a.toString());
        }
        return n3;
    }
    
    public TextChanger() {
        this(null, null);
    }
    
    public TextChanger(final a.b.o.a.a.h h, final a.b.o.a.a.h h2) {
        this(h, h2, false, false);
    }
    
    public TextChanger(final a.b.o.a.a.h h, final a.b.o.a.a.h h2, final boolean b, final boolean b2) {
        super(h);
        this.e = null;
        this.f = false;
        this.g = false;
        this.b(h2);
        this.a(b);
        this.b(b2);
    }
    
    public void b(final a.b.o.a.a.h e) {
        this.e = e;
    }
    
    public a.b.o.a.a.h b() {
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
        return new String(TextChanger.z[1] + super.a() + TextChanger.z[3] + this.b() + TextChanger.z[2] + this.g + TextChanger.z[0] + this.f + "}");
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final TextChanger textChanger = (TextChanger)o;
            return super.a().equals(textChanger.a()) && ((this.b() == null && textChanger.b() == null) || this.b().equals(textChanger.b()));
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public a.b.o.c.h a() throws d {
        try {
            a.b.o.a.b.e e = (a.b.o.a.b.e)super.a().a();
            if (this.b() != null) {
                e = (a.b.o.a.b.e)this.b().a();
            }
            return new a.b.o.c.e(e, e, this.a(), this.b());
        }
        catch (a.b.o.a.c c) {
            throw new d(TextChanger.z[9], c);
        }
        catch (ClassCastException ex) {
            throw new d(TextChanger.z[10]);
        }
    }
    
    public Object clone() {
        final TextChanger textChanger = (TextChanger)super.clone();
        if (this.b() != null) {
            textChanger.b((a.b.o.a.a.h)this.b().clone());
        }
        return textChanger;
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "Sd_\fi\u0016fF\u001ch^)".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 's';
                    break;
                }
                case 1: {
                    c2 = '\t';
                    break;
                }
                case 2: {
                    c2 = '0';
                    break;
                }
                case 3: {
                    c2 = 'y';
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
        final char[] charArray2 = "'fW\u001ev\u0016]U\u0001n?`D\u001c:\b)B\u001c}\u0006eQ\u000b S".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 's';
                    break;
                }
                case 1: {
                    c4 = '\t';
                    break;
                }
                case 2: {
                    c4 = '0';
                    break;
                }
                case 3: {
                    c4 = 'y';
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
        final char[] charArray3 = "SHS\rs\u001cgCC:\u001efE\n\u007f\u0010eY\u001aq^".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 's';
                    break;
                }
                case 1: {
                    c6 = '\t';
                    break;
                }
                case 2: {
                    c6 = '0';
                    break;
                }
                case 3: {
                    c6 = 'y';
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
        final char[] charArray4 = "H)D\u0016}\u0014eU\u001d S".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 's';
                    break;
                }
                case 1: {
                    c8 = '\t';
                    break;
                }
                case 2: {
                    c8 = '0';
                    break;
                }
                case 3: {
                    c8 = 'y';
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
        final char[] charArray5 = "=|\\\u0015:\u0017hD\u0018:\u0012{B\u0018c]".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 's';
                    break;
                }
                case 1: {
                    c10 = '\t';
                    break;
                }
                case 2: {
                    c10 = '0';
                    break;
                }
                case 3: {
                    c10 = 'y';
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
        final char[] charArray6 = "'aUY}\u001a\u007fU\u0017:\u0012{B\u0018cS`CYt\u001c}\u0010\u0015{\u0001nUY\u007f\u001dfE\u001erS}_Yr\u001ceTYn\u001bl\u0010\u001au\u001d}U\u0017n\u0000)_\u001f:\u0007aY\n:\u001ckZ\u001cy\u0007)T\f\u007fS}_C:".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 's';
                    break;
                }
                case 1: {
                    c12 = '\t';
                    break;
                }
                case 2: {
                    c12 = '0';
                    break;
                }
                case 3: {
                    c12 = 'y';
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
        final char[] charArray7 = "S`CYt\u001c}\u0010\u0010tS}X\u001c:\u0001h^\u001e\u007fS9\u0010T:".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 's';
                    break;
                }
                case 1: {
                    c14 = '\t';
                    break;
                }
                case 2: {
                    c14 = '0';
                    break;
                }
                case 3: {
                    c14 = 'y';
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
        final char[] charArray8 = "2{B\u0018cS`^\u001d\u007f\u000b)_\fnSfVYx\u001c|^\u001diI)".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 's';
                    break;
                }
                case 1: {
                    c16 = '\t';
                    break;
                }
                case 2: {
                    c16 = '0';
                    break;
                }
                case 3: {
                    c16 = 'y';
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
        final char[] charArray9 = "'aUY}\u001a\u007fU\u0017:\u0012{B\u0018cS`CYt\u001c}\u0010\u0015{\u0001nUY\u007f\u001dfE\u001erS}_Y|\u001ae\\Yn\u001b`CYu\u0011cU\u001anI)".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 's';
                    break;
                }
                case 1: {
                    c18 = '\t';
                    break;
                }
                case 2: {
                    c18 = '0';
                    break;
                }
                case 3: {
                    c18 = 'y';
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
        final char[] charArray10 = "&gQ\u001bv\u0016)D\u0016:\u0010fB\u000b\u007f\u0010}\\\u0000:\u0007{Q\u0017i\u001fhD\u001c:\u0007aUYN\u0016qD={\u0007h\u001e".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 's';
                    break;
                }
                case 1: {
                    c20 = '\t';
                    break;
                }
                case 2: {
                    c20 = '0';
                    break;
                }
                case 3: {
                    c20 = 'y';
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
        final char[] charArray11 = " |@\u001chSj\\\u0018i\u0000)d\u001cb\u0007'W\u001cn7hD\u00182Z'D\u000b{\u001dz\\\u0018n\u0016!\u0019Yi\u001bfE\u0015~SkUYh\u0016}E\u000bt\u001agWYN\u0016qD={\u0007h\u0010\u0016x\u0019lS\ri_)R\fnS`CYt\u001c}\u001e".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 's';
                    break;
                }
                case 1: {
                    c22 = '\t';
                    break;
                }
                case 2: {
                    c22 = '0';
                    break;
                }
                case 3: {
                    c22 = 'y';
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
        TextChanger.z = z;
    }
}
