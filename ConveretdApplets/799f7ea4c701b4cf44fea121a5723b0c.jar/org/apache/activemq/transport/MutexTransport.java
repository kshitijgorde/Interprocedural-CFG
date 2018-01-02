// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.transport;

import java.io.IOException;

public class MutexTransport extends TransportFilter
{
    private final Object writeMutex;
    
    public MutexTransport(final Transport next) {
        super(next);
        this.writeMutex = new Object();
    }
    
    @Override
    public FutureResponse asyncRequest(final Object command, final ResponseCallback responseCallback) throws IOException {
        synchronized (this.writeMutex) {
            return this.next.asyncRequest(command, null);
        }
    }
    
    @Override
    public void oneway(final Object command) throws IOException {
        synchronized (this.writeMutex) {
            this.next.oneway(command);
        }
    }
    
    @Override
    public Object request(final Object command) throws IOException {
        synchronized (this.writeMutex) {
            return this.next.request(command);
        }
    }
    
    @Override
    public Object request(final Object command, final int timeout) throws IOException {
        synchronized (this.writeMutex) {
            return this.next.request(command, timeout);
        }
    }
    
    @Override
    public String toString() {
        return this.next.toString();
    }
}
