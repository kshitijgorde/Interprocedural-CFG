import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.TextArea;
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
    Object instance;
    TextArea s;
    
    imageLoader(final Object instance, final String[] array, final int nImages, final TextArea s) {
        this.image = new Image[nImages];
        this.szImage = new String[nImages];
        for (int i = 0; i < nImages; ++i) {
            this.szImage[i] = array[i];
            this.image[i] = null;
        }
        this.nImages = nImages;
        this.instance = instance;
        this.s = s;
    }
    
    public void run() {
        if (this.instance instanceof Applet) {
            if (this.s != null) {
                this.s.appendText("\r\nLoading images...");
            }
            this.loaded = false;
            final MediaTracker mediaTracker = new MediaTracker((Component)this.instance);
            for (int i = 0; i < this.nImages; ++i) {
                URL url = null;
                Label_0119: {
                    if (this.szImage[i] != null) {
                        try {
                            url = new URL(((Applet)this.instance).getCodeBase(), this.szImage[i]);
                            break Label_0119;
                        }
                        catch (MalformedURLException ex) {
                            if (this.s != null) {
                                this.s.appendText("\r\nError: Malformed Image URL.");
                            }
                            System.out.println("\r\nError: Malformed Image URL.");
                            continue;
                        }
                    }
                    url = null;
                }
                if (url != null) {
                    this.image[i] = ((Applet)this.instance).getImage(url);
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
                        if (this.s != null) {
                            this.s.appendText("\r\nError: Image(s) not loaded.");
                        }
                        System.out.println("\r\nError: Image(s) not loaded.");
                    }
                }
            }
        }
        else {
            if (this.s != null) {
                this.s.appendText("\r\nLoading images...");
            }
            this.loaded = false;
            final MediaTracker mediaTracker2 = new MediaTracker((Component)this.instance);
            for (int j = 0; j < this.nImages; ++j) {
                if (this.szImage[j] != null) {
                    this.image[j] = ((Component)this.instance).getToolkit().getImage(this.szImage[j]);
                    if (this.image[j] != null) {
                        mediaTracker2.addImage(this.image[j], j);
                    }
                }
                else {
                    this.image[j] = null;
                }
                if (this.image[j] != null) {
                    try {
                        mediaTracker2.waitForID(j);
                    }
                    catch (InterruptedException ex3) {
                        this.image[j] = null;
                        if (this.s != null) {
                            this.s.appendText("\r\nError: Image(s) not loaded.");
                        }
                        System.out.println("\r\nError: Image(s) not loaded.");
                    }
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
