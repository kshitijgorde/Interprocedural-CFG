// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.http;

import java.util.zip.GZIPInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.io.IOException;
import java.io.InputStream;

public final class Read
{
    private static final int SLEEP_TIME = 100;
    
    public static int readBytesBlocking(final InputStream in, final byte[] b, final int off, final int len, final int timeoutInMillis) throws IOException {
        int totalBytesRead = 0;
        long whenToGiveUp = System.currentTimeMillis() + timeoutInMillis;
        int bytesRead;
        while (totalBytesRead < len && (bytesRead = in.read(b, off + totalBytesRead, len - totalBytesRead)) >= 0) {
            if (bytesRead == 0) {
                try {
                    if (System.currentTimeMillis() >= whenToGiveUp) {
                        throw new IOException("timeout");
                    }
                    Thread.sleep(100L);
                }
                catch (InterruptedException e) {}
            }
            else {
                totalBytesRead += bytesRead;
                whenToGiveUp = System.currentTimeMillis() + timeoutInMillis;
            }
        }
        return totalBytesRead;
    }
    
    public static String readStringBlocking(final InputStream is, int estimatedLength, final long timeoutInMillis, final boolean gzipped, final Charset charSet) throws IOException {
        if (estimatedLength <= 0) {
            estimatedLength = 32768;
        }
        final int chunkSizeInBytes = Math.min(estimatedLength, 4096);
        final byte[] ba = new byte[chunkSizeInBytes];
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(estimatedLength + 1024);
        InputStream decoder;
        if (gzipped) {
            decoder = new GZIPInputStream(is, 4096);
        }
        else {
            decoder = is;
        }
        long whenToGiveUp = System.currentTimeMillis() + timeoutInMillis;
        int bytesRead;
        while ((bytesRead = decoder.read(ba, 0, chunkSizeInBytes)) >= 0) {
            if (bytesRead == 0) {
                try {
                    if (System.currentTimeMillis() >= whenToGiveUp) {
                        throw new IOException("timeout");
                    }
                    Thread.sleep(100L);
                }
                catch (InterruptedException e) {}
            }
            else {
                baos.write(ba, 0, bytesRead);
                whenToGiveUp = System.currentTimeMillis() + timeoutInMillis;
            }
        }
        decoder.close();
        baos.close();
        return new String(baos.toByteArray(), charSet);
    }
}
