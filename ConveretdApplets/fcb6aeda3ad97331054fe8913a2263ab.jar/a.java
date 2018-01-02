import javax.swing.text.BadLocationException;
import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

// 
// Decompiled by Procyon v0.5.30
// 

public class a extends PlainDocument
{
    public static final String a;
    public static final String b;
    private int c;
    protected String d;
    private boolean e;
    public static boolean f;
    
    public a(final int c, final String d, final boolean e) {
        this.c = 9;
        this.d = null;
        this.d = d;
        this.e = e;
        this.c = c;
    }
    
    public void insertString(final int n, String upperCase, final AttributeSet set) throws BadLocationException {
        final int y = ColorConversion.Y;
        if (upperCase == null) {
            return;
        }
        Label_0063: {
            if (this.d.equals(a.a) && !this.e) {
                upperCase = upperCase.toUpperCase();
                if (y == 0) {
                    break Label_0063;
                }
            }
            if (this.d.equals(a.b) && this.e) {
                upperCase = upperCase;
            }
        }
        int i = 0;
        while (true) {
            while (i < upperCase.length()) {
                final a a = this;
                if (y != 0) {
                    a.insertString(n, upperCase, set);
                    return;
                }
                if (this.d.indexOf(String.valueOf(upperCase.charAt(i))) == -1) {
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
                if (this.getLength() + upperCase.length() > this.c) {
                    Toolkit.getDefaultToolkit().beep();
                    return;
                }
                ++i;
                if (y != 0) {
                    break;
                }
            }
            final a a = this;
            continue;
        }
    }
    
    static {
        final char[] charArray = "\u0014K-0\u0005\u0011L(;\b".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '$';
                    break;
                }
                case 1: {
                    c2 = 'z';
                    break;
                }
                case 2: {
                    c2 = '\u001f';
                    break;
                }
                case 3: {
                    c2 = '\u0003';
                    break;
                }
                default: {
                    c2 = '1';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        b = new String(charArray).intern();
        final char[] charArray2 = "\u0014K-0\u0005\u0011L(;\be8\\Gtb".toCharArray();
        final int j = charArray2.length;
        for (int n3 = 0; j > n3; ++n3) {
            final int n4 = n3;
            final char c3 = charArray2[n4];
            char c4 = '\0';
            switch (n3 % 5) {
                case 0: {
                    c4 = '$';
                    break;
                }
                case 1: {
                    c4 = 'z';
                    break;
                }
                case 2: {
                    c4 = '\u001f';
                    break;
                }
                case 3: {
                    c4 = '\u0003';
                    break;
                }
                default: {
                    c4 = '1';
                    break;
                }
            }
            charArray2[n4] = (char)(c3 ^ c4);
        }
        a = new String(charArray2).intern();
    }
}
