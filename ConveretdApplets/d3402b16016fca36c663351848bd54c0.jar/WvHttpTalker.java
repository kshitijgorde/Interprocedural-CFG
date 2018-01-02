// 
// Decompiled by Procyon v0.5.30
// 

abstract class WvHttpTalker implements Runnable, WvEventListener
{
    protected WvDispatcher wvDispatcher;
    protected Thread thread;
    protected String idStr;
    protected boolean connected;
    protected boolean videoConnected;
    protected boolean cameraConnected;
    
    private final void stop() {
        this.connected = false;
        this.cameraConnected = false;
        this.videoConnected = false;
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public final void kickOff() {
        this.connected = false;
        this.cameraConnected = false;
        this.videoConnected = false;
    }
    
    public final void cameraConnected(final boolean cameraConnected) {
        this.cameraConnected = cameraConnected;
    }
    
    protected abstract String getThreadName();
    
    public final void disconnect(final int n) {
        this.stop();
    }
    
    public final void videoConnected(final boolean videoConnected) {
        this.videoConnected = videoConnected;
    }
    
    public WvHttpTalker(final WvDispatcher wvDispatcher) {
        this.wvDispatcher = wvDispatcher;
    }
    
    public abstract void run();
    
    protected final void sleepWhile(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public final void connect(final String idStr) {
        this.idStr = idStr;
        this.connected = true;
        if (this.thread != null && this.thread.isAlive()) {
            return;
        }
        (this.thread = new Thread(this, this.getThreadName())).start();
    }
}
