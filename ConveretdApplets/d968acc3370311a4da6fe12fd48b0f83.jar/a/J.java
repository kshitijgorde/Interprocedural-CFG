// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;

public final class J implements Runnable
{
    private Thread q;
    private Vector q;
    
    public final void q(final Object o) {
        synchronized (this.q) {
            this.q.addElement(o);
        }
    }
    
    public final void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                synchronized (this.q) {
                    int i = 0;
                    while (i < this.q.size()) {
                        final d d;
                        if ((d = this.q.elementAt(i)) != null) {
                            d.repaint();
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
    
    public J() {
        this.q = new Thread(this);
        ((Thread)(this.q = new Vector(10, 5))).start();
    }
}
