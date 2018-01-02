// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.image;

import java.awt.AWTEvent;

public class ImageEvent extends AWTEvent
{
    private SerializableImage image;
    public static final int IMAGE_CHANGED = 0;
    private static final long serialVersionUID = -7671078796273832149L;
    
    public ImageEvent(final Object o, final int n, final SerializableImage image) {
        super(o, n);
        this.image = image;
    }
    
    public SerializableImage getImage() {
        return this.image;
    }
}
