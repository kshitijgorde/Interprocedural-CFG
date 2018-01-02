// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Event;
import java.awt.Graphics;

public class CheckButton extends r
{
    private boolean fChecked;
    
    public CheckButton(final int n, final int n2) {
        super(n, n2);
        this.fChecked = false;
    }
    
    public CheckButton(final String s) {
        super(s);
        this.fChecked = false;
    }
    
    public CheckButton() {
        this.fChecked = false;
    }
    
    public void setChecked(final boolean d) {
        if (this.fChecked != d) {
            super.d = d;
            this.fChecked = d;
            final Graphics graphics;
            if ((graphics = super.getGraphics()) != null) {
                super.d = d;
                super.e = true;
                this.a(graphics);
                super.e = false;
                super.repaint();
                graphics.dispose();
            }
        }
    }
    
    public boolean getChecked() {
        return this.fChecked;
    }
    
    public boolean handleEvent(final Event event) {
        if (super.f) {
            if (this.inside(event.x, event.y)) {
                switch (event.id) {
                    default: {
                        return false;
                    }
                    case 501: {
                        if (this.fChecked) {
                            return true;
                        }
                        break;
                    }
                    case 502: {
                        if (!super.e) {
                            break;
                        }
                        final boolean fChecked = !this.fChecked;
                        this.fChecked = fChecked;
                        if (fChecked) {
                            this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.d()));
                            return true;
                        }
                        break;
                    }
                }
            }
            else if (this.fChecked || event.id != 506) {
                return false;
            }
        }
        return super.handleEvent(event);
    }
}
