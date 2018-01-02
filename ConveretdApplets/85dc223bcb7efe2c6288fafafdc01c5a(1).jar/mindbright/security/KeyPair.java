// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class KeyPair
{
    PrivateKey privateKey;
    PublicKey publicKey;
    
    public KeyPair(final PublicKey publicKey, final PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
    
    public PublicKey getPublic() {
        return this.publicKey;
    }
    
    public PrivateKey getPrivate() {
        return this.privateKey;
    }
}
