// 
// Decompiled by Procyon v0.5.30
// 

package forward.client;

import java.util.Enumeration;
import anon.infoservice.MixCascade;
import java.util.Vector;

public class ForwardConnectionDescriptor
{
    private Vector m_supportedMixCascades;
    private int m_maximumBandwidth;
    private int m_guaranteedBandwidth;
    private int m_minDummyTrafficInterval;
    
    public ForwardConnectionDescriptor() {
        this.m_supportedMixCascades = new Vector();
        this.m_maximumBandwidth = 0;
        this.m_guaranteedBandwidth = 0;
        this.m_minDummyTrafficInterval = -1;
    }
    
    public void addMixCascade(final MixCascade mixCascade) {
        this.m_supportedMixCascades.addElement(mixCascade);
    }
    
    public Vector getMixCascadeList() {
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration<Object> elements = this.m_supportedMixCascades.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement());
        }
        return vector;
    }
    
    public void setMaximumBandwidth(final int maximumBandwidth) {
        this.m_maximumBandwidth = maximumBandwidth;
    }
    
    public int getMaximumBandwidth() {
        return this.m_maximumBandwidth;
    }
    
    public void setGuaranteedBandwidth(final int guaranteedBandwidth) {
        this.m_guaranteedBandwidth = guaranteedBandwidth;
    }
    
    public int getGuaranteedBandwidth() {
        return this.m_guaranteedBandwidth;
    }
    
    public void setMinDummyTrafficInterval(final int minDummyTrafficInterval) {
        this.m_minDummyTrafficInterval = minDummyTrafficInterval;
    }
    
    public int getMinDummyTrafficInterval() {
        return this.m_minDummyTrafficInterval;
    }
}
