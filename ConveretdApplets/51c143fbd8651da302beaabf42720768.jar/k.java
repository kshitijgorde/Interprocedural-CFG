import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class k extends MouseAdapter
{
    private final fb a;
    private static String z;
    
    k(final fb a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        fb.a(this.a).e();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int equalsIgnoreCase = fb.a(this.a).T.equalsIgnoreCase(k.z) ? 1 : 0;
        if (!d.r) {
            if (equalsIgnoreCase == 0) {
                return;
            }
            final int n = mouseEvent.getModifiers() & 0x4;
        }
        if (equalsIgnoreCase > 0) {
            fb.a(this.a).Fb.show(fb.a(this.a).Zb.e, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    static {
        final char[] charArray = "~\u00184".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0011';
                    break;
                }
                case 1: {
                    c2 = '~';
                    break;
                }
                case 2: {
                    c2 = 'R';
                    break;
                }
                case 3: {
                    c2 = '|';
                    break;
                }
                default: {
                    c2 = 'k';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        k.z = new String(charArray).intern();
    }
}
