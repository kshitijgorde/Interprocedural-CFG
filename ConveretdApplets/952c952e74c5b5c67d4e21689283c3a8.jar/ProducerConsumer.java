// 
// Decompiled by Procyon v0.5.30
// 

public class ProducerConsumer
{
    private int steps;
    
    public ProducerConsumer() {
        this.steps = 0;
    }
    
    public synchronized void consume() {
        while (this.steps <= 0) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        --this.steps;
        System.out.println("Step Consumed");
        this.notifyAll();
    }
    
    public synchronized void produce() {
        ++this.steps;
        System.out.println("Step Produced");
        this.notifyAll();
    }
    
    public synchronized void consumeAll() {
        this.steps = 0;
    }
}
