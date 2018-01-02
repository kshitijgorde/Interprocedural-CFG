// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Vector;

public class StringParser
{
    public static String trim(final String s) {
        int n;
        for (n = 0; n < s.length() && s.charAt(n) == ' '; ++n) {}
        if (n == s.length()) {
            return "";
        }
        int n2;
        for (n2 = s.length() - 1; n2 >= 0 && s.charAt(n2) == ' '; --n2) {}
        if (n2 < 0) {
            return "";
        }
        return s.substring(n, n2 + 1);
    }
    
    private int indexOf(final String s, final char c) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (n == 0 && char1 == c) {
                return i;
            }
            if (char1 == '\"') {
                n = 1 - n;
            }
            if (char1 == '\'') {
                n = 1 - n;
            }
        }
        return -1;
    }
    
    public String[] parseString(String trim) {
        final Vector vector = new Vector<String>();
        while (trim.length() != 0) {
            final int index = this.indexOf(trim, ' ');
            if (index == -1) {
                vector.insertElementAt(trim, vector.size());
                trim = "";
            }
            else {
                final String trim2 = trim(trim.substring(0, index));
                trim = trim(trim.substring(index));
                vector.insertElementAt(trim2, vector.size());
            }
        }
        final String[] array = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
}
