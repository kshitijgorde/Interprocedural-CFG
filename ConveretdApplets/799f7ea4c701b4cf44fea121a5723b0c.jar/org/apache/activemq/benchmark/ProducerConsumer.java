// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.benchmark;

public class ProducerConsumer extends Producer
{
    private Consumer consumer;
    
    public ProducerConsumer() {
        this.consumer = new Consumer();
    }
    
    public static void main(final String[] args) {
        final ProducerConsumer tool = new ProducerConsumer();
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
            tool.setDurable(Boolean.getBoolean(args[3]));
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
    
    @Override
    public void run() throws Exception {
        this.consumer.start();
        this.consumer.subscribe();
        this.start();
        this.publish();
    }
    
    @Override
    public void setTopic(final boolean topic) {
        super.setTopic(topic);
        this.consumer.setTopic(topic);
    }
    
    @Override
    public void setSubject(final String subject) {
        super.setSubject(subject);
        this.consumer.setSubject(subject);
    }
    
    @Override
    public void setUrl(final String url) {
        super.setUrl(url);
        this.consumer.setUrl(url);
    }
    
    @Override
    protected boolean useTimerLoop() {
        return false;
    }
    
    public Consumer getConsumer() {
        return this.consumer;
    }
}
