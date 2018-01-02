// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;

public final class ar extends Thread
{
    private Vector a;
    
    public ar() {
        this.a = new Vector();
        this.start();
    }
    
    final synchronized void a(final em em) {
        this.a.addElement(em);
        this.notifyAll();
    }
    
    final synchronized void a() {
        this.a.addElement(null);
        this.notifyAll();
    }
    
    public final void run() {
        while (true) {
            final ar ar;
            final em em;
            synchronized (ar) {
                while (ar.a.size() == 0) {
                    try {
                        ar.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                em = ar.a.elementAt(0);
                ar.a.removeElementAt(0);
            }
            if (em == null) {
                break;
            }
            em.b();
        }
    }
}
