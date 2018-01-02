// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.imageloader;

import javax.swing.ImageIcon;

public class LocalImageLoader implements ImageLoader
{
    private String imageFolder;
    
    public LocalImageLoader(final String imageFolder) {
        this.imageFolder = imageFolder;
    }
    
    public ImageIcon loadImage(final String imageName) {
        return new ImageIcon(this.getClass().getResource(this.imageFolder + imageName));
    }
}
