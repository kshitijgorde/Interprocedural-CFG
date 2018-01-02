// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import anon.util.IXMLEncodable;
import java.security.PrivateKey;

public interface IMyPrivateKey extends PrivateKey, IXMLEncodable
{
    IMyPublicKey createPublicKey();
    
    ISignatureCreationAlgorithm getSignatureAlgorithm();
    
    PrivateKeyInfo getAsPrivateKeyInfo();
}
