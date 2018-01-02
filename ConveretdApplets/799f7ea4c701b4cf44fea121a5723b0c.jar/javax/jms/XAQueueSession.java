// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XAQueueSession extends XASession
{
    QueueSession getQueueSession() throws JMSException;
}
