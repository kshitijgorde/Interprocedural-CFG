import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class cg implements ActionListener
{
    private ce a;
    private bj b;
    
    public cg(final bj b, final ce a) {
        this.b = b;
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.a.a().equals("")) {
            p.a().c(this.b.s(), this.a.a());
        }
    }
}
