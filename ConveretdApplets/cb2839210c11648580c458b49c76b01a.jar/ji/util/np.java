// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import javax.swing.SwingUtilities;

public class np implements Runnable
{
    long a;
    boolean b;
    String c;
    
    private np(final long a) {
        this.a = a;
        this.b = false;
    }
    
    private void a(final String c) {
        this.c = c;
        System.currentTimeMillis();
        long n = 0L;
        final long n2 = 500L;
        SwingUtilities.invokeLater(this);
        while (n < this.a && !this.b && !d.ck(c)) {
            synchronized (this) {
                Label_0082: {
                    try {
                        if (!this.b) {
                            this.wait(n2);
                            n += n2;
                            break Label_0082;
                        }
                    }
                    // monitorexit(this)
                    catch (InterruptedException ex) {
                        // monitorexit(this)
                        continue;
                    }
                }
            }
            break;
        }
    }
    
    public void run() {
        synchronized (this) {
            this.b = true;
            this.notifyAll();
        }
    }
    
    public static boolean a(final long n, final String s) {
        if (SwingUtilities.isEventDispatchThread()) {
            return false;
        }
        new np(n).a(s);
        return true;
    }
}
