// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.security.MessageDigest;
import java.io.IOException;

abstract class h extends c
{
    private byte s;
    private int t;
    private int u;
    protected int v;
    protected int w;
    
    protected h() throws IOException {
        try {
            super.M = new byte[3];
            super.J = 8388607;
            super.S = new byte[16];
            super.R = new byte[16];
        }
        catch (Exception ex) {
            throw new IOException("Ssl2: " + ex.toString());
        }
    }
    
    protected final synchronized byte[] j(final MessageDigest messageDigest, final byte[] array, final int n, final byte[] array2) {
        messageDigest.update(array);
        messageDigest.update(array2);
        for (int i = 0; i < 4; ++i) {
            messageDigest.update((byte)(n >>> (3 - i) * 8 & 0xFF));
        }
        return messageDigest.digest();
    }
    
    protected final void m(final boolean b) {
        super.K.reset();
        super.K.update(super.Q);
        super.K.update((byte)48);
        super.K.update(super.R);
        super.K.update(super.S);
        final byte[] digest = super.K.digest();
        super.K.reset();
        super.K.update(super.Q);
        super.K.update((byte)49);
        super.K.update(super.R);
        super.K.update(super.S);
        final byte[] digest2 = super.K.digest();
        super.W = new byte[258];
        super.X = new byte[258];
        if (b) {
            super.U = digest2;
            super.V = digest;
            de.bb.minissl.c.P(digest2, super.W);
            de.bb.minissl.c.P(digest, super.X);
            return;
        }
        super.U = digest;
        super.V = digest2;
        de.bb.minissl.c.P(digest, super.W);
        de.bb.minissl.c.P(digest2, super.X);
    }
    
    protected final byte[] h(final int n) throws IOException {
        this.i(super.O);
        if ((super.O[0] & 0xFF) != n) {
            return null;
        }
        final byte[] array = new byte[this.t - 1];
        this.i(array);
        return array;
    }
    
    protected final void l(final byte[] array, final int n) throws IOException {
        final byte[] array2 = new byte[array.length + 1];
        array2[0] = (byte)n;
        System.arraycopy(array, 0, array2, 1, array.length);
        this.k(array2, 22);
    }
    
    protected final int i(final byte[] array) throws IOException {
        int i = 0;
        while (i < array.length) {
            if (super.P < super.N.length) {
                int length = array.length;
                if (length > super.N.length - super.P) {
                    length = super.N.length - super.P;
                }
                System.arraycopy(super.N, super.P, array, i, length);
                i += length;
                super.P += length;
            }
            if (i < array.length && this.K() == 0) {
                return i;
            }
        }
        return array.length;
    }
    
    protected final void k(final byte[] array, final int n) throws IOException {
        int length = array.length;
        if (super.Z != null) {
            length += super.b;
        }
        super.I.write(length >>> 8 | 0x80);
        super.I.write((byte)length);
        if (super.Z == null) {
            super.I.write(array);
            ++this.w;
            return;
        }
        final byte[] j = this.j(super.Z, super.V, this.w++, array);
        de.bb.minissl.c.M(j, super.X);
        de.bb.minissl.c.M(array, super.X);
        super.I.write(j);
        super.I.write(array);
    }
    
    protected final int Q() throws IOException {
        if (this.i(super.O) == 0) {
            return -1;
        }
        return super.O[0] & 0xFF;
    }
    
    protected final int G(final byte[] array) throws IOException {
        final int n = (this.i(super.O) == 0) ? -1 : (super.O[0] & 0xFF);
        if (n <= 0) {
            return n;
        }
        int a = this.A();
        if (array.length - 1 < a) {
            a = array.length - 1;
        }
        final byte[] array2 = new byte[a];
        this.i(array2);
        array[0] = (byte)n;
        System.arraycopy(array2, 0, array, 1, a);
        return a + 1;
    }
    
    protected final int K() throws IOException {
        int i = 0;
        if (super.d < 0) {
            super.M[i++] = super.d;
            super.d = 0;
        }
        while (i < 2) {
            final int read = super.H.read(super.M, i, 2 - i);
            if (read < 0) {
                return 0;
            }
            i += read;
        }
        super.P = 0;
        if ((super.M[0] & 0xFF) >= 128) {
            this.t = ((super.M[0] & 0x7F) << 8 | (super.M[1] & 0xFF));
        }
        else {
            if (super.H.read(super.M, i, 1) < 0) {
                return 0;
            }
            this.t = ((super.M[0] & 0x3F) << 8 | (super.M[1] & 0xFF));
            this.u = (super.M[2] & 0xFF);
        }
        if (super.Y == null) {
            ++this.v;
            super.N = new byte[this.t + this.u];
            int read2;
            for (int j = 0; j < super.N.length; j += read2) {
                read2 = super.H.read(super.N, j, super.N.length - j);
                if (read2 < 0) {
                    return 0;
                }
            }
            return super.N.length;
        }
        int read3;
        for (int k = 0; k < super.a; k += read3) {
            read3 = super.H.read(super.c, k, super.a - k);
            if (read3 < 0) {
                return 0;
            }
        }
        this.t -= super.a;
        super.N = new byte[this.t];
        int read4;
        for (int l = 0; l < super.N.length; l += read4) {
            read4 = super.H.read(super.N, l, super.N.length - l);
            if (read4 < 0) {
                return 0;
            }
        }
        de.bb.minissl.c.M(super.c, super.W);
        de.bb.minissl.c.M(super.N, super.W);
        if (!de.bb.minissl.c.E(super.c, 0, this.j(super.Y, super.U, this.v++, super.N), 0, super.a)) {
            throw new IOException("MAC error");
        }
        return super.N.length;
    }
    
    protected final void F(final byte[] array) throws IOException {
        if (array.length < 16384) {
            this.k(array, 23);
            return;
        }
        byte[] array2 = new byte[16383];
        int n;
        for (int i = 0; i != array.length; i += n) {
            n = array2.length;
            if (i + n > array.length) {
                array2 = new byte[array.length - i];
                n = array.length;
            }
            System.arraycopy(array, i, array2, 0, n);
            this.k(array2, 23);
        }
    }
}
