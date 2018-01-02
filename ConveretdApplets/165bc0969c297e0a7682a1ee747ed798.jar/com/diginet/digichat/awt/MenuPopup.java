// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Window;
import java.awt.Event;
import java.awt.Point;
import java.awt.Component;

public class MenuPopup extends MenuPanel implements MenuController
{
    private boolean fActive;
    private Thread trdHide;
    private LayeredContainer lrcParent;
    
    public void show(final Component component, int n, int n2) {
        Component parent = component;
        do {
            final Point location = parent.getLocation();
            n += location.x;
            n2 += location.y;
        } while ((parent = parent.getParent()) != null && !(parent instanceof LayeredContainer));
        if (parent != null) {
            (this.lrcParent = (LayeredContainer)parent).setPopup(this);
            super.show(this.lrcParent, component, this, n, n2);
            this.requestFocus();
            this.fActive = true;
        }
    }
    
    public void hidePopup(final Event event) {
        this.fActive = false;
        this.hide();
        this.lrcParent.removePopup();
    }
    
    public void run() {
        if (Thread.currentThread() == this.trdHide) {
            int n = 0;
            while (this.fActive) {
                Component parent = this;
                while ((parent = parent.getParent()) != null && !(parent instanceof Window)) {}
                if (parent == null) {
                    if (n != 0) {
                        this.hidePopup(null);
                        return;
                    }
                    n = 1;
                }
                else {
                    final Component focusOwner;
                    if ((focusOwner = ((Window)parent).getFocusOwner()) != this) {
                        if (!(focusOwner instanceof MenuPanel)) {
                            this.hidePopup(null);
                        }
                        return;
                    }
                    n = 0;
                }
                try {
                    Thread.sleep(10000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        else {
            super.run();
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1004: {
                this.fActive = true;
                break;
            }
            case 1005: {
                (this.trdHide = new Thread(this)).start();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
