import java.awt.MediaTracker;
import java.awt.Component;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class RCLoadImage
{
    public static synchronized Image loadImage(final Class clazz, final String s) {
        try {
            if (clazz == null || s == null) {
                throw new InterruptedException("Bad Param");
            }
            final InputStream resourceAsStream = clazz.getResourceAsStream(s);
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            return Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    public static synchronized Image loadImage(final Class clazz, final String s, final Component component) {
        final Image loadImage = loadImage(clazz, s);
        if (loadImage == null) {
            return loadImage;
        }
        try {
            if (component == null) {
                throw new InterruptedException("Bad Param");
            }
            final MediaTracker mediaTracker = new MediaTracker(component);
            mediaTracker.addImage(loadImage, 0);
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorAny()) {
                throw new InterruptedException("Error loading image resource: " + s);
            }
            return loadImage;
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
