import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class QueueMedia implements Runnable
{
    Image[] images;
    LemonadeAnim parent;
    
    public QueueMedia(final Image[] images, final LemonadeAnim parent) {
        this.images = images;
        this.parent = parent;
    }
    
    public void run() {
        System.out.println("Starting queueing...");
        final MediaTracker mediaTracker = new MediaTracker(this.parent);
        for (int i = 0; i < this.images.length; ++i) {
            mediaTracker.addImage(this.images[i], 0);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.out.println("ERROR!" + ex);
        }
        System.out.println("Done queueing...");
    }
}
