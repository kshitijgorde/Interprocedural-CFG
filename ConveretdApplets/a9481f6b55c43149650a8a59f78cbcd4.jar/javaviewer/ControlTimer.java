// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

public class ControlTimer implements Runnable
{
    volatile Thread m_Timer;
    private Controler _$1581;
    private int _$1582;
    
    public ControlTimer(final Controler $1581) {
        this.m_Timer = null;
        this._$1581 = $1581;
        this._$1582 = -1;
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
        this._$1582 = -1;
    }
    
    public void destroy() {
        if (this.m_Timer != null) {
            this.m_Timer = null;
        }
    }
    
    public boolean setTimer(final int $1582) {
        this._$1582 = $1582;
        return true;
    }
    
    public boolean clearTimer() {
        this._$1582 = -1;
        return true;
    }
    
    public void run() {
        while (this.m_Timer != null) {
            try {
                final Thread timer = this.m_Timer;
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            if (this._$1582 > 0) {
                --this._$1582;
            }
            else {
                if (this._$1582 != 0) {
                    continue;
                }
                --this._$1582;
                this._$1581.callBackPrc();
            }
        }
    }
}
