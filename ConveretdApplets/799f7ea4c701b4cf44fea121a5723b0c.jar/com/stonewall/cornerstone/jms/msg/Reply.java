// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import javax.jms.JMSException;
import javax.jms.Session;
import org.xmodel.IModelObject;
import javax.jms.Destination;

public abstract class Reply extends Message
{
    private String jmsCorrelationId;
    private Destination replyTo;
    
    public Reply(final String rootTag) {
        super(rootTag);
    }
    
    public Reply(final String rootTag, final Request request) {
        super(rootTag);
        this.replyTo = request.jmsReplyTo;
        this.setMessageId(request.getMessageId());
        this.setProperties(request.getProperties());
        this.jmsCorrelationId = request.getJmsMessageId();
    }
    
    public Reply(final IModelObject root) {
        super(root);
    }
    
    public Reply(final JmsMessage message, final IModelObject o) throws Exception {
        super(message, o);
    }
    
    public Status getStatus() {
        final String status = this.getProperty("status");
        if (status == null) {
            return Status.succeeded;
        }
        return Enum.valueOf(Status.class, status);
    }
    
    public void setStatus(final Status status) {
        this.setProperty("status", status.name());
    }
    
    public boolean succeeded() {
        return this.getStatus().equals(Status.succeeded);
    }
    
    public void processJMSMessageInfo(final JmsMessage jmsMessage) throws Exception {
        super.processJMSMessageInfo(jmsMessage);
        this.jmsCorrelationId = jmsMessage.getCorrelation();
    }
    
    @Override
    public JmsMessage getJMSMessage(final Session session) throws JMSException {
        final JmsMessage message = super.getJMSMessage(session);
        message.setCorrelation(this.jmsCorrelationId);
        return message;
    }
    
    public void send() {
        if (this.replyTo == null) {
            return;
        }
        Reply.log.debug("Reply sent: " + this);
        super._send(this.replyTo);
    }
    
    public String getTaskId() {
        return this.getProperty("taskId");
    }
    
    public String getTaskSignature() {
        return this.getProperty("taskSignature");
    }
    
    public enum Status
    {
        succeeded("succeeded", 0), 
        failed("failed", 1), 
        rejected("rejected", 2);
        
        private Status(final String s, final int n) {
        }
    }
}
