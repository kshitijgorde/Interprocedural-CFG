// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.OutputStream;

public class RandomAccessFileOutputStream extends OutputStream
{
    public static final String Ident = "$Id: RandomAccessFileOutputStream.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private RandomAccessFile f;
    
    public RandomAccessFileOutputStream(final RandomAccessFile f) {
        this.f = f;
    }
    
    public void write(final int n) throws IOException {
        this.f.write(n);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.f.write(array, n, n2);
    }
}
