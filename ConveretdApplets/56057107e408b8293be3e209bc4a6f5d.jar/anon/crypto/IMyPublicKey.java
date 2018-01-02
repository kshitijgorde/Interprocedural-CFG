// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import anon.util.IXMLEncodable;
import java.security.PublicKey;

public interface IMyPublicKey extends PublicKey, IXMLEncodable
{
    ISignatureVerificationAlgorithm getSignatureAlgorithm();
    
    SubjectPublicKeyInfo getAsSubjectPublicKeyInfo();
    
    int getKeyLength();
    
    boolean equals(final Object p0);
    
    int hashCode();
}
