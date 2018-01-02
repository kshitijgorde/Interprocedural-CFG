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

class background
{
    private int[] bgarray;
    private int size;
    boolean exist;
    
    background(final Applet applet, final Image image, final int n, final int n2) {
        this.size = n * n2;
        this.exist = false;
        this.bgarray = new int[this.size];
        for (int i = 0; i < this.size; ++i) {
            this.bgarray[i] = -16777216;
        }
        final String parameter = applet.getParameter("background");
        if (parameter != null) {
            final MediaTracker mediaTracker = new MediaTracker(applet);
            this.exist = true;
            final Graphics graphics = image.getGraphics();
            graphics.setColor(Color.gray);
            graphics.setFont(new Font("TimesRoman", 1, 11));
            graphics.drawString("Loading BackGround...", 5, 20);
            graphics.drawString(String.valueOf(String.valueOf(applet.getCodeBase())) + parameter, 5, 35);
            applet.repaint();
            applet.showStatus("Loading BackGround: " + applet.getCodeBase() + parameter);
            System.err.println("BackGround " + applet.getCodeBase() + parameter);
            Image image2 = applet.getImage(applet.getCodeBase(), parameter);
            if (image2 == null) {
                this.exist = false;
            }
            else {
                try {
                    mediaTracker.addImage(image2, 0);
                    mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex) {
                    image2 = null;
                    this.exist = false;
                }
            }
            if (this.exist) {
                graphics.drawImage(image2, 0, 0, n, n2, null);
                try {
                    new PixelGrabber(image, 0, 0, n, n2, this.bgarray, 0, n).grabPixels();
                }
                catch (InterruptedException ex2) {
                    this.exist = false;
                }
                image2.flush();
            }
            graphics.dispose();
        }
    }
    
    public boolean draw(final int[] array) {
        System.arraycopy(this.bgarray, 0, array, 0, this.size);
        return this.exist;
    }
}
