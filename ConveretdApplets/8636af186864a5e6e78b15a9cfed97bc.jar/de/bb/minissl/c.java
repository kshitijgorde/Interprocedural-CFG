// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.math.BigInteger;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Vector;
import java.io.OutputStream;
import java.io.InputStream;

abstract class c
{
    private InputStream E;
    private OutputStream F;
    protected Vector G;
    protected InputStream H;
    protected OutputStream I;
    protected int J;
    protected MessageDigest K;
    protected MessageDigest L;
    protected byte[] M;
    protected byte[] N;
    protected byte[] O;
    protected int P;
    protected byte[] Q;
    protected byte[] R;
    protected byte[] S;
    protected byte[] T;
    protected byte[] U;
    protected byte[] V;
    protected byte[] W;
    protected byte[] X;
    protected MessageDigest Y;
    protected MessageDigest Z;
    protected int a;
    protected int b;
    protected byte[] c;
    protected byte d;
    
    protected c() {
        this.N = new byte[0];
        this.O = new byte[1];
        this.P = 0;
        try {
            this.K = MessageDigest.getInstance("MD5");
            this.L = MessageDigest.getInstance("SHA");
        }
        catch (Exception ex) {}
    }
    
    protected final int A() throws IOException {
        if (this.P < this.N.length) {
            return this.N.length - this.P;
        }
        if (this.H.available() < this.M.length + this.a) {
            return 0;
        }
        this.K();
        return this.N.length - this.P;
    }
    
    protected void J() throws IOException {
        this.H.close();
        this.I.flush();
        this.I.close();
    }
    
    protected abstract void H(final InputStream p0, final OutputStream p1, final byte[] p2, final byte p3, final byte[] p4) throws IOException;
    
    protected static final byte[] O(final byte[] array, final byte[] array2, final byte[] array3) {
        final BigInteger bigInteger = new BigInteger(array2);
        final BigInteger bigInteger2 = new BigInteger(array3);
        final byte[] array4 = new byte[array.length + 1];
        System.arraycopy(array, 0, array4, 1, array.length);
        final byte[] byteArray = new BigInteger(array4).modPow(bigInteger2, bigInteger).toByteArray();
        final byte[] array5 = new byte[array.length];
        if (byteArray.length > array5.length) {
            System.arraycopy(byteArray, byteArray.length - array5.length, array5, 0, array5.length);
        }
        else {
            System.arraycopy(byteArray, 0, array5, array5.length - byteArray.length, byteArray.length);
        }
        return array5;
    }
    
    protected static final boolean E(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) {
        for (int i = 0; i < n3; ++i) {
            if (array[i + n] != array2[i + n2]) {
                return false;
            }
        }
        return true;
    }
    
    protected final void N() throws IOException {
        this.I.flush();
    }
    
    public final Vector getCertificates() {
        return this.G;
    }
    
    public final int getCipherType() {
        return this.J;
    }
    
    protected final InputStream I() {
        if (this.E == null) {
            this.E = new d(this);
        }
        return this.E;
    }
    
    protected final OutputStream D() {
        if (this.F == null) {
            this.F = new b(this);
        }
        return this.F;
    }
    
    static final byte[] L(final byte[] array, final int[] array2, int n) {
        if (array == null) {
            return null;
        }
        int n2 = 0;
        int length = array.length;
        try {
            int i = 0;
            while (i < array2.length) {
                final int n3 = n;
                final byte b = (byte)(array[n++] & 0x1F);
                n2 = array[n++];
                if (n2 < 0) {
                    int n4 = n2 & 0x7F;
                    n2 = 0;
                    while (n4-- > 0) {
                        n2 = (n2 << 8 | (array[n++] & 0xFF));
                    }
                    if (n2 == 0) {
                        n2 = length - n;
                    }
                }
                if (array2[i] == b) {
                    if (++i == array2.length) {
                        n2 += n - n3;
                        n = n3;
                    }
                }
                else if ((0x7F & array2[i]) == b) {
                    ++i;
                    length = n + n2;
                    continue;
                }
                if (i < array2.length) {
                    n += n2;
                }
            }
            final byte[] array3 = new byte[n2];
            System.arraycopy(array, n, array3, 0, n2);
            return array3;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    protected static final void P(final byte[] array, final byte[] array2) {
        for (int i = 0; i < 256; ++i) {
            array2[i] = (byte)i;
        }
        array2[257] = (array2[256] = 0);
        int n = 0;
        int n2 = 0;
        for (int j = 0; j < 256; ++j) {
            n2 = (array[n] + array2[j] + n2 & 0xFF);
            final byte b = array2[j];
            array2[j] = array2[n2];
            array2[n2] = b;
            n = (n + 1) % array.length;
        }
    }
    
    protected static final void M(final byte[] array, final byte[] array2) {
        int n = array2[256] & 0xFF;
        int n2 = array2[257] & 0xFF;
        for (int i = 0; i < array.length; ++i) {
            n = (n + 1 & 0xFF);
            n2 = (array2[n] + n2 & 0xFF);
            final byte b = array2[n];
            array2[n] = array2[n2];
            array2[n2] = b;
            final int n3 = i;
            array[n3] ^= array2[array2[n] + array2[n2] & 0xFF];
        }
        array2[256] = (byte)n;
        array2[257] = (byte)n2;
    }
    
    protected abstract int Q() throws IOException;
    
    protected abstract int G(final byte[] p0) throws IOException;
    
    protected abstract int K() throws IOException;
    
    protected void B(final InputStream h, final OutputStream i) {
        this.H = h;
        this.I = i;
    }
    
    protected static final void C(final byte[] array) {
        a.A.nextBytes(array);
        for (int i = 0; i < array.length; ++i) {
            while (array[i] == 0) {
                array[i] = (byte)a.A.nextInt();
            }
        }
    }
    
    protected abstract void F(final byte[] p0) throws IOException;
}
