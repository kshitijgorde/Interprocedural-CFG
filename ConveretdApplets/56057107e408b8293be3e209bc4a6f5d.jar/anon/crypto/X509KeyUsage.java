// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.DERBitString;
import java.util.Vector;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.DERSequence;

public class X509KeyUsage extends AbstractX509Extension
{
    public static final String IDENTIFIER;
    public static final int DIGITAL_SIGNATURE = 128;
    public static final int NON_REPUDIATION = 64;
    public static final int KEY_ENCIPHERMENT = 32;
    public static final int DATA_ENCIPHERMENT = 16;
    public static final int KEY_AGREEMENT = 8;
    public static final int KEY_CERT_SIGN = 4;
    public static final int CRL_SIGN = 2;
    public static final int ENCIPHER_ONLY = 1;
    public static final int DECIPHER_ONLY = 32768;
    private static final String TXT_DIGITAL_SIGNATURE = "digitalSignature";
    private static final String TXT_NON_REPUDIATION = "nonRepudiation/contentCommitment";
    private static final String TXT_KEY_ENCIPHERMENT = "keyEncipherment";
    private static final String TXT_DATA_ENCIPHERMENT = "dataEncipherment";
    private static final String TXT_KEY_AGREEMENT = "keyAgreement";
    private static final String TXT_KEY_CERT_SIGN = "keyCertSign";
    private static final String TXT_CRL_SIGN = "cRLSign";
    private static final String TXT_ENCIPHER_ONLY = "encipherOnly";
    private static final String TXT_DECIPHER_ONLY = "decipherOnly";
    private static final int[] USAGES;
    private int m_usage;
    
    public X509KeyUsage(final int usage) {
        super(X509KeyUsage.IDENTIFIER, true, createDEROctet(usage));
        this.m_usage = usage;
    }
    
    public X509KeyUsage(final DERSequence derSequence) {
        super(derSequence);
        this.createValue();
    }
    
    private static byte[] createDEROctet(final int n) {
        return new KeyUsage(n).getDEREncoded();
    }
    
    public String getName() {
        return "KeyUsage";
    }
    
    public Vector getValues() {
        final Vector<String> vector = new Vector<String>();
        for (int i = 0; i < X509KeyUsage.USAGES.length; ++i) {
            if ((X509KeyUsage.USAGES[i] & this.m_usage) == X509KeyUsage.USAGES[i]) {
                vector.addElement(this.getUsageString(X509KeyUsage.USAGES[i]));
            }
        }
        return vector;
    }
    
    public String getUsageString(final int n) {
        switch (n) {
            case 128: {
                return "digitalSignature";
            }
            case 64: {
                return "nonRepudiation/contentCommitment";
            }
            case 32: {
                return "keyEncipherment";
            }
            case 16: {
                return "dataEncipherment";
            }
            case 8: {
                return "keyAgreement";
            }
            case 4: {
                return "keyCertSign";
            }
            case 2: {
                return "cRLSign";
            }
            case 1: {
                return "encipherOnly";
            }
            case 32768: {
                return "decipherOnly";
            }
            default: {
                return null;
            }
        }
    }
    
    private void createValue() {
        try {
            this.m_usage = ((DERBitString)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject()).intValue();
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not read key usage from byte array!");
        }
    }
    
    public boolean isAllowedUsage(final int n) {
        return (this.m_usage & n) == this.m_usage;
    }
    
    public boolean allowsDigitalSignature() {
        return this.isAllowedUsage(128);
    }
    
    public boolean allowsNonRepudiation() {
        return this.isAllowedUsage(64);
    }
    
    public boolean allowsKeyEncipherment() {
        return this.isAllowedUsage(32);
    }
    
    public boolean allowsDataEncipherment() {
        return this.isAllowedUsage(16);
    }
    
    public boolean allowsKeyAgreement() {
        return this.isAllowedUsage(8);
    }
    
    public boolean allowsKeyCertSign() {
        return this.isAllowedUsage(4);
    }
    
    public boolean allowsCRLSign() {
        return this.isAllowedUsage(2);
    }
    
    public boolean allowsEncipherOnly() {
        return this.isAllowedUsage(1);
    }
    
    public boolean allowsDecipherOnly() {
        return this.isAllowedUsage(32768);
    }
    
    static {
        IDENTIFIER = X509Extensions.KeyUsage.getId();
        USAGES = new int[] { 128, 64, 32, 16, 8, 4, 2, 1, 32768 };
    }
}
