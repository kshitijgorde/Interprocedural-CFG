// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.image.ImageProducer;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Caps;
import java.awt.Frame;
import java.awt.Component;
import com.fluendo.jst.Sink;

public class VideoSink extends Sink
{
    private Component component;
    private boolean keepAspect;
    private Frame frame;
    private int width;
    private int height;
    private int aspect_x;
    private int aspect_y;
    
    public VideoSink() {
        this.keepAspect = true;
    }
    
    protected boolean setCapsFunc(final Caps caps) {
        final String mime = caps.getMime();
        if (!mime.equals("video/raw")) {
            return false;
        }
        this.width = caps.getFieldInt("width", -1);
        this.height = caps.getFieldInt("height", -1);
        if (this.width == -1 || this.height == -1) {
            return false;
        }
        this.aspect_x = caps.getFieldInt("aspect_x", 1);
        this.aspect_y = caps.getFieldInt("aspect_y", 1);
        if (this.aspect_y > this.aspect_x) {
            this.height = this.height * this.aspect_y / this.aspect_x;
        }
        else {
            this.width = this.width * this.aspect_x / this.aspect_y;
        }
        this.component.setVisible(true);
        return true;
    }
    
    protected int preroll(final Buffer buf) {
        return this.render(buf);
    }
    
    protected int render(final Buffer buf) {
        Image image;
        if (buf.object instanceof ImageProducer) {
            image = this.component.createImage((ImageProducer)buf.object);
        }
        else {
            if (!(buf.object instanceof Image)) {
                System.out.println(this + ": unknown buffer received " + buf);
                return -5;
            }
            image = (Image)buf.object;
        }
        if (!this.component.isVisible()) {
            return -4;
        }
        final Dimension d = this.component.getSize();
        final Graphics graphics = this.component.getGraphics();
        int w;
        int h;
        int x;
        int y;
        if (this.keepAspect) {
            w = d.width;
            h = d.height;
            x = 0;
            y = 0;
        }
        else {
            w = d.width;
            h = d.height;
            x = 0;
            y = 0;
        }
        graphics.drawImage(image, x, y, w, h, null);
        return 0;
    }
    
    public String getFactoryName() {
        return "videosink";
    }
    
    public boolean setProperty(final String name, final java.lang.Object value) {
        if (name.equals("component")) {
            this.component = (Component)value;
        }
        else {
            if (!name.equals("keep-aspect")) {
                return false;
            }
            this.keepAspect = String.valueOf(value).equals("true");
        }
        return true;
    }
    
    public java.lang.Object getProperty(final String name) {
        if (name.equals("component")) {
            return this.component;
        }
        if (name.equals("keep-aspect")) {
            return this.keepAspect ? "true" : "false";
        }
        return null;
    }
    
    protected int changeState(final int transition) {
        if (this.currentState == 1 && this.pendingState == 2 && this.component == null) {
            this.frame = new Frame();
            this.component = this.frame;
        }
        return super.changeState(transition);
    }
}
