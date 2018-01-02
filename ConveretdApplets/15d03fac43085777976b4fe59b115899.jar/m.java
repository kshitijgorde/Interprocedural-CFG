import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class m extends MouseAdapter
{
    private final y a;
    private static String z;
    
    m(final y a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        final int contains;
        Label_0084: {
            if (!r) {
                if (clickCount == n) {
                    contains = (this.a.b.b.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                    if (r) {
                        break Label_0084;
                    }
                    if (contains != 0) {
                        y.a(this.a).a(m.z + this.a.c, 1);
                    }
                }
                mouseEvent.getModifiers();
            }
        }
        if (contains > 0) {
            y.a(this.a).Hb.show(y.a(this.a).Zb.e, mouseEvent.getX() - 50, mouseEvent.getY() - 5);
        }
    }
    
    static {
        final char[] charArray = "%Z[>eR".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'r';
                    break;
                }
                case 1: {
                    c2 = '\u0012';
                    break;
                }
                case 2: {
                    c2 = '\u0014';
                    break;
                }
                case 3: {
                    c2 = 'w';
                    break;
                }
                default: {
                    c2 = '6';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        m.z = new String(charArray).intern();
    }
}
