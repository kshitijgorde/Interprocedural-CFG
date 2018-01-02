// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import com.charliemouse.cambozola.ViewerAttributeInterface;
import java.net.URL;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class ChangeStreamAccessory extends Accessory
{
    private int current;
    
    public ChangeStreamAccessory() {
        this.current = 0;
    }
    
    public String getName() {
        return "Change Stream";
    }
    
    public String getIconLocation() {
        return "changeStream.gif";
    }
    
    public String getDescription() {
        return "Change the current stream";
    }
    
    public boolean isEnabled() {
        return this.getViewerAttributes().getAlternateURLs().size() > 1;
    }
    
    public void actionPerformed(final Point point) {
        final ViewerAttributeInterface viewerAttributes = this.getViewerAttributes();
        final int size = viewerAttributes.getAlternateURLs().size();
        if (size == 0) {
            return;
        }
        final int n = 15;
        if (Math.abs(n - point.x) < 1) {
            return;
        }
        this.current = (this.current + ((point.x > n) ? 1 : -1) + size) % size;
        final URL currentURL = viewerAttributes.getAlternateURLs().elementAt(this.current);
        System.err.println("New Current stream = #" + this.current + " - " + currentURL);
        viewerAttributes.setCurrentURL(currentURL);
        viewerAttributes.getViewArea().reset();
        viewerAttributes.repaint();
    }
}
