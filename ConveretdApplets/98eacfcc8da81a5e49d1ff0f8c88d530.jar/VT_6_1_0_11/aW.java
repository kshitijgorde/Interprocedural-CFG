// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class aW extends g
{
    private int j;
    private byte[] k;
    
    public aW(final int j, final byte[] k) {
        this.j = j;
        this.k = k;
        this.h = this.b();
    }
    
    public aW(final int n) {
        this(n, null);
    }
    
    public aW(final InputStream inputStream) {
        this.a(inputStream);
    }
    
    protected final void a() {
        if (this.h != null) {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.h);
            try {
                if (this.i < 4) {
                    if (this.i > 0) {
                        this.k = this.h;
                    }
                    return;
                }
                final byte[] array = new byte[4];
                byteArrayInputStream.read(array);
                this.j = VT_6_1_0_11.g.a(array);
                if (this.i > 4) {
                    byteArrayInputStream.read(this.k = new byte[this.i - 4]);
                }
            }
            catch (IOException ex) {}
        }
    }
    
    private byte[] b() {
        this.i = 0;
        if (this.j >= 0) {
            this.i += 4;
        }
        if (this.k != null) {
            this.i += this.k.length;
        }
        if (this.i > 0) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(this.i);
            try {
                if (this.j > 0) {
                    byteArrayOutputStream.write(VT_6_1_0_11.g.a(this.j));
                }
                if (this.k != null) {
                    byteArrayOutputStream.write(this.k);
                }
                return byteArrayOutputStream.toByteArray();
            }
            catch (IOException ex) {
                return byteArrayOutputStream.toByteArray();
            }
            finally {
                try {
                    byteArrayOutputStream.close();
                }
                catch (IOException ex2) {}
            }
        }
        return null;
    }
    
    public final void a(final OutputStream outputStream) {
        outputStream.write(aW.b);
        outputStream.write(VT_6_1_0_11.g.a(this.i));
    }
    
    public final int a(final byte[] array, final int n) {
        System.arraycopy(aW.b, 0, array, n, 4);
        System.arraycopy(VT_6_1_0_11.g.a(this.i), 0, array, n + 4, 4);
        return 8;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer("    FACT CHUNK\n")).append("  HEADER\n");
        sb.append("Chunk identifier       (4): ").append("fact").append("\n");
        sb.append("Chunk size             (4): ").append(this.i).append("\n");
        sb.append("  DATA\n");
        sb.append("Data Length in Samples (4): ").append(this.j).append("\n");
        return sb.toString();
    }
}
