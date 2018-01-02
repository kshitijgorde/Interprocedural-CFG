// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.common.utils;

import java.util.zip.GZIPInputStream;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.Inflater;
import java.io.ByteArrayInputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Deflater;
import java.io.ByteArrayOutputStream;

public class CompressionUtils
{
    public static final int COMPRESSION_TYPE_DEFLATE = 1;
    public static final int COMPRESSION_TYPE_GZIP = 2;
    public static final int COMPRESSION_TYPE_NONE = 0;
    
    public static byte[] compressObject(final Object object, final int compressionType) throws Exception {
        if (object != null) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            if (compressionType == 1) {
                oos = new ObjectOutputStream(new DeflaterOutputStream(baos, new Deflater(1, false)));
            }
            else {
                if (compressionType != 2) {
                    throw new Exception("Unbekannter CompressionType: " + compressionType);
                }
                oos = new ObjectOutputStream(new GZIPOutputStream(baos));
            }
            oos.writeObject(object);
            oos.close();
            return baos.toByteArray();
        }
        return null;
    }
    
    public static Object decompressObject(final byte[] byteArray, final int compressionType) throws Exception {
        Object object = null;
        if (byteArray != null) {
            ObjectInputStream ois = null;
            if (compressionType == 1) {
                ois = new ObjectInputStream(new InflaterInputStream(new ByteArrayInputStream(byteArray), new Inflater(false)));
            }
            else {
                if (compressionType != 2) {
                    throw new Exception("Unbekannter CompressionType: " + compressionType);
                }
                ois = new ObjectInputStream(new GZIPInputStream(new ByteArrayInputStream(byteArray)));
            }
            object = ois.readObject();
            ois.close();
        }
        return object;
    }
    
    public static int getObjectSize(final Object obj) {
        int byteCount = -1;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            byteCount = baos.toByteArray().length;
        }
        catch (Exception e) {}
        return byteCount;
    }
    
    public static int getObjectSizeDeflated(final Object obj) {
        int byteCount = -1;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DeflaterOutputStream dos = new DeflaterOutputStream(baos, new Deflater(1, true));
            final ObjectOutputStream oos = new ObjectOutputStream(dos);
            oos.writeObject(obj);
            oos.close();
            byteCount = baos.toByteArray().length;
        }
        catch (Exception e) {}
        return byteCount;
    }
    
    public static int getObjectSizeGZIPped(final Object obj) {
        int byteCount = -1;
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final GZIPOutputStream zos = new GZIPOutputStream(baos);
            final ObjectOutputStream oos = new ObjectOutputStream(zos);
            oos.writeObject(obj);
            oos.close();
            byteCount = baos.toByteArray().length;
        }
        catch (Exception e) {}
        return byteCount;
    }
    
    public static void main(final String[] args) {
    }
}
