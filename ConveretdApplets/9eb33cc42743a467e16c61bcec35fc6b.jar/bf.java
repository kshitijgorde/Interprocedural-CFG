import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bf implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.cv.jd.setVisible(false);
        try {
            this.cv.e9.f.g_();
        }
        catch (Throwable t) {
            this.cv.ij("Error saving settings: " + t.getMessage());
        }
        this.cv.e9.f.g2();
        this.cv.jb = true;
        this.cv.jd.setVisible(false);
    }
    
    public bf(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
