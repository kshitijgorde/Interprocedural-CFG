// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import java.util.Enumeration;
import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.Message;

public class JmsMessage
{
    private Message message;
    
    public JmsMessage(final Message message) {
        this.message = message;
    }
    
    public Message getMessage() {
        return this.message;
    }
    
    public Destination getReplyTo() throws JMSException {
        return this.message.getJMSReplyTo();
    }
    
    public void setReplyTo(final Destination d) throws JMSException {
        this.message.setJMSReplyTo(d);
    }
    
    public String getMessageId() throws JMSException {
        return this.message.getJMSMessageID();
    }
    
    public String getStringProperty(final String s) throws JMSException {
        return this.message.getStringProperty(s);
    }
    
    public Enumeration getPropertyNames() throws JMSException {
        return this.message.getPropertyNames();
    }
    
    public String getCorrelation() throws JMSException {
        return this.message.getJMSCorrelationID();
    }
    
    public void setCorrelation(final String s) throws JMSException {
        this.message.setJMSCorrelationID(s);
    }
    
    public void setStringProperty(final String key, final String value) throws JMSException {
        this.message.setStringProperty(key, value);
    }
    
    public void setDeliveryMode(final int mode) throws JMSException {
        this.message.setJMSDeliveryMode(mode);
    }
    
    public void setPriority(final int priority) throws JMSException {
        this.message.setJMSPriority(priority);
    }
}
