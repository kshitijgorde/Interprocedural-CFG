// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.CipherParameters;

public class DESedeEngine extends DESEngine
{
    protected static final int BLOCK_SIZE = 8;
    private int[] workingKey1;
    private int[] workingKey2;
    private int[] workingKey3;
    private boolean forEncryption;
    
    public DESedeEngine() {
        this.workingKey1 = null;
        this.workingKey2 = null;
        this.workingKey3 = null;
    }
    
    public void init(final boolean forEncryption, final CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to DESede init - " + cipherParameters.getClass().getName());
        }
        final byte[] key = ((KeyParameter)cipherParameters).getKey();
        final byte[] array = new byte[8];
        final byte[] array2 = new byte[8];
        final byte[] array3 = new byte[8];
        if (key.length > 24) {
            throw new IllegalArgumentException("key size greater than 24 bytes");
        }
        this.forEncryption = forEncryption;
        if (key.length == 24) {
            System.arraycopy(key, 0, array, 0, array.length);
            System.arraycopy(key, 8, array2, 0, array2.length);
            System.arraycopy(key, 16, array3, 0, array3.length);
            this.workingKey1 = this.generateWorkingKey(forEncryption, array);
            this.workingKey2 = this.generateWorkingKey(!forEncryption, array2);
            this.workingKey3 = this.generateWorkingKey(forEncryption, array3);
        }
        else {
            System.arraycopy(key, 0, array, 0, array.length);
            System.arraycopy(key, 8, array2, 0, array2.length);
            this.workingKey1 = this.generateWorkingKey(forEncryption, array);
            this.workingKey2 = this.generateWorkingKey(!forEncryption, array2);
            this.workingKey3 = this.workingKey1;
        }
    }
    
    public String getAlgorithmName() {
        return "DESede";
    }
    
    public int getBlockSize() {
        return 8;
    }
    
    public int processBlock(final byte[] array, final int n, final byte[] array2, final int n2) {
        if (this.workingKey1 == null) {
            throw new IllegalStateException("DESede engine not initialised");
        }
        if (n + 8 > array.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 8 > array2.length) {
            throw new DataLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            this.desFunc(this.workingKey1, array, n, array2, n2);
            this.desFunc(this.workingKey2, array2, n2, array2, n2);
            this.desFunc(this.workingKey3, array2, n2, array2, n2);
        }
        else {
            this.desFunc(this.workingKey3, array, n, array2, n2);
            this.desFunc(this.workingKey2, array2, n2, array2, n2);
            this.desFunc(this.workingKey1, array2, n2, array2, n2);
        }
        return 8;
    }
    
    public void reset() {
    }
}
