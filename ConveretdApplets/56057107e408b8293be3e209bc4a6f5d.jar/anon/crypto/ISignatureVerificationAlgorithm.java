// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface ISignatureVerificationAlgorithm
{
    boolean verify(final byte[] p0, final byte[] p1);
    
    boolean verify(final byte[] p0, final int p1, final int p2, final byte[] p3, final int p4, final int p5);
    
    byte[] decodeForXMLSignature(final byte[] p0);
    
    String getXMLSignatureAlgorithmReference();
    
    AlgorithmIdentifier getIdentifier();
}
