// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.awt.Image;
import com.fluendo.jst.Caps;
import java.awt.image.ImageObserver;
import com.fluendo.jst.Buffer;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Event;
import com.fluendo.jst.Pad;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Toolkit;
import com.fluendo.jst.Element;

public class JPEGDec extends Element
{
    private Toolkit toolkit;
    private Component component;
    private MediaTracker mediaTracker;
    private int width;
    private int height;
    private Pad srcpad;
    private Pad sinkpad;
    
    public JPEGDec() {
        this.srcpad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                return JPEGDec.this.sinkpad.pushEvent(event);
            }
        };
        this.sinkpad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                boolean result = false;
                switch (event.getType()) {
                    case 1: {
                        result = JPEGDec.this.srcpad.pushEvent(event);
                        synchronized (this.streamLock) {
                            Debug.log(3, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        result = JPEGDec.this.srcpad.pushEvent(event);
                        break;
                    }
                    default: {
                        result = JPEGDec.this.srcpad.pushEvent(event);
                        break;
                    }
                }
                return result;
            }
            
            protected int chainFunc(final Buffer buf) {
                Image img = null;
                img = JPEGDec.this.toolkit.createImage(buf.data, buf.offset, buf.length);
                int ret;
                if (img != null) {
                    try {
                        JPEGDec.this.mediaTracker.addImage(img, 0);
                        JPEGDec.this.mediaTracker.waitForID(0);
                        JPEGDec.this.mediaTracker.removeImage(img, 0);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        return -5;
                    }
                    final int imgWidth = img.getWidth(null);
                    final int imgHeight = img.getHeight(null);
                    if (imgWidth != JPEGDec.this.width || imgHeight != JPEGDec.this.height) {
                        JPEGDec.this.width = imgWidth;
                        JPEGDec.this.height = imgHeight;
                        Debug.log(3, "jpeg frame: " + JPEGDec.this.width + "," + JPEGDec.this.height);
                        (this.caps = new Caps("video/raw")).setFieldInt("width", JPEGDec.this.width);
                        this.caps.setFieldInt("height", JPEGDec.this.height);
                        this.caps.setFieldInt("aspect_x", 1);
                        this.caps.setFieldInt("aspect_y", 1);
                    }
                    buf.object = img;
                    buf.caps = this.caps;
                    ret = JPEGDec.this.srcpad.push(buf);
                }
                else {
                    System.out.println("could not decode jpeg image");
                    Debug.log(2, "could not decode jpeg image, continueing");
                    buf.free();
                    ret = 0;
                }
                return ret;
            }
        };
        this.toolkit = Toolkit.getDefaultToolkit();
        this.addPad(this.srcpad);
        this.addPad(this.sinkpad);
    }
    
    protected int changeState(final int transition) {
        switch (transition) {
            case 18: {
                this.width = -1;
                this.height = -1;
                break;
            }
        }
        final int res = super.changeState(transition);
        return res;
    }
    
    public boolean setProperty(final String name, final java.lang.Object value) {
        if (name.equals("component")) {
            this.component = (Component)value;
            this.toolkit = this.component.getToolkit();
            this.mediaTracker = new MediaTracker(this.component);
            return true;
        }
        return false;
    }
    
    public java.lang.Object getProperty(final String name) {
        if (name.equals("component")) {
            return this.component;
        }
        return null;
    }
    
    public String getFactoryName() {
        return "jpegdec";
    }
    
    public String getMime() {
        return "image/jpeg";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        return -1;
    }
}
