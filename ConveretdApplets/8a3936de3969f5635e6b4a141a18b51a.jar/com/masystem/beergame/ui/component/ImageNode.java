// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component;

import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import com.masystem.beergame.ui.component.swing.ImageComponent;
import com.masystem.beergame.ui.scene.Node;

public class ImageNode extends Node
{
    public ImageNode() {
        super(new ImageComponent());
    }
    
    public ImageNode(final BufferedImage bufferedImage) {
        super(new ImageComponent(bufferedImage));
    }
    
    public final BufferedImage getImage() {
        return ((ImageComponent)super.getComponent()).getImage();
    }
    
    public final void setImage(final BufferedImage image) {
        ((ImageComponent)super.getComponent()).setImage(image);
    }
    
    @Override
    public final void setCacheEnabled(final boolean cacheEnabled) {
        if (!cacheEnabled || this.getNbrChildren() != 0) {
            super.setCacheEnabled(cacheEnabled);
        }
    }
    
    @Override
    public final ImageComponent getComponent() {
        return (ImageComponent)super.getComponent();
    }
}
