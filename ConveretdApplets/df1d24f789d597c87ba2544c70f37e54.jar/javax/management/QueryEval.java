// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.QueryExpSupport;
import java.io.Serializable;

public abstract class QueryEval implements Serializable
{
    private static final long serialVersionUID = 2675899265640874796L;
    
    public static MBeanServer getMBeanServer() {
        return QueryExpSupport.server.get();
    }
    
    public void setMBeanServer(final MBeanServer mbeanServer) {
        QueryExpSupport.server.set(mbeanServer);
    }
}
