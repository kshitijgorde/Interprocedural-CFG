// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

class AppletTab$FadeCopyrightThread implements Runnable
{
    private AppletTab KV;
    private Thread PU;
    private boolean LV;
    final AppletTab RU;
    
    public AppletTab$FadeCopyrightThread(final AppletTab ru, final AppletTab kv) {
        this.RU = ru;
        this.KV = kv;
        this.LV = true;
        (this.PU = new Thread(this, "MCS-CopyrightFader")).start();
    }
    
    public void stop() {
        this.LV = false;
        try {
            this.PU.interrupt();
        }
        catch (Exception ex) {}
        try {
            this.PU.join();
        }
        catch (Exception ex2) {}
    }
    
    public void run() {
        try {
            while (System.currentTimeMillis() < System.currentTimeMillis() + 10000L) {
                if (!this.LV) {
                    break;
                }
                this.KV.repaint();
                try {
                    Thread.sleep(200L);
                }
                catch (Exception ex) {}
            }
        }
        finally {
            this.PU = null;
        }
        this.PU = null;
    }
}
