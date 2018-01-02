import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bb implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.cv.iu = 0;
        this.cv.it = 0;
        this.cv.i5.setVisible(false);
        this.cv.i1.setText("");
        this.cv.i0.setText("");
    }
    
    public bb(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
