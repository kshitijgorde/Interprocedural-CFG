import java.awt.event.MouseListener;
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
    public y b;
    private static String z;
    
    z(final esChat a, final String s) {
        this.a = a;
        this.setLayout(new BorderLayout());
        a.getClass();
        this.b = new y(a, null, true);
        this.b.b.setFont(new Font(a.bb, a.d, a.eb));
        this.add(this.b, z.z);
        this.b.b.addMouseListener(new i(this));
    }
    
    static esChat a(final z z) {
        return z.a;
    }
    
    static {
        final char[] charArray = "\u00132p%Z\"".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'P';
                    break;
                }
                case 1: {
                    c2 = 'W';
                    break;
                }
                case 2: {
                    c2 = '\u001e';
                    break;
                }
                case 3: {
                    c2 = 'Q';
                    break;
                }
                default: {
                    c2 = '?';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        z.z = new String(charArray).intern();
    }
}