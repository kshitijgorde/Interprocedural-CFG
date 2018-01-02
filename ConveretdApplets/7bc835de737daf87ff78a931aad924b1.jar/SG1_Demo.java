// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Demo implements Runnable
{
    boolean running;
    String resp;
    Thread thread;
    
    public SG1_Demo() {
        this.running = true;
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        this.resp = "";
        this.running = true;
        try {
            Thread.sleep(5000L);
        }
        catch (InterruptedException ex) {}
        this.resp = "This is DEMO version of StarGate applet *so your mail was not actualy sent*To buy full version contact author: *            bigor@most.gfos.hr* *or visit authors StarGate homepage at:*       http://www.gfos.hr/~bigor/StarGate* * PRICE: 20$";
        this.running = false;
    }
}
