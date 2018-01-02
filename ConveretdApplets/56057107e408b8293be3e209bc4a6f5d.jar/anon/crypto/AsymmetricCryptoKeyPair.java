// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import logging.LogHolder;
import logging.LogType;
import anon.util.ClassUtil;
import java.security.Key;
import java.util.Enumeration;
import java.util.Random;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import java.security.InvalidKeyException;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import java.util.Vector;

public class AsymmetricCryptoKeyPair
{
    public static final int KEY_LENGTH_512 = 512;
    public static final int KEY_LENGTH_1024 = 1024;
    private static final MyDSAPrivateKey dsaKey;
    private static final MyRSAPrivateKey rsaKey;
    private static final MyECPrivateKey ecKey;
    private static Vector ms_privateKeyClasses;
    private static Vector ms_publicKeyClasses;
    private IMyPrivateKey m_privateKey;
    private IMyPublicKey m_publicKey;
    static /* synthetic */ Class class$anon$crypto$IMyPrivateKey;
    static /* synthetic */ Class class$anon$crypto$MyDSAPrivateKey;
    static /* synthetic */ Class class$anon$crypto$MyRSAPrivateKey;
    static /* synthetic */ Class class$anon$crypto$IMyPublicKey;
    static /* synthetic */ Class class$anon$crypto$MyDSAPublicKey;
    static /* synthetic */ Class class$anon$crypto$MyRSAPublicKey;
    
    public AsymmetricCryptoKeyPair(final IMyPrivateKey privateKey) {
        this.m_privateKey = privateKey;
        this.m_publicKey = privateKey.createPublicKey();
    }
    
    public AsymmetricCryptoKeyPair(final PrivateKeyInfo privateKeyInfo) throws InvalidKeyException {
        IMyPrivateKey privateKey;
        try {
            privateKey = (IMyPrivateKey)createAsymmetricCryptoKey(privateKeyInfo, getPrivateKeyClasses());
        }
        catch (ClassCastException ex) {
            throw new InvalidKeyException("The key that was created was no private key!");
        }
        this.m_privateKey = privateKey;
        this.m_publicKey = privateKey.createPublicKey();
    }
    
    public static final IMyPublicKey createPublicKey(final SubjectPublicKeyInfo subjectPublicKeyInfo) throws InvalidKeyException {
        IMyPublicKey myPublicKey;
        try {
            myPublicKey = (IMyPublicKey)createAsymmetricCryptoKey(subjectPublicKeyInfo, getPublicKeyClasses());
        }
        catch (ClassCastException ex) {
            throw new InvalidKeyException("The key that was created was no public key!");
        }
        return myPublicKey;
    }
    
    public final IMyPrivateKey getPrivate() {
        return this.m_privateKey;
    }
    
    public final IMyPublicKey getPublic() {
        return this.m_publicKey;
    }
    
    protected static final boolean isValidKeyPair(final AsymmetricCryptoKeyPair asymmetricCryptoKeyPair) {
        if (asymmetricCryptoKeyPair == null) {
            return false;
        }
        final Random random = new Random();
        random.setSeed(0L);
        final byte[] array = new byte[1024];
        random.nextBytes(array);
        final byte[] sign = ByteSignature.sign(array, asymmetricCryptoKeyPair);
        final byte[] array2 = new byte[sign.length];
        random.nextBytes(array2);
        try {
            if (ByteSignature.verify(array, array2, asymmetricCryptoKeyPair)) {
                return false;
            }
        }
        catch (Exception ex) {}
        return ByteSignature.verify(array, sign, asymmetricCryptoKeyPair);
    }
    
    private static Key createAsymmetricCryptoKey(final Object o, final Enumeration enumeration) throws InvalidKeyException {
        Key key = null;
        final Class[] array = { null };
        final Object[] array2 = { null };
        array[0] = o.getClass();
        array2[0] = o;
        while (key == null && enumeration.hasMoreElements()) {
            final Class<Key> clazz = enumeration.nextElement();
            try {
                key = clazz.getConstructor((Class<?>[])array).newInstance(array2);
            }
            catch (Throwable t) {}
        }
        if (key == null) {
            throw new InvalidKeyException("No key available for this key info!");
        }
        return key;
    }
    
    private static Enumeration getPrivateKeyClasses() {
        if (AsymmetricCryptoKeyPair.ms_privateKeyClasses == null) {
            try {
                (AsymmetricCryptoKeyPair.ms_privateKeyClasses = ClassUtil.findSubclasses((AsymmetricCryptoKeyPair.class$anon$crypto$IMyPrivateKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$IMyPrivateKey = class$("anon.crypto.IMyPrivateKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$IMyPrivateKey)).removeElement((AsymmetricCryptoKeyPair.class$anon$crypto$IMyPrivateKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$IMyPrivateKey = class$("anon.crypto.IMyPrivateKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$IMyPrivateKey);
            }
            catch (Throwable t) {
                LogHolder.log(2, LogType.CRYPTO, t);
            }
            if (AsymmetricCryptoKeyPair.ms_privateKeyClasses == null) {
                AsymmetricCryptoKeyPair.ms_privateKeyClasses = new Vector();
            }
            if (AsymmetricCryptoKeyPair.ms_privateKeyClasses.size() < 2) {
                LogHolder.log(2, LogType.CRYPTO, "Private key classes have not been loaded automatically!");
                AsymmetricCryptoKeyPair.ms_privateKeyClasses.removeElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPrivateKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPrivateKey = class$("anon.crypto.MyDSAPrivateKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPrivateKey);
                AsymmetricCryptoKeyPair.ms_privateKeyClasses.removeElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPrivateKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPrivateKey = class$("anon.crypto.MyRSAPrivateKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPrivateKey);
                AsymmetricCryptoKeyPair.ms_privateKeyClasses.addElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPrivateKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPrivateKey = class$("anon.crypto.MyDSAPrivateKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPrivateKey);
                AsymmetricCryptoKeyPair.ms_privateKeyClasses.addElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPrivateKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPrivateKey = class$("anon.crypto.MyRSAPrivateKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPrivateKey);
            }
        }
        return AsymmetricCryptoKeyPair.ms_privateKeyClasses.elements();
    }
    
    private static Enumeration getPublicKeyClasses() {
        if (AsymmetricCryptoKeyPair.ms_publicKeyClasses == null) {
            try {
                (AsymmetricCryptoKeyPair.ms_publicKeyClasses = ClassUtil.findSubclasses((AsymmetricCryptoKeyPair.class$anon$crypto$IMyPublicKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$IMyPublicKey = class$("anon.crypto.IMyPublicKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$IMyPublicKey)).removeElement((AsymmetricCryptoKeyPair.class$anon$crypto$IMyPublicKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$IMyPublicKey = class$("anon.crypto.IMyPublicKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$IMyPublicKey);
            }
            catch (Throwable t) {
                LogHolder.log(2, LogType.CRYPTO, t);
            }
            if (AsymmetricCryptoKeyPair.ms_publicKeyClasses == null) {
                AsymmetricCryptoKeyPair.ms_publicKeyClasses = new Vector();
            }
            if (AsymmetricCryptoKeyPair.ms_publicKeyClasses.size() < 2) {
                int n;
                if (ClassUtil.isFindSubclassesEnabled()) {
                    n = 2;
                }
                else {
                    n = 5;
                }
                LogHolder.log(n, LogType.CRYPTO, "Public key classes have not been loaded automatically!");
                AsymmetricCryptoKeyPair.ms_publicKeyClasses.removeElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPublicKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPublicKey = class$("anon.crypto.MyDSAPublicKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPublicKey);
                AsymmetricCryptoKeyPair.ms_publicKeyClasses.removeElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPublicKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPublicKey = class$("anon.crypto.MyRSAPublicKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPublicKey);
                AsymmetricCryptoKeyPair.ms_publicKeyClasses.addElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPublicKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPublicKey = class$("anon.crypto.MyDSAPublicKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyDSAPublicKey);
                AsymmetricCryptoKeyPair.ms_publicKeyClasses.addElement((AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPublicKey == null) ? (AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPublicKey = class$("anon.crypto.MyRSAPublicKey")) : AsymmetricCryptoKeyPair.class$anon$crypto$MyRSAPublicKey);
            }
        }
        return AsymmetricCryptoKeyPair.ms_publicKeyClasses.elements();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        dsaKey = null;
        rsaKey = null;
        ecKey = null;
    }
}
