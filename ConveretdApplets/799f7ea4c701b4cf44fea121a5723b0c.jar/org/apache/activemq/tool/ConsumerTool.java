// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.tool;

import java.io.IOException;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.Topic;
import javax.jms.MessageListener;

public class ConsumerTool extends ToolSupport implements MessageListener
{
    protected int count;
    protected int dumpCount;
    protected boolean verbose;
    protected int maxiumMessages;
    private boolean pauseBeforeShutdown;
    
    public ConsumerTool() {
        this.dumpCount = 10;
        this.verbose = true;
    }
    
    public static void main(final String[] args) {
        final ConsumerTool tool = new ConsumerTool();
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
            tool.maxiumMessages = Integer.parseInt(args[4]);
        }
        tool.run();
    }
    
    public void run() {
        try {
            System.out.println("Connecting to URL: " + this.url);
            System.out.println("Consuming " + (this.topic ? "topic" : "queue") + ": " + this.subject);
            System.out.println("Using " + (this.durable ? "durable" : "non-durable") + " subscription");
            final Connection connection = this.createConnection();
            final Session session = this.createSession(connection);
            MessageConsumer consumer = null;
            if (this.durable && this.topic) {
                consumer = session.createDurableSubscriber((Topic)this.destination, this.consumerName);
            }
            else {
                consumer = session.createConsumer(this.destination);
            }
            if (this.maxiumMessages <= 0) {
                consumer.setMessageListener(this);
            }
            connection.start();
            if (this.maxiumMessages > 0) {
                this.consumeMessagesAndClose(connection, session, consumer);
            }
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
    
    @Override
    public void onMessage(final Message message) {
        try {
            if (message instanceof TextMessage) {
                final TextMessage txtMsg = (TextMessage)message;
                if (this.verbose) {
                    String msg = txtMsg.getText();
                    if (msg.length() > 50) {
                        msg = msg.substring(0, 50) + "...";
                    }
                    System.out.println("Received: " + msg);
                }
            }
            else if (this.verbose) {
                System.out.println("Received: " + message);
            }
        }
        catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
    
    protected void consumeMessagesAndClose(final Connection connection, final Session session, final MessageConsumer consumer) throws JMSException, IOException {
        System.out.println("We are about to wait until we consume: " + this.maxiumMessages + " message(s) then we will shutdown");
        for (int i = 0; i < this.maxiumMessages; ++i) {
            final Message message = consumer.receive();
            this.onMessage(message);
        }
        System.out.println("Closing connection");
        consumer.close();
        session.close();
        connection.close();
        if (this.pauseBeforeShutdown) {
            System.out.println("Press return to shut down");
            System.in.read();
        }
    }
}
