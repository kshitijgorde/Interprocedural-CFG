// 
// Decompiled by Procyon v0.5.30
// 

package anon.tor;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import anon.crypto.MyRSA;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.DHKeyPairGenerator;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import java.security.SecureRandom;
import logging.LogHolder;
import logging.LogType;
import org.bouncycastle.crypto.InvalidCipherTextException;
import anon.crypto.MyRSAPublicKey;
import anon.crypto.tinytls.util.hash;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import anon.util.ByteArrayUtil;
import java.net.InetAddress;
import anon.tor.cells.Cell;
import anon.tor.cells.CreateCell;
import anon.tor.cells.RelayCell;
import java.io.IOException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import anon.crypto.MyAES;
import org.bouncycastle.crypto.agreement.DHBasicAgreement;
import anon.tor.ordescription.ORDescriptor;
import org.bouncycastle.crypto.params.DHParameters;
import java.math.BigInteger;

public class OnionRouter
{
    private static final BigInteger SAFEPRIME;
    private static final BigInteger MINKEY;
    private static final BigInteger MAXKEY;
    private static final DHParameters DH_PARAMS;
    private ORDescriptor m_description;
    private DHBasicAgreement m_dhe;
    private MyAES m_encryptionEngine;
    private MyAES m_decryptionEngine;
    private OnionRouter m_nextOR;
    private int m_circID;
    private SHA1Digest m_digestDf;
    private SHA1Digest m_digestDb;
    private boolean m_extended;
    
    public OnionRouter(final int circID, final ORDescriptor description) throws IOException {
        this.m_description = description;
        this.m_circID = circID;
        this.m_nextOR = null;
        this.m_extended = false;
    }
    
    public ORDescriptor getDescription() {
        return this.m_description;
    }
    
    public synchronized RelayCell encryptCell(RelayCell encryptCell) throws Exception {
        if (this.m_nextOR != null) {
            encryptCell = this.m_nextOR.encryptCell(encryptCell);
        }
        else {
            encryptCell.generateDigest(this.m_digestDf);
        }
        encryptCell.doCryptography(this.m_encryptionEngine);
        return encryptCell;
    }
    
    public synchronized RelayCell decryptCell(final RelayCell relayCell) throws Exception {
        RelayCell decryptCell = relayCell;
        decryptCell.doCryptography(this.m_decryptionEngine);
        if (this.m_nextOR != null && this.m_extended) {
            decryptCell = this.m_nextOR.decryptCell(decryptCell);
        }
        else {
            decryptCell.checkDigest(this.m_digestDb);
        }
        return decryptCell;
    }
    
    public CreateCell createConnection() throws Exception {
        final CreateCell createCell = new CreateCell(this.m_circID);
        createCell.setPayload(this.createExtendOnionSkin(), 0);
        return createCell;
    }
    
    public boolean checkCreatedCell(final Cell cell) {
        try {
            this.checkExtendParameters(cell.getPayload(), 0, 148);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private RelayCell extendConnection(final String s, final int n) throws IOException, InvalidCipherTextException, Exception {
        final byte[] conc = ByteArrayUtil.conc(InetAddress.getByName(s).getAddress(), ByteArrayUtil.inttobyte(n, 2), this.createExtendOnionSkin());
        final MyRSAPublicKey signingKey = this.m_description.getSigningKey();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
        derOutputStream.writeObject(signingKey.getAsSubjectPublicKeyInfo().getPublicKey());
        derOutputStream.flush();
        return new RelayCell(this.m_circID, (byte)6, 0, ByteArrayUtil.conc(conc, hash.sha(byteArrayOutputStream.toByteArray())));
    }
    
    public RelayCell extendConnection(final ORDescriptor orDescriptor) throws IOException, InvalidCipherTextException, Exception {
        RelayCell relayCell;
        if (this.m_nextOR == null) {
            this.m_nextOR = new OnionRouter(this.m_circID, orDescriptor);
            relayCell = this.m_nextOR.extendConnection(orDescriptor.getAddress(), orDescriptor.getPort());
            relayCell.generateDigest(this.m_digestDf);
        }
        else {
            relayCell = this.m_nextOR.extendConnection(orDescriptor);
        }
        relayCell.doCryptography(this.m_encryptionEngine);
        return relayCell;
    }
    
    public boolean checkExtendedCell(final RelayCell relayCell) {
        try {
            if (this.m_nextOR == null) {
                this.checkExtendParameters(relayCell.getPayload(), 11, 148);
                LogHolder.log(7, LogType.MISC, "[TOR] Circuit '" + this.m_circID + "' Extended");
                return true;
            }
            relayCell.doCryptography(this.m_decryptionEngine);
            if (!this.m_extended) {
                relayCell.checkDigest(this.m_digestDb);
                if (!(this.m_extended = this.m_nextOR.checkExtendedCell(relayCell))) {
                    this.m_nextOR = null;
                }
                return this.m_extended;
            }
            return this.m_nextOR.checkExtendedCell(relayCell);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private byte[] createExtendOnionSkin() throws IOException, InvalidCipherTextException, Exception {
        final byte[] array = new byte[86];
        final byte[] array2 = new byte[16];
        final MyAES myAES = new MyAES();
        new SecureRandom().nextBytes(array2);
        myAES.init(true, array2);
        final DHKeyGenerationParameters dhKeyGenerationParameters = new DHKeyGenerationParameters(new SecureRandom(), OnionRouter.DH_PARAMS);
        final DHKeyPairGenerator dhKeyPairGenerator = new DHKeyPairGenerator();
        dhKeyPairGenerator.init(dhKeyGenerationParameters);
        final AsymmetricCipherKeyPair generateKeyPair = dhKeyPairGenerator.generateKeyPair();
        final DHPublicKeyParameters dhPublicKeyParameters = (DHPublicKeyParameters)generateKeyPair.getPublic();
        (this.m_dhe = new DHBasicAgreement()).init(generateKeyPair.getPrivate());
        final byte[] byteArray = dhPublicKeyParameters.getY().toByteArray();
        int n = 0;
        if (byteArray[0] == 0) {
            n = 1;
        }
        System.arraycopy(array2, 0, array, 0, 16);
        System.arraycopy(byteArray, n, array, 16, 70);
        final MyRSA myRSA = new MyRSA();
        myRSA.init(this.m_description.getOnionKey());
        final byte[] processBlockOAEP = myRSA.processBlockOAEP(array, 0, array.length);
        final byte[] array3 = new byte[186];
        System.arraycopy(processBlockOAEP, 0, array3, 0, 128);
        myAES.processBytesCTR(byteArray, 70 + n, array3, 128, 58);
        return array3;
    }
    
    private void checkExtendParameters(final byte[] array, final int n, final int n2) throws Exception {
        final byte[] array2 = new byte[128];
        System.arraycopy(array, n, array2, 0, 128);
        final BigInteger calculateAgreement = this.m_dhe.calculateAgreement(new DHPublicKeyParameters(new BigInteger(1, array2), OnionRouter.DH_PARAMS));
        final byte[] byteArray = calculateAgreement.toByteArray();
        final byte[] array3 = new byte[129];
        if (byteArray[0] == 0) {
            System.arraycopy(byteArray, 1, array3, 0, 128);
        }
        else {
            System.arraycopy(byteArray, 0, array3, 0, 128);
        }
        final byte[] sha = hash.sha(array3);
        for (int i = 0; i < sha.length; ++i) {
            if (sha[i] != array[i + n + 128]) {
                throw new Exception("wrong derivative key");
            }
        }
        if (calculateAgreement.compareTo(OnionRouter.MINKEY) == -1 || calculateAgreement.compareTo(OnionRouter.MAXKEY) == 1) {
            throw new CryptoException("Calculated DH-Key is not in allowed range (KEY:" + calculateAgreement.doubleValue() + ")");
        }
        if (calculateAgreement.bitCount() < 16 || 1024 - calculateAgreement.bitCount() < 16) {
            throw new CryptoException("Calculated DH-Key is not valid. Not enough zeros ore ones");
        }
        array3[128] = 1;
        (this.m_digestDf = new SHA1Digest()).update(hash.sha(array3), 0, 20);
        array3[128] = 2;
        (this.m_digestDb = new SHA1Digest()).update(hash.sha(array3), 0, 20);
        array3[128] = 3;
        final byte[] sha2 = hash.sha(array3);
        (this.m_encryptionEngine = new MyAES()).init(true, sha2, 0, 16);
        final byte[] array4 = new byte[16];
        System.arraycopy(sha2, 16, array4, 0, 4);
        array3[128] = 4;
        System.arraycopy(hash.sha(array3), 0, array4, 4, 12);
        (this.m_decryptionEngine = new MyAES()).init(true, array4);
    }
    
    static {
        SAFEPRIME = new BigInteger("00FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE65381FFFFFFFFFFFFFFFF", 16);
        MINKEY = new BigInteger(new byte[] { 1, 0, 0, 0 });
        MAXKEY = OnionRouter.SAFEPRIME.subtract(OnionRouter.MINKEY);
        DH_PARAMS = new DHParameters(OnionRouter.SAFEPRIME, new BigInteger("2"));
    }
}
