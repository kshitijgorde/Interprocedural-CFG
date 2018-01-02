// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.X509Extensions;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.DERSequence;

public final class X509SubjectKeyIdentifier extends AbstractX509KeyIdentifier
{
    public static final String IDENTIFIER;
    
    public X509SubjectKeyIdentifier(final IMyPublicKey myPublicKey) {
        super(X509SubjectKeyIdentifier.IDENTIFIER, createDEROctets(myPublicKey));
        this.createValue();
    }
    
    public X509SubjectKeyIdentifier(final DERSequence derSequence) {
        super(derSequence);
        this.createValue();
    }
    
    public String getName() {
        return "SubjectKeyIdentifier";
    }
    
    private static byte[] createDEROctets(final IMyPublicKey myPublicKey) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new DEROutputStream(byteArrayOutputStream).writeObject(new SubjectKeyIdentifier(myPublicKey.getAsSubjectPublicKeyInfo()).getDERObject());
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not write DER object to bytes!");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private void createValue() {
        byte[] octets;
        try {
            octets = ((DEROctetString)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject()).getOctets();
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not read subject key identifier from byte array!");
        }
        super.m_value = ByteSignature.toHexString(octets);
    }
    
    static {
        IDENTIFIER = X509Extensions.SubjectKeyIdentifier.getId();
    }
}
