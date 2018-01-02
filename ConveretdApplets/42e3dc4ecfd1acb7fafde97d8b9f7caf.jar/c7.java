import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class c7 extends WindowAdapter implements ActionListener
{
    private cl a;
    
    public c7(final cl a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.c();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.a.c();
    }
}
