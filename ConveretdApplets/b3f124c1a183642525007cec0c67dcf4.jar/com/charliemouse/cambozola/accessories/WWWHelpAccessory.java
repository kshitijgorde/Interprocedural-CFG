// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import com.charliemouse.cambozola.ViewerAttributeInterface;
import java.net.MalformedURLException;
import java.net.URL;
import com.charliemouse.cambozola.shared.AppID;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class WWWHelpAccessory extends Accessory
{
    public String getName() {
        return "Help [from the web]";
    }
    
    public String getDescription() {
        return "Displays web help page";
    }
    
    public boolean isEnabled() {
        return !this.getViewerAttributes().isStandalone();
    }
    
    public String getIconLocation() {
        return "www.gif";
    }
    
    public void actionPerformed(final Point point) {
        final AppID appID = AppID.getAppID();
        final ViewerAttributeInterface viewerAttributes = this.getViewerAttributes();
        try {
            viewerAttributes.displayURL(new URL(new StringBuffer(appID.getHelpURL()).toString()), null);
        }
        catch (MalformedURLException ex) {}
    }
}
