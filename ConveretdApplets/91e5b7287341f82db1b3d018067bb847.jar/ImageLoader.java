import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageLoader extends Canvas implements Runnable
{
    static final int NUM_TRIES = 10;
    static final int BACKOFF_MEAN = 100;
    static final int MAXWAITTIME = 60000;
    MediaTracker tracker;
    Thread thread;
    SlideProjector ss;
    int n;
    String url;
    
    public ImageLoader(final SlideProjector slideProjector, final int i, final String string) {
        this.ss = slideProjector;
        this.n = i;
        this.url = string;
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        this.thread.setPriority(Math.max(1, 5 - this.n));
        if (this.tracker == null) {
            this.tracker = new MediaTracker(this.ss);
        }
        try {
            if (this.url.startsWith("/") || this.url.startsWith("http://") || this.url.startsWith("ftp://") || this.url.startsWith("gopher://")) {
                this.ss.images[this.n] = Toolkit.getDefaultToolkit().getImage(new URL(this.url));
            }
            else {
                this.ss.images[this.n] = Toolkit.getDefaultToolkit().getImage(new URL(this.ss.getDocumentBase(), this.url));
            }
        }
        catch (Exception ex) {}
        int i = 0;
        while (i < 10) {
            try {
                this.tracker.addImage(this.ss.images[this.n], this.n);
                this.tracker.checkID(this.n, true);
                this.tracker.waitForID(this.n);
                this.ss.showMessage("[Got image for slide: " + (this.n + 1) + "]");
                return;
            }
            catch (Exception ex2) {
                if (!this.tracker.isErrorID(this.n)) {
                    break;
                }
                this.ss.showMessage("Timeout waiting for " + this.url);
                ++i;
            }
        }
        System.err.println("Failed to load image for slide: " + (this.n + 1));
    }
    
    private ImageProducer fetchImage(final String string) {
        ImageProducer imageProducer = null;
        try {
            imageProducer = (ImageProducer)new URL(string).getContent();
        }
        catch (MalformedURLException ex) {
            this.ss.showMessage("dodgy URL. Sort it out, matey." + string);
        }
        catch (ClassCastException ex2) {
            this.ss.showMessage("probably a dodgy URL. Sort it out, matey." + string);
        }
        catch (IOException ex3) {
            this.ss.showMessage("failed to get image via http." + string);
        }
        return imageProducer;
    }
}
