// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system;

import org.jboss.mx.util.ObjectNameFactory;
import javax.management.ObjectName;

public interface BarrierControllerMBean extends ListenerServiceMBean
{
    public static final ObjectName OBJECT_NAME = ObjectNameFactory.create("jboss:service=BarrierController");
    
    String getBarrierStateString();
    
    void setBarrierObjectName(final ObjectName p0);
    
    ObjectName getBarrierObjectName();
    
    void setBarrierCreatedOnStartup(final Boolean p0);
    
    Boolean getBarrierCreatedOnStartup();
    
    void setBarrierEnabledOnStartup(final Boolean p0);
    
    Boolean getBarrierEnabledOnStartup();
    
    void setCreateBarrierHandback(final String p0);
    
    String getCreateBarrierHandback();
    
    void setStartBarrierHandback(final String p0);
    
    String getStartBarrierHandback();
    
    void setStopBarrierHandback(final String p0);
    
    String getStopBarrierHandback();
    
    void setDestroyBarrierHandback(final String p0);
    
    String getDestroyBarrierHandback();
    
    void setDynamicSubscriptions(final Boolean p0);
    
    Boolean getDynamicSubscriptions();
    
    void createBarrier();
    
    void startBarrier();
    
    void stopBarrier();
    
    void destroyBarrier();
}
