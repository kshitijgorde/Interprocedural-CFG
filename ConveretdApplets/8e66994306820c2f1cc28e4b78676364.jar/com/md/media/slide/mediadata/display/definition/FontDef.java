// 
// Decompiled by Procyon v0.5.30
// 

package com.md.media.slide.mediadata.display.definition;

import a.b.o.a.b.d;
import a.b.g.b;
import java.awt.Font;
import a.b.o.a.a.e;

public class FontDef implements e
{
    private String a;
    private int b;
    private int c;
    private static String[] z;
    
    public FontDef() {
        this.a = null;
        this.b = -1;
        this.c = -1;
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    public void b(final int c) {
        this.c = c;
    }
    
    public Font a() {
        return new Font(this.b(), this.c(), this.d());
    }
    
    public String b() {
        return this.a;
    }
    
    public int c() {
        return this.b;
    }
    
    public int d() {
        return this.c;
    }
    
    public Object clone() {
        try {
            final FontDef fontDef = (FontDef)super.clone();
            if (this.b() != null) {
                fontDef.a(new String(this.b()));
            }
            return fontDef;
        }
        catch (CloneNotSupportedException ex) {
            a.b.g.b.a().d().a(this.getClass().getName()).e(FontDef.z[3], ex);
            return null;
        }
    }
    
    public int a() {
        return 0;
    }
    
    public byte[] a() {
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
    
    public int b(final byte[] array, final int n) {
        return 0;
    }
    
    public int a(final byte[] array) {
        return this.a(array, 0);
    }
    
    public int a(final byte[] array, final int n) {
        return 0;
    }
    
    public a.b.o.a.b.d a() {
        return new a.b.o.a.b.b(this.a());
    }
    
    public String toString() {
        return new String(FontDef.z[2] + this.a + FontDef.z[0] + this.b + FontDef.z[1] + this.c + "}");
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "R{\u001eLu\u0012>W\u0018".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '~';
                    break;
                }
                case 1: {
                    c2 = '[';
                    break;
                }
                case 2: {
                    c2 = 'm';
                    break;
                }
                case 3: {
                    c2 = '8';
                    break;
                }
                default: {
                    c2 = '\f';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "R{\u001eQv\u001baM".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '~';
                    break;
                }
                case 1: {
                    c4 = '[';
                    break;
                }
                case 2: {
                    c4 = 'm';
                    break;
                }
                case 3: {
                    c4 = '8';
                    break;
                }
                default: {
                    c4 = '\f';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "84\u0003LH\u001b=\u0004Ve\n2\u0002V,\u00055\fUiD{".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '~';
                    break;
                }
                case 1: {
                    c6 = '[';
                    break;
                }
                case 2: {
                    c6 = 'm';
                    break;
                }
                case 3: {
                    c6 = '8';
                    break;
                }
                default: {
                    c6 = '\f';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "=:\u0003Vc\n{\u000eTc\u0010>M~c\u0010/)]jD{".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '~';
                    break;
                }
                case 1: {
                    c8 = '[';
                    break;
                }
                case 2: {
                    c8 = 'm';
                    break;
                }
                case 3: {
                    c8 = '8';
                    break;
                }
                default: {
                    c8 = '\f';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        FontDef.z = z;
    }
}
