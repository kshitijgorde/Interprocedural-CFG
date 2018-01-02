// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

public class JCStringTokenizer
{
    private int index;
    private String string;
    private int length;
    private boolean count;
    public boolean strip_esc;
    char escape_char;
    private char[] token;
    
    public JCStringTokenizer(final String s) {
        this.count = false;
        this.strip_esc = true;
        this.escape_char = '\\';
        if (s != null) {
            this.string = s.trim();
            this.length = this.string.length();
        }
    }
    
    public static String[] parse(final String s, final char c) {
        return parse(s, c, '\\');
    }
    
    public static String[] parse(final String s, final char c, final char escape_char) {
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        jcStringTokenizer.escape_char = escape_char;
        final String[] array = new String[jcStringTokenizer.countTokens(c)];
        for (int i = 0; i < array.length; ++i) {
            array[i] = jcStringTokenizer.nextToken(c);
            if (array[i] == null) {
                array[i] = "";
            }
        }
        return array;
    }
    
    public char getEscapeChar() {
        return this.escape_char;
    }
    
    public void setEscapeChar(final char escape_char) {
        this.escape_char = escape_char;
    }
    
    public String nextToken() {
        if (this.string == null || this.index >= this.length) {
            return null;
        }
        int index;
        for (index = this.index; index < this.length && Character.isSpace(this.string.charAt(index)); ++index) {}
        if ((this.index = index) >= this.length) {
            return null;
        }
        if (!this.count) {
            this.token = new char[this.length + 1];
        }
        int index2 = this.index;
        int n = 0;
        while (index2 < this.length && !Character.isSpace(this.string.charAt(index2))) {
            if (!this.count) {
                this.token[n++] = this.string.charAt(index2);
            }
            ++index2;
        }
        this.index = index2 + 1;
        if (!this.count) {
            return new String(this.token).trim();
        }
        return null;
    }
    
    public String nextToken(final char c) {
        if (this.string == null || this.index >= this.length) {
            return null;
        }
        if (!this.count) {
            this.token = new char[this.length + 1];
        }
        int i;
        int n;
        for (i = this.index, n = 0; i < this.length; ++i, ++n) {
            if (this.escape_char != '\0' && i + 1 < this.length && this.string.charAt(i) == this.escape_char) {
                if (!this.strip_esc) {
                    if (this.token != null) {
                        this.token[n++] = this.string.charAt(i);
                        this.token[n] = this.string.charAt(++i);
                    }
                }
                else {
                    ++i;
                    if (!this.count) {
                        if (this.string.charAt(i) == 'n') {
                            this.token[n] = '\n';
                        }
                        else {
                            this.token[n] = this.string.charAt(i);
                        }
                    }
                }
            }
            else {
                if (this.string.charAt(i) == c) {
                    break;
                }
                if (!this.count) {
                    this.token[n] = this.string.charAt(i);
                }
            }
        }
        this.index = i + 1;
        if (!this.count && n > 0) {
            return new String(this.token, 0, n);
        }
        return null;
    }
    
    public int countTokens(final char c) {
        final int index = this.index;
        this.count = true;
        int n = 0;
        while (this.index < this.length) {
            this.nextToken(c);
            ++n;
        }
        this.index = index;
        this.count = false;
        return n;
    }
    
    public boolean hasMoreTokens() {
        return this.index < this.length;
    }
    
    public int getPosition() {
        return this.index;
    }
}
