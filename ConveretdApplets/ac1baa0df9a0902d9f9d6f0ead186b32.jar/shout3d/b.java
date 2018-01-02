// 
// Decompiled by Procyon v0.5.30
// 

package shout3d;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

class b
{
    public void a(final ByteArrayInputStream byteArrayInputStream, final a[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        try {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
            final byte[] array2 = new byte[512];
            while (true) {
                final int read = gzipInputStream.read(array2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(array2, 0, read);
                byteArrayOutputStream.flush();
            }
            gzipInputStream.close();
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        try {
            array[0] = new a(byteArrayOutputStream.toByteArray());
        }
        catch (Exception ex2) {
            System.err.println("GZIPWrapper: " + ex2);
        }
    }
}
