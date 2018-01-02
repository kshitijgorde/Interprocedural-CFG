// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

import java.io.Serializable;

public interface Session extends Runnable
{
    public static final int AUTO_ACKNOWLEDGE = 1;
    public static final int CLIENT_ACKNOWLEDGE = 2;
    public static final int DUPS_OK_ACKNOWLEDGE = 3;
    public static final int SESSION_TRANSACTED = 0;
    
    BytesMessage createBytesMessage() throws JMSException;
    
    MapMessage createMapMessage() throws JMSException;
    
    Message createMessage() throws JMSException;
    
    ObjectMessage createObjectMessage() throws JMSException;
    
    ObjectMessage createObjectMessage(final Serializable p0) throws JMSException;
    
    StreamMessage createStreamMessage() throws JMSException;
    
    TextMessage createTextMessage() throws JMSException;
    
    TextMessage createTextMessage(final String p0) throws JMSException;
    
    boolean getTransacted() throws JMSException;
    
    int getAcknowledgeMode() throws JMSException;
    
    void commit() throws JMSException;
    
    void rollback() throws JMSException;
    
    void close() throws JMSException;
    
    void recover() throws JMSException;
    
    MessageListener getMessageListener() throws JMSException;
    
    void setMessageListener(final MessageListener p0) throws JMSException;
    
    void run();
    
    MessageProducer createProducer(final Destination p0) throws JMSException;
    
    MessageConsumer createConsumer(final Destination p0) throws JMSException;
    
    MessageConsumer createConsumer(final Destination p0, final String p1) throws JMSException;
    
    MessageConsumer createConsumer(final Destination p0, final String p1, final boolean p2) throws JMSException;
    
    Queue createQueue(final String p0) throws JMSException;
    
    Topic createTopic(final String p0) throws JMSException;
    
    TopicSubscriber createDurableSubscriber(final Topic p0, final String p1) throws JMSException;
    
    TopicSubscriber createDurableSubscriber(final Topic p0, final String p1, final String p2, final boolean p3) throws JMSException;
    
    QueueBrowser createBrowser(final Queue p0) throws JMSException;
    
    QueueBrowser createBrowser(final Queue p0, final String p1) throws JMSException;
    
    TemporaryQueue createTemporaryQueue() throws JMSException;
    
    TemporaryTopic createTemporaryTopic() throws JMSException;
    
    void unsubscribe(final String p0) throws JMSException;
}
