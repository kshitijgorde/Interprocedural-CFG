// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Sequence;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.ReasonFlags;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;

public class X509IssuingDistributionPoint extends AbstractX509Extension
{
    public static final String IDENTIFIER;
    private IssuingDistributionPoint m_issuingDistributionPoint;
    
    public X509IssuingDistributionPoint(final DistributionPointName distributionPointName, final boolean b, final boolean b2, final ReasonFlags reasonFlags, final boolean b3, final boolean b4) {
        super(X509IssuingDistributionPoint.IDENTIFIER, false, createDEROctets(distributionPointName, !b, !b2, reasonFlags, !b3, !b4));
        this.m_issuingDistributionPoint = new IssuingDistributionPoint(distributionPointName, !b, !b2, reasonFlags, !b3, !b4);
    }
    
    public X509IssuingDistributionPoint(final boolean b) {
        this(null, false, false, null, b, false);
    }
    
    public X509IssuingDistributionPoint(final DERSequence derSequence) {
        super(derSequence);
        this.createValue();
    }
    
    private void createValue() {
        try {
            this.m_issuingDistributionPoint = new IssuingDistributionPoint((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject());
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not read issuing distribution point extension from byte array!");
        }
    }
    
    private static byte[] createDEROctets(final DistributionPointName distributionPointName, final boolean b, final boolean b2, final ReasonFlags reasonFlags, final boolean b3, final boolean b4) {
        return new IssuingDistributionPoint(distributionPointName, b, b2, reasonFlags, b3, b4).getDEREncoded();
    }
    
    public String getName() {
        return "IssuingDistributionPoint";
    }
    
    public Vector getValues() {
        return null;
    }
    
    public boolean isIndirectCRL() {
        return this.m_issuingDistributionPoint.isIndirectCRL();
    }
    
    public IssuingDistributionPoint getIssuingDistributionPoint() {
        return this.m_issuingDistributionPoint;
    }
    
    static {
        IDENTIFIER = X509Extensions.IssuingDistributionPoint.getId();
    }
}
