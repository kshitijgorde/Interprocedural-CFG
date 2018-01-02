// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.io.RandomAccessFile;

public class L
{
    private final RandomAccessFile b;
    public boolean a;
    private static /* synthetic */ boolean c;
    
    public L(final RandomAccessFile b) {
        this.a = true;
        this.b = b;
    }
    
    public final void a(final int n) {
        if (!L.c && n < 0) {
            throw new AssertionError();
        }
        this.b.seek(n);
    }
    
    public final long a() {
        return this.b.getFilePointer();
    }
    
    public final void a(final String s) {
        if (!this.b(s)) {
            throw new DataFormatException("Buffer pattern match failed on '" + s + "'");
        }
        this.b.skipBytes(s.length());
    }
    
    public final boolean b(final String s) {
        if (!L.c && s == null) {
            throw new AssertionError();
        }
        final long filePointer = this.b.getFilePointer();
        try {
            char[] charArray;
            for (int length = (charArray = s.toCharArray()).length, i = 0; i < length; ++i) {
                if (this.b.readByte() != charArray[i]) {
                    return false;
                }
            }
            return true;
        }
        finally {
            this.b.seek(filePointer);
        }
    }
    
    public final byte b(int byte1) {
        final long filePointer = this.b.getFilePointer();
        this.b.skipBytes(11);
        byte1 = this.b.readByte();
        this.b.seek(filePointer);
        return (byte)byte1;
    }
    
    public final int b() {
        if (this.a) {
            return (this.b.readByte() & 0xFF) << 8 | (this.b.readByte() & 0xFF);
        }
        return (this.b.readByte() & 0xFF) | (this.b.readByte() & 0xFF) << 8;
    }
    
    public final int c() {
        if (this.a) {
            return (this.b.readByte() & 0xFF) << 24 | (this.b.readByte() & 0xFF) << 16 | (this.b.readByte() & 0xFF) << 8 | (this.b.readByte() & 0xFF);
        }
        return (this.b.readByte() & 0xFF) | (this.b.readByte() & 0xFF) << 8 | (this.b.readByte() & 0xFF) << 16 | (this.b.readByte() & 0xFF) << 24;
    }
    
    public final void c(final int n) {
        if (!L.c && n <= 0) {
            throw new AssertionError();
        }
        this.b.skipBytes(n);
    }
    
    public final int d() {
        return (int)this.b.length();
    }
    
    public final void a(final byte[] array) {
        if (this.b.length() - this.b.getFilePointer() < array.length) {
            throw new IOException("File is too short for read request");
        }
        this.b.read(array);
    }
    
    static {
        L.c = !L.class.desiredAssertionStatus();
    }
}
