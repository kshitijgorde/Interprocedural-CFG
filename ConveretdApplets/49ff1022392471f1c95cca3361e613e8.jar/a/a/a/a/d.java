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
        ae.char = new af();
        while (!ae.b) {
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
            Thread.yield();
        }
        final Image image = ac.C.createImage(ae.k);
        ac.if.addImage(image, 0);
        try {
            ac.if.waitForAll();
        }
        catch (Exception ex2) {}
        ae.char.do = image.getWidth(null);
        ae.char.case = image.getHeight(null);
        ae.char.new = new int[ae.char.do * ae.char.case];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, ae.char.do, ae.char.case, ae.char.new, 0, ae.char.do);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex3) {}
        pixelGrabber.getStatus();
        ac.if.removeImage(image, 0);
        for (int i = 0; i < ae.char.do * ae.char.case; ++i) {
            if ((ae.char.new[i] & 0xFF000000) == 0x0) {
                ae.char.char = true;
                break;
            }
        }
        ae.char.int = true;
        ae.case = true;
        ae.char.a();
        return true;
    }
}