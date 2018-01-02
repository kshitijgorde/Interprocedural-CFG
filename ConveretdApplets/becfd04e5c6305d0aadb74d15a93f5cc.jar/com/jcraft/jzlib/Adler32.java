// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

final class Adler32
{
    private java.util.zip.Adler32 adler;
    
    Adler32() {
        this.adler = new java.util.zip.Adler32();
    }
    
    long adler32(final long n, final byte[] array, final int n2, final int n3) {
        if (n == 1L) {
            this.adler.reset();
        }
        if (array == null) {
            this.adler.reset();
        }
        else {
            this.adler.update(array, n2, n3);
        }
        return this.adler.getValue();
    }
}
