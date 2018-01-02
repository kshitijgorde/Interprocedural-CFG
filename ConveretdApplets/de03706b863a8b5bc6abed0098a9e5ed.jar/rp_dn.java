import java.awt.event.WindowListener;
import jnlpapp.JNLPEPApp;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dn implements Runnable
{
    private /* synthetic */ rp_dh a;
    private /* synthetic */ JNLPEPApp a;
    
    public rp_dn(final JNLPEPApp a, final rp_dh a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final void run() {
        final JNLPEPApp a = this.a;
        final rp_dh a2 = this.a;
        final JNLPEPApp jnlpepApp = a;
        a.setDefaultCloseOperation(0);
        jnlpepApp.addWindowListener(new rp_dp(jnlpepApp));
        JNLPEPApp.a((rp_bZ)a2, (rp_Q)jnlpepApp);
        jnlpepApp.setSize(1000, 620);
        jnlpepApp.setVisible(true);
    }
}
