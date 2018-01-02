// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Material;
import org.collada.colladaschema.LibraryMaterials;
import java.util.ArrayList;

public class LibraryMaterialsProcessor extends Processor
{
    private ArrayList<Processor> processors;
    
    public LibraryMaterialsProcessor(final LibraryMaterials libraryMaterials, final Processor parent) {
        super(libraryMaterials, parent);
        this.processors = new ArrayList<Processor>();
        final List<Material> materials = libraryMaterials.getMaterials();
        for (final Material l : materials) {
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
