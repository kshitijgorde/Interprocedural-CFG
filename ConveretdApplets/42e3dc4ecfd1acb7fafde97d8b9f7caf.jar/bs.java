import java.awt.Frame;
import java.awt.FileDialog;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class bs extends MouseAdapter
{
    private bm a;
    
    public bs(final bm a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final FileDialog fileDialog = new FileDialog(this.a, Main.p.a("dialog.setcapturepathheading"), 1);
        fileDialog.setDirectory(Main.i);
        fileDialog.setFile(Main.p.a("dialog.setcapturepath"));
        fileDialog.setVisible(true);
        this.a.a = false;
        this.a.z.setText(fileDialog.getDirectory());
    }
}
