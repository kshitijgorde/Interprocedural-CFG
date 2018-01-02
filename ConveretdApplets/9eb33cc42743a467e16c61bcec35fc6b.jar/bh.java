import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bh implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final int ic = this.cv.jf.ic();
        if (this.cv.jf.ie(ic).indexOf("#FTP") != -1) {
            return;
        }
        if (ic != -1) {
            this.cv.e9.f.gp(ic);
            this.cv.is();
        }
    }
    
    public bh(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
