// 
// Decompiled by Procyon v0.5.30
// 

class Animate extends Thread
{
    animateButton b;
    
    public Animate(final animateButton b) {
        this.b = b;
    }
    
    public void run() {
        while (this.b.running) {
            this.b.advanceFrame();
            this.b.repaint();
            try {
                Thread.sleep(this.b.sleeptime);
            }
            catch (Exception ex) {}
        }
    }
}
