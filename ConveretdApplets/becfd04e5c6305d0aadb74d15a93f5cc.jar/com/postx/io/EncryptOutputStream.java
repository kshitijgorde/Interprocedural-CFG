// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.IOException;
import com.postx.sec.algorithms.AES;
import java.io.OutputStream;

public class EncryptOutputStream extends OutputStream
{
    public static final String Ident = "$Id: EncryptOutputStream.java,v 1.3 2011/01/10 05:13:52 blm Exp $";
    private static final int BUF_SIZE = 4096;
    private OutputStream dst;
    private AES aes;
    private byte[] outBuf;
    private int outBufPos;
    
    public void close() throws IOException {
        if (this.dst != null) {
            if (this.outBufPos == this.outBuf.length) {
                this.flushOutBuf();
            }
            this.outBufPos += this.aes.addPadding(this.outBuf, 0, this.outBuf, 0, this.outBufPos, 3);
            this.flushOutBuf();
            try {
                this.dst.close();
            }
            catch (IOException ex) {}
            this.dst = null;
            this.aes.wipe();
        }
    }
    
    public EncryptOutputStream(final OutputStream dst, final byte[] array, final byte[] array2) {
        this.outBuf = new byte[4096];
        this.outBufPos = 0;
        this.dst = dst;
        this.aes = new AES(array, 2, array2, 1);
    }
    
    public void write(final int n) throws IOException {
        if (this.dst == null) {
            throw new IOException("can't write to closed stream");
        }
        if (this.outBufPos == this.outBuf.length) {
            this.flushOutBuf();
        }
        this.outBuf[this.outBufPos++] = (byte)n;
    }
    
    public void write(final byte[] array, int n, int i) throws IOException {
        if (this.dst == null) {
            throw new IOException("can't write to closed stream");
        }
        if (this.outBufPos > 0) {
            final int n2;
            if ((n2 = this.outBufPos % 16) > 0) {
                int n3 = 16 - n2;
                if (i < n3) {
                    n3 = i;
                }
                System.arraycopy(array, n, this.outBuf, this.outBufPos, n3);
                n += n3;
                i -= n3;
                this.outBufPos += n3;
                if (i == 0) {
                    return;
                }
            }
            this.flushOutBuf();
        }
        while (i >= 16) {
            int length = i - i % 16;
            if (this.outBuf.length < length) {
                length = this.outBuf.length;
            }
            this.dst.write(this.aes.arrayencrypt(array, n, this.outBuf, 0, length), 0, length);
            i -= length;
            n += length;
        }
        if (i > 0) {
            System.arraycopy(array, n, this.outBuf, 0, i);
            this.outBufPos = i;
        }
    }
    
    private void flushOutBuf() throws IOException {
        this.dst.write(this.aes.arrayencrypt(this.outBuf, 0, this.outBuf, 0, this.outBufPos), 0, this.outBufPos);
        this.outBufPos = 0;
    }
}
