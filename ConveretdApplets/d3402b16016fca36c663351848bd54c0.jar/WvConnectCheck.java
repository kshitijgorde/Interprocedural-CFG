// 
// Decompiled by Procyon v0.5.30
// 

public class WvConnectCheck implements Runnable
{
    WvAppletTemplate applet;
    WvDispatcher dispatcher;
    Thread thread;
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public WvConnectCheck(final WvAppletTemplate applet, final WvDispatcher dispatcher) {
        this.applet = applet;
        this.dispatcher = dispatcher;
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            if (this.applet.connectFlag) {
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex) {}
                this.dispatcher.connect();
                this.applet.connectFlag = false;
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex2) {}
        }
    }
}
