// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Sequence;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import java.math.BigInteger;

public class X509AuthorityKeyIdentifier extends AbstractX509KeyIdentifier
{
    public static final String IDENTIFIER;
    private BigInteger m_serial;
    private GeneralNames m_names;
    
    public X509AuthorityKeyIdentifier(final IMyPublicKey myPublicKey) {
        super(X509AuthorityKeyIdentifier.IDENTIFIER, createDEROctets(myPublicKey, null, null));
        this.createValue();
    }
    
    public X509AuthorityKeyIdentifier(final IMyPublicKey myPublicKey, final GeneralNames generalNames, final BigInteger bigInteger) {
        super(X509AuthorityKeyIdentifier.IDENTIFIER, createDEROctets(myPublicKey, generalNames, bigInteger));
        this.createValue();
    }
    
    public X509AuthorityKeyIdentifier(final IMyPublicKey myPublicKey, final X509DistinguishedName x509DistinguishedName, final BigInteger bigInteger) {
        super(X509AuthorityKeyIdentifier.IDENTIFIER, createDEROctets(myPublicKey, new GeneralNames(new GeneralName(x509DistinguishedName.getX509Name())), bigInteger));
        this.createValue();
    }
    
    public X509AuthorityKeyIdentifier(final DERSequence derSequence) {
        super(derSequence);
        this.createValue();
    }
    
    public String getName() {
        return "AuthorityKeyIdentifier";
    }
    
    private static byte[] createDEROctets(final IMyPublicKey myPublicKey, final GeneralNames generalNames, final BigInteger bigInteger) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        AuthorityKeyIdentifier authorityKeyIdentifier;
        if (generalNames != null && bigInteger != null) {
            authorityKeyIdentifier = new AuthorityKeyIdentifier(myPublicKey.getAsSubjectPublicKeyInfo(), generalNames, bigInteger);
        }
        else {
            authorityKeyIdentifier = new AuthorityKeyIdentifier(myPublicKey.getAsSubjectPublicKeyInfo());
        }
        try {
            new DEROutputStream(byteArrayOutputStream).writeObject(authorityKeyIdentifier.getDERObject());
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not write DER object to bytes!");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private void createValue() {
        try {
            final AuthorityKeyIdentifier authorityKeyIdentifier = new AuthorityKeyIdentifier((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject());
            super.m_value = ByteSignature.toHexString(authorityKeyIdentifier.getKeyIdentifier());
            this.m_serial = authorityKeyIdentifier.getAuthorityCertSerialNumber();
            this.m_names = authorityKeyIdentifier.getAuthorityCertIssuer();
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not read authority key identifier from byte array!");
        }
    }
    
    public Vector getValues() {
        final Vector<String> vector = new Vector<String>();
        vector.addElement(super.m_value);
        if (this.m_names != null) {
            final GeneralName[] names = this.m_names.getNames();
            for (int i = 0; i < names.length; ++i) {
                String s;
                if (names[i].getTagNo() == 4) {
                    s = new X509Name((ASN1Sequence)names[i].getName().getDERObject()).toString();
                }
                else {
                    s = new String(names[i].getName().getDERObject().getDEREncoded()).trim();
                }
                vector.addElement(s);
            }
        }
        if (this.m_serial != null) {
            vector.addElement("authorityCertSerialNumber: " + this.m_serial);
        }
        return vector;
    }
    
    static {
        IDENTIFIER = X509Extensions.AuthorityKeyIdentifier.getId();
    }
}
