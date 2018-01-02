// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

public interface IVerifyable
{
    boolean isVerified();
    
    boolean isValid();
    
    MultiCertPath getCertPath();
}
