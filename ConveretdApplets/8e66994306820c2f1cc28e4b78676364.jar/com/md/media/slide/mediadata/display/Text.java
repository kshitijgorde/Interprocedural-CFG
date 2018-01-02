// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display;

import a.b.o.a.c;
import a.b.o.c.d;
import a.b.o.a.b.e;
import a.b.p.b;
import a.b.c.a;
import a.b.o.b.h;

public class Text implements h
{
    private byte[] a;
    private long b;
    private a.b.o.a.a.h c;
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
        if (this.a() != null) {
            n += this.a().a();
        }
        return n;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(Text.z[5]);
        }
        if (n >= array.length || n < 0) {
            throw new a(Text.z[6] + n + Text.z[4] + array.length);
        }
        int n2 = 0;
        if (this.a() != null) {
            n2 += this.a().a(array, n + n2);
        }
        try {
            this.a(a.b.p.b.a(array, n + n2, 16));
            n2 += 16;
            this.a(a.b.p.b.b(array, n + n2));
            n2 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(Text.z[3] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(Text.z[5]);
        }
        if (n >= array.length || n < 0) {
            throw new a(Text.z[6] + n + Text.z[4] + array.length);
        }
        int n2 = 0;
        if (this.a() != null) {
            n2 += this.a().b(array, n + n2);
        }
        int n3;
        try {
            final byte[] b = this.b();
            System.arraycopy(b, 0, array, n + n2, b.length);
            n3 = n2 + b.length;
        }
        catch (Exception ex) {
            throw new a(Text.z[8] + ex.toString());
        }
        try {
            a.b.p.b.a(this.c(), array, n + n3);
            n3 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(Text.z[8] + a.toString());
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
    
    public Text() {
        this(null);
    }
    
    public Text(final a.b.o.a.a.h h) {
        this.c = null;
        this.a(h);
    }
    
    public void a(final a.b.o.a.a.h c) {
        this.c = c;
        if (this.c == null) {
            try {
                this.c = a.b.g.b.a().b().a();
            }
            catch (a.b.g.a a) {}
        }
    }
    
    public a.b.o.a.a.h a() {
        return this.c;
    }
    
    public String toString() {
        return new String(Text.z[7] + this.a().toString() + "}");
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            return this.a().equals(((Text)o).a());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public a.b.o.c.h a() throws a.b.o.d {
        try {
            return new d((a.b.o.a.b.e)this.a().a());
        }
        catch (a.b.o.a.c c) {
            throw new a.b.o.d(Text.z[1], c);
        }
        catch (ClassCastException ex) {
            throw new a.b.o.d(Text.z[0], ex);
        }
    }
    
    public Object clone() {
        try {
            final Text text = (Text)super.clone();
            final byte[] b = this.b();
            if (b != null) {
                final byte[] array = new byte[b.length];
                System.arraycopy(b, 0, array, 0, b.length);
                text.a(array);
            }
            if (this.a() != null) {
                text.a((a.b.o.a.a.h)this.a().clone());
            }
            return text;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).d(Text.z[2]);
            return null;
        }
    }
    
    static {
        final String[] z = new String[9];
        final int n = 0;
        final char[] charArray = "&y\f?\u007fQ\u007f\u001a!}Ql\u0006?}\u0003j\u00174|Qm\f#8%n\u001b%\\\u0010\u007f\u0002\u007f".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'q';
                    break;
                }
                case 1: {
                    c2 = '\u000b';
                    break;
                }
                case 2: {
                    c2 = 'c';
                    break;
                }
                case 3: {
                    c2 = 'Q';
                    break;
                }
                default: {
                    c2 = '\u0018';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "$e\u00023t\u0014+\u0017>8\u0012y\u00060l\u0014+74`\u0005O\u0002%y_".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'q';
                    break;
                }
                case 1: {
                    c4 = '\u000b';
                    break;
                }
                case 2: {
                    c4 = 'c';
                    break;
                }
                case 3: {
                    c4 = 'Q';
                    break;
                }
                default: {
                    c4 = '\u0018';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "%n\u001b%8\u0018xC?w\u0005+\u0000=w\u001fn\u00023t\u0014%".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'q';
                    break;
                }
                case 1: {
                    c6 = '\u000b';
                    break;
                }
                case 2: {
                    c6 = 'c';
                    break;
                }
                case 3: {
                    c6 = 'Q';
                    break;
                }
                default: {
                    c6 = '\u0018';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "%c\u0006q\u007f\u0018}\u0006?8\u0010y\u00110aQb\u0010qv\u001e\u007fC=y\u0003l\u0006q}\u001fd\u00166pQ\u007f\fq~\u0018g\u000fql\u0019b\u0010qw\u0013a\u00062lK+".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'q';
                    break;
                }
                case 1: {
                    c8 = '\u000b';
                    break;
                }
                case 2: {
                    c8 = 'c';
                    break;
                }
                case 3: {
                    c8 = 'Q';
                    break;
                }
                default: {
                    c8 = '\u0018';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "Qb\u0010qv\u001e\u007fC8vQ\u007f\u000b48\u0003j\r6}Q;C|8".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'q';
                    break;
                }
                case 1: {
                    c10 = '\u000b';
                    break;
                }
                case 2: {
                    c10 = 'c';
                    break;
                }
                case 3: {
                    c10 = 'Q';
                    break;
                }
                default: {
                    c10 = '\u0018';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "?~\u000f=8\u0015j\u001708\u0010y\u00110a_".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'q';
                    break;
                }
                case 1: {
                    c12 = '\u000b';
                    break;
                }
                case 2: {
                    c12 = 'c';
                    break;
                }
                case 3: {
                    c12 = 'Q';
                    break;
                }
                default: {
                    c12 = '\u0018';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "0y\u00110aQb\r5}\t+\f$lQd\u0005qz\u001e~\r5kK+".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'q';
                    break;
                }
                case 1: {
                    c14 = '\u000b';
                    break;
                }
                case 2: {
                    c14 = 'c';
                    break;
                }
                case 3: {
                    c14 = 'Q';
                    break;
                }
                default: {
                    c14 = '\u0018';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "%n\u001b%8\n\u007f\u0006)lK+".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'q';
                    break;
                }
                case 1: {
                    c16 = '\u000b';
                    break;
                }
                case 2: {
                    c16 = 'c';
                    break;
                }
                case 3: {
                    c16 = 'Q';
                    break;
                }
                default: {
                    c16 = '\u0018';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "%c\u0006q\u007f\u0018}\u0006?8\u0010y\u00110aQb\u0010qv\u001e\u007fC=y\u0003l\u0006q}\u001fd\u00166pQ\u007f\fqp\u001eg\u0007ql\u0019nC2w\u001f\u007f\u0006?l\u0002+\f78\u0005c\n\"8\u001ei\t4{\u0005+\u0007$}Q\u007f\fk8".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'q';
                    break;
                }
                case 1: {
                    c18 = '\u000b';
                    break;
                }
                case 2: {
                    c18 = 'c';
                    break;
                }
                case 3: {
                    c18 = 'Q';
                    break;
                }
                default: {
                    c18 = '\u0018';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        Text.z = z;
    }
}
