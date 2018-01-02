import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

class backgroundLoading implements Runnable
{
    MediaTracker MT;
    ImageZoom2 IZ2;
    
    public backgroundLoading(final ImageZoom2 IZ, final MediaTracker MTracker) {
        this.MT = MTracker;
        this.IZ2 = IZ;
    }
    
    public void run() {
        this.IZ2.repaint();
        this.IZ2.requestFocus();
        try {
            this.MT.waitForID(1);
        }
        catch (InterruptedException IE) {
            this.IZ2.showStatus("Cannot load Image");
            System.out.println("Cannot load Image");
            System.exit(1);
        }
        this.IZ2.loading = false;
        this.IZ2.repaint();
    }
}
