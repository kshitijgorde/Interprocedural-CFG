// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.awt.Frame;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.jst.Pad;
import java.awt.Component;
import com.fluendo.jst.Element;

public class Overlay extends Element
{
    protected Component component;
    private Pad videoSrcPad;
    private Pad videoSinkPad;
    
    public Overlay() {
        this.videoSrcPad = new Pad(1, "videosrc") {
            protected boolean eventFunc(final Event event) {
                return Overlay.this.videoSinkPad.pushEvent(event);
            }
        };
        this.addPad(this.videoSinkPad = new Pad(2, "videosink") {
            protected boolean eventFunc(final Event event) {
                return Overlay.this.videoSrcPad.pushEvent(event);
            }
            
            protected int chainFunc(final Buffer buffer) {
                Debug.log(4, super.parent.getName() + " <<< " + buffer);
                Overlay.this.overlay(buffer);
                final int push = Overlay.this.videoSrcPad.push(buffer);
                if (push != 0) {
                    Debug.log(2, super.parent.getName() + ": failed to push buffer to video source pad: " + push);
                }
                return push;
            }
            
            protected boolean activateFunc(final int n) {
                return true;
            }
        });
        this.addPad(this.videoSrcPad);
    }
    
    protected void overlay(final Buffer buffer) {
    }
    
    public boolean setProperty(final String s, final java.lang.Object o) {
        if (s.equals("component")) {
            this.component = (Component)o;
            return true;
        }
        return super.setProperty(s, o);
    }
    
    public java.lang.Object getProperty(final String s) {
        if (s.equals("component")) {
            return this.component;
        }
        return super.getProperty(s);
    }
    
    protected int changeState(final int n) {
        if (super.currentState == 1 && super.pendingState == 2 && this.component == null) {
            this.component = new Frame();
        }
        return super.changeState(n);
    }
    
    public String getFactoryName() {
        return "overlay";
    }
}
