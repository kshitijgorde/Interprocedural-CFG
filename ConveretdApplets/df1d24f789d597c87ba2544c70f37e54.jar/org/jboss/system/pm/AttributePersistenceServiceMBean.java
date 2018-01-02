// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system.pm;

import org.jboss.mx.persistence.AttributePersistenceManager;
import org.w3c.dom.Element;
import org.jboss.system.ServiceMBean;

public interface AttributePersistenceServiceMBean extends ServiceMBean
{
    String getVersionTag();
    
    void setVersionTag(final String p0);
    
    String getAttributePersistenceManagerClass();
    
    void setAttributePersistenceManagerClass(final String p0);
    
    Element getAttributePersistenceManagerConfig();
    
    void setAttributePersistenceManagerConfig(final Element p0);
    
    boolean getApmDestroyOnServiceStop();
    
    void setApmDestroyOnServiceStop(final boolean p0);
    
    AttributePersistenceManager apmCreate();
    
    boolean apmExists(final String p0) throws Exception;
    
    void apmRemove(final String p0) throws Exception;
    
    void apmRemoveAll() throws Exception;
    
    String[] apmListAll() throws Exception;
    
    String apmListAllAsString() throws Exception;
}
