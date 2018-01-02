// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.OutputStream;

public class g extends OutputStream
{
    private RandomAccessFile A;
    
    public g(final RandomAccessFile a) {
        if (a == null) {
            throw new IllegalArgumentException(m.A("SeekableOutputStream0"));
        }
        this.A = a;
    }
    
    public void write(final int n) throws IOException {
        this.A.write(n);
    }
    
    public void write(final byte[] array) throws IOException {
        this.A.write(array);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.A.write(array, n, n2);
    }
    
    public void flush() throws IOException {
        this.A.getFD().sync();
    }
    
    public void close() throws IOException {
        this.A.close();
    }
    
    public long A() throws IOException {
        return this.A.getFilePointer();
    }
    
    public void A(final long n) throws IOException {
        this.A.seek(n);
    }
}
