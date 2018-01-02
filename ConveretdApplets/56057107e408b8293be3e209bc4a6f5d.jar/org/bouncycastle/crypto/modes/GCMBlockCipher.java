// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.modes;

import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.BlockCipher;
import java.math.BigInteger;

public class GCMBlockCipher implements AEADBlockCipher
{
    private static final int BLOCK_SIZE = 16;
    private static final byte[] ZEROES;
    private static final BigInteger R;
    private static final BigInteger ZERO;
    private final BlockCipher cipher;
    private boolean forEncryption;
    private int macSize;
    private byte[] nonce;
    private byte[] A;
    private KeyParameter keyParam;
    private BigInteger H;
    private BigInteger initS;
    private byte[] J0;
    private byte[] bufBlock;
    private byte[] macBlock;
    private BigInteger S;
    private byte[] counter;
    private int bufOff;
    private long totalLength;
    
    public GCMBlockCipher(final BlockCipher cipher) {
        if (cipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        this.cipher = cipher;
    }
    
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }
    
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCM";
    }
    
    public void init(final boolean forEncryption, final CipherParameters cipherParameters) throws IllegalArgumentException {
        this.forEncryption = forEncryption;
        this.macSize = 16;
        this.macBlock = null;
        this.bufBlock = new byte[forEncryption ? 16 : (16 + this.macSize)];
        if (cipherParameters instanceof AEADParameters) {
            final AEADParameters aeadParameters = (AEADParameters)cipherParameters;
            this.nonce = aeadParameters.getNonce();
            this.A = aeadParameters.getAssociatedText();
            if (aeadParameters.getMacSize() != 128) {
                throw new IllegalArgumentException("only 128-bit MAC supported currently");
            }
            this.keyParam = aeadParameters.getKey();
        }
        else {
            if (!(cipherParameters instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("invalid parameters passed to GCM");
            }
            final ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.A = null;
            this.keyParam = (KeyParameter)parametersWithIV.getParameters();
        }
        if (this.nonce == null || this.nonce.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (this.A == null) {
            this.A = new byte[0];
        }
        this.cipher.init(true, this.keyParam);
        final byte[] array = new byte[16];
        this.cipher.processBlock(GCMBlockCipher.ZEROES, 0, array, 0);
        this.H = new BigInteger(1, array);
        this.initS = this.gHASH(this.A, false);
        if (this.nonce.length == 12) {
            this.J0 = new byte[16];
            System.arraycopy(this.nonce, 0, this.J0, 0, this.nonce.length);
            this.J0[15] = 1;
        }
        else {
            this.J0 = this.asBlock(this.multiply(this.gHASH(this.nonce, true).xor(BigInteger.valueOf(this.nonce.length * 8)), this.H));
        }
        this.S = this.initS;
        this.counter = Arrays.clone(this.J0);
        this.bufOff = 0;
        this.totalLength = 0L;
    }
    
    public byte[] getMac() {
        return Arrays.clone(this.macBlock);
    }
    
    public int getOutputSize(final int n) {
        if (this.forEncryption) {
            return n + this.bufOff + this.macSize;
        }
        return n + this.bufOff - this.macSize;
    }
    
    public int getUpdateOutputSize(final int n) {
        return (n + this.bufOff) / 16 * 16;
    }
    
    public int processByte(final byte b, final byte[] array, final int n) throws DataLengthException {
        return this.process(b, array, n);
    }
    
    public int processBytes(final byte[] array, final int n, final int n2, final byte[] array2, final int n3) throws DataLengthException {
        int n4 = 0;
        for (int i = 0; i != n2; ++i) {
            n4 += this.process(array[n + i], array2, n3 + n4);
        }
        return n4;
    }
    
    private int process(final byte b, final byte[] array, final int n) throws DataLengthException {
        this.bufBlock[this.bufOff++] = b;
        if (this.bufOff == this.bufBlock.length) {
            this.gCTRBlock(this.bufBlock, 16, array, n);
            if (!this.forEncryption) {
                System.arraycopy(this.bufBlock, 16, this.bufBlock, 0, 16);
            }
            this.bufOff = this.bufBlock.length - 16;
            return 16;
        }
        return 0;
    }
    
    public int doFinal(final byte[] array, final int n) throws IllegalStateException, InvalidCipherTextException {
        int bufOff = this.bufOff;
        if (!this.forEncryption) {
            if (bufOff < this.macSize) {
                throw new InvalidCipherTextException("data too short");
            }
            bufOff -= this.macSize;
        }
        if (bufOff > 0) {
            final byte[] array2 = new byte[16];
            System.arraycopy(this.bufBlock, 0, array2, 0, bufOff);
            this.gCTRBlock(array2, bufOff, array, n);
        }
        this.S = this.multiply(this.S.xor(BigInteger.valueOf(this.A.length * 8).shiftLeft(64).add(BigInteger.valueOf(this.totalLength * 8L))), this.H);
        final byte[] array3 = new byte[16];
        this.cipher.processBlock(this.J0, 0, array3, 0);
        final byte[] block = this.asBlock(this.S.xor(new BigInteger(1, array3)));
        int n2 = bufOff;
        if (this.forEncryption) {
            System.arraycopy(this.macBlock = block, 0, array, n + this.bufOff, block.length);
            n2 += block.length;
        }
        else {
            this.macBlock = new byte[this.macSize];
            System.arraycopy(this.bufBlock, bufOff, this.macBlock, 0, this.macSize);
            if (!Arrays.areEqual(block, this.macBlock)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        this.reset(false);
        return n2;
    }
    
    public void reset() {
        this.reset(true);
    }
    
    private void reset(final boolean b) {
        this.S = this.initS;
        this.counter = Arrays.clone(this.J0);
        this.bufOff = 0;
        this.totalLength = 0L;
        if (this.bufBlock != null) {
            Arrays.fill(this.bufBlock, (byte)0);
        }
        if (b) {
            this.macBlock = null;
        }
        this.cipher.reset();
    }
    
    private void gCTRBlock(final byte[] array, final int n, final byte[] array2, final int n2) {
        inc(this.counter);
        final byte[] array3 = new byte[16];
        this.cipher.processBlock(this.counter, 0, array3, 0);
        if (this.forEncryption) {
            System.arraycopy(GCMBlockCipher.ZEROES, n, array3, n, 16 - n);
            for (int i = n - 1; i >= 0; --i) {
                final byte[] array4 = array3;
                final int n3 = i;
                array4[n3] ^= array[i];
                array2[n2 + i] = array3[i];
            }
            this.gHASHBlock(array3);
        }
        else {
            for (int j = n - 1; j >= 0; --j) {
                final byte[] array5 = array3;
                final int n4 = j;
                array5[n4] ^= array[j];
                array2[n2 + j] = array3[j];
            }
            this.gHASHBlock(array);
        }
        this.totalLength += n;
    }
    
    private BigInteger gHASH(final byte[] array, final boolean b) {
        BigInteger bigInteger = GCMBlockCipher.ZERO;
        for (int i = 0; i < array.length; i += 16) {
            final byte[] array2 = new byte[16];
            System.arraycopy(array, i, array2, 0, Math.min(array.length - i, 16));
            bigInteger = this.multiply(bigInteger.xor(new BigInteger(1, array2)), this.H);
        }
        return bigInteger;
    }
    
    private void gHASHBlock(byte[] array) {
        if (array.length > 16) {
            final byte[] array2 = new byte[16];
            System.arraycopy(array, 0, array2, 0, 16);
            array = array2;
        }
        this.S = this.multiply(this.S.xor(new BigInteger(1, array)), this.H);
    }
    
    private static void inc(final byte[] array) {
        for (int n = 15; n >= 12 && (array[n] = (byte)(array[n] + 1 & 0xFF)) == 0; --n) {}
    }
    
    private BigInteger multiply(final BigInteger bigInteger, final BigInteger bigInteger2) {
        BigInteger bigInteger3 = GCMBlockCipher.ZERO;
        BigInteger bigInteger4 = bigInteger;
        for (int i = 0; i < 128; ++i) {
            if (bigInteger2.testBit(127 - i)) {
                bigInteger3 = bigInteger3.xor(bigInteger4);
            }
            final boolean testBit = bigInteger4.testBit(0);
            bigInteger4 = bigInteger4.shiftRight(1);
            if (testBit) {
                bigInteger4 = bigInteger4.xor(GCMBlockCipher.R);
            }
        }
        return bigInteger3;
    }
    
    private byte[] asBlock(final BigInteger bigInteger) {
        byte[] unsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger);
        if (unsignedByteArray.length < 16) {
            final byte[] array = new byte[16];
            System.arraycopy(unsignedByteArray, 0, array, array.length - unsignedByteArray.length, unsignedByteArray.length);
            unsignedByteArray = array;
        }
        return unsignedByteArray;
    }
    
    static {
        ZEROES = new byte[16];
        R = new BigInteger("11100001", 2).shiftLeft(120);
        ZERO = BigInteger.valueOf(0L);
    }
}
