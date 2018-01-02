import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class bu implements ActionListener
{
    private bm a;
    
    public bu(final bm a) {
        this.a = a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.l();
    }
}
