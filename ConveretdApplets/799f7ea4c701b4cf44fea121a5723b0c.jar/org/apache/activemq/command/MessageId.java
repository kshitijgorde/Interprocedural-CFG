// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.command;

public class MessageId implements DataStructure, Comparable<MessageId>
{
    public static final byte DATA_STRUCTURE_TYPE = 110;
    protected ProducerId producerId;
    protected long producerSequenceId;
    protected long brokerSequenceId;
    private transient String key;
    private transient int hashCode;
    
    public MessageId() {
        this.producerId = new ProducerId();
    }
    
    public MessageId(final ProducerInfo producerInfo, final long producerSequenceId) {
        this.producerId = producerInfo.getProducerId();
        this.producerSequenceId = producerSequenceId;
    }
    
    public MessageId(final String messageKey) {
        this.setValue(messageKey);
    }
    
    public MessageId(final String producerId, final long producerSequenceId) {
        this(new ProducerId(producerId), producerSequenceId);
    }
    
    public MessageId(final ProducerId producerId, final long producerSequenceId) {
        this.producerId = producerId;
        this.producerSequenceId = producerSequenceId;
    }
    
    public void setValue(String messageKey) {
        this.key = messageKey;
        final int p = messageKey.lastIndexOf(":");
        if (p >= 0) {
            this.producerSequenceId = Long.parseLong(messageKey.substring(p + 1));
            messageKey = messageKey.substring(0, p);
        }
        this.producerId = new ProducerId(messageKey);
    }
    
    public void setTextView(final String key) {
        this.key = key;
    }
    
    @Override
    public byte getDataStructureType() {
        return 110;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        final MessageId id = (MessageId)o;
        return this.producerSequenceId == id.producerSequenceId && this.producerId.equals(id.producerId);
    }
    
    @Override
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (this.producerId.hashCode() ^ (int)this.producerSequenceId);
        }
        return this.hashCode;
    }
    
    @Override
    public String toString() {
        if (this.key == null) {
            this.key = this.producerId.toString() + ":" + this.producerSequenceId;
        }
        return this.key;
    }
    
    public ProducerId getProducerId() {
        return this.producerId;
    }
    
    public void setProducerId(final ProducerId producerId) {
        this.producerId = producerId;
    }
    
    public long getProducerSequenceId() {
        return this.producerSequenceId;
    }
    
    public void setProducerSequenceId(final long producerSequenceId) {
        this.producerSequenceId = producerSequenceId;
    }
    
    public long getBrokerSequenceId() {
        return this.brokerSequenceId;
    }
    
    public void setBrokerSequenceId(final long brokerSequenceId) {
        this.brokerSequenceId = brokerSequenceId;
    }
    
    @Override
    public boolean isMarshallAware() {
        return false;
    }
    
    public MessageId copy() {
        final MessageId copy = new MessageId(this.producerId, this.producerSequenceId);
        copy.key = this.key;
        copy.brokerSequenceId = this.brokerSequenceId;
        return copy;
    }
    
    @Override
    public int compareTo(final MessageId other) {
        int result = -1;
        if (other != null) {
            result = this.toString().compareTo(other.toString());
        }
        return result;
    }
}
