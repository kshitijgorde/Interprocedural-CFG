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
        final char[] charArray = "( X\u0001(\u001c".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = 'B';
                    break;
                }
                case 2: {
                    c2 = '4';
                    break;
                }
                case 3: {
                    c2 = '`';
                    break;
                }
                default: {
                    c2 = 'F';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u001f6@\u0010|XmC\u00171Y'B\u0012#\u00191[\u00062Y![\r".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'w';
                    break;
                }
                case 1: {
                    c4 = 'B';
                    break;
                }
                case 2: {
                    c4 = '4';
                    break;
                }
                case 3: {
                    c4 = '`';
                    break;
                }
                default: {
                    c4 = 'F';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        q.z = z;
    }
}
