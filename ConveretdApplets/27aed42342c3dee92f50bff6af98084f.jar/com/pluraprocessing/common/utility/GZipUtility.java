// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.utility;

import java.util.zip.ZipException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;

public class GZipUtility
{
    public static final byte[] compress(final String uncompressed) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final GZIPOutputStream zos = new GZIPOutputStream(baos);
        final byte[] uncompressedBytes = uncompressed.getBytes();
        zos.write(uncompressedBytes, 0, uncompressedBytes.length);
        zos.close();
        return baos.toByteArray();
    }
    
    public static final byte[] uncompress(final byte[] compressed) throws ZipException, IOException {
        byte[] uncompressed = null;
        GZIPInputStream gzin = null;
        ByteArrayOutputStream decompressed = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(compressed);
            gzin = new GZIPInputStream(bais);
            decompressed = new ByteArrayOutputStream();
            final byte[] buf = new byte[8192];
            int len;
            while ((len = gzin.read(buf)) > 0) {
                decompressed.write(buf, 0, len);
            }
            uncompressed = decompressed.toByteArray();
        }
        finally {
            try {
                if (gzin != null) {
                    gzin.close();
                }
                if (decompressed != null) {
                    decompressed.close();
                }
            }
            catch (IOException ioExc) {
                System.out.println("Exception Occured in finally block" + ioExc.getMessage());
            }
        }
        try {
            if (gzin != null) {
                gzin.close();
            }
            if (decompressed != null) {
                decompressed.close();
            }
        }
        catch (IOException ioExc) {
            System.out.println("Exception Occured in finally block" + ioExc.getMessage());
        }
        return uncompressed;
    }
}
