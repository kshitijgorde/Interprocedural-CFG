// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class $FXB
{
    public static Image createImage(final $WWB $wwb, final Image image, final int n, final int n2) {
        if (n < 1 || n2 < 1) {
            if (image != null) {
                image.flush();
            }
            return null;
        }
        if (image != null && image.getWidth(null) == n && image.getHeight(null) == n2) {
            return image;
        }
        final Image image2 = $wwb.createImage(n, n2);
        if (image != null) {
            image.flush();
        }
        return image2;
    }
    
    public static Dimension getImageSize(final Image image) {
        if (image == null) {
            return new Dimension(0, 0);
        }
        return new Dimension(image.getWidth(null), image.getHeight(null));
    }
}
