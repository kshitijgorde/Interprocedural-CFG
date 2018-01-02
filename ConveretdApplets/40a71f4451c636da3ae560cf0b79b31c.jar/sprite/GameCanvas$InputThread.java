// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Event;
import java.util.Vector;

class GameCanvas$InputThread extends Thread
{
    private GameCanvas \u00d2;
    private boolean m_bStopped;
    final GameCanvas \u00e7;
    
    void \u00e7() {
        this.m_bStopped = true;
    }
    
    GameCanvas$InputThread(final GameCanvas \u00e7, final GameCanvas \u00f2) {
        (this.\u00e7 = \u00e7).getClass();
        this.\u00d2 = \u00f2;
        this.m_bStopped = false;
        this.start();
    }
    
    private void \u00e8() {
        final Vector \u00e4 = this.\u00d2.\u00e4;
        synchronized (\u00e4) {
            while (\u00e4.size() == 0 && !this.m_bStopped) {
                try {
                    \u00e4.wait(3000L);
                }
                catch (InterruptedException ex) {
                    System.out.println(ex.toString());
                    // monitorexit(\u00e4)
                    return;
                }
            }
        }
        // monitorexit(\u00e4)
    }
    
    public void run() {
        while (!this.m_bStopped) {
            try {
                final Vector \u00e4 = this.\u00d2.\u00e4;
                synchronized (\u00e4) {
                    if (\u00e4.size() > 0) {
                        this.\u00d2.m_saveEvent = \u00e4.firstElement();
                        \u00e4.removeElementAt(0);
                    }
                }
                // monitorexit(\u00e4)
                this.\u00d2.processEvent();
                this.\u00d2.m_saveEvent = null;
                this.\u00e8();
            }
            catch (RuntimeException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
