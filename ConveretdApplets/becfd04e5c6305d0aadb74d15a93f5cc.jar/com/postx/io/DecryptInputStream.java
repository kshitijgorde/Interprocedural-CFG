// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.io;

import java.io.IOException;
import com.postx.sec.algorithms.AES;
import com.postx.sec.algorithms.ARC4;
import java.io.InputStream;

public class DecryptInputStream extends InputStream
{
    public static final String Ident = "$Id: DecryptInputStream.java,v 1.5 2011/01/25 21:51:19 blm Exp $";
    private static final int BUF_SIZE = 4096;
    private InputStream src;
    private ARC4 arc4;
    private AES aes;
    private byte[] temp;
    private int tempPos;
    private byte[] inBuf;
    private int inBufStart;
    private int inBufEnd;
    
    public void close() {
        if (this.src != null) {
            if (this.arc4 != null) {
                this.arc4.wipe();
            }
            else {
                this.aes.wipe();
            }
            this.src = null;
        }
    }
    
    public DecryptInputStream(final InputStream src, final byte[] array, final int n, final int n2) {
        this.temp = new byte[4112];
        this.tempPos = 0;
        this.inBufStart = 0;
        this.inBufEnd = 0;
        this.src = src;
        this.arc4 = new ARC4(array, n, n2);
    }
    
    public DecryptInputStream(final InputStream src, byte[] array, final byte[] array2) {
        this.temp = new byte[4112];
        this.tempPos = 0;
        this.inBufStart = 0;
        this.inBufEnd = 0;
        this.src = src;
        final int length = array.length;
        if (length != 16 && length != 24 && length != 32) {
            int n;
            if (length < 16) {
                n = 16;
            }
            else if (length < 24) {
                n = 24;
            }
            else {
                n = 32;
            }
            final byte[] array3 = new byte[n];
            int i = (n > array.length) ? array.length : n;
            int n2 = 0;
            System.arraycopy(array, 0, array3, 0, i);
            while (i < n) {
                int length2 = n - i;
                if (length2 > array.length) {
                    length2 = array.length;
                }
                else {
                    n2 = (array.length - length2) / 2;
                }
                for (int j = 0, n3 = length2 - 1; j < length2; ++j, --n3) {
                    array3[i + j] = (byte)(array[n2 + n3] ^ 0xA5);
                }
                i += length2;
            }
            array = array3;
        }
        this.aes = new AES(array, 2, array2, 3);
    }
    
    public int read() throws IOException {
        if (this.src == null || (this.inBufStart == this.inBufEnd && this.fillInBuf() == -1)) {
            return -1;
        }
        return this.inBuf[this.inBufStart++] & 0xFF;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (this.src == null || (this.inBufStart == this.inBufEnd && this.fillInBuf() == -1)) {
            return -1;
        }
        int n3 = this.inBufEnd - this.inBufStart;
        if (n2 < n3) {
            n3 = n2;
        }
        System.arraycopy(this.inBuf, this.inBufStart, array, n, n3);
        this.inBufStart += n3;
        return n3;
    }
    
    private int fillInBuf() throws IOException {
        final byte[] temp = this.temp;
        final int tempPos = this.tempPos;
        int length;
        int tempPos2;
        int read;
        for (length = temp.length, tempPos2 = this.tempPos; tempPos2 < length && (read = this.src.read(temp, tempPos2, length - tempPos2)) != -1; tempPos2 += read) {}
        if (tempPos2 == 0) {
            this.close();
            return -1;
        }
        if (this.arc4 != null) {
            this.inBuf = this.arc4.arraycrypt(temp, 0, temp, 0, tempPos2);
            this.inBufStart = 0;
            return this.inBufEnd = tempPos2;
        }
        final boolean b = tempPos2 == 16;
        if (b) {
            this.inBuf = this.aes.finishDecrypt(temp, 0);
        }
        else {
            this.inBuf = this.aes.decrypt(temp, 0, null, 0, tempPos2 - 16);
        }
        this.inBufStart = 0;
        this.inBufEnd = this.inBuf.length;
        if (b) {
            this.tempPos = 0;
            if (this.inBufEnd == 0) {
                this.close();
                return -1;
            }
        }
        else {
            final int tempPos3 = tempPos2 - this.inBufEnd;
            if (tempPos3 > 0) {
                System.arraycopy(temp, this.inBufEnd, temp, 0, tempPos3);
            }
            this.tempPos = tempPos3;
        }
        return this.inBufEnd;
    }
}
