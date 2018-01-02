// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import org.collada.colladaschema.Image;

public class ImageProcessor extends Processor
{
    public ImageProcessor(final Image image, final Processor parent) {
        super(image, parent);
        final String id = image.getId();
        final String initFrom = image.getInitFrom();
        if (id != null) {
            ElementCache.cache().putImage(id, initFrom);
        }
    }
}
