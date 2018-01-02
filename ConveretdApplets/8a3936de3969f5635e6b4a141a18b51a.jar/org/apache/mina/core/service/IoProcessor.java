// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import org.apache.mina.core.session.IoSession;

public interface IoProcessor<S extends IoSession>
{
    boolean isDisposing();
    
    void dispose();
    
    void add(final S p0);
    
    void flush(final S p0);
    
    void updateTrafficControl(final S p0);
    
    void remove(final S p0);
}
