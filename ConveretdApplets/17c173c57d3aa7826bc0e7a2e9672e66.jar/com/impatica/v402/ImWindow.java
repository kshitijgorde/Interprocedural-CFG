// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.MenuBar;
import java.awt.Frame;

public class ImWindow extends Frame
{
    ImPlayer I;
    boolean Z;
    MenuBar C;
    
    public boolean handleEvent(final Event event) {
        if (event.id == 401) {
            if (this.I.B && event.key == 27) {
                this.I.I(false);
                return true;
            }
        }
        else if (!ImFullScreen.C && (event.id == 403 || event.id == 404 || (event.id == 402 && (event.modifiers & 0x8) != 0x0))) {
            if (this.I.B) {
                this.I.I(false);
                return true;
            }
        }
        else if (ImFullScreen.Z && event.id == 203 && event.target == this) {
            if (this.I.B) {
                this.I.I(false);
            }
        }
        else if (event.id == 201) {
            if (this.I.B) {
                if (ImFullScreen.Z) {
                    this.I.I(false);
                }
            }
            else {
                this.I.stop();
                this.dispose();
                if (this.Z) {
                    System.exit(0);
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public final void layout() {
        super.layout();
        final Rectangle bounds = this.getBounds();
        final Insets insets = this.getInsets();
        this.I.setSize(bounds.width - insets.left - insets.right, bounds.height - insets.top - insets.bottom);
    }
    
    void I(final String s) {
    }
}
