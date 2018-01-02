// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface QueueReceiver extends MessageConsumer
{
    Queue getQueue() throws JMSException;
}
