// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import com.charliemouse.cambozola.ViewerAttributeInterface;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class PanAccessory extends Accessory
{
    public String getName() {
        return "Pan View";
    }
    
    public String getIconLocation() {
        return "pan.gif";
    }
    
    public String getDescription() {
        return "Pan around the image (must be zoomed in)";
    }
    
    public void actionPerformed(final Point point) {
        final ViewerAttributeInterface viewerAttributes = this.getViewerAttributes();
        final int n = 30 - point.x;
        final int n2 = 15;
        if (Math.abs(n2 - point.x) < 3 && Math.abs(n2 - point.y) < 3) {
            return;
        }
        final boolean b = point.x > point.y;
        final boolean b2 = n > point.y;
        if (b) {
            if (b2) {
                viewerAttributes.getViewArea().panVertical(-1);
            }
            else {
                viewerAttributes.getViewArea().panHorizontal(1);
            }
        }
        else if (b2) {
            viewerAttributes.getViewArea().panHorizontal(-1);
        }
        else {
            viewerAttributes.getViewArea().panVertical(1);
        }
        viewerAttributes.repaint();
    }
}
