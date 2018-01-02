// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface Connection
{
    Session createSession(final boolean p0, final int p1) throws JMSException;
    
    String getClientID() throws JMSException;
    
    void setClientID(final String p0) throws JMSException;
    
    ConnectionMetaData getMetaData() throws JMSException;
    
    ExceptionListener getExceptionListener() throws JMSException;
    
    void setExceptionListener(final ExceptionListener p0) throws JMSException;
    
    void start() throws JMSException;
    
    void stop() throws JMSException;
    
    void close() throws JMSException;
    
    ConnectionConsumer createConnectionConsumer(final Destination p0, final String p1, final ServerSessionPool p2, final int p3) throws JMSException;
    
    ConnectionConsumer createDurableConnectionConsumer(final Topic p0, final String p1, final String p2, final ServerSessionPool p3, final int p4) throws JMSException;
}
