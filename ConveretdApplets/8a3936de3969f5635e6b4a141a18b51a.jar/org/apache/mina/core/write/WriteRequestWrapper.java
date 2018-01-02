// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.write;

import org.apache.mina.core.future.WriteFuture;
import java.net.SocketAddress;

public class WriteRequestWrapper implements WriteRequest
{
    private final WriteRequest parentRequest;
    
    public WriteRequestWrapper(final WriteRequest parentRequest) {
        if (parentRequest == null) {
            throw new IllegalArgumentException("parentRequest");
        }
        this.parentRequest = parentRequest;
    }
    
    @Override
    public final SocketAddress getDestination() {
        return this.parentRequest.getDestination();
    }
    
    @Override
    public final WriteFuture getFuture() {
        return this.parentRequest.getFuture();
    }
    
    @Override
    public Object getMessage() {
        return this.parentRequest.getMessage();
    }
    
    @Override
    public final WriteRequest getOriginalRequest() {
        return this.parentRequest.getOriginalRequest();
    }
    
    public final WriteRequest getParentRequest() {
        return this.parentRequest;
    }
    
    @Override
    public String toString() {
        return "WR Wrapper" + this.parentRequest.toString();
    }
    
    @Override
    public final boolean isEncoded() {
        return false;
    }
}
