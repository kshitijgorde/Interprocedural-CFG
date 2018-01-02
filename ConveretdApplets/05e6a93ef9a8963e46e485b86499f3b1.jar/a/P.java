// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;

public final class P implements Runnable
{
    private Thread q;
    Vector q;
    
    public final void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                synchronized (this.q) {
                    int i = 0;
                    while (i < this.q.size()) {
                        final aH ah;
                        if ((ah = this.q.elementAt(i)) != null) {
                            ah.repaint();
                            ++i;
                        }
                        else {
                            this.q.removeElementAt(i);
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public P() {
        this.q = new Thread(this);
        ((Thread)(this.q = new Vector(10, 5))).start();
    }
}
