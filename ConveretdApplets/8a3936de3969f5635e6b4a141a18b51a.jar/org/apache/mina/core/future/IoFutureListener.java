// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.future;

import java.util.EventListener;

public interface IoFutureListener<F extends IoFuture> extends EventListener
{
    void operationComplete(final F p0);
    
    default static {
        new IoFutureListener<IoFuture>() {
            @Override
            public final void operationComplete(final IoFuture ioFuture) {
                ioFuture.getSession().close(true);
            }
        };
    }
}
