// 
// Decompiled by Procyon v0.5.30
// 

package anon;

import anon.client.ITrustModel;

public interface IServiceContainer extends ITrustModel
{
    void keepCurrentService(final boolean p0);
    
    boolean isServiceAutoSwitched();
    
    boolean isReconnectedAutomatically();
}
