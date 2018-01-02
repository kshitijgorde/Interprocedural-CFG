// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Light;
import org.collada.colladaschema.LibraryLights;
import java.util.ArrayList;

public class LibraryLightsProcessor extends Processor
{
    private ArrayList<Processor> processors;
    
    public LibraryLightsProcessor(final LibraryLights libraryLights, final Processor parent) {
        super(libraryLights, parent);
        this.processors = new ArrayList<Processor>();
        final List<Light> lights = libraryLights.getLights();
        for (final Light l : lights) {
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
