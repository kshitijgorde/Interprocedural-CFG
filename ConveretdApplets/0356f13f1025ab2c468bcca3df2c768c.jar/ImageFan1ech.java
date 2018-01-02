import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Component;
import java.net.URL;
import java.awt.Point;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ImageFan1ech extends Applet implements Runnable
{
    boolean inapplet;
    boolean clockwise;
    double blade_max;
    double angle_offset;
    Graphics bufferG;
    Image bufferI;
    Image[] images;
    int width;
    int height;
    int index;
    int transition_count;
    int transition_speed;
    int pause;
    int blades;
    MediaTracker mt;
    Point origin;
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
        if (this.getParameter("CLOCKWISE") != null) {
            this.clockwise = new Boolean(this.getParameter("CLOCKWISE"));
        }
        if (this.getParameter("PAUSE") != null) {
            this.pause = Integer.parseInt(this.getParameter("PAUSE"));
        }
        if (this.getParameter("ANGLE.OFFSET") != null) {
            this.angle_offset = 3.141592653589793 * new Double(this.getParameter("ANGLE.OFFSET")) / 180.0;
        }
        if (this.getParameter("BLADES") != null) {
            this.blades = Integer.parseInt(this.getParameter("BLADES"));
        }
        this.blade_max = 6.283185307179586 / this.blades;
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
            final String parameter;
            if ((parameter = this.getParameter("ORIGIN")) != null) {
                this.origin = (parameter.toLowerCase().startsWith("r") ? new Point((int)(Math.random() * this.width), (int)(Math.random() * this.height)) : this.makePoint(parameter));
            }
            else {
                this.origin = new Point(this.width / 2, this.height / 2);
            }
            final Image[] array = new Image[this.transition_count];
            final Pixelator3 pixelator3 = new Pixelator3(this.images[this.index]);
            final Pixelator3 pixelator4 = new Pixelator3(this.images[n]);
            final int[][] array2 = new int[this.transition_count][this.height * this.width];
            for (int i = 0; i < this.transition_count; ++i) {
                for (int j = 0; j < this.height; ++j) {
                    for (int k = 0; k < this.width; ++k) {
                        final double calculateAngle = this.calculateAngle(k, j, this.origin.x, this.origin.y);
                        if (this.clockwise) {
                            array2[i][j * this.width + k] = ((calculateAngle % this.blade_max < this.blade_max * (i + 1) / this.transition_count) ? pixelator4.imgpixels[j][k] : pixelator3.imgpixels[j][k]);
                        }
                        else {
                            array2[i][j * this.width + k] = ((calculateAngle % this.blade_max > this.blade_max * (this.transition_count - i - 1) / this.transition_count) ? pixelator4.imgpixels[j][k] : pixelator3.imgpixels[j][k]);
                        }
                    }
                }
                array[i] = this.createImage(new MemoryImageSource(this.width, this.height, array2[i], 0, this.width));
            }
            final int n2 = (int)(System.currentTimeMillis() - currentTimeMillis);
            if (n2 < this.pause) {
                try {
                    Thread.sleep(this.pause - n2);
                }
                catch (Exception ex2) {}
            }
            for (int l = 0; l < this.transition_count; ++l) {
                this.bufferG.drawImage(array[l], 0, 0, this);
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
    
    public double calculateAngle(final int n, final int n2, final int n3, final int n4) {
        double n5 = (n3 - n != 0) ? Math.atan((n4 - n2) / (n3 - n)) : 1.5707963267948966;
        if (n < n3 || (n == n3 && n2 < n4)) {
            n5 += 3.141592653589793;
        }
        if (n5 < 0.0) {
            n5 += 6.283185307179586;
        }
        return n5 + this.angle_offset;
    }
    
    public Point makePoint(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ,");
        return new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
    }
    
    public void start() {
        if (this.woohoo == null) {
            this.woohoo = new Thread(this);
        }
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("ImageFan applet, Copyright 1999, Eric Harshbarger")) {
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
    
    public ImageFan1ech() {
        this.inapplet = false;
        this.clockwise = true;
        this.angle_offset = 1.5707963267948966;
        this.transition_count = 10;
        this.transition_speed = 100;
        this.pause = 5000;
        this.blades = 10;
        this.target = "_self";
    }
}
