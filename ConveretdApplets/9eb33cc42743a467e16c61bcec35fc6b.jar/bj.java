import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bj implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final int ic = this.cv.jg.ic();
        if (ic != -1) {
            this.cv.e9.f.gq(ic, true);
            this.cv.is();
        }
    }
    
    public bj(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
