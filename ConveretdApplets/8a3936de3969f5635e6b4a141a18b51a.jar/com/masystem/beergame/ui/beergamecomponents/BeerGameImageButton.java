// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import com.masystem.beergame.ui.graphics.FixedSizeImage;
import com.masystem.beergame.resource.ResourceManager;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.component.StretchableButtonNode;

public final class BeerGameImageButton extends StretchableButtonNode
{
    public BeerGameImageButton(final String s, final String s2, final String s3, final String s4) {
        super(load(s), load(s2), load(s3), load(s4));
        this.setSize(this.getReleasedImage().getSourceImage().getWidth(), this.getReleasedImage().getSourceImage().getHeight());
    }
    
    private static final StretchableImage load(final String s) {
        try {
            return new FixedSizeImage(ResourceManager.getOptimizedImage(s));
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
