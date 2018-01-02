// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Blink implements Runnable
{
    int c;
    Thread thread;
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.c *= -1;
        }
    }
    
    public SG1_Blink() {
        this.c = 1;
        (this.thread = new Thread(this)).start();
    }
}
