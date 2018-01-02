// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public abstract class AbstractPublicKey implements IMyPublicKey
{
    protected AbstractPublicKey() {
    }
    
    public AbstractPublicKey(final SubjectPublicKeyInfo subjectPublicKeyInfo) {
    }
    
    public final byte[] getEncoded() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
        try {
            derOutputStream.writeObject(this.getAsSubjectPublicKeyInfo());
            derOutputStream.close();
        }
        catch (IOException ex) {
            throw new RuntimeException("IOException while encoding public key");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public abstract int hashCode();
    
    public abstract boolean equals(final Object p0);
    
    public abstract int getKeyLength();
    
    public abstract SubjectPublicKeyInfo getAsSubjectPublicKeyInfo();
    
    public abstract ISignatureVerificationAlgorithm getSignatureAlgorithm();
    
    public abstract String getFormat();
    
    public abstract String getAlgorithm();
    
    public abstract Element toXmlElement(final Document p0);
}
