// 
// Decompiled by Procyon v0.5.30
// 

package anon;

import java.net.ConnectException;
import anon.infoservice.IMutableProxyInterface;
import anon.terms.TermsAndConditionConfirmation;

public interface AnonService
{
    public static final String ANONLIB_VERSION = "00.12.003";
    
    int initialize(final AnonServerDescription p0, final IServiceContainer p1, final TermsAndConditionConfirmation p2);
    
    int setProxy(final IMutableProxyInterface p0);
    
    void shutdown(final boolean p0);
    
    boolean isConnected();
    
    AnonChannel createChannel(final int p0) throws ConnectException;
    
    void addEventListener(final AnonServiceEventListener p0);
    
    void removeEventListener(final AnonServiceEventListener p0);
    
    void removeEventListeners();
}
