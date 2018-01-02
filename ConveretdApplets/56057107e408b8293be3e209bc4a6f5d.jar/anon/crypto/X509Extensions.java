// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import anon.util.Util;
import org.bouncycastle.asn1.DERSet;
import java.util.Vector;
import org.bouncycastle.asn1.DERObjectIdentifier;

public final class X509Extensions
{
    private DERObjectIdentifier X509_EXTENSIONS_IDENTIFIER;
    private static final Vector KNOWN_CERTIFICATE_EXTENSIONS;
    private static final Vector KNOWN_CRL_EXTENSIONS;
    private static final Vector KNOWN_CRL_ENTRY_EXTENSIONS;
    private DERSet m_extensions;
    private Vector m_vecExtensions;
    
    public X509Extensions(final AbstractX509Extension abstractX509Extension) {
        this(Util.toVector(abstractX509Extension));
    }
    
    public X509Extensions(Vector vector) {
        this.X509_EXTENSIONS_IDENTIFIER = new DERObjectIdentifier("1.2.840.113549.1.9.14");
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        if (vector == null) {
            vector = new Vector<AbstractX509Extension>();
        }
        this.m_vecExtensions = new Vector();
        for (int i = 0; i < vector.size(); ++i) {
            if (!(vector.elementAt(i) instanceof AbstractX509Extension)) {
                throw new IllegalArgumentException("X509 extension expected, but was: " + vector.elementAt(i));
            }
            this.m_vecExtensions.addElement(vector.elementAt(i));
            asn1EncodableVector.add(vector.elementAt(i).getBCExtension());
        }
        final ASN1EncodableVector asn1EncodableVector2 = new ASN1EncodableVector();
        asn1EncodableVector2.add(this.X509_EXTENSIONS_IDENTIFIER);
        asn1EncodableVector2.add(new DERSet(new DERSequence(asn1EncodableVector)));
        this.m_extensions = new DERSet(new DERSequence(asn1EncodableVector2));
    }
    
    X509Extensions(final DERSet extensions) {
        this.X509_EXTENSIONS_IDENTIFIER = new DERObjectIdentifier("1.2.840.113549.1.9.14");
        this.m_extensions = extensions;
        this.m_vecExtensions = new Vector();
        if (this.m_extensions.size() == 0) {
            return;
        }
        final DERSequence derSequence = (DERSequence)this.m_extensions.getObjectAt(0);
        final DERObjectIdentifier derObjectIdentifier = (DERObjectIdentifier)derSequence.getObjectAt(0);
        if (!derObjectIdentifier.equals(this.X509_EXTENSIONS_IDENTIFIER)) {
            throw new IllegalArgumentException("Wrong identifier: " + derObjectIdentifier.getId());
        }
        final DERSequence derSequence2 = (DERSequence)((DERSet)derSequence.getObjectAt(1)).getObjectAt(0);
        for (int i = 0; i < derSequence2.size(); ++i) {
            this.m_vecExtensions.addElement(AbstractX509Extension.getInstance((DERSequence)derSequence2.getObjectAt(i)));
        }
    }
    
    X509Extensions(final org.bouncycastle.asn1.x509.X509Extensions x509Extensions) {
        this(createExtensionsFromX509Extensions(x509Extensions));
    }
    
    public int getSize() {
        return this.m_vecExtensions.size();
    }
    
    public AbstractX509Extension getExtension(final int n) {
        return this.m_vecExtensions.elementAt(n);
    }
    
    public AbstractX509Extension getExtension(final String s) {
        for (int i = 0; i < this.m_vecExtensions.size(); ++i) {
            final AbstractX509Extension abstractX509Extension = this.m_vecExtensions.elementAt(i);
            if (abstractX509Extension.getIdentifier().equals(s)) {
                return abstractX509Extension;
            }
        }
        return null;
    }
    
    public Vector getExtensions(final String s) {
        final Vector<AbstractX509Extension> vector = new Vector<AbstractX509Extension>();
        for (int i = 0; i < this.m_vecExtensions.size(); ++i) {
            final AbstractX509Extension abstractX509Extension = this.m_vecExtensions.elementAt(i);
            if (abstractX509Extension.getIdentifier().equals(s)) {
                vector.addElement(abstractX509Extension);
            }
        }
        return vector;
    }
    
    public Vector getExtensions() {
        return (Vector)this.m_vecExtensions.clone();
    }
    
    DERSet getBCExtensions() {
        return this.m_extensions;
    }
    
    org.bouncycastle.asn1.x509.X509Extensions getBCX509Extensions() {
        return new org.bouncycastle.asn1.x509.X509Extensions((ASN1Sequence)((DERSet)((DERSequence)this.m_extensions.getObjectAt(0)).getObjectAt(1)).getObjectAt(0));
    }
    
    private static Vector createExtensionsFromX509Extensions(final org.bouncycastle.asn1.x509.X509Extensions x509Extensions) {
        final Vector<AbstractX509Extension> vector = new Vector<AbstractX509Extension>();
        if (x509Extensions == null) {
            return vector;
        }
        final DERSequence derSequence = (DERSequence)x509Extensions.getDERObject();
        for (int i = 0; i < derSequence.size(); ++i) {
            vector.addElement(AbstractX509Extension.getInstance((DERSequence)derSequence.getObjectAt(i)));
        }
        return vector;
    }
    
    protected boolean hasUnknownCriticalExtensions() {
        final Enumeration<AbstractX509Extension> elements = this.m_vecExtensions.elements();
        while (elements.hasMoreElements()) {
            final AbstractX509Extension abstractX509Extension = elements.nextElement();
            final String identifier = abstractX509Extension.getIdentifier();
            if (abstractX509Extension.isCritical() && !X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.contains(identifier) && !X509Extensions.KNOWN_CRL_EXTENSIONS.contains(identifier) && !X509Extensions.KNOWN_CRL_EXTENSIONS.contains(identifier)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        KNOWN_CERTIFICATE_EXTENSIONS = new Vector();
        KNOWN_CRL_EXTENSIONS = new Vector();
        KNOWN_CRL_ENTRY_EXTENSIONS = new Vector();
        X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.AuthorityKeyIdentifier.toString());
        X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.SubjectKeyIdentifier.toString());
        X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.BasicConstraints.toString());
        X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.KeyUsage.toString());
        X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.IssuerAlternativeName.toString());
        X509Extensions.KNOWN_CERTIFICATE_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.SubjectAlternativeName.toString());
        X509Extensions.KNOWN_CRL_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.AuthorityKeyIdentifier.toString());
        X509Extensions.KNOWN_CRL_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.IssuerAlternativeName.toString());
        X509Extensions.KNOWN_CRL_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.IssuingDistributionPoint.toString());
        X509Extensions.KNOWN_CRL_ENTRY_EXTENSIONS.addElement(org.bouncycastle.asn1.x509.X509Extensions.CertificateIssuer.toString());
    }
}
