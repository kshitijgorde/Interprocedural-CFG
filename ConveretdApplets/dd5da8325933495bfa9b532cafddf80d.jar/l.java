import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class l extends k implements ImageObserver
{
    public synchronized boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.b();
            return false;
        }
        return true;
    }
}
