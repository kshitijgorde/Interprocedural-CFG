// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.plugin;

import java.awt.Image;
import java.awt.image.ImageProducer;
import com.fluendo.utils.Debug;
import com.fluendo.jst.Buffer;
import com.fluendo.jst.Event;
import com.fluendo.jst.Pad;
import com.fluendo.jtiger.Renderer;
import java.awt.Font;

public class KateOverlay extends Overlay
{
    private Font font;
    private String text;
    private Renderer tr;
    private Pad kateSinkPad;
    
    public KateOverlay() {
        this.font = null;
        this.text = null;
        this.tr = new Renderer();
        this.addPad(this.kateSinkPad = new Pad(2, "katesink") {
            protected boolean eventFunc(final Event event) {
                return true;
            }
            
            protected synchronized int chainFunc(final Buffer buffer) {
                KateOverlay.this.addKateEvent((com.fluendo.jkate.Event)buffer.object);
                return 0;
            }
        });
    }
    
    protected synchronized void addKateEvent(final com.fluendo.jkate.Event event) {
        this.tr.add(event);
        Debug.log(4, "Kate overlay got Kate event: " + new String(event.text));
    }
    
    protected synchronized void overlay(final Buffer buffer) {
        Image image;
        if (buffer.object instanceof ImageProducer) {
            image = super.component.createImage((ImageProducer)buffer.object);
        }
        else {
            if (!(buffer.object instanceof Image)) {
                System.out.println(this + ": unknown buffer received " + buffer);
                return;
            }
            image = (Image)buffer.object;
        }
        if (this.tr.update(super.component, image, buffer.timestamp / 1000000.0) == 1) {
            return;
        }
        buffer.object = this.tr.render(super.component, image);
    }
    
    public String getFactoryName() {
        return "kateoverlay";
    }
}
