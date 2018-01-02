// 
// Decompiled by Procyon v0.5.30
// 

class Timer extends Thread
{
    Slider parent;
    public long delay;
    
    Timer(final String s, final Slider parent, final long delay) {
        super(s);
        this.parent = parent;
        this.delay = delay;
    }
    
    public void run() {
        try {
            while (true) {
                this.parent.repaint();
                Thread.sleep(this.delay);
            }
        }
        catch (InterruptedException ex) {}
    }
}
