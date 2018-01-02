import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import mindbright.ssh.SSHMenuHandlerFull;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a9 implements ActionListener
{
    public final SSHMenuHandlerFull cv;
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.cv.jo == null) {
            (this.cv.jo = new FileDialog(this.cv.a, "MindTerm - Select file with identity (private)", 0)).setDirectory(this.cv.e9.f.hc());
        }
        this.cv.jo.setVisible(true);
        if (this.cv.jo.getFile() != null && this.cv.jo.getFile().length() > 0) {
            this.cv.jx.setText(String.valueOf(this.cv.jo.getDirectory()) + this.cv.jo.getFile());
        }
    }
    
    public a9(final SSHMenuHandlerFull cv) {
        this.cv = cv;
    }
}
