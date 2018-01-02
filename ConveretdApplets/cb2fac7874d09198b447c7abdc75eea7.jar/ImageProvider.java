import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageProvider
{
    private Applet applet;
    private MediaTracker mediaTracker;
    
    public ImageProvider(final Applet applet) {
        this.applet = applet;
        this.mediaTracker = new MediaTracker(applet);
    }
    
    public Image get(final String s) throws InterruptedException, ImageProviderException {
        final Image image = this.applet.getImage(this.applet.getCodeBase(), s);
        this.mediaTracker.addImage(image, 0);
        this.mediaTracker.waitForAll();
        if (this.mediaTracker.isErrorAny()) {
            throw new ImageProviderException(s);
        }
        return image;
    }
    
    public Image[] get(final String[] array) throws InterruptedException, ImageProviderException {
        final Image[] array2 = new Image[array.length];
        for (int i = 0; i < array2.length; ++i) {
            this.mediaTracker.addImage(array2[i] = this.applet.getImage(this.applet.getCodeBase(), array[i]), 0);
        }
        this.mediaTracker.waitForAll();
        if (this.mediaTracker.isErrorAny()) {
            String string = "";
            for (int j = 0; j < array2.length; ++j) {
                string = string + array[j] + " ";
            }
            throw new ImageProviderException(string);
        }
        return array2;
    }
}
