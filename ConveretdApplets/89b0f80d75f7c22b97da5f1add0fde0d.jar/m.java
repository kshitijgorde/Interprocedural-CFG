import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class m extends MouseAdapter
{
    private final cb a;
    private static String z;
    
    m(final cb a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        final int contains;
        Label_0064: {
            if (m == 0) {
                if (clickCount == n) {
                    contains = (this.a.d.b.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                    if (m != 0) {
                        break Label_0064;
                    }
                    if (contains != 0) {
                        cb.a(this.a).a(m.z, 1);
                    }
                }
                mouseEvent.getModifiers();
            }
        }
        if (contains > 0) {
            cb.a(this.a).wb.show(cb.a(this.a).Ob.d, mouseEvent.getX() - 50, mouseEvent.getY() - 5);
        }
    }
    
    static {
        final char[] charArray = ":Q.\u007fI%".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'v';
                    break;
                }
                case 1: {
                    c2 = '\u0004';
                    break;
                }
                case 2: {
                    c2 = '}';
                    break;
                }
                case 3: {
                    c2 = ':';
                    break;
                }
                default: {
                    c2 = '\u001b';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        m.z = new String(charArray).intern();
    }
}