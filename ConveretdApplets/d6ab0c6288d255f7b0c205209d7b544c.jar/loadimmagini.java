import java.awt.Component;
import java.applet.Applet;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class loadimmagini
{
    Image[] imgs;
    MediaTracker ImageTracker;
    
    public loadimmagini(final URL context, final String dir, final Applet parent) {
        this.imgs = new Image[5];
        this.ImageTracker = new MediaTracker(parent);
        for (int i = 1; i <= this.imgs.length; ++i) {
            Image img;
            if (dir.equals("")) {
                img = parent.getImage(context, "stella" + i + ".jpg");
            }
            else {
                img = parent.getImage(context, String.valueOf(dir) + "/stella" + i + ".jpg");
            }
            this.ImageTracker.addImage(img, i);
            this.imgs[i - 1] = img;
        }
    }
}
