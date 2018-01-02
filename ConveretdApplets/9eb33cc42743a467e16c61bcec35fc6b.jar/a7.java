import java.awt.Component;
import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a7 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.cv.jk) {
            this.cv.jm.setLabel("More options...");
            this.cv.d_.remove(this.cv.jj);
        }
        else {
            this.cv.jm.setLabel("Less options...");
            this.cv.d_.add(this.cv.jj);
        }
        this.cv.d_.pack();
        this.cv.jk = !this.cv.jk;
    }
    
    public a7(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
