// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Vector;

public class C implements Runnable
{
    private Thread b;
    private Vector d;
    
    public void a(final Object o) {
        synchronized (this.d) {
            this.d.addElement(o);
        }
        // monitorexit(this.d)
    }
    
    public void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                synchronized (this.d) {
                    int i = 0;
                    while (i < this.d.size()) {
                        final M m = this.d.elementAt(i);
                        if (m != null) {
                            m.repaint();
                            ++i;
                        }
                        else {
                            this.d.removeElementAt(i);
                        }
                    }
                }
                // monitorexit(this.d)
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public C() {
        this.b = new Thread(this);
        this.d = new Vector(10, 5);
        this.b.start();
    }
}
