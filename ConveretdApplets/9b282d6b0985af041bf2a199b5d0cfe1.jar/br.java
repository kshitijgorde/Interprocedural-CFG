import java.util.Date;
import java.io.ByteArrayInputStream;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class br extends bq
{
    protected InputStream i;
    protected int j;
    protected int k;
    protected int l;
    protected float m;
    
    private final byte b(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read < 0) {
            throw new EOFException();
        }
        return (byte)read;
    }
    
    private final float c(final InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(this.f(inputStream));
    }
    
    private final int d(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        if ((read | read2 | read3) < 0) {
            throw new EOFException();
        }
        return (read & 0xFF) | (read2 & 0xFF) << 8 | (read3 & 0xFF) << 16;
    }
    
    br(final URL url, float b) {
        super(url);
        this.i = null;
        if (b > 0.0f) {
            b = bn.b(b, 0.0f, 114.59f);
        }
        this.m = b * 3.1415927f / 180.0f;
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
    
    void a(final InputStream inputStream) {
        this.i = new e(this, inputStream);
    }
    
    private final short e(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        if ((read | read2) < 0) {
            throw new EOFException();
        }
        return (short)(read + (read2 << 8));
    }
    
    static final int a(final byte[] array) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            byte b = array[i];
            int n2 = 0;
            do {
                final boolean b2 = ((n & 0x1) ^ (b & 0x1)) != 0x0;
                n >>>= 1;
                if (b2) {
                    n ^= 0xA001;
                }
                b = (byte)(b >> 1 & 0x7F);
            } while (++n2 < 9);
        }
        return n;
    }
    
    protected void a(final w w, final Image image) throws InterruptedException {
        w.a(image);
        final Dimension c = w.c();
        final float[] array = new float[2];
        array[1] = (array[0] = c.width / 2.0f - 0.5f);
        w.a(array);
    }
    
    protected boolean b(final w w) throws IOException {
        if (this.e(this.i) != 25203) {
            return false;
        }
        this.l = this.e(this.i);
        final int f = this.f(this.i);
        final byte[] array = new byte[this.l - 8];
        this.a(this.i, array);
        if (a(array) != f) {
            return false;
        }
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            if (this.b(byteArrayInputStream) != 6) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            w.a("vFOV", new Float(this.e(byteArrayInputStream) * 0.017453292f));
            byteArrayInputStream.skip(2L);
            final byte b = this.b(byteArrayInputStream);
            if (b != -112 && (b > -117 || b < -125)) {
                return false;
            }
            byteArrayInputStream.skip(1L);
            w.a("Radius", new Float(this.f(byteArrayInputStream)));
            this.j = this.f(byteArrayInputStream) * 4;
            this.j += this.f(byteArrayInputStream);
            this.j += this.f(byteArrayInputStream);
            this.k = this.f(byteArrayInputStream);
            if (this.k == 0) {
                return false;
            }
            byteArrayInputStream.skip(4L);
            final boolean b2 = this.b(byteArrayInputStream) != 0;
            final boolean b3 = this.b(byteArrayInputStream) != 0;
            if (b2) {
                w.a("Layout", "Sphere");
                w.a("hFOV", new Float(6.2831855f));
            }
            else if (b3) {
                w.a("Layout", "Mirrored");
                w.a("hFOV", new Float(6.2831855f));
            }
            else {
                w.a("Layout", "Hemisphere");
                w.a("hFOV", new Float(3.1415927f));
            }
            byteArrayInputStream.skip(334L);
            this.j += this.f(byteArrayInputStream);
            this.j += this.f(byteArrayInputStream);
            this.j += this.f(byteArrayInputStream);
            w.a("RefViewpoint", new float[] { this.c(byteArrayInputStream) * 0.017453292f, this.c(byteArrayInputStream) * -0.017453292f, this.c(byteArrayInputStream) * -0.017453292f, 0.0f });
            final float[] array2 = { this.c(byteArrayInputStream) * 0.017453292f, this.c(byteArrayInputStream) * -0.017453292f, 0.0f, 0.0f };
            byteArrayInputStream.skip(4L);
            array2[3] = this.c(byteArrayInputStream);
            if (this.m > 0.0f) {
                array2[3] = 1.0f / (1.28f * (float)Math.tan(this.m / 2.0f));
            }
            w.a("DefViewpoint", array2);
            this.j += this.f(byteArrayInputStream);
            this.j += this.f(byteArrayInputStream);
            byteArrayInputStream.skip(1L);
            final long n = this.d(byteArrayInputStream);
            if (n != 0L && new Date(new Date(97, 0, 1).getTime() + n * 24L * 60L * 60L * 1000L).before(new Date())) {
                return false;
            }
            w.a("ZoomMax", new Float(this.c(byteArrayInputStream)));
            w.a("ZoomMin", new Float(this.c(byteArrayInputStream)));
            byteArrayInputStream.skip(20L);
            final long n2 = this.d(byteArrayInputStream);
            if (n2 != 0L && new Date(new Date(97, 0, 1).getTime() + n2 * 24L * 60L * 60L * 1000L).after(new Date())) {
                return false;
            }
        }
        catch (EOFException ex) {}
        return true;
    }
    
    private final int f(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        final int read2 = inputStream.read();
        final int read3 = inputStream.read();
        final int read4 = inputStream.read();
        if ((read | read2 | read3 | read4) < 0) {
            throw new EOFException();
        }
        return read + (read2 << 8) + (read3 << 16) + (read4 << 24);
    }
    
    protected void a() throws IOException, RuntimeException, InterruptedException, Exception {
        final int n = 958122090;
        final int n2 = 73959415;
        final long n3 = (n ^ n2) << 32 ^ ~n2;
        if (!this.b(super.c)) {
            this.a(new f(-1));
            return;
        }
        ((e)this.i).a(this.l + this.j + this.k);
        this.a(this.i, new byte[this.j]);
        super.a(new d(this.i, n3));
        this.a(super.c, this.k);
    }
}
