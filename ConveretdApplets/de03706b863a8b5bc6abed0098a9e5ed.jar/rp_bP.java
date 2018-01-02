import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bP implements ActionListener
{
    private /* synthetic */ rp_bl a;
    
    rp_bP(final rp_bl a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.a.requestFocusInWindow();
    }
}
