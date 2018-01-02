import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ocgifix extends Panel
{
    public Image gifix(Image image, String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.lastIndexOf("gif") == -1) {
            return image;
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * (height + 1)];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, -1, width, height + 1, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        array[0] = 0;
        image = this.createImage(new MemoryImageSource(width, height + 1, array, 0, width));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        if (mediaTracker.isErrorID(0)) {
            return null;
        }
        return image;
    }
}
