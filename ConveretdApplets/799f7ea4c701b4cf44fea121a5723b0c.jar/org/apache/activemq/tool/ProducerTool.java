// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.tool;

import java.util.Date;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Connection;

public class ProducerTool extends ToolSupport
{
    protected int messageCount;
    protected long sleepTime;
    protected boolean verbose;
    protected int messageSize;
    
    public ProducerTool() {
        this.messageCount = 10;
        this.verbose = true;
        this.messageSize = 255;
    }
    
    public static void main(final String[] args) {
        runTool(args, new ProducerTool());
    }
    
    protected static void runTool(final String[] args, final ProducerTool tool) {
        if (args.length > 0) {
            tool.url = args[0];
        }
        if (args.length > 1) {
            tool.topic = args[1].equalsIgnoreCase("true");
        }
        if (args.length > 2) {
            tool.subject = args[2];
        }
        if (args.length > 3) {
            tool.durable = args[3].equalsIgnoreCase("true");
        }
        if (args.length > 4) {
            tool.messageCount = Integer.parseInt(args[4]);
        }
        if (args.length > 5) {
            tool.messageSize = Integer.parseInt(args[5]);
        }
        tool.run();
    }
    
    public void run() {
        try {
            System.out.println("Connecting to URL: " + this.url);
            System.out.println("Publishing a Message with size " + this.messageSize + " to " + (this.topic ? "topic" : "queue") + ": " + this.subject);
            System.out.println("Using " + (this.durable ? "durable" : "non-durable") + " publishing");
            final Connection connection = this.createConnection();
            final Session session = this.createSession(connection);
            final MessageProducer producer = this.createProducer(session);
            this.sendLoop(session, producer);
            System.out.println("Done.");
            this.close(connection, session);
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
    
    protected MessageProducer createProducer(final Session session) throws JMSException {
        final MessageProducer producer = session.createProducer(this.destination);
        if (this.durable) {
            producer.setDeliveryMode(2);
        }
        else {
            producer.setDeliveryMode(1);
        }
        return producer;
    }
    
    protected void sendLoop(final Session session, final MessageProducer producer) throws Exception {
        for (int i = 0; i < this.messageCount; ++i) {
            final TextMessage message = session.createTextMessage(this.createMessageText(i));
            if (this.verbose) {
                String msg = message.getText();
                if (msg.length() > 50) {
                    msg = msg.substring(0, 50) + "...";
                }
                System.out.println("Sending message: " + msg);
            }
            producer.send(message);
            Thread.sleep(this.sleepTime);
        }
        producer.send(session.createMessage());
    }
    
    private String createMessageText(final int index) {
        final StringBuffer buffer = new StringBuffer(this.messageSize);
        buffer.append("Message: " + index + " sent at: " + new Date());
        if (buffer.length() > this.messageSize) {
            return buffer.substring(0, this.messageSize);
        }
        for (int i = buffer.length(); i < this.messageSize; ++i) {
            buffer.append(' ');
        }
        return buffer.toString();
    }
}
