// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.write;

import java.util.Collection;

public final class WriteToClosedSessionException extends WriteException
{
    private static final long serialVersionUID = 5550204573739301393L;
    
    public WriteToClosedSessionException(final Collection<WriteRequest> collection) {
        super(collection);
    }
    
    public WriteToClosedSessionException(final WriteRequest writeRequest) {
        super(writeRequest);
    }
}
