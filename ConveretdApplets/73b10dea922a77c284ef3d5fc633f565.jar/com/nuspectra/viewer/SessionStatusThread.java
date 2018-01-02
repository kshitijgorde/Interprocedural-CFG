// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

public class SessionStatusThread extends Thread
{
    ControlSession session;
    boolean isRunnable;
    
    public SessionStatusThread(final ControlSession session) {
        this.isRunnable = true;
        this.session = session;
        this.setName("SessionStatusThread");
    }
    
    private SessionStatusThread() {
        this.isRunnable = true;
    }
    
    void quit() {
        this.isRunnable = false;
        this.interrupt();
    }
    
    public void run() {
        this.isRunnable = true;
        Debug.println("SessionStatusThread run " + this.toString());
        Debug.println(" " + this.session.toString());
        while (this.isRunnable) {
            try {
                if (this.session.cameraState() == 7) {
                    break;
                }
                final Reply reply = this.session.queryCameraState();
                if (this.session.disconnected()) {
                    break;
                }
                if (reply.getErrorString() != null) {
                    this.session.sessionStatusDied("session notice error:" + reply.getErrorString());
                    break;
                }
                Thread.sleep(10000L);
            }
            catch (InterruptedException ex) {}
            catch (Throwable e) {
                Debug.report(e, "SessionStatusThread: run exception");
                this.session.sessionStatusDied("exception:" + e.toString());
                break;
            }
        }
        this.isRunnable = false;
        Debug.println("SessionStatusThread exit " + this.toString());
    }
}
