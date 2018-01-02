import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ab extends Panel
{
    private final esChat a;
    public z b;
    private static String z;
    
    ab(final esChat a, final String s) {
        this.a = a;
        this.setLayout(new BorderLayout());
        a.getClass();
        this.b = new z(a, null, true);
        this.b.b.setFont(new Font(a.eb, a.d, a.hb));
        this.add(this.b, ab.z);
        this.b.b.addMouseListener(new j(this));
    }
    
    static esChat a(final ab ab) {
        return ab.a;
    }
    
    static {
        final char[] charArray = "kZ.L:Z".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '(';
                    break;
                }
                case 1: {
                    c2 = '?';
                    break;
                }
                case 2: {
                    c2 = '@';
                    break;
                }
                case 3: {
                    c2 = '8';
                    break;
                }
                default: {
                    c2 = '_';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        ab.z = new String(charArray).intern();
    }
}
