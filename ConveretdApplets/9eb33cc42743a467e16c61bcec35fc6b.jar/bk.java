import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bk implements ActionListener
{
    public final a2 ir;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.ir.ip.setText(this.ir.iq.getSelectedItem());
        this.ir.ip.requestFocus();
    }
    
    public bk(final a2 ir) {
        this.ir = ir;
    }
}
