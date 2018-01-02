// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xidget;

import javax.jms.Connection;
import com.stonewall.cornerstone.jms.JmsProvider;
import javax.jms.BytesMessage;
import java.io.IOException;
import javax.jms.Message;
import javax.jms.JMSException;
import com.stonewall.cornerstone.jms.ProviderFactory;
import org.xmodel.net.Protocol;
import java.nio.ByteBuffer;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Destination;
import javax.jms.MessageListener;
import org.xmodel.net.ILink;

public class JmsLink implements ILink, MessageListener
{
    public static final int connectionRetrySleep = 2000;
    private String incoming;
    private String outgoing;
    private Destination inQueue;
    private Destination outQueue;
    private Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;
    private ByteBuffer buffer;
    private Protocol protocol;
    
    public JmsLink(final String incoming, final String outgoing, final int timeout) throws JMSException {
        this.incoming = incoming;
        this.outgoing = outgoing;
        this.inQueue = ProviderFactory.getJmsProvider().getDestination(incoming);
        this.outQueue = ProviderFactory.getJmsProvider().getDestination(outgoing);
        this.buffer = ByteBuffer.allocate(8192);
        this.protocol = new Protocol(timeout);
    }
    
    public String getIncomingQueue() {
        return this.incoming;
    }
    
    public String getOutgoingQueue() {
        return this.outgoing;
    }
    
    public Protocol getProtocol() {
        return this.protocol;
    }
    
    @Override
    public void send(final ByteBuffer buffer) throws IOException {
        this.open();
        try {
            final BytesMessage message = this.session.createBytesMessage();
            message.writeBytes(buffer.array(), buffer.position(), buffer.remaining());
            this.producer.send(message);
        }
        catch (JMSException e) {
            final String message2 = String.format("Unable to send to JMS queue: %s.", this.outgoing);
            throw new IOException(message2, e);
        }
    }
    
    public void open() {
        if (this.isOpen()) {
            return;
        }
        final JmsProvider provider = ProviderFactory.getJmsProvider();
        while (true) {
            try {
                final Connection connection = provider.getConnection();
                this.session = connection.createSession(false, 1);
                (this.producer = this.session.createProducer(this.outQueue)).setDeliveryMode(1);
                this.producer.setDisableMessageID(true);
                this.producer.setDisableMessageTimestamp(true);
                (this.consumer = this.session.createConsumer(this.inQueue)).setMessageListener(this);
            }
            catch (JMSException e) {
                e.printStackTrace(System.err);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
                continue;
            }
            break;
        }
    }
    
    @Override
    public boolean isOpen() {
        return this.session != null;
    }
    
    @Override
    public void close() {
        try {
            this.consumer.close();
            this.consumer = null;
        }
        catch (JMSException e) {
            e.printStackTrace(System.err);
        }
        try {
            this.producer.close();
            this.producer = null;
        }
        catch (JMSException e) {
            e.printStackTrace(System.err);
        }
        try {
            this.session.close();
            this.session = null;
        }
        catch (JMSException e) {
            e.printStackTrace(System.err);
        }
    }
    
    @Override
    public void onMessage(final Message message) {
        try {
            if (this.buffer.position() < this.buffer.limit()) {
                this.buffer.position(this.buffer.limit());
                this.buffer.limit(this.buffer.capacity());
            }
            else {
                this.buffer.clear();
            }
            this.insureCapacity();
            final BytesMessage bytesMessage = (BytesMessage)message;
            final int nread = bytesMessage.readBytes(this.buffer.array());
            if (nread > 0) {
                this.buffer.position(0);
                this.buffer.limit(nread);
                this.protocol.onReceive(this, this.buffer);
            }
        }
        catch (JMSException e) {
            e.printStackTrace(System.err);
            this.close();
        }
    }
    
    private void insureCapacity() {
        if (this.buffer.position() == this.buffer.limit()) {
            final ByteBuffer larger = ByteBuffer.allocate(this.buffer.capacity() << 1);
            this.buffer.flip();
            larger.put(this.buffer);
            this.buffer = larger;
        }
    }
}
