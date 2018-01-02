import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_cr implements ActionListener
{
    private /* synthetic */ rp_fX a;
    
    rp_cr(final rp_fX a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.a(((JButton)actionEvent.getSource()).getBackground());
        this.a.dispose();
    }
}
