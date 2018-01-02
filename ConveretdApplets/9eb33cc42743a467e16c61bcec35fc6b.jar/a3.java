import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a3 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            this.cv.e9.f.ci("local" + this.cv.e9.lq.size(), this.cv.jg.ia());
            this.cv.is();
        }
        catch (Exception ex) {
            this.cv.jg.ib();
        }
    }
    
    public a3(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
