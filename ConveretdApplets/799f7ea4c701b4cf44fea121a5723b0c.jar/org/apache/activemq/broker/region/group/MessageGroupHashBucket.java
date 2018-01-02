// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.group;

import org.apache.activemq.command.ConsumerId;

public class MessageGroupHashBucket implements MessageGroupMap
{
    private final int bucketCount;
    private final ConsumerId[] consumers;
    
    public MessageGroupHashBucket(final int bucketCount) {
        this.bucketCount = bucketCount;
        this.consumers = new ConsumerId[bucketCount];
    }
    
    @Override
    public void put(final String groupId, final ConsumerId consumerId) {
        final int bucket = this.getBucketNumber(groupId);
        this.consumers[bucket] = consumerId;
    }
    
    @Override
    public ConsumerId get(final String groupId) {
        final int bucket = this.getBucketNumber(groupId);
        return this.consumers[bucket];
    }
    
    @Override
    public ConsumerId removeGroup(final String groupId) {
        final int bucket = this.getBucketNumber(groupId);
        final ConsumerId answer = this.consumers[bucket];
        this.consumers[bucket] = null;
        return answer;
    }
    
    @Override
    public MessageGroupSet removeConsumer(final ConsumerId consumerId) {
        MessageGroupSet answer = null;
        for (int i = 0; i < this.consumers.length; ++i) {
            final ConsumerId owner = this.consumers[i];
            if (owner != null && owner.equals(consumerId)) {
                answer = this.createMessageGroupSet(i, answer);
                this.consumers[i] = null;
            }
        }
        if (answer == null) {
            answer = EmptyMessageGroupSet.INSTANCE;
        }
        return answer;
    }
    
    @Override
    public String toString() {
        int count = 0;
        for (int i = 0; i < this.consumers.length; ++i) {
            if (this.consumers[i] != null) {
                ++count;
            }
        }
        return "active message group buckets: " + count;
    }
    
    protected MessageGroupSet createMessageGroupSet(final int bucketNumber, final MessageGroupSet parent) {
        final MessageGroupSet answer = this.createMessageGroupSet(bucketNumber);
        if (parent == null) {
            return answer;
        }
        return new MessageGroupSet() {
            @Override
            public boolean contains(final String groupID) {
                return parent.contains(groupID) || answer.contains(groupID);
            }
        };
    }
    
    protected MessageGroupSet createMessageGroupSet(final int bucketNumber) {
        return new MessageGroupSet() {
            @Override
            public boolean contains(final String groupID) {
                final int bucket = MessageGroupHashBucket.this.getBucketNumber(groupID);
                return bucket == bucketNumber;
            }
        };
    }
    
    protected int getBucketNumber(final String groupId) {
        int bucket = groupId.hashCode() % this.bucketCount;
        if (bucket < 0) {
            bucket *= -1;
        }
        return bucket;
    }
}
