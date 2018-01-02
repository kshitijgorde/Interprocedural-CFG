import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Controller extends Thread
{
    protected IpixViewer host;
    protected Vector translatorList;
    protected Vector listeners;
    protected Translator active;
    protected long wait;
    private long averageFramesInMicroseconds;
    private long dTo;
    private int ConsecutiveZeroRenders;
    private long LastRenderEndPoint;
    private boolean mExit;
    protected boolean mShutdown;
    protected Object mLock;
    private UpdateEvent mUpdateEvent;
    
    Controller(final IpixViewer h) {
        this.translatorList = new Vector();
        this.listeners = new Vector();
        this.wait = 1L;
        this.averageFramesInMicroseconds = 15000L;
        this.dTo = 0L;
        this.ConsecutiveZeroRenders = 1;
        this.LastRenderEndPoint = 0L;
        this.mExit = false;
        this.mShutdown = false;
        this.mLock = new Object();
        this.mUpdateEvent = null;
        this.host = h;
    }
    
    protected Translator createMouseTranslator11() {
        return new MouseTranslator11(this);
    }
    
    public void run() {
        if (this.mExit) {
            return;
        }
        this.setPriority(1);
        final boolean Mac = System.getProperty("os.name").startsWith("Mac");
        final boolean Netscape = System.getProperty("java.vendor").startsWith("Netscape");
        final boolean JDK102 = System.getProperty("java.version").compareTo("1.1") < 0;
        if (Mac && Netscape) {
            this.wait = 50L;
        }
        if (IpixViewer.getMRJ() > 2.1f) {
            this.translatorList.addElement(new KeyboardTranslator(this));
        }
        try {
            if ((Mac && Netscape) || JDK102 || IpixViewer.getMRJ() <= 2.0f) {
                this.translatorList.addElement(new MouseTranslator(this));
            }
            else {
                this.translatorList.addElement(this.createMouseTranslator11());
            }
        }
        catch (Throwable t2) {
            this.translatorList.addElement(new MouseTranslator(this));
        }
        try {
            this.mUpdateEvent = new UpdateEvent();
            while (!this.mExit) {
                while (true) {
                    for (Translator t = this.active; t != null; t = this.active) {
                        if (this.mExit) {
                            return;
                        }
                        float[] p0 = this.host.getViewpoint();
                        final float[] p2 = Util.copy(p0);
                        if (t.updateViewpoint(p2)) {
                            p0 = Util.delta(p0, p2);
                            final boolean updated = this.host.setViewpoint(p2, false);
                            if (!updated && t.interrupt(2)) {
                                this.retireControl(t);
                            }
                            this.mUpdateEvent.setViewpointChange(p0);
                            this.dispatchUpdateEvent(this.mUpdateEvent);
                            if (updated) {
                                final long start = System.currentTimeMillis();
                                this.host.dewarp();
                                long end = System.currentTimeMillis();
                                if (end == start || end - start > 33L) {
                                    Thread.sleep(1L);
                                }
                                else {
                                    Thread.sleep(33L - (end - start));
                                }
                                end = System.currentTimeMillis();
                                long dT = (end - start + (this.wait - 1L)) * 1000L;
                                if (dT == 0L) {
                                    if (this.ConsecutiveZeroRenders > 1) {
                                        dT = (end - this.LastRenderEndPoint) * 1000L;
                                    }
                                    this.LastRenderEndPoint = end;
                                    ++this.ConsecutiveZeroRenders;
                                }
                                if (dT != 0L) {
                                    if (dT < this.averageFramesInMicroseconds * 2L || dT < 2L * this.dTo) {
                                        this.averageFramesInMicroseconds = (dT + (50 - this.ConsecutiveZeroRenders) * this.averageFramesInMicroseconds) / 50L;
                                    }
                                    this.dTo = dT;
                                    this.ConsecutiveZeroRenders = 1;
                                }
                            }
                        }
                        Thread.sleep(this.wait);
                    }
                    if (this.mExit) {
                        continue;
                    }
                    break;
                }
                this.host.setViewpoint(this.host.getViewpoint(), true);
                this.dispatchUpdateEvent(new UpdateEvent(new float[5]));
                this.host.dewarp();
                while (this.active == null && !this.mExit) {
                    Thread.sleep(50L);
                }
            }
        }
        catch (ThreadDeath e) {
            throw e;
        }
        catch (Throwable e2) {
            e2.printStackTrace();
        }
        finally {
            for (int i = 0; i < this.translatorList.size(); ++i) {
                this.translatorList.elementAt(i).close();
            }
            this.translatorList.removeAllElements();
            this.active = null;
            synchronized (this.mLock) {
                this.mShutdown = true;
                this.mExit = false;
            }
            // monitorexit(this.mLock)
        }
        for (int i = 0; i < this.translatorList.size(); ++i) {
            this.translatorList.elementAt(i).close();
        }
        this.translatorList.removeAllElements();
        this.active = null;
        synchronized (this.mLock) {
            this.mShutdown = true;
            this.mExit = false;
        }
        // monitorexit(this.mLock)
    }
    
    void shutdown(final int timeout) throws InterruptedException, IllegalThreadStateException {
        if (Thread.currentThread() == this) {
            throw new IllegalThreadStateException();
        }
        synchronized (this.mLock) {
            if (this.mShutdown) {
                // monitorexit(this.mLock)
                return;
            }
            this.mExit = true;
        }
        // monitorexit(this.mLock)
        final long To = System.currentTimeMillis();
        while (this.mExit && System.currentTimeMillis() - To < timeout) {
            Thread.sleep(10L);
        }
    }
    
    IpixViewer getHost() {
        return this.host;
    }
    
    synchronized boolean requestControl(final Translator t) {
        if (this.active != null && !this.active.interrupt((t != null) ? 1 : 0)) {
            return false;
        }
        this.retireControl(this.active);
        if (t != null) {
            this.active = t;
            if (t instanceof SpinTranslator) {
                this.wait += 25L;
            }
            if (t instanceof ConstantSpeedLocationTranslator || t instanceof ModulatingSpeedLocationTranslator) {
                final IpixViewer host = this.host;
                host.state |= IpixViewer.kSpinningToLocation;
            }
        }
        return true;
    }
    
    synchronized void retireControl(final Translator t) {
        if (t == this.active) {
            this.active = null;
            if (t instanceof SpinTranslator) {
                this.wait -= 25L;
            }
            if (t instanceof ConstantSpeedLocationTranslator || t instanceof ModulatingSpeedLocationTranslator) {
                final IpixViewer host = this.host;
                host.state &= ~IpixViewer.kSpinningToLocation;
            }
        }
    }
    
    boolean isActive(final Translator t) {
        if (t == null) {
            return this.active != null;
        }
        return t == this.active;
    }
    
    Translator getActive() {
        return this.active;
    }
    
    float getFrameRate() {
        return 1000000.0f / this.averageFramesInMicroseconds;
    }
    
    void addUpdateEventListener(final UpdateEventListener l) {
        this.listeners.addElement(l);
    }
    
    void removeUpdateEventListener(final UpdateEventListener l) {
        this.listeners.removeElement(l);
    }
    
    protected void dispatchUpdateEvent(final UpdateEvent e) {
        for (int i = 0; i < this.listeners.size(); ++i) {
            this.listeners.elementAt(i).update(e);
        }
    }
}
