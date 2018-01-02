// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class TopicRequestor
{
    private TopicSession session;
    private Topic topic;
    private TemporaryTopic temporaryTopic;
    private TopicPublisher publisher;
    private TopicSubscriber subscriber;
    
    public TopicRequestor(final TopicSession session, final Topic topic) throws JMSException {
        this.setSession(session);
        this.setTopic(topic);
        this.setTemporaryTopic(session.createTemporaryTopic());
        this.setPublisher(session.createPublisher(topic));
        this.setSubscriber(session.createSubscriber(this.getTemporaryTopic()));
    }
    
    public Message request(final Message message) throws JMSException {
        message.setJMSReplyTo(this.getTemporaryTopic());
        this.getPublisher().publish(message);
        return this.getSubscriber().receive();
    }
    
    public void close() throws JMSException {
        this.getSession().close();
        this.getTemporaryTopic().delete();
    }
    
    private void setPublisher(final TopicPublisher publisher) {
        this.publisher = publisher;
    }
    
    private TopicPublisher getPublisher() {
        return this.publisher;
    }
    
    private void setSession(final TopicSession session) {
        this.session = session;
    }
    
    private TopicSession getSession() {
        return this.session;
    }
    
    private void setSubscriber(final TopicSubscriber subscriber) {
        this.subscriber = subscriber;
    }
    
    private TopicSubscriber getSubscriber() {
        return this.subscriber;
    }
    
    private void setTemporaryTopic(final TemporaryTopic temporaryTopic) {
        this.temporaryTopic = temporaryTopic;
    }
    
    private TemporaryTopic getTemporaryTopic() {
        return this.temporaryTopic;
    }
    
    private void setTopic(final Topic topic) {
        this.topic = topic;
    }
    
    private Topic getTopic() {
        return this.topic;
    }
}
