// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;

public class CTSBlockCipher extends BufferedBlockCipher
{
    private int blockSize;
    
    public CTSBlockCipher(final BlockCipher cipher) {
        if (cipher instanceof OFBBlockCipher || cipher instanceof CFBBlockCipher) {
            throw new IllegalArgumentException("CTSBlockCipher can only accept ECB, or CBC ciphers");
        }
        super.cipher = cipher;
        this.blockSize = cipher.getBlockSize();
        super.buf = new byte[this.blockSize * 2];
        super.bufOff = 0;
    }
    
    public int getUpdateOutputSize(final int n) {
        final int n2 = n + super.bufOff;
        final int n3 = n2 % super.buf.length;
        if (n3 == 0) {
            return n2 - super.buf.length;
        }
        return n2 - n3;
    }
    
    public int getOutputSize(final int n) {
        return n + super.bufOff;
    }
    
    public int processByte(final byte b, final byte[] array, final int n) throws DataLengthException, IllegalStateException {
        int processBlock = 0;
        if (super.bufOff == super.buf.length) {
            processBlock = super.cipher.processBlock(super.buf, 0, array, n);
            System.arraycopy(super.buf, this.blockSize, super.buf, 0, this.blockSize);
            super.bufOff = this.blockSize;
        }
        super.buf[super.bufOff++] = b;
        return processBlock;
    }
    
    public int processBytes(final byte[] array, int n, int i, final byte[] array2, final int n2) throws DataLengthException, IllegalStateException {
        if (i < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        final int blockSize = this.getBlockSize();
        final int updateOutputSize = this.getUpdateOutputSize(i);
        if (updateOutputSize > 0 && n2 + updateOutputSize > array2.length) {
            throw new DataLengthException("output buffer too short");
        }
        int n3 = 0;
        final int n4 = super.buf.length - super.bufOff;
        if (i > n4) {
            System.arraycopy(array, n, super.buf, super.bufOff, n4);
            n3 += super.cipher.processBlock(super.buf, 0, array2, n2);
            System.arraycopy(super.buf, blockSize, super.buf, 0, blockSize);
            super.bufOff = blockSize;
            for (i -= n4, n += n4; i > blockSize; i -= blockSize, n += blockSize) {
                System.arraycopy(array, n, super.buf, super.bufOff, blockSize);
                n3 += super.cipher.processBlock(super.buf, 0, array2, n2 + n3);
                System.arraycopy(super.buf, blockSize, super.buf, 0, blockSize);
            }
        }
        System.arraycopy(array, n, super.buf, super.bufOff, i);
        super.bufOff += i;
        return n3;
    }
    
    public int doFinal(final byte[] array, final int n) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        if (super.bufOff + n > array.length) {
            throw new DataLengthException("output buffer to small in doFinal");
        }
        final int blockSize = super.cipher.getBlockSize();
        final int n2 = super.bufOff - blockSize;
        final byte[] array2 = new byte[blockSize];
        if (super.forEncryption) {
            super.cipher.processBlock(super.buf, 0, array2, 0);
            if (super.bufOff < blockSize) {
                throw new DataLengthException("need at least one block of input for CTS");
            }
            for (int i = super.bufOff; i != super.buf.length; ++i) {
                super.buf[i] = array2[i - blockSize];
            }
            for (int j = blockSize; j != super.bufOff; ++j) {
                final byte[] buf = super.buf;
                final int n3 = j;
                buf[n3] ^= array2[j - blockSize];
            }
            if (super.cipher instanceof CBCBlockCipher) {
                ((CBCBlockCipher)super.cipher).getUnderlyingCipher().processBlock(super.buf, blockSize, array, n);
            }
            else {
                super.cipher.processBlock(super.buf, blockSize, array, n);
            }
            System.arraycopy(array2, 0, array, n + blockSize, n2);
        }
        else {
            final byte[] array3 = new byte[blockSize];
            if (super.cipher instanceof CBCBlockCipher) {
                ((CBCBlockCipher)super.cipher).getUnderlyingCipher().processBlock(super.buf, 0, array2, 0);
            }
            else {
                super.cipher.processBlock(super.buf, 0, array2, 0);
            }
            for (int k = blockSize; k != super.bufOff; ++k) {
                array3[k - blockSize] = (byte)(array2[k - blockSize] ^ super.buf[k]);
            }
            System.arraycopy(super.buf, blockSize, array2, 0, n2);
            super.cipher.processBlock(array2, 0, array, n);
            System.arraycopy(array3, 0, array, n + blockSize, n2);
        }
        final int bufOff = super.bufOff;
        this.reset();
        return bufOff;
    }
}
