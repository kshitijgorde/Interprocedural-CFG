import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class y extends Panel
{
    private final esChat a;
    public d b;
    public b c;
    int d;
    int e;
    private static String[] z;
    
    y(final esChat a, final eb eb, final boolean b) {
        this.a = a;
        this.setLayout(new BorderLayout());
        this.setBackground(a.g);
        a.getClass();
        this.b = new d(a, 0, b);
        this.b.E = eb;
        this.b.setFont(new Font(a.Y, 0, a.bb));
        this.e = this.getFontMetrics(this.b.getFont()).getHeight();
        this.add(this.b, y.z[1]);
        a.getClass();
        this.c = new b(a, this.e);
        this.c.e = eb;
        this.add(this.c, y.z[0]);
        this.b.J = this.c;
        this.d = this.b.getHeight();
    }
    
    public void a(final String s) {
        this.b.d(s);
    }
    
    public void a() {
        this.b.a(this.c.b() * this.e);
    }
    
    public void setFont(final Font font) {
        this.b.setFont(font);
        this.e = this.getFontMetrics(font).getHeight();
        this.a();
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\u0016rZv".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'S';
                    break;
                }
                case 1: {
                    c2 = '\u0013';
                    break;
                }
                case 2: {
                    c2 = ')';
                    break;
                }
                case 3: {
                    c2 = '\u0002';
                    break;
                }
                default: {
                    c2 = '\u001d';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0010vGvx!".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'S';
                    break;
                }
                case 1: {
                    c4 = '\u0013';
                    break;
                }
                case 2: {
                    c4 = ')';
                    break;
                }
                case 3: {
                    c4 = '\u0002';
                    break;
                }
                default: {
                    c4 = '\u001d';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        y.z = z;
    }
}
