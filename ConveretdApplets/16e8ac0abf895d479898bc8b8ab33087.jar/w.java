import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class w implements ActionListener
{
    public final TSSView a;
    
    public w(final TSSView a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        TSSView.l(this.a, actionEvent);
    }
}
