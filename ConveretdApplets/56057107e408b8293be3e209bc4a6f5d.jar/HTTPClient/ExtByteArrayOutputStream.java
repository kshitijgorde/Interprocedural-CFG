// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.io.OutputStream;

final class ExtByteArrayOutputStream
{
    private byte[] buf;
    private int count;
    
    public ExtByteArrayOutputStream() {
        this(100);
    }
    
    public ExtByteArrayOutputStream(final int n) {
        this.buf = new byte[n];
        this.count = 0;
    }
    
    public final void write(final String s) {
        final int length = s.length();
        if (this.buf.length - this.count < length) {
            this.buf = Util.resizeArray(this.buf, Math.max(this.buf.length * 2, this.count + 2 * length));
        }
        Util.getBytes(s, this.buf, this.count);
        this.count += length;
    }
    
    public final void write(final String s, final String s2) {
        final int length = s.length();
        final int length2 = s2.length();
        if (this.buf.length - this.count < length + length2) {
            this.buf = Util.resizeArray(this.buf, Math.max(this.buf.length * 2, this.count + 2 * (length + length2)));
        }
        Util.getBytes(s, this.buf, this.count);
        this.count += length;
        Util.getBytes(s2, this.buf, this.count);
        this.count += length2;
    }
    
    public final void write(final String s, final String s2, final String s3) {
        final int length = s.length();
        final int length2 = s2.length();
        final int length3 = s3.length();
        if (this.buf.length - this.count < length + length2 + length3) {
            this.buf = Util.resizeArray(this.buf, Math.max(this.buf.length * 2, this.count + 2 * (length + length2 + length3)));
        }
        Util.getBytes(s, this.buf, this.count);
        this.count += length;
        Util.getBytes(s2, this.buf, this.count);
        this.count += length2;
        Util.getBytes(s3, this.buf, this.count);
        this.count += length3;
    }
    
    public final void write(final byte[] array) {
        if (this.buf.length - this.count < array.length) {
            this.buf = Util.resizeArray(this.buf, Math.max(this.buf.length * 2, this.count + 2 * array.length));
        }
        System.arraycopy(array, 0, this.buf, this.count, array.length);
        this.count += array.length;
    }
    
    public final void reset() {
        this.count = 0;
    }
    
    public final void writeTo(final OutputStream outputStream) throws IOException {
        outputStream.write(this.buf, 0, this.count);
    }
}
