// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.Arrays;

public final class l
{
    private char[] a;
    
    public l(final int n) {
        this.a = null;
        this.a = new char[n];
    }
    
    public final void a(final int n) {
        Arrays.fill(this.a, (char)(n & 0xFF));
    }
    
    public final char b(final int n) {
        if (n >= 0 && n < this.a.length) {
            return (char)(this.a[n] & '\u00ff');
        }
        return '\0';
    }
    
    public final void a(final int n, final char c) {
        if (n >= 0 && n < this.a.length) {
            this.a[n] = (char)(c & '\u00ff');
        }
    }
}
