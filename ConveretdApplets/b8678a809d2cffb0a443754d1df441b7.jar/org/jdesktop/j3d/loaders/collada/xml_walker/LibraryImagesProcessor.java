// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Image;
import org.collada.colladaschema.LibraryImages;
import java.util.ArrayList;

public class LibraryImagesProcessor extends Processor
{
    private ArrayList<Processor> processors;
    
    public LibraryImagesProcessor(final LibraryImages images, final Processor parent) {
        super(images, parent);
        this.processors = new ArrayList<Processor>();
        final List<Image> imgs = images.getImages();
        for (final Image l : imgs) {
            this.processors.add(ProcessorFactory.createProcessor(l, this));
        }
    }
    
    @Override
    public void create(final Node parent) {
        for (final Processor p : this.processors) {
            p.create(parent);
        }
    }
}
