import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class n extends MouseAdapter
{
    private final db a;
    private static String z;
    
    n(final db a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        final int contains;
        Label_0064: {
            if (!r) {
                if (clickCount == n) {
                    contains = (this.a.d.b.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                    if (r) {
                        break Label_0064;
                    }
                    if (contains != 0) {
                        db.a(this.a).a(n.z, 1);
                    }
                }
                mouseEvent.getModifiers();
            }
        }
        if (contains > 0) {
            db.a(this.a).Gb.show(db.a(this.a).Zb.e, mouseEvent.getX() - 50, mouseEvent.getY() - 5);
        }
    }
    
    static {
        final char[] charArray = "\u0006S$ =\u0019".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'J';
                    break;
                }
                case 1: {
                    c2 = '\u0006';
                    break;
                }
                case 2: {
                    c2 = 'w';
                    break;
                }
                case 3: {
                    c2 = 'e';
                    break;
                }
                default: {
                    c2 = 'o';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        n.z = new String(charArray).intern();
    }
}
