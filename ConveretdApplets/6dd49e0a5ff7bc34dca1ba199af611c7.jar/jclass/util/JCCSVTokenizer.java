// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

public class JCCSVTokenizer
{
    private int index;
    private String string;
    private int length;
    private boolean count;
    private char[] token;
    
    public JCCSVTokenizer(final String s) {
        this.count = false;
        this.string = s.trim();
        this.length = this.string.length();
    }
    
    public String nextToken() {
        if (this.string == null || this.index >= this.length) {
            return null;
        }
        if (!this.count) {
            this.token = new char[this.length + 1];
        }
        int n = 0;
        int i = this.index;
        int n2 = 0;
        while (i < this.length) {
            if (i + 1 < this.length && this.string.charAt(i) == '\"' && this.string.charAt(i + 1) == '\"') {
                ++i;
                if (!this.count) {
                    this.token[n2++] = '\"';
                }
            }
            else if (i == this.index && this.string.charAt(i) == '\"') {
                n = 1;
            }
            else if (n != 0 && this.string.charAt(i) == '\"') {
                n = 0;
            }
            else {
                if (n == 0 && this.string.charAt(i) == ',') {
                    break;
                }
                if (!this.count) {
                    this.token[n2++] = this.string.charAt(i);
                }
            }
            ++i;
        }
        this.index = i + 1;
        return (!this.count && n2 > 0) ? new String(this.token, 0, n2) : null;
    }
    
    public int countTokens() {
        final int index = this.index;
        this.count = true;
        int n = 0;
        while (this.index < this.length) {
            this.nextToken();
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
