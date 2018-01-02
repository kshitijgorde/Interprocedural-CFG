// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Controller;
import org.collada.colladaschema.LibraryControllers;
import java.util.ArrayList;

public class LibraryControllersProcessor extends Processor
{
    private ArrayList<Processor> processors;
    
    public LibraryControllersProcessor(final LibraryControllers libraryControllers, final Processor parent) {
        super(libraryControllers, parent);
        this.processors = new ArrayList<Processor>();
        final List<Controller> cs = libraryControllers.getControllers();
        for (final Controller l : cs) {
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
