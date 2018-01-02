// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import com.fluendo.jst.Caps;
import com.fluendo.jst.Buffer;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Event;
import com.fluendo.jst.Pad;
import com.fluendo.jst.Element;

public class MulawDec extends Element
{
    private int rate;
    private int channels;
    private Pad srcPad;
    private Pad sinkPad;
    
    public MulawDec() {
        this.srcPad = new Pad(1, "src") {
            protected boolean eventFunc(final Event event) {
                return MulawDec.this.sinkPad.pushEvent(event);
            }
        };
        this.sinkPad = new Pad(2, "sink") {
            protected boolean eventFunc(final Event event) {
                boolean result = false;
                switch (event.getType()) {
                    case 1: {
                        result = MulawDec.this.srcPad.pushEvent(event);
                        synchronized (this.streamLock) {
                            Debug.log(3, "synced " + this);
                        }
                        break;
                    }
                    case 2: {
                        result = MulawDec.this.srcPad.pushEvent(event);
                        break;
                    }
                    default: {
                        result = MulawDec.this.srcPad.pushEvent(event);
                        break;
                    }
                }
                return result;
            }
            
            protected int chainFunc(final Buffer buf) {
                if (this.caps == null) {
                    Debug.log(3, "mulaw: rate: " + MulawDec.this.rate);
                    Debug.log(3, "mulaw: channels: " + MulawDec.this.channels);
                    (this.caps = new Caps("audio/x-mulaw")).setFieldInt("rate", MulawDec.this.rate);
                    this.caps.setFieldInt("channels", MulawDec.this.channels);
                }
                buf.caps = this.caps;
                final int ret = MulawDec.this.srcPad.push(buf);
                return ret;
            }
        };
        this.rate = 8000;
        this.channels = 1;
        this.addPad(this.srcPad);
        this.addPad(this.sinkPad);
    }
    
    public String getFactoryName() {
        return "mulawdec";
    }
    
    public String getMime() {
        return "audio/x-mulaw";
    }
    
    public int typeFind(final byte[] data, final int offset, final int length) {
        return -1;
    }
}
