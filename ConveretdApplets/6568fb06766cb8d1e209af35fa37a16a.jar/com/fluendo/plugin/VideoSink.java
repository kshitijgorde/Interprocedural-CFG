// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.ImageProducer;
import com.fluendo.jst.Buffer;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Caps;
import java.awt.Rectangle;
import java.awt.Frame;
import java.awt.Component;
import com.fluendo.jst.Sink;

public class VideoSink extends Sink
{
    private Component component;
    private boolean keepAspect;
    private boolean ignoreAspect;
    private boolean scale;
    private Frame frame;
    private int width;
    private int height;
    private int aspectX;
    private int aspectY;
    private Rectangle bounds;
    
    public VideoSink() {
        this.keepAspect = true;
        this.scale = true;
        this.bounds = null;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        if (!caps.getMime().equals("video/raw")) {
            return false;
        }
        this.width = caps.getFieldInt("width", -1);
        this.height = caps.getFieldInt("height", -1);
        if (this.width == -1 || this.height == -1) {
            return false;
        }
        this.aspectX = caps.getFieldInt("aspect_x", 1);
        this.aspectY = caps.getFieldInt("aspect_y", 1);
        if (!this.ignoreAspect) {
            Debug.log(4, this + " dimension: " + this.width + "x" + this.height + ", aspect: " + this.aspectX + "/" + this.aspectY);
            if (this.aspectY > this.aspectX) {
                this.height = this.height * this.aspectY / this.aspectX;
            }
            else {
                this.width = this.width * this.aspectX / this.aspectY;
            }
            Debug.log(4, this + " scaled source: " + this.width + "x" + this.height);
        }
        this.component.setVisible(true);
        return true;
    }
    
    protected int preroll(final Buffer buffer) {
        return this.render(buffer);
    }
    
    protected int render(final Buffer buffer) {
        Debug.log(4, this.getName() + " starting buffer " + buffer);
        Image image;
        if (buffer.object instanceof ImageProducer) {
            image = this.component.createImage((ImageProducer)buffer.object);
        }
        else {
            if (!(buffer.object instanceof Image)) {
                System.out.println(this + ": unknown buffer received " + buffer);
                return -5;
            }
            image = (Image)buffer.object;
        }
        if (!this.component.isVisible()) {
            return -4;
        }
        final Graphics graphics = this.component.getGraphics();
        int n3;
        int n4;
        int n5;
        int n6;
        if (this.keepAspect) {
            if (this.bounds == null) {
                this.bounds = new Rectangle(this.component.getSize());
            }
            final double n = this.width / this.height;
            final double n2 = this.bounds.width / this.bounds.height;
            if (n > n2) {
                n3 = this.bounds.width;
                n4 = (int)(this.bounds.width / n);
                n5 = this.bounds.x;
                n6 = this.bounds.y + (this.bounds.height - n4) / 2;
            }
            else if (n < n2) {
                n3 = (int)(this.bounds.height * n);
                n4 = this.bounds.height;
                n5 = this.bounds.x + (this.bounds.width - n3) / 2;
                n6 = this.bounds.y;
            }
            else {
                n5 = this.bounds.x;
                n6 = this.bounds.y;
                n3 = this.bounds.width;
                n4 = this.bounds.height;
            }
        }
        else if (!this.scale) {
            n3 = Math.min(this.width, this.bounds.width);
            n4 = Math.min(this.height, this.bounds.height);
            n5 = this.bounds.x + (this.bounds.width - n3) / 2;
            n6 = this.bounds.y + (this.bounds.height - n4) / 2;
        }
        else {
            n3 = this.bounds.width;
            n4 = this.bounds.height;
            n5 = 0;
            n6 = 0;
        }
        graphics.drawImage(image, n5, n6, n3, n4, null);
        Debug.log(4, this.getName() + " done with buffer " + buffer);
        return 0;
    }
    
    public String getFactoryName() {
        return "videosink";
    }
    
    public boolean setProperty(final String s, final java.lang.Object o) {
        if (s.equals("component")) {
            this.component = (Component)o;
        }
        else if (s.equals("keep-aspect")) {
            this.keepAspect = String.valueOf(o).equals("true");
        }
        else if (s.equals("ignore-aspect")) {
            this.ignoreAspect = o.toString().equals("true");
        }
        else if (s.equals("scale")) {
            this.scale = String.valueOf(o).equals("true");
        }
        else {
            if (!s.equals("bounds")) {
                return super.setProperty(s, o);
            }
            this.bounds = (Rectangle)o;
            Debug.info("Video bounding rectangle: x=" + this.bounds.x + ", y=" + this.bounds.y + ", w=" + this.bounds.width + ", h=" + this.bounds.height);
        }
        return true;
    }
    
    public java.lang.Object getProperty(final String s) {
        if (s.equals("component")) {
            return this.component;
        }
        if (s.equals("keep-aspect")) {
            return this.keepAspect ? "true" : "false";
        }
        if (s.equals("bounds")) {
            return this.bounds;
        }
        return super.getProperty(s);
    }
    
    protected int changeState(final int n) {
        if (super.currentState == 1 && super.pendingState == 2 && this.component == null) {
            this.frame = new Frame();
            this.component = this.frame;
        }
        return super.changeState(n);
    }
}
