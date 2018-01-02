// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.io.IOException;

public class ImRecord
{
    byte[] I;
    int arraycopy;
    int Z;
    ImIstream read;
    int C;
    int setCharAt;
    int setLength;
    
    ImRecord(final ImIstream read) {
        this.I = new byte[32768];
        this.read = read;
    }
    
    public final int I(final byte[] array, int n, int i) {
        final int n2 = i;
        int n3 = this.Z - this.arraycopy;
        if (n3 > 0) {
            if (n3 > i) {
                n3 = i;
            }
            System.arraycopy(this.I, this.arraycopy, array, n, n3);
            this.arraycopy = 0;
            this.Z = 0;
            this.setLength += n3;
            i -= n3;
            n += n3;
        }
        this.read.Z(this.setLength);
        while (i > 0) {
            try {
                final int read = this.read.read(array, n, i);
                if (read == -1) {
                    return -1;
                }
                i -= read;
                n += read;
                this.setLength += read;
                continue;
            }
            catch (IOException ex) {
                return -1;
            }
            break;
        }
        return n2;
    }
    
    final boolean I() {
        return this.setLength >= this.setCharAt;
    }
    
    final int Z() {
        return 0xFF000000 | this.F() << 16 | this.F() << 8 | this.F();
    }
    
    final int C() {
        final int d = this.D();
        int n;
        if ((d & 0x1) != 0x0) {
            n = -(d >> 1);
        }
        else {
            n = d >> 1;
        }
        return n;
    }
    
    final int B() {
        final int f = this.F();
        this.F();
        return f | this.I[this.arraycopy - 1] << 8;
    }
    
    final void I(final StringBuffer sb, final int length) {
        sb.setLength(length);
        for (int i = 0; i < length; ++i) {
            sb.setCharAt(i, (char)this.D());
        }
    }
    
    final int D() {
        int f = this.F();
        if (f >= 128) {
            f &= 0x7F;
            int i;
            do {
                i = this.F();
                f = (f << 7 | (i & 0x7F));
            } while (i >= 128);
        }
        return f;
    }
    
    final void arraycopy() {
        this.setLength += this.Z - this.arraycopy;
        int n = this.setCharAt - this.setLength;
        if (n > 0) {
            if (n > 32768) {
                n = 32768;
            }
            this.read.Z(this.setLength);
            this.arraycopy = 0;
            this.Z = 0;
            int z = 0;
            while (this.Z < n) {
                int read;
                try {
                    read = this.read.read(this.I, z, n);
                }
                catch (IOException ex) {
                    read = -1;
                }
                if (read <= 0) {
                    if (z > 0) {
                        break;
                    }
                    return;
                }
                else {
                    z += read;
                    n -= read;
                }
            }
            this.Z = z;
        }
    }
    
    public final int F() {
        if (this.setCharAt - this.setLength <= 0) {
            return -1;
        }
        if (this.arraycopy >= this.Z) {
            this.arraycopy();
        }
        ++this.setLength;
        return this.I[this.arraycopy++] & 0xFF;
    }
    
    final int I(final int n) {
        this.Z(n);
        return this.F();
    }
    
    public final int J() {
        return this.F() | this.F() << 8;
    }
    
    public final int S() {
        return this.J() | this.J() << 16;
    }
    
    public final void I(final int n, final int n2) {
        this.C = n;
        this.setLength = n;
        this.setCharAt = n + n2;
        this.arraycopy = 0;
        this.Z = 0;
    }
    
    public final int A() {
        return this.setLength - this.C;
    }
    
    public final void Z(final int arraycopy) {
        final int a = this.A();
        if (a == this.arraycopy && arraycopy < this.Z) {
            this.arraycopy = arraycopy;
            this.setLength = this.C + arraycopy;
            return;
        }
        int n = arraycopy - a;
        if (n < 0) {
            this.I(this.C, this.setCharAt - this.C);
            n = arraycopy;
        }
        this.C(n);
    }
    
    final int E() {
        return this.setCharAt - this.C;
    }
    
    final void C(int i) {
        while (i > 0) {
            this.F();
            --i;
        }
    }
    
    public final byte[] B(final int n) {
        final byte[] array = new byte[n];
        this.I(array, 0, n);
        return array;
    }
}
