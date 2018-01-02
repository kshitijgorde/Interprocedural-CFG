// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.write.WriteRequest;
import java.util.Queue;
import org.apache.mina.core.write.WriteRequestQueue;

final class DefaultIoSessionDataStructureFactory$DefaultWriteRequestQueue implements WriteRequestQueue
{
    private final Queue<WriteRequest> q;
    
    public DefaultIoSessionDataStructureFactory$DefaultWriteRequestQueue() {
        this.q = new ConcurrentLinkedQueue<WriteRequest>();
    }
    
    @Override
    public final void dispose(final IoSession ioSession) {
    }
    
    @Override
    public final synchronized boolean isEmpty(final IoSession ioSession) {
        return this.q.isEmpty();
    }
    
    @Override
    public final synchronized void offer(final IoSession ioSession, final WriteRequest writeRequest) {
        this.q.offer(writeRequest);
    }
    
    @Override
    public final synchronized WriteRequest poll(final IoSession ioSession) {
        return this.q.poll();
    }
    
    @Override
    public final String toString() {
        return this.q.toString();
    }
}
