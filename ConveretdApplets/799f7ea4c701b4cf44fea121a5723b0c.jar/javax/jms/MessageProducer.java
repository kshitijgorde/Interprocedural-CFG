// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface MessageProducer
{
    void setDisableMessageID(final boolean p0) throws JMSException;
    
    boolean getDisableMessageID() throws JMSException;
    
    void setDisableMessageTimestamp(final boolean p0) throws JMSException;
    
    boolean getDisableMessageTimestamp() throws JMSException;
    
    void setDeliveryMode(final int p0) throws JMSException;
    
    int getDeliveryMode() throws JMSException;
    
    void setPriority(final int p0) throws JMSException;
    
    int getPriority() throws JMSException;
    
    void setTimeToLive(final long p0) throws JMSException;
    
    long getTimeToLive() throws JMSException;
    
    Destination getDestination() throws JMSException;
    
    void close() throws JMSException;
    
    void send(final Message p0) throws JMSException;
    
    void send(final Message p0, final int p1, final int p2, final long p3) throws JMSException;
    
    void send(final Destination p0, final Message p1) throws JMSException;
    
    void send(final Destination p0, final Message p1, final int p2, final int p3, final long p4) throws JMSException;
}
