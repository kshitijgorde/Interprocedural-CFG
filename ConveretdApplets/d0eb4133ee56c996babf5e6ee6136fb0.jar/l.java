import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class l extends MouseAdapter
{
    private final x a;
    private static String z;
    
    l(final x a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final int m = fb.m;
        final int clickCount = mouseEvent.getClickCount();
        final int n = 2;
        final int contains;
        Label_0084: {
            if (m == 0) {
                if (clickCount == n) {
                    contains = (this.a.b.b.contains(mouseEvent.getX(), mouseEvent.getY()) ? 1 : 0);
                    if (m != 0) {
                        break Label_0084;
                    }
                    if (contains != 0) {
                        x.a(this.a).a(l.z + this.a.c, 1);
                    }
                }
                mouseEvent.getModifiers();
            }
        }
        if (contains > 0) {
            x.a(this.a).Bb.show(x.a(this.a).Tb.d, mouseEvent.getX() - 50, mouseEvent.getY() - 5);
        }
    }
    
    static {
        final char[] charArray = "P0CL]'".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = 'x';
                    break;
                }
                case 2: {
                    c2 = '\f';
                    break;
                }
                case 3: {
                    c2 = '\u0005';
                    break;
                }
                default: {
                    c2 = '\u000e';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        l.z = new String(charArray).intern();
    }
}
