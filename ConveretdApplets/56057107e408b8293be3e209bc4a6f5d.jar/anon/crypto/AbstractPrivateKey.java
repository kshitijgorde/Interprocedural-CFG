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
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;

public abstract class AbstractPrivateKey implements IMyPrivateKey
{
    protected AbstractPrivateKey() {
    }
    
    public AbstractPrivateKey(final PrivateKeyInfo privateKeyInfo) {
    }
    
    public final byte[] getEncoded() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
        try {
            derOutputStream.writeObject(this.getAsPrivateKeyInfo());
            derOutputStream.close();
        }
        catch (IOException ex) {
            throw new RuntimeException("IOException while encoding private key");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public abstract PrivateKeyInfo getAsPrivateKeyInfo();
    
    public abstract ISignatureCreationAlgorithm getSignatureAlgorithm();
    
    public abstract IMyPublicKey createPublicKey();
    
    public abstract String getFormat();
    
    public abstract String getAlgorithm();
    
    public abstract Element toXmlElement(final Document p0);
}
