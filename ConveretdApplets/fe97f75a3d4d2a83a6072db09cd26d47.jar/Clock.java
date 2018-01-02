import java.awt.image.ImageObserver;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.Component;
import java.awt.image.FilteredImageSource;
import java.net.URL;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Clock extends Applet implements Runnable
{
    int frameNumber;
    int delay;
    int r;
    int offset;
    MediaTracker tracker1;
    MediaTracker tracker2;
    Thread animatorThread;
    MinuteThread mThread;
    HourThread hThread;
    boolean frozen;
    Dimension offDimension;
    Image offImage;
    Image resultImage;
    Image face;
    Image tempImage;
    Image minuteImage;
    Image hourImage;
    Graphics pGraphics;
    Graphics offGraphics;
    static double radiansPerDegree;
    Date now;
    static Image candyimage;
    static Image minImage;
    static Image hImage;
    String str;
    URL imageURL;
    
    public Clock() {
        this.frameNumber = -1;
        this.delay = 1000;
        this.str = "";
    }
    
    public void start() {
        if (!this.frozen) {
            if (this.animatorThread == null) {
                this.animatorThread = new Thread(this);
            }
            this.animatorThread.start();
        }
    }
    
    public void stop() {
        this.animatorThread = null;
        this.mThread = null;
        this.hThread = null;
        this.offGraphics = null;
        this.offImage = null;
    }
    
    public void rotateImage(final double angle) {
        final ImageFilter filter = new RotateFilter(angle);
        final ImageProducer producer = new FilteredImageSource(Clock.candyimage.getSource(), filter);
        this.resultImage = this.createImage(producer);
        (this.tracker2 = new MediaTracker(this)).addImage(this.resultImage, 0);
        try {
            this.tracker2.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean mouseDown(final Event e, final int x, final int y) {
        if (this.frozen) {
            this.frozen = false;
            this.start();
        }
        else {
            this.frozen = true;
            this.animatorThread = null;
        }
        return true;
    }
    
    static {
        Clock.radiansPerDegree = 0.017453292519943295;
    }
    
    public String getAppletInfo() {
        return "Name: rcand\r\n" + "Author: Naeem Malik\r\n" + "Copyright (c). All rights reserved.";
    }
    
    public void run() {
        try {
            this.tracker1.waitForAll();
        }
        catch (InterruptedException ex) {}
        long startTime = System.currentTimeMillis();
        while (Thread.currentThread() == this.animatorThread) {
            ++this.frameNumber;
            this.repaint();
            try {
                startTime += this.delay;
                Thread.sleep(Math.max(0L, startTime - System.currentTimeMillis()));
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
    
    public void destroy() {
    }
    
    public void init() {
        this.imageURL = this.getCodeBase();
        this.str = this.getParameter("imageurl");
        try {
            if (this.str != null) {
                this.imageURL = new URL(this.str);
            }
        }
        catch (MalformedURLException ex) {}
        this.resize(100, 100);
        this.tracker1 = new MediaTracker(this);
        Clock.candyimage = this.getImage(this.imageURL, "second.gif");
        this.face = this.getImage(this.imageURL, "clock.gif");
        Clock.minImage = this.getImage(this.imageURL, "minute.gif");
        Clock.hImage = this.getImage(this.imageURL, "hour.gif");
        this.tracker1.addImage(Clock.candyimage, 0);
        this.tracker1.addImage(this.face, 1);
        this.tracker1.addImage(Clock.minImage, 3);
        this.tracker1.addImage(Clock.hImage, 2);
        (this.mThread = new MinuteThread("mins", this)).start();
        (this.hThread = new HourThread("hours", this)).start();
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void update(final Graphics g) {
        final Dimension d = this.size();
        int minute = 0;
        int hour = 0;
        if (this.offGraphics == null || d.width != this.offDimension.width || d.height != this.offDimension.height) {
            this.offDimension = d;
            this.offImage = this.createImage(d.width, d.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.setColor(Color.white);
        this.offGraphics.fillRect(0, 0, d.width, d.height);
        if (!this.tracker1.checkAll()) {
            this.offGraphics.clearRect(0, 0, d.width, d.height);
            this.offGraphics.drawString("Please wait...", 0, d.height / 2);
            g.drawImage(this.offImage, 0, 0, this);
        }
        else {
            this.offGraphics.drawImage(this.face, 0, 0, this);
            this.now = new Date();
            minute = this.now.getMinutes();
            hour = this.now.getHours();
            if (this.r == 0) {
                this.rotateImage(-this.now.getSeconds() * 6 * Clock.radiansPerDegree);
                ++this.r;
                ImageFilter filter = new RotateFilter(-minute * 6 * Clock.radiansPerDegree);
                ImageProducer producer = new FilteredImageSource(Clock.minImage.getSource(), filter);
                this.minuteImage = this.createImage(producer);
                (this.tracker2 = new MediaTracker(this)).addImage(this.minuteImage, 1);
                try {
                    this.tracker2.waitForID(1);
                }
                catch (InterruptedException ex) {}
                filter = new RotateFilter((-hour * 60 - minute) / 2 * Clock.radiansPerDegree);
                producer = new FilteredImageSource(Clock.hImage.getSource(), filter);
                this.hourImage = this.createImage(producer);
                this.tracker2.addImage(this.hourImage, 3);
                try {
                    this.tracker2.waitForID(3);
                }
                catch (InterruptedException ex2) {}
            }
            else {
                this.tracker2.addImage(this.minuteImage, 2);
                this.tracker2.addImage(this.hourImage, 3);
                try {
                    this.tracker2.waitForAll();
                }
                catch (InterruptedException ex3) {}
                if (this.tracker2.checkAll()) {
                    int x = (d.width - this.hourImage.getWidth(this)) / 2;
                    int y = (d.height - this.hourImage.getHeight(this)) / 2;
                    this.offGraphics.drawImage(this.hourImage, x, y, this);
                    x = (d.width - this.minuteImage.getWidth(this)) / 2;
                    y = (d.height - this.minuteImage.getHeight(this)) / 2;
                    this.offGraphics.drawImage(this.minuteImage, x, y, this);
                    this.tempImage = this.resultImage;
                    this.rotateImage(-this.now.getSeconds() * 6 * Clock.radiansPerDegree);
                    x = (d.width - this.tempImage.getWidth(this)) / 2;
                    y = (d.height - this.tempImage.getHeight(this)) / 2;
                    this.offGraphics.drawImage(this.tempImage, x, y, this);
                }
            }
            g.drawImage(this.offImage, 0, 0, this);
        }
    }
}
