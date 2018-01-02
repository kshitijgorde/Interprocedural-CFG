// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.jms.msg;

import org.xmodel.xml.IXmlIO;
import com.stonewall.cornerstone.utility.ModelBuilder;
import org.xmodel.Xlate;
import java.util.Iterator;
import java.util.Enumeration;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Connection;
import com.stonewall.cornerstone.jms.JmsProvider;
import org.xmodel.Element;
import java.util.HashMap;
import com.stonewall.cornerstone.jms.ProviderFactory;
import javax.jms.JMSException;
import org.xmodel.log.Log;
import java.util.Map;
import javax.jms.Destination;
import org.xmodel.IModelObject;

public abstract class Message
{
    protected IModelObject root;
    protected Destination replyTo;
    private Map<String, String> properties;
    private static int sleepDuration;
    protected long timeToLive;
    protected int priority;
    public static int MinPriority;
    public static int MaxPriority;
    static final Log log;
    
    static {
        Message.sleepDuration = 2000;
        Message.MinPriority = 0;
        Message.MaxPriority = 9;
        log = Log.getLog(Message.class);
    }
    
    protected static Destination getDestination(final String id) throws JMSException {
        if (id == null) {
            throw new JMSException("Problem creating destination: " + id);
        }
        return ProviderFactory.getJmsProvider().getDestination(id);
    }
    
    public Message(final String rootTag) {
        this.properties = new HashMap<String, String>();
        this.timeToLive = 0L;
        this.priority = 4;
        this.root = new Element(rootTag);
    }
    
    public Message() {
        this.properties = new HashMap<String, String>();
        this.timeToLive = 0L;
        this.priority = 4;
    }
    
    public Message(final JmsMessage message, final IModelObject root) throws Exception {
        this.properties = new HashMap<String, String>();
        this.timeToLive = 0L;
        this.priority = 4;
        this.root = root;
        this.processJMSMessageInfo(message);
    }
    
    public Message(final IModelObject root) {
        this.properties = new HashMap<String, String>();
        this.timeToLive = 0L;
        this.priority = 4;
        this.root = root;
    }
    
    protected void _send(final Destination destination) {
        final JmsProvider provider = ProviderFactory.getJmsProvider();
        while (true) {
            try {
                final Connection connection = provider.getConnection();
                final Session session = connection.createSession(false, 1);
                final MessageProducer producer = session.createProducer(destination);
                producer.setTimeToLive(this.timeToLive);
                final JmsMessage jmsg = this.getJMSMessage(session);
                producer.send(jmsg.getMessage());
                producer.close();
                session.close();
            }
            catch (JMSException e) {
                Message.log.error(e);
                try {
                    Thread.sleep(this.sleepDuration());
                }
                catch (InterruptedException ex) {}
                continue;
            }
            break;
        }
    }
    
    protected long sleepDuration() {
        return Message.sleepDuration;
    }
    
    protected void processJMSMessageInfo(final JmsMessage jmsMessage) throws Exception {
        final Enumeration e = jmsMessage.getPropertyNames();
        while (e.hasMoreElements()) {
            final String key = e.nextElement();
            this.properties.put(key, jmsMessage.getStringProperty(key));
        }
    }
    
    public JmsMessage getJMSMessage(final Session session) throws JMSException {
        final MessageBuilder builder = new MessageBuilder();
        final JmsMessage jmsMessage = builder.buildMessage(this.root.cloneTree(), session);
        this.setMessageProperties(jmsMessage);
        jmsMessage.setDeliveryMode(this.getDeliveryMode());
        jmsMessage.setPriority(this.getPriority());
        return jmsMessage;
    }
    
    private void setMessageProperties(final JmsMessage msg) throws JMSException {
        for (final String key : this.properties.keySet()) {
            msg.setStringProperty(key, this.properties.get(key));
        }
        msg.setStringProperty("messageName", this.root.getType());
    }
    
    public IModelObject getDocument() {
        return this.root.cloneTree();
    }
    
    public IModelObject getRoot() {
        return this.root;
    }
    
    public String getRootTag() {
        return this.root.getType();
    }
    
    public boolean isValid() {
        final String valid = Xlate.get(this.root, "valid", (String)null);
        return valid == null || new Boolean(valid);
    }
    
    protected int getDeliveryMode() {
        return 2;
    }
    
    protected int getPriority() {
        return this.priority;
    }
    
    public String getMessageId() {
        return this.getProperty("messageId");
    }
    
    public void setMessageId(final String value) {
        this.setProperty("messageId", value);
    }
    
    protected Map<String, String> getProperties() {
        return this.properties;
    }
    
    protected void setProperties(final Map<String, String> value) {
        this.properties = value;
    }
    
    protected void setProperty(final String key, final String value) {
        this.properties.put(key, value);
    }
    
    protected String getProperty(final String key) {
        return this.properties.get(key);
    }
    
    public void setTimeToLive(final long time) {
        this.timeToLive = time;
    }
    
    public void setPriority(final int pty) {
        if (pty < Message.MinPriority || pty > Message.MaxPriority) {
            throw new IllegalArgumentException("jms priority must be < 9 > -1");
        }
        this.priority = pty;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("\nHeader Properties: ");
        for (final String key : this.properties.keySet()) {
            sb.append(" {");
            sb.append(key);
            sb.append(": ");
            sb.append(this.getProperty(key));
            sb.append("} ");
        }
        sb.append("\n");
        final ModelBuilder builder = new ModelBuilder();
        sb.append(builder.writeModel(this.getRoot(), IXmlIO.Style.printable));
        return sb.toString();
    }
}
