// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Window;

public final class cm implements Runnable
{
    private final Window q;
    
    public cm(final Window q) {
        this.q = q;
    }
    
    public final void run() {
        while (System.currentTimeMillis() - System.currentTimeMillis() < 300L) {
            this.q.toFront();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
        }
    }
}
