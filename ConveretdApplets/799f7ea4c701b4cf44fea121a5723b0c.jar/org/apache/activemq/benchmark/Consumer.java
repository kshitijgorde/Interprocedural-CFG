// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.benchmark;

import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.JMSException;
import javax.jms.MessageListener;

public class Consumer extends BenchmarkSupport implements MessageListener
{
    public static void main(final String[] args) {
        final Consumer tool = new Consumer();
        if (args.length > 0) {
            tool.setUrl(args[0]);
        }
        if (args.length > 1) {
            tool.setTopic(BenchmarkSupport.parseBoolean(args[1]));
        }
        if (args.length > 2) {
            tool.setSubject(args[2]);
        }
        if (args.length > 3) {
            tool.setDurable(BenchmarkSupport.parseBoolean(args[3]));
        }
        if (args.length > 4) {
            tool.setConnectionCount(Integer.parseInt(args[4]));
        }
        try {
            tool.run();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
    
    public void run() throws JMSException {
        this.start();
        this.subscribe();
    }
    
    protected void subscribe() throws JMSException {
        for (int i = 0; i < this.subjects.length; ++i) {
            this.subscribe(this.subjects[i]);
        }
    }
    
    protected void subscribe(final String subject) throws JMSException {
        final Session session = this.createSession();
        final Destination destination = this.createDestination(session, subject);
        System.out.println("Consuming on : " + destination + " of type: " + destination.getClass().getName());
        MessageConsumer consumer = null;
        if (this.isDurable() && this.isTopic()) {
            consumer = session.createDurableSubscriber((Topic)destination, this.getClass().getName());
        }
        else {
            consumer = session.createConsumer(destination);
        }
        consumer.setMessageListener(this);
        this.addResource(consumer);
    }
    
    @Override
    public void onMessage(final Message message) {
        try {
            final TextMessage textMessage = (TextMessage)message;
            textMessage.getText();
            this.count(1);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
