// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto.tinytls.keyexchange;

import anon.crypto.tinytls.util.PRF;
import anon.crypto.tinytls.util.hash;
import anon.crypto.IMyPublicKey;
import anon.crypto.MyDSAPublicKey;
import logging.LogHolder;
import logging.LogType;
import anon.crypto.JAPCertificate;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import anon.crypto.MyDSASignature;
import anon.util.ByteArrayUtil;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.DHKeyPairGenerator;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import java.security.SecureRandom;
import anon.crypto.tinytls.TLSException;
import anon.crypto.MyDSAPrivateKey;
import anon.crypto.IMyPrivateKey;
import org.bouncycastle.crypto.agreement.DHBasicAgreement;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.params.DHParameters;
import java.math.BigInteger;

public class DHE_DSS_Key_Exchange extends Key_Exchange
{
    private static final int MAXKEYMATERIALLENGTH = 104;
    private static final byte[] CLIENTFINISHEDLABEL;
    private static final byte[] SERVERFINISHEDLABEL;
    private static final byte[] KEYEXPANSION;
    private static final byte[] MASTERSECRET;
    private static final BigInteger SAFEPRIME;
    private static final DHParameters DH_PARAMS;
    private DHParameters m_dhparams;
    private DHPublicKeyParameters m_dhserverpub;
    private byte[] m_premastersecret;
    private byte[] m_mastersecret;
    private byte[] m_clientrandom;
    private byte[] m_serverrandom;
    private DHBasicAgreement m_dhe;
    
    public DHE_DSS_Key_Exchange() {
        this.m_dhe = null;
    }
    
    public byte[] generateServerKeyExchange(final IMyPrivateKey myPrivateKey, final byte[] clientrandom, final byte[] serverrandom) throws TLSException {
        if (!(myPrivateKey instanceof MyDSAPrivateKey)) {
            throw new TLSException("wrong key type (cannot cast to MyDSAPrivateKey)");
        }
        final MyDSAPrivateKey myDSAPrivateKey = (MyDSAPrivateKey)myPrivateKey;
        this.m_clientrandom = clientrandom;
        this.m_serverrandom = serverrandom;
        final DHKeyGenerationParameters dhKeyGenerationParameters = new DHKeyGenerationParameters(new SecureRandom(), DHE_DSS_Key_Exchange.DH_PARAMS);
        final DHKeyPairGenerator dhKeyPairGenerator = new DHKeyPairGenerator();
        dhKeyPairGenerator.init(dhKeyGenerationParameters);
        final AsymmetricCipherKeyPair generateKeyPair = dhKeyPairGenerator.generateKeyPair();
        final DHPublicKeyParameters dhPublicKeyParameters = (DHPublicKeyParameters)generateKeyPair.getPublic();
        (this.m_dhe = new DHBasicAgreement()).init(generateKeyPair.getPrivate());
        final byte[] byteArray = dhPublicKeyParameters.getParameters().getP().toByteArray();
        final byte[] conc = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(byteArray.length, 2), byteArray);
        final byte[] byteArray2 = dhPublicKeyParameters.getParameters().getG().toByteArray();
        final byte[] conc2 = ByteArrayUtil.conc(ByteArrayUtil.inttobyte(byteArray2.length, 2), byteArray2);
        final byte[] byteArray3 = dhPublicKeyParameters.getY().toByteArray();
        final byte[] conc3 = ByteArrayUtil.conc(conc, conc2, ByteArrayUtil.conc(ByteArrayUtil.inttobyte(byteArray3.length, 2), byteArray3));
        final byte[] conc4 = ByteArrayUtil.conc(clientrandom, serverrandom, conc3);
        final MyDSASignature myDSASignature = new MyDSASignature();
        try {
            myDSASignature.initSign(myDSAPrivateKey);
        }
        catch (Exception ex) {
            throw new TLSException("wrong key type (cannot init signature algorithm (" + ex.getMessage() + "))");
        }
        final byte[] sign = myDSASignature.sign(conc4);
        return ByteArrayUtil.conc(conc3, ByteArrayUtil.inttobyte(sign.length, 2), sign);
    }
    
    public void processServerKeyExchange(final byte[] array, int n, final int n2, final byte[] clientrandom, final byte[] serverrandom, final JAPCertificate japCertificate) throws TLSException {
        this.m_clientrandom = clientrandom;
        this.m_serverrandom = serverrandom;
        final int n3 = n;
        final int n4 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        final byte[] copy = ByteArrayUtil.copy(array, n, n4);
        n += n4;
        final BigInteger bigInteger = new BigInteger(1, copy);
        LogHolder.log(7, LogType.MISC, "[SERVER_KEY_EXCHANGE] DH_P = " + bigInteger.toString());
        final int n5 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        final byte[] copy2 = ByteArrayUtil.copy(array, n, n5);
        n += n5;
        final BigInteger bigInteger2 = new BigInteger(1, copy2);
        LogHolder.log(7, LogType.MISC, "[SERVER_KEY_EXCHANGE] DH_G = " + bigInteger2.toString());
        final int n6 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        final byte[] copy3 = ByteArrayUtil.copy(array, n, n6);
        n += n6;
        final BigInteger bigInteger3 = new BigInteger(1, copy3);
        LogHolder.log(7, LogType.MISC, "[SERVER_KEY_EXCHANGE] DH_Ys = " + bigInteger3.toString());
        this.m_dhparams = new DHParameters(bigInteger, bigInteger2);
        this.m_dhserverpub = new DHPublicKeyParameters(bigInteger3, this.m_dhparams);
        final byte[] conc = ByteArrayUtil.conc(clientrandom, serverrandom, ByteArrayUtil.copy(array, n3, n - n3));
        final int n7 = (array[n] & 0xFF) << 8 | (array[n + 1] & 0xFF);
        n += 2;
        final MyDSASignature myDSASignature = new MyDSASignature();
        if (!(japCertificate.getPublicKey() instanceof MyDSAPublicKey)) {
            throw new TLSException("cannot decode certificate");
        }
        final MyDSAPublicKey myDSAPublicKey = (MyDSAPublicKey)japCertificate.getPublicKey();
        try {
            myDSASignature.initVerify(myDSAPublicKey);
        }
        catch (Exception ex) {}
        if (!myDSASignature.verify(conc, 0, conc.length, array, n, n7)) {
            LogHolder.log(7, LogType.MISC, "[SERVER_KEY_EXCHANGE] Signature wrong");
            throw new TLSException("wrong Signature", 2, 21);
        }
        LogHolder.log(7, LogType.MISC, "[SERVER_KEY_EXCHANGE] Signature ok");
    }
    
    public byte[] calculateServerFinished(final byte[] array) {
        return new PRF(this.m_mastersecret, DHE_DSS_Key_Exchange.SERVERFINISHEDLABEL, ByteArrayUtil.conc(hash.md5(array), hash.sha(array))).calculate(12);
    }
    
    public void processServerFinished(final byte[] array, final int n, final byte[] array2) throws TLSException {
        final byte[] calculate = new PRF(this.m_mastersecret, DHE_DSS_Key_Exchange.SERVERFINISHEDLABEL, ByteArrayUtil.conc(hash.md5(array2), hash.sha(array2))).calculate(12);
        if (array[0] == 20 && array[1] == 0 && array[2] == 0 && array[3] == 12) {
            for (int i = 0; i < calculate.length; ++i) {
                if (calculate[i] != array[i + 4]) {
                    throw new TLSException("wrong Server Finished message recieved", 2, 20);
                }
            }
            return;
        }
        throw new TLSException("wrong Server Finished message recieved", 2, 10);
    }
    
    public void processClientKeyExchange(final BigInteger bigInteger) {
        this.m_premastersecret = this.m_dhe.calculateAgreement(new DHPublicKeyParameters(bigInteger, DHE_DSS_Key_Exchange.DH_PARAMS)).toByteArray();
        if (this.m_premastersecret[0] == 0) {
            this.m_premastersecret = ByteArrayUtil.copy(this.m_premastersecret, 1, this.m_premastersecret.length - 1);
        }
        this.m_mastersecret = new PRF(this.m_premastersecret, DHE_DSS_Key_Exchange.MASTERSECRET, ByteArrayUtil.conc(this.m_clientrandom, this.m_serverrandom)).calculate(48);
        this.m_premastersecret = null;
    }
    
    public byte[] calculateClientKeyExchange() throws TLSException {
        final DHKeyGenerationParameters dhKeyGenerationParameters = new DHKeyGenerationParameters(new SecureRandom(), this.m_dhparams);
        final DHKeyPairGenerator dhKeyPairGenerator = new DHKeyPairGenerator();
        dhKeyPairGenerator.init(dhKeyGenerationParameters);
        final AsymmetricCipherKeyPair generateKeyPair = dhKeyPairGenerator.generateKeyPair();
        final DHPublicKeyParameters dhPublicKeyParameters = (DHPublicKeyParameters)generateKeyPair.getPublic();
        final DHPrivateKeyParameters dhPrivateKeyParameters = (DHPrivateKeyParameters)generateKeyPair.getPrivate();
        final DHBasicAgreement dhBasicAgreement = new DHBasicAgreement();
        dhBasicAgreement.init(dhPrivateKeyParameters);
        this.m_premastersecret = dhBasicAgreement.calculateAgreement(this.m_dhserverpub).toByteArray();
        if (this.m_premastersecret[0] == 0) {
            this.m_premastersecret = ByteArrayUtil.copy(this.m_premastersecret, 1, this.m_premastersecret.length - 1);
        }
        this.m_mastersecret = new PRF(this.m_premastersecret, DHE_DSS_Key_Exchange.MASTERSECRET, ByteArrayUtil.conc(this.m_clientrandom, this.m_serverrandom)).calculate(48);
        this.m_premastersecret = null;
        return dhPublicKeyParameters.getY().toByteArray();
    }
    
    public void processClientFinished(final byte[] array, final byte[] array2) throws TLSException {
        final PRF prf = new PRF(this.m_mastersecret, DHE_DSS_Key_Exchange.CLIENTFINISHEDLABEL, ByteArrayUtil.conc(hash.md5(array2), hash.sha(array2)));
    }
    
    public byte[] calculateClientFinished(final byte[] array) throws TLSException {
        return new PRF(this.m_mastersecret, DHE_DSS_Key_Exchange.CLIENTFINISHEDLABEL, ByteArrayUtil.conc(hash.md5(array), hash.sha(array))).calculate(12);
    }
    
    public byte[] calculateKeys() {
        return new PRF(this.m_mastersecret, DHE_DSS_Key_Exchange.KEYEXPANSION, ByteArrayUtil.conc(this.m_serverrandom, this.m_clientrandom)).calculate(104);
    }
    
    static {
        CLIENTFINISHEDLABEL = "client finished".getBytes();
        SERVERFINISHEDLABEL = "server finished".getBytes();
        KEYEXPANSION = "key expansion".getBytes();
        MASTERSECRET = "master secret".getBytes();
        SAFEPRIME = new BigInteger("00FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE65381FFFFFFFFFFFFFFFF", 16);
        DH_PARAMS = new DHParameters(DHE_DSS_Key_Exchange.SAFEPRIME, new BigInteger("2"));
    }
}
