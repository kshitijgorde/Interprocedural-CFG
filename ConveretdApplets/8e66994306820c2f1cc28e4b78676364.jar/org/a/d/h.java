// 
// Decompiled by Procyon v0.5.30
// 

package org.a.d;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class h
{
    public static byte[] a(final long n) {
        final long n2 = 255L;
        final byte[] array = new byte[8];
        for (int i = 0; i < 8; ++i) {
            array[i] = (byte)(n >> i * 8 & n2);
        }
        return array;
    }
    
    public static long a(byte[] array) {
        long n = 0L;
        final long n2 = 255L;
        int length = 8;
        if (array == null) {
            array = new byte[0];
        }
        if (array.length < length) {
            length = array.length;
        }
        for (int i = 0; i < length; ++i) {
            n += (array[i] & n2) << i * 8;
        }
        return n;
    }
    
    public static boolean a(final byte[] array, final byte[] array2) {
        if (array == null && array2 == null) {
            return true;
        }
        if (array == null || array2 == null) {
            return false;
        }
        if (array.length != array2.length) {
            return false;
        }
        boolean b = true;
        for (int n = 0; n < array.length && b; ++n) {
            if (array[n] != array2[n]) {
                b = false;
            }
        }
        return b;
    }
    
    public static byte[] a(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[1024];
        int read = 0;
        while (inputStream.available() >= 0 && read >= 0) {
            read = inputStream.read(array, 0, array.length);
            if (read < 1) {
                continue;
            }
            byteArrayOutputStream.write(array, 0, read);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
