import java.net.URL;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class q extends WindowAdapter
{
    private final f a;
    private static String[] z;
    
    q(final f a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        try {
            f.a(this.a).getAppletContext().showDocument(new URL(q.z[1]), q.z[0]);
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
        final char[] charArray = ")d[=5\u001d".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'v';
                    break;
                }
                case 1: {
                    c2 = '\u0006';
                    break;
                }
                case 2: {
                    c2 = '7';
                    break;
                }
                case 3: {
                    c2 = '\\';
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
        final char[] charArray2 = "\u001erC,aY)@+,XcA.>\u0018uX:/XeX1".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'v';
                    break;
                }
                case 1: {
                    c4 = '\u0006';
                    break;
                }
                case 2: {
                    c4 = '7';
                    break;
                }
                case 3: {
                    c4 = '\\';
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
        q.z = z;
    }
}
