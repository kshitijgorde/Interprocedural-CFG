// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;

public final class G implements Runnable
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
                        final c c;
                        if ((c = this.q.elementAt(i)) != null) {
                            c.repaint();
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
    
    public G() {
        this.q = new Thread(this);
        ((Thread)(this.q = new Vector(10, 5))).start();
    }
}
