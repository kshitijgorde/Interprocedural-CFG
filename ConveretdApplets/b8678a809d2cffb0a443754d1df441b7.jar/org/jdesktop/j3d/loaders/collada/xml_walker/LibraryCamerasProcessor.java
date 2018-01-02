// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import org.collada.colladaschema.LibraryCameras;
import java.util.logging.Logger;

public class LibraryCamerasProcessor extends Processor
{
    private Logger logger;
    
    public LibraryCamerasProcessor(final LibraryCameras camera, final Processor parent) {
        super(camera, parent);
        (this.logger = Logger.getLogger("collada.processor")).info("LibraryCamera");
    }
}
