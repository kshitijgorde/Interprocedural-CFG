import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a4 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.cv.iu();
    }
    
    public a4(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
