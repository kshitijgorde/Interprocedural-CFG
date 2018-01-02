import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.net.URL;
import java.applet.AudioClip;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class animateButton extends Applet
{
    int nframe;
    Image[] image;
    AudioClip audio;
    URL url;
    String target;
    int sleeptime;
    MediaTracker tracker;
    Animate animate;
    int frame;
    boolean running;
    private Image offScreenImage;
    private Dimension offScreenSize;
    private Graphics offScreenGraphics;
    
    public void advanceFrame() {
        this.frame = (this.frame + 1) % this.nframe;
    }
    
    public void init() {
        final String parameter = this.getParameter("nframe");
        if (parameter == null) {
            System.out.println("Error: invalid parameter: nframe");
        }
        else {
            this.nframe = Integer.parseInt(parameter);
        }
        this.image = new Image[this.nframe];
        this.tracker = new MediaTracker(this);
        for (int i = 0; i < this.nframe; ++i) {
            final String parameter2 = this.getParameter("image" + i);
            if (parameter2 == null) {
                System.out.println("Error: invalid parameter: image" + i);
            }
            else {
                this.image[i] = this.getImage(this.getDocumentBase(), parameter2);
                this.tracker.addImage(this.image[i], i);
            }
        }
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for image to load.");
        }
        final String parameter3 = this.getParameter("audio");
        if (parameter3 != null) {
            this.audio = this.getAudioClip(this.getDocumentBase(), parameter3);
        }
        final String parameter4 = this.getParameter("url");
        if (parameter4 != null) {
            try {
                this.url = new URL(parameter4);
            }
            catch (MalformedURLException ex2) {
                System.out.println("Error locating URL address.");
            }
        }
        this.target = this.getParameter("target");
        final String parameter5 = this.getParameter("sleeptime");
        if (parameter5 == null) {
            this.sleeptime = 1000;
            return;
        }
        this.sleeptime = Integer.parseInt(parameter5);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.image[this.frame], 0, 0, null);
    }
    
    public final synchronized void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offScreenImage == null || size.width != this.offScreenSize.width || size.height != this.offScreenSize.height) {
            this.offScreenImage = this.createImage(size.width, size.height);
            this.offScreenSize = size;
            this.offScreenGraphics = this.offScreenImage.getGraphics();
        }
        this.offScreenGraphics.setColor(this.getBackground());
        this.offScreenGraphics.fillRect(0, 0, size.width, size.height);
        this.paint(this.offScreenGraphics);
        graphics.drawImage(this.offScreenImage, 0, 0, null);
    }
    
    public void stop() {
        this.running = false;
        this.destroy();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.audio != null) {
            this.audio.play();
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.url != null) {
            if (this.target != null) {
                this.getAppletContext().showDocument(this.url, this.target);
            }
            else {
                this.getAppletContext().showDocument(this.url);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.running = true;
        (this.animate = new Animate(this)).start();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.running = false;
        return true;
    }
}
