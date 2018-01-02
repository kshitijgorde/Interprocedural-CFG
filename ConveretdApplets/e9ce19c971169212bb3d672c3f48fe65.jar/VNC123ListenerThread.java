// 
// Decompiled by Procyon v0.5.30
// 

public class VNC123ListenerThread extends Thread
{
    private boolean stop;
    private VNC123ComThread LocalToHub;
    private VNC123ComThread HubToLocal;
    private Process pVNCLocalExe;
    private VNC123Main applet;
    
    public VNC123ListenerThread(final VNC123Main applet, final VNC123ComThread LocalToHub, final VNC123ComThread HubToLocal, final Process pVNCLocalExe) throws Exception {
        this.stop = false;
        this.LocalToHub = null;
        this.HubToLocal = null;
        this.pVNCLocalExe = null;
        this.applet = null;
        this.LocalToHub = LocalToHub;
        this.HubToLocal = HubToLocal;
        this.pVNCLocalExe = pVNCLocalExe;
        this.applet = applet;
    }
    
    public void run() {
        try {
            System.out.println("Main Listener ...");
            while (!this.stop) {
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex) {}
                if (this.LocalToHub != null && !this.LocalToHub.isAlive()) {
                    break;
                }
                if (this.HubToLocal != null && !this.HubToLocal.isAlive()) {
                    break;
                }
                if (this.pVNCLocalExe == null) {
                    continue;
                }
                try {
                    this.pVNCLocalExe.exitValue();
                    break;
                }
                catch (Exception ex2) {}
            }
            if (!this.stop) {
                this.applet.stop();
                System.exit(1);
            }
        }
        catch (Exception e) {
            System.err.println("VNC123ListenerThread error : " + e);
            e.printStackTrace();
        }
    }
    
    public void stopMe() {
        this.stop = true;
    }
}
