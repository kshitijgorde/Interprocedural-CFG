// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.GCMBlockCipher;

public class ControlChannelCipher
{
    GCMBlockCipher m_sentEngine;
    GCMBlockCipher m_recvEngine;
    long m_EncMsgCounter;
    long m_DecMsgCounter;
    byte[] m_sentKey;
    byte[] m_recvKey;
    
    public ControlChannelCipher() {
        this.m_sentEngine = new GCMBlockCipher(new AESFastEngine());
        this.m_recvEngine = new GCMBlockCipher(new AESFastEngine());
        this.m_EncMsgCounter = 0L;
        this.m_DecMsgCounter = 0L;
    }
    
    public synchronized int setSentKey(final byte[] array, final int n, final int n2) {
        try {
            System.arraycopy(array, n, this.m_sentKey = new byte[16], 0, 16);
            this.m_EncMsgCounter = 0L;
            return 0;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    public synchronized int setRecvKey(final byte[] array, final int n, final int n2) {
        try {
            System.arraycopy(array, n, this.m_recvKey = new byte[16], 0, 16);
            this.m_DecMsgCounter = 0L;
            return 0;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    private byte[] createIV(final long n) {
        final byte[] array = new byte[12];
        for (int i = 0; i < 8; ++i) {
            array[i] = 0;
        }
        array[8] = (byte)(n >> 24 & 0xFFL);
        array[9] = (byte)(n >> 16 & 0xFFL);
        array[10] = (byte)(n >> 8 & 0xFFL);
        array[11] = (byte)(n & 0xFFL);
        return array;
    }
    
    public void encryptGCM1(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) throws Exception {
        final byte[] iv = this.createIV(this.m_EncMsgCounter);
        ++this.m_EncMsgCounter;
        this.m_sentEngine.init(true, new AEADParameters(new KeyParameter(this.m_sentKey), 128, iv, null));
        this.m_sentEngine.doFinal(array2, n2 + this.m_sentEngine.processBytes(array, n, n3, array2, n2));
    }
    
    public void decryptGCM2(final byte[] array, final int n, final byte[] array2, final int n2, final int n3) throws Exception {
        final byte[] iv = this.createIV(this.m_DecMsgCounter);
        ++this.m_DecMsgCounter;
        this.m_recvEngine.init(false, new AEADParameters(new KeyParameter(this.m_recvKey), 128, iv, null));
        this.m_recvEngine.doFinal(array2, n2 + this.m_recvEngine.processBytes(array, n, n3, array2, n2));
    }
    
    public int getEncryptedOutputSize(final int n) {
        return n + 16;
    }
    
    public int getDecryptedOutputSize(final int n) {
        return n - 16;
    }
}
