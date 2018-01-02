// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XATopicConnection extends XAConnection, TopicConnection
{
    XATopicSession createXATopicSession() throws JMSException;
    
    TopicSession createTopicSession(final boolean p0, final int p1) throws JMSException;
}
