// 
// Decompiled by Procyon v0.5.30
// 

package anon.client;

import java.security.SignatureException;
import anon.infoservice.MixCascade;

public interface ITrustModel
{
    boolean isTrusted(final MixCascade p0);
    
    void checkTrust(final MixCascade p0) throws TrustException, SignatureException;
}
