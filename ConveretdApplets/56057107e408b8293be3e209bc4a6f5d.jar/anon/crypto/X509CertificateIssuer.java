// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import anon.util.Util;
import java.util.Vector;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.asn1.ASN1Sequence;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.DERSequence;

public class X509CertificateIssuer extends AbstractX509Extension
{
    public static final String IDENTIFIER;
    private X509DistinguishedName m_issuer;
    
    public X509CertificateIssuer(final X509DistinguishedName issuer) {
        super(X509CertificateIssuer.IDENTIFIER, true, createDEROctets(issuer));
        this.m_issuer = issuer;
    }
    
    public X509CertificateIssuer(final DERSequence derSequence) {
        super(derSequence);
        this.createValue();
    }
    
    private static byte[] createDEROctets(final X509DistinguishedName x509DistinguishedName) {
        return new GeneralNames(new GeneralName(x509DistinguishedName.getX509Name())).getDEREncoded();
    }
    
    public String getName() {
        return "CertificateIssuer";
    }
    
    private void createValue() {
        try {
            final GeneralName generalName = new GeneralNames((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject()).getNames()[0];
            if (generalName.getTagNo() != 4) {
                throw new Exception();
            }
            this.m_issuer = new X509DistinguishedName(new X509Name((ASN1Sequence)generalName.getName()));
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not read certificate issuer extension from byte array!");
        }
    }
    
    public Vector getValues() {
        return Util.toVector(this.m_issuer.toString());
    }
    
    public boolean equalsIssuer(final Object o) {
        return o != null && (o instanceof X509DistinguishedName || o instanceof X509Name) && this.m_issuer.equals(o);
    }
    
    public X509DistinguishedName getDistinguishedName() {
        return this.m_issuer;
    }
    
    static {
        IDENTIFIER = X509Extensions.CertificateIssuer.getId();
    }
}
