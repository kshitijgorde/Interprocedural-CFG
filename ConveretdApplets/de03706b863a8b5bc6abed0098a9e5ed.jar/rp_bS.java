import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bS implements ActionListener
{
    private /* synthetic */ CardLayout a;
    private /* synthetic */ JPanel a;
    
    rp_bS(final rp_bl rp_bl, final CardLayout a, final JPanel a2) {
        this.a = a;
        this.a = a2;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.show(this.a, "ACCT_LOGIN_PANEL");
    }
}
