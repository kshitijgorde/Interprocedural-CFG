// 
// Decompiled by Procyon v0.5.30
// 

package photostrip;

import javax.swing.SwingUtilities;
import java.net.URL;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JApplet;

public class Applet extends JApplet
{
    private PhotoStrip strip;
    
    public void init() {
        System.out.println("init");
        final String username = this.getParameter("flickruser");
        String size = this.getParameter("size");
        if (size == null) {
            size = "150";
        }
        String cols = this.getParameter("cols");
        if (cols == null) {
            cols = "4";
        }
        String rows = this.getParameter("rows");
        if (rows == null) {
            rows = "1";
        }
        String orientation = this.getParameter("orientation");
        if (orientation == null) {
            orientation = "h";
        }
        final String feedURL = "http://api.flickr.com/services/feeds/photos_public.gne?id=" + username + "&lang=en-us&format=atom";
        final int colsi = Integer.parseInt(cols);
        final int rowsi = Integer.parseInt(rows);
        final int sizei = Integer.parseInt(size);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Applet.this.setContentPane(new JLabel("Loading Photo Strip"));
                if (Applet.this.strip == null) {
                    Applet.this.strip = new PhotoStrip(feedURL, colsi, rowsi, sizei, new URLShower() {
                        public void showDocument(final URL url) {
                            Applet.this.getAppletContext().showDocument(url);
                        }
                    });
                    Applet.this.setContentPane(Applet.this.strip);
                }
            }
        });
    }
}
