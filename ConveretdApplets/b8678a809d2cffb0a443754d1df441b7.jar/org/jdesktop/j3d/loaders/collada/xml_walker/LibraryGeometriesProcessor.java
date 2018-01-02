// 
// Decompiled by Procyon v0.5.30
// 

package org.jdesktop.j3d.loaders.collada.xml_walker;

import javax.media.j3d.Node;
import java.util.Iterator;
import java.util.List;
import org.collada.colladaschema.Asset;
import org.collada.colladaschema.Geometry;
import org.collada.colladaschema.LibraryGeometries;
import java.util.ArrayList;

public class LibraryGeometriesProcessor extends Processor
{
    private ArrayList<Processor> children;
    
    public LibraryGeometriesProcessor(final LibraryGeometries jaxbNode, final Processor parent) {
        super(jaxbNode, parent);
        this.children = new ArrayList<Processor>();
        final Asset asset = jaxbNode.getAsset();
        final List<Geometry> geoms = jaxbNode.getGeometries();
        for (final Geometry nodeType : geoms) {
            this.children.add(ProcessorFactory.createProcessor(nodeType, this));
        }
    }
    
    @Override
    public void create(final Node parent) {
        for (final Processor p : this.children) {
            p.create(parent);
        }
    }
}
