// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

import java.awt.Color;
import java.util.StringTokenizer;

abstract class d extends f
{
    protected String[] a(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    public String a(String lowerCase) {
        final int n = 17;
        lowerCase = lowerCase.toLowerCase();
        final char[] array = new char[n];
        final int length = lowerCase.length();
        char c = '\0';
        final int n2 = 0;
        for (int i = 0; i < length; c += lowerCase.charAt(i++)) {}
        int n3 = 0;
        this.a(c);
        int j = 0;
        int abs = 0;
        while (j < n) {
            final int n4 = (Math.abs(this.a() + abs) + lowerCase.charAt(n3)) % 10;
            if (n4 < 10) {
                array[j] = (char)(n4 + 48);
            }
            else if (n4 < 34) {
                array[j] = (char)(n4 - 10 + 97);
            }
            else {
                array[j] = (char)(n4 - 34 + 65);
            }
            ++j;
            if (++n3 >= length) {
                n3 = n2;
                abs = Math.abs(this.a());
            }
        }
        return new String(array);
    }
    
    protected Color if(final String s) {
        if (s.charAt(0) == '#') {
            return new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return this.do(s);
    }
    
    private Color do(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.equals("black")) {
            return Color.black;
        }
        if (lowerCase.equals("orange")) {
            return Color.orange;
        }
        if (lowerCase.equals("red")) {
            return Color.red;
        }
        if (lowerCase.equals("green")) {
            return Color.green;
        }
        if (lowerCase.equals("blue")) {
            return Color.blue;
        }
        if (lowerCase.equals("cyan")) {
            return Color.cyan;
        }
        if (lowerCase.equals("magenta")) {
            return Color.magenta;
        }
        if (lowerCase.equals("gray")) {
            return Color.gray;
        }
        return Color.white;
    }
}
