// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.SwingUtilities;

final class aJ implements Runnable
{
    private /* synthetic */ Runnable a;
    private /* synthetic */ ar b;
    
    aJ(final ar b, final Runnable a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        try {
            this.b.a();
        }
        finally {
            this.b.a.b();
        }
        SwingUtilities.invokeLater(this.a);
    }
}
