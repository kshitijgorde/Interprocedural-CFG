import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import javax.swing.JComponent;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_gb extends rp_gd
{
    private rp_fK a;
    private URL a;
    private rp_dX a;
    private int a;
    private JComponent a;
    
    public rp_gb(final rp_fK a, final URL a2, final rp_dX a3, final int a4, final JComponent a5) {
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.a = a4;
        this.a = a5;
    }
    
    private Image a() {
        final Image a = this.a.a(this.a);
        final MediaTracker mediaTracker;
        (mediaTracker = new MediaTracker(rp_au.a())).addImage(a, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return null;
        }
        if (a != null && a.getWidth(null) == -1) {
            return null;
        }
        return a;
    }
    
    public final void a() {
        try {
            final Image image = (Image)this.get();
            this.a.a(image, this.a);
            if (image != null) {
                this.a.invalidate();
                this.a.repaint();
                this.a.paintImmediately(0, 0, 100, 100);
            }
        }
        catch (Exception ex) {}
    }
}
