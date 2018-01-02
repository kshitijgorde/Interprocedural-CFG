// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.b;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.OutputStream;
import java.io.InputStream;

public final class a
{
    public static int a(InputStream inputStream, OutputStream outputStream) {
        final InputStream inputStream2 = inputStream;
        outputStream = outputStream;
        inputStream = inputStream2;
        final byte[] array = new byte[4096];
        long n = 0L;
        int read;
        while (-1 != (read = inputStream.read(array))) {
            outputStream.write(array, 0, read);
            n += read;
        }
        final long n2;
        if ((n2 = n) > 2147483647L) {
            return -1;
        }
        return (int)n2;
    }
    
    static {
        final StringWriter stringWriter = new StringWriter(4);
        new PrintWriter(stringWriter).println();
        stringWriter.toString();
    }
}
