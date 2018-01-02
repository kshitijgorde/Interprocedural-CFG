// 
// Decompiled by Procyon v0.5.30
// 

package b.a.d;

import javax.swing.SwingUtilities;

class h extends Thread
{
    private final long a;
    private final Runnable b;
    
    h(final String s, final long a, final Runnable b) {
        super(s);
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        d.a(this.a);
        SwingUtilities.invokeLater(this.b);
    }
}
