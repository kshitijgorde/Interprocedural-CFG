import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class j extends MouseAdapter
{
    private final eb a;
    private static String z;
    
    j(final eb a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        eb.a(this.a).e();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int equalsIgnoreCase = eb.a(this.a).P.equalsIgnoreCase(j.z) ? 1 : 0;
        if (fb.m == 0) {
            if (equalsIgnoreCase == 0) {
                return;
            }
            final int n = mouseEvent.getModifiers() & 0x4;
        }
        if (equalsIgnoreCase > 0) {
            eb.a(this.a).zb.show(eb.a(this.a).Tb.d, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    static {
        final char[] charArray = "C^]".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = ',';
                    break;
                }
                case 1: {
                    c2 = '8';
                    break;
                }
                case 2: {
                    c2 = ';';
                    break;
                }
                case 3: {
                    c2 = 'F';
                    break;
                }
                default: {
                    c2 = 'k';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        j.z = new String(charArray).intern();
    }
}
