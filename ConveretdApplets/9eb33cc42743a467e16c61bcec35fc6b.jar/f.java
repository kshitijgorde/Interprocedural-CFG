import java.awt.event.ActionEvent;
import java.awt.Dialog;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class f implements ActionListener
{
    public Dialog ae;
    
    public f(final Dialog ae) {
        this.ae = ae;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.ae.setVisible(false);
    }
}
