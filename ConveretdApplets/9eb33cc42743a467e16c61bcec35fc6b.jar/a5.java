import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a5 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final int selectedIndex = this.cv.jh.getSelectedIndex();
        if (selectedIndex == -1) {
            this.cv.d4.bs();
            return;
        }
        this.cv.e9.fq.l0(selectedIndex);
        Thread.yield();
        this.cv.iu();
    }
    
    public a5(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
