import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_bv implements ActionListener
{
    private /* synthetic */ JTextArea a;
    
    rp_bv(final rp_co rp_co, final JTextArea a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        System.out.println("*** Start of dump ***");
        System.out.println(this.a.getText());
        System.out.println("*** End of dump ***");
    }
}
