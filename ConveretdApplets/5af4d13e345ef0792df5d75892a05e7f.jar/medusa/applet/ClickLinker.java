// 
// Decompiled by Procyon v0.5.30
// 

package medusa.applet;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;

public class ClickLinker
{
    JApplet applet;
    String linkStart;
    String linkEnd;
    
    public ClickLinker(final String linkStart, final String linkEnd, final JApplet applet) {
        this.applet = applet;
        System.out.println(linkStart);
        this.linkStart = linkStart;
        this.linkEnd = linkEnd;
    }
    
    public boolean isActive() {
        return this.linkStart != null;
    }
    
    public void linkOut(final String s) {
        if (this.linkStart == null) {
            return;
        }
        try {
            final URL url = new URL(this.linkStart + s + this.linkEnd);
            System.out.println("Linking to " + this.linkStart + s + this.linkEnd);
            this.applet.getAppletContext().showDocument(url, "_blank");
        }
        catch (MalformedURLException ex) {}
    }
}
