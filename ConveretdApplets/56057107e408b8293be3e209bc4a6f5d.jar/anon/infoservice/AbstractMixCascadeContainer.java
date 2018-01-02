// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

import anon.AnonServerDescription;
import anon.IServiceContainer;
import anon.client.BasicTrustModel;

public abstract class AbstractMixCascadeContainer extends BasicTrustModel implements IServiceContainer
{
    public abstract MixCascade getNextCascade();
    
    public abstract MixCascade getCurrentCascade();
    
    public final AnonServerDescription getCurrentService() {
        return this.getCurrentCascade();
    }
    
    public abstract void keepCurrentService(final boolean p0);
    
    public abstract boolean isServiceAutoSwitched();
    
    public abstract boolean isReconnectedAutomatically();
}
