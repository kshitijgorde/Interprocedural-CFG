// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

public class ControlTimer implements Runnable
{
    volatile Thread m_Timer;
    private Controler _$210605;
    private int _$210613;
    
    public ControlTimer(final Controler $210605) {
        this.m_Timer = null;
        this._$210605 = $210605;
        this._$210613 = -1;
    }
    
    public void start() {
        if (this.m_Timer == null) {
            (this.m_Timer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_Timer != null) {
            this.m_Timer = null;
        }
        this._$210613 = -1;
    }
    
    public void destroy() {
        if (this.m_Timer != null) {
            this.m_Timer = null;
        }
    }
    
    public boolean setTimer(final int $210613) {
        this._$210613 = $210613;
        return true;
    }
    
    public boolean clearTimer() {
        this._$210613 = -1;
        return true;
    }
    
    public void run() {
        while (this.m_Timer != null) {
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            if (this._$210613 > 0) {
                --this._$210613;
            }
            else {
                if (this._$210613 != 0) {
                    continue;
                }
                --this._$210613;
                this._$210605.callBackPrc();
            }
        }
    }
}
