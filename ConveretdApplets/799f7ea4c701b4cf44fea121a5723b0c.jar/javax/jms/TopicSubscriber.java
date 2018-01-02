// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface TopicSubscriber extends MessageConsumer
{
    Topic getTopic() throws JMSException;
    
    boolean getNoLocal() throws JMSException;
}
