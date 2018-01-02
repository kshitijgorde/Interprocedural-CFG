// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.lang.reflect.Method;
import java.util.Map;

public class RMIMultiSocketHandler implements RMIMultiSocket
{
    Object target;
    Map invokerMap;
    
    public RMIMultiSocketHandler(final Object target, final Map invokerMap) {
        this.target = target;
        this.invokerMap = invokerMap;
    }
    
    public Object invoke(final long methodHash, final Object[] args) throws Exception {
        final Method method = this.invokerMap.get(new Long(methodHash));
        return method.invoke(this.target, args);
    }
}
