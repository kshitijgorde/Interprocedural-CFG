import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bg implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String selectedItem = this.cv.jc.getSelectedItem();
        try {
            String ii = "";
            while (true) {
                try {
                    this.cv.e9.f.hf(ii);
                    this.cv.e9.f.gu(selectedItem, false);
                    this.cv.e9.ko = true;
                    this.cv.e9.kp.ez("Connecting to: " + selectedItem);
                    this.cv.jd.setVisible(false);
                }
                catch (b4 b4) {
                    if ((ii = this.cv.ii("Please give file password for " + selectedItem, "MindTerm - File Password")) != null) {
                        continue;
                    }
                }
                break;
            }
        }
        catch (Throwable t) {
            this.cv.ij("Error loading settings: " + t.getMessage());
        }
    }
    
    public bg(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
