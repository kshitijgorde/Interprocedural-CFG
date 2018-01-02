// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.graphics;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class FixedSizeImage extends StretchableImage
{
    public FixedSizeImage(final BufferedImage bufferedImage) {
        super(bufferedImage);
    }
    
    @Override
    public final void draw$2f958723(final Graphics graphics, final int n, final int n2) {
        graphics.drawImage(this.getSourceImage(), 0, 0, null);
    }
}
