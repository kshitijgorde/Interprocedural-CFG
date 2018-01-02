// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

import java.io.DataInput;
import java.io.IOException;
import java.io.DataOutput;

public class x
{
    private byte[] a;
    
    public void a(final DataOutput dataOutput) throws IOException {
        if (this.a == null) {
            dataOutput.writeInt(0);
        }
        else {
            dataOutput.writeInt(this.a.length);
            dataOutput.write(this.a);
        }
    }
    
    public boolean a() {
        return this.a == null;
    }
    
    public String toString() {
        if (this.a == null) {
            return "";
        }
        final int n = (this.a.length + 1) / 2;
        final char[] array = new char[n];
        char c = (char)(this.a[this.a.length - 1] + 48);
        for (int i = n - 1; i > 0; --i) {
            short n2 = this.a[2 * i - 1];
            short n3 = this.a[2 * i - 2];
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
    
    public boolean equals(final Object o) {
        if (o instanceof String) {
            return o.toString().equals(this.toString());
        }
        if (o instanceof x) {
            final byte[] b = ((x)o).b();
            if (this.a == b) {
                return true;
            }
            if (this.a != null && b != null && b.length == this.a.length) {
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] != this.a[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public byte[] b() {
        return this.a;
    }
    
    public x(final DataInput dataInput) throws IOException {
        final int int1 = dataInput.readInt();
        if (int1 != 0) {
            dataInput.readFully(this.a = new byte[int1]);
        }
        else {
            this.a = null;
        }
    }
    
    public x(final String s) {
        final int n = (s == null) ? 0 : s.length();
        if (n == 0) {
            this.a = null;
        }
        else {
            this.a = new byte[2 * n - 1];
            for (int i = 0; i < n - 1; ++i) {
                final char c = (char)((s.charAt(i) & '\u00ff') * (s.charAt(i + 1) & '\u00ff'));
                this.a[2 * i + 1] = (byte)(c & '\u00ff');
                this.a[2 * i] = (byte)(c >> 8 & '\u00ff');
            }
            this.a[2 * n - 2] = (byte)((s.charAt(n - 1) - '0') % 'K');
        }
    }
    
    public x() {
    }
}
