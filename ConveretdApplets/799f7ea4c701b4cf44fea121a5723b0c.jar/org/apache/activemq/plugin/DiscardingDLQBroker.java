// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.plugin;

import org.slf4j.LoggerFactory;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.region.Subscription;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.broker.Broker;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerFilter;

public class DiscardingDLQBroker extends BrokerFilter
{
    public static Logger log;
    private boolean dropTemporaryTopics;
    private boolean dropTemporaryQueues;
    private boolean dropAll;
    private Pattern[] destFilter;
    private int reportInterval;
    private long dropCount;
    
    public DiscardingDLQBroker(final Broker next) {
        super(next);
        this.dropTemporaryTopics = true;
        this.dropTemporaryQueues = true;
        this.dropAll = true;
        this.reportInterval = 1000;
        this.dropCount = 0L;
    }
    
    @Override
    public void sendToDeadLetterQueue(final ConnectionContext ctx, final MessageReference msgRef, final Subscription subscription) {
        if (DiscardingDLQBroker.log.isTraceEnabled()) {
            DiscardingDLQBroker.log.trace("Discarding DLQ BrokerFilter[pass through] - skipping message:" + ((msgRef != null) ? msgRef.getMessage() : null));
        }
        boolean dropped = true;
        Message msg = null;
        ActiveMQDestination dest = null;
        String destName = null;
        msg = msgRef.getMessage();
        dest = msg.getDestination();
        destName = dest.getPhysicalName();
        if (dest == null || destName == null) {
            this.skipMessage("NULL DESTINATION", msgRef);
        }
        else if (this.dropAll) {
            this.skipMessage("dropAll", msgRef);
        }
        else if (this.dropTemporaryTopics && dest.isTemporary() && dest.isTopic()) {
            this.skipMessage("dropTemporaryTopics", msgRef);
        }
        else if (this.dropTemporaryQueues && dest.isTemporary() && dest.isQueue()) {
            this.skipMessage("dropTemporaryQueues", msgRef);
        }
        else if (this.destFilter != null && this.matches(destName)) {
            this.skipMessage("dropOnly", msgRef);
        }
        else {
            dropped = false;
            this.next.sendToDeadLetterQueue(ctx, msgRef, subscription);
        }
        if (dropped && this.getReportInterval() > 0) {
            final long dropCount = this.dropCount + 1L;
            this.dropCount = dropCount;
            if (dropCount % this.getReportInterval() == 0L) {
                DiscardingDLQBroker.log.info("Total of " + this.dropCount + " messages were discarded, since their destination was the dead letter queue");
            }
        }
    }
    
    public boolean matches(final String destName) {
        for (int i = 0; this.destFilter != null && i < this.destFilter.length; ++i) {
            if (this.destFilter[i] != null && this.destFilter[i].matcher(destName).matches()) {
                return true;
            }
        }
        return false;
    }
    
    private void skipMessage(final String prefix, final MessageReference msgRef) {
        if (DiscardingDLQBroker.log.isDebugEnabled()) {
            final String lmsg = "Discarding DLQ BrokerFilter[" + prefix + "] - skipping message:" + ((msgRef != null) ? msgRef.getMessage() : null);
            DiscardingDLQBroker.log.debug(lmsg);
        }
    }
    
    public void setDropTemporaryTopics(final boolean dropTemporaryTopics) {
        this.dropTemporaryTopics = dropTemporaryTopics;
    }
    
    public void setDropTemporaryQueues(final boolean dropTemporaryQueues) {
        this.dropTemporaryQueues = dropTemporaryQueues;
    }
    
    public void setDropAll(final boolean dropAll) {
        this.dropAll = dropAll;
    }
    
    public void setDestFilter(final Pattern[] destFilter) {
        this.destFilter = destFilter;
    }
    
    public void setReportInterval(final int reportInterval) {
        this.reportInterval = reportInterval;
    }
    
    public boolean isDropTemporaryTopics() {
        return this.dropTemporaryTopics;
    }
    
    public boolean isDropTemporaryQueues() {
        return this.dropTemporaryQueues;
    }
    
    public boolean isDropAll() {
        return this.dropAll;
    }
    
    public Pattern[] getDestFilter() {
        return this.destFilter;
    }
    
    public int getReportInterval() {
        return this.reportInterval;
    }
    
    static {
        DiscardingDLQBroker.log = LoggerFactory.getLogger(DiscardingDLQBroker.class);
    }
}
