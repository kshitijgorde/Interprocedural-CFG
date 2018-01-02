// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.io.Serializable;

public class bx implements Serializable
{
    public String a;
    public long n;
    public int i;
    
    protected static String a(final long n) {
        final long n2 = n >> 16;
        String s = String.valueOf(n2 % 1000L);
        String s2 = String.valueOf(n2 / 1000L % 100000L);
        String s3 = String.valueOf(n2 / 100000000L);
        if (s.length() < 3) {
            s = "0000".substring(0, 3 - s.length()) + s;
        }
        if (s2.length() < 5) {
            s2 = "0000".substring(0, 5 - s2.length()) + s2;
        }
        if (s3.length() < 3) {
            s3 = "0000".substring(0, 3 - s3.length()) + s3;
        }
        return "DC-" + s + "-" + s2 + "-" + (char)((n & 0xFF00L) >> 8) + (char)(n & 0xFFL) + "-" + s3;
    }
    
    public String toString() {
        return a(this.n);
    }
    
    public String a() {
        return this.a;
    }
    
    public boolean j() {
        long n = 0L;
        char upperCase = Character.toUpperCase(this.a.charAt(0));
        char upperCase2 = Character.toUpperCase(this.a.charAt(1));
        if (upperCase < 'A' || upperCase > 'Z') {
            upperCase = (char)(upperCase % '\u001a' + 'A');
        }
        if (upperCase2 < 'A' || upperCase2 > 'Z') {
            upperCase2 = (char)(upperCase2 % '\u001a' + 'A');
        }
        final String string = Integer.toString(upperCase + upperCase2, 25);
        final char c = (char)(Character.digit(string.charAt(0), 25) + 65);
        final char c2 = (char)(Character.digit(string.charAt(1), 25) + 65);
        for (int i = 0; i < this.a.length() - 1; ++i) {
            n += this.a.charAt(i) * this.a.charAt(i + 1);
        }
        final long n2 = 54569215829L;
        final long n3 = ((this.i + n ^ n2) & 0xFFFFFFFFFFFFL) * n2 + 11L & 0xFFFFFFFFFFFFL;
        return (Math.abs((n3 >> 16 << 32) + (int)((n3 * n2 + 11L & 0xFFFFFFFFFFFFL) >> 16)) % 62545461248L + 16400842752L << 16) + (c << 8) + c2 == this.n;
    }
    
    public bx(final String a, final long n, final int i) {
        this.a = a;
        this.n = n;
        this.i = i;
    }
    
    public bx(final String s, final String s2, final int n) {
        if (s2.length() == 19 && s2.startsWith("DC")) {
            try {
                final int int1 = Integer.parseInt(s2.substring(16, 19));
                final int int2 = Integer.parseInt(s2.substring(7, 12));
                final int int3 = Integer.parseInt(s2.substring(3, 6));
                this.a = s;
                this.n = (int1 * 100000000L + int2 * 1000L + int3 << 16) + (s2.charAt(13) << 8) + s2.charAt(14);
                this.i = n;
                return;
            }
            catch (NumberFormatException ex) {}
        }
        this.a = s;
        this.n = 0L;
        this.i = n;
    }
}
