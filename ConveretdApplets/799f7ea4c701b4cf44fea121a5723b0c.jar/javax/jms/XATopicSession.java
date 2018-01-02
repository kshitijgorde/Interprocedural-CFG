// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public interface XATopicSession extends XASession
{
    TopicSession getTopicSession() throws JMSException;
}
