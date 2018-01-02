// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

import java.awt.Event;
import com.diginet.digichat.awt.ba;
import com.diginet.digichat.awt.o;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;

class bw extends MouseAdapter
{
    private final /* synthetic */ Component a = a;
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (!(mouseEvent.getSource() instanceof o)) {
            if (!(mouseEvent.getSource() instanceof ba)) {
                return;
            }
        }
        try {
            this.a.getClass().getMethod("handleEvent", Class.forName("java.awt.Event")).invoke(this.a, new Event(mouseEvent.getSource(), mouseEvent.getWhen(), 501, mouseEvent.getX(), mouseEvent.getY(), -1, -1));
        }
        catch (Exception ex) {}
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (!(mouseEvent.getSource() instanceof o)) {
            if (!(mouseEvent.getSource() instanceof ba)) {
                return;
            }
        }
        try {
            this.a.getClass().getMethod("handleEvent", Class.forName("java.awt.Event")).invoke(this.a, new Event(mouseEvent.getSource(), mouseEvent.getWhen(), 502, mouseEvent.getX(), mouseEvent.getY(), -1, -1));
        }
        catch (Exception ex) {}
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        if (!(mouseEvent.getSource() instanceof o)) {
            if (!(mouseEvent.getSource() instanceof ba)) {
                return;
            }
        }
        try {
            this.a.getClass().getMethod("handleEvent", Class.forName("java.awt.Event")).invoke(this.a, new Event(mouseEvent.getSource(), mouseEvent.getWhen(), 504, mouseEvent.getX(), mouseEvent.getY(), -1, -1));
        }
        catch (Exception ex) {}
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (!(mouseEvent.getSource() instanceof o)) {
            if (!(mouseEvent.getSource() instanceof ba)) {
                return;
            }
        }
        try {
            this.a.getClass().getMethod("handleEvent", Class.forName("java.awt.Event")).invoke(this.a, new Event(mouseEvent.getSource(), mouseEvent.getWhen(), 505, mouseEvent.getX(), mouseEvent.getY(), -1, -1));
        }
        catch (Exception ex) {}
    }
    
    private final void a() {
    }
}
