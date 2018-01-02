import java.awt.event.WindowEvent;
import jnlpapp.JNLPEPApp;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dp extends WindowAdapter
{
    private /* synthetic */ JNLPEPApp a;
    
    rp_dp(final JNLPEPApp a) {
        this.a = a;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        if (!this.a.a.a()) {
            System.exit(0);
        }
    }
}
