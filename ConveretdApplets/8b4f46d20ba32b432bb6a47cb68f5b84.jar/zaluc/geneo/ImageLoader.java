// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;

class ImageLoader implements Runnable
{
    Image image;
    ImageUser user;
    MediaTracker tracker;
    Thread thread;
    
    public ImageLoader(final Image image, final ImageUser user, final Component component) {
        this.image = image;
        this.user = user;
        (this.tracker = new MediaTracker(component)).addImage(image, 0);
        (this.thread = new Thread(this, "ImageLoader Thread")).start();
    }
    
    public void run() {
        try {
            this.tracker.waitForID(0);
            if (!this.tracker.isErrorID(0)) {
                this.user.setImage(this.image);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Couldn't load image");
        }
    }
}
