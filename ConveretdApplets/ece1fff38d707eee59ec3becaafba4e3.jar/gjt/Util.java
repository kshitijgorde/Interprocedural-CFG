// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.MediaTracker;
import java.awt.Frame;
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;

public class Util
{
    public static void wallPaper(final Component component, final Graphics g, final Image image) {
        final Dimension compsize = component.size();
        waitForImage(component, image);
        final int patchW = image.getWidth(component);
        final int patchH = image.getHeight(component);
        Assert.notFalse(patchW != -1 && patchH != -1);
        for (int r = 0; r < compsize.width; r += patchW) {
            for (int c = 0; c < compsize.height; c += patchH) {
                g.drawImage(image, r, c, component);
            }
        }
    }
    
    public static Applet getApplet(final Component component) {
        Component c = component;
        if (c instanceof Applet) {
            return (Applet)c;
        }
        while ((c = c.getParent()) != null) {
            if (c instanceof Applet) {
                return (Applet)c;
            }
        }
        return null;
    }
    
    public static Frame getFrame(final Component component) {
        Component c = component;
        if (c instanceof Frame) {
            return (Frame)c;
        }
        while ((c = c.getParent()) != null) {
            if (c instanceof Frame) {
                return (Frame)c;
            }
        }
        return null;
    }
    
    public static void waitForImage(final Component component, final Image image) {
        System.out.println("************ wait for called ************");
    }
    
    public static void reallyWaitForImage(final Component component, final Image image) {
        final MediaTracker tracker = new MediaTracker(component);
        try {
            tracker.addImage(image, 0);
            tracker.waitForID(0);
        }
        catch (InterruptedException e) {
            Assert.notNull(null);
        }
    }
    
    public static void setCursor(final int cursor, final Component component) {
        getFrame(component).setCursor(cursor);
    }
}
