// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public interface IoFuture
{
    IoSession getSession();
    
    IoFuture addListener(final IoFutureListener<?> p0);
}
