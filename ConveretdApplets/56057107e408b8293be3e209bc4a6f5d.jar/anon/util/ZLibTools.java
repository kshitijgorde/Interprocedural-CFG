// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import logging.LogHolder;
import logging.LogType;
import java.util.zip.Inflater;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;

public final class ZLibTools
{
    public static byte[] compress(final byte[] array) {
        byte[] byteArray = null;
        try {
            final Deflater deflater = new Deflater(9);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            deflaterOutputStream.write(array, 0, array.length);
            deflaterOutputStream.finish();
            byteArray = byteArrayOutputStream.toByteArray();
        }
        catch (Throwable t) {}
        return byteArray;
    }
    
    public static byte[] decompress(final byte[] input) {
        byte[] byteArray = null;
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final Inflater inflater = new Inflater();
            inflater.setInput(input);
            final byte[] array = new byte[10000];
            int inflate;
            while ((inflate = inflater.inflate(array)) > 0) {
                byteArrayOutputStream.write(array, 0, inflate);
            }
            byteArrayOutputStream.flush();
            byteArray = byteArrayOutputStream.toByteArray();
        }
        catch (Throwable t) {
            LogHolder.log(6, LogType.MISC, "ZLIb decompress() decommpressed failed!");
        }
        return byteArray;
    }
}
