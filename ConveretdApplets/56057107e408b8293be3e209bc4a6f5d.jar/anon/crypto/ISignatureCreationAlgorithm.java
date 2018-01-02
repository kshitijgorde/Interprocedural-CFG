// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface ISignatureCreationAlgorithm
{
    byte[] sign(final byte[] p0);
    
    byte[] encodeForXMLSignature(final byte[] p0);
    
    AlgorithmIdentifier getIdentifier();
    
    String getXMLSignatureAlgorithmReference();
}
