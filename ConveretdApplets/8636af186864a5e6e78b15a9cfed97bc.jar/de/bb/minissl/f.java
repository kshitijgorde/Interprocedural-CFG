// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.io.IOException;
import java.security.MessageDigest;

abstract class f extends c
{
    protected static final byte[] h;
    protected static final byte[] i;
    protected static final byte[] j;
    private byte[] k;
    private byte[] l;
    protected boolean m;
    protected MessageDigest n;
    protected MessageDigest o;
    protected long p;
    protected long q;
    
    static {
        h = new byte[] { 83, 82, 86, 82 };
        i = new byte[] { 67, 76, 78, 84 };
        j = new byte[0];
    }
    
    protected f() throws IOException {
        try {
            this.k = new byte[5];
            super.M = new byte[4];
            this.l = new byte[5];
            super.J = 0;
            this.n = MessageDigest.getInstance("MD5");
            this.o = MessageDigest.getInstance("SHA");
            super.R = new byte[32];
            super.S = new byte[32];
        }
        catch (Exception ex) {
            throw new IOException("Ssl3: " + ex.toString());
        }
    }
    
    protected final synchronized byte[] Z(final MessageDigest messageDigest, final int n, final byte[] array, final long n2, final int n3, final byte[] array2) {
        messageDigest.update(array);
        for (int i = 0; i < 80 - 2 * n; ++i) {
            messageDigest.update((byte)54);
        }
        for (int j = 0; j < 8; ++j) {
            messageDigest.update((byte)(n2 >>> (7 - j) * 8 & 0xFFL));
        }
        messageDigest.update((byte)n3);
        messageDigest.update((byte)(array2.length >>> 8));
        messageDigest.update((byte)array2.length);
        messageDigest.update(array2);
        final byte[] digest = messageDigest.digest();
        messageDigest.update(array);
        for (int k = 0; k < 80 - 2 * n; ++k) {
            messageDigest.update((byte)92);
        }
        messageDigest.update(digest);
        return messageDigest.digest();
    }
    
    protected final byte[] U(final MessageDigest messageDigest, final int n, final byte[] array) {
        messageDigest.update(array);
        messageDigest.update(super.Q);
        for (int i = 0; i < n; ++i) {
            messageDigest.update((byte)54);
        }
        final byte[] digest = messageDigest.digest();
        messageDigest.update(super.Q);
        for (int j = 0; j < n; ++j) {
            messageDigest.update((byte)92);
        }
        messageDigest.update(digest);
        return messageDigest.digest();
    }
    
    protected void J() throws IOException {
        this.c(new byte[] { 1, 0 }, 21);
        super.J();
    }
    
    protected final void W(final boolean b, final int n) {
        final int n2 = (super.J < 4) ? 5 : 16;
        byte[] digest = new byte[n2];
        byte[] digest2 = new byte[n2];
        super.U = new byte[n];
        super.V = new byte[n];
        final byte[] x = this.X(super.Q, 2 * n + 2 * n2, super.S, super.R);
        System.arraycopy(x, 0, b ? super.U : super.V, 0, n);
        System.arraycopy(x, n, b ? super.V : super.U, 0, n);
        System.arraycopy(x, 2 * n, digest, 0, n2);
        System.arraycopy(x, 2 * n + n2, digest2, 0, n2);
        if (super.J < 4) {
            super.K.update(digest);
            super.K.update(super.R);
            super.K.update(super.S);
            digest = super.K.digest();
            super.K.update(digest2);
            super.K.update(super.S);
            super.K.update(super.R);
            digest2 = super.K.digest();
        }
        super.W = new byte[258];
        super.X = new byte[258];
        if (b) {
            de.bb.minissl.c.P(digest, super.W);
            de.bb.minissl.c.P(digest2, super.X);
            return;
        }
        de.bb.minissl.c.P(digest2, super.W);
        de.bb.minissl.c.P(digest, super.X);
    }
    
    protected final byte[] b(final int n) throws IOException {
        if (!this.a(super.M, 22)) {
            return null;
        }
        if (n != super.M[0]) {
            return null;
        }
        int n2 = (super.M[1] & 0xFF) << 16 | (super.M[2] & 0xFF) << 8 | (super.M[3] & 0xFF);
        if (this.k[1] == 2) {
            final byte[] array = new byte[--n2];
            array[0] = this.k[3];
            array[1] = this.k[4];
            for (int i = 2; i < n2; i += super.H.read(array, i, n2 - i)) {}
            this.n.reset();
            this.n.update(this.k, 2, 1);
            this.n.update(array);
            this.o.reset();
            this.o.update(this.k, 2, 1);
            this.o.update(array);
            array[0] = 2;
            return array;
        }
        final byte[] array2 = new byte[n2];
        if (!this.a(array2, 22)) {
            return null;
        }
        return array2;
    }
    
    protected final void Y(final byte[] array, final int n) throws IOException {
        final byte[] array2 = new byte[array.length + 4];
        array2[0] = (byte)n;
        array2[1] = (byte)(array.length >> 16);
        array2[2] = (byte)(array.length >> 8);
        array2[3] = (byte)array.length;
        System.arraycopy(array, 0, array2, 4, array.length);
        this.c(array2, 22);
    }
    
    protected final byte[] X(final byte[] array, final int n, final byte[] array2, final byte[] array3) {
        final byte[] array4 = new byte[n + 15 & 0xFFFFFFF0];
        for (int n2 = 0; n2 * 16 < n; ++n2) {
            for (int i = 0; i <= n2; ++i) {
                super.L.update((byte)(65 + n2));
            }
            super.L.update(array);
            super.L.update(array2);
            super.L.update(array3);
            super.K.update(array);
            super.K.update(super.L.digest());
            System.arraycopy(super.K.digest(), 0, array4, n2 * 16, 16);
        }
        final byte[] array5 = new byte[n];
        System.arraycopy(array4, 0, array5, 0, n);
        return array5;
    }
    
    protected final boolean a(final byte[] array, final int n) throws IOException {
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
            if (i < array.length) {
                this.K();
                if (this.k[0] == n) {
                    continue;
                }
                if (this.k[0] == 0) {
                    return false;
                }
                if (this.k[0] != 21) {
                    throw new IOException("wrong packet type: " + this.k[0]);
                }
                if (super.N[0] == 1 && super.N[1] == 0) {
                    this.J();
                    return false;
                }
                throw new IOException("alert: " + super.N[0] + ":" + super.N[1]);
            }
        }
        if (this.m && n == 22) {
            this.n.update(array);
            this.o.update(array);
        }
        return true;
    }
    
    protected final void c(final byte[] array, final int n) throws IOException {
        if (this.m && n == 22) {
            this.n.update(array);
            this.o.update(array);
        }
        int length = array.length;
        if (super.Z != null) {
            length += super.b;
        }
        this.l[0] = (byte)n;
        this.l[1] = 3;
        this.l[2] = 0;
        this.l[3] = (byte)(length >>> 8);
        this.l[4] = (byte)length;
        super.I.write(this.l);
        if (super.Z == null) {
            super.I.write(array);
            return;
        }
        final byte[] z = this.Z(super.Z, super.b, super.V, this.q++, n, array);
        de.bb.minissl.c.M(array, super.X);
        de.bb.minissl.c.M(z, super.X);
        super.I.write(array);
        super.I.write(z);
    }
    
    protected final int Q() throws IOException {
        if (!this.a(super.O, 23)) {
            return -1;
        }
        return super.O[0] & 0xFF;
    }
    
    protected final int G(final byte[] array) throws IOException {
        final int n = this.a(super.O, 23) ? (super.O[0] & 0xFF) : -1;
        if (n < 0) {
            return n;
        }
        int a = this.A();
        if (array.length - 1 < a) {
            a = array.length - 1;
        }
        final byte[] array2 = new byte[a];
        if (a > 0 && !this.a(array2, 23)) {
            return -1;
        }
        array[0] = (byte)n;
        System.arraycopy(array2, 0, array, 1, a);
        return a + 1;
    }
    
    protected final int K() throws IOException {
        if (!this.V(this.k)) {
            return 0;
        }
        final int n = (this.k[3] & 0xFF) << 8 | (this.k[4] & 0xFF);
        super.P = 0;
        if (this.k[1] == 3 && n > 0) {
            if (super.Y != null) {
                super.N = new byte[n - super.a];
                if (!this.V(super.N)) {
                    return 0;
                }
                if (!this.V(super.c)) {
                    return 0;
                }
                de.bb.minissl.c.M(super.N, super.W);
                de.bb.minissl.c.M(super.c, super.W);
                if (!de.bb.minissl.c.E(super.c, 0, this.Z(super.Y, super.a, super.U, this.p++, this.k[0], super.N), 0, super.a)) {
                    throw new IOException("MAC error");
                }
            }
            else {
                super.N = new byte[n];
                if (!this.V(super.N)) {
                    return 0;
                }
            }
            return 0;
        }
        if (this.k[2] != 1 || this.k[3] != 3) {
            throw new IOException("no SSL3 packet");
        }
        (super.N = new byte[4])[0] = 1;
        super.N[1] = 0;
        super.N[2] = (byte)(this.k[0] & 0x3F);
        super.N[3] = this.k[1];
        this.k[0] = 22;
        this.k[1] = 2;
        return 0;
    }
    
    private final boolean V(final byte[] array) throws IOException {
        int i = 0;
        if (super.d > 0) {
            array[i++] = super.d;
            super.d = 0;
        }
        while (i < array.length) {
            final int read = super.H.read(array, i, array.length - i);
            if (read < 0) {
                this.k[0] = 0;
                return false;
            }
            i += read;
        }
        return true;
    }
    
    protected final void F(final byte[] array) throws IOException {
        if (array.length < 16384) {
            this.c(array, 23);
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
            this.c(array2, 23);
        }
    }
}
