// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graphics;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.Component;

public class Util
{
    public static Dialog getDialog(Component parent) {
        if (parent instanceof Dialog) {
            return (Dialog)parent;
        }
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof Dialog) {
                return (Dialog)parent;
            }
        }
        return null;
    }
    
    public static Frame getFrame(Component parent) {
        if (parent instanceof Frame) {
            return (Frame)parent;
        }
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof Frame) {
                return (Frame)parent;
            }
        }
        return null;
    }
    
    public static Applet getApplet(Component parent) {
        if (parent instanceof Applet) {
            return (Applet)parent;
        }
        while ((parent = parent.getParent()) != null) {
            if (parent instanceof Applet) {
                return (Applet)parent;
            }
        }
        return null;
    }
    
    public static void waitForImage(final Component component, final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        try {
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void wallPaper(final Component component, final Graphics graphics, final Image image) {
        final Dimension size = component.getSize();
        waitForImage(component, image);
        final int width = image.getWidth(component);
        final int height = image.getHeight(component);
        Assert.notFalse(width != -1 && height != -1);
        for (int i = 0; i < size.width; i += width) {
            for (int j = 0; j < size.height; j += height) {
                graphics.drawImage(image, i, j, component);
            }
        }
    }
    
    public static void stretchImage(final Component component, final Graphics graphics, final Image image) {
        final Dimension size = component.getSize();
        waitForImage(component, image);
        graphics.drawImage(image, 0, 0, size.width, size.height, component);
    }
    
    public static void setCursor(final int n, final Component component) {
        component.setCursor(Cursor.getPredefinedCursor(n));
    }
    
    public static boolean isMicrosoft() {
        return System.getProperty("java.vendor").toLowerCase().startsWith("microsoft");
    }
}
