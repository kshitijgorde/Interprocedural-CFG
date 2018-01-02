// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XATopicConnectionFactory extends XAConnectionFactory, TopicConnectionFactory
{
    XATopicConnection createXATopicConnection() throws JMSException;
    
    XATopicConnection createXATopicConnection(final String p0, final String p1) throws JMSException;
}
