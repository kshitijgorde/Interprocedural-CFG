import java.net.URL;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class p extends WindowAdapter
{
    private final e a;
    private static String[] z;
    
    p(final e a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        try {
            e.a(this.a).getAppletContext().showDocument(new URL(p.z[1]), p.z[0]);
        }
        catch (Exception ex) {
            return;
        }
        this.a.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        this.a.toFront();
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\u0019huSW-".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'F';
                    break;
                }
                case 1: {
                    c2 = '\n';
                    break;
                }
                case 2: {
                    c2 = '\u0019';
                    break;
                }
                case 3: {
                    c2 = '2';
                    break;
                }
                default: {
                    c2 = '9';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = ".~mB\u0003i%nENhoo@\\(yvTMhiv_".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'F';
                    break;
                }
                case 1: {
                    c4 = '\n';
                    break;
                }
                case 2: {
                    c4 = '\u0019';
                    break;
                }
                case 3: {
                    c4 = '2';
                    break;
                }
                default: {
                    c4 = '9';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        p.z = z;
    }
}
