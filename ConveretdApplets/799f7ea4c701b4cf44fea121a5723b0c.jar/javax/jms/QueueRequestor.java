// 
// Decompiled by Procyon v0.5.30
// 

package javax.jms;

public class QueueRequestor
{
    private QueueSession session;
    private TemporaryQueue temporaryQueue;
    private QueueSender sender;
    private QueueReceiver receiver;
    
    public QueueRequestor(final QueueSession session, final Queue queue) throws JMSException {
        if (queue == null) {
            throw new InvalidDestinationException("Invalid queue");
        }
        this.setSession(session);
        this.setTemporaryQueue(session.createTemporaryQueue());
        this.setSender(session.createSender(queue));
        this.setReceiver(session.createReceiver(this.getTemporaryQueue()));
    }
    
    public Message request(final Message message) throws JMSException {
        message.setJMSReplyTo(this.getTemporaryQueue());
        this.getSender().send(message);
        return this.getReceiver().receive();
    }
    
    public void close() throws JMSException {
        this.getSession().close();
        this.getTemporaryQueue().delete();
    }
    
    private void setReceiver(final QueueReceiver receiver) {
        this.receiver = receiver;
    }
    
    private QueueReceiver getReceiver() {
        return this.receiver;
    }
    
    private void setSender(final QueueSender sender) {
        this.sender = sender;
    }
    
    private QueueSender getSender() {
        return this.sender;
    }
    
    private void setSession(final QueueSession session) {
        this.session = session;
    }
    
    private QueueSession getSession() {
        return this.session;
    }
    
    private void setTemporaryQueue(final TemporaryQueue temporaryQueue) {
        this.temporaryQueue = temporaryQueue;
    }
    
    private TemporaryQueue getTemporaryQueue() {
        return this.temporaryQueue;
    }
}
