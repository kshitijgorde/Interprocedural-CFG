import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

public class p
{
    public boolean a(final aa aa, final l l) {
        aa.case = new an();
        if (l.h) {
            return false;
        }
        while (!aa.void) {
            if (l.h) {
                return false;
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        final Image image = l.byte.createImage(aa.k, 0, aa.if);
        l.b.addImage(image, 0);
        try {
            l.b.waitForID(0);
        }
        catch (Exception ex2) {}
        aa.case.long = image.getWidth(null);
        aa.case.e = image.getHeight(null);
        aa.case.c = new int[aa.case.long * aa.case.e];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, aa.case.long, aa.case.e, aa.case.c, 0, aa.case.long);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex3) {}
        pixelGrabber.getStatus();
        l.b.removeImage(image, 0);
        for (int i = 0; i < aa.case.long * aa.case.e; ++i) {
            if ((aa.case.c[i] & 0xFF000000) == 0x0) {
                aa.case.f = true;
                break;
            }
        }
        aa.case.for = true;
        return aa.byte = true;
    }
}
