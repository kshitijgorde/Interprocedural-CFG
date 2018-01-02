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
        this.setBackground(a.g);
        final String q = a.q;
        a.getClass();
        this.b = new db(a);
        a.getClass();
        a.Mb = new cb(a, hb.z[1]);
        this.b.a(hb.z[1], a.Mb, true);
        a.getClass();
        this.c = new gb(a);
        a.getClass();
        this.d = new fb(a);
        a.nb = false;
        char c2;
        final char c = c2 = q.charAt(4);
        if (m == 0) {
            if (c == 't') {
                this.add(hb.z[3], this.b);
            }
            this.e = new Panel(new BorderLayout());
            this.d.setForeground(a.i);
            final boolean fb;
            c2 = (char)((fb = a.Fb) ? 1 : 0);
        }
        if (m == 0) {
            if (c == '\0') {
                this.e.add(hb.z[0], this.c);
            }
            this.e.add(hb.z[2], this.d);
            if (m != 0) {
                return;
            }
            c2 = a.q.charAt(5);
        }
        if (c2 == 'r') {
            this.add(hb.z[0], this.e);
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "{4\\+a".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '5';
                    break;
                }
                case 1: {
                    c2 = '[';
                    break;
                }
                case 2: {
                    c2 = '.';
                    break;
                }
                case 3: {
                    c2 = '_';
                    break;
                }
                default: {
                    c2 = '\t';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "f/O+|F".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '5';
                    break;
                }
                case 1: {
                    c4 = '[';
                    break;
                }
                case 2: {
                    c4 = '.';
                    break;
                }
                case 3: {
                    c4 = '_';
                    break;
                }
                default: {
                    c4 = '\t';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "f4[+a".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '5';
                    break;
                }
                case 1: {
                    c6 = '[';
                    break;
                }
                case 2: {
                    c6 = '.';
                    break;
                }
                case 3: {
                    c6 = '_';
                    break;
                }
                default: {
                    c6 = '\t';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "v>@+lG".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '5';
                    break;
                }
                case 1: {
                    c8 = '[';
                    break;
                }
                case 2: {
                    c8 = '.';
                    break;
                }
                case 3: {
                    c8 = '_';
                    break;
                }
                default: {
                    c8 = '\t';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        hb.z = z;
    }
}
