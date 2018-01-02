// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import com.charliemouse.cambozola.ViewerAttributeInterface;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class ZoomOutAccessory extends Accessory
{
    public String getName() {
        return "Zoom Out";
    }
    
    public String getIconLocation() {
        return "zoomout.gif";
    }
    
    public String getDescription() {
        return "Zoom out of stream image";
    }
    
    public void actionPerformed(final Point point) {
        final ViewerAttributeInterface viewerAttributes = this.getViewerAttributes();
        viewerAttributes.getViewArea().zoomOut();
        viewerAttributes.repaint();
    }
}
