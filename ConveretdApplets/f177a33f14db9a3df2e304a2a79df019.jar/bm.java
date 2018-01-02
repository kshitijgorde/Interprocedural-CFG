import java.io.EOFException;
import java.util.Date;
import java.io.ByteArrayInputStream;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class bm extends bl
{
    protected InputStream n;
    protected int o;
    protected int p;
    protected int q;
    protected float r;
    
    private static String b(final int n) {
        return new String() + (char)((n & 0xFF000000) >> 24) + (char)((n & 0xFF0000) >> 16) + (char)((n & 0xFF00) >> 8) + (char)(n & 0xFF);
    }
    
    bm(final URL url, float a) {
        super(url);
        this.n = null;
        if (a > 0.0f) {
            a = bj.a(a, 0.0f, 114.59f);
        }
        this.r = a * 3.1415927f / 180.0f;
    }
    
    void a(final InputStream inputStream) {
        this.n = new d(this.f, inputStream);
    }
    
    protected void a() throws IOException, RuntimeException, InterruptedException, Exception {
        final int n = 958122090;
        final int n2 = 73959415;
        final long n3 = (n ^ n2) << 32 ^ ~n2;
        if (!this.b(this.h)) {
            this.f.a(-1, 0.0f);
            return;
        }
        ((d)this.n).a(this.q + this.o + this.p);
        this.a(this.n, new byte[this.o]);
        super.a(new c(this.n, n3));
        this.a(this.h, this.p);
    }
    
    protected void a(final q q, final Image image) throws InterruptedException {
        q.a(image);
        final Dimension b = q.b();
        final float[] array = new float[2];
        array[1] = (array[0] = b.width / 2.0f - 0.5f);
        q.a(array);
    }
    
    protected boolean b(final q q) throws IOException {
        if (g.b(this.n) != 25203) {
            return false;
        }
        this.q = g.b(this.n);
        this.q &= 0xFFFF;
        final int c = g.c(this.n);
        final byte[] array = new byte[this.q - 8];
        this.a(this.n, array);
        if (a(array) != c) {
            return false;
        }
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            if (g.a(byteArrayInputStream) != 6) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            q.a("vfov", new Float(g.b(byteArrayInputStream) * 0.017453292f));
            byteArrayInputStream.skip(2L);
            final byte a = g.a(byteArrayInputStream);
            if (a != -112 && (a > -117 || a < -125)) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            final Float n = new Float(g.c(byteArrayInputStream));
            q.a("irad", n);
            this.o = g.c(byteArrayInputStream) * 4;
            this.o += g.c(byteArrayInputStream);
            this.o += g.c(byteArrayInputStream);
            this.p = g.c(byteArrayInputStream);
            if (this.p == 0) {
                return false;
            }
            byteArrayInputStream.skip(4L);
            final boolean b = g.a(byteArrayInputStream) != 0;
            final boolean b2 = g.a(byteArrayInputStream) != 0;
            q.a("mirr", b2 ? Boolean.TRUE : Boolean.FALSE);
            if (b) {
                q.a("frmt", "SPHERE");
            }
            else if (b2) {
                q.a("frmt", "MIRRORED");
            }
            else {
                q.a("frmt", "HEMISPHERE");
                q.a("hfov", new Float(3.1415927f));
            }
            byteArrayInputStream.skip(334L);
            this.o += g.c(byteArrayInputStream);
            this.o += g.c(byteArrayInputStream);
            this.o += g.c(byteArrayInputStream);
            q.a("rfvp", new float[] { g.d(byteArrayInputStream) * 0.017453292f, g.d(byteArrayInputStream) * -0.017453292f, g.d(byteArrayInputStream) * -0.017453292f, 0.0f });
            final float[] array2 = { g.d(byteArrayInputStream) * 0.017453292f, g.d(byteArrayInputStream) * -0.017453292f, 0.0f, 0.0f };
            byteArrayInputStream.skip(4L);
            array2[3] = g.d(byteArrayInputStream);
            if (this.r > 0.0f) {
                array2[3] = 1.0f / (1.28f * (float)Math.tan(this.r / 2.0f));
            }
            q.a("invp", array2);
            this.o += g.c(byteArrayInputStream);
            this.o += g.c(byteArrayInputStream);
            byteArrayInputStream.skip(1L);
            final long n2 = g.e(byteArrayInputStream);
            if (n2 != 0L && new Date(new Date(97, 0, 1).getTime() + n2 * 24L * 60L * 60L * 1000L).before(new Date())) {
                return false;
            }
            q.a("zmax", new Float(g.d(byteArrayInputStream)));
            q.a("zmin", new Float(g.d(byteArrayInputStream)));
            q.a("glng", new Float(g.d(byteArrayInputStream)));
            q.a("glat", new Float(g.d(byteArrayInputStream)));
            byteArrayInputStream.skip(12L);
            final long n3 = g.e(byteArrayInputStream);
            if (n3 != 0L && new Date(new Date(97, 0, 1).getTime() + n3 * 24L * 60L * 60L * 1000L).after(new Date())) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            if (g.c(byteArrayInputStream) > 0) {
                final x x = new x();
                x.a(byteArrayInputStream);
                for (int i = 0; i < x.c(); ++i) {
                    final x a2 = x.a(i);
                    if (a2.a() == s.i) {
                        final int c2 = a2.c();
                        if (c2 > 0) {
                            final s[] array3 = new s[c2];
                            for (int j = 0; j < c2; ++j) {
                                (array3[j] = new s()).a(a2.a(j));
                                array3[j].a(n);
                            }
                            q.a("spts", array3);
                        }
                    }
                    else {
                        switch (a2.b()) {
                            case 1:
                            case 2: {
                                q.a(b(a2.a()), new Integer(a2.e()));
                                break;
                            }
                            case 3:
                            case 4: {
                                q.a(b(a2.a()), new Integer(a2.f()));
                                break;
                            }
                            case 5:
                            case 6: {
                                q.a(b(a2.a()), new Integer(a2.g()));
                                break;
                            }
                            case 7:
                            case 8: {
                                q.a(b(a2.a()), new Long(a2.h()));
                                break;
                            }
                            case 9: {
                                q.a(b(a2.a()), new Float(a2.i()));
                                break;
                            }
                            case 10: {
                                q.a(b(a2.a()), new Double(a2.j()));
                                break;
                            }
                            case 13: {
                                q.a(b(a2.a()), a2.k());
                                break;
                            }
                        }
                    }
                }
            }
        }
        catch (EOFException ex) {}
        return true;
    }
    
    static final int a(final byte[] array) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            byte b = array[i];
            for (int j = 0; j < 9; ++j) {
                final boolean b2 = ((n & 0x1) ^ (b & 0x1)) != 0x0;
                n >>>= 1;
                if (b2) {
                    n ^= 0xA001;
                }
                b = (byte)(b >> 1 & 0x7F);
            }
        }
        return n;
    }
    
    private final void a(final InputStream inputStream, final byte[] array) throws IOException {
        int read;
        for (int i = 0; i < array.length; i += read) {
            read = inputStream.read(array, i, array.length - i);
            if (read < 0) {
                throw new EOFException();
            }
        }
    }
}
