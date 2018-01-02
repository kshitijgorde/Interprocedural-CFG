// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;

public class d
{
    public boolean a(final ae ae, final ac ac) {
        if (ac.G) {
            return false;
        }
        while (!ae.b) {
            if (ac.G) {
                return false;
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
        boolean b = false;
        final int n = 12 + (int)(3.0 * Math.pow(2.0, (ae.l[10] & 0x7) + 1));
        if (ae.l.length > n + 1 && (ae.l[n + 1] & 0xFF) == 0x21 && (ae.l[n + 2] & 0xFF) == 0xFF) {
            b = true;
        }
        final Image image = ac.I.createImage(ae.l, 0, ae.do);
        ac.if.addImage(image, 0);
        try {
            ac.if.waitForID(0);
        }
        catch (Exception ex2) {}
        ae.char = new af();
        ae.char.do = image.getWidth(null);
        ae.char.else = image.getHeight(null);
        if (ae.char.do == -1 || ae.char.else == -1) {
            ae.char.do = 1;
            ae.char.else = 1;
            (ae.char.new = new int[1])[0] = 0;
            ae.char.goto = true;
            System.out.println("Error in image " + ae.n);
        }
        else {
            ae.char.new = new int[ae.char.do * ae.char.else];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, ae.char.do, ae.char.else, ae.char.new, 0, ae.char.do);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex3) {}
            pixelGrabber.getStatus();
            if (b) {
                ae.char.char = image;
                ae.char.try = ac.P.createImage(ae.char.do, ae.char.else);
            }
            for (int i = 0; i < ae.char.do * ae.char.else; ++i) {
                if ((ae.char.new[i] & 0xFF000000) == 0x0) {
                    ae.char.goto = true;
                    break;
                }
            }
        }
        ae.char.goto &= !b;
        ae.char.int = true;
        ae.case = true;
        ae.char.a();
        return true;
    }
}
