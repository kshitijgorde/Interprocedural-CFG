import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class bd implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.cv.i4 == null) {
            (this.cv.i4 = new FileDialog(this.cv.a, "MindTerm - Select file to save identity to", 1)).setDirectory(this.cv.e9.f.hc());
        }
        this.cv.i4.setVisible(true);
        if (this.cv.i4.getFile() != null && this.cv.i4.getFile().length() > 0) {
            this.cv.i2.setText(String.valueOf(this.cv.i4.getDirectory()) + this.cv.i4.getFile());
        }
    }
    
    public bd(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
