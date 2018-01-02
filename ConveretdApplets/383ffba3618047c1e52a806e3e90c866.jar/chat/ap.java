// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.DataInput;

public final class ap
{
    public byte[] a;
    
    public final boolean a() {
        return this.a == null;
    }
    
    public final String toString() {
        if (this.a == null) {
            return "";
        }
        int i;
        final char[] array = new char[i = (this.a.length + 1) / 2];
        char c = (char)(this.a[this.a.length - 1] + 48);
        --i;
        while (i > 0) {
            short n = this.a[(i << 1) - 1];
            short n2 = this.a[(i << 1) - 2];
            if (n < 0) {
                n += 256;
            }
            if (n2 < 0) {
                n2 += 256;
            }
            array[i] = c;
            c = (char)((n + (n2 << 8)) / c);
            --i;
        }
        array[0] = c;
        return new String(array);
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof String) {
            return o.toString().equals(this.toString());
        }
        if (o instanceof ap) {
            final byte[] a = ((ap)o).a;
            if (this.a == a) {
                return true;
            }
            if (this.a != null && a != null && a.length == this.a.length) {
                for (int i = 0; i < a.length; ++i) {
                    if (a[i] != this.a[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public ap(final DataInput dataInput) {
        final int int1;
        if ((int1 = dataInput.readInt()) != 0) {
            dataInput.readFully(this.a = new byte[int1]);
            return;
        }
        this.a = null;
    }
    
    public ap(final String s) {
        final int n;
        if ((n = ((s != null) ? s.length() : 0)) == 0) {
            this.a = null;
            return;
        }
        this.a = new byte[(n << 1) - 1];
        for (int i = 0; i < n - 1; ++i) {
            final char c = (char)((s.charAt(i) & '\u00ff') * (s.charAt(i + 1) & '\u00ff'));
            this.a[(i << 1) + 1] = (byte)c;
            this.a[i << 1] = (byte)(c >> 8);
        }
        this.a[(n << 1) - 2] = (byte)((s.charAt(n - 1) - '0') % 'K');
    }
    
    public ap() {
    }
}
