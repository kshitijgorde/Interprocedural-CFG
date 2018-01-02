// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.network;

import org.slf4j.LoggerFactory;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.activemq.command.ConsumerId;
import java.util.Set;
import org.apache.activemq.command.ConsumerInfo;
import org.slf4j.Logger;

public class DemandSubscription
{
    private static final Logger LOG;
    private final ConsumerInfo remoteInfo;
    private final ConsumerInfo localInfo;
    private Set<ConsumerId> remoteSubsIds;
    private AtomicInteger dispatched;
    private AtomicBoolean activeWaiter;
    
    DemandSubscription(final ConsumerInfo info) {
        this.remoteSubsIds = new CopyOnWriteArraySet<ConsumerId>();
        this.dispatched = new AtomicInteger(0);
        this.activeWaiter = new AtomicBoolean();
        this.remoteInfo = info;
        (this.localInfo = info.copy()).setNetworkSubscription(true);
        this.remoteSubsIds.add(info.getConsumerId());
    }
    
    public boolean add(final ConsumerId id) {
        return this.remoteSubsIds.add(id);
    }
    
    public boolean remove(final ConsumerId id) {
        return this.remoteSubsIds.remove(id);
    }
    
    public boolean isEmpty() {
        return this.remoteSubsIds.isEmpty();
    }
    
    public ConsumerInfo getLocalInfo() {
        return this.localInfo;
    }
    
    public ConsumerInfo getRemoteInfo() {
        return this.remoteInfo;
    }
    
    public void waitForCompletion() {
        if (this.dispatched.get() > 0) {
            if (DemandSubscription.LOG.isDebugEnabled()) {
                DemandSubscription.LOG.debug("Waiting for completion for sub: " + this.localInfo.getConsumerId() + ", dispatched: " + this.dispatched.get());
            }
            this.activeWaiter.set(true);
            if (this.dispatched.get() > 0) {
                synchronized (this.activeWaiter) {
                    try {
                        this.activeWaiter.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                if (this.dispatched.get() > 0) {
                    DemandSubscription.LOG.warn("demand sub interrupted or timedout while waiting for outstanding responses, expect potentially " + this.dispatched.get() + " duplicate deliveried");
                }
            }
        }
    }
    
    public void decrementOutstandingResponses() {
        if (this.dispatched.decrementAndGet() == 0 && this.activeWaiter.get()) {
            synchronized (this.activeWaiter) {
                this.activeWaiter.notifyAll();
            }
        }
    }
    
    public boolean incrementOutstandingResponses() {
        this.dispatched.incrementAndGet();
        if (this.activeWaiter.get()) {
            this.decrementOutstandingResponses();
            return false;
        }
        return true;
    }
    
    static {
        LOG = LoggerFactory.getLogger(DemandSubscription.class);
    }
}
