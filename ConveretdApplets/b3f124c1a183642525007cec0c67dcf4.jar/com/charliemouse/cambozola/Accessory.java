// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola;

import java.awt.Point;
import java.io.IOException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.Image;

public abstract class Accessory
{
    public static final int ICON_SIZE = 24;
    public static final int ICON_INDENT = 3;
    public static final int BUTTON_SIZE = 30;
    public ViewerAttributeInterface m_viewerAttribs;
    private String m_key;
    
    public void setViewerAttributes(final ViewerAttributeInterface viewerAttribs) {
        this.m_viewerAttribs = viewerAttribs;
    }
    
    public ViewerAttributeInterface getViewerAttributes() {
        return this.m_viewerAttribs;
    }
    
    public Accessory() {
        this.m_viewerAttribs = null;
        this.m_key = "";
        final String name = this.getClass().getName();
        final String substring = name.substring(name.lastIndexOf(".") + 1);
        if (substring.endsWith("Accessory")) {
            this.m_key = substring.substring(0, substring.length() - 9);
        }
    }
    
    public String getKey() {
        return this.m_key;
    }
    
    public String getName() {
        return "NoName";
    }
    
    public String getDescription() {
        return null;
    }
    
    public boolean isEnabled() {
        return true;
    }
    
    public String getIconLocation() {
        return null;
    }
    
    public Image getIconImage() {
        final String iconLocation = this.getIconLocation();
        if (iconLocation == null) {
            return null;
        }
        try {
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(this.getClass().getResourceAsStream(iconLocation)));
            final byte[] array = new byte[dataInputStream.available()];
            dataInputStream.readFully(array);
            final Image image = Toolkit.getDefaultToolkit().createImage(array);
            final MediaTracker mediaTracker = new MediaTracker(new Panel());
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex2) {}
            return image;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void terminate() {
    }
    
    public void actionPerformed(final Point point) {
        System.err.println("<Default handler> " + this.getName() + " accessory called");
    }
}
