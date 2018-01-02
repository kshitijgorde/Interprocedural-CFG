// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

public interface ICertificate
{
    IMyPublicKey getPublicKey();
    
    JAPCertificate getX509Certificate();
    
    byte[] toByteArray();
}
