import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class v implements ActionListener
{
    public final TSSView a;
    
    public v(final TSSView a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        TSSView.h(this.a, actionEvent);
    }
}
