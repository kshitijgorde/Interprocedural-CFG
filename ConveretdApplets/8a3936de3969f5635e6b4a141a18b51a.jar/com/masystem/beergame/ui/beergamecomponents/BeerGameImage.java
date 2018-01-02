// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import java.awt.image.BufferedImage;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.component.ImageNode;

public class BeerGameImage extends ImageNode
{
    public BeerGameImage() {
    }
    
    public BeerGameImage(final String s) {
        super(ResourceManager.getOptimizedImage(s));
    }
    
    public BeerGameImage(final BufferedImage bufferedImage) {
        super(bufferedImage);
    }
}
