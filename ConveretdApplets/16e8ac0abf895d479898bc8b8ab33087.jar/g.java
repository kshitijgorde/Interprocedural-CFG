import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class g implements ActionListener
{
    public final TSSView a;
    
    public g(final TSSView a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        TSSView.p(this.a, actionEvent);
    }
}
