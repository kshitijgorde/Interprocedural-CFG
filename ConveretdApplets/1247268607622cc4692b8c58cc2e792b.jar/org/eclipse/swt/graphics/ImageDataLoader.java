// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import java.io.InputStream;

class ImageDataLoader
{
    public static ImageData[] load(final InputStream inputStream) {
        return new ImageLoader().load(inputStream);
    }
    
    public static ImageData[] load(final String s) {
        return new ImageLoader().load(s);
    }
}
