import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.TextArea;
import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class imageLoader extends Thread
{
    static final String BAD_URL = "\r\nError: Malformed Image URL.";
    static final String IMAGE_ERROR = "\r\nError: Image(s) not loaded.";
    static final String IMAGE_LOADED = "Done.";
    static final String IMAGE_LOADING = "\r\nLoading images...";
    Image[] image;
    String[] szImage;
    int nImages;
    boolean loaded;
    Applet app;
    TextArea s;
    
    imageLoader(final Applet a, final String[] szName, final int numImages, final TextArea scroller) {
        this.image = new Image[numImages];
        this.szImage = new String[numImages];
        for (int i = 0; i < numImages; ++i) {
            this.szImage[i] = szName[i];
            this.image[i] = null;
        }
        this.nImages = numImages;
        this.app = a;
        this.s = scroller;
    }
    
    public void run() {
        if (this.s != null) {
            this.s.appendText("\r\nLoading images...");
        }
        this.loaded = false;
        final MediaTracker tracker = new MediaTracker(this.app);
        for (int i = 0; i < this.nImages; ++i) {
            URL imageURL = null;
            Label_0103: {
                if (this.szImage[i] != null) {
                    try {
                        imageURL = new URL(this.app.getCodeBase(), this.szImage[i]);
                        break Label_0103;
                    }
                    catch (MalformedURLException ex) {
                        imageURL = null;
                        if (this.s != null) {
                            this.s.appendText("\r\nError: Malformed Image URL.");
                        }
                        System.out.println("\r\nError: Malformed Image URL.");
                        continue;
                    }
                }
                imageURL = null;
            }
            if (imageURL != null) {
                this.image[i] = this.app.getImage(imageURL);
                if (this.image[i] != null) {
                    tracker.addImage(this.image[i], i);
                }
            }
            else {
                this.image[i] = null;
            }
            if (this.image[i] != null) {
                try {
                    tracker.waitForID(i);
                }
                catch (InterruptedException ex2) {
                    this.image[i] = null;
                    if (this.s != null) {
                        this.s.appendText("\r\nError: Image(s) not loaded.");
                    }
                    System.out.println("\r\nError: Image(s) not loaded.");
                }
            }
        }
        this.loaded = true;
        if (this.s != null) {
            this.s.appendText("Done.");
        }
    }
    
    Image[] retrieveImages() {
        if (this.loaded) {
            return this.image;
        }
        return null;
    }
}
