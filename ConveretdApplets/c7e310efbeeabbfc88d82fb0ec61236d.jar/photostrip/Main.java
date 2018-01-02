// 
// Decompiled by Procyon v0.5.30
// 

package photostrip;

import java.awt.Component;
import java.net.URL;
import javax.swing.JFrame;

public class Main
{
    public static void main(final String[] args) throws Exception {
        final JFrame frame = new JFrame("PhotoStrip");
        frame.setDefaultCloseOperation(3);
        final PhotoStrip strip = new PhotoStrip("http://api.flickr.com/services/feeds/photos_public.gne?id=31706743@N00&lang=en-us&format=atom", 5, 2, 150, new URLShower() {
            public void showDocument(final URL url) {
                System.out.println("going to: " + url);
            }
        });
        frame.add(strip);
        frame.pack();
        frame.setVisible(true);
    }
}
