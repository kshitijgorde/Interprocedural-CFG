// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Vector;

public class T implements Runnable
{
    private Thread b;
    private Vector e;
    
    public void a(final Object o) {
        synchronized (this.e) {
            this.e.addElement(o);
        }
        // monitorexit(this.e)
    }
    
    public void run() {
        try {
            while (true) {
                Thread.sleep(100L);
                synchronized (this.e) {
                    int i = 0;
                    while (i < this.e.size()) {
                        final bp bp = this.e.elementAt(i);
                        if (bp != null) {
                            bp.repaint();
                            ++i;
                        }
                        else {
                            this.e.removeElementAt(i);
                        }
                    }
                }
                // monitorexit(this.e)
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public T() {
        this.b = new Thread(this);
        this.e = new Vector(10, 5);
        this.b.start();
    }
}
