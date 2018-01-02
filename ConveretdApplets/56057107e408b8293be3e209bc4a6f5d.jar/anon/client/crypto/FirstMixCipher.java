// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import anon.client.MixPacket;
import anon.client.ISendCallbackHandler;
import java.util.Vector;

public class FirstMixCipher implements IMixCipher
{
    private boolean m_firstEncryptionPacket;
    private SymCipher m_mixCipher;
    private SymCipher m_channelSymCipher;
    
    public FirstMixCipher(final SymCipher mixCipher, final SymCipher channelSymCipher) {
        this.m_firstEncryptionPacket = true;
        this.m_mixCipher = mixCipher;
        this.m_channelSymCipher = channelSymCipher;
    }
    
    public byte[] encrypt(final byte[] array, final int n, final Vector vector) {
        int length = 0;
        int length2 = array.length;
        byte[] array2;
        byte[] array3;
        if (this.m_firstEncryptionPacket) {
            length2 += this.m_channelSymCipher.getKeys().length;
            if (n > length2) {
                array2 = new byte[n];
            }
            else {
                array2 = new byte[length2];
            }
            System.arraycopy(this.m_channelSymCipher.getKeys(), 0, array2, 0, this.m_channelSymCipher.getKeys().length);
            System.arraycopy(array, 0, array2, this.m_channelSymCipher.getKeys().length, array.length);
            array3 = new byte[array2.length];
            System.arraycopy(array2, 0, array3, 0, this.m_channelSymCipher.getKeys().length);
            length = this.m_channelSymCipher.getKeys().length;
            vector.addElement(new MixEncryptionHandler(this.m_mixCipher, length));
            this.m_firstEncryptionPacket = false;
        }
        else {
            if (n > length2) {
                array2 = new byte[n];
            }
            else {
                array2 = new byte[length2];
            }
            System.arraycopy(array, 0, array2, 0, array.length);
            array3 = new byte[array2.length];
        }
        this.m_channelSymCipher.encryptAES1(array2, length, array3, length, array2.length - length);
        if (length2 < array3.length) {
            final byte[] array4 = array3;
            array3 = new byte[length2];
            System.arraycopy(array4, 0, array3, 0, length2);
        }
        return array3;
    }
    
    public void decrypt(final byte[] array) {
        this.m_channelSymCipher.encryptAES2(array);
    }
    
    public int getNextPacketEncryptionOverhead() {
        int length = 0;
        if (this.m_firstEncryptionPacket) {
            length = this.m_channelSymCipher.getKeys().length;
        }
        return length;
    }
    
    private class MixEncryptionHandler implements ISendCallbackHandler
    {
        private SymCipher m_mixStreamCipher;
        private int m_bytesToEncrypt;
        
        public MixEncryptionHandler(final SymCipher mixStreamCipher, final int bytesToEncrypt) {
            this.m_mixStreamCipher = mixStreamCipher;
            this.m_bytesToEncrypt = bytesToEncrypt;
        }
        
        public void finalizePacket(final MixPacket mixPacket) {
            this.m_mixStreamCipher.encryptAES1(mixPacket.getPayloadData(), 0, mixPacket.getPayloadData(), 0, this.m_bytesToEncrypt);
        }
    }
}
