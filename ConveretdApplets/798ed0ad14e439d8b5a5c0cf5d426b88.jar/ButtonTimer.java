// 
// Decompiled by Procyon v0.5.30
// 

class ButtonTimer extends Thread
{
    private WAutomaticButton b;
    public boolean pressed;
    public boolean fired;
    
    public ButtonTimer(final WAutomaticButton b) {
        this.b = b;
        this.fired = false;
        this.start();
    }
    
    public final void run() {
        try {
            Thread.sleep(this.b.waitTime);
            while (this.pressed) {
                this.b.fireAction();
                this.fired = true;
                Thread.sleep(this.b.repeatTime);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
