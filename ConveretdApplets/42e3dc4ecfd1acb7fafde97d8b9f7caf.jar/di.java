import au.com.rocketdog.project.awc.applet.Main;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class di implements ActionListener
{
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            Runtime.getRuntime().exec(new LineNumberReader(new InputStreamReader(new FileInputStream(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "BCPath.txt")))).readLine() + "Broadcaster.exe");
        }
        catch (Exception ex) {
            Main.b("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.xsl&XSL=xsl.broadcaster.downloadmain", "frameContent");
        }
    }
}
