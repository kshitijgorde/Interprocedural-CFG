// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util;

public class $IC
{
    String s;
    int $JC;
    char separatorChar;
    int $KC;
    
    public $IC(final String s, final char separatorChar) {
        this.s = s;
        this.separatorChar = separatorChar;
        this.$KC = -1;
        this.$JC = s.length();
    }
    
    public String nextToken(final String s) {
        if (this.$KC < this.$JC) {
            final int n = this.$KC + 1;
            this.$KC = this.s.indexOf(this.separatorChar, n);
            if (this.$KC < 0) {
                this.$KC = this.$JC;
            }
            final String trim = this.s.substring(n, this.$KC).trim();
            if (trim.length() != 0) {
                return trim;
            }
        }
        return s;
    }
}
