// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Vector;

public final class bE implements Runnable
{
    private Thread a;
    final Vector a;
    
    public final void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                final bE be;
                synchronized (be.a) {
                    int i = 0;
                    while (i < be.a.size()) {
                        final q q;
                        if ((q = be.a.elementAt(i)) != null) {
                            q.repaint();
                            ++i;
                        }
                        else {
                            be.a.removeElementAt(i);
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public bE() {
        this.a = new Thread(this);
        ((Thread)(this.a = new Vector(10, 5))).start();
    }
}
