import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class z extends Panel
{
    private final esChat a;
    public e b;
    public b c;
    int d;
    int e;
    private static String[] z;
    
    z(final esChat a, final fb fb, final boolean b) {
        this.a = a;
        this.setLayout(new BorderLayout());
        this.setBackground(a.h);
        a.getClass();
        this.b = new e(a, 0, b);
        this.b.E = fb;
        this.b.setFont(new Font(a.eb, a.d, a.hb));
        this.e = this.getFontMetrics(this.b.getFont()).getHeight();
        this.add(this.b, z.z[1]);
        a.getClass();
        this.c = new b(a, this.e);
        this.c.e = fb;
        this.add(this.c, z.z[0]);
        this.b.J = this.c;
        this.d = this.b.getHeight();
    }
    
    public void a(final String s) {
        this.b.d(s);
    }
    
    public void a() {
        final e b = this.b;
        if (!d.r) {
            if (b == null) {
                return;
            }
            final e b2 = this.b;
        }
        b.b();
    }
    
    public void b() {
        this.b.a(this.c.b() * this.e);
    }
    
    public void setFont(final Font font) {
        this.b.setFont(font);
        this.e = this.getFontMetrics(font).getHeight();
        this.b();
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "#RJY".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'f';
                    break;
                }
                case 1: {
                    c2 = '3';
                    break;
                }
                case 2: {
                    c2 = '9';
                    break;
                }
                case 3: {
                    c2 = '-';
                    break;
                }
                default: {
                    c2 = '\u001b';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "%VWY~\u0014".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'f';
                    break;
                }
                case 1: {
                    c4 = '3';
                    break;
                }
                case 2: {
                    c4 = '9';
                    break;
                }
                case 3: {
                    c4 = '-';
                    break;
                }
                default: {
                    c4 = '\u001b';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        z.z = z;
    }
}
