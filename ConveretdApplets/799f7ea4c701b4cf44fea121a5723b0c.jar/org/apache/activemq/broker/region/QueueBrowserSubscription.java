// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region;

import org.apache.activemq.command.MessageAck;
import java.io.IOException;
import org.apache.activemq.filter.MessageEvaluationContext;
import javax.jms.InvalidSelectorException;
import org.apache.activemq.command.ConsumerInfo;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.usage.SystemUsage;
import org.apache.activemq.broker.Broker;

public class QueueBrowserSubscription extends QueueSubscription
{
    int queueRefs;
    boolean browseDone;
    boolean destinationsAdded;
    
    public QueueBrowserSubscription(final Broker broker, final SystemUsage usageManager, final ConnectionContext context, final ConsumerInfo info) throws InvalidSelectorException {
        super(broker, usageManager, context, info);
    }
    
    @Override
    protected boolean canDispatch(final MessageReference node) {
        return !((QueueMessageReference)node).isAcked();
    }
    
    @Override
    public synchronized String toString() {
        return "QueueBrowserSubscription: consumer=" + this.info.getConsumerId() + ", destinations=" + this.destinations.size() + ", dispatched=" + this.dispatched.size() + ", delivered=" + this.prefetchExtension + ", pending=" + this.getPendingQueueSize();
    }
    
    public synchronized void destinationsAdded() throws Exception {
        this.destinationsAdded = true;
        this.checkDone();
    }
    
    private void checkDone() throws Exception {
        if (!this.browseDone && this.queueRefs == 0 && this.destinationsAdded) {
            this.browseDone = true;
            this.add(QueueMessageReference.NULL_MESSAGE);
        }
    }
    
    @Override
    public boolean matches(final MessageReference node, final MessageEvaluationContext context) throws IOException {
        return !this.browseDone && super.matches(node, context);
    }
    
    @Override
    protected void acknowledge(final ConnectionContext context, final MessageAck ack, final MessageReference n) throws IOException {
        if (this.info.isNetworkSubscription()) {
            super.acknowledge(context, ack, n);
        }
    }
    
    public synchronized void incrementQueueRef() {
        ++this.queueRefs;
    }
    
    public synchronized void decrementQueueRef() throws Exception {
        --this.queueRefs;
        this.checkDone();
    }
}
