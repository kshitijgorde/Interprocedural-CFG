// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import org.jboss.util.id.GUID;
import java.rmi.Remote;

public interface Invoker extends Remote
{
    public static final GUID ID = new GUID();
    
    String getServerHostName() throws Exception;
    
    Object invoke(final Invocation p0) throws Exception;
}
