// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.benchmark;

import javax.jms.JMSException;
import javax.jms.Connection;
import javax.jms.Session;
import java.util.ArrayList;
import org.apache.activemq.util.IdGenerator;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.NumberFormat;
import java.util.List;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Destination;

public class BenchmarkSupport
{
    protected int connectionCount;
    protected int batch;
    protected Destination destination;
    protected String[] subjects;
    private boolean topic;
    private boolean durable;
    private ActiveMQConnectionFactory factory;
    private String url;
    private int counter;
    private List<Object> resources;
    private NumberFormat formatter;
    private AtomicInteger connectionCounter;
    private IdGenerator idGenerator;
    private boolean timerLoop;
    
    public BenchmarkSupport() {
        this.connectionCount = 1;
        this.batch = 1000;
        this.topic = true;
        this.resources = new ArrayList<Object>();
        this.formatter = NumberFormat.getInstance();
        this.connectionCounter = new AtomicInteger(0);
        this.idGenerator = new IdGenerator();
    }
    
    public void start() {
        System.out.println("Using: " + this.connectionCount + " connection(s)");
        this.subjects = new String[this.connectionCount];
        for (int i = 0; i < this.connectionCount; ++i) {
            this.subjects[i] = "BENCHMARK.FEED" + i;
        }
        if (this.useTimerLoop()) {
            final Thread timer = new Thread() {
                @Override
                public void run() {
                    BenchmarkSupport.this.timerLoop();
                }
            };
            timer.start();
        }
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public boolean isTopic() {
        return this.topic;
    }
    
    public void setTopic(final boolean topic) {
        this.topic = topic;
    }
    
    public ActiveMQConnectionFactory getFactory() {
        return this.factory;
    }
    
    public void setFactory(final ActiveMQConnectionFactory factory) {
        this.factory = factory;
    }
    
    public void setSubject(final String subject) {
        this.connectionCount = 1;
        this.subjects = new String[] { subject };
    }
    
    public boolean isDurable() {
        return this.durable;
    }
    
    public void setDurable(final boolean durable) {
        this.durable = durable;
    }
    
    public int getConnectionCount() {
        return this.connectionCount;
    }
    
    public void setConnectionCount(final int connectionCount) {
        this.connectionCount = connectionCount;
    }
    
    protected Session createSession() throws JMSException {
        if (this.factory == null) {
            this.factory = this.createFactory();
        }
        final Connection connection = this.factory.createConnection();
        final int value = this.connectionCounter.incrementAndGet();
        System.out.println("Created connection: " + value + " = " + connection);
        if (this.durable) {
            connection.setClientID(this.idGenerator.generateId());
        }
        this.addResource(connection);
        connection.start();
        final Session session = connection.createSession(false, 1);
        this.addResource(session);
        return session;
    }
    
    protected ActiveMQConnectionFactory createFactory() {
        final ActiveMQConnectionFactory answer = new ActiveMQConnectionFactory(this.getUrl());
        return answer;
    }
    
    protected synchronized void count(final int count) {
        this.counter += count;
    }
    
    protected synchronized int resetCount() {
        final int answer = this.counter;
        this.counter = 0;
        return answer;
    }
    
    protected void timerLoop() {
        int times = 0;
        int total = 0;
        final int dumpVmStatsFrequency = 10;
        final Runtime runtime = Runtime.getRuntime();
        while (true) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            final int processed = this.resetCount();
            double average = 0.0;
            if (processed > 0) {
                total += processed;
                ++times;
            }
            if (times > 0) {
                average = total / times;
            }
            System.out.println(this.getClass().getName() + " Processed: " + processed + " messages this second. Average: " + average);
            if (times % dumpVmStatsFrequency == 0 && times != 0) {
                System.out.println("Used memory: " + this.asMemoryString(runtime.totalMemory() - runtime.freeMemory()) + " Free memory: " + this.asMemoryString(runtime.freeMemory()) + " Total memory: " + this.asMemoryString(runtime.totalMemory()) + " Max memory: " + this.asMemoryString(runtime.maxMemory()));
            }
        }
    }
    
    protected String asMemoryString(final long value) {
        return this.formatter.format(value / 1024L) + " K";
    }
    
    protected boolean useTimerLoop() {
        return this.timerLoop;
    }
    
    protected Destination createDestination(final Session session, final String subject) throws JMSException {
        if (this.topic) {
            return session.createTopic(subject);
        }
        return session.createQueue(subject);
    }
    
    protected void addResource(final Object resource) {
        this.resources.add(resource);
    }
    
    public int getCounter() {
        return this.counter;
    }
    
    public void setTimerLoop(final boolean timerLoop) {
        this.timerLoop = timerLoop;
    }
    
    protected static boolean parseBoolean(final String text) {
        return text.equalsIgnoreCase("true");
    }
}
