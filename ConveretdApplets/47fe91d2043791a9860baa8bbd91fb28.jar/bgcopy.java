import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class bgcopy
{
    bgcopy(final Applet applet, final Image image, final int n, final int n2, final int[] array) {
        for (int n3 = n * n2, i = 0; i < n3; ++i) {
            array[i] = -16777216;
        }
        final String parameter = applet.getParameter("background");
        if (parameter != null) {
            final MediaTracker mediaTracker = new MediaTracker(applet);
            boolean b = true;
            final Graphics graphics = image.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 5, n, 20);
            graphics.setColor(Color.gray);
            graphics.drawRect(0, 5, n, 20);
            graphics.setColor(Color.black);
            graphics.setFont(new Font("Arial", 1, 11));
            graphics.drawString("Loading BackGround: " + parameter, 10, 20);
            applet.repaint();
            applet.showStatus("Loading BackGround: " + applet.getCodeBase() + parameter);
            System.err.println("BackGround " + applet.getCodeBase() + parameter);
            Image image2 = applet.getImage(applet.getCodeBase(), parameter);
            if (image2 == null) {
                b = false;
            }
            else {
                try {
                    mediaTracker.addImage(image2, 0);
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex) {
                    image2 = null;
                    b = false;
                }
            }
            if (b) {
                graphics.drawImage(image2, 0, 0, n, n2, null);
                try {
                    new PixelGrabber(image, 0, 0, n, n2, array, 0, n).grabPixels();
                }
                catch (InterruptedException ex2) {}
                image2.flush();
            }
            graphics.dispose();
            System.gc();
        }
    }
}
