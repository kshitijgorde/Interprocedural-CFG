import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class hb extends Panel
{
    private final esChat a;
    public db b;
    gb c;
    fb d;
    Panel e;
    private static String[] z;
    
    hb(final esChat a) {
        final int m = fb.m;
        super(new BorderLayout());
        this.a = a;
        this.setBackground(a.h);
        final String r = a.r;
        a.getClass();
        this.b = new db(a);
        a.getClass();
        a.Rb = new cb(a, hb.z[0]);
        this.b.a(hb.z[0], a.Rb, true);
        a.getClass();
        this.c = new gb(a);
        a.getClass();
        this.d = new fb(a);
        a.rb = false;
        char c2;
        final char c = c2 = r.charAt(4);
        if (m == 0) {
            if (c == 'm') {
                this.add(hb.z[2], this.b);
            }
            this.e = new Panel(new BorderLayout());
            this.d.setForeground(a.j);
            final boolean kb;
            c2 = (char)((kb = a.Kb) ? 1 : 0);
        }
        if (m == 0) {
            if (c == '\0') {
                this.e.add(hb.z[3], this.c);
            }
            this.e.add(hb.z[1], this.d);
            if (m != 0) {
                return;
            }
            c2 = a.r.charAt(5);
        }
        if (c2 == 'e') {
            this.add(hb.z[3], this.e);
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "r}VCmR".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '!';
                    break;
                }
                case 1: {
                    c2 = '\t';
                    break;
                }
                case 2: {
                    c2 = '7';
                    break;
                }
                case 3: {
                    c2 = '7';
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
        final char[] charArray2 = "rfBCp".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '!';
                    break;
                }
                case 1: {
                    c4 = '\t';
                    break;
                }
                case 2: {
                    c4 = '7';
                    break;
                }
                case 3: {
                    c4 = '7';
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
        final char[] charArray3 = "blYC}S".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '!';
                    break;
                }
                case 1: {
                    c6 = '\t';
                    break;
                }
                case 2: {
                    c6 = '7';
                    break;
                }
                case 3: {
                    c6 = '7';
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
        final char[] charArray4 = "ofECp".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '!';
                    break;
                }
                case 1: {
                    c8 = '\t';
                    break;
                }
                case 2: {
                    c8 = '7';
                    break;
                }
                case 3: {
                    c8 = '7';
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
        hb.z = z;
    }
}
