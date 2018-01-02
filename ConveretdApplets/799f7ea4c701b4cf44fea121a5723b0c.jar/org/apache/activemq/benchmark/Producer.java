// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.benchmark;

import java.io.IOException;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Destination;
import javax.jms.Session;
import javax.jms.JMSException;

public class Producer extends BenchmarkSupport
{
    int loops;
    int loopSize;
    private int messageSize;
    
    public Producer() {
        this.loops = -1;
        this.loopSize = 1000;
        this.messageSize = 1000;
    }
    
    public static void main(final String[] args) {
        final Producer tool = new Producer();
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
            tool.setMessageSize(Integer.parseInt(args[4]));
        }
        if (args.length > 5) {
            tool.setConnectionCount(Integer.parseInt(args[5]));
        }
        try {
            tool.run();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
    
    public void run() throws Exception {
        this.start();
        this.publish();
    }
    
    public int getMessageSize() {
        return this.messageSize;
    }
    
    public void setMessageSize(final int messageSize) {
        this.messageSize = messageSize;
    }
    
    public int getLoopSize() {
        return this.loopSize;
    }
    
    public void setLoopSize(final int loopSize) {
        this.loopSize = loopSize;
    }
    
    protected void publish() throws Exception {
        final String text = this.getMessage();
        System.out.println("Publishing to: " + this.subjects.length + " subject(s)");
        for (int i = 0; i < this.subjects.length; ++i) {
            final String subject = this.subjects[i];
            final Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        Producer.this.publish(text, subject);
                    }
                    catch (JMSException e) {
                        System.out.println("Caught: " + e);
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }
    
    protected String getMessage() {
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < this.messageSize; ++i) {
            final char ch = 'X';
            buffer.append(ch);
        }
        return buffer.toString();
    }
    
    protected void publish(final String text, final String subject) throws JMSException {
        final Session session = this.createSession();
        final Destination destination = this.createDestination(session, subject);
        final MessageProducer publisher = session.createProducer(destination);
        if (this.isDurable()) {
            publisher.setDeliveryMode(2);
        }
        else {
            publisher.setDeliveryMode(1);
        }
        System.out.println("Starting publisher on : " + destination + " of type: " + destination.getClass().getName());
        System.out.println("Message length: " + text.length());
        if (this.loops > 0) {
            for (int i = 0; i < this.loops; ++i) {
                this.publishLoop(session, publisher, text);
            }
            return;
        }
        while (true) {
            this.publishLoop(session, publisher, text);
        }
    }
    
    protected void publishLoop(final Session session, final MessageProducer publisher, final String text) throws JMSException {
        for (int i = 0; i < this.loopSize; ++i) {
            final Message message = session.createTextMessage(text);
            publisher.send(message);
            this.count(1);
        }
    }
    
    protected String loadFile(final String file) throws IOException {
        System.out.println("Loading file: " + file);
        final StringBuffer buffer = new StringBuffer();
        final BufferedReader in = new BufferedReader(new FileReader(file));
        while (true) {
            final String line = in.readLine();
            if (line == null) {
                break;
            }
            buffer.append(line);
            buffer.append(File.separator);
        }
        in.close();
        return buffer.toString();
    }
    
    public int getLoops() {
        return this.loops;
    }
    
    public void setLoops(final int loops) {
        this.loops = loops;
    }
}
