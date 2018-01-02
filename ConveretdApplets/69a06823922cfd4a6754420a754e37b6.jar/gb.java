import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class gb
{
    private Vector a;
    private int b;
    private static final String[] z;
    
    public gb(final String s, final int b) {
        final int c = d.c;
        this.b = b;
        this.a = new Vector();
        int i = 0;
        while (i < s.length()) {
            String substring = gb.z[1];
            if (i <= s.length() - 5) {
                substring = s.substring(i, i + 5);
            }
            String substring2 = gb.z[1];
            if (i <= s.length() - 6) {
                substring2 = s.substring(i, i + 6);
            }
            if (i <= s.length() - 5 && substring.toLowerCase().indexOf(gb.z[4]) != -1) {
                this.b = -1;
                i += 5;
            }
            if (i <= s.length() - 5 && substring.toLowerCase().indexOf(gb.z[3]) != -1) {
                this.b = -2;
                i += 5;
            }
            if (i <= s.length() - 6 && substring2.toLowerCase().indexOf(gb.z[2]) != -1) {
                this.b = -3;
                i += 6;
            }
            if (i <= s.length() - 6 && substring2.toLowerCase().indexOf(gb.z[0]) != -1) {
                this.b = -3;
                i += 6;
            }
            if (i < s.length()) {
                this.a.addElement(new fb(s.charAt(i), this.b));
            }
            ++i;
            if (c != 0) {
                break;
            }
        }
    }
    
    public fb[] a() {
        final int c = d.c;
        final fb[] array = new fb[this.a.size()];
        int i = 0;
        while (i < array.length) {
            array[i] = (fb)this.a.elementAt(i);
            ++i;
            if (c != 0) {
                break;
            }
        }
        return array;
    }
    
    public int b() {
        return this.b;
    }
    
    static {
        final String[] z2 = new String[5];
        final int n = 0;
        final char[] charArray = "CwXUsA".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u007f';
                    break;
                }
                case 1: {
                    c2 = 'X';
                    break;
                }
                case 2: {
                    c2 = '+';
                    break;
                }
                case 3: {
                    c2 = ' ';
                    break;
                }
                default: {
                    c2 = '\u0003';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u001e:HDf\u0019?".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u007f';
                    break;
                }
                case 1: {
                    c4 = 'X';
                    break;
                }
                case 2: {
                    c4 = '+';
                    break;
                }
                case 3: {
                    c4 = ' ';
                    break;
                }
                default: {
                    c4 = '\u0003';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "CwXUaA".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u007f';
                    break;
                }
                case 1: {
                    c6 = 'X';
                    break;
                }
                case 2: {
                    c6 = '+';
                    break;
                }
                case 3: {
                    c6 = ' ';
                    break;
                }
                default: {
                    c6 = '\u0003';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "C+^P=".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u007f';
                    break;
                }
                case 1: {
                    c8 = 'X';
                    break;
                }
                case 2: {
                    c8 = '+';
                    break;
                }
                case 3: {
                    c8 = ' ';
                    break;
                }
                default: {
                    c8 = '\u0003';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "C+^B=".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u007f';
                    break;
                }
                case 1: {
                    c10 = 'X';
                    break;
                }
                case 2: {
                    c10 = '+';
                    break;
                }
                case 3: {
                    c10 = ' ';
                    break;
                }
                default: {
                    c10 = '\u0003';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        z = z2;
    }
}
