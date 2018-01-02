// 
// Decompiled by Procyon v0.5.30
// 

package medusa.extended;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

public class ModifiedClickLinker
{
    JApplet applet;
    String linkStart;
    String linkEnd;
    
    public ModifiedClickLinker(final String linkStart, final String linkEnd, final JApplet applet) {
        this.applet = applet;
        System.out.println(linkStart);
        this.linkStart = linkStart;
        this.linkEnd = linkEnd;
    }
    
    public boolean isActive() {
        return this.linkStart != null;
    }
    
    public void linkOut(final String s) {
        if (s == null) {
            return;
        }
        try {
            final URL url = new URL(s);
            System.out.println("Linking to " + s);
            this.applet.getAppletContext().showDocument(url, "_blank");
        }
        catch (MalformedURLException ex) {}
    }
}
