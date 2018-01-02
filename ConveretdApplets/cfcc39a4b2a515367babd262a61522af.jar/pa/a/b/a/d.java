// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a;

import java.awt.Panel;

public abstract class d extends Panel implements Runnable
{
    private Thread do;
    private boolean a;
    private int if;
    
    public void run() {
        try {
            while (!this.a) {
                if (this.a()) {
                    Thread.yield();
                    this.if = 10;
                }
                else {
                    Thread.sleep(this.if);
                    if (this.if >= 75) {
                        continue;
                    }
                    this.if += 5;
                }
            }
        }
        catch (Throwable t) {}
        finally {
            this.do = null;
        }
    }
    
    protected abstract boolean a();
    
    public void for() {
        if (this.do == null) {
            this.if();
            this.a = false;
            this.if = 10;
            (this.do = new Thread(this)).start();
        }
        else {
            this.repaint();
            this.requestFocus();
        }
    }
    
    protected abstract void if();
    
    public void int() {
        if (this.do != null) {
            this.a = true;
            while (this.do != null) {
                Thread.yield();
            }
            this.do();
        }
    }
    
    protected abstract void do();
}
