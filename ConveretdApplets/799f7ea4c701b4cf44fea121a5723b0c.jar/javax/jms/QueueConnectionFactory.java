// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface QueueConnectionFactory extends ConnectionFactory
{
    QueueConnection createQueueConnection() throws JMSException;
    
    QueueConnection createQueueConnection(final String p0, final String p1) throws JMSException;
}
