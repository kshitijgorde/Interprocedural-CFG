// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import anon.client.replay.ReplayTimestamp;
import java.util.Vector;
import anon.client.MixParameters;

public class DefaultMixCipher implements IMixCipher
{
    private boolean m_firstEncryptionPacket;
    private MixParameters m_mixParameters;
    private SymCipher m_symCipher;
    
    public DefaultMixCipher(final MixParameters mixParameters, final SymCipher symCipher) {
        this.m_firstEncryptionPacket = true;
        this.m_mixParameters = mixParameters;
        this.m_symCipher = symCipher;
    }
    
    public byte[] encrypt(final byte[] array, final int n, final Vector vector) {
        int inputBlockSize = 0;
        int outputBlockSize = 0;
        int length = array.length;
        byte[] array2;
        byte[] array3;
        if (this.m_firstEncryptionPacket) {
            length = length + this.m_symCipher.getKeys().length + this.m_mixParameters.getMixCipher().getPaddingSize();
            if (n > length) {
                array2 = new byte[n];
            }
            else {
                array2 = new byte[length];
            }
            final byte[] keys = this.m_symCipher.getKeys();
            keys[0] &= 0x7F;
            final byte[] currentReplayOffset = this.m_mixParameters.getCurrentReplayOffset((int)(System.currentTimeMillis() / 1000L - MixParameters.m_referenceTime));
            if (currentReplayOffset != null) {
                for (int i = 0; i < currentReplayOffset.length; ++i) {
                    keys[keys.length - currentReplayOffset.length + i] = currentReplayOffset[i];
                }
            }
            final ReplayTimestamp replayTimestamp = this.m_mixParameters.getReplayTimestamp();
            if (replayTimestamp != null) {
                final byte[] currentTimestamp = replayTimestamp.getCurrentTimestamp();
                System.arraycopy(currentTimestamp, 0, keys, keys.length - currentTimestamp.length, currentTimestamp.length);
            }
            this.m_symCipher.setEncryptionKeysAES(keys);
            System.arraycopy(this.m_symCipher.getKeys(), 0, array2, 0, this.m_symCipher.getKeys().length);
            System.arraycopy(array, 0, array2, this.m_symCipher.getKeys().length, array.length);
            array3 = new byte[array2.length];
            this.m_mixParameters.getMixCipher().encrypt(array2, 0, array3, 0);
            inputBlockSize = this.m_mixParameters.getMixCipher().getInputBlockSize();
            outputBlockSize = this.m_mixParameters.getMixCipher().getOutputBlockSize();
            this.m_firstEncryptionPacket = false;
        }
        else {
            if (n > length) {
                array2 = new byte[n];
            }
            else {
                array2 = new byte[length];
            }
            System.arraycopy(array, 0, array2, 0, array.length);
            array3 = new byte[array2.length];
        }
        this.m_symCipher.encryptAES1(array2, inputBlockSize, array3, outputBlockSize, array2.length - outputBlockSize);
        if (length < array3.length) {
            final byte[] array4 = array3;
            array3 = new byte[length];
            System.arraycopy(array4, 0, array3, 0, length);
        }
        return array3;
    }
    
    public void decrypt(final byte[] array) {
        this.m_symCipher.encryptAES2(array);
    }
    
    public int getNextPacketEncryptionOverhead() {
        int n = 0;
        if (this.m_firstEncryptionPacket) {
            n = this.m_symCipher.getKeys().length + this.m_mixParameters.getMixCipher().getPaddingSize();
        }
        return n;
    }
}
