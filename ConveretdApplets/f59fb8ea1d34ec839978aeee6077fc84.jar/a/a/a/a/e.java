// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;

public class e
{
    public boolean a(final aq aq, final an an) {
        if (an.G) {
            return false;
        }
        while (!aq.b) {
            if (an.G) {
                return false;
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        boolean b = false;
        final int n = 12 + (int)(3.0 * Math.pow(2.0, (aq.l[10] & 0x7) + 1));
        if (aq.l.length > n + 1 && (aq.l[n + 1] & 0xFF) == 0x21 && (aq.l[n + 2] & 0xFF) == 0xFF) {
            b = true;
        }
        final Image image = an.I.createImage(aq.l, 0, aq.do);
        an.if.addImage(image, 0);
        try {
            an.if.waitForID(0);
        }
        catch (Exception ex2) {}
        aq.char = new ar();
        aq.char.do = image.getWidth(null);
        aq.char.else = image.getHeight(null);
        if (aq.char.do == -1 || aq.char.else == -1) {
            aq.char.do = 1;
            aq.char.else = 1;
            (aq.char.new = new int[1])[0] = 0;
            aq.char.goto = true;
            System.out.println("Error in image " + aq.n);
        }
        else {
            aq.char.new = new int[aq.char.do * aq.char.else];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, aq.char.do, aq.char.else, aq.char.new, 0, aq.char.do);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex3) {}
            pixelGrabber.getStatus();
            if (b) {
                aq.char.char = image;
                aq.char.try = an.P.createImage(aq.char.do, aq.char.else);
            }
            for (int i = 0; i < aq.char.do * aq.char.else; ++i) {
                if ((aq.char.new[i] & 0xFF000000) == 0x0) {
                    aq.char.goto = true;
                    break;
                }
            }
        }
        aq.char.goto &= !b;
        aq.char.int = true;
        aq.case = true;
        aq.char.a();
        return true;
    }
}
