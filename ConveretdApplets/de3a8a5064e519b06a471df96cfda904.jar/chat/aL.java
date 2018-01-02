// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Vector;

public final class aL implements Runnable
{
    private Thread a;
    final Vector a;
    
    public final void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                final aL al;
                synchronized (al.a) {
                    int i = 0;
                    while (i < al.a.size()) {
                        final l l;
                        if ((l = al.a.elementAt(i)) != null) {
                            l.repaint();
                            ++i;
                        }
                        else {
                            al.a.removeElementAt(i);
                        }
                    }
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public aL() {
        this.a = new Thread(this);
        ((Thread)(this.a = new Vector(10, 5))).start();
    }
}
