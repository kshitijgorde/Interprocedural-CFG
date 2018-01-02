// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataInput;
import java.io.Serializable;

public final class dD implements Serializable
{
    public byte[] q;
    
    public final boolean q() {
        return this.q == null;
    }
    
    public final String toString() {
        if (this.q == null) {
            return "";
        }
        final int n;
        final char[] array = new char[n = (this.q.length + 1) / 2];
        char c = (char)(this.q[this.q.length - 1] + 48);
        for (int i = n - 1; i > 0; --i) {
            short n2 = this.q[i * 2 - 1];
            short n3 = this.q[i * 2 - 2];
            if (n2 < 0) {
                n2 += 256;
            }
            if (n3 < 0) {
                n3 += 256;
            }
            array[i] = c;
            c = (char)((n2 + (n3 << 8)) / c);
        }
        array[0] = c;
        return new String(array);
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof String) {
            return o.toString().equals(this.toString());
        }
        if (o instanceof dD) {
            final byte[] q = ((dD)o).q;
            if (this.q == q) {
                return true;
            }
            if (this.q != null && q != null && q.length == this.q.length) {
                for (int i = 0; i < q.length; ++i) {
                    if (q[i] != this.q[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public final void q(final String s) {
        final byte[] q = new byte[s.length() / 2];
        for (int i = 0, n = 0; i < q.length; q[i++] = (byte)(Object)new Integer(Integer.parseInt(s.substring(n++, ++n), 16))) {}
        this.q = q;
    }
    
    public dD(final DataInput dataInput) {
        final int int1;
        if ((int1 = dataInput.readInt()) != 0) {
            dataInput.readFully(this.q = new byte[int1]);
            return;
        }
        this.q = null;
    }
    
    public dD(final String s) {
        final int n;
        if ((n = ((s != null) ? s.length() : 0)) == 0) {
            this.q = null;
            return;
        }
        this.q = new byte[n * 2 - 1];
        for (int i = 0; i < n - 1; ++i) {
            final char c = (char)((s.charAt(i) & '\u00ff') * (s.charAt(i + 1) & '\u00ff'));
            this.q[i * 2 + 1] = (byte)c;
            this.q[i * 2] = (byte)(c >> 8);
        }
        this.q[n * 2 - 2] = (byte)((s.charAt(n - 1) - '0') % 'K');
    }
    
    public dD() {
    }
}
