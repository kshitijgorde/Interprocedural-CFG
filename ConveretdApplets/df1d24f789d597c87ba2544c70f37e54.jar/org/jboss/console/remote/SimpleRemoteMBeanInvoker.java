// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.remote;

import javax.management.ObjectName;

public interface SimpleRemoteMBeanInvoker
{
    Object invoke(final ObjectName p0, final String p1, final Object[] p2, final String[] p3) throws Exception;
    
    Object getAttribute(final ObjectName p0, final String p1) throws Exception;
}
