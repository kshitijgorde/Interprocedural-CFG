import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletAnimation implements Runnable
{
    public AppletRotator rotator;
    public Color background;
    public int width;
    public int height;
    public int[] originalPixels;
    private int \u00c4;
    private AppletAnimFrame[] \u00c5;
    public String status;
    private Thread \u00c6;
    private double kx;
    private double ky;
    private double \u00c7;
    
    AppletAnimation() {
        this.rotator = new AppletRotator();
        this.status = "0%";
        this.\u00c6 = new Thread(this);
        this.kx = 0.03;
        this.ky = 0.01;
        this.\u00c7 = 8.0;
    }
    
    public void generateFrames(final Image image, final int \u00e4, final Color background) {
        this.background = background;
        this.\u00c4 = \u00e4;
        this.\u00c5 = new AppletAnimFrame[this.\u00c4];
        this.rotator.setRotation(0, 15, 0);
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
        this.\u00c4(image);
        this.\u00c6.start();
    }
    
    public void run() {
        this.\u00c6.setPriority(10);
        try {
            final int n = 360 / this.\u00c4;
            for (int i = 0; i < this.\u00c4; ++i) {
                final String string = "00" + i * 100 / this.\u00c4 + "%";
                this.status = string.substring(string.length() - 3);
                this.\u00c5[i] = new AppletAnimFrame(this, i * n);
            }
            this.status = "100%";
        }
        catch (Exception ex) {
            System.err.println("Error in animation frame generation.");
        }
    }
    
    public Image getFrame(final int n) {
        return this.\u00c5[n].getFrame();
    }
    
    public int calcZvalue(final int n, final int n2, final int n3) {
        this.kx = 0.03 + 11.0 * n / (this.width * this.width);
        this.ky = 0.01 + 4.4 * n / (this.width * this.width);
        if (n < this.width / 6) {
            this.\u00c7 = 1.0 + n * (8.0 / (this.width / 6));
        }
        else {
            this.\u00c7 = 8.0;
        }
        return (int)Math.rint(this.\u00c7 * Math.sin((float)(n3 * 0.017453292519943295 - this.kx * n + this.ky * n2)));
    }
    
    private void \u00c4(final Image image) {
        this.originalPixels = new int[this.width * this.height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.width, this.height, this.originalPixels, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            System.err.println("image fetch aborted or errored");
        }
    }
}
