// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.imageload;

import java.util.Enumeration;
import java.awt.Image;
import mapapplet.Main;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Observable;

public class ImageLoader extends Observable
{
    Hashtable images;
    Vector downloadedImages;
    boolean isUpdating;
    Object parent;
    protected Vector imageLoadingThreads;
    
    public ImageLoader(final Object par) {
        this.isUpdating = false;
        this.images = new Hashtable();
        this.downloadedImages = new Vector();
        this.imageLoadingThreads = new Vector();
        this.parent = par;
    }
    
    public void loadImage(final String imgStr, final String ID) {
        final ImageLoaderThread imLoadThr = new ImageLoaderThread(this, ID, imgStr);
        imLoadThr.start();
    }
    
    protected synchronized void imageLoaded() {
        this.isUpdating = true;
        while (!this.downloadedImages.isEmpty()) {
            final String desc = this.downloadedImages.elementAt(0);
            this.setChanged();
            if (this.images.get(desc) != null) {
                if (this.images.get(desc).getClass().isInstance(new Integer(0))) {
                    ((Main)this.parent).statusBar.setText("Error loading image.");
                    this.notifyObservers(new ErrorMessage(this.images.remove(desc), desc));
                    if (!this.hasMoreDwnlImages()) {
                        ((Main)this.parent).waiter(false);
                    }
                }
                else if (this.images.get(desc).getClass().getName().indexOf("Image") > 0) {
                    this.notifyObservers(new ImageMessage(this.images.remove(desc), desc));
                    if (!this.hasMoreDwnlImages()) {
                        ((Main)this.parent).waiter(false);
                    }
                }
                else {
                    System.out.println("Imageloader: Error getting downloaded image from array: type not known. " + this.images.get(desc).getClass().getName());
                }
            }
            else {
                System.err.println("Imageloader: ERROR getting image " + desc + " from images array.");
            }
            this.downloadedImages.removeElementAt(0);
        }
        this.isUpdating = false;
    }
    
    private boolean hasMoreDwnlImages() {
        if (this.imageLoadingThreads.size() > 0) {
            final Enumeration en = this.imageLoadingThreads.elements();
            while (en.hasMoreElements()) {
                final ImageLoaderThread imageLoaderThread = en.nextElement();
                if (imageLoaderThread.isAlive()) {
                    return true;
                }
                this.imageLoadingThreads.removeElement(imageLoaderThread);
            }
        }
        return false;
    }
}
