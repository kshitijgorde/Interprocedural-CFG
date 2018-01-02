import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
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
    Scroller s;
    
    imageLoader(final Applet app, final String[] array, final int nImages, final Scroller s) {
        this.image = new Image[nImages];
        this.szImage = new String[nImages];
        for (int i = 0; i < nImages; ++i) {
            this.szImage[i] = array[i];
            this.image[i] = null;
        }
        this.nImages = nImages;
        this.app = app;
        this.s = s;
    }
    
    public void run() {
        if (this.s != null) {
            this.s.appendText("\r\nLoading images...");
        }
        this.loaded = false;
        final MediaTracker mediaTracker = new MediaTracker(this.app);
        for (int i = 0; i < this.nImages; ++i) {
            URL url = null;
            Label_0088: {
                if (this.szImage[i] != null) {
                    try {
                        url = new URL(this.app.getCodeBase(), this.szImage[i]);
                        break Label_0088;
                    }
                    catch (MalformedURLException ex) {
                        this.s.appendText("\r\nError: Malformed Image URL.");
                        continue;
                    }
                }
                url = null;
            }
            if (url != null) {
                this.image[i] = this.app.getImage(url);
                if (this.image[i] != null) {
                    mediaTracker.addImage(this.image[i], i);
                }
            }
            else {
                this.image[i] = null;
            }
            if (this.image[i] != null) {
                try {
                    mediaTracker.waitForID(i);
                }
                catch (InterruptedException ex2) {
                    this.image[i] = null;
                    this.s.appendText("\r\nError: Image(s) not loaded.");
                    return;
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
