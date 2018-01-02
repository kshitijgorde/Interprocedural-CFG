import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a8 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        aq.d4("MindTerm - Proxy Settings", this.cv.a, this.cv.e9.f);
    }
    
    public a8(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
