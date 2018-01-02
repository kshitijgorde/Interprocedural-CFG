// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.net;

public class UrlEncodedBuf
{
    private boolean emptyBuf;
    private StringBuffer encodedBuf;
    static char[] digits;
    
    public UrlEncodedBuf() {
        this.encodedBuf = new StringBuffer();
        this.empty();
    }
    
    public void empty() {
        this.encodedBuf.setLength(0);
        this.emptyBuf = true;
    }
    
    public void addPair(final String s, final String s2) {
        if (this.emptyBuf) {
            this.emptyBuf = false;
        }
        else {
            this.encodedBuf.append("&");
        }
        this.encodedBuf.append(CGIEscapeString(s));
        this.encodedBuf.append("=");
        this.encodedBuf.append(CGIEscapeString(s2));
    }
    
    public void addPair(final String s, final int n) {
        this.addPair(s, Integer.toString(n));
    }
    
    public String toString() {
        return this.encodedBuf.toString();
    }
    
    public static String CGIEscapeString(final String s) {
        String s2 = "";
        try {
            final byte[] bytes = s.getBytes("UTF8");
            for (int length = bytes.length, i = 0; i < length; ++i) {
                final char c = (char)bytes[i];
                if (isSpecial(c)) {
                    s2 += EscapeChar((char)bytes[i]);
                }
                else if (c == ' ') {
                    s2 += '+';
                }
                else {
                    s2 += c;
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static String EscapeChar(final char c) {
        return "%" + UrlEncodedBuf.digits[c >> 4 & '\u000f'] + UrlEncodedBuf.digits[c & '\u000f'];
    }
    
    public static boolean isSpecial(final char c) {
        if (c <= '\u001f' || c >= '\u007f') {
            return true;
        }
        switch (c) {
            case '\"':
            case '#':
            case '%':
            case '&':
            case '\'':
            case '+':
            case '/':
            case '<':
            case '=':
            case '>':
            case '?':
            case '[':
            case '\\':
            case ']':
            case '^':
            case '{':
            case '|':
            case '}':
            case '~': {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    static {
        UrlEncodedBuf.digits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
