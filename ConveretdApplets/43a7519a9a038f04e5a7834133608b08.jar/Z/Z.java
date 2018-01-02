// 
// Decompiled by Procyon v0.5.30
// 

package Z;

import core.S;
import I.I;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.applet.Applet;

public final class Z
{
    public final int a;
    public final int b;
    public final int[] c;
    
    public Z(Applet image, final URL url) {
        final MediaTracker mediaTracker = new MediaTracker(image);
        image = (Applet)image.getImage(url);
        mediaTracker.addImage((Image)image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.a = ((Image)image).getWidth(null);
        this.b = ((Image)image).getHeight(null);
        int[] c;
        try {
            Class.forName(I.I(704));
            final Z.I i = new Z.I((Image)image);
            ((Image)image).flush();
            image = null;
            c = i.a();
        }
        catch (Throwable t) {
            c = S.a((Image)image);
        }
        this.c = c;
    }
}
