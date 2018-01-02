// 
// Decompiled by Procyon v0.5.30
// 

package com.q10.applet.bean;

import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Component;

public class Util
{
    public static void waitForImage(final Component component, final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        try {
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            mediaTracker.removeImage(image, 0);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
