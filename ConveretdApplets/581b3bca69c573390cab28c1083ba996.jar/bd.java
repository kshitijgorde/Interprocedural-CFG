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

class bd extends bc
{
    protected InputStream n;
    protected int o;
    protected int p;
    protected int q;
    protected float r;
    
    bd(final URL url, float a) {
        super(url);
        this.n = null;
        if (a > 0.0f) {
            a = ba.a(a, 0.0f, 114.59f);
        }
        this.r = a * 3.1415927f / 180.0f;
    }
    
    void a(final InputStream inputStream) {
        this.n = new d(super.f, inputStream);
    }
    
    protected void a() throws IOException, RuntimeException, InterruptedException, Exception {
        final int n = 958122090;
        final int n2 = 73959415;
        final long n3 = (n ^ n2) << 32 ^ ~n2;
        if (!this.b(super.h)) {
            super.f.a(-1, 0.0f);
            return;
        }
        ((d)this.n).a(this.q + this.o + this.p);
        this.a(this.n, new byte[this.o]);
        super.a(new c(this.n, n3));
        this.a(super.h, this.p);
    }
    
    protected void a(final o o, final Image image) throws InterruptedException {
        o.a(image);
        final Dimension b = o.b();
        final float[] array = new float[2];
        array[1] = (array[0] = b.width / 2.0f - 0.5f);
        o.a(array);
    }
    
    protected boolean b(final o o) throws IOException {
        if (this.c(this.n) != 25203) {
            return false;
        }
        this.q = this.c(this.n);
        final int d = this.d(this.n);
        final byte[] array = new byte[this.q - 8];
        this.a(this.n, array);
        if (a(array) != d) {
            return false;
        }
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            if (this.b(byteArrayInputStream) != 6) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            o.a("vFOV", new Float(this.c(byteArrayInputStream) * 0.017453292f));
            byteArrayInputStream.skip(2L);
            final byte b = this.b(byteArrayInputStream);
            if (b != -112 && (b > -117 || b < -125)) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            o.a("Radius", new Float(this.d(byteArrayInputStream)));
            this.o = this.d(byteArrayInputStream) * 4;
            this.o += this.d(byteArrayInputStream);
            this.o += this.d(byteArrayInputStream);
            this.p = this.d(byteArrayInputStream);
            if (this.p == 0) {
                return false;
            }
            byteArrayInputStream.skip(4L);
            final boolean b2 = this.b(byteArrayInputStream) != 0;
            final boolean b3 = this.b(byteArrayInputStream) != 0;
            if (b2) {
                o.a("Layout", "Sphere");
                o.a("hFOV", new Float(6.2831855f));
            }
            else if (b3) {
                o.a("Layout", "Mirrored");
                o.a("hFOV", new Float(6.2831855f));
            }
            else {
                o.a("Layout", "Hemisphere");
                o.a("hFOV", new Float(3.1415927f));
            }
            byteArrayInputStream.skip(334L);
            this.o += this.d(byteArrayInputStream);
            this.o += this.d(byteArrayInputStream);
            this.o += this.d(byteArrayInputStream);
            o.a("RefViewpoint", new float[] { this.e(byteArrayInputStream) * 0.017453292f, this.e(byteArrayInputStream) * -0.017453292f, this.e(byteArrayInputStream) * -0.017453292f, 0.0f });
            final float[] array2 = { this.e(byteArrayInputStream) * 0.017453292f, this.e(byteArrayInputStream) * -0.017453292f, 0.0f, 0.0f };
            byteArrayInputStream.skip(4L);
            array2[3] = this.e(byteArrayInputStream);
            if (this.r > 0.0f) {
                array2[3] = 1.0f / (1.28f * (float)Math.tan(this.r / 2.0f));
            }
            o.a("DefViewpoint", array2);
            this.o += this.d(byteArrayInputStream);
            this.o += this.d(byteArrayInputStream);
            byteArrayInputStream.skip(1L);
            final long n = this.f(byteArrayInputStream);
            if (n != 0L && new Date(new Date(97, 0, 1).getTime() + n * 24L * 60L * 60L * 1000L).before(new Date())) {
                return false;
            }
            o.a("ZoomMax", new Float(this.e(byteArrayInputStream)));
            o.a("ZoomMin", new Float(this.e(byteArrayInputStream)));
            byteArrayInputStream.skip(20L);
            final long n2 = this.f(byteArrayInputStream);
            if (n2 != 0L && new Date(new Date(97, 0, 1).getTime() + n2 * 24L * 60L * 60L * 1000L).after(new Date())) {
                return false;
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
    
    private final byte b(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    private final short c(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)(read + (read2 << 8));
    }
    
    private final int d(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        final int read4 = inputStream.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return read + (read2 << 8) + (read3 << 16) + (read4 << 24);
    }
    
    private final float e(final InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(this.d(inputStream));
    }
    
    private final int f(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        if ((read | read2 | read3) < 0) {
            throw new EOFException();
        }
        return (read & 0xFF) | (read2 & 0xFF) << 8 | (read3 & 0xFF) << 16;
    }
}
