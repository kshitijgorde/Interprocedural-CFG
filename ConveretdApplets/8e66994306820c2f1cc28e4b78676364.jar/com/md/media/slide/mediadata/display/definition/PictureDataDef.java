// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display.definition;

import org.a.d.h;
import a.b.o.a.b.c;
import a.b.o.a.b.d;
import a.b.c.a;
import a.b.o.a.a.b;

public class PictureDataDef implements b
{
    private int a;
    private long b;
    private byte[] c;
    private static String[] z;
    
    public int b() {
        return this.a;
    }
    
    public void a(final int a) {
        this.a = a;
    }
    
    public long c() {
        return this.b;
    }
    
    public void a(final long b) {
        this.b = b;
    }
    
    public int a() {
        return 12;
    }
    
    public int a(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(PictureDataDef.z[4]);
        }
        if (n >= array.length || n < 0) {
            throw new a(PictureDataDef.z[2] + n + PictureDataDef.z[3] + array.length);
        }
        int n2 = 0;
        try {
            this.a(a.b.p.b.a(array, n + n2));
            n2 += 4;
            this.a(a.b.p.b.b(array, n + n2));
            n2 += 8;
        }
        catch (a.b.p.a a) {
            throw new a(PictureDataDef.z[1] + a.toString());
        }
        return n2;
    }
    
    public int a(final byte[] array) throws a {
        return this.a(array, 0);
    }
    
    public int b(final byte[] array, final int n) throws a {
        if (array == null) {
            throw new a(PictureDataDef.z[4]);
        }
        if (n >= array.length || n < 0) {
            throw new a(PictureDataDef.z[2] + n + PictureDataDef.z[3] + array.length);
        }
        final int n2 = 0;
        int n4;
        try {
            final int n3 = n2 + a.b.p.b.a(this.b(), array, n + n2);
            n4 = n3 + a.b.p.b.a(this.c(), array, n + n3);
        }
        catch (a.b.p.a a) {
            throw new a(PictureDataDef.z[6] + a.toString());
        }
        return n4;
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
    
    public PictureDataDef() {
        this(null);
    }
    
    public PictureDataDef(final byte[] array) {
        this.c = null;
        this.a(array);
    }
    
    public byte[] d() {
        return this.c;
    }
    
    public void a(final byte[] c) {
        this.c = c;
    }
    
    public a.b.o.a.b.d a() throws a.b.o.a.c {
        return new a.b.o.a.b.c(this.d());
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            return h.a(this.d(), ((PictureDataDef)o).d());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public String toString() {
        return new String(PictureDataDef.z[0] + this.d().length + "}");
    }
    
    public Object clone() {
        try {
            final PictureDataDef pictureDataDef = (PictureDataDef)super.clone();
            final byte[] d = this.d();
            if (d != null) {
                final byte[] array = new byte[d.length];
                System.arraycopy(d, 0, array, 0, d.length);
                pictureDataDef.a(array);
            }
            return pictureDataDef;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).d(PictureDataDef.z[5]);
            return null;
        }
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = "\u0014V.\"m6Z\t7l%{(08?\u001f>?b!\u0005m".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'D';
                    break;
                }
                case 1: {
                    c2 = '?';
                    break;
                }
                case 2: {
                    c2 = 'M';
                    break;
                }
                case 3: {
                    c2 = 'V';
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
        final char[] charArray2 = "\u0010W(v\u007f-I(88%M?7adV>vv+Km:y6X(v}*P81pdK\"v~-S!vl,V>vw&U(5l~\u001f".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'D';
                    break;
                }
                case 1: {
                    c4 = '?';
                    break;
                }
                case 2: {
                    c4 = 'M';
                    break;
                }
                case 3: {
                    c4 = 'V';
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
        final char[] charArray3 = "\u0005M?7adV#2}<\u001f\"#ldP+vz+J#2k~\u001f".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'D';
                    break;
                }
                case 1: {
                    c6 = '?';
                    break;
                }
                case 2: {
                    c6 = 'M';
                    break;
                }
                case 3: {
                    c6 = 'V';
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
        final char[] charArray4 = "dV>vv+Km?vdK%386^#1}d\u000fm{8".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'D';
                    break;
                }
                case 1: {
                    c8 = '?';
                    break;
                }
                case 2: {
                    c8 = 'M';
                    break;
                }
                case 3: {
                    c8 = 'V';
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
        final char[] charArray5 = "\nJ!:8 ^978%M?7aj".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'D';
                    break;
                }
                case 1: {
                    c10 = '?';
                    break;
                }
                case 2: {
                    c10 = 'M';
                    break;
                }
                case 3: {
                    c10 = 'V';
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
        final char[] charArray6 = "\u0014V.\"m6Z\t7l%{(08-Lm8w0\u001f.:w*Z,4t!\u0011".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'D';
                    break;
                }
                case 1: {
                    c12 = '?';
                    break;
                }
                case 2: {
                    c12 = 'M';
                    break;
                }
                case 3: {
                    c12 = 'V';
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
        final char[] charArray7 = "\u0010W(v\u007f-I(88%M?7adV>vv+Km:y6X(v}*P81pdK\"vp+S)vl,Zm5w*K(8l7\u001f\"080W$%8+]'3{0\u0005m".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'D';
                    break;
                }
                case 1: {
                    c14 = '?';
                    break;
                }
                case 2: {
                    c14 = 'M';
                    break;
                }
                case 3: {
                    c14 = 'V';
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
        PictureDataDef.z = z;
    }
}
