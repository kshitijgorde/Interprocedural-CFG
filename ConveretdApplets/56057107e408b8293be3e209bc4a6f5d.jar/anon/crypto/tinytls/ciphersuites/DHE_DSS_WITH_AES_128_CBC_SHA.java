// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.ciphersuites;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import anon.util.ByteArrayUtil;
import anon.crypto.tinytls.TLSException;
import anon.crypto.tinytls.keyexchange.Key_Exchange;
import anon.crypto.tinytls.keyexchange.DHE_DSS_Key_Exchange;

public class DHE_DSS_WITH_AES_128_CBC_SHA extends CipherSuite
{
    public DHE_DSS_WITH_AES_128_CBC_SHA() throws TLSException {
        super(new byte[] { 0, 50 });
        super.m_ciphersuitename = "TLS_DHE_DSS_WITH_AES_128_CBC_SHA";
        this.setKeyExchangeAlgorithm(new DHE_DSS_Key_Exchange());
    }
    
    protected void calculateKeys(final byte[] array, final boolean b) {
        super.m_clientwritekey = ByteArrayUtil.copy(array, 40, 16);
        super.m_serverwritekey = ByteArrayUtil.copy(array, 56, 16);
        super.m_clientwriteIV = ByteArrayUtil.copy(array, 72, 16);
        super.m_serverwriteIV = ByteArrayUtil.copy(array, 88, 16);
        if (b) {
            super.m_clientmacsecret = ByteArrayUtil.copy(array, 0, 20);
            super.m_servermacsecret = ByteArrayUtil.copy(array, 20, 20);
            (super.m_encryptcipher = new CBCBlockCipher(new AESFastEngine())).init(true, new ParametersWithIV(new KeyParameter(super.m_clientwritekey), super.m_clientwriteIV));
            (super.m_decryptcipher = new CBCBlockCipher(new AESFastEngine())).init(false, new ParametersWithIV(new KeyParameter(super.m_serverwritekey), super.m_serverwriteIV));
        }
        else {
            super.m_servermacsecret = ByteArrayUtil.copy(array, 0, 20);
            super.m_clientmacsecret = ByteArrayUtil.copy(array, 20, 20);
            (super.m_encryptcipher = new CBCBlockCipher(new AESFastEngine())).init(true, new ParametersWithIV(new KeyParameter(super.m_serverwritekey), super.m_serverwriteIV));
            (super.m_decryptcipher = new CBCBlockCipher(new AESFastEngine())).init(false, new ParametersWithIV(new KeyParameter(super.m_clientwritekey), super.m_clientwriteIV));
        }
    }
}
