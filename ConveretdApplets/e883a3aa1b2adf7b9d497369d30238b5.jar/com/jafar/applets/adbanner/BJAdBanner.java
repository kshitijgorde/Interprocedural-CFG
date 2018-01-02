// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.applets.adbanner;

import java.io.InputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class BJAdBanner extends Applet implements Runnable, MouseListener
{
    private int width;
    private int height;
    private int numImages;
    private static final String[] imgStrings;
    private Image[] images;
    private static final String[] urlStrings;
    private URL[] urls;
    private MediaTracker tracker;
    private Thread thread;
    private Image curImage;
    private int curImageIndex;
    private int rotateInterval;
    private boolean Active;
    private Image im;
    private Graphics imGraphics;
    
    public void init() {
        final String parameter = this.getParameter("width");
        if (parameter != null) {
            this.width = Integer.parseInt(parameter);
        }
        else {
            this.width = 468;
        }
        final String parameter2 = this.getParameter("height");
        if (parameter2 != null) {
            this.height = Integer.parseInt(parameter2);
        }
        else {
            this.height = 60;
        }
        this.numImages = BJAdBanner.imgStrings.length;
        if (this.numImages > 0) {
            this.getDocumentBase();
            this.tracker = new MediaTracker(this);
            this.images = new Image[this.numImages];
            for (int i = 0; i < this.numImages; ++i) {
                this.images[i] = this.getImageResource("/adbres/images/" + BJAdBanner.imgStrings[i]);
                this.tracker.addImage(this.images[i], i);
                this.tracker.checkID(i, true);
            }
        }
        this.urls = new URL[this.numImages];
        for (int j = 0; j < this.numImages; ++j) {
            if (BJAdBanner.urlStrings[j] != null) {
                try {
                    this.urls[j] = new URL(BJAdBanner.urlStrings[j]);
                }
                catch (MalformedURLException ex) {}
            }
        }
        final String parameter3 = this.getParameter("updateInterval");
        if (parameter3 != null) {
            try {
                this.rotateInterval = Integer.parseInt(parameter3) * 1000;
            }
            catch (NumberFormatException ex2) {
                System.err.println("Invalid update interval: " + parameter3);
            }
        }
        else {
            this.rotateInterval = 30000;
        }
        this.addMouseListener(this);
        this.Active = true;
        this.start();
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public void stop() {
        this.Active = false;
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        while (this.Active) {
            ++this.curImageIndex;
            if (this.curImageIndex >= this.numImages) {
                this.curImageIndex = 0;
            }
            this.repaint();
            try {
                Thread.sleep(this.rotateInterval);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.im == null) {
            final Rectangle bounds = this.getBounds();
            if (bounds.width <= 0 || bounds.height <= 0) {
                return;
            }
            try {
                this.im = this.createImage(bounds.width, bounds.height);
                this.imGraphics = this.im.getGraphics();
                return;
            }
            catch (Exception ex) {
                this.imGraphics = null;
                return;
            }
        }
        if (this.imGraphics != null) {
            this.paintApplet(this.imGraphics);
            graphics.drawImage(this.im, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    private void paintApplet(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        try {
            if (this.numImages > 0) {
                if (!this.tracker.checkID(this.curImageIndex)) {
                    this.tracker.waitForID(this.curImageIndex);
                }
                if (!this.tracker.isErrorID(this.curImageIndex)) {
                    graphics.drawImage(this.curImage = this.images[this.curImageIndex], (bounds.width - this.curImage.getWidth(this)) / 2, (bounds.height - this.curImage.getHeight(this)) / 2, this);
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final URL url = this.urls[this.curImageIndex];
        if (url != null) {
            this.getAppletContext().showDocument(url, "_blank");
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private Image getImageResource(final String s) {
        Image image = null;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            if (resourceAsStream == null) {
                System.err.println("Image " + s + " not found.");
                return null;
            }
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            image = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (IOException ex) {
            System.err.println("Unable to read image: " + s);
            ex.printStackTrace();
        }
        return image;
    }
    
    public BJAdBanner() {
        this.Active = false;
    }
    
    static {
        imgStrings = new String[] { "jcom3.gif", "jipcalc.gif", "jimgmap.gif" };
        urlStrings = new String[] { "http://www.jafar.com/", "http://www.jafar.com/java/jipcalc/", "http://www.jafar.com/java/jimgmap/" };
    }
}
