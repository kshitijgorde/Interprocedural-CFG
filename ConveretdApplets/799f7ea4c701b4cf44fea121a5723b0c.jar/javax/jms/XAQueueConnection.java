// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XAQueueConnection extends XAConnection, QueueConnection
{
    XAQueueSession createXAQueueSession() throws JMSException;
    
    QueueSession createQueueSession(final boolean p0, final int p1) throws JMSException;
}
