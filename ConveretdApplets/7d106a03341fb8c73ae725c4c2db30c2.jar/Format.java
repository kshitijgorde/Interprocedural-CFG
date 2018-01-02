// 
// Decompiled by Procyon v0.5.30
// 

public class Format
{
    StringBuffer buf;
    
    Format(double abs, int n) {
        boolean b = false;
        abs = Math.round(abs * Math.pow(10.0, n)) / Math.pow(10.0, n);
        if (abs < 0.0) {
            b = true;
            abs = Math.abs(abs);
        }
        final long n2 = (long)abs;
        final double n3 = abs - n2;
        this.buf = new StringBuffer();
        if (b) {
            this.buf.append('-');
        }
        this.buf.append(n2);
        if (n > 0) {
            this.buf.append('.');
            long n4 = (long)(n3 * Math.pow(10.0, n));
            while (--n >= 0) {
                final int n5 = (int)(n4 / (long)Math.pow(10.0, n));
                n4 -= n5 * (long)Math.pow(10.0, n);
                this.buf.append(n5);
            }
        }
    }
    
    public String toString() {
        return this.buf.toString();
    }
    
    public static String toString(final double n, final int n2) {
        return new Format(n, n2).toString();
    }
}
