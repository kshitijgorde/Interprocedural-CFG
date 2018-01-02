import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_eO implements ImageObserver
{
    private /* synthetic */ rp_eB a;
    
    rp_eO(final rp_eB a) {
        this.a = a;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.a.repaint();
        return true;
    }
}
