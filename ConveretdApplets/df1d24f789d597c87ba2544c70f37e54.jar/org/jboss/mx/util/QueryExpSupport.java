// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.MBeanServer;

public class QueryExpSupport
{
    public static ThreadLocal server;
    
    public void setMBeanServer(final MBeanServer mbeanServer) {
        QueryExpSupport.server.set(mbeanServer);
    }
    
    static {
        QueryExpSupport.server = new ThreadLocal();
    }
}
