// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Event;
import java.awt.Scrollbar;

class ImageSlider extends Scrollbar
{
    NuApplet applet;
    protected static final int SLIDER_CHANGED = 3010;
    
    protected ImageSlider(final NuApplet applet, final int items) {
        super(0, 0, 1, 0, items);
        this.applet = applet;
    }
    
    protected ImageSlider(final NuApplet applet) {
        super(0, 0, 1, 0, 0);
        this.applet = applet;
    }
    
    protected void setMaxItems(final int i) {
        this.setValues(0, 1, 0, i);
    }
    
    protected void setIndex(final int value) {
        if (this.getValue() != value) {
            super.setValue(value);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target instanceof ImageSlider && event.id != 605 && event.id != 602 && event.id != 601 && event.id != 604 && event.id != 603) {
            return true;
        }
        this.applet.sendMessage(3010, this);
        return super.handleEvent(event);
    }
}
