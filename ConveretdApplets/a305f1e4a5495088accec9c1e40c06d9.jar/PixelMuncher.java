import java.awt.Image;
import java.awt.image.PixelGrabber;

// 
// Decompiled by Procyon v0.5.30
// 

class PixelMuncher extends Thread
{
    static int muncher_number;
    int done;
    PixelGrabber p;
    Thread thread;
    
    public PixelMuncher(final Image image, final int n, final int n2, final int[] array) {
        super("pixel muncher number " + PixelMuncher.muncher_number++);
        this.setPriority(1);
        this.p = new PixelGrabber(image.getSource(), 0, 0, n, n2, array, 0, n);
        this.start();
    }
    
    public void run() {
        try {
            this.p.grabPixels();
            this.done = 1;
        }
        catch (Exception ex) {}
    }
    
    public int ready() {
        return this.done;
    }
}
