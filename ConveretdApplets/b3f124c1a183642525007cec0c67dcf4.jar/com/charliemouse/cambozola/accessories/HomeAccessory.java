// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import com.charliemouse.cambozola.ViewerAttributeInterface;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class HomeAccessory extends Accessory
{
    public String getName() {
        return "Home View";
    }
    
    public String getDescription() {
        return "Resets the view to show the complete stream image";
    }
    
    public String getIconLocation() {
        return "home.gif";
    }
    
    public void actionPerformed(final Point point) {
        final ViewerAttributeInterface viewerAttributes = this.getViewerAttributes();
        viewerAttributes.getViewArea().reset();
        viewerAttributes.repaint();
    }
}
