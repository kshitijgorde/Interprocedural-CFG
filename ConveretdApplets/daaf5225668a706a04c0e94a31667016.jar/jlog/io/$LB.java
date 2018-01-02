// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.io.Writer;
import java.io.Reader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;

public class $LB
{
    public static final int $OPD = 4096;
    
    public static long copy(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, new byte[4096]);
    }
    
    public static long copy(final InputStream inputStream, final OutputStream outputStream, final byte[] array) throws IOException {
        long n = 0L;
        Thread.currentThread();
        while (true) {
            final int read = inputStream.read(array);
            if (read < 0) {
                break;
            }
            n += read;
            Thread.yield();
            outputStream.write(array, 0, read);
            Thread.yield();
        }
        outputStream.flush();
        return n;
    }
    
    public static long copy(final Reader reader, final Writer writer) throws IOException {
        return copy(reader, writer, new char[4096]);
    }
    
    public static long copy(final Reader reader, final Writer writer, final char[] array) throws IOException {
        long n = 0L;
        Thread.currentThread();
        while (true) {
            final int read = reader.read(array);
            if (read < 0) {
                break;
            }
            n += read;
            Thread.yield();
            writer.write(array, 0, read);
            Thread.yield();
        }
        writer.flush();
        return n;
    }
}
