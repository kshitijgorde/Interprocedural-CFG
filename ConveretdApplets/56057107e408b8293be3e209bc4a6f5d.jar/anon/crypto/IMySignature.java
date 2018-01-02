// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.security.InvalidKeyException;

public interface IMySignature extends ISignatureVerificationAlgorithm, ISignatureCreationAlgorithm
{
    void initVerify(final IMyPublicKey p0) throws InvalidKeyException;
    
    void initSign(final IMyPrivateKey p0) throws InvalidKeyException;
    
    boolean verify(final byte[] p0, final byte[] p1);
    
    byte[] sign(final byte[] p0);
    
    byte[] encodeForXMLSignature(final byte[] p0);
    
    byte[] decodeForXMLSignature(final byte[] p0);
    
    String getXMLSignatureAlgorithmReference();
}
