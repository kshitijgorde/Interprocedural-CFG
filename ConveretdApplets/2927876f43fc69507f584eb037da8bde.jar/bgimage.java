import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class bgimage
{
    Image bgArt;
    boolean exist;
    Color bgcolor;
    
    bgimage(final Applet applet, final int n, final int n2) {
        this.bgcolor = Color.black;
        final String parameter = applet.getParameter("bgcolor");
        if (parameter != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter, 16));
        }
        applet.setBackground(this.bgcolor);
        final String parameter2 = applet.getParameter("background");
        if (parameter2 != null) {
            final MediaTracker mediaTracker = new MediaTracker(applet);
            System.err.println("Loading Background image: " + parameter2);
            this.exist = true;
            final Image image = applet.getImage(applet.getCodeBase(), parameter2);
            if (image == null) {
                this.exist = false;
            }
            else {
                try {
                    mediaTracker.addImage(image, 0);
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex) {
                    this.exist = false;
                }
            }
            if (this.exist) {
                this.bgArt = applet.createImage(n, n2);
                final Graphics graphics = this.bgArt.getGraphics();
                graphics.setColor(this.bgcolor);
                graphics.fillRect(0, 0, n, n2);
                String parameter3 = applet.getParameter("bgalign");
                if (parameter3 == null) {
                    parameter3 = "tile";
                }
                if (parameter3.toLowerCase().equals("center")) {
                    int n3 = n / 2 - image.getWidth(null) / 2;
                    int n4 = n2 / 2 - image.getHeight(null) / 2;
                    if (n3 < 0) {
                        n3 = 0;
                    }
                    if (n4 < 0) {
                        n4 = 0;
                    }
                    graphics.drawImage(image, n3, n4, null);
                }
                else if (parameter3.toLowerCase().equals("scale")) {
                    graphics.drawImage(image, 0, 0, n, n2, null);
                }
                else {
                    final int width = image.getWidth(null);
                    final int height = image.getHeight(null);
                    int i = 0;
                    int j = 0;
                    System.err.println("x=" + width + " y=" + height);
                    if (width > 0 && height > 0) {
                        while (j < n2) {
                            while (i < n) {
                                graphics.drawImage(image, i, j, null);
                                i += width;
                            }
                            i = 0;
                            j += height;
                        }
                    }
                }
                graphics.dispose();
                image.flush();
            }
        }
    }
    
    void draw(final Graphics graphics, final int n, final int n2) {
        if (this.exist) {
            graphics.drawImage(this.bgArt, 0, 0, null);
        }
        else {
            graphics.setColor(this.bgcolor);
            graphics.fillRect(0, 0, n, n2);
        }
    }
}
