// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.ciphersuites;

import anon.util.ByteArrayUtil;
import anon.crypto.tinytls.TLSPlaintextRecord;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import java.math.BigInteger;
import anon.crypto.tinytls.TLSException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import anon.crypto.MyRandom;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import anon.crypto.JAPCertificate;
import anon.crypto.tinytls.keyexchange.Key_Exchange;

public abstract class CipherSuite
{
    private byte[] m_ciphersuitecode;
    protected String m_ciphersuitename;
    private Key_Exchange m_keyexchangealgorithm;
    private JAPCertificate m_servercertificate;
    protected CBCBlockCipher m_decryptcipher;
    protected CBCBlockCipher m_encryptcipher;
    private HMac m_hmacInput;
    private HMac m_hmacOutput;
    private MyRandom m_Random;
    protected long m_writesequenznumber;
    protected long m_readsequenznumber;
    protected byte[] m_clientwritekey;
    protected byte[] m_clientmacsecret;
    protected byte[] m_clientwriteIV;
    protected byte[] m_serverwritekey;
    protected byte[] m_servermacsecret;
    protected byte[] m_serverwriteIV;
    
    public CipherSuite(final byte[] ciphersuitecode) throws TLSException {
        this.m_ciphersuitename = "Name not set";
        this.m_keyexchangealgorithm = null;
        this.m_servercertificate = null;
        this.m_hmacInput = new HMac(new SHA1Digest());
        this.m_hmacOutput = new HMac(new SHA1Digest());
        this.m_clientwritekey = null;
        this.m_clientmacsecret = null;
        this.m_clientwriteIV = null;
        this.m_serverwritekey = null;
        this.m_servermacsecret = null;
        this.m_serverwriteIV = null;
        if (ciphersuitecode.length != 2) {
            throw new TLSException("wrong CipherSuiteCode ");
        }
        this.m_ciphersuitecode = ciphersuitecode;
        this.m_writesequenznumber = 0L;
        this.m_readsequenznumber = 0L;
        this.m_Random = new MyRandom();
    }
    
    protected void setKeyExchangeAlgorithm(final Key_Exchange keyexchangealgorithm) {
        this.m_keyexchangealgorithm = keyexchangealgorithm;
    }
    
    public Key_Exchange getKeyExchangeAlgorithm() {
        return this.m_keyexchangealgorithm;
    }
    
    public void setServerCertificate(final JAPCertificate servercertificate) {
        this.m_servercertificate = servercertificate;
    }
    
    public byte[] getCipherSuiteCode() {
        return this.m_ciphersuitecode;
    }
    
    public void processClientKeyExchange(final BigInteger bigInteger) {
        this.m_keyexchangealgorithm.processClientKeyExchange(bigInteger);
        this.calculateKeys(this.m_keyexchangealgorithm.calculateKeys(), false);
        this.m_hmacInput.init(new KeyParameter(this.m_servermacsecret));
        this.m_hmacOutput.init(new KeyParameter(this.m_clientmacsecret));
    }
    
    public byte[] calculateClientKeyExchange() throws TLSException {
        final byte[] calculateClientKeyExchange = this.m_keyexchangealgorithm.calculateClientKeyExchange();
        this.calculateKeys(this.m_keyexchangealgorithm.calculateKeys(), true);
        this.m_hmacInput.init(new KeyParameter(this.m_servermacsecret));
        this.m_hmacOutput.init(new KeyParameter(this.m_clientmacsecret));
        return calculateClientKeyExchange;
    }
    
    public void processServerFinished(final TLSPlaintextRecord tlsPlaintextRecord, final byte[] array) throws TLSException {
        this.decode(tlsPlaintextRecord);
        this.m_keyexchangealgorithm.processServerFinished(tlsPlaintextRecord.getData(), tlsPlaintextRecord.getLength(), array);
    }
    
    public void encode(final TLSPlaintextRecord tlsPlaintextRecord) {
        final int length = tlsPlaintextRecord.getLength();
        final byte[] data = tlsPlaintextRecord.getData();
        final byte[] header = tlsPlaintextRecord.getHeader();
        this.m_hmacOutput.reset();
        this.m_hmacOutput.update(ByteArrayUtil.inttobyte(this.m_writesequenznumber, 8), 0, 8);
        ++this.m_writesequenznumber;
        this.m_hmacOutput.update(header, 0, header.length);
        this.m_hmacOutput.update(data, 0, length);
        this.m_hmacOutput.doFinal(data, length);
        int length2 = length + this.m_hmacOutput.getMacSize();
        final int nextInt = this.m_Random.nextInt(240);
        for (int n = nextInt + (this.m_encryptcipher.getBlockSize() - (length2 + 1 + nextInt) % this.m_encryptcipher.getBlockSize()), i = 0; i < n + 1; ++i) {
            data[length2++] = (byte)n;
        }
        for (int j = 0; j < length2; j += this.m_encryptcipher.getBlockSize()) {
            this.m_encryptcipher.processBlock(data, j, data, j);
        }
        tlsPlaintextRecord.setLength(length2);
    }
    
    public void decode(final TLSPlaintextRecord tlsPlaintextRecord) throws TLSException {
        final int length = tlsPlaintextRecord.getLength();
        final byte[] data = tlsPlaintextRecord.getData();
        if (length % this.m_decryptcipher.getBlockSize() != 0 || length < this.m_hmacInput.getMacSize()) {
            throw new TLSException("wrong payload len!");
        }
        for (int i = 0; i < length; i += this.m_decryptcipher.getBlockSize()) {
            this.m_decryptcipher.processBlock(data, i, data, i);
        }
        final int n = length - this.m_hmacInput.getMacSize() - 1;
        final byte b = data[length - 1];
        final int n2 = b & 0xFF;
        if (n2 > length - 2) {
            throw new TLSException("wrong Padding len detected", 2, 51);
        }
        for (int j = length - 1; j > length - n2 - 2; --j) {
            if (data[j] != b) {
                throw new TLSException("wrong Padding detected", 2, 51);
            }
        }
        final int length2 = n - n2;
        tlsPlaintextRecord.setLength(length2);
        this.m_hmacInput.reset();
        this.m_hmacInput.update(ByteArrayUtil.inttobyte(this.m_readsequenznumber, 8), 0, 8);
        ++this.m_readsequenznumber;
        final byte[] header = tlsPlaintextRecord.getHeader();
        this.m_hmacInput.update(header, 0, header.length);
        this.m_hmacInput.update(data, 0, length2);
        final byte[] array = new byte[this.m_hmacInput.getMacSize()];
        this.m_hmacInput.doFinal(array, 0);
        if (!ByteArrayUtil.equal(data, length2, array, 0, array.length)) {
            throw new TLSException("Wrong MAC detected!!!", 2, 20);
        }
    }
    
    protected abstract void calculateKeys(final byte[] p0, final boolean p1);
    
    public String toString() {
        return this.m_ciphersuitename;
    }
}
