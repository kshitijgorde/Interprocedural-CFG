import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Component;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ImageDissolve1ech extends Applet implements Runnable
{
    boolean inapplet;
    Graphics bufferG;
    Image bufferI;
    Image[] images;
    int width;
    int height;
    int index;
    int transition_count;
    int transition_speed;
    int pause;
    MediaTracker mt;
    String target;
    Thread woohoo;
    URL[] urls;
    
    public void init() {
        this.mt = new MediaTracker(this);
        this.width = this.size().width;
        this.height = this.size().height;
        if (this.bufferG == null) {
            this.bufferI = this.createImage(this.width, this.height);
            this.bufferG = this.bufferI.getGraphics();
        }
        if (this.getParameter("TARGET") != null) {
            this.target = this.getParameter("TARGET");
        }
        if (this.getParameter("PAUSE") != null) {
            this.pause = Integer.parseInt(this.getParameter("PAUSE"));
        }
        if (this.getParameter("TRANSITION.STEPS") != null) {
            this.transition_count = Integer.parseInt(this.getParameter("TRANSITION.STEPS"));
        }
        if (this.getParameter("TRANSITION.SPEED") != null) {
            this.transition_speed = Integer.parseInt(this.getParameter("TRANSITION.SPEED"));
        }
        int n = 0;
        final Vector vector = new Vector<Image>();
        while (this.getParameter("IMAGE." + n) != null) {
            try {
                final Image image = this.getImage(this.getDocumentBase(), this.getParameter("IMAGE." + n));
                if (image != null) {
                    vector.addElement(image);
                }
                this.mt.addImage(image, n);
            }
            catch (Exception ex) {}
            ++n;
        }
        vector.copyInto(this.images = new Image[vector.size()]);
        this.urls = new URL[this.images.length];
        for (int i = 0; i < this.urls.length; ++i) {
            if (this.getParameter("URL." + i) != null) {
                try {
                    this.urls[i] = new URL(this.getDocumentBase(), this.getParameter("URL." + i));
                }
                catch (Exception ex2) {}
            }
        }
        this.index = 0;
    }
    
    public void run() {
        while (true) {
            if (this.inapplet && this.urls[this.index] != null) {
                this.showStatus(this.urls[this.index].toString());
            }
            else {
                this.showStatus("");
            }
            final int n = (this.index + 1) % this.images.length;
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                if (!this.mt.checkID(this.index)) {
                    this.mt.waitForID(this.index);
                }
                this.bufferG.drawImage(this.images[this.index], 0, 0, this);
                this.repaint();
                if (!this.mt.checkID(n)) {
                    this.mt.waitForID(n);
                }
            }
            catch (Exception ex) {}
            final Image[] array = new Image[this.transition_count];
            final Pixelator3 pixelator3 = new Pixelator3(this.images[this.index]);
            final Pixelator3 pixelator4 = new Pixelator3(this.images[n]);
            final int[][] array2 = new int[this.transition_count][this.height * this.width];
            final int[] array3 = new int[this.width * this.height];
            final int[] array4 = new int[this.width * this.height];
            for (int i = 0; i < this.width * this.height; ++i) {
                array4[i] = i;
            }
            for (int j = 0; j < this.width * this.height; ++j) {
                final int n2 = (int)(Math.random() * (this.width * this.height - j));
                array3[j] = array4[n2];
                array4[n2] = array4[this.width * this.height - j - 1];
            }
            for (int k = 0; k < this.transition_count; ++k) {
                for (int l = 0; l < this.height; ++l) {
                    for (int n3 = 0; n3 < this.width; ++n3) {
                        final int n4 = l * this.width + n3;
                        array2[k][array3[n4]] = ((n4 < this.width * this.height * (k + 1) / this.transition_count) ? pixelator4.imgpixels[array3[n4] / this.width][array3[n4] % this.width] : pixelator3.imgpixels[array3[n4] / this.width][array3[n4] % this.width]);
                    }
                }
                array[k] = this.createImage(new MemoryImageSource(this.width, this.height, array2[k], 0, this.width));
            }
            final int n5 = (int)(System.currentTimeMillis() - currentTimeMillis);
            if (n5 < this.pause) {
                try {
                    Thread.sleep(this.pause - n5);
                }
                catch (Exception ex2) {}
            }
            for (int n6 = 0; n6 < this.transition_count; ++n6) {
                this.bufferG.drawImage(array[n6], 0, 0, this);
                this.repaint();
                try {
                    Thread.sleep(this.transition_speed);
                }
                catch (Exception ex3) {}
            }
            this.index = (this.index + 1) % this.images.length;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.bufferI, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.bufferI, 0, 0, this);
    }
    
    public void start() {
        if (this.woohoo == null) {
            this.woohoo = new Thread(this);
        }
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("ImageDissolve applet, Copyright 1999, Eric Harshbarger")) {
            this.woohoo.start();
            return;
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public void stop() {
        if (this.woohoo != null) {
            this.woohoo.stop();
            this.woohoo = null;
        }
    }
    
    public void destroy() {
        if (this.bufferG != null) {
            this.bufferG.dispose();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.inapplet = true;
        if (this.urls[this.index] != null) {
            this.showStatus(this.urls[this.index].toString());
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.inapplet = false;
        this.showStatus("");
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.urls[this.index] != null) {
            try {
                this.getAppletContext().showDocument(this.urls[this.index], this.target);
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public ImageDissolve1ech() {
        this.inapplet = false;
        this.transition_count = 10;
        this.transition_speed = 100;
        this.pause = 5000;
        this.target = "_self";
    }
}
