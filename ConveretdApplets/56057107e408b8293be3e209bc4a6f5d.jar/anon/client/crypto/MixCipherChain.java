// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import java.util.Vector;

public class MixCipherChain
{
    private IMixCipher[] m_cipherChain;
    
    public MixCipherChain(final IMixCipher[] cipherChain) {
        this.m_cipherChain = cipherChain;
    }
    
    public byte[] encryptPacket(final byte[] array, final int n, final Vector vector) {
        byte[] encrypt = array;
        for (int i = this.m_cipherChain.length - 1; i >= 0; --i) {
            encrypt = this.m_cipherChain[i].encrypt(encrypt, n, vector);
        }
        return encrypt;
    }
    
    public int getNextPacketEncryptionOverhead() {
        int n = 0;
        for (int i = this.m_cipherChain.length - 1; i >= 0; --i) {
            n += this.m_cipherChain[i].getNextPacketEncryptionOverhead();
        }
        return n;
    }
    
    public void decryptPacket(final byte[] array) {
        for (int i = 0; i < this.m_cipherChain.length; ++i) {
            this.m_cipherChain[i].decrypt(array);
        }
    }
}
