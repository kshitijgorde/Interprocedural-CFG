// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.crypto;

public class ASymMixCipherRSAOAEP extends ASymMixCipherPlainRSA
{
    public int encrypt(final byte[] array, final int n, final byte[] array2, final int n2) {
        byte[] processBlockOAEP;
        try {
            processBlockOAEP = super.m_RSA.processBlockOAEP(array, n, 86);
        }
        catch (Exception ex) {
            return -1;
        }
        if (processBlockOAEP.length == 128) {
            System.arraycopy(processBlockOAEP, 0, array2, n2, 128);
        }
        else if (processBlockOAEP.length == 129) {
            System.arraycopy(processBlockOAEP, 1, array2, n2, 128);
        }
        else {
            for (int i = 0; i < 128 - processBlockOAEP.length; ++i) {
                array2[n2 + i] = 0;
            }
            System.arraycopy(processBlockOAEP, 0, array2, n2 + 128 - processBlockOAEP.length, processBlockOAEP.length);
        }
        return 128;
    }
    
    public int getPaddingSize() {
        return 42;
    }
    
    public int getInputBlockSize() {
        return 86;
    }
}
