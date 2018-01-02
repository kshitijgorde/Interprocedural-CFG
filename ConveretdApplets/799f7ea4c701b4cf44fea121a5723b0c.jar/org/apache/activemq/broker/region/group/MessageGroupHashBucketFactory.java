// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.broker.region.group;

public class MessageGroupHashBucketFactory implements MessageGroupMapFactory
{
    private int bucketCount;
    
    public MessageGroupHashBucketFactory() {
        this.bucketCount = 1024;
    }
    
    @Override
    public MessageGroupMap createMessageGroupMap() {
        return new MessageGroupHashBucket(this.bucketCount);
    }
    
    public int getBucketCount() {
        return this.bucketCount;
    }
    
    public void setBucketCount(final int bucketCount) {
        this.bucketCount = bucketCount;
    }
}
