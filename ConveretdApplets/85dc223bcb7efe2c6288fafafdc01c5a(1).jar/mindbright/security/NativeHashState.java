// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public class NativeHashState extends MessageDigest
{
    java.security.MessageDigest md;
    String myAlg;
    
    protected void init(String algorithm) throws Exception {
        this.myAlg = algorithm;
        if (algorithm.equals("SHA1")) {
            algorithm = "SHA";
        }
        this.md = java.security.MessageDigest.getInstance(algorithm);
    }
    
    public String getName() {
        return this.myAlg;
    }
    
    public void reset() {
        this.md.reset();
    }
    
    public void update(final byte[] buffer, final int offset, final int length) {
        this.md.update(buffer, offset, length);
    }
    
    public byte[] digest() {
        return this.md.digest();
    }
    
    public int blockSize() {
        return 64;
    }
    
    public int hashSize() {
        if (this.myAlg.equals("SHA")) {
            return 20;
        }
        return 16;
    }
}
