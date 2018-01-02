// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.RSAEngine;

public class MyRSA
{
    RSAEngine m_RSAEngine;
    OAEPEncoding m_OAEP;
    PKCS1Encoding m_PKCS1;
    
    public MyRSA() {
        this.m_RSAEngine = new RSAEngine();
        this.m_OAEP = new OAEPEncoding(this.m_RSAEngine);
        this.m_PKCS1 = new PKCS1Encoding(this.m_RSAEngine);
    }
    
    public MyRSA(final Digest digest) {
        this.m_RSAEngine = new RSAEngine();
        this.m_OAEP = new OAEPEncoding(this.m_RSAEngine, digest);
    }
    
    public void init(final MyRSAPublicKey myRSAPublicKey) throws Exception {
        synchronized (this.m_RSAEngine) {
            this.m_RSAEngine.init(true, myRSAPublicKey.getParams());
            this.m_PKCS1.init(true, myRSAPublicKey.getParams());
            this.m_OAEP.init(true, myRSAPublicKey.getParams());
        }
    }
    
    public void init(final MyRSAPrivateKey myRSAPrivateKey) throws Exception {
        synchronized (this.m_RSAEngine) {
            this.m_RSAEngine.init(false, myRSAPrivateKey.getParams());
            this.m_PKCS1.init(false, myRSAPrivateKey.getParams());
            this.m_OAEP.init(false, myRSAPrivateKey.getParams());
        }
    }
    
    public byte[] processBlock(final byte[] array, final int n, final int n2) throws Exception {
        synchronized (this.m_RSAEngine) {
            return this.m_RSAEngine.processBlock(array, n, n2);
        }
    }
    
    public byte[] processBlockOAEP(final byte[] array, final int n, final int n2) throws Exception {
        synchronized (this.m_RSAEngine) {
            return this.m_OAEP.encodeBlock(array, n, n2);
        }
    }
    
    public byte[] processBlockPKCS1(final byte[] array, final int n, final int n2) throws Exception {
        synchronized (this.m_RSAEngine) {
            return this.m_PKCS1.processBlock(array, n, n2);
        }
    }
}
