// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.io.EOFException;
import java.io.InputStream;

public class m
{
    public static int a(final InputStream inputStream) {
        final byte[] array = new byte[4];
        a(inputStream, array);
        return 0x0 | (array[0] << 24 & 0xFF000000) | (array[1] << 16 & 0xFF0000) | (array[2] << 8 & 0xFF00) | (array[3] & 0xFF);
    }
    
    public static void a(final InputStream inputStream, final byte[] array) {
        int read;
        for (int i = 0; i != array.length; i += read) {
            read = inputStream.read(array, i, array.length - i);
            if (read == -1) {
                throw new EOFException("Failed to read all bytes: " + i + "<" + array.length);
            }
        }
    }
}
