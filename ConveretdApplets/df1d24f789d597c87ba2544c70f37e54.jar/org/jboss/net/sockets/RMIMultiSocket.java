// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.sockets;

import java.rmi.Remote;

public interface RMIMultiSocket extends Remote
{
    Object invoke(final long p0, final Object[] p1) throws Exception;
}
