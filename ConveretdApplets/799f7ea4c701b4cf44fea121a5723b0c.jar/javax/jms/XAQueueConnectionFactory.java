// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XAQueueConnectionFactory extends XAConnectionFactory, QueueConnectionFactory
{
    XAQueueConnection createXAQueueConnection() throws JMSException;
    
    XAQueueConnection createXAQueueConnection(final String p0, final String p1) throws JMSException;
}
