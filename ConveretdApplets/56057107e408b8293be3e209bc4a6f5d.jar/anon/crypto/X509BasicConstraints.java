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
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.DERSequence;

public class X509BasicConstraints extends AbstractX509Extension
{
    public static final String IDENTIFIER;
    private boolean m_cA;
    private int m_pathLenConstraint;
    
    public X509BasicConstraints(final boolean ca) {
        super(X509BasicConstraints.IDENTIFIER, true, createDEROctets(ca));
        this.m_pathLenConstraint = -1;
        this.m_cA = ca;
    }
    
    public X509BasicConstraints(final int pathLenConstraint) {
        super(X509BasicConstraints.IDENTIFIER, true, createDEROctets(pathLenConstraint));
        this.m_pathLenConstraint = -1;
        this.m_cA = true;
        this.m_pathLenConstraint = pathLenConstraint;
    }
    
    public X509BasicConstraints(final DERSequence derSequence) {
        super(derSequence);
        this.m_pathLenConstraint = -1;
        this.createValue();
    }
    
    private static byte[] createDEROctets(final int n) {
        return new BasicConstraints(n).getDEREncoded();
    }
    
    private static byte[] createDEROctets(final boolean b) {
        return new BasicConstraints(b).getDEREncoded();
    }
    
    private void createValue() {
        try {
            final BasicConstraints basicConstraints = new BasicConstraints((ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject());
            this.m_cA = basicConstraints.isCA();
            if (basicConstraints.getPathLenConstraint() != null) {
                this.m_pathLenConstraint = basicConstraints.getPathLenConstraint().intValue();
            }
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not read basic constraints from byte array!");
        }
    }
    
    public boolean isCA() {
        return this.m_cA;
    }
    
    public int getPathLengthConstraint() {
        return this.m_pathLenConstraint;
    }
    
    public String getName() {
        return "BasicConstraints";
    }
    
    public Vector getValues() {
        final Vector<String> vector = new Vector<String>();
        vector.addElement(new String("cA=" + this.m_cA));
        if (this.m_pathLenConstraint != -1) {
            vector.addElement(new String("pathLenConstraint=" + this.m_pathLenConstraint));
        }
        return vector;
    }
    
    static {
        IDENTIFIER = X509Extensions.BasicConstraints.getId();
    }
}
