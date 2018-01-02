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
        final char[] charArray = "&C\u0004\u001b5\u0012".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'y';
                    break;
                }
                case 1: {
                    c2 = '!';
                    break;
                }
                case 2: {
                    c2 = 'h';
                    break;
                }
                case 3: {
                    c2 = 'z';
                    break;
                }
                default: {
                    c2 = '[';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0011U\u001c\naV\u000e\u001f\r,WD\u001e\b>\u0017R\u0007\u001c/WB\u0007\u0017".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'y';
                    break;
                }
                case 1: {
                    c4 = '!';
                    break;
                }
                case 2: {
                    c4 = 'h';
                    break;
                }
                case 3: {
                    c4 = 'z';
                    break;
                }
                default: {
                    c4 = '[';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        p.z = z;
    }
}
