// 
// Decompiled by Procyon v0.5.30
// 

package anon.infoservice;

public class SimpleMixCascadeContainer extends AbstractMixCascadeContainer
{
    private MixCascade m_mixCascade;
    private boolean m_bAutoReConnect;
    
    public SimpleMixCascadeContainer(final MixCascade mixCascade) {
        this.m_bAutoReConnect = false;
        this.m_mixCascade = mixCascade;
    }
    
    public MixCascade getNextCascade() {
        return this.m_mixCascade;
    }
    
    public MixCascade getCurrentCascade() {
        return this.m_mixCascade;
    }
    
    public boolean isServiceAutoSwitched() {
        return false;
    }
    
    public void setAutoReConnect(final boolean bAutoReConnect) {
        this.m_bAutoReConnect = bAutoReConnect;
    }
    
    public boolean isReconnectedAutomatically() {
        return this.m_bAutoReConnect;
    }
    
    public void keepCurrentService(final boolean b) {
    }
}
