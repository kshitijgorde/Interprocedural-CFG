// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.B.A;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

public class A
{
    private final File U;
    private final byte[] V;
    private final InputStream W;
    private C S;
    private static final byte _ = -38;
    private static final byte T = -39;
    public static final byte R = -32;
    public static final byte Q = -31;
    public static final byte P = -30;
    public static final byte O = -29;
    public static final byte M = -28;
    public static final byte L = -27;
    public static final byte K = -26;
    public static final byte I = -25;
    public static final byte G = -24;
    public static final byte F = -23;
    public static final byte C = -22;
    public static final byte B = -21;
    public static final byte A = -20;
    public static final byte Z = -19;
    public static final byte Y = -18;
    public static final byte X = -17;
    public static final byte E = -40;
    public static final byte N = -37;
    public static final byte J = -60;
    public static final byte D = -64;
    public static final byte H = -2;
    
    public A(final File u) throws B {
        this.U = u;
        this.V = null;
        this.W = null;
        this.C();
    }
    
    public A(final byte[] v) throws B {
        this.U = null;
        this.V = v;
        this.W = null;
        this.C();
    }
    
    public A(final InputStream w) throws B {
        this.W = w;
        this.U = null;
        this.V = null;
        this.C();
    }
    
    public A(final C s) {
        this.U = null;
        this.V = null;
        this.W = null;
        this.S = s;
    }
    
    public byte[] A(final byte b) throws B {
        return this.A(b, 0);
    }
    
    public byte[] A(final byte b, final int n) {
        return this.S.A(b, n);
    }
    
    public final int B(final byte b) {
        return this.S.D(b);
    }
    
    public final C B() {
        return this.S;
    }
    
    private void C() throws B {
        this.S = new C();
        final BufferedInputStream a = this.A();
        try {
            int n = 0;
            if (!this.A(a)) {
                throw new B("not a jpeg file");
            }
            n += 2;
            while (true) {
                final byte b = (byte)(a.read() & 0xFF);
                if ((b & 0xFF) != 0xFF) {
                    throw new B("expected jpeg segment start identifier 0xFF at offset " + n + ", not 0x" + Integer.toHexString(b & 0xFF));
                }
                ++n;
                final byte b2 = (byte)(a.read() & 0xFF);
                ++n;
                final byte[] array = new byte[2];
                a.read(array, 0, 2);
                n += 2;
                int n2 = (array[0] << 8 & 0xFF00) | (array[1] & 0xFF);
                n2 -= 2;
                if (n2 > a.available()) {
                    throw new B("segment size would extend beyond file stream length");
                }
                if (n2 < 0) {
                    throw new B("segment size would be less than zero");
                }
                final byte[] array2 = new byte[n2];
                a.read(array2, 0, n2);
                n += n2;
                if ((b2 & 0xFF) == 0xDA) {
                    return;
                }
                if ((b2 & 0xFF) == 0xD9) {
                    return;
                }
                this.S.A(b2, array2);
            }
        }
        catch (IOException ex) {
            throw new B("IOException processing Jpeg file: " + ex.getMessage(), ex);
        }
        finally {
            try {
                if (a != null) {
                    a.close();
                }
            }
            catch (IOException ex2) {
                throw new B("IOException processing Jpeg file: " + ex2.getMessage(), ex2);
            }
        }
    }
    
    private BufferedInputStream A() throws B {
        if (this.W == null) {
            if (this.V == null) {
                try {
                    final InputStream inputStream = new FileInputStream(this.U);
                    return new BufferedInputStream(inputStream);
                }
                catch (FileNotFoundException ex) {
                    throw new B("Jpeg file does not exist", ex);
                }
            }
            final InputStream inputStream = new ByteArrayInputStream(this.V);
            return new BufferedInputStream(inputStream);
        }
        if (this.W instanceof BufferedInputStream) {
            return (BufferedInputStream)this.W;
        }
        return new BufferedInputStream(this.W);
    }
    
    private boolean A(final InputStream inputStream) throws IOException {
        final byte[] array = new byte[2];
        inputStream.read(array, 0, 2);
        return (array[0] & 0xFF) == 0xFF && (array[1] & 0xFF) == 0xD8;
    }
}
