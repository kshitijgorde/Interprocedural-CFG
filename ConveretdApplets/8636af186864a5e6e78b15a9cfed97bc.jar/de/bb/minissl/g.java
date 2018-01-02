// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import java.security.MessageDigest;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

final class g extends h
{
    private byte[] r;
    
    protected void H(final InputStream h, final OutputStream i, final byte[] r, final byte d, final byte[] array) throws IOException {
        super.H = h;
        super.I = i;
        super.R = r;
        super.d = d;
        try {
            super.v = 0;
            super.w = 1;
            final MessageDigest messageDigest = null;
            super.Z = messageDigest;
            super.Y = messageDigest;
            super.J = this.d(this.h(4));
            if (super.T == null) {
                this.l(this.g(super.G), 2);
            }
            super.Z = super.K;
            super.b = 16;
            this.m(false);
            super.Y = super.Z;
            super.a = super.b;
            super.c = new byte[super.a];
            this.l(this.f(), 3);
            if (!de.bb.minissl.c.E(r, 0, this.h(5), 0, r.length)) {
                throw new IOException("corrupt challenge");
            }
            super.T = this.h(6);
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new IOException("client handshake");
        }
    }
    
    private final byte[] f() {
        final byte[] array = new byte[super.S.length];
        System.arraycopy(super.S, 0, array, 0, array.length);
        return array;
    }
    
    private final byte[] g(final Vector vector) throws IOException {
        final int n = 0;
        if (n >= vector.size()) {
            return null;
        }
        final byte[] l = de.bb.minissl.c.L(vector.elementAt(n), new int[] { 144, 144, 16, 16, 16, 16, 144, 131 }, 0);
        final byte[] i = de.bb.minissl.c.L(l, new int[] { 144, 130 }, (l[0] == 0) ? 1 : 0);
        final byte[] j = de.bb.minissl.c.L(l, new int[] { 144, 2, 130 }, (l[0] == 0) ? 1 : 0);
        super.Q = new byte[16];
        de.bb.minissl.a.A.nextBytes(super.Q);
        int n2 = 0;
        if (super.J < 4) {
            n2 = 11;
        }
        int length = i.length;
        if (i[0] == 0) {
            --length;
        }
        final byte[] array = new byte[length];
        de.bb.minissl.c.C(array);
        array[0] = 0;
        array[1] = 2;
        array[array.length - 17 - n2] = 0;
        System.arraycopy(super.Q, n2, array, array.length - (16 - n2), 16 - n2);
        final int length2 = array.length;
        final byte[] array2 = new byte[9 + n2 + length2];
        array2[0] = (byte)((super.J == 5) ? 1 : 2);
        array2[1] = 0;
        array2[2] = -128;
        array2[3] = (byte)(n2 >>> 8);
        array2[4] = (byte)n2;
        array2[5] = (byte)(length2 >>> 8);
        array2[6] = (byte)length2;
        array2[8] = (array2[7] = 0);
        System.arraycopy(super.Q, 0, array2, 9, n2);
        final byte[] o = de.bb.minissl.c.O(array, i, j);
        System.arraycopy(o, 0, array2, 9 + n2, o.length);
        return array2;
    }
    
    private final Vector e(final byte[] array) throws Exception {
        int i = 0;
        int n = (array[i] & 0xFF) << 16 | (array[i + 1] & 0xFF) << 8 | (array[i + 2] & 0xFF);
        n += 3;
        i += 3;
        final Vector<ByteArrayInputStream> vector = new Vector<ByteArrayInputStream>();
        while (i < n) {
            final int n2 = (array[i] & 0xFF) << 16 | (array[i + 1] & 0xFF) << 8 | (array[i + 2] & 0xFF);
            if (n2 == 0) {
                break;
            }
            i += 3;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(n2);
            byteArrayOutputStream.write(array, i, n2);
            vector.addElement(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            i += n2;
        }
        return vector;
    }
    
    private final int d(final byte[] array) throws Exception {
        int n = 0;
        if (array[n++] == 0) {
            super.T = null;
        }
        ++n;
        final byte b = array[n++];
        final byte b2 = array[n++];
        if (b != 0 || b2 != 2) {
            return 0;
        }
        final int n2 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        final int n3 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        final int n4 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        if (n2 > 0) {
            final byte[] array2 = new byte[n2];
            System.arraycopy(array, n, array2, 0, n2);
            (super.G = new Vector()).addElement(array2);
            n += n2;
        }
        int j;
        if (n3 > 0) {
            int n5 = Integer.MAX_VALUE;
            for (int i = n3 / 3, n6 = 0; i > 0; --i, ++n6) {
                final int n7 = (array[n] & 0xFF) << 16 | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF);
                if (n5 > n7) {
                    n5 = n7;
                }
                n += 3;
            }
            j = 7 - 2 * (n5 >>> 16);
        }
        else {
            j = super.J;
        }
        System.arraycopy(array, n, super.S = new byte[n4], 0, n4);
        return j;
    }
}
