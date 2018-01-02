import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ActL2 implements ActionListener
{
    private final int inp1;
    private final DropMenuII ParentApp;
    
    public ActL2(final DropMenuII parentApp, final int inp1) {
        this.ParentApp = parentApp;
        this.inp1 = inp1;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("done")) {
            this.ParentApp.DrpM(this.inp1);
        }
    }
}
