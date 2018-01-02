// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.write;

public final class WriteTimeoutException extends WriteException
{
    private static final long serialVersionUID = 3906931157944579121L;
    
    public WriteTimeoutException(final WriteRequest writeRequest) {
        super(writeRequest);
    }
}
