// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.Serializable;

public final class cB implements Serializable
{
    private long q;
    
    public final String toString() {
        final long q;
        final long n;
        String s = String.valueOf((n = (q = this.q) >> 16) % 1000L);
        String s2 = String.valueOf(n / 1000L % 100000L);
        String s3 = String.valueOf(n / 100000000L);
        if (s.length() < 3) {
            s = "0000".substring(0, 3 - s.length()) + s;
        }
        if (s2.length() < 5) {
            s2 = "0000".substring(0, 5 - s2.length()) + s2;
        }
        if (s3.length() < 3) {
            s3 = "0000".substring(0, 3 - s3.length()) + s3;
        }
        return "DC-" + s + "-" + s2 + "-" + (char)((q & 0xFF00L) >> 8) + (char)(q & 0xFFL) + "-" + s3;
    }
    
    public cB(final String s, final long q, final int n) {
        this.q = q;
    }
}
