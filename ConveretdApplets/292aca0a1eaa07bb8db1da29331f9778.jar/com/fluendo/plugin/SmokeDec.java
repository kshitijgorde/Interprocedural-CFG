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
import com.fluendo.codecs.SmokeCodec;
import java.awt.MediaTracker;
import java.awt.Component;
import com.fluendo.jst.Element;

public class SmokeDec extends Element
{
    private Component component;
    private MediaTracker mediaTracker;
    private SmokeCodec smoke;
    private int width;
    private int height;
    private Pad srcPad;
    private Pad sinkPad;
    
    public SmokeDec() {
        this.srcPad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                return SmokeDec.this.sinkPad.pushEvent(event);
            }
        };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                boolean result = false;
                switch (event.getType()) {
                    case 1: {
                        result = SmokeDec.this.srcPad.pushEvent(event);
                        synchronized (this.streamLock) {
                            Debug.log(3, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        result = SmokeDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        result = SmokeDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return result;
            }
            
            protected int chainFunc(final Buffer buf) {
                Image img = null;
                img = SmokeDec.this.smoke.decode(buf.data, buf.offset, buf.length);
                int ret;
                if (img != null) {
                    if (img.getWidth(null) != SmokeDec.this.width || img.getHeight(null) != SmokeDec.this.height) {
                        SmokeDec.this.width = img.getWidth(null);
                        SmokeDec.this.height = img.getHeight(null);
                        Debug.log(3, "smoke frame: " + SmokeDec.this.width + "," + SmokeDec.this.height);
                        (this.caps = new Caps("video/raw")).setFieldInt("width", SmokeDec.this.width);
                        this.caps.setFieldInt("height", SmokeDec.this.height);
                        this.caps.setFieldInt("aspect_x", 1);
                        this.caps.setFieldInt("aspect_y", 1);
                    }
                    buf.object = img;
                    buf.caps = this.caps;
                    ret = SmokeDec.this.srcPad.push(buf);
                }
                else {
                    if ((SmokeDec.this.smoke.flags & 0x1) != 0x0) {
                        Debug.log(2, "could not decode jpeg image");
                    }
                    buf.free();
                    ret = 0;
                }
                return ret;
            }
        };
        this.addPad(this.srcPad);
        this.addPad(this.sinkPad);
    }
    
    public boolean setProperty(final String name, final java.lang.Object value) {
        if (name.equals("component")) {
            this.component = (Component)value;
            this.mediaTracker = new MediaTracker(this.component);
            this.smoke = new SmokeCodec(this.component, this.mediaTracker);
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
        return "smokedec";
    }
    
    public String getMime() {
        return "video/x-smoke";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        if (data[offset + 1] == 115) {
            return 10;
        }
        return -1;
    }
}
