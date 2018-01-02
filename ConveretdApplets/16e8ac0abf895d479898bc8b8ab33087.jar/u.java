import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class u implements ActionListener
{
    public final TSSView a;
    
    public u(final TSSView a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        TSSView.q(this.a, actionEvent);
    }
}
