import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.MediaTracker;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ib extends Panel
{
    private final esChat a;
    public eb b;
    MediaTracker c;
    hb d;
    gb e;
    Panel f;
    private static String[] z;
    
    ib(final esChat a) {
        final boolean r = d.r;
        super(new BorderLayout());
        this.a = a;
        this.c = new MediaTracker(this);
        this.setBackground(a.h);
        final String r2 = a.r;
        a.getClass();
        this.b = new eb(a);
        a.getClass();
        a.Xb = new db(a, ib.z[3]);
        this.b.a(ib.z[3], a.Xb, true);
        a.getClass();
        this.d = new hb(a);
        a.getClass();
        this.e = new gb(a);
        a.wb = false;
        char c2;
        final char c = c2 = r2.charAt(4);
        if (!r) {
            if (c == 'm') {
                this.add(ib.z[2], this.b);
            }
            this.f = new Panel(new BorderLayout());
            this.e.setForeground(a.j);
            final boolean qb;
            c2 = (char)((qb = a.Qb) ? 1 : 0);
        }
        if (!r) {
            if (c == '\0') {
                this.f.add(ib.z[0], this.d);
            }
            this.f.add(ib.z[1], this.e);
            if (r) {
                return;
            }
            c2 = a.r.charAt(5);
        }
        if (c2 == 'e') {
            this.add(ib.z[0], this.f);
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "\u0012f&&H".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\\';
                    break;
                }
                case 1: {
                    c2 = '\t';
                    break;
                }
                case 2: {
                    c2 = 'T';
                    break;
                }
                case 3: {
                    c2 = 'R';
                    break;
                }
                default: {
                    c2 = ' ';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u000ff!&H".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\\';
                    break;
                }
                case 1: {
                    c4 = '\t';
                    break;
                }
                case 2: {
                    c4 = 'T';
                    break;
                }
                case 3: {
                    c4 = 'R';
                    break;
                }
                default: {
                    c4 = ' ';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u001fl:&E.".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\\';
                    break;
                }
                case 1: {
                    c6 = '\t';
                    break;
                }
                case 2: {
                    c6 = 'T';
                    break;
                }
                case 3: {
                    c6 = 'R';
                    break;
                }
                default: {
                    c6 = ' ';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u000f}5&U/".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\\';
                    break;
                }
                case 1: {
                    c8 = '\t';
                    break;
                }
                case 2: {
                    c8 = 'T';
                    break;
                }
                case 3: {
                    c8 = 'R';
                    break;
                }
                default: {
                    c8 = ' ';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        ib.z = z;
    }
}
