// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.imageload;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Panel;
import java.awt.MediaTracker;

public class ImageLoaderThread extends Thread
{
    MediaTracker tracker;
    ImageLoader parent;
    String ID;
    String imgStr;
    
    public ImageLoaderThread(final ImageLoader par, final String imgID, final String imgStr) {
        this.parent = par;
        this.tracker = new MediaTracker(new Panel());
        this.ID = imgID;
        this.imgStr = imgStr;
    }
    
    public void run() {
        this.parent.imageLoadingThreads.addElement(this);
        URL imgURL = null;
        try {
            imgURL = new URL(this.imgStr);
        }
        catch (MalformedURLException e) {
            System.err.println("malformed URL exception " + e);
            this.parent.imageLoadingThreads.removeElement(this);
            return;
        }
        Image mapImage = null;
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        mapImage = toolkit.getImage(imgURL);
        this.tracker.addImage(mapImage, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException e2) {
            System.err.println("Interrupted image waiting: " + e2);
            this.parent.imageLoadingThreads.removeElement(this);
            return;
        }
        if (this.tracker.statusID(0, false) != 8) {
            System.err.println("Error loading image " + this.imgStr + "\n >>> Image status is " + this.tracker.statusID(0, false));
            this.parent.downloadedImages.addElement(this.ID);
            this.parent.images.put(this.ID, new Integer(this.tracker.statusID(0, false)));
            if (!this.parent.isUpdating) {
                this.parent.imageLoadingThreads.removeElement(this);
                this.parent.imageLoaded();
            }
            return;
        }
        this.parent.images.put(this.ID, mapImage);
        this.parent.downloadedImages.addElement(this.ID);
        if (!this.parent.isUpdating) {
            this.parent.imageLoadingThreads.removeElement(this);
            this.parent.imageLoaded();
        }
    }
}
