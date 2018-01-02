// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.scheduler;

import org.slf4j.LoggerFactory;
import org.apache.activemq.state.ProducerState;
import org.apache.activemq.command.ProducerInfo;
import org.apache.activemq.command.MessageId;
import java.util.Iterator;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.kahadb.util.ByteSequence;
import org.apache.activemq.command.TransactionId;
import org.apache.activemq.util.TypeConversionSupport;
import org.apache.activemq.command.Message;
import org.apache.activemq.broker.ProducerBrokerExchange;
import org.apache.activemq.security.SecurityContext;
import org.apache.activemq.openwire.OpenWireFormat;
import org.apache.activemq.broker.Broker;
import java.io.File;
import org.apache.activemq.command.ProducerId;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.wireformat.WireFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.activemq.util.LongSequenceGenerator;
import org.apache.activemq.util.IdGenerator;
import org.slf4j.Logger;
import org.apache.activemq.broker.BrokerFilter;

public class SchedulerBroker extends BrokerFilter implements JobListener
{
    private static final Logger LOG;
    private static final IdGenerator ID_GENERATOR;
    private final LongSequenceGenerator messageIdGenerator;
    private final AtomicBoolean started;
    private final WireFormat wireFormat;
    private final ConnectionContext context;
    private final ProducerId producerId;
    private File directory;
    private JobSchedulerStore store;
    private JobScheduler scheduler;
    
    public SchedulerBroker(final Broker next, final File directory) throws Exception {
        super(next);
        this.messageIdGenerator = new LongSequenceGenerator();
        this.started = new AtomicBoolean();
        this.wireFormat = new OpenWireFormat();
        this.context = new ConnectionContext();
        this.producerId = new ProducerId();
        this.directory = directory;
        this.producerId.setConnectionId(SchedulerBroker.ID_GENERATOR.generateId());
        this.context.setSecurityContext(SecurityContext.BROKER_SECURITY_CONTEXT);
        this.context.setBroker(next);
        SchedulerBroker.LOG.info("Scheduler using directory: " + directory);
    }
    
    public synchronized JobScheduler getJobScheduler() throws Exception {
        return new JobSchedulerFacade(this);
    }
    
    public File getDirectory() {
        return this.directory;
    }
    
    public void setDirectory(final File directory) {
        this.directory = directory;
    }
    
    @Override
    public void start() throws Exception {
        this.started.set(true);
        this.getInternalScheduler();
        super.start();
    }
    
    @Override
    public void stop() throws Exception {
        if (this.started.compareAndSet(true, false)) {
            if (this.store != null) {
                this.store.stop();
            }
            if (this.scheduler != null) {
                this.scheduler.removeListener(this);
                this.scheduler = null;
            }
        }
        super.stop();
    }
    
    @Override
    public void send(final ProducerBrokerExchange producerExchange, final Message messageSend) throws Exception {
        long delay = 0L;
        long period = 0L;
        int repeat = 0;
        String cronEntry = "";
        final String jobId = (String)messageSend.getProperty("scheduledJobId");
        final Object cronValue = messageSend.getProperty("AMQ_SCHEDULED_CRON");
        final Object periodValue = messageSend.getProperty("AMQ_SCHEDULED_PERIOD");
        final Object delayValue = messageSend.getProperty("AMQ_SCHEDULED_DELAY");
        final String physicalName = messageSend.getDestination().getPhysicalName();
        final boolean schedularManage = physicalName.regionMatches(true, 0, "ActiveMQ.Scheduler.Management", 0, "ActiveMQ.Scheduler.Management".length());
        if (schedularManage) {
            final JobScheduler scheduler = this.getInternalScheduler();
            final ActiveMQDestination replyTo = messageSend.getReplyTo();
            final String action = (String)messageSend.getProperty("AMQ_SCHEDULER_ACTION");
            if (action != null) {
                final Object startTime = messageSend.getProperty("ACTION_START_TIME");
                final Object endTime = messageSend.getProperty("ACTION_END_TIME");
                if (replyTo != null && action.equals("BROWSE")) {
                    if (startTime != null && endTime != null) {
                        final long start = (long)TypeConversionSupport.convert(startTime, Long.class);
                        final long finish = (long)TypeConversionSupport.convert(endTime, Long.class);
                        for (final Job job : scheduler.getAllJobs(start, finish)) {
                            this.sendScheduledJob(producerExchange.getConnectionContext(), job, replyTo);
                        }
                    }
                    else {
                        for (final Job job2 : scheduler.getAllJobs()) {
                            this.sendScheduledJob(producerExchange.getConnectionContext(), job2, replyTo);
                        }
                    }
                }
                if (jobId != null && action.equals("REMOVE")) {
                    scheduler.remove(jobId);
                }
                else if (action.equals("REMOVEALL")) {
                    if (startTime != null && endTime != null) {
                        final long start = (long)TypeConversionSupport.convert(startTime, Long.class);
                        final long finish = (long)TypeConversionSupport.convert(endTime, Long.class);
                        scheduler.removeAllJobs(start, finish);
                    }
                    else {
                        scheduler.removeAllJobs();
                    }
                }
            }
        }
        else if ((cronValue != null || periodValue != null || delayValue != null) && jobId == null) {
            final Message msg = messageSend.copy();
            msg.setTransactionId(null);
            final org.apache.activemq.util.ByteSequence packet = this.wireFormat.marshal(msg);
            if (cronValue != null) {
                cronEntry = cronValue.toString();
            }
            if (periodValue != null) {
                period = (long)TypeConversionSupport.convert(periodValue, Long.class);
            }
            if (delayValue != null) {
                delay = (long)TypeConversionSupport.convert(delayValue, Long.class);
            }
            final Object repeatValue = msg.getProperty("AMQ_SCHEDULED_REPEAT");
            if (repeatValue != null) {
                repeat = (int)TypeConversionSupport.convert(repeatValue, Integer.class);
            }
            this.getInternalScheduler().schedule(msg.getMessageId().toString(), new ByteSequence(packet.data, packet.offset, packet.length), cronEntry, delay, period, repeat);
        }
        else {
            super.send(producerExchange, messageSend);
        }
    }
    
    @Override
    public void scheduledJob(final String id, final ByteSequence job) {
        final org.apache.activemq.util.ByteSequence packet = new org.apache.activemq.util.ByteSequence(job.getData(), job.getOffset(), job.getLength());
        try {
            final Message messageSend = (Message)this.wireFormat.unmarshal(packet);
            messageSend.setOriginalTransactionId(null);
            final Object repeatValue = messageSend.getProperty("AMQ_SCHEDULED_REPEAT");
            final Object cronValue = messageSend.getProperty("AMQ_SCHEDULED_REPEAT");
            final String cronStr = (cronValue != null) ? cronValue.toString() : null;
            int repeat = 0;
            if (repeatValue != null) {
                repeat = (int)TypeConversionSupport.convert(repeatValue, Integer.class);
            }
            if (repeat != 0 || (cronStr != null && cronStr.length() > 0)) {
                messageSend.setMessageId(new MessageId(this.producerId, this.messageIdGenerator.getNextSequenceId()));
            }
            messageSend.setProperty("scheduledJobId", id);
            messageSend.removeProperty("AMQ_SCHEDULED_PERIOD");
            messageSend.removeProperty("AMQ_SCHEDULED_DELAY");
            messageSend.removeProperty("AMQ_SCHEDULED_REPEAT");
            messageSend.removeProperty("AMQ_SCHEDULED_CRON");
            final ProducerBrokerExchange producerExchange = new ProducerBrokerExchange();
            producerExchange.setConnectionContext(this.context);
            producerExchange.setMutable(true);
            producerExchange.setProducerState(new ProducerState(new ProducerInfo()));
            super.send(producerExchange, messageSend);
        }
        catch (Exception e) {
            SchedulerBroker.LOG.error("Failed to send scheduled message " + id, e);
        }
    }
    
    protected synchronized JobScheduler getInternalScheduler() throws Exception {
        if (this.started.get()) {
            if (this.scheduler == null) {
                (this.scheduler = this.getStore().getJobScheduler("JMS")).addListener(this);
            }
            return this.scheduler;
        }
        return null;
    }
    
    private JobSchedulerStore getStore() throws Exception {
        if (this.started.get()) {
            if (this.store == null) {
                (this.store = new JobSchedulerStore()).setDirectory(this.directory);
                this.store.start();
            }
            return this.store;
        }
        return null;
    }
    
    protected void sendScheduledJob(final ConnectionContext context, final Job job, final ActiveMQDestination replyTo) throws Exception {
        final org.apache.activemq.util.ByteSequence packet = new org.apache.activemq.util.ByteSequence(job.getPayload());
        try {
            final Message msg = (Message)this.wireFormat.unmarshal(packet);
            msg.setOriginalTransactionId(null);
            msg.setPersistent(false);
            msg.setType("Advisory");
            msg.setMessageId(new MessageId(this.producerId, this.messageIdGenerator.getNextSequenceId()));
            msg.setDestination(replyTo);
            msg.setResponseRequired(false);
            msg.setProducerId(this.producerId);
            msg.setProperty("scheduledJobId", job.getJobId());
            final boolean originalFlowControl = context.isProducerFlowControl();
            final ProducerBrokerExchange producerExchange = new ProducerBrokerExchange();
            producerExchange.setConnectionContext(context);
            producerExchange.setMutable(true);
            producerExchange.setProducerState(new ProducerState(new ProducerInfo()));
            try {
                context.setProducerFlowControl(false);
                this.next.send(producerExchange, msg);
            }
            finally {
                context.setProducerFlowControl(originalFlowControl);
            }
        }
        catch (Exception e) {
            SchedulerBroker.LOG.error("Failed to send scheduled message " + job.getJobId(), e);
        }
    }
    
    static {
        LOG = LoggerFactory.getLogger(SchedulerBroker.class);
        ID_GENERATOR = new IdGenerator();
    }
}
