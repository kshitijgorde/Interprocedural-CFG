// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq;

import org.apache.activemq.command.ProducerId;
import org.apache.activemq.command.MessageId;
import org.apache.activemq.broker.region.MessageReference;
import org.apache.activemq.util.IdGenerator;
import javax.jms.JMSException;
import javax.jms.Message;
import org.apache.activemq.util.BitArrayBin;
import org.apache.activemq.util.LRUCache;
import java.io.Serializable;

public class ActiveMQMessageAuditNoSync implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_WINDOW_SIZE = 2048;
    public static final int MAXIMUM_PRODUCER_COUNT = 64;
    private int auditDepth;
    private int maximumNumberOfProducersToTrack;
    private LRUCache<Object, BitArrayBin> map;
    
    public ActiveMQMessageAuditNoSync() {
        this(2048, 64);
    }
    
    public ActiveMQMessageAuditNoSync(final int auditDepth, final int maximumNumberOfProducersToTrack) {
        this.auditDepth = auditDepth;
        this.maximumNumberOfProducersToTrack = maximumNumberOfProducersToTrack;
        this.map = new LRUCache<Object, BitArrayBin>(0, maximumNumberOfProducersToTrack, 0.75f, true);
    }
    
    public int getAuditDepth() {
        return this.auditDepth;
    }
    
    public void setAuditDepth(final int auditDepth) {
        this.auditDepth = auditDepth;
    }
    
    public int getMaximumNumberOfProducersToTrack() {
        return this.maximumNumberOfProducersToTrack;
    }
    
    public void setMaximumNumberOfProducersToTrack(final int maximumNumberOfProducersToTrack) {
        this.maximumNumberOfProducersToTrack = maximumNumberOfProducersToTrack;
        this.map.setMaxCacheSize(maximumNumberOfProducersToTrack);
    }
    
    public boolean isDuplicate(final Message message) throws JMSException {
        return this.isDuplicate(message.getJMSMessageID());
    }
    
    public boolean isDuplicate(final String id) {
        boolean answer = false;
        final String seed = IdGenerator.getSeedFromId(id);
        if (seed != null) {
            BitArrayBin bab = this.map.get(seed);
            if (bab == null) {
                bab = new BitArrayBin(this.auditDepth);
                this.map.put(seed, bab);
            }
            final long index = IdGenerator.getSequenceFromId(id);
            if (index >= 0L) {
                answer = bab.setBit(index, true);
            }
        }
        return answer;
    }
    
    public boolean isDuplicate(final MessageReference message) {
        final MessageId id = message.getMessageId();
        return this.isDuplicate(id);
    }
    
    public boolean isDuplicate(final MessageId id) {
        boolean answer = false;
        if (id != null) {
            final ProducerId pid = id.getProducerId();
            if (pid != null) {
                BitArrayBin bab = this.map.get(pid);
                if (bab == null) {
                    bab = new BitArrayBin(this.auditDepth);
                    this.map.put(pid, bab);
                }
                answer = bab.setBit(id.getProducerSequenceId(), true);
            }
        }
        return answer;
    }
    
    public void rollback(final MessageReference message) {
        final MessageId id = message.getMessageId();
        this.rollback(id);
    }
    
    public void rollback(final MessageId id) {
        if (id != null) {
            final ProducerId pid = id.getProducerId();
            if (pid != null) {
                final BitArrayBin bab = this.map.get(pid);
                if (bab != null) {
                    bab.setBit(id.getProducerSequenceId(), false);
                }
            }
        }
    }
    
    public boolean isInOrder(final Message msg) throws JMSException {
        return this.isInOrder(msg.getJMSMessageID());
    }
    
    public boolean isInOrder(final String id) {
        boolean answer = true;
        if (id != null) {
            final String seed = IdGenerator.getSeedFromId(id);
            if (seed != null) {
                final BitArrayBin bab = this.map.get(seed);
                if (bab != null) {
                    final long index = IdGenerator.getSequenceFromId(id);
                    answer = bab.isInOrder(index);
                }
            }
        }
        return answer;
    }
    
    public boolean isInOrder(final MessageReference message) {
        return this.isInOrder(message.getMessageId());
    }
    
    public boolean isInOrder(final MessageId id) {
        boolean answer = false;
        if (id != null) {
            final ProducerId pid = id.getProducerId();
            if (pid != null) {
                BitArrayBin bab = this.map.get(pid);
                if (bab == null) {
                    bab = new BitArrayBin(this.auditDepth);
                    this.map.put(pid, bab);
                }
                answer = bab.isInOrder(id.getProducerSequenceId());
            }
        }
        return answer;
    }
    
    public long getLastSeqId(final ProducerId id) {
        long result = -1L;
        final BitArrayBin bab = this.map.get(id.toString() + ":");
        if (bab != null) {
            result = bab.getLastSetIndex();
        }
        return result;
    }
    
    public void clear() {
        this.map.clear();
    }
}
