// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.crypto.digests;

public class SHA512Digest extends LongDigest
{
    private static final int DIGEST_LENGTH = 64;
    
    public SHA512Digest() {
    }
    
    public SHA512Digest(final SHA512Digest sha512Digest) {
        super(sha512Digest);
    }
    
    public String getAlgorithmName() {
        return "SHA-512";
    }
    
    public int getDigestSize() {
        return 64;
    }
    
    public int doFinal(final byte[] array, final int n) {
        this.finish();
        this.unpackWord(super.H1, array, n);
        this.unpackWord(super.H2, array, n + 8);
        this.unpackWord(super.H3, array, n + 16);
        this.unpackWord(super.H4, array, n + 24);
        this.unpackWord(super.H5, array, n + 32);
        this.unpackWord(super.H6, array, n + 40);
        this.unpackWord(super.H7, array, n + 48);
        this.unpackWord(super.H8, array, n + 56);
        this.reset();
        return 64;
    }
    
    public void reset() {
        super.reset();
        super.H1 = 7640891576956012808L;
        super.H2 = -4942790177534073029L;
        super.H3 = 4354685564936845355L;
        super.H4 = -6534734903238641935L;
        super.H5 = 5840696475078001361L;
        super.H6 = -7276294671716946913L;
        super.H7 = 2270897969802886507L;
        super.H8 = 6620516959819538809L;
    }
}
