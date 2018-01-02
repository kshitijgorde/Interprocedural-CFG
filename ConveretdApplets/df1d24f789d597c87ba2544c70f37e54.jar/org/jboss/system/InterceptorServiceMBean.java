// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system;

import java.util.List;

public interface InterceptorServiceMBean extends ServiceMBean
{
    void setInterceptables(final List p0);
    
    List getInterceptables();
}
