import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class s_image_loader implements Runnable
{
    static final int IMAGE_LOADED = -1;
    static final int IMAGE_ERROR = -2;
    private Image[] image_array;
    private Component component;
    private MediaTracker tracker;
    private Thread progress;
    
    public s_image_loader(final Image[] image_array, final Component component) {
        this.component = component;
        this.image_array = image_array;
        this.tracker = new MediaTracker(this.component);
        for (int i = 0; i < this.image_array.length; ++i) {
            this.tracker.addImage(this.image_array[i], i);
        }
        this.progress = null;
    }
    
    public void run() {
        for (int i = 0; i < this.image_array.length; ++i) {
            try {
                this.tracker.waitForID(i);
            }
            catch (InterruptedException ex) {
                System.out.println("The MediaTracker thread was interrupted by another thread.");
            }
            this.component.deliverEvent(new Event(this.component, this.tracker.isErrorID(i) ? -2 : -1, new Integer(i)));
        }
    }
    
    public void start() throws IllegalThreadStateException {
        if (this.progress != null) {
            this.progress.stop();
            this.progress = null;
        }
        (this.progress = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.progress != null) {
            this.progress.stop();
            this.progress = null;
        }
    }
}
