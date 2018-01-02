// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.paddings;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.DataLengthException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;

public class PaddedBufferedBlockCipher extends BufferedBlockCipher
{
    BlockCipherPadding padding;
    
    public PaddedBufferedBlockCipher(final BlockCipher cipher, final BlockCipherPadding padding) {
        super.cipher = cipher;
        this.padding = padding;
        super.buf = new byte[cipher.getBlockSize()];
        super.bufOff = 0;
    }
    
    public PaddedBufferedBlockCipher(final BlockCipher blockCipher) {
        this(blockCipher, new PKCS7Padding());
    }
    
    public void init(final boolean forEncryption, final CipherParameters cipherParameters) throws IllegalArgumentException {
        super.forEncryption = forEncryption;
        this.reset();
        if (cipherParameters instanceof ParametersWithRandom) {
            final ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.padding.init(parametersWithRandom.getRandom());
            super.cipher.init(forEncryption, parametersWithRandom.getParameters());
        }
        else {
            this.padding.init(null);
            super.cipher.init(forEncryption, cipherParameters);
        }
    }
    
    public int getOutputSize(final int n) {
        final int n2 = n + super.bufOff;
        final int n3 = n2 % super.buf.length;
        if (n3 != 0) {
            return n2 - n3 + super.buf.length;
        }
        if (super.forEncryption) {
            return n2 + super.buf.length;
        }
        return n2;
    }
    
    public int getUpdateOutputSize(final int n) {
        final int n2 = n + super.bufOff;
        final int n3 = n2 % super.buf.length;
        if (n3 == 0) {
            return n2 - super.buf.length;
        }
        return n2 - n3;
    }
    
    public int processByte(final byte b, final byte[] array, final int n) throws DataLengthException, IllegalStateException {
        int processBlock = 0;
        if (super.bufOff == super.buf.length) {
            processBlock = super.cipher.processBlock(super.buf, 0, array, n);
            super.bufOff = 0;
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
            super.bufOff = 0;
            for (i -= n4, n += n4; i > super.buf.length; i -= blockSize, n += blockSize) {
                n3 += super.cipher.processBlock(array, n, array2, n2 + n3);
            }
        }
        System.arraycopy(array, n, super.buf, super.bufOff, i);
        super.bufOff += i;
        return n3;
    }
    
    public int doFinal(final byte[] array, final int n) throws DataLengthException, IllegalStateException, InvalidCipherTextException {
        final int blockSize = super.cipher.getBlockSize();
        int processBlock = 0;
        int processBlock2;
        if (super.forEncryption) {
            if (super.bufOff == blockSize) {
                if (n + 2 * blockSize > array.length) {
                    this.reset();
                    throw new DataLengthException("output buffer too short");
                }
                processBlock = super.cipher.processBlock(super.buf, 0, array, n);
                super.bufOff = 0;
            }
            this.padding.addPadding(super.buf, super.bufOff);
            processBlock2 = processBlock + super.cipher.processBlock(super.buf, 0, array, n + processBlock);
            this.reset();
        }
        else {
            if (super.bufOff != blockSize) {
                this.reset();
                throw new DataLengthException("last block incomplete in decryption");
            }
            processBlock2 = super.cipher.processBlock(super.buf, 0, super.buf, 0);
            super.bufOff = 0;
            try {
                processBlock2 -= this.padding.padCount(super.buf);
                System.arraycopy(super.buf, 0, array, n, processBlock2);
            }
            finally {
                this.reset();
            }
        }
        return processBlock2;
    }
}
