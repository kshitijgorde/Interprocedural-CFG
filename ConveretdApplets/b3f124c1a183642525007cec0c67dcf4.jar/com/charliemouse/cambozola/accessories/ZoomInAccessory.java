// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import com.charliemouse.cambozola.ViewerAttributeInterface;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class ZoomInAccessory extends Accessory
{
    public String getName() {
        return "Zoom In";
    }
    
    public String getIconLocation() {
        return "zoomin.gif";
    }
    
    public String getDescription() {
        return "Zoom into the stream image";
    }
    
    public void actionPerformed(final Point point) {
        final ViewerAttributeInterface viewerAttributes = this.getViewerAttributes();
        viewerAttributes.getViewArea().zoomIn();
        viewerAttributes.repaint();
    }
}
