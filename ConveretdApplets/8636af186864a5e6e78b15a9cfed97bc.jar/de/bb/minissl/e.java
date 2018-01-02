// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.io.ByteArrayOutputStream;
import java.util.Vector;
import java.security.MessageDigest;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

final class e extends f
{
    private byte[] g;
    
    protected void H(final InputStream h, final OutputStream i, final byte[] array, final byte d, final byte[] array2) throws IOException {
        try {
            super.H = h;
            super.I = i;
            final MessageDigest messageDigest = null;
            super.Y = messageDigest;
            super.Z = messageDigest;
            super.P = super.N.length;
            super.m = true;
            boolean b = false;
            if (super.T == null) {
                super.n.reset();
                super.o.reset();
                b = true;
            }
            int n = super.R.length;
            if (array.length <= super.R.length) {
                n = array.length;
            }
            System.arraycopy(array, 0, super.R, super.R.length - n, n);
            super.d = d;
            super.n.update((byte)1);
            super.n.update(array2, 3, array2.length - 3);
            super.o.update((byte)1);
            super.o.update(array2, 3, array2.length - 3);
            super.q = 0L;
            super.J = this.R(this.b(2));
            if (b) {
                super.G = this.T(this.b(11));
            }
            this.b(14);
            if (b) {
                this.Y(this.S(super.G), 16);
            }
            final byte[] array3 = { 1 };
            this.c(array3, 20);
            if (super.J < 5) {
                super.Z = super.K;
                super.b = 16;
            }
            else {
                super.Z = super.L;
                super.b = 20;
            }
            super.Q = this.X(this.g, 48, super.R, super.S);
            this.W(false, super.b);
            final byte[] u = this.U((MessageDigest)super.n.clone(), 48, f.i);
            final byte[] u2 = this.U((MessageDigest)super.o.clone(), 40, f.i);
            final byte[] array4 = new byte[40];
            array4[0] = 20;
            array4[2] = (array4[1] = 0);
            array4[3] = 36;
            System.arraycopy(u, 0, array4, 4, 16);
            System.arraycopy(u2, 0, array4, 20, 20);
            this.c(array4, 22);
            final byte[] u3 = this.U((MessageDigest)super.n.clone(), 48, f.h);
            final byte[] u4 = this.U((MessageDigest)super.o.clone(), 40, f.h);
            super.m = false;
            this.a(array3, 20);
            super.Y = super.Z;
            super.a = super.b;
            super.p = 0L;
            super.c = new byte[super.a];
            this.a(array4, 22);
            if (!de.bb.minissl.c.E(array4, 4, u3, 0, 16) || !de.bb.minissl.c.E(array4, 20, u4, 0, 20)) {
                throw new IOException("finished: MAC error");
            }
        }
        catch (IOException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new IOException("client handshake");
        }
    }
    
    private final byte[] S(final Vector vector) throws IOException {
        final int n = 0;
        if (n >= vector.size()) {
            return null;
        }
        final byte[] l = de.bb.minissl.c.L(vector.elementAt(n), new int[] { 144, 144, 16, 16, 16, 16, 144, 131 }, 0);
        final byte[] i = de.bb.minissl.c.L(l, new int[] { 144, 130 }, (l[0] == 0) ? 1 : 0);
        final byte[] j = de.bb.minissl.c.L(l, new int[] { 144, 2, 130 }, (l[0] == 0) ? 1 : 0);
        this.g = new byte[48];
        de.bb.minissl.a.A.nextBytes(this.g);
        this.g[0] = 3;
        this.g[1] = 0;
        int length = i.length;
        if (i[0] == 0) {
            --length;
        }
        final byte[] array = new byte[length];
        de.bb.minissl.c.C(array);
        array[0] = 0;
        array[1] = 2;
        array[array.length - 49] = 0;
        System.arraycopy(this.g, 0, array, array.length - 48, 48);
        final byte[] o = de.bb.minissl.c.O(array, i, j);
        final int n2 = array.length - o.length;
        if (n2 >= 0) {
            for (int k = 0; k < n2; ++k) {
                array[k] = 0;
            }
            System.arraycopy(o, 0, array, n2, o.length);
        }
        else {
            System.arraycopy(o, -n2, array, 0, array.length);
        }
        return array;
    }
    
    private final Vector T(final byte[] array) throws Exception {
        int i = 0;
        int n = (array[i] & 0xFF) << 16 | (array[i + 1] & 0xFF) << 8 | (array[i + 2] & 0xFF);
        n += 3;
        i += 3;
        final Vector<byte[]> vector = new Vector<byte[]>();
        while (i < n) {
            final int n2 = (array[i] & 0xFF) << 16 | (array[i + 1] & 0xFF) << 8 | (array[i + 2] & 0xFF);
            if (n2 == 0) {
                break;
            }
            i += 3;
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(n2);
            byteArrayOutputStream.write(array, i, n2);
            vector.addElement(byteArrayOutputStream.toByteArray());
            i += n2;
        }
        return vector;
    }
    
    private final int R(final byte[] array) throws IOException {
        int n = 0;
        final byte b = array[n++];
        final byte b2 = array[n++];
        if (b != 3 || b2 != 0) {
            return 0;
        }
        System.arraycopy(array, n, super.S, 0, 32);
        n += 32;
        final int n2 = array[n++] & 0xFF;
        System.arraycopy(array, n, super.T = new byte[n2], 0, n2);
        final int n3 = n + n2;
        final int n4 = (array[n3] & 0xFF) << 8 | (array[n3 + 1] & 0xFF);
        if (array[n3 + 2] != 0) {
            return 0;
        }
        return n4;
    }
}
