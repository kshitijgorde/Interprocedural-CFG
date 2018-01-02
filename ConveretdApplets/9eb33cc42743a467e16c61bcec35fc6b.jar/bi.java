import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bi implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.cv.e9.f.ci("remote" + this.cv.e9.lp.size(), this.cv.jf.ia());
            this.cv.is();
        }
        catch (Exception ex) {
            this.cv.jf.ib();
        }
    }
    
    public bi(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
