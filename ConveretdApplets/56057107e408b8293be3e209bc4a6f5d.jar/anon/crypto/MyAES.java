// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.engines.AESFastEngine;

public class MyAES
{
    private AESFastEngine m_AES;
    private byte[] m_arCounter;
    private byte[] m_arCounterOut;
    private int m_posCTR;
    
    public MyAES() {
        this.m_AES = new AESFastEngine();
        this.m_arCounter = null;
        this.m_arCounterOut = null;
        this.m_posCTR = 0;
    }
    
    public synchronized void init(final boolean b, final byte[] array) throws Exception {
        this.init(b, array, 0, array.length);
    }
    
    public synchronized void init(final boolean b, final byte[] array, final int n, final int n2) throws Exception {
        this.m_AES.init(b, new KeyParameter(array, n, n2));
        this.m_arCounter = null;
        this.m_arCounterOut = null;
        this.m_posCTR = 0;
    }
    
    public synchronized void processBlockECB(final byte[] array, final byte[] array2) throws Exception {
        this.m_AES.processBlock(array, 0, array2, 0);
    }
    
    public synchronized byte[] processBlockECB(final byte[] array) throws Exception {
        final byte[] array2 = new byte[16];
        this.m_AES.processBlock(array, 0, array2, 0);
        return array2;
    }
    
    public void processBytesCTR(final byte[] array, int n, final byte[] array2, int n2, int i) throws Exception {
        if (this.m_arCounterOut == null) {
            this.m_arCounterOut = new byte[16];
            this.m_posCTR = 0;
            this.m_arCounter = new byte[16];
        }
        while (i > 0) {
            if (this.m_posCTR == 0) {
                this.processBlockECB(this.m_arCounter, this.m_arCounterOut);
            }
            while (this.m_posCTR < this.m_arCounterOut.length) {
                array2[n2] = (byte)(this.m_arCounterOut[this.m_posCTR] ^ array[n]);
                ++n2;
                ++n;
                --i;
                ++this.m_posCTR;
                if (i == 0) {
                    return;
                }
            }
            this.m_posCTR = 0;
            int n3 = 1;
            for (int j = this.m_arCounter.length - 1; j >= 0; --j) {
                final int n4 = (this.m_arCounter[j] & 0xFF) + n3;
                if (n4 > 255) {
                    n3 = 1;
                }
                else {
                    n3 = 0;
                }
                this.m_arCounter[j] = (byte)n4;
            }
        }
    }
}
