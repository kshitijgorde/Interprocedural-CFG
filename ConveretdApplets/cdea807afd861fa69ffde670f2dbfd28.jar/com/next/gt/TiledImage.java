// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Image;

public class TiledImage
{
    static Image tiledImage;
    
    public static Image createTiledImage(final Image image, final int n, final int n2, final Gamelication gamelication) {
        TiledImage.tiledImage = ((Component)gamelication).createImage(n, n2);
        for (int i = 0; i < n; i += image.getWidth(null)) {
            for (int j = 0; j < n2; j += image.getHeight(null)) {
                TiledImage.tiledImage.getGraphics().drawImage(image, i, j, (ImageObserver)gamelication);
            }
        }
        return TiledImage.tiledImage;
    }
}
