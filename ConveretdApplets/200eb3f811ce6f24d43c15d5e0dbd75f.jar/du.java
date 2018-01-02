import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class du implements ak
{
    private do a;
    private byte[] b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    
    public du(final do a, final byte[] b) throws am {
        this.a = a;
        this.c = a.l;
        Label_0240: {
            if (a.e() == 1 && b.length > 0) {
                if (ak.a.l()) {
                    ak.a.j("processing gzipped message for header " + a.h());
                }
                this.g = true;
                this.e = b.length;
                byte[] array = new byte[8 * b.length];
                try {
                    final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(b, 0, b.length));
                    int read = gzipInputStream.read(array);
                    while (true) {
                        if (read >= array.length) {
                            final byte[] array2 = array;
                            array = new byte[array.length * 2];
                            System.arraycopy(array2, 0, array, 0, read);
                        }
                        final int read2 = gzipInputStream.read(array, read, array.length - read);
                        if (read2 <= 0) {
                            break;
                        }
                        read += read2;
                    }
                    System.arraycopy(array, 0, this.b = new byte[read], 0, read);
                    break Label_0240;
                }
                catch (Exception ex) {
                    throw new al("trouble with gzip decompression", ex);
                }
            }
            this.g = false;
            this.b = b;
        }
        this.d = this.b.length;
        this.f = this.c + (this.g ? this.e : this.d);
        if (ak.a.k()) {
            ak.a.i("content:" + new String(this.b));
        }
    }
    
    public final do a() {
        return this.a;
    }
    
    public final byte[] b() {
        return this.b;
    }
}
