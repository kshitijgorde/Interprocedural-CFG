// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.transport.socket;

import org.apache.mina.core.session.AbstractIoSessionConfig;

public abstract class AbstractDatagramSessionConfig extends AbstractIoSessionConfig implements DatagramSessionConfig
{
    private boolean closeOnPortUnreachable;
    
    protected AbstractDatagramSessionConfig() {
        this.closeOnPortUnreachable = true;
    }
    
    public final boolean isCloseOnPortUnreachable() {
        return this.closeOnPortUnreachable;
    }
}
