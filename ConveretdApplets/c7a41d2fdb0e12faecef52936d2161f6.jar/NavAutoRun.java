// 
// Decompiled by Procyon v0.5.30
// 

class NavAutoRun implements Runnable
{
    Thread T;
    AnimNav applet;
    int howLong;
    
    NavAutoRun(final AnimNav applet, final int howLong) {
        this.applet = applet;
        this.howLong = howLong;
    }
    
    public void start() {
        if (this.T == null) {
            (this.T = new Thread(this, "T")).start();
            this.T.setPriority(2);
        }
    }
    
    public void run() {
        while (this.T != null) {
            this.applet.repaint();
            final AnimNav applet = this.applet;
            ++applet.frame;
            if (this.applet.frame >= this.applet.imageCount) {
                this.applet.frame = 0;
            }
            this.applet.showStatus(this.applet.URLdescription[this.applet.translate[this.applet.frame]]);
            try {
                Thread.sleep(this.howLong);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        if (this.T != null) {
            this.T.stop();
            this.T = null;
        }
    }
}
