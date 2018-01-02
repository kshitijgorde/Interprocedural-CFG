import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import netscape.javascript.JSObject;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JSedit extends Applet
{
    public void init() {
        try {
            final JSObject window = JSObject.getWindow((Applet)this);
            this.setLayout(new BorderLayout());
            this.add("Center", new MainContainer(window));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
