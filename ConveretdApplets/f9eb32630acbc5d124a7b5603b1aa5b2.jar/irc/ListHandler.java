// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Hashtable;

public class ListHandler
{
    private boolean _baseAll;
    private Hashtable _list;
    private String _orig;
    
    public ListHandler(String convert) {
        this._orig = convert;
        convert = this.convert(convert);
        this._baseAll = true;
        this._list = new Hashtable();
        final StringTokenizer stringTokenizer = new StringTokenizer(convert, "\u0001\u0002", true);
        if (!stringTokenizer.hasMoreTokens()) {
            return;
        }
        if (stringTokenizer.nextToken().equals("none")) {
            this._baseAll = false;
        }
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("\u0001")) {
                nextToken = "-";
            }
            else if (nextToken.equals("\u0002")) {
                nextToken = "+";
            }
            if (!stringTokenizer.hasMoreTokens()) {
                break;
            }
            this._list.put(stringTokenizer.nextToken().toLowerCase(Locale.ENGLISH), nextToken);
        }
    }
    
    private String convert(final String s) {
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '-') {
                c = '\u0001';
            }
            else if (c == '+') {
                c = '\u0002';
            }
            else if (c == '\\' && i != s.length() - 1) {
                ++i;
                c = s.charAt(i);
            }
            string += c;
        }
        return string;
    }
    
    public boolean inList(String lowerCase) {
        lowerCase = lowerCase.toLowerCase(Locale.ENGLISH);
        final String s = this._list.get(lowerCase);
        if (s == null) {
            return this._baseAll;
        }
        return s.equals("+");
    }
    
    public String toString() {
        return this._orig;
    }
}
