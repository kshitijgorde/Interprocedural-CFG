// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Vector;

public class cx implements Runnable
{
    private Thread b;
    private Vector h;
    
    public void a(final Object o) {
        synchronized (this.h) {
            this.h.addElement(o);
        }
        // monitorexit(this.h)
    }
    
    public void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                synchronized (this.h) {
                    int i = 0;
                    while (i < this.h.size()) {
                        final B b = this.h.elementAt(i);
                        if (b != null) {
                            b.repaint();
                            ++i;
                        }
                        else {
                            this.h.removeElementAt(i);
                        }
                    }
                }
                // monitorexit(this.h)
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public cx() {
        this.b = new Thread(this);
        this.h = new Vector(10, 5);
        this.b.start();
    }
}
