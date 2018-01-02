// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.InputStream;

public class RandomAccessFileInputStream extends InputStream
{
    public static final String Ident = "$Id: RandomAccessFileInputStream.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private RandomAccessFile f;
    
    public RandomAccessFileInputStream(final RandomAccessFile f) {
        this.f = f;
    }
    
    public int read() throws IOException {
        return this.f.read();
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        return this.f.read(array, n, n2);
    }
}
