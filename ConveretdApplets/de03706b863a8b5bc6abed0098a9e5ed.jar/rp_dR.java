import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_dR implements ActionListener
{
    private /* synthetic */ rp_fE a;
    
    rp_dR(final rp_fE a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.a.setSelected(!this.a.a.isSelected());
    }
}
