import javax.swing.SwingUtilities;
import java.util.Collections;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class rp_cx implements Runnable
{
    private List a;
    
    rp_cx() {
        this.a = null;
    }
    
    protected abstract void a(final List p0);
    
    public final void run() {
        this.a(this.a());
    }
    
    public final synchronized void a(final boolean b, final Object... array) {
        boolean b2 = true;
        if (this.a == null) {
            b2 = false;
            this.a = new ArrayList();
        }
        if (b) {
            this.a.addAll(0, Arrays.asList(array));
        }
        else {
            Collections.addAll(this.a, array);
        }
        if (!b2) {
            this.a();
        }
    }
    
    protected void a() {
        SwingUtilities.invokeLater(this);
    }
    
    private final synchronized List a() {
        final List a = this.a;
        this.a = null;
        return a;
    }
}
